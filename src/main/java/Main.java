import ru.javamentor.exception.DBException;
import ru.javamentor.model.User;
import ru.javamentor.service.Service;
import ru.javamentor.service.UserService;

public class Main {
    public static void main(String[] args) throws DBException {
        System.out.println("START MAIN!!!!!!!!!!!!!!!!!!!!!!!");

        Service service = new UserService();
//        service.cleanUp();
//        service.createTable();

        service.addUser("admin", 31, "1", "admin");
        service.addUser("nick", 18, "1", "user");
        service.addUser("dick", 28, "1", "user");
        service.addUser("quick", 68, "1", "user");
        service.addUser("vick", 38, "1", "user");
        service.addUser("brick", 81, "1", "user");
        service.addUser("kick", 9, "1", "user");
        service.addUser("pick", 999, "1", "user");
        service.addUser("frick", 77, "1", "user");

        service.getAllUsers().forEach(System.out::println);

        User mick = service.getUserByName("mick");

        System.out.println(mick + "\n");
    }
}
