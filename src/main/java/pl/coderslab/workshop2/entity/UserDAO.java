package pl.coderslab.workshop2.entity;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.workshop2.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDAO {
    private static final String CREATE_USER_QUERY = "INSERT INTO users (email, username, password) VALUES ( ?, ?, ?)";
    private static final String SELECT_USER = "SELECT * FROM users where id = ?";

    private static final String SELECT_USERS = "SELECT * FROM users";
    private static final String UPDATE_USER = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_USER);
            statement.setString(1, String.valueOf(userId));
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    User user = new User(rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("username"),
                            rs.getString("password"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(User user) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User[] increaseUsersArray(User user, User[] users) {
        users = Arrays.copyOf(users, users.length + 1);
        users[users.length - 1] = user;
        return users;
    }

    public User[] readAllUsers() {
        User[] users = new User[0];
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_USERS);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = new User(rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("username"),
                            rs.getString("password"));
                            users = increaseUsersArray(user, users);
                }
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
