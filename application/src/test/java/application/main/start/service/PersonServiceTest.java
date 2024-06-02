package application.main.start.service;

import application.main.start.controller.handler.exception.NotFoundException;
import application.main.start.dto.PersonDto;
import application.main.start.mapper.PersonMapper;
import application.main.start.model.Person;
import application.main.start.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper personMapper;
    @InjectMocks
    private PersonService personService;

    private Person person;
    private PersonDto personDto;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setId(1);
        person.setFirstName("Test");
        person.setLastName("Push");

        personDto = new PersonDto();
        personDto.setId(1);
        personDto.setFirstName("Test");
        personDto.setLastName("Push");
    }

    @Test
    @DisplayName("Успешный поиск юзера по айди")
    void testFindPerson() {
        when(personRepository.findById(eq(1))).thenReturn(Optional.ofNullable(person));
        when(personMapper.toDto(any(Person.class))).thenReturn(personDto);

        PersonDto result = personService.findById(1);

        assertEquals(result.getId(), personDto.getId());
        assertEquals(result.getFirstName(), personDto.getFirstName());
        assertEquals(result.getLastName(), personDto.getLastName());

        verify(personRepository, times(1)).findById(eq(1));
        verify(personMapper, times(1)).toDto(any(Person.class));
    }

    @Test
    @DisplayName("Ошибка при поиске юзера по айди")
    void testFindPersonWhenNotFound() {
        when(personRepository.findById(eq(1))).thenReturn(Optional.empty());

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> {
            personService.findById(1);
        });

        assertEquals("User not found", notFoundException.getMessage());

        verify(personRepository, times(1)).findById(eq(1));
        verify(personMapper, never()).toDto(any(Person.class));
    }

    @Test
    @DisplayName("Успешное создание юзера")
    void testCreate() {
        when(personMapper.toEntity(any(PersonDto.class))).thenReturn(person);
        when(personRepository.save(any(Person.class))).thenReturn(person);

        PersonDto result = personService.create(personDto);

        assertEquals(result.getId(), person.getId());
        assertEquals(result.getFirstName(), person.getFirstName());
        assertEquals(result.getLastName(), person.getLastName());

        verify(personRepository, times(1)).save(any(Person.class));
        verify(personMapper, times(1)).toEntity(any(PersonDto.class));
    }

    @Test
    @DisplayName("Успешное обновление юзера")
    void testUpdate() {
        when(personRepository.findById(eq(1))).thenReturn(Optional.ofNullable(person));
        when(personMapper.toDto(any(Person.class))).thenReturn(personDto);
        when(personMapper.toUpdatedEntity(any(PersonDto.class), any(Person.class))).thenReturn(person);

        PersonDto result = personService.update(personDto);

        assertEquals(result.getId(), person.getId());
        assertEquals(result.getFirstName(), person.getFirstName());
        assertEquals(result.getLastName(), person.getLastName());

        verify(personRepository, times(1)).findById(eq(1));
        verify(personMapper, times(1)).toDto(any(Person.class));
        verify(personMapper, times(1)).toUpdatedEntity(any(PersonDto.class), any(Person.class));
    }

    @Test
    @DisplayName("Ошибка при обновлении юзера")
    void testUpdateCauseUnexpectedError() {
        when(personRepository.findById(eq(1))).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            personService.update(personDto);
        });

        assertEquals("При обновлении произошла ошибка", exception.getMessage());

        verify(personRepository, times(1)).findById(eq(1));
        verify(personMapper, never()).toDto(any(Person.class));
        verify(personMapper, never()).toUpdatedEntity(any(PersonDto.class), any(Person.class));
    }
}

