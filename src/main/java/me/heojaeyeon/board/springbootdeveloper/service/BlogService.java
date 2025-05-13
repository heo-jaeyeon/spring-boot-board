package me.heojaeyeon.board.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.heojaeyeon.board.springbootdeveloper.DTO.AddArticleRequest;
import me.heojaeyeon.board.springbootdeveloper.DTO.UpdateArticleRequest;
import me.heojaeyeon.board.springbootdeveloper.domain.Article;
import me.heojaeyeon.board.springbootdeveloper.repository.BlogRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service //빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    //save()는 AddArticleRequest클래스에 저장된 값들을 article 데이터베이스에 저장한다.
    public Article save(AddArticleRequest request, String userName) {
        return blogRepository.save(request.toEntity(userName));
    }

    //블로그에 있는 모든 글을 자겨오는 메서드
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    //블로그에 있는 글 하나를 조회하는 메서드
    public Article findById(Long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:" + id));
    }

    // 블로그에 있는 글 삭제 메서드
    public void delete(Long id){
        Article article = blogRepository.findById(id)
                        .orElseThrow(()-> new IllegalArgumentException("not found:" + id));

        authorizeArticleAuthor(article);
        blogRepository.deleteById(id);
    }

    //블로그에 있는 글 수정 메서드
    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article  = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:" + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    // 게시글을 작성한 유저인지 확인
    private static void authorizeArticleAuthor(Article article){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(!article.getAuthor().equals(userName)){
            throw new IllegalArgumentException("not found:" + userName);
        }
    }
}
