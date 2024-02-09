package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String model;

   private int series;

   @OneToOne
   @JoinColumn(name = "user_id", unique = true)
   private User user;

   public Car() {
   }

   public Car(User user, String model, int series) {
      this.user = user;
      this.model = model;
      this.series = series;
   }

   public Car(String model, int series) {
      this.model = model;
      this.series = series;
   }

   public String getModel() {
      return model;
   }

   public void setModel(String model) {
      this.model = model;
   }

   public int getSeries() {
      return series;
   }

   public void setSeries(int series) {
      this.series = series;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   @Override
   public String toString() {
      return "Car" +
             "id = " + id +
             ", model = '" + model + '\'' +
             ", series = " + series + '\'' +
             ", user = " + user + '\'';
   }
}
