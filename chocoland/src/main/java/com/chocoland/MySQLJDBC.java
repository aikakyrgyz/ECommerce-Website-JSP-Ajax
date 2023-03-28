package com.chocoland;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.*;
import java.util.List;

import java.text.SimpleDateFormat;  
import java.util.Date;  

public class MySQLJDBC implements JDBCCredentials {

	// Attributes
	private Connection conn;
	private ResultSet rs;


	public MySQLJDBC()
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

	public void createTable() {
		String sql = "CREATE TABLE Products " + "(id INTEGER not NULL, " + " name VARCHAR(50), "
				+ " category VARCHAR(50), " + " description VARCHAR(500), " + 
                 " cost DOUBLE, " + " image VARCHAR(100), " + "brand VARCHAR(50)," + "PRIMARY KEY ( id ))";

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


	public void insertProducts() {
		try {
			String query = "INSERT INTO Products (id, name, category, description, cost, image, brand) values(?,?,?,?,?,?,?)";
			List<Product> products = generateProducts.generate();
			for(Product p: products){
				PreparedStatement pStat = conn.prepareStatement(query);
				pStat.setInt(1, p.id);
				pStat.setString(2, p.name);
				pStat.setString(3, p.category);
				pStat.setString(4, p.description);
				pStat.setDouble(5, p.cost);
				pStat.setString(6, p.image);
				pStat.setString(7, p.brand);
				int rowCount = pStat.executeUpdate();
				// System.out.println("row Count = " + rowCount);
				pStat.close();
			}

		} catch (SQLException e) {
			System.out.println("Problem inserting products");
			e.printStackTrace();
		}
	}

	public void createOrderDetailsTable()
	{
		String sql = "CREATE TABLE OrderDetails " + "(orderID INTEGER not NULL," + "productID INTEGER NOT NULL, "+ "quantity int)";
		
		// String sql2 = "ALTER TABLE Orders ADD CONSTRAINT id FOREIGN KEY REFERENCES OrderDetails(orderID)";
		
		// String sql3 = "ALTER TABLE OrderDetails ADD CONSTRAINT orderID FOREIGN KEY REFERENCES Orders(id)";

		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			// stmt.executeUpdate(sql2);
			// stmt.executeUpdate(sql3);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Table cannot be created!");
		}
	}

	public void createOrdersTable()
	{
		String sql = "CREATE TABLE Orders " + "(id INTEGER not NULL PRIMARY KEY, " 
		+ " orderDate VARCHAR(100),"
		+ " firstName VARCHAR(50), "
		+ " lastName VARCHAR(50), "  
		+ " phoneNumber VARCHAR(50), " 
		+ " email VARCHAR(50), " 
		+ " address VARCHAR(100), "
		+ " country VARCHAR(50),"
		+ " state VARCHAR(50),"
		+ " city VARCHAR(50),"
		+ " zip VARCHAR(10),"
		+ " ccName VARCHAR(50),"
		+ " ccNumber VARCHAR(50),"
		+ " ccExpiration VARCHAR(50),"
		+ " ccCVV VARCHAR(5),"
		+ " totalQuantity int,"
		+ " taxes VARCHAR(20), "
		+ " totalPaid VARCHAR(20),"
		+ " rating int)";

			try {
				Statement stmt = conn.createStatement(); // construct a statement
				stmt.executeUpdate(sql); // execute my query (i.e. sql)
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Table cannot be created!");
			}
			System.out.println("Created Orders Table in given database...");
	}



	public void InsertIntoOrderDetailsTable(List<CartItem> cart, int orderID)
	{
		
		// System.out.println("Created linked foreign key of order id...");
		// "name VARCHAR(50), "
		// + " category VARCHAR(50), " + " description VARCHAR(500), " + 
		//  " cost DOUBLE, " + " image VARCHAR(100), " + "brand VARCHAR(50)," + "quantity int) "; 
		

		 // insert the order
 
		for (CartItem item: cart){
		 try {
			 String query = "INSERT INTO OrderDetails (orderID, productID, quantity) values(?,?,?)";
				 PreparedStatement pStat = conn.prepareStatement(query);
				 pStat.setInt(1, orderID);
				 pStat.setInt(2, item.getProduct().getId());
				 pStat.setInt(3, item.getQuantity());
				 int rowCount = pStat.executeUpdate();
				 // System.out.println("row Count = " + rowCount);
				 pStat.close();
		 } catch (SQLException e) {
			 System.out.println("Problem inserting order details");
			 e.printStackTrace();
		 }
		}
		 
	}


	public void insertOrder(Order order, List<CartItem> cart)
	{

		// get the date
		Date d =  new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
		String myDate = formatter.format(d);  
		System.out.println("today's date is " + myDate);

		// insert the order

		try {
			// need to insert more when the order form is finished by Orhan
			String query = "INSERT INTO Orders (id, orderDate, firstName, lastName, phoneNumber, email, address, country, state, city, "
			+ "zip, ccName, ccNumber, ccExpiration, ccCVV, totalQuantity, taxes, totalPaid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pStat = conn.prepareStatement(query);
				pStat.setInt(1, order.getID());
				pStat.setString(2, myDate);
				pStat.setString(3, order.getfirstName());
				pStat.setString(4, order.getLastName());
				pStat.setString(5, order.phoneNumber);
				pStat.setString(6, order.email);
				pStat.setString(7, order.address);
				pStat.setString(8, order.country);
				pStat.setString(9, order.state);
				pStat.setString(10, order.city);
				pStat.setString(11, order.zip);
				pStat.setString(12, order.ccName);
				pStat.setString(13, order.ccNumber);
				pStat.setString(14, order.ccExpiration);
				pStat.setString(15, order.ccCVV);
				pStat.setInt(16, order.totalQuantity);
				pStat.setString(17, order.taxes);
				pStat.setString(18, order.totalPaid);
				int rowCount = pStat.executeUpdate();
				// System.out.println("row Count = " + rowCount);
				pStat.close();
		} catch (SQLException e) {
			System.out.println("Problem inserting order");
			e.printStackTrace();
		}
		// ----- don't need anymore ------
		// insert the orderDetails
		// set the foreign key
		// set FK for OrderTable and Orders

		// create rderDetails table
		InsertIntoOrderDetailsTable(cart, order.getID());

		
}
	
	

	protected void insertRatingIntoOrdersTable(int orderID, int rating)
	{
		try {
			System.out.println("Inserting rate into Orders");
			String query = "UPDATE Orders SET rating = " + rating + " WHERE id = " + Integer.toString(orderID);
			// String query = "INSERT INTO Orders (rating) values(?) where id = " + Integer.toString(orderID);
			Statement stmt = conn.createStatement(); // construct a statement
			int rowCount = stmt.executeUpdate(query);
				// System.out.println("row Count = " + rowCount);
				System.out.println("Insert DONE rate into Orders");
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Problem inserting order rating");
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			// rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args0) {
		MySQLJDBC myApp = new MySQLJDBC();
		myApp.createTable();  //only call this function once then comment out.
		myApp.insertProducts();
		myApp.createOrdersTable();
		myApp.createOrderDetailsTable();
		myApp.close();
	}

}
