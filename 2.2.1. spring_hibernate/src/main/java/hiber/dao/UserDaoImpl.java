package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

   private final SessionFactory sessionFactory;


   public UserDaoImpl(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      String hql = "from User user where user.car.model = :model and user.car.series = :series";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql,User.class);
      query.setParameter("model", model).setParameter("series", series);
      return query.setMaxResults(1).getSingleResult();
   }



}
