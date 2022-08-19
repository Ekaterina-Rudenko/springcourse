package by.rudenko;

import by.rudenko.model.Person;
import java.util.List;
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
      //HQL
      List<Person> personList = session.createQuery("select p FROM Person p where p.name LIKE 'K%'",
          Person.class).getResultList();
      for (Person person : personList) {
        System.out.println(person);
      }

      session.createQuery("update Person set name='Mike' where age=27").executeUpdate();

      session.createQuery("delete from Person where age=26").executeUpdate();
      session.getTransaction().commit();

    } finally {
      sessionFactory.close();
    }
  }
}
