package me.heojaeyeon.board.springbootdeveloper.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.heojaeyeon.board.springbootdeveloper.domain.Article;

@NoArgsConstructor //기본생성자 추가
@AllArgsConstructor // 모든 필드 값을 피라미터로 받는 생성자 추가
@Getter //getter 자동생성
public class AddArticleRequest {

    private String title;
    private String content;

    // toEntity는 빌더패턴을 사용해 DTO를 엔티티로 만들어주는 메서드이다.블로그에 글을 추가할 때 저장할 엔티티로 뱐환하는 용도로 사용
    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

    public Article toEntity(String author){
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
