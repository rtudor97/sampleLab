package tudor.training.spring.boot.lab.lab.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tudor.training.spring.boot.lab.lab.exceptions.PersonNotFoundException;
import tudor.training.spring.boot.lab.lab.models.Person;
import tudor.training.spring.boot.lab.lab.repos.PersonRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{
    private static Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    private PersonRepo personRepo;

    @Autowired
    public PersonServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public Person create(Person person) {
        Person savedPerson = personRepo.save(person);
        return savedPerson;
    }

    @Override
    public Person getPersonById(Integer id) throws PersonNotFoundException {
        Optional<Person> personOptional = personRepo.findById(id);
        if(personOptional.isEmpty()){
            logger.error("Person with id {} does not exist", id);
            throw new PersonNotFoundException("Person not found");
        }
        return personOptional.get();
    }

    @Override
    public List getAllPeople() {
        return (List) personRepo.findAll();
    }

    @Override
    public Person updatePerson(Integer id, Person person) throws PersonNotFoundException {
        Optional<Person> personOptional = personRepo.findById(id);
        if(personOptional.isEmpty()) {
            throw new PersonNotFoundException("Person does not exist, cannot update");
        }
        Person savedPerson = personOptional.get();
        savedPerson.setPhoneNumbers(person.getPhoneNumbers());
        savedPerson.setLastName(person.getLastName());
        return personRepo.save(savedPerson);
    }

    @Override
    public Boolean deletePerson(Integer id) throws PersonNotFoundException {
        Optional<Person> personOptional = personRepo.findById(id);
        if(personOptional.isEmpty()){
            throw new PersonNotFoundException("Person does not exist, cannot delete");}
        Person personToDelete = personOptional.get();
        personRepo.delete(personToDelete);
        return true;
    }
}
