package by.rudenko.FirstRestApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// @Controller + @ResponseBody in every method
@RequestMapping("/api")
public class FirstRestController {

  @GetMapping("/sayHello")
  public String sayHello(){
   return "Hello world";
  }
}
