package tudor.training.spring.boot.lab.lab.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "First_Name")
    private String firstName;
    @Column(name = "Last_Name")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;

    public Person() {}

    public Person(String firstName, String lastName, List<PhoneNumber> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(phoneNumbers, person.phoneNumbers);
    }

    @Override
    public int hashCode(){return Objects.hash(firstName,lastName,phoneNumbers);}

    @Override
    public String toString() {
        return "Person{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
