package by.spingcourse;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RockMusic implements Music{
  private List<String> songs = new ArrayList<>();
  {
    songs.add("Wind Cries Mary");
    songs.add("Pieces");
    songs.add("Your Last Song");
  }
  @Override
  public List<String> getSongs() {
    return songs;
  }
}
