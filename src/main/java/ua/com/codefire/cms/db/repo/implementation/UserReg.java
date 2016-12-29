package ua.com.codefire.cms.db.repo.implementation;

import ua.com.codefire.cms.db.entity.UserEntity;
import ua.com.codefire.cms.db.repo.UserEntityRepository;
import ua.com.codefire.cms.db.repo.abstraction.IUserReg;
import ua.com.codefire.cms.db.service.UserService;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by USER on 18.12.2016.
 * @deprecated  As of release 1.3, replaced by {@link UserEntityRepository}
 * Not required, because these methods exist in UserEntityRepo, linked higher. They are used in the latest
 * UserService {@link UserService}
 */
public class UserReg implements IUserReg{

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/contactmanager";
    private static final String USER = "USER";
    private static final String PASSWORD = "12345";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IUserReg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void add(UserEntity user) {
        try (Connection connect = getConnection()) {
            Statement statement = connect.createStatement();

            ResultSet result = statement.executeQuery("SELECT count(password) FROM contactmanager.contactinformation where password = " + user.getPassword());
            if (result.next()) {
                System.out.println("You are already registered");
            } else {
                String query = String.format("INSERT INTO contactmanager.contactinformation (name, password, email,id) VALUES ('%s', '%s' ,%s')",
                        user.getUsername(), user.getPassword(), user.getEmail());
                statement.executeUpdate(query);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserReg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isExist(UserEntity user) {
        try (Connection connect = getConnection()) {
            Statement statement = connect.createStatement();

            ResultSet result = statement.executeQuery("SELECT count(password) FROM contactmanager.contactinformation where password = " + user.getPassword());

            return result.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserReg.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

