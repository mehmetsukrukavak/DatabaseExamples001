package com.database.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class TransactionTest {
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/db_java";
            String user = "postgres";
            String pwd = "123456";
            Connection connection = DriverManager.getConnection(url, user, pwd);
            connection.setAutoCommit(false);
            System.out.println("Veritabanı Adı : " + connection.getMetaData().getDatabaseProductName());
            String sql = "INSERT INTO Product (productName, salesPrice) VALUES(?, ?)";

            PreparedStatement statement1 = connection.prepareStatement(sql);
            statement1.setString(1, "ÜDS Hazırlık ");
            statement1.setDouble(2, 36.99);
            int affected1 = statement1.executeUpdate();
            System.out.println("Etkilenen Satır : " + affected1);

            PreparedStatement statement2 = connection.prepareStatement(sql);
            statement2.setString(1, "ÖSS Hazırlık ");
            statement2.setDouble(2, 38.99);
            int affected2 = statement2.executeUpdate();
            System.out.println("Etkilenen Satır : " + affected2);

            connection.rollback();

            PreparedStatement statement3 = connection.prepareStatement(sql);
            statement3.setString(1, "ÖYS Hazırlık ");
            statement3.setDouble(2, 41.99);
            int affected3 = statement3.executeUpdate();
            System.out.println("Etkilenen Satır : " + affected3);

            connection.commit();
            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


