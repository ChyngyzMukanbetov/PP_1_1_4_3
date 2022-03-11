package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
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

        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sql);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Table successfully created...");
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users ";
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Table successfully dropped...");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            session.close();
            System.out.println("User с id – " + id + " удален из базы данных");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<User> users = session.createCriteria(User.class).list();
            session.getTransaction().commit();
            session.close();
            System.out.println(users.toString());
            return users;
        }


    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("delete from users").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Table successfully cleaned...");
        }
    }
}
