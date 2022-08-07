package by.springcourse;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;

public class MusicPlayer {
  private List<Music> musicList;
  @Value("${musicPlayer.name}")
  private String name;
  @Value("${musicPlayer.volume}")
  private int volume;

  public MusicPlayer(List<Music> musicList) {
    this.musicList = musicList;
  }

  public String getName() {
    return name;
  }

  public int getVolume() {
    return volume;
  }

  public String playMusic() {
    Random random = new Random();
    int randomNumber = random.nextInt(3);
   return "Playing: " + musicList.get(random.nextInt(musicList.size())).getSongs()
       + " with volume: " + getVolume();

  }
}
