package pl.coderslab.workshop2;

import java.awt.print.Book;
import java.sql.*;
import java.util.Arrays;


public class DbUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Workshop2?useSSL=false&serverTimezone=UTC";
    private static final String DB_User = "root";
    private static final String DB_Password = "coderslab";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL,DB_User,DB_Password);
    }

}
