package application.main.start.mapper;

import application.main.start.users.Article;
import application.main.start.dto.ArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    ArticleDto articleDto(Article article);

    Article toEntity(ArticleDto articleDto);

    Article toUpdatedEntity(ArticleDto articleDto, @MappingTarget Article article);
}
