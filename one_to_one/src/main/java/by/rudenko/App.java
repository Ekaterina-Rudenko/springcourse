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

      //add person AND passport
      /*Person newPerson = new Person("Test person", 45);
      Passport passport = new Passport(123454);
      newPerson.setPassport(passport);
      session.save(newPerson);*/

      //get passport number through person
/*      Person person = session.get(Person.class, 3);
      System.out.println(person.getPassport().getPassportNumber());*/

      //get person through passport
    /*  Passport passport = session.get(Passport.class, 3);
      System.out.println(passport.getPerson());*/

      //Change passport number in DB
     /* Person person = session.get(Person.class, 3);
      person.getPassport().setPassportNumber(11111);*/

      Person person = session.get(Person.class, 2);
      person.setPassport(new Passport(123));

      session.getTransaction().commit();
    } finally {
      sessionFactory.close();
    }
  }
}
