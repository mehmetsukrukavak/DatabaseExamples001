package com.database.prepared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FindTest {
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/db_java";
            String user = "postgres";
            String pwd = "123456";
            Connection connection = DriverManager.getConnection(url, user, pwd);
            System.out.println("Veritabanı Adı : " + connection.getMetaData().getDatabaseProductName());

            String sql = "select * from Product where productId = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, 5);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                long productId = resultSet.getLong("productId");
                String productName = resultSet.getString("productName");
                Double salesPrice = resultSet.getDouble("salesPRice");

                System.out.printf("ProdoctId : %d \r\nProduct Name : %-25s \r\nProduct Price : %5.2f \r\n", productId, productName, salesPrice);
            }else{
                System.out.println("Product not found.");
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
