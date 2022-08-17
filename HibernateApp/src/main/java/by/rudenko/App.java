package by.rudenko;

import by.rudenko.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {
    Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    try {
      session.beginTransaction();

      /*Person person1 = new Person("Test1", 30);
      Person person2 = new Person("Test2", 30);
      Person person3 = new Person("Test3", 30);
      session.save(person1);
      session.save(person2);
      session.save(person3);*/

     /* Person person = session.get(Person.class, 2);
      *//*person.setName("NEW NAME");*//*

      session.delete(person);*/

      Person person = new Person("Test 4", 33);
      session.save(person);

      session.getTransaction().commit();
      System.out.println(person.getId());
    } finally {
      sessionFactory.close();
    }
  }
}
