package by.rudenko.FirstRestApp.controllers;

import by.rudenko.FirstRestApp.models.Person;
import by.rudenko.FirstRestApp.services.PeopleService;
import by.rudenko.FirstRestApp.util.PersonErrorResponse;
import by.rudenko.FirstRestApp.util.PersonNotCreatedException;
import by.rudenko.FirstRestApp.util.PersonNotFoundException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping
  public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person,
  BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      StringBuilder errorMessage = new StringBuilder();
      List<FieldError> errors = bindingResult.getFieldErrors();
      for(FieldError error: errors){
        errorMessage.append(error.getField()).append(" - ")
            .append(error.getDefaultMessage()).append("; ");
      }
      throw  new PersonNotCreatedException(errorMessage.toString());
    }
    peopleService.save(person);

    //http resp with empty body and status 200
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @ExceptionHandler
  private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
    PersonErrorResponse response = new PersonErrorResponse(
        "Person with this id wasn't found",
        System.currentTimeMillis()
    );
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler
  private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e){
    PersonErrorResponse response = new PersonErrorResponse(
        e.getMessage(),
        System.currentTimeMillis()
    );
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

}
