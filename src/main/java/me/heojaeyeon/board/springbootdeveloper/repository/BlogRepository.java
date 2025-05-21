package me.heojaeyeon.board.springbootdeveloper.repository;

import me.heojaeyeon.board.springbootdeveloper.DTO.ArticleResponse;
import me.heojaeyeon.board.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository  extends JpaRepository<Article, Long> {
    List<Article> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
}
