package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional//todo: если таки удастся применить @Transactional - то @Transactional выносится как обобщение - над классом
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   @Override
   public void addCarToUser(User user, Car car) {
      userDao.addCarToUser(user, car);
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserByCar(String model, int series) {
      return userDao.getUserByCar(model, series);
   }
}