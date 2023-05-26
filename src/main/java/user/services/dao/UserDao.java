package user.services.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import user.services.config.HibernateConfiguration;
import user.services.entity.User;

public class UserDao {

    private final SessionFactory sessionFactory;

    public UserDao() {
        this.sessionFactory = HibernateConfiguration.getSessionFactory();
    }

    public User getById(Integer id) {
        Session session = null;
        Transaction transaction = null;
        User user = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
//            if (session != null) {
//                session.close();
//            }
        }

        return user;
    }

    public void createUser(User user) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateUser(User user) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("update users set name=:name, surname=:surname, age=:age where id=:id");
            query.setParameter("name", user.getName());
            query.setParameter("surname", user.getLastName());
            query.setParameter("age", user.getAge());
            query.setParameter("id", user.getId());
            query.executeUpdate();

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
