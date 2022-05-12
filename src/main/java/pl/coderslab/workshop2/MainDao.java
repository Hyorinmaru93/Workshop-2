package pl.coderslab.workshop2;

import pl.coderslab.workshop2.entity.User;
import pl.coderslab.workshop2.entity.UserDAO;

import java.util.Arrays;

public class MainDao {

    public static void create(String email, String username, String password) {
        UserDAO userDAO = new UserDAO();
        User user = new User(0, email, username, password);
        userDAO.create(user);
        user.printUser();
    }

    public static void deleteUser(int id) {
        UserDAO userDAO = new UserDAO();
        userDAO.deleteUser(id);
    }

    public static void showUser(int id) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.read(id);
        try {
            user.printUser();
        } catch (NullPointerException e) {
            System.out.println("Missing user with id: "+id);
        }

    }

    public static void showAll() {
        UserDAO userDAO = new UserDAO();
        User[] users = userDAO.readAllUsers();
        for (User user : users) {
            user.printUser();
        }
    }

    public static void changeUsername(String newUsername, int id) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.read(id);
        try {
            user.setUsername(newUsername);
            userDAO.updateUser(user);
        } catch (NullPointerException e){
            System.out.println("User does not exist - failed to change username");
        }
    }

    public static void changeEmail(String newEmail, int id) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.read(id);
        try {
            user.setEmail(newEmail);
            userDAO.updateUser(user);
        } catch (NullPointerException e){
            System.out.println("User does not exist - failed to change e-mail");
        }
    }

    public static void changePassword(String newPassword, int id) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.read(id);
        try {
            user.setPassword(newPassword);
            userDAO.updateUser(user);
        } catch (NullPointerException e){
            System.out.println("User does not exist - failed to change password");
        }
    }

}
