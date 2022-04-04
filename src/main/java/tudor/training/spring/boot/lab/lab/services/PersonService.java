package tudor.training.spring.boot.lab.lab.services;

import tudor.training.spring.boot.lab.lab.exceptions.PersonNotFoundException;
import tudor.training.spring.boot.lab.lab.models.Person;

import java.util.List;

public interface PersonService {
    Person create(Person person);
    Person getPersonById(Integer id) throws PersonNotFoundException;
    List<Person> getAllPeople();
    Person updatePerson(Integer id, Person person) throws PersonNotFoundException;
    Boolean deletePerson(Integer id) throws PersonNotFoundException;
}
