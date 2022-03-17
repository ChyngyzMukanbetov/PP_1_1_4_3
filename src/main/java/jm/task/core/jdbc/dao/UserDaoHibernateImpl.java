package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT(8) not NULL AUTO_INCREMENT, " +
                " name TEXT NOT NULL, " +
                " lastName TEXT NOT NULL, " +
                " age TINYINT(1) NOT NULL, " +
                " PRIMARY KEY (id))";

        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                SQLQuery query = session.createSQLQuery(sql);
                query.executeUpdate();
                session.getTransaction().commit();
                System.out.println("Table successfully created...");
            } catch ( Exception e ) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
    }


    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users ";
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.createSQLQuery(sql).executeUpdate();
                session.getTransaction().commit();
                System.out.println("Table successfully dropped...");
            } catch ( Exception e ) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            try {
                User user = new User(name, lastName, age);
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
                System.out.println("User с именем – " + name + " добавлен в базу данных");
            } catch ( Exception e ) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                User user = session.get(User.class, id);
                session.delete(user);
                session.getTransaction().commit();
                System.out.println("User с id – " + id + " удален из базы данных");
            } catch ( Exception e ) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                users = session.createQuery("SELECT user FROM User user", User.class).list();
                session.getTransaction().commit();
                System.out.println(users.toString());
            } catch ( Exception e ) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.createSQLQuery("delete from users").executeUpdate();
                session.getTransaction().commit();
                System.out.println("Table successfully cleaned...");
            } catch ( Exception e ) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeConnection() {
        sessionFactory.close();
    }
}
