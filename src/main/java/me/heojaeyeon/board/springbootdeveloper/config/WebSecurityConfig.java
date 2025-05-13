package me.heojaeyeon.board.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.heojaeyeon.board.springbootdeveloper.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

//@RequiredArgsConstructor // final이 붙은 필드를 대상으로 생성자를 자동 생성
//@Configuration //해당 클래스가 설정클래스임을 나타낸다.
//public class WebSecurityConfig {
//
//    private final UserDetailService userService;
//
//    //스프링 시큐리티 기능 비활성화
//    @Bean
//    //정적 자원이나 특정 요청을 Spring Security 필터에서 제외
//    public WebSecurityCustomizer configure(){
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console()) //H2 데이터베이스 콘솔은 보안 필터에서 제외해야 정상적으로 접근 가능
//                .requestMatchers("/static/**");//JS, CSS, 이미지 등 정적 자원은 인증 없이 접근할 수 있도록 함.
//    }
//
//    //특정 HTTP요청에 대해 웹 기반 보안을 구성. 인증/인가 및 로그인, 로그아웃 관련 설정가능
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        return http
//                .authorizeRequests()
//                .requestMatchers("/login", "/signup", "/user").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/articles")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true)
//                .and()
//                .csrf().disable()
//                .build();
//
//    }
//
//    //인증관리자 관련 설정
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
//                                                       UserDetailService userDetailService) throws Exception{
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userService)
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }
//
//
//
//
//    @Bean
//    //비밀번호 암호화에 사용되는 인코더. 암호화된 비밀번호를 DB에 저장하고 로그인 시 비교
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}
