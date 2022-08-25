package by.rudenko;

import by.rudenko.model.Item;
import by.rudenko.model.Person;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchTypeApp {

  public static void main(String[] args) {
    Configuration configuration = new Configuration()
        .addAnnotatedClass(Person.class)
        .addAnnotatedClass(Item.class);

    SessionFactory sessionFactory = configuration.buildSessionFactory();
    try (sessionFactory) {
      Session session = sessionFactory.getCurrentSession();
      session.beginTransaction();

 /*     Item item = session.get(Item.class, 1);
      System.out.println(item);
      System.out.println(item.getOwner());*/

      Person person = session.get(Person.class, 1);
      System.out.println(person);
      //System.out.println(person.getItems());
      //Hibernate.initialize(person.getItems());

      session.getTransaction().commit();
      System.out.println("Session closed");

      //можно сделать в любом другом месте в коде
      session = sessionFactory.getCurrentSession();
      session.beginTransaction();
      System.out.println("Inside other transaction");
      person = (Person) session.merge(person);
      Hibernate.initialize(person.getItems());
      session.getTransaction().commit();
      System.out.println("Out of second session");
      System.out.println(person.getItems());

      //2 способ
      session = sessionFactory.getCurrentSession();
      session.beginTransaction();
      List<Item> items = session.createQuery("select i from Item i where i.owner.id = :personId",
              Item.class)
          .setParameter("personId", person.getId()).getResultList();
      System.out.println(items);
      session.getTransaction().commit();

      //System.out.println(person.getItems());//работает только для eager вне сессии(для lazy - толкьо если был вызван гетер для сущности)
      //!!!если при lazy вызвать getter, но не использовать, то также будет ошибка, из-за оптимизации компилятором
      //Нужен Hibernate initialize
    }
  }
}
