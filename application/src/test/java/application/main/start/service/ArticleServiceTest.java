package application.main.start.service;

import application.main.start.dto.ArticleDto;
import application.main.start.mapper.ArticleMapper;
import application.main.start.model.Article;
import application.main.start.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleMapper articleMapper;
    @InjectMocks
    private ArticleService articleService;

    private ArticleDto articleDto;
    private Article article;

    @BeforeEach
    void setUp() {
        article = new Article();
        article.setId(1);
        article.setText("Text");
        article.setHeader("Header");

        articleDto = new ArticleDto();
        articleDto.setId(1);
        articleDto.setText("Text");
        articleDto.setHeader("Header");
    }

    @Test
    @DisplayName("Проверка сохранения статьи")
    void testSave() {
        when(articleMapper.toEntity(any(ArticleDto.class))).thenReturn(article);
        when(articleRepository.save(any(Article.class))).thenReturn(article);

        ArticleDto result = articleService.create(articleDto);

        assertEquals(result.getHeader(), article.getHeader());
        assertEquals(result.getText(), article.getText());
        assertEquals(LocalDate.now(), result.getDate());

        verify(articleMapper, times(1)).toEntity(any(ArticleDto.class));
        verify(articleRepository, times(1)).save(any(Article.class));
    }

    @Test
    @DisplayName("Проверка обновления статьи")
    void testUpdate() {
        when(articleRepository.findById(1)).thenReturn(Optional.ofNullable(article));
        when(articleMapper.toUpdatedEntity(any(ArticleDto.class), any(Article.class))).thenReturn(article);
        when(articleMapper.toDto(any(Article.class))).thenReturn(articleDto);

        ArticleDto result = articleService.update(articleDto);

        assertEquals(result.getId(), article.getId());
        assertEquals(result.getHeader(), article.getHeader());
        assertEquals(result.getText(), article.getText());

        verify(articleRepository, times(1)).findById(eq(1));
        verify(articleMapper, times(1)).toUpdatedEntity(any(ArticleDto.class), any(Article.class));
        verify(articleMapper, times(1)).toDto(any(Article.class));

    }

    @Test
    @DisplayName("Проверка выброса ошибки при обновлении статьи")
    void testUpdateWhenArticleNotFound() {
        when(articleRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            articleService.update(articleDto);
        });

        assertEquals("Not found article", exception.getMessage());

        verify(articleRepository, times(1)).findById(eq(1));
        verify(articleMapper, never()).toUpdatedEntity(any(ArticleDto.class), any(Article.class));
        verify(articleMapper, never()).toDto(any(Article.class));
    }
}