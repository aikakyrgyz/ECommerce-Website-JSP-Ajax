

Chocoland Website with Servlet as a Maven project using Tomcat 9.x Server.

The website consists of four main pages:
* Home
* Product
* My orders
* Cart
* About

For the detailed view of all the files within the project, please go to: [https://github.com/aikakyrgyz/JavaServlets-Ecommerce-Website]

### Additional Files that were added:
* **CityStateServlet.java**: For Ajax abilities of the website. When the user enters text in the State field, it will return a JSON object of all the matching states from the database. In times when the user enters the zip code (the database must contain this zip code), the zip code, total price, and the tax values will be sent with the request. When received, tt will find the city, state, and tax rate that are associated with this code and return a JSON object with the updated tax charge, total price, as well as city and state fields.

* **ParseTax.java**: parses the two files, tax_rates.csv and zip_codes.csv and creates a database containing records with zip code, state, city, and the tax rate. **Notice**: the files do not have a matching information, some of the zip-code rates are missing from the tax_rates.csv, for that reason when testing the Ajax requirement please use a zip code with non-NULL field of rate that is contained in the Taxes table. 

* **products.jsp**: described below

* **productDetails.jsp**: described below

* **previousOrders.jsp**: described below


## How the requirements for this project were satisfied:

1. 
    *  *Reimplementing home page*: ``products.jsp`` lists all the product available <br/>
             Servlet rewritten: ``ProductsServlet.java`` <br/>
             Products Page <br/>
             ![Screen Shot 2023-03-19 at 22 40 58](https://user-images.githubusercontent.com/80508372/226256876-d23399af-cc1f-47c0-afd4-7ec7e75a046c.png)

    *  *Reimplementing any servlet (1st)*: ``productDetails.jsp`` contains the detailed information about the chosen product <br/>
             Servlet rewritten: ``ProductDetailsServlet.java``<br/>
             Product Details Page <br/>
             ![Screen Shot 2023-03-19 at 22 41 25](https://user-images.githubusercontent.com/80508372/226256994-8099ef38-2ce1-4c9e-9918-67c18278d851.png)

    *  *Reimplementing any servlet (2nd)*: ``previousOrders.jsp`` contains the information about the last five orders, contains the rating functionalities.<br/>
             Servlet rewritten: ``PreviousOrdersServlet.java``<br/>
             Previous Orders Page <br/>
             ![Screen Shot 2023-03-19 at 22 41 10](https://user-images.githubusercontent.com/80508372/226257070-8dfc0be5-e36f-4556-b21f-f0edc2e00b17.png)

2. 
    * *Ajax (1st)*: When the user enters a zip code, the city, state, and country fields will be automatically filled (on blur). <br/>
    ![Zip code in the database](https://user-images.githubusercontent.com/80508372/226254254-dd504a38-9ccb-4d0d-a479-ceca4b21aa79.png)
) <br/><br/>
      The value will be retrieved from the database and automatically filled in the form<br/>
    ![Zip Code 99129 corresponds to state WA and city Fruitland](https://user-images.githubusercontent.com/80508372/226254344-3eb2f3cc-077e-4db4-bb99-39fae59cd0e8.png)
)
    * *Ajax (2nd)*: Depending on the entered zip code, the tax subcharge as well as the total due charge will be automatically updated
    ![The updated charge](https://user-images.githubusercontent.com/80508372/226254593-6306f396-88bc-42e6-8009-3256dcc37915.png)
)
    * *Ajax (3rd)*: When the user enters characters in the state form field, it will automatically present possible matches for the state
    ![State matches as a convenient list](https://user-images.githubusercontent.com/80508372/226254699-67654585-b185-4014-b820-b34dc19ce12e.png)
)
3. *Extra credit*: not implemented

## Instructions on Running the Project:
1. Change ``JDBCCredentials.java`` to your configurations of the database. <br/>
    The database name is **chocoland**
3. Run ``MySQLJDBC.java`` in order to create all necessary tables and insert the available products.
    **Note**: If you are having issues with paths to the CSV files, try to change it to the absolute paths within your system: <br/>
        ``generateProducts.java`` -> pathToProductsCSV <br/>
        ``ParseTax.java``         -> pathToTaxesCSV 
                                  and PathToZipCSV <br/>

3. Run ``ParseTax.java`` in order to create the tax, city, state database (for Ajax)
4. ``maven clean``
5. ``maven package``
6. run ``chocoland.war`` on server (Tomcat 9.x)
7. go to: ``localhost:8080/chocoland``




