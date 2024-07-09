package org.example.model;

import org.example.db.DbConnection;
import org.example.dto.loginDto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginModel {

    public boolean log(loginDto ld) throws SQLException {{
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "select * from signup where USERNAME = ? and PASSWORD = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, ld.getUserName());
            pstm.setString(2, ld.getPassWord());


            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }



        }
    }

    public loginModel() throws SQLException {
    }
}
