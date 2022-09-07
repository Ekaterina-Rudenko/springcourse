package by.rudenko.FirstRestApp.services;

import by.rudenko.FirstRestApp.models.Person;
import by.rudenko.FirstRestApp.repositories.PeopleRepository;
import by.rudenko.FirstRestApp.util.PersonNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
  private final PeopleRepository peopleRepository;

  @Autowired
  public PeopleService(PeopleRepository peopleRepository) {
    this.peopleRepository = peopleRepository;
  }
  public List<Person> findAll() {
    return peopleRepository.findAll();
  }

  public Person findOne(int id) {
    Optional<Person> foundPerson = peopleRepository.findById(id);
    return foundPerson.orElseThrow(PersonNotFoundException::new);
  }


}
