import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");

//        Music music = context.getBean("musicBean", Music.class);

        MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);
        player.playMusic();

        System.out.println(player.getName());
        System.out.println(player.getVolume());

        context.close();
    }
}
