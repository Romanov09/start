package application.main.start.controllers;

import application.main.start.dto.ArticleDto;
import application.main.start.service.ArticleService;
import exception.main.handler.resource.ResourceBadRequestException;
import exception.main.handler.resource.ResourceUnauthorizedException;
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
        try {
            return ResponseEntity.ok(articleService.saveArticle(articleDto));
        } catch (ResourceBadRequestException ex) {
            throw new ResourceBadRequestException("Check you input message");
        }
    }

    @PatchMapping("/editArticle")
    public ResponseEntity<String> editArticle(ArticleDto articleDto) {
        try {
            articleService.editArticle(articleDto);
        } catch (ResourceUnauthorizedException ex) {
            throw new ResourceUnauthorizedException("Access denied");
        }
        return ResponseEntity.ok("");
    }
}
