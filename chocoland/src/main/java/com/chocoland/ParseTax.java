package com.chocoland;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.*;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;  

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import org.json.simple.JSONObject;

public class ParseTax implements JDBCCredentials {

	// Attributes
	private Connection conn;
	private ResultSet rs;
    public static String pathToTaxesCSV = "csvFiles/tax_rates.csv";
    public static String pathToZipCSV = "csvFiles/zip_codes.csv";

	public ParseTax()
	{
		try {
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Problem connecting to the database");
			e.printStackTrace();
		}
	}

	public void createTaxesTable() {
		String sql = "CREATE TABLE Taxes " + "(zip INTEGER not NULL, " + " state VARCHAR(100), "
				+ " city VARCHAR(100), " + " rate DOUBLE, " +  "PRIMARY KEY ( zip ))";

		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Table cannot be created!");
		}
		System.out.println("Created table in given database...");
	}


	public void insertTaxes() {

        int zip;
        String state;
        String city;

        // taxes_csv
        try {
            String line = "";
            int count = 0; 
            String query = "INSERT INTO Taxes (zip, state, city) values(?,?,?)";
            // change the path to  relative....
            BufferedReader br = new BufferedReader(new FileReader(pathToZipCSV));  
            while ((line = br.readLine()) != null)
            {  

                count+=1;
                if(count==1) continue;
                String[] c = line.split(",");  
                // one line
                zip = Integer.parseInt(c[0].trim().replace("\"", ""));
                state = c[1].trim().replace("\"", "");
                city = c[2].trim().replace("\"", "");
                try 
                {
                PreparedStatement pStat = conn.prepareStatement(query);
                pStat.setInt(1, zip);
				pStat.setString(2, state);
				pStat.setString(3, city);
                int rowCount = pStat.executeUpdate();
				pStat.close();
                }catch (SQLException e) {
                    System.out.println("Problem inserting TAXES");
                    e.printStackTrace();
                }
            }  
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        System.out.println("Done inserting taxes to the table");
	}

    public void insertRates()
    {
        try {
            String line = "";
            int count = 0; 
            // change the path to  relative....
            BufferedReader br = new BufferedReader(new FileReader(pathToTaxesCSV));  
            while ((line = br.readLine()) != null)
            {  

                count+=1;
                if(count==1) continue;
                String[] c = line.split(",");  
                // one line
                String state = c[0].trim().replace("\"", "");
                int zip = Integer.parseInt(c[1].trim().replace("\"", ""));
                double rate = Double.parseDouble(c[3].trim().replace("\"", ""));
                try 
                {
                    String query = "UPDATE Taxes SET rate = ? WHERE zip = ? AND state = ?";
                    PreparedStatement pStat = conn.prepareStatement(query);
                    pStat.setDouble(1, rate);
                    pStat.setInt(2, zip);
                    pStat.setString(3, state);
                    int rowCount = pStat.executeUpdate();
                    pStat.close();

                }catch (SQLException e) {
                    System.out.println("Problem inserting RATE");
                    e.printStackTrace();
                }
            }  
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    public JSONObject retrieveTaxData(int zip)
    {
        Statement stmt = null;
        JSONObject json = new JSONObject();
        String city = "";
        String state = "";
        double rate = 0;
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT city, state, rate FROM Taxes WHERE zip = " + zip;

            ResultSet rs = stmt.executeQuery(sql);
                //Retrieve by column name

            while(rs.next()){
                    city = rs.getString("city");
                    state = rs.getString("state");
                    rate = rs.getDouble("rate");
            }
            json.put("city", city);
            json.put("state", state);
            json.put("rate", rate);
            System.out.println("THIS IS JSON IN PARSETAX" + json);
            rs.close(); 
            stmt.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                System.out.println(se.getMessage());
                return json;
            }catch(Exception e){
                //Handle any other type of error
                System.out.println(e.getMessage());
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                    stmt.close();
                }catch(SQLException ignore) {}// nothing we can do
            
        }

        return json;

    }

    public List<String> retrieveStates()
    {
        ArrayList<String> states = new ArrayList();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT DISTINCT state FROM Taxes";

            ResultSet rs = stmt.executeQuery(sql);
                //Retrieve by column name

            while(rs.next()){
                    String state = rs.getString("state");
                    states.add(state);
                    
            }
            rs.close(); 
            stmt.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                System.out.println(se.getMessage());
            }catch(Exception e){
                //Handle any other type of error
                System.out.println(e.getMessage());
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                    stmt.close();
                }catch(SQLException ignore) {}// nothing we can do
            
        }
        System.out.println("States Array: " + states);

        String regex = "A"; 

            for (String e: states)
            {
                if(e.matches(regex)){
                    System.out.println(e);
                    System.out.println(e + "matches?" + e.matches(regex));
                }
            }

        return states;

    }



	public void close() {
		try {
			// rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	


	public static void main(String[] args) {
		ParseTax myApp = new ParseTax();
		myApp.createTaxesTable();
        myApp.insertTaxes();
        myApp.insertRates();
		myApp.close();
	}

}
