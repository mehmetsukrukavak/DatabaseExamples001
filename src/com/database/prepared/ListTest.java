package com.database.prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListTest {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/db_java";
            String user = "postgres";
            String pwd = "123456";
            Connection connection = DriverManager.getConnection(url, user, pwd);
            System.out.println("Veritabanı Adı : " + connection.getMetaData().getDatabaseProductName());
            String sql = "select * from Product";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long productId = resultSet.getLong("productId");
                String productName = resultSet.getString("productName");
                Double salesPrice = resultSet.getDouble("salesPRice");

                System.out.printf("%d %-25s %5.2f \r\n",productId,productName,salesPrice);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
