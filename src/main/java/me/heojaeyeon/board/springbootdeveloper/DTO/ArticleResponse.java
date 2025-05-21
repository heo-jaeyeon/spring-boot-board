package me.heojaeyeon.board.springbootdeveloper.DTO;

import lombok.Getter;
import me.heojaeyeon.board.springbootdeveloper.domain.Article;

@Getter
public class ArticleResponse {
    private final Long id;
    private final String title;
    private final String content;

    public ArticleResponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
