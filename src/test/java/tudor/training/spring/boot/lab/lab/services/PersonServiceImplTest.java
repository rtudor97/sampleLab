package tudor.training.spring.boot.lab.lab.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tudor.training.spring.boot.lab.lab.exceptions.PersonNotFoundException;
import tudor.training.spring.boot.lab.lab.models.Person;
import tudor.training.spring.boot.lab.lab.models.PhoneNumber;
import tudor.training.spring.boot.lab.lab.repos.PersonRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PersonServiceImplTest {
    @MockBean
    private PersonRepo mockPersonRepo;

    @Autowired
    private PersonService personService;

    private Person inputPerson;
    private Person mockResponsePerson01;
    private Person mockResponsePerson02;

    @BeforeEach
    public void setUp(){
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("(302)123-4567", true));
        phoneNumbers.add(new PhoneNumber("(610)123-9876", false));

        inputPerson = new Person("Ryan", "Tudor", phoneNumbers);

        mockResponsePerson01 = new Person("Ryan", "Tudor", phoneNumbers);
        mockResponsePerson01.setId(1);

        mockResponsePerson02 = new Person("Ryan", "Tudor", phoneNumbers);
        mockResponsePerson02.setId(2);
    }

    @Test
    @DisplayName("Person Service: Create Person - Success")
    public void createPersonTest(){
        BDDMockito.doReturn(mockResponsePerson01).when(mockPersonRepo).save(ArgumentMatchers.any());
        Person returnedPerson = personService.create(inputPerson);
        Assertions.assertNotNull(returnedPerson, "Person should not be null");
        Assertions.assertEquals(returnedPerson.getId(), 1);
    }

    @Test
    @DisplayName("Person Service: Update Person - Success")
    public void updatePersonSuccess() throws PersonNotFoundException{
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("302",true));
        phoneNumbers.add(new PhoneNumber("102",false));
        Person expectedPersonUpdate = new Person("After","Update person", phoneNumbers);

        BDDMockito.doReturn(Optional.of(mockResponsePerson01)).when(mockPersonRepo).findById(1);
        BDDMockito.doReturn(expectedPersonUpdate).when(mockPersonRepo).save(ArgumentMatchers.any());

        Person actualPerson = personService.updatePerson(1,expectedPersonUpdate);
        Assertions.assertEquals(expectedPersonUpdate.toString(),actualPerson.toString());
    }

    @Test
    @DisplayName("PersonService: Update Person - Fail")
    public void updatePersonFail(){
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("302",true));
        phoneNumbers.add(new PhoneNumber("102",false));
        Person expectedPersonUpdate = new Person("After","Update person", phoneNumbers);
        BDDMockito.doReturn(Optional.empty()).when(mockPersonRepo).findById(1);
        Assertions.assertThrows(PersonNotFoundException.class, () ->{
            personService.updatePerson(1,expectedPersonUpdate);
        });
    }

    @Test
    @DisplayName("Person Service: Delete Person - Success")
    public void deletePersonTestSuccess() throws PersonNotFoundException {
        BDDMockito.doReturn(Optional.of(mockResponsePerson01)).when(mockPersonRepo).findById(1);
        Boolean actualResponse = personService.deletePerson(1);
        Assertions.assertTrue(actualResponse);
    }

    @Test
    @DisplayName("Person Service: Delete Person - Fail")
    public void deletePersonTestFail(){
        BDDMockito.doReturn(Optional.empty()).when(mockPersonRepo).findById(1);
        Assertions.assertThrows(PersonNotFoundException.class, () ->{
            personService.deletePerson(1);
        });
    }
}
