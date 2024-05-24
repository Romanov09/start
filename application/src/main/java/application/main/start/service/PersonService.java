package application.main.start.service;

import application.main.start.repository.PersonRepository;
import application.main.start.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person findById(int id) {
        return personRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found beach")
        );
    }


}
