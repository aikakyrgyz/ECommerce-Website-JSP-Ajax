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
import org.json.simple.*;



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


public class CityStateServlet extends HttpServlet
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ParseTax db = new ParseTax();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        System.out.println(request.getParameterMap());
        if (request.getParameterMap().containsKey("q")) {
            List<String> stateMatches = db.retrieveStates();
            JSONArray states = new JSONArray();
            String state = request.getParameter("q");
            System.out.println("Q IS " + state);
            String regex = ".*" + state + ".*"; 
            // ".*A.*"

            for (String e: stateMatches)
            {
                if(e.matches(regex)){
                    states.add(e);
                    System.out.println(e);
                    System.out.println(e + e.matches(regex));
                }
            }
            JSONObject json = new JSONObject();
            json.put("stateMatches", states);
            out.println(json);
        }
       else
       {
            int zip = Integer.parseInt(request.getParameter("zip").trim());
            double subTotal = Double.parseDouble(request.getParameter("subtotal").trim());
            JSONObject taxData = db.retrieveTaxData(zip);
            double tax = (Double) taxData.get("rate") * subTotal;
            taxData.put("tax", tax);
            taxData.put("total", tax + subTotal);
            out.println(taxData);
       }
        // return taxData.toString();
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


