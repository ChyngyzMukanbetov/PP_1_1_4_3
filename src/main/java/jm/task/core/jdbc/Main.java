package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Petya", "Petrov", (byte) 7);
        userServiceImpl.saveUser("Oleg", "Olegov", (byte) 10);
        userServiceImpl.saveUser("Dmitrii", "Dmitriev", (byte) 22);
        userServiceImpl.saveUser("Igor", "Igorev", (byte) 36);
        userServiceImpl.getAllUsers();
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
