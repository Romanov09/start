package application.main.start.controller;

import application.main.start.dto.PersonDto;
import application.main.start.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PatchMapping("/createPerson")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto create(PersonDto personDto) {
        return personService.create(personDto);
    }

    @GetMapping("/{$id}")
    public PersonDto findById(@PathVariable Integer id) {
        return personService.findById(id);
    }

    @PatchMapping("/editPerson/{$id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto update(PersonDto personDto) {
        return personService.update(personDto);
    }
}
