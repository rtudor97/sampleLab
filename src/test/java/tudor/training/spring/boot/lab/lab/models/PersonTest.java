package tudor.training.spring.boot.lab.lab.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class PersonTest {

    private Person person01;
    private Person person02;

    private Person emptyPerson01;
    private Person emptyPerson02;

    @BeforeEach
    public void setup(){
        emptyPerson01 = new Person();
        emptyPerson02 = new Person();

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber("302-555-1055",true));
        phoneNumbers.add(new PhoneNumber("302-456-7890",false));

        person01 = new Person("Ryan","Tudor", phoneNumbers);
        person01.setId(1);
        person02 = new Person("Ryan","Tudor",phoneNumbers);
        person02.setId(2);
    }

    @Test
    public void testEmptyEquals(){
        assertTrue(emptyPerson01.equals(emptyPerson02),"Both empty persons should be equal");
    }

    @Test
    public void testContentsEquals(){
        assertTrue(person01.equals(person02),"Both non-empty persons should be equal");
    }

    @Test
    public void testNotEquals(){
        assertFalse(person01.equals(emptyPerson01),"These should not be equal");
    }

    @Test
    public void testEmptyHashCode(){
        assertEquals(emptyPerson01.hashCode(),
                    emptyPerson02.hashCode(),
                    "Both empty persons should have the same hashCode");
    }

    @Test
    public void testContentHashCode(){
        assertEquals(
                person01.hashCode(),
                person02.hashCode(),
                "Both non-empty persons should have the same hash code");
    }

    @Test
    public void testHashCode(){
        assertNotEquals(
                emptyPerson01.hashCode(),
                person02.hashCode(),
                "The Person instances should not have the same hashCode");
    }

    @Test
    public void testEmptyToString() throws Exception {
        assertEquals(
                emptyPerson01.toString(),
                emptyPerson02.toString(),
                "Both empty Person instances should have the same toString");
    }
    @Test
    public void testContentToString() throws Exception {
        assertEquals(
                person01.toString(),
                person02.toString(),
                "Both non-empty Widget instances should have the same toString");
    }

    @Test
    public void testNotToString() throws Exception {
        assertNotEquals(
                emptyPerson01.toString(),
                person02.toString(),
                "The Widget instances should not have the same toString");
    }
}
