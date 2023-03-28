<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="javax.sql.DataSource" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<!DOCTYPE html> <html lang='en'> 
    <head>
    <meta charset='UTF-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Responsive Website</title>
    <link rel='stylesheet' type='text/css' href='css/index.css'>
    <link rel='stylesheet' type='text/css' href='css/products.css'>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel='preconnect' href='https://fonts.googleapis.com'>
    <link rel='preconnect' href='https://fonts.gstatic.com' crossorigin>
    <link href='https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap' rel='stylesheet'>
    </head>
    
    <body class='products-list-body'>
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

        <sql:setDataSource var = "snapshot" driver = "com.mysql.cj.jdbc.Driver"
                        url = "jdbc:mysql://localhost/chocoland?useSSL=false"
                        user = "root"  password = "aika"/>
        <sql:query dataSource = "${snapshot}" var = "result">
            SELECT id, name, category, description, cost, image, brand FROM products WHERE category = 'white'
        </sql:query>

        <div class="white-chocolate"> 
            <section class="new" id="new"> 
                <div class="centered-text"> 
                    <h2 class="category-name">White</h2>
                </div> 
                <div class="new-content">
                    <c:forEach var = "row" items = "${result.rows}">
                        <div class='box'>
                            <c:set var="imageStr" value="${row.image}"/>
                            <c:set var="imageSub" value="${fn:substring(imageStr, 3, -1)}"/>
                            <img src="${imageSub}"></img></a>
                            <h5><c:out value="${row.name}"/></h5>
                            <h6>Brand: <c:out value="${row.brand}"/></h6>
                            <h6>Category: <c:out value="${row.category}"/></h6>
                            <div class="sale">
                                    <h4>$ <c:out value="${row.cost}"/></h4>
                            </div>
                            <form action="productDetails.jsp" method="GET">
                                <input type=hidden id=productID name=productID value="${row.id}">
                                <input type=submit value=View>
                            </form>
                        </div>
                    </c:forEach>
                </div> 
            </section>

            <sql:query dataSource = "${snapshot}" var = "result">
                SELECT id, name, category, description, cost, image, brand FROM products WHERE category = 'dark'
            </sql:query>

            <section class="new" id="new"> 
                <div class="centered-text"> 
                    <h2 class="category-name">Dark</h2>
                    </div> 
                <div class="new-content">
                    <c:forEach var = "row" items = "${result.rows}">
                    <div class='box'>
                                <c:set var="imageStr" value="${row.image}"/>
                                <c:set var="imageSub" value="${fn:substring(imageStr, 3, -1)}"/>
                                <img src="${imageSub}"></img></a>
                                <h5><c:out value="${row.name}"/></h5>
                                <h6>Brand: <c:out value="${row.brand}"/></h6>
                                <h6>Category: <c:out value="${row.category}"/></h6>
                                <div class="sale"> <h4>$ <c:out value="${row.cost}"/> </h4></div>
                                <form action="productDetails.jsp" method="GET">
                                <input type=hidden id=productID name=productID value="${row.id}">
                                <input type=submit value=View>
                                </form>
                    </div>
                    </c:forEach>
                </div> 
            </section>

            <sql:query dataSource = "${snapshot}" var = "result">
                SELECT id, name, category, description, cost, image, brand FROM products WHERE category = 'milk'
            </sql:query>

            <div class="milk-chocolate"> 
                <section class="new" id="new"> 
                    <div class="centered-text"> 
                        <h2 class="category-name">Milk</h2>
                    </div> 
                    <div class="new-content">
                        <c:forEach var = "row" items = "${result.rows}">
                            <div class='box'>
                                        <c:set var="imageStr" value="${row.image}"/>
                                        <c:set var="imageSub" value="${fn:substring(imageStr, 3, -1)}"/>
                                        <img src="${imageSub}"></img></a>
                                        <h5><c:out value="${row.name}"/></h5>
                                        <h6>Brand: <c:out value="${row.brand}"/></h6>
                                        <h6>Category: <c:out value="${row.category}"/></h6>
                                        <div class="sale"> <h4>$ <c:out value="${row.cost}"/> </h4></div>
                                        <form action="productDetails.jsp" method="GET">
                                        <input type=hidden id=productID name=productID value="${row.id}">
                                        <input type=submit value=View>
                                        </form>
                            </div>
                        </c:forEach>
                    </div> 
            </section>
        </div>
   </body>
</html>