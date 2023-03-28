<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="javax.sql.DataSource" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Responsive Website</title>
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <link rel="stylesheet" type="text/css" href="css/cartContent.css">
        <link rel='stylesheet' type='text/css' href='css/previousOrders.css'>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>

    <body class='previous-orders-body'>
        <!-- Header -->
        <header class='navbar-header'>
            <a href="http://localhost:8080/chocoland/" class="logo">Chocoland</a>
                <ul class="nav-bar">
                <li><a href="http://localhost:8080/chocoland/">Home</a></li>
                <li><a href="products.jsp">Products</a></li>
                <li><a href="previousOrders.jsp">My Orders</a></li>
                <li><a href="about.jsp">About</a></li></ul>
            <div class="header-icons">
                <a href="./api/cart"><i class='bx bx-cart-alt'></i></a>
                <div id="side-bar-icon\"><i class='bx bx-sidebar'></i></div>
            </div>
        </header>

        <script>
            const header = document.querySelector(".navbar-header");
            window.addEventListener("scroll", () => 
            {
            header.classList.toggle("sticky", window.scrollY > 0);
            });
        </script>
        <!---End Header-->
      
    
        <sql:setDataSource var = "snapshot" driver = "com.mysql.cj.jdbc.Driver"
                           url = "jdbc:mysql://localhost/chocoland?useSSL=false"
                           user = "root"  password = "aika"/>
        <sql:query dataSource = "${snapshot}" var = "resultDetail">
            SELECT id, orderDate, totalQuantity, firstName, lastName, taxes, totalPaid, rating  FROM Orders ORDER BY id DESC LIMIT 5
        </sql:query>
        
        <c:forEach var = "row" items = "${resultDetail.rows}">
            <c:if test="${empty row.rating}">
                <c:set var = "rated"
                        value = "false"
                        scope = "page" />
            </c:if>
            <c:if test="${not empty row.rating}">
                <c:set var = "rated"
                value = "true"
                scope = "page" />
            </c:if>


            <div cl ass="d-flex justify-content-between align-items-center mb-4"></div>
            <div>
                <p class="mb-1">Your orders</p>
                <p class="mb-1">Previous last 5 orders</p>
            </div>
            </div>


            <div class="card mb-3">
                <div class="card-body">
                 <div class="d-flex justify-content-between"></div>
                 <div class="d-flex flex-row align-items-center">
                    <div>
                        <img class="img-fluid rounded-3" alt="order" style="width: 65px;" src="images/order-delivery.png"></img>
                    </div>
                          <div class="ms-3">
                       <h5 style="margin-left:10px;"> Order ID:  <c:out value="${row.id}"></c:out> </h5>
                     <p style="margin-left:10px;" class="small mb-0">Order Date: <c:out value="${row.orderDate}"></c:out></p>                                
                     </div>
                    </div>



                   <div class="d-flex flex-row align-items-center">

                      <div style="width: 300px;"><h5 class="fw-normal mb-0 text-center">Quantity:  <c:out value="${row.totalQuantity}"></c:out></h5></div>

                     <div style="width: 300px;" class="text-right"><h5 class="mb-0">Total: $ <c:out value ="${row.totalPaid}"></c:out></h5>
                     <div style='margin-top:10px; margin-left: 170px;' class="d-flex justify-content-between">

                  <span>   Tax: $ <c:out value="${row.taxes}"></c:out></span></div>
                  </div>
                 </div>
                 </div>
                <!-- </div> -->
                  
                  <form  action="./api/rating" method="POST" id = "rating-submit-form-${row.id}">
                  <input type="hidden" name="orderID" value="${row.id}"  >
                  <div class="star-rating">
                  
                  <!-- star5 -->
                <c:choose>
                    <c:when test="${ not rated || row.rating != 5 }">
                        <input type="radio" name="stars" id="star-a-${row.id}" value="5">
                    </c:when>    
                    <c:otherwise>
                        <input type="radio" name="stars" id="star-a-${row.id}" value="5" checked>
                    </c:otherwise>
                </c:choose>


                  
                  <label for="star-a-${row.id}"></label>
            
                  <!-- star4 -->


                  <c:choose>
                    <c:when test="${ not rated || row.rating != 4 }">
                        <input type="radio" name="stars" id="star-b-${row.id}" value="4">
                    </c:when>    
                    <c:otherwise>
                        <input type="radio" name="stars" id="star-b-${row.id}" value="4" checked>
                    </c:otherwise>
                </c:choose>


                <label for="star-b-${row.id}"></label>


                   <!-- star3 -->

                   <c:choose>
                    <c:when test="${ not rated || row.rating != 3 }">
                        <input type="radio" name="stars" id="star-c-${row.id}" value="3">
                    </c:when>    
                    <c:otherwise>
                        <input type="radio" name="stars" id="star-c-${row.id}" value="3" checked>
                    </c:otherwise>
                </c:choose>

                <label for="star-c-${row.id}"></label>



              
                  <!-- star2 -->


                  <c:choose>
                    <c:when test="${ not rated || row.rating != 2 }">
                        <input type="radio" name="stars" id="star-d-${row.id}" value="2">
                    </c:when>    
                    <c:otherwise>
                        <input type="radio" name="stars" id="star-d-${row.id}" value="2" checked>
                    </c:otherwise>
                </c:choose>

                <label for="star-d-${row.id}"></label>

                  <!-- star1 -->

                  <c:choose>
                    <c:when test="${ not rated || row.rating != 1 }">
                        <input type="radio" name="stars" id="star-e-${row.id}" value="1">
                    </c:when>    
                    <c:otherwise>
                        <input type="radio" name="stars" id="star-e-${row.id}" value="1" checked>
                    </c:otherwise>
                </c:choose>

                <label for="star-e-${row.id}"></label>

                  </div>
                   </form>    
                   <button type="submit" form="rating-submit-form-${row.id}" value="Submit" class="btn btn-info btn-block btn-lg rate-button">Rate</button>
               </div>
            <br/>
            <br/>

            </div>                </div>


    </c:forEach>

    </body>
</html>