package me.heojaeyeon.board.springbootdeveloper.controller;


import lombok.RequiredArgsConstructor;
import me.heojaeyeon.board.springbootdeveloper.DTO.ArticleListViewResponse;
import me.heojaeyeon.board.springbootdeveloper.DTO.ArticleResponse;
import me.heojaeyeon.board.springbootdeveloper.DTO.ArticleViewResponse;
import me.heojaeyeon.board.springbootdeveloper.domain.Article;
import me.heojaeyeon.board.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/search")
    public String searchByArticle(@RequestParam(value="keyword", required = false) String keyword, Model model){
        List<ArticleResponse> articles = (keyword != null && !keyword.trim().isEmpty())
                ? blogService.searchByArticle(keyword)
                : blogService.findAllArticles();

        model.addAttribute("articles", articles);
        model.addAttribute("keyword", keyword);
        return "articleList";

    }
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }


    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        }else{
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
