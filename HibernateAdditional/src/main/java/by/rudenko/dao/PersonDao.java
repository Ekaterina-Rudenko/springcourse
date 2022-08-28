package by.rudenko.dao;

import by.rudenko.models.Person;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonDao {


  private final EntityManager entityManager;

  @Autowired
  public PersonDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional(readOnly = true)
  public void testNPlus1(){
    Session session = entityManager.unwrap(Session.class);
    Set<Person> personList = new HashSet<Person>(session.createQuery("select p from Person p LEFT JOIN FETCH p.items")
        .getResultList());
    for(Person person : personList){
      System.out.println(person.getName() + ", " + person.getItems());
    }
  }
}
