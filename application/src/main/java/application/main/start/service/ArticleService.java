package application.main.start.service;

import application.main.start.dto.ArticleDto;
import application.main.start.mapper.ArticleMapper;
import application.main.start.model.Article;
import application.main.start.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Transactional
    public ArticleDto create(ArticleDto articleDto) {
        articleDto.setDate(LocalDate.now());
        articleRepository.save(articleMapper.toEntity(articleDto));
        return articleDto;
    }

    @Transactional
    public ArticleDto update(ArticleDto articleDto) {
        Article article = articleRepository.findById(articleDto.getId())
                .map(a -> articleMapper.toUpdatedEntity(articleDto, a))
                .orElseThrow(
                        () -> new RuntimeException("Not found article")
                );
        return articleMapper.toDto(article);
    }

}
