package com.example.chatapplication.model;

import com.example.chatapplication.db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clientModel {
    public static String generateNextUserId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1";

        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        if (resultSet.next()){
            return splitUserId(resultSet.getString(1));
        }
        return splitUserId(null);
    }

    private static String splitUserId(String currentUserId) {
        if (currentUserId !=null){
            String[] strings = currentUserId.split("U");
            int id = Integer.parseInt(strings[1]);
            id++;
            String generatd = String.format("U%03d",id);
            return generatd;
        }
        return "U001";
    }

    public static List<String> getUsername() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT user_name FROM user";

        List<String> names = new ArrayList<>();

        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()){
            names.add(resultSet.getString(1));
        }
        return names;
    }
}
