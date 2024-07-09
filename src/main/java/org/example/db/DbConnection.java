package org.example.db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;

    private Connection connection;

    private DbConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotelnew",
                    "root",
                    null
            );


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "database error");

        }
    }
    public static DbConnection getInstance() throws SQLException {
        return (null == dbConnection)?dbConnection =new DbConnection():dbConnection;

    }
    public Connection getConnection() {
        return connection;
    }


}
