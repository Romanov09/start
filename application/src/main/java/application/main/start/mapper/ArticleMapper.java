package application.main.start.mapper;

import application.main.start.model.Article;
import application.main.start.dto.ArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDto toDto(Article article);

    Article toEntity(ArticleDto articleDto);

    Article toUpdatedEntity(ArticleDto articleDto, @MappingTarget Article article);
}
