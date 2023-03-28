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
        <link rel="stylesheet" type="text/css" href="css/about.css">
        <link rel='stylesheet' type='text/css' href='css/productDetails.css'>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    </head>

    <body class='product-details-body'>
        <!-- Navigation bar -->
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

        <!-- Main Code from ProductDetailsServlet.java -->
        <%
            String productID = request.getParameter("productID");
            int ID = Integer.parseInt(productID);
            System.out.println("THE ID IS " + ID);
        %>
    
        <sql:setDataSource var = "snapshot" driver = "com.mysql.cj.jdbc.Driver"
                           url = "jdbc:mysql://localhost/chocoland?useSSL=false"
                           user = "root"  password = "aika"/>
        <sql:query dataSource = "${snapshot}" var = "resultDetail">
            SELECT id, name, category, description, cost, image, brand FROM products WHERE id=<%= ID%>;
        </sql:query>
        
        <c:forEach var = "row" items = "${resultDetail.rows}">
        <section class="pd-product">
            <div class="product_image">
                <div class="image-container">
                    <div class='image-main'>
                        <c:set var="imageStr" value="${row.image}"/>
                        <c:set var="imageSub" value="${fn:substring(imageStr, 3, -1)}"/>
                        <img class="product-details-image" src="${imageSub}"></img>
                    </div>
                </div>
            </div>
            
            <div class="product-info">
                <div class="product-detail-title">
                    <h1><c:out value="${row.name}"/></h1>
                    <span><c:out value="${row.id}"/></span>
                </div>

                <div class="pd-cost">
                    $ <span><c:out value="${row.cost}"/></span>
                </div>

                <div class="pd-description">
                    <h3 class=".product-details-h3">Description</h3>
                    <p>Description: <c:out value="${row.description}"/></p>
                    <ul><li>Brand: <c:out value="${row.brand}"/></li></ul>
                </div>
                
                <form action="./api/cart" method="GET" id = "addToCartForm">
                    <input type=hidden id=productID name=productID value=${row.id}>
                    <input type=hidden id=toDo name=toDo value="addToCart">
                </form>
                
                <button type="submit" form="addToCartForm" value="Submit" class="button pd-button">Add To Cart</button>
            </div> 
        </section>
    </c:forEach>

    </body>
</html>