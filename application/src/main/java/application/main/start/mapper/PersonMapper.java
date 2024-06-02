package application.main.start.mapper;

import application.main.start.dto.PersonDto;
import application.main.start.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDto toDto(Person person);

    Person toEntity(PersonDto personDto);

    Person toUpdatedEntity(PersonDto personDto, @MappingTarget Person person);
}
