package com.chocoland;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieStore;
import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductsServlet extends HttpServlet
{
    
    Connection conn;
    //final String DB_URL="jdbc:mysql://localhost:3306/testdb";
    // final String DB_URL="jdbc:mysql://localhost:3306/testdb?useSSL=false";
    String DB_URL = JDBCCredentials.DB_URL + "?useSSL=false";
    public void init (ServletConfig config) throws ServletException {
        
        super.init(config);

        try {
              // Register JDBC driver
              Class.forName(JDBCCredentials.JDBC_DRIVER);
              // Open a connection
              conn = DriverManager.getConnection(DB_URL, JDBCCredentials.USERNAME, JDBCCredentials.PASSWORD);         
          } catch (ClassNotFoundException e) {
              throw new UnavailableException("JDBCDemoServlet.init() ClassNotFoundException: " + e.getMessage());
          } catch (SQLException e) {
              throw new UnavailableException("JDBCDemoServlet.init() SQLException: " + e.getMessage());
          }
    }

  

    protected String createProductsHtml(ResultSet rs) throws SQLException
    {
        String innerHTML = "";
        while(rs.next()){
            //Retrieve by column name
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String category = rs.getString("category");
            String description = rs.getString("description");
            double cost = rs.getDouble("cost");
            String image = rs.getString("image");
            String brand = rs.getString("brand");
            //Display values
            String productHTML = 
                    "<div class='box'>" 
                    + "<a href=../ProductDetail>"
                    + "<img src=" + image + "></img></a>" 
                    + "<h5>"  + name  + "</h5>" 
                    + "<h6>Brand: " + brand + "</h6>" 
                    + "<h6>Category: " + category + "</h6>" 
                    + "<div class=\"sale\"> <h4>$" + cost + "</h4></div>"
                    + "<form action=\"product-details\" method=\"GET\">"
                    + "<input type=hidden id=productID name=productID value=" + Integer.toString(id) + ">"
                    + "<input type=submit value=View>"
                    + "</form>" 
                    + "</div>";
            innerHTML += productHTML;
        }
        return innerHTML;
    }

    protected String retrieveWhiteCategory() 
    {

        String outerDiv ="<div class=\"white-chocolate\"> <section class=\"new\" id=\"new\"> <div class=\"centered-text\"> <h2 class=\"category-name\">White</h2></div> <div class=\"new-content\" >";
        String innerHTML = "";
        String closing = "</div> </section></div>";
        // Execute SQL query
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, category, description, cost, image, brand FROM products WHERE category = 'white'";
            ResultSet rs = stmt.executeQuery(sql);
            innerHTML = createProductsHtml(rs);
            rs.close(); 
            stmt.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                return se.getMessage();
            }catch(Exception e){
                //Handle any other type of error
                return e.getMessage();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                    stmt.close();
                }catch(SQLException ignore) {}// nothing we can do
        }
        return outerDiv + innerHTML + closing;

    }

    protected String retrieveDarkCategoryString() 
    {
        String outerDiv ="<section class=\"new\" id=\"new\"> <div class=\"centered-text\"> <h2>Dark</h2></div> <div class=\"new-content\" >";
        String innerHTML = "";
        String closing = "</div> </section>";

        // Execute SQL query
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, category, description, cost, image, brand FROM products WHERE category = 'dark'";
            ResultSet rs = stmt.executeQuery(sql);
            innerHTML = createProductsHtml(rs);
    
            rs.close(); 
            stmt.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                return se.getMessage();
            }catch(Exception e){
                //Handle any other type of error
                return e.getMessage();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                    stmt.close();
                }catch(SQLException ignore) {}// nothing we can do
        }
        return outerDiv + innerHTML + closing;
    }

    protected String retrieveMilkCategory()
    {
        String outerDiv = "<div class=\"milk-chocolate\"><section class=\"new\" id=\"new\"> <div class=\"centered-text\"> <h2>Milk</h2></div> <div class=\"new-content\" >";
        String innerHTML = "";
        String closing = "</div> </section></div>";

        // Execute SQL query
        Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, category, description, cost, image, brand FROM products WHERE category = 'milk'";
            ResultSet rs = stmt.executeQuery(sql);
            innerHTML = createProductsHtml(rs);

            rs.close(); 
            stmt.close();
            }catch(SQLException se){
                //Handle errors for JDBC
                return se.getMessage();
            }catch(Exception e){
                //Handle any other type of error
                return e.getMessage();
            }finally{
                //finally block used to close resources
                try{
                    if(stmt!=null)
                    stmt.close();
                }catch(SQLException ignore) {}// nothing we can do
        } //end try
        return outerDiv + innerHTML + closing;
    }

      /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws SQLException
     */


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String heading  = "<!DOCTYPE html> <html lang='en'> <head>"
                    + "<meta charset='UTF-8'>"
                    + "<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
                    + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                    + "<title>Responsive Website</title>"
                    + "<link rel='stylesheet' type='text/css' href='../css/index.css'>"
                    + "<link rel='stylesheet' type='text/css' href='../css/products.css'>"
                    + "<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>"
                    + "<link rel='preconnect' href='https://fonts.googleapis.com'>"
                    + "<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>"
                    + "<link href='https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap' rel='stylesheet'></head>";
        String ending = "</body></html>";
        String whiteCategory = retrieveWhiteCategory();
        String darkCategory = retrieveDarkCategoryString();
        String milkCategory = retrieveMilkCategory();
        out.println(heading);     
        out.println("<body class='products-list-body'>");
        request.getRequestDispatcher("header").include(request, response);;
        out.println(whiteCategory);
        out.println(darkCategory);
        out.println(milkCategory);
        out.println(ending);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public void destroy () {
        //close the connection
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ignore) {}
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "This servlet dynamically retrieves all the products from the database and displays them on browser";
    }// </editor-fold>
}
