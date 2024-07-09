package org.example.model;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.controller.CustomerController;
import org.example.db.DbConnection;
import org.example.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {


    public boolean saveuser(CustomerDto dto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES (?, ?, ?, ?, ?) ";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getNic());
        pstm.setDate(3, java.sql.Date.valueOf(dto.getSdate()));
        pstm.setDate(4, java.sql.Date.valueOf(dto.getEdate()));
        pstm.setString(5, dto.getRoomId());

        boolean IsSaved = pstm.executeUpdate() > 0;
        System.out.println(IsSaved);
        return IsSaved;

    }
    public boolean isRoomAvailable(String roomId, LocalDate startDate, LocalDate endDate) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT COUNT(*) FROM customer WHERE roomid = ? AND (SDAY <= ? AND EDAY >= ?)";
        PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, roomId);
            pstm.setDate(2, java.sql.Date.valueOf(endDate));
            pstm.setDate(3, java.sql.Date.valueOf(startDate));

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count == 0;
                }
            }

        return false;
    }

 /*   public boolean Datecheck(CustomerDto dto) throws SQLException {

        if (dto.getSdate() != null && dto.getEdate() != null) {
            List<LocalDate> datesInRange = getDatesInRange(dto.getSdate(), dto.getEdate());

            datesInRange.forEach(date -> System.out.println(date));


        } else {
            System.out.println("Please select both start and end dates.");
        }


    }*/


 /*   public static List<LocalDate> getDatesInRange(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return dates;


    }*/


    /*   public boolean check2(CustomerDto dto) throws SQLException {
           Connection conection = DbConnection.getInstance().getConnection();
           String sql = "SELECT * FROM customer WHERE SDATE =? OR EDATE =?";

           PreparedStatement pstm = connection.prepareStatement(sql);
           //pstm.setString(1, getDatesInRange(dto.getSdate(), dto.getEdate()).toString());
        //   pstm.setString(2,getDatesInRange(dto.getSdate(), dto.getEdate()).toString());
           pstm.setDate(1,dto.getSdate());
           pstm.setString(2,dto.getEdate().toString());
           ResultSet resultSet = pstm.executeQuery();
           if (resultSet.next()) {
               return true;
           } else return false;

       } */
  /*  public boolean checkDateRangeAvailability(CustomerDto custdtt) throws Exception {
        // DateTimeFormatter to format LocalDate to String
        LocalDate startDate = custdtt.getSdate();
        LocalDate endDate = custdtt.getEdate();

        // SQL query to check date range


        String query = "SELECT * FROM customer WHERE (SDATE BETWEEN ? AND ?) OR (EDATE BETWEEN ? AND ?) OR (? BETWEEN SDATE AND EDATE) OR (? BETWEEN SDATE AND EDATE)";

        try (Connection connection = DbConnection.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, java.sql.Date.valueOf(startDate));
            preparedStatement.setDate(2, java.sql.Date.valueOf(endDate));
            preparedStatement.setDate(3, java.sql.Date.valueOf(startDate));
            preparedStatement.setDate(4, java.sql.Date.valueOf(endDate));
            preparedStatement.setDate(5, java.sql.Date.valueOf(startDate));
            preparedStatement.setDate(6, java.sql.Date.valueOf(endDate));

            ResultSet resultSet = preparedStatement.executeQuery();

            // If there is a record in the result set, the date range is not available
            if (resultSet.next()) {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Database query failed", ex);
        }
        // If no record found, the date range is available
        return true;


    }*/
    public ObservableList loadDataFromDatabase() {
        ObservableList<CustomerDto> data = FXCollections.observableArrayList();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String query = "SELECT * FROM customer";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString(1);
                String nic = resultSet.getString(2);
                String sdate = String.valueOf(resultSet.getDate(3));
                String edate = String.valueOf(resultSet.getDate(4));
                String roomId = String.valueOf(resultSet.getDate(4));
                data.add(new CustomerDto(name, nic, sdate, edate,roomId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }


        return  data;
    }

}
