package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
       try (var session = sessionFactory.openSession()) {
           TypedQuery<User> query=session.createQuery("from User");
           return query.getResultList();
       }
   }

   @Override
   public void addCarToUser(User user, Car car) {
       try (var session = sessionFactory.openSession()) {
           Transaction transaction = session.beginTransaction();
           user.setCar(car);
           session.saveOrUpdate(user);
           transaction.commit();
       }
   }

    @Override
    public User getUserByCar(String model, int series) {
        try (var session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            TypedQuery<User> query = session.createQuery("from User where car.model = :model and car.series = :series");
            query.setParameter("model", model);
            query.setParameter("series", series);
            transaction.commit();
            return query.getResultList().stream().findFirst().orElse(null);
        }
    }
}
