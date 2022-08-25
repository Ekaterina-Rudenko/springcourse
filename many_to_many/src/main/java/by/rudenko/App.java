package by.rudenko;

import by.rudenko.model.Actor;
import by.rudenko.model.Movie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

  public static void main(String[] args) {
    Configuration configuration = new Configuration()
        .addAnnotatedClass(Actor.class)
        .addAnnotatedClass(Movie.class);
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    try (sessionFactory) {
      Session session = sessionFactory.getCurrentSession();
      session.getTransaction().begin();
/*
      Movie movie = new Movie("Pulp fiction", 1994);
      Actor actor1 = new Actor("Harvey Keitel", 81);
      Actor actor2 = new Actor("Samuel Jackson", 72);

      //Arrays.asList() - нельзя доб. новые эелементы, но список изменяемый,
      //List.of() - неизменяемый
      movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
      actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
      actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));

      session.save(movie);
      session.save(actor1);
      session.save(actor2);*/

    /*  Movie movie = session.get(Movie.class, 3);
      System.out.println(movie.getActors());
      Actor actor = session.get(Actor.class, 3);
      System.out.println(actor.getMovies());*/

     /* Movie movie = new Movie("Reservoir dogs", 1992);
      Actor actor = session.get(Actor.class, 3);
      movie.setActors(new ArrayList<>(Collections.singletonList(actor)));
      actor.getMovies().add(movie);
      session.save(movie);*/

      Actor actor = session.get(Actor.class, 3);
      Movie movieToRemove = actor.getMovies().get(0);
      actor.getMovies().remove(0);
      movieToRemove.getActors().remove(actor);

      session.getTransaction().commit();
    }

  }
}

