package by.springcourse;

import java.util.ArrayList;
import java.util.List;

public class PopMusic implements Music{
  private List<String> songs = new ArrayList<>();
  {
    songs.add("Under Pressure");
    songs.add("Summer Moved On");
    songs.add("All Good Things");
  }
  @Override
  public List<String> getSongs() {
    return songs;
  }
}
