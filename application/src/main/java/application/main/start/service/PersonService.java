package application.main.start.service;

import application.main.start.controller.handler.exception.NotFoundException;
import application.main.start.dto.PersonDto;
import application.main.start.mapper.PersonMapper;
import application.main.start.model.Person;
import application.main.start.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonDto findById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        return personMapper.toDto(person);
    }

    @Transactional
    public PersonDto create(PersonDto personDto) {
        personRepository.save(personMapper.toEntity(personDto));

        return personDto;
    }

    @Transactional
    public PersonDto update(PersonDto personDto) {
        Person person = personRepository.findById(personDto.getId())
                .map(entity -> personMapper.toUpdatedEntity(personDto, entity))
                .orElseThrow(
                        () -> new RuntimeException("При обновлении произошла ошибка")
                );

        return personMapper.toDto(person);
    }

}
