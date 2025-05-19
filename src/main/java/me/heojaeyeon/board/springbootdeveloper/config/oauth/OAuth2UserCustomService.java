package me.heojaeyeon.board.springbootdeveloper.config.oauth;

import lombok.RequiredArgsConstructor;
import me.heojaeyeon.board.springbootdeveloper.domain.User;
import me.heojaeyeon.board.springbootdeveloper.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        try {
            System.out.println("loadUser() 진입 확인됨");

            OAuth2User oAuth2User = super.loadUser(userRequest);
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            System.out.println("registrationId = " + registrationId);
            System.out.println("attributes = " + oAuth2User.getAttributes());

            saveOrUpdate(oAuth2User, registrationId);

            return oAuth2User;

        } catch (Exception e) {
            System.err.println("loadUser() 내부 오류 발생: " + e.getMessage());
            e.printStackTrace(); // 상세 예외 로그
            throw new OAuth2AuthenticationException("카카오 로그인 중 오류 발생" + e);
        }
    }


    //유저가 있으면 업데이트, 없으면 유저 생성
    private User saveOrUpdate(OAuth2User oAuth2User, String registrationId){
        Map<String,Object> attributes = oAuth2User.getAttributes();
        String email = null;
        String name = null;

        if("kakao".equals(registrationId)){
            System.out.println("1단계: attributes 가져옴");
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            System.out.println("2단계: kakao_account 가져옴 = " + kakaoAccount);
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            System.out.println("3단계: profile 가져옴 = " + profile);

            email = (String) kakaoAccount.get("email");
            System.out.println("4단계: email = " + email);
            name = (String) profile.get("nickname");
            System.out.println("5단계: nickname = " + name);

            if(email == null){
                throw new OAuth2AuthenticationException("카카오에서 이메일을 제공하지 않았습니다.");
            }
        }else{
            email = (String) attributes.get("email");
            name = (String) attributes.get("name");
        }

        String finalName = name;
        System.out.println("name =" + finalName);
        System.out.println("email =" + email);
        User user = userRepository.findByEmail(email)
                .map(entity->entity.update(finalName))
                .orElse(User.builder()
                        .email(email)
                        .nickname(finalName)
                        .build());
        return userRepository.save(user);
    }

}
