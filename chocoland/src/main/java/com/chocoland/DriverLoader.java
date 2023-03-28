package com.chocoland;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverLoader {   
    public static void load() {
        try {
        
            Class.forName(JDBCCredentials.JDBC_DRIVER);
            System.out.println("DRIVER LOADED!!!!!!!!!!!!!!!!!!!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
} 
