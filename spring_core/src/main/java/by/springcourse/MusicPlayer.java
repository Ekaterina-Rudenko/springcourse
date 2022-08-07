package by.springcourse;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {
  private Music music1;
  private Music music2;
  @Value("${musicPlayer.name}")
  private String name;
  @Value("${musicPlayer.volume}")
  private int volume;

  @Autowired
  public MusicPlayer(@Qualifier("rockMusic") Music music1, @Qualifier("classicalMusic") Music music2) {
    this.music1 = music1;
    this.music2 = music2;
  }

  public String getName() {
    return name;
  }

  public int getVolume() {
    return volume;
  }

  public void playMusic(MusicGenre genre) {
    Random random = new Random();
    int randomNumber = random.nextInt(3);
    if(genre == MusicGenre.ROCK){
      System.out.println(music1.getSongs().get(randomNumber));
    } else {
      System.out.println(music2.getSongs().get(randomNumber));
    }
  }
}
