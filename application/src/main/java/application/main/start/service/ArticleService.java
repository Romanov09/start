package application.main.start.service;

import application.main.start.repository.ArticleRepository;
import application.main.start.dto.ArticleDto;
import application.main.start.mapper.ArticleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper = ArticleMapper.INSTANCE;

    @Transactional
    public ArticleDto saveArticle(ArticleDto articleDto) {
        return save(articleDto);
    }

    @Transactional
    public void editArticle(ArticleDto articleDto) {
        articleRepository.findById(articleDto.getId())
                .map(article -> articleMapper.toUpdatedEntity(articleDto, article))
                .orElseThrow(
                () -> new RuntimeException("User not Found")
        );
    }


    private ArticleDto save(ArticleDto articleDto) {
        articleDto.setDate(LocalDate.now());
        articleRepository.save(articleMapper.toEntity(articleDto));
        return articleDto;
    }
}
