package com.Romawertq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import org.mindrot.jbcrypt.BCrypt;

public class db {

    private static final String URL = "jdbc:postgresql://10.0.0.200:5432/postgres";
    private static final String USER = "tyr";
    private static final String PASSWORD = "postgresEQW1";

    public static boolean register_user(String name, String email, String password, String admin){
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Подключение к PostgreSQL выполнено.");

            if (Objects.equals(admin, "ddd")){
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
                System.out.println("Хеш: " + hashed);

                String insertSQL = "INSERT INTO fer (name, email, password, isadmin) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertSQL);

                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, hashed);
                pstmt.setBoolean(4, false);
                pstmt.executeUpdate();
                return false;
            } else if (Objects.equals(admin, "Дебоширики")) {
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
                System.out.println("Хеш: " + hashed);

                String insertSQL = "INSERT INTO fer (name, email, password, isadmin) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(insertSQL);

                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, hashed);
                pstmt.setBoolean(4, true);
                pstmt.executeUpdate();
                return true;
            }
            else{
                return false;
            }


        } catch (SQLException e) {
            System.err.println("Ошибка при работе с PostgreSQL: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
