package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Petya", "Petrov", (byte) 7);
        userService.saveUser("Oleg", "Olegov", (byte) 10);
        userService.saveUser("Dmitrii", "Dmitriev", (byte) 22);
        userService.saveUser("Igor", "Igorev", (byte) 36);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        userService.closeConnection();
    }
}
