package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      userService.addCarToUser(user1, new Car("Kia", 1));
      userService.addCarToUser(user2, new Car("Volkswagen", 2));
      userService.addCarToUser(user3, new Car("Mercedes", 3));
      userService.addCarToUser(user4, new Car("Toyota", 4));

      List<User> users = userService.listUsers();
      users.forEach(System.out::println);
      System.out.println("+++++++++++++++++++++++++");
      System.out.println(userService.getUserByCar("kia", 1));

      context.close();
   }
}
