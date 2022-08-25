package by.rudenko.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Movie")
public class Movie {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name="year_of_production")
  private int yearOfProduction;

  @ManyToMany(mappedBy = "movies")
  private List<Actor> actors;

  public Movie(String name, int yearOfProduction) {
    this.name = name;
    this.yearOfProduction = yearOfProduction;
  }

  public Movie() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getYearOfProduction() {
    return yearOfProduction;
  }

  public void setYearOfProduction(int yearOfProduction) {
    this.yearOfProduction = yearOfProduction;
  }

  public List<Actor> getActors() {
    return actors;
  }

  public void setActors(List<Actor> actors) {
    this.actors = actors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Movie)) {
      return false;
    }

    Movie movie = (Movie) o;

    if (getId() != movie.getId()) {
      return false;
    }
    if (getYearOfProduction() != movie.getYearOfProduction()) {
      return false;
    }
    return getName() != null ? getName().equals(movie.getName()) : movie.getName() == null;
  }

  @Override
  public int hashCode() {
    int result = getId();
    result = 31 * result + (getName() != null ? getName().hashCode() : 0);
    result = 31 * result + getYearOfProduction();
    return result;
  }

  @Override
  public String toString() {
    return "Movie{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", yearOfProduction=" + yearOfProduction +
        '}';
  }
}
