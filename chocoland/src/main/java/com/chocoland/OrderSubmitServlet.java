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
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class OrderSubmitServlet extends HttpServlet{
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


    protected void goBackHome(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {            
        System.out.println("In go back home");
        HttpSession session = request.getSession();
        session.removeAttribute("cart");
        session.removeAttribute("orderSubmitted");
        session.removeAttribute("totalPaid");
        session.removeAttribute("tax");
        System.out.println("SCHEME " + request.getScheme());
        System.out.println(request.getServerName());
        System.out.println(request.getServerPort() == 80 ? "" : ":" + request.getServerPort());
        System.out.println(request.getContextPath());
        response.sendRedirect("http://localhost:8080/chocoland/");
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // get all the products that were in the cart
        HttpSession session = request.getSession();
        if(session.getAttribute("orderSubmitted") != null && session.getAttribute("orderSubmitted").equals("true") )
        {
            System.out.println("ORDER SUBMITTED WAS TRUE");
            goBackHome(request, response);
            return;
        }

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        // String total = (String)session.getAttribute("total");
        //get the total
        double total = 0;
        // double totalPaid;
        int totalQuantity = 0;
        // double taxes = 0;
        for (CartItem i: cart)
        {
            total+= i.getProduct().getCost();
            totalQuantity+= i.getQuantity();
        }
        // taxes = total*0.1;
        // totalPaid = total+taxes;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String address2 = request.getParameter("address2");
        String country = request.getParameter("country");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String ccName = request.getParameter("ccName");
        String ccNumber = request.getParameter("ccNumber");
        String ccExpiration = request.getParameter("ccExpiration");
        String ccCVV = request.getParameter("ccCvv");
        String city = request.getParameter("city");
        double tp =  Double.parseDouble(request.getParameter("totalWithTax"));
        double t  = Double.parseDouble(request.getParameter("tax"));
        String totalPaid = String.format("%.2f", tp);
        String taxes = String.format("%.2f", t);
        System.out.println("TOTAL WITH TAX: " + Double.toString(tp));
        System.out.println("TAX " + Double.toString(t));
        System.out.print("totalPaid: " + totalPaid);
        System.out.print("taxes" + taxes);



        address = address + " " + (address2 == null ? " " : address2);
        
        Order myOrder = new Order(firstName, lastName, phoneNumber, email, address, country, state, city, zip, ccName, ccNumber, ccExpiration, 
        ccCVV,  totalQuantity, taxes, totalPaid);
        MySQLJDBC myDBManager= new MySQLJDBC();
        // initialize the connection first! only then call the inserOrder function
        myDBManager.insertOrder(myOrder, cart);
        System.out.println("The order was successfully inserted into the Orders table");
        session.setAttribute("orderSubmitted", "true");
        session.setAttribute("totalPaid", totalPaid); 
        session.setAttribute("tax",taxes); 
        System.out.println(session);
        request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
        // redirect to the confirmation page
        // would like to show all the order details on that page including the which products
        // session.removeAttribute("cart");

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
        return "This servlet dynamically retrieves product detailsa according to the ID in the GET request";
    }// </editor-fold>


}
