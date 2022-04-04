package tudor.training.spring.boot.lab.lab.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tudor.training.spring.boot.lab.lab.models.Person;
import tudor.training.spring.boot.lab.lab.models.PhoneNumber;
import tudor.training.spring.boot.lab.lab.services.PersonService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class PersonControllerTest {
    @MockBean
    private PersonService mockPersonService;

    @Autowired
    private MockMvc mockMvc;

    private Person inputPerson;
    private Person mockResponsePerson01;
    private Person mockResponsePerson02;


    @BeforeEach
    public void setUp(){
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("(302)123-4567", true));
        phoneNumbers.add(new PhoneNumber("(610)123-9876", false));

        inputPerson = new Person("Irlanda", "Manning", phoneNumbers);


        mockResponsePerson01 = new Person("Irlanda", "Manning", phoneNumbers);
        mockResponsePerson01.setId(1);

        mockResponsePerson02 = new Person("Irlanda", "Manning", phoneNumbers);
        mockResponsePerson02.setId(2);
    }

//    @Test
//    @DisplayName("Person Post: /person - Success")
//    public void createPersonRequestSuccess(){
//        BDDMockito.doReturn(mockResponsePerson01).when(mockPersonService).create(any());
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/person")).
//    }

}
