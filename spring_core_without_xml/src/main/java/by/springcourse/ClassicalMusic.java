package by.springcourse;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ClassicalMusic implements Music {
  private List<String> songs = new ArrayList<>();
  {
    songs.add("Hungarian Rhapsody");
    songs.add("Symphony no. 5 in C Minor, op. 67");
    songs.add("Night on Bald Mountain");
  }

  @Override
  public List<String> getSongs() {
    return songs;
  }
  @PostConstruct
  public void doMyInit(){
    System.out.println("Doing initialization");
  }
  @PreDestroy
  public void doMyDestroy(){
    System.out.println("Doing destruction");
  }
}
