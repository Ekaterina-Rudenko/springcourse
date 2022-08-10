package by.rudenko.springcourse.controllers;

import by.rudenko.springcourse.dao.PersonDao;
import by.rudenko.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

  private final PersonDao personDao;

  @Autowired
  public PeopleController(PersonDao personDao) {
    this.personDao = personDao;
  }

  @GetMapping
  public String index(Model model) {
    model.addAttribute("people", personDao.index());
    return "/people/index";
  }

  @GetMapping("/{id}")
  public String shoe(@PathVariable("id") int id, Model model) {
    model.addAttribute("person", personDao.show(id));
    return "/people/show";
  }

  @GetMapping("/new")
  public String newPerson(@ModelAttribute("person") Person person /*Model model*/) {
    /*model.addAttribute("person", new Person());*/ //annot ModelAttr can be used instead
    return "/people/new";
  }

  @PostMapping()
  public String create(@ModelAttribute("person") Person person) {
    personDao.save(person);
    return "redirect:/people";
  }

}