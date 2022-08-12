package by.rudenko.springcourse.dao;

import by.rudenko.springcourse.models.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PersonDao {

  private static int PEOPLE_COUNT;
  private List<Person> people;
  {
    people = new ArrayList<>();
    people.add(new Person(++PEOPLE_COUNT, "Tom"));
    people.add(new Person(++PEOPLE_COUNT, "Bob"));
    people.add(new Person(++PEOPLE_COUNT, "Mike"));
    people.add(new Person(++PEOPLE_COUNT, "Alex"));
  }
  public List<Person> index(){
    return people;
  }
  public Person show(int id){
    return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
  }
  public void save(Person person){
    person.setId(++PEOPLE_COUNT);
    people.add(person);
  }
  public void update(int id, Person person){
    Person personToBeUpdated = show(id);
    personToBeUpdated.setName(person.getName());
  }
  public void delete(int id){
    people.removeIf(p -> p.getId() == id);
  }
}
