package org.example.model;
import javax.swing.*;
import org.example.db.DbConnection;
import org.example.dto.UserDto;
import org.example.controller.SignupController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpModel {
    public boolean saveuser(UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO signup VALUES (?, ?, ?, ?) ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getUserName());
        pstm.setString(2, dto.getEmail());
        pstm.setString(3, dto.getContact());
        pstm.setString(4, dto.getPassowrd());

        boolean IsSaved = pstm.executeUpdate() > 0;
        return IsSaved;

    }
}
