package user.services;

import user.services.dao.UserDao;
import user.services.entity.User;

public class Main {

    UserDao userDao = new UserDao();

    public static void main(String[] args) {
        Main main = new Main();
        User user = main.userDao.getById(1);
        System.out.println(user);
        System.out.println("Process completed successfully");
    }
}
