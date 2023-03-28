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


public class ProductDetailsServlet extends HttpServlet
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
    private String retrieveProductDetails(int productID)
    {
        Statement stmt = null;
        String productHTML= "";
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, category, description, cost, image, brand FROM products WHERE id = " + Integer.toString(productID);

            ResultSet rs = stmt.executeQuery(sql);
                //Retrieve by column name

            while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String category = rs.getString("category");
                    String description = rs.getString("description");
                    double cost = rs.getDouble("cost");
                    String image = rs.getString("image");
                    String brand = rs.getString("brand");
                    //Display values

                    productHTML =
                            "<section class=\"pd-product\">"
                        + "<div class=\"product_image\">"
                    + "<div class=image-container'>"
                        + "<div class='image-main'>"
                    +"<img class=\"product-details-image\" src=" + image + "></img>"
                +"</div>"
            +"</div>"
            +"</div>"
            + "<div class=\"product-info\">"
            + "<div class=\"product-detail-title\">"
                + "<h1>" + name + "</h1>"
                +"<span>ID:"  + Integer.toString(productID)+ "</span>"
            +"</div>"
            +"<div class=\"pd-cost\">"
                +"$ <span>" + cost + "</span>"
            +"</div>"
            +"<div class=\"pd-description\">"
                +"<h3 class=\".product-details-h3\">Description</h3>"
                +"<p>Description: " + description + "</p>" 
                + "<ul><li>Brand: " + brand + "</li></ul>"

            +"</div>"
            + "<form  action=\"cart\" method=\"GET\" id = \"addToCartForm\">"
            + "<input type=hidden id=productID name=productID value="+ productID +">"
            + "<input type=hidden id=toDo name=toDo value="+ "addToCart" +">"
            // + "<input class = \"button\" type=submit value=Add to Cart>"

            + "</form>"
            + "<button type=\"submit\" form=\"addToCartForm\" value=\"Submit\" class=\"button pd-button\">Add To Cart</button></div> </section>";
                
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
        return productHTML;
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productID = request.getParameter("productID");
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String heading  = "<!DOCTYPE html> <html lang='en'> <head>"
                    + "<meta charset='UTF-8'>"
                    + "<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
                    + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                    + "<title>Responsive Website</title>"
                    + "<link rel='stylesheet' type='text/css' href='../css/index.css'>"
                    + "<link rel='stylesheet' type='text/css' href='../css/productDetails.css'>"
                    + "<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>"
                    + "<link rel='preconnect' href='https://fonts.googleapis.com'>"
                    + "<link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>"
                    + "<link href='https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap' rel='stylesheet'></head>";
        String productDetails = retrieveProductDetails(Integer.parseInt(productID));
        String ending = "</body></html>";
        out.println(heading);
        out.println("<body class='product-details-body'>");
        request.getRequestDispatcher("header").include(request, response);
        out.println(productDetails);
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
        return "This servlet dynamically retrieves product detailsa according to the ID in the GET request";
    }
}

