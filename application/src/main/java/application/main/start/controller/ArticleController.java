package application.main.start.controller;

import application.main.start.dto.ArticleDto;
import application.main.start.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/article")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PutMapping("/createArticle")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ArticleDto> createArticle(ArticleDto articleDto) {
        return ResponseEntity.ok(articleService.create(articleDto));
    }

    @PatchMapping("/editArticle")
    public ResponseEntity<ArticleDto> editArticle(ArticleDto articleDto) {
        return ResponseEntity.ok(articleService.update(articleDto));
    }
}
