package by.springcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "applicationContext.xml"
    );

  /*  Music music1 = context.getBean("rockMusic", Music.class);
    Music music2 = context.getBean("classicalMusic", Music.class);

    MusicPlayer musicPlayer1 = new MusicPlayer(music1);
    MusicPlayer musicPlayer2 = new MusicPlayer(music2);

    musicPlayer1.playMusic();
    musicPlayer2.playMusic();*/

    /*MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
    musicPlayer.playMusic();*/

    /*Computer computer = context.getBean("computer", Computer.class);
    System.out.println(computer);*/

    MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
    musicPlayer.playMusic(MusicGenre.CLASSICAL);
    musicPlayer.playMusic(MusicGenre.ROCK);

    System.out.println(musicPlayer.getName() + ", volume " + musicPlayer.getVolume());

    ClassicalMusic classicalMusic1 = context.getBean("classicalMusic", ClassicalMusic.class);
    ClassicalMusic classicalMusic2 = context.getBean("classicalMusic", ClassicalMusic.class);
    System.out.println(classicalMusic1 == classicalMusic2);

    context.close();
  }
}
