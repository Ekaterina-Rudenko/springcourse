package by.rudenko;

import by.rudenko.model.Item;
import by.rudenko.model.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        .addAnnotatedClass(Item.class);

    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    try {
      session.beginTransaction();

     /* Person person = session.get(Person.class, 3);
      System.out.println(person);
      List<Item> itemList = person.getItems();
      System.out.println(itemList);*/

   /*   Item item = session.get(Item.class, 5);
      System.out.println(item);
      Person person = item.getOwner();
      System.out.println(person);*/


      /*Person person = session.get(Person.class, 2);
      Item newItem = new Item("Item from Hiber", person);
      person.getItems().add(newItem);
      session.save(newItem);*/

      //Add person and item
    /*  Person person = new Person("Test person", 44);
      Item item = new Item("Item for test", person);
      person.setItems(new ArrayList<Item>(Collections.singletonList(item)));
      session.save(person);
      session.save(item);*/

/*      Person person = session.get(Person.class, 3);
      List<Item> items = person.getItems();
      for(Item item : items){
        session.remove(item);
      }
      person.getItems().clear();*/

      /*Person person = session.get(Person.class, 2);
      session.remove(person);
      person.getItems().forEach(i -> i.setOwner(null));*/

      //new owner
      /*Person person = session.get(Person.class, 4);
      Item item = session.get(Item.class, 1);

      item.getOwner().getItems().remove(item);
      item.setOwner(person);//only this line effects db
      person.getItems().add(item);*/

      Person person = new Person("Test cascading", 30);
      person.addItem(new Item("item1"));
      person.addItem(new Item("item2"));
      person.addItem(new Item("item3"));

      session.save(person);

      session.getTransaction().commit();

    } finally {
      sessionFactory.close();
    }
  }
}
