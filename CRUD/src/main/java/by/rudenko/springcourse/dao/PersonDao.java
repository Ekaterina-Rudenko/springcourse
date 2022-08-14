package by.rudenko.springcourse.dao;

import by.rudenko.springcourse.models.Person;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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

  public Optional<Person> show(String email) {
    return jdbcTemplate.query("Select * FROM person WHERE email=?", new Object[]{email},
        new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
  }
    public Person show(int id) {
    String SQL = "SELECT * FROM person WHERE id=?";
    return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
        .stream().findAny().orElse(null);
  }

  public void save(Person person) {
    String SQL = "INSERT INTO person (name, age, email, address) VALUES ( ?, ?, ?, ?)";
    jdbcTemplate.update(SQL, person.getName(), person.getAge(), person.getEmail(), person.getAddress());
  }

  public void update(int id, Person updatedPerson) {
    String SQL = "UPDATE person SET name =?, age=?, email=?, address =? WHERE id=?";
    jdbcTemplate.update(SQL, updatedPerson.getName(), updatedPerson.getAge(),
        updatedPerson.getEmail(), updatedPerson.getAddress(), id);
  }

  public void delete(int id) {
    String SQL = "DELETE FROM person WHERE id=?";
    jdbcTemplate.update(SQL, id);
  }

  /////////////////////////////////
  /////////////Тестируем производительность пакетной вставки
  /////////////////////////////

  public void testMultipleUpdate() {
    List<Person> people = create1000People();
    long before = System.currentTimeMillis();
    for (Person person : people) {
      jdbcTemplate.update("INSERT INTO person VALUES (?, ?, ?, ?)", person.getId(),
          person.getName(), person.getAge(), person.getEmail());
    }
    long after = System.currentTimeMillis();
    System.out.println("Time:" + (after - before));
  }

  public void testBatchUpdate() {
    List<Person> people = create1000People();
    long before = System.currentTimeMillis();
    jdbcTemplate.batchUpdate("INSERT INTO person VALUES (?, ?, ?, ?)",
        new BatchPreparedStatementSetter() {
          @Override
          public void setValues(PreparedStatement ps, int i) throws SQLException {
            ps.setInt(1, people.get(i).getId());
            ps.setString(2, people.get(i).getName());
            ps.setInt(3, people.get(i).getAge());
            ps.setString(4, people.get(i).getEmail());
          }

          @Override
          public int getBatchSize() {
            return 1000;
          }
        });
    long after = System.currentTimeMillis();
    System.out.println("Time batch update:" + (after - before));
  }

  private List<Person> create1000People() {
    List<Person> people = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      people.add(new Person(i, "Name" + i, 30, "test" + i + "@gm.com", "some address"));
    }
    return people;
  }
}
