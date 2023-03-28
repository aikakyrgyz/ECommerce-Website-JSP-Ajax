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
public class PreviousOrdersServlet extends HttpServlet {
    
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

     private String retrieveOrders()
     {
         Statement stmt = null;
         String orderHTML= "";
         String orders = "";
        int count = 0;
        boolean rated=false;
        int rating=-1;
         try {
             stmt = conn.createStatement();
             String sql;
             sql = "SELECT id, orderDate, totalQuantity, firstName, lastName, taxes, totalPaid, rating  FROM Orders ORDER BY id DESC";
 
             ResultSet rs = stmt.executeQuery(sql);
                 //Retrieve by column name
 
             while(rs.next()){
                    if (count>=5) break;
                     int id = rs.getInt("id");
                     String orderDate = rs.getString("orderDate");
                     int totalQuantity = rs.getInt("totalQuantity");
                     String firstName = rs.getString("firstName");
                     String lastName = rs.getString("lastName");
                     double taxes = rs.getDouble("taxes");
                     double totalPaid = rs.getDouble("totalPaid");
                     rating = rs.getInt("rating");
                     System.out.println("PRS rating: " + rating);
                     if(rs.wasNull())
                     {
                        rated=false;
                     }
                     else{
                        rated = true;
                     }
                     String orderImage = "../images/order-delivery.png";

                    String star5 = !rated || rating!=5 ? "<input type=\"radio\" name=\"stars\" id=\"star-a-" + id + "\" value=\"5\"/>" : 
                                                        "<input type=\"radio\" name=\"stars\" id=\"star-a-"  + id + "\" value=\"5\" checked/>";
                    
                    String star4 = !rated || rating!=4 ?  "<input type=\"radio\" name=\"stars\" id=\"star-b-" + id + "\" value=\"4\"/>":
                                                             "<input type=\"radio\" name=\"stars\" id=\"star-b-" + id + "\" value=\"4\" checked/>";


                    String star3 = !rated || rating!=3 ?  "<input type=\"radio\" name=\"stars\" id=\"star-c-" + id + "\" value=\"3\"/>":
                                                            "<input type=\"radio\" name=\"stars\" id=\"star-c-" + id + "\" value=\"3\" checked/>";
                          
                    String star2 = !rated || rating!=2 ?  "<input type=\"radio\" name=\"stars\" id=\"star-d-" + id + "\" value=\"2\"/>":
                                                             "<input type=\"radio\" name=\"stars\" id=\"star-d-" + id + "\" value=\"2\" checked/>";
                    
                    String star1 = !rated || rating!=1 ?  "<input type=\"radio\" name=\"stars\" id=\"star-e-" + id + "\" value=\"1\"/>":
                                                             "<input type=\"radio\" name=\"stars\" id=\"star-e-" + id + "\" value=\"1\" checked/>";

                    orderHTML =
                     "<div class=\"card mb-3\">"
                     +"<div class=\"card-body\">"
                      + "<div class=\"d-flex justify-content-between\">"
                        


                      +"<div class=\"d-flex flex-row align-items-center\">"
                             
                         
                         +"<div><img class=\"img-fluid rounded-3\" alt=\"order\" style=\"width: 65px;\""
                              + " src=" + orderImage + "></img>"
                               + "</div>"



                         
                         
                               +  "<div class=\"ms-3\">"
                            + "<h5 style=\"margin-left:10px;\"> Order ID: " + id + "</h5>"
                          +  "<p style=\"margin-left:10px;\" class=\"small mb-0\">Order Date: " + orderDate + "</p>"                                
                          + "</div>"
                         
                         
                          + "</div>"



                        + "<div class=\"d-flex flex-row align-items-center\">"

                           +  "<div style=\"width: 300px;\"><h5 class=\"fw-normal mb-0\">Quantity:  " + totalQuantity + "</h5></div>"
                          + "<div style=\"width: 300px;\"><h5 class=\"mb-0\">Total: $ " +   String.format("%.2f", totalPaid) + "</h5>"
                     
                          + "<div style='margin-top:10px;'class=\"d-flex justify-content-between\">"
                       +  "<span>Tax: $ " + String.format("%.2f", taxes) + "</span></div>"

                       +"</div>"
                       
                      + "</div>"

                      + "</div></div>"
                       // stars
                       + "<form  action=\"rating\" method=\"POST\" id = \"rating-submit-form-" + id + "\">"
                       +  "<input type=\"hidden\" name=\"orderID\" value=\"" + id  + "\"/>"
                       + "<div class=\"star-rating\">"
                       
                       + star5
                       + "<label for=\"star-a-" +  id + "\"></label>"
                 
                       + star4
                       + "<label for=\"star-b-" + id + "\"></label>"
                   
                        + star3
                       + "<label for=\"star-c-" + id + "\"></label>"
                   
                       + star2
                       + "<label for=\"star-d-" + id +  "\"></label>"
                   
                       + star1

                       + "<label for=\"star-e-" + id + "\"></label>"
                       + "</div>"
                        + "</form>"
                        // + "<h2>Rating is : " + rating + "</h2>"
                         + "<button type=\"submit\" form=\"rating-submit-form-" +  id + 
                         "\" value=\"Submit\" class=\"btn btn-info btn-block btn-lg rate-button\">Rate</button>"

                    + "</div></div>";
                    orders += orderHTML;
                    count++;                    
                     }
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
         return orders;
     }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println("Previous Orders Servlet");
    //    request.getRequestDispatcher("header").include(request, response);;

        String heading  = "<!DOCTYPE html> <html lang='en'> <head>"
        + "<meta charset='UTF-8'>"
        + "<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
        + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
        + "<title>Responsive Website</title>"

        + "<link rel='stylesheet' type='text/css' href='../css/index.css'>"
        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/cartContent.css\">"
        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/previousOrders.css\">"
        + "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">"
        + "<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>"
        + "<link rel='preconnect' href='https://fonts.googleapis.com'>"
        + "<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>"
        + "<link href='https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap' rel='stylesheet'></head>";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(heading);
        out.println("<body class='previous-orders-body'>");
        request.getRequestDispatcher("header").include(request, response);;

        // RequestDispatcher rq = (RequestDispatcher) request.getRequestDispatcher("./api/header");
        // ((javax.servlet.RequestDispatcher) rq).include(request, response);
        out.println("<div class=\"d-flex justify-content-between align-items-center mb-4\">");
        out.println("<div><p class=\"mb-1\">Your previous orders</p>");
        out.println("<p class=\"mb-0\">These are your 5 last orders</p></div></div>");
        String ordersCards = retrieveOrders();
        out.println(ordersCards);
        out.println("</body></html>"); 
        
    }


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
        return "This servlet dynamically retrieves previous orders";
    }// </editor-fold>
}


