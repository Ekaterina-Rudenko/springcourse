package by.rudenko.FirstRestApp.controllers;

import by.rudenko.FirstRestApp.models.Person;
import by.rudenko.FirstRestApp.services.PeopleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleController {

  private final PeopleService peopleService;

  @Autowired
  public PeopleController(PeopleService peopleService) {
    this.peopleService = peopleService;
  }

  @GetMapping()
  public List<Person> getPeople(){
    return peopleService.findAll();//Jackson converts to json
  }

  @GetMapping("/{id}")
  public Person getPerson(@PathVariable("id")int id){
    return peopleService.findOne(id);
  }

}
