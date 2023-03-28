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


public class HeaderServlet extends HttpServlet
{



    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                System.out.println("in the process Request pf HEADER SERVLET");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String header = 

        "<header class='navbar-header'><a href=\"http://localhost:8080/chocoland/\" class=\"logo\">Chocoland</a>"
        + "<ul class=\"nav-bar\">"
            +"<li><a href=\"http://localhost:8080/chocoland/\">Home</a></li>"
            +"<li><a href=\"http://localhost:8080/chocoland/products.jsp\">Products</a></li>"
            +"<li><a href=\"http://localhost:8080/chocoland/previousOrders.jsp\">My Orders</a></li>"
           + "<li><a href=\"http://localhost:8080/chocoland/about.jsp\">About</a></li></ul>"
       + "<div class=\"header-icons\">"
            +"<a href=\"cart\"><i class='bx bx-cart-alt'></i></a>"
           + "<div id=\"side-bar-icon\"><i class='bx bx-sidebar'></i></div></div></header>"
            // creating a sticky navigation bar"
               +"<script>const header = document.querySelector(\".navbar-header\");"
         +  "window.addEventListener(\"scroll\", () => {"
             +  "header.classList.toggle(\"sticky\", window.scrollY > 0);"
          + "});</script>";
        out.println(header);
        return;
        // creating a sticky navigation bar

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
    
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "This servlet dynamically retrieves product detailsa according to the ID in the GET request";
    }// </editor-fold>
}

