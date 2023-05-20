package com.database.prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class UpdateTest {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/db_java";
            String user = "postgres";
            String pwd = "123456";
            Connection connection = DriverManager.getConnection(url, user, pwd);
            System.out.println("Veritabanı Adı : " + connection.getMetaData().getDatabaseProductName());
            String sql = "UPDATE Product set productName=?, salesPrice=? WHERE productId=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "Pilot Kalem");
            statement.setDouble(2, 36.44);
            statement.setLong(3, 5);
            int affected = statement.executeUpdate();
            System.out.println("Etkilenen Satır : " + affected);

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
