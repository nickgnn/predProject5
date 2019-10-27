import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");

        MusicPlayer player1 = context.getBean("musicPlayer", MusicPlayer.class);
        MusicPlayer player2 = context.getBean("musicPlayer", MusicPlayer.class);

        boolean comparison = player1 == player2;

        System.out.println(comparison);

        System.out.println(player1);
        System.out.println(player2);

        player1.setVolume(10);

        System.out.println(player1.getVolume());
        System.out.println(player2.getVolume());

        context.close();
    }
}
