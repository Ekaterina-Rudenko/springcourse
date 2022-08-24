package by.rudenko;

import by.rudenko.model.Passport;
import by.rudenko.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {
    Configuration configuration = new Configuration()
        .addAnnotatedClass(Person.class)
        .addAnnotatedClass(Passport.class);

    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    try {
      session.beginTransaction();

      Person newPerson = new Person("Test person", 45);
      Passport passport = new Passport(123454);
      newPerson.setPassport(passport);

      session.save(newPerson);

      session.getTransaction().commit();
    } finally {
      sessionFactory.close();
    }
  }
}
