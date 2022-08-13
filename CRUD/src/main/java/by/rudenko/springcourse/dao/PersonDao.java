package by.rudenko.springcourse.dao;

import by.rudenko.springcourse.models.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PersonDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PersonDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Person> index() {
    String SQL = "SELECT * FROM person";
    return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Person.class));
  }

  public Person show(int id) {
    String SQL = "SELECT * FROM person WHERE id=?";
    return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
        .stream().findAny().orElse(null);
  }

  public void save(Person person) {
    String SQL = "INSERT INTO person VALUES (1, ?, ?, ?)";
    jdbcTemplate.update(SQL, person.getName(), person.getAge(), person.getEmail());
  }

  public void update(int id, Person updatedPerson) {
    String SQL = "UPDATE person SET name =?, age=?, email=? WHERE id=?";
    jdbcTemplate.update(SQL, updatedPerson.getName(), updatedPerson.getAge(),
        updatedPerson.getEmail(), id);
  }

  public void delete(int id) {
    String SQL = "DELETE FROM person WHERE id=?";
    jdbcTemplate.update(SQL, id);
  }
}
