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


public class RatingServlet extends HttpServlet
{
    
    Connection conn;
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




    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // retrieve the value of the radio button
        String starNum = request.getParameter("stars");
        String id = request.getParameter("orderID");
        // StarNum is either 1, 2, 3, 4, or 5
        int rating = Integer.parseInt(starNum);
        int orderID = Integer.parseInt(id);
        MySQLJDBC myDB = new MySQLJDBC();
        // updating the rating row in the database
        myDB.insertRatingIntoOrdersTable(orderID, rating);
        // send the rating to the database
        // cannot send the request form data again. so redirect, not forward.
        response.sendRedirect("http://localhost:8080/chocoland/previousOrders.jsp");
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
        return "This servlet dynamically retrieves product ratings";
    }// </editor-fold>
}

