package hiber.model;

import javax.persistence.*;
import java.util.Objects;

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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Car car = (Car) o;
      return getSeries() == car.getSeries() && Objects.equals(id, car.id) && Objects.equals(getModel(), car.getModel()) && Objects.equals(getUser(), car.getUser());
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, getModel(), getSeries(), getUser());
   }
}
