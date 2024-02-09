package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      //todo: Session session = sessionFactory.getCurrentSession(); - это то, что мы должны получить в каждом методе и обернуть try_catch_with_resource
      //todo: на нужных методах (нужно изучить на каких) нужно использовать открытие/закрытие Transaction пока вручную (без аннотирования)
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void addCarToUser(User user, Car car) {
      user.setCar(car);
      sessionFactory.getCurrentSession().saveOrUpdate(user);
   }

   @Override
   public User getUserByCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where car.model = :model and car.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getResultList().get(0);
   }
}
