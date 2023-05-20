package com.database.postgresql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/db_java";
            String user = "postgres";
            String pwd = "123456";

            Connection connection = DriverManager.getConnection(url, user, pwd);

            System.out.println("Veritabanı Adı : " + connection.getMetaData().getDatabaseProductName());

            //Writing
            String sql = "INSERT INTO Product (productName, salesPrice) VALUES('Harita Metod Defter', 35.85)";

            Statement statement = connection.createStatement();
            int affected = statement.executeUpdate(sql);
            System.out.println("Etkilenen Satır : " + affected);

            //Reading
            sql = "select * from Product";

            statement = connection.createStatement();
            ResultSet resultSet  = statement.executeQuery(sql);

            while (resultSet.next()){
                long productId = resultSet.getLong("productId");
                String productName = resultSet.getString("productName");
                Double salesPrice = resultSet.getDouble("salesPRice");

                System.out.println(" Product "+ productId + " " +productName + " " + salesPrice);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
