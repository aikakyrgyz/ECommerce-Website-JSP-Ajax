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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




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


public class CartServlet extends HttpServlet
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


    protected Product retrieveProduct(String productID)
    {
        Product myProduct = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, category, description, cost, image, brand FROM products WHERE id = " + productID;
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
                    myProduct = new Product(id, name, category, description, cost, image, brand);
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
        return myProduct;
    }


    protected void showCartContent(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        request.getRequestDispatcher("/cartContent.jsp").forward(request, response);
        }

        
    protected int alreadyInCart(List<CartItem> cart, String id)
    {
        int productID = Integer.parseInt(id);
        for(int i=0; i < cart.size(); i++)
        {
            if(cart.get(i).getProduct().getId() == productID) return i;
        }
        return -1;

    }


    protected void addToCart(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
    
        System.out.println("IN add TO CART");
		HttpSession session = request.getSession();
        Product myProduct = retrieveProduct(request.getParameter("productID").toString());

		if (session.getAttribute("cart") == null) {
			List<CartItem> cart = new ArrayList<CartItem>();
			cart.add(new CartItem(myProduct, 1));
			session.setAttribute("cart", cart);
            System.out.println(cart);
            System.out.println(cart.get(0).getProduct().name + "added to cart");
		} else {
			List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
			int inCartIndex = alreadyInCart(cart, request.getParameter("productID"));
            System.out.println(inCartIndex);
			if (inCartIndex < 0) {
				cart.add(new CartItem(myProduct, 1));
			} else {
    		    cart.get(inCartIndex).setQuantity(cart.get(inCartIndex).getQuantity()+1);
			}
			session.setAttribute("cart", cart);
		}
        System.out.println("REDIRECTING TO CART VIEW");
		response.sendRedirect("cart");
    }


    // Cart productModel = new ProductModel();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
            
    {
        System.out.println("IN THE CART SERVLET!!!");
            if(request.getParameter("toDo") == null)
            {
                showCartContent(request, response);
            }
            else if (request.getParameter("toDo").equals("addToCart"))
            {
                addToCart(request, response);

            }
        }

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
        return "This servlet add the item to the cart and then redirects to the page that shows all the cart items";
    }// </editor-fold>
}

