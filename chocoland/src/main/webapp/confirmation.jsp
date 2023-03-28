

<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en">
    <head> <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Website</title>
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <link rel="stylesheet" type="text/css" href="../css/cartContent.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap" rel="stylesheet"></head>
    <body class="confirmation-body">


        <header class="navbar-header">
            <a href="order-submit" class="logo">Chocoland</a>
            <ul class="nav-bar">
              <!-- in order to clean the clean the cart need to take to another servlet before taking back home -->
                <li><a href="order-submit">Home</a></li>
                <!-- <li><a href="./api/products">Products</a></li>
                <li><a href="./api/previous-orders">My Orders</a></li>
                <li><a href="#contact">About</a></li> -->
            </ul>
            <!-- <div class="header-icons">
                <a href="./api/cart"><i class='bx bx-cart-alt'></i></a>
                <div id="side-bar-icon"><i class='bx bx-sidebar'></i></div>
            </div> -->
        </header>
    <!-- <a href ="order-submit">Home</a> -->


    <div class="d-flex justify-content-between align-items-center mb-4">
        <div><p class="mb-1">Thank you for your order!</p>
          <p class="mb-0">Here are the details of your order with ${sessionScope.cart.size()} item(s) </p></div></div>
      <c:set var="total" value="0"></c:set>
      <c:forEach var="cartItem" items="${sessionScope.cart}">
        <!-- <c:set var="total" value="${total + cartItem.product.cost * cartItem.quantity }"></c:set> -->
          <div class="card mb-3">
            <div class="card-body">
              <div class="d-flex justify-content-between">
                <div class="d-flex flex-row align-items-center"><div>
                    <img
                      src=${cartItem.product.image}
                      class="img-fluid rounded-3" alt="Your chocolate" style="width: 65px;"></div>
                  <div class="ms-3">
                    <h5>${cartItem.product.name}</h5>
                    <p class="small mb-0">ID: ${cartItem.product.id}</p>
                    <!-- <p class="small mb-0">Total Paid: $${total + total*0.1}</p> -->

                </div></div>
                <div class="d-flex flex-row align-items-center">
                    <div style="width: 300px;"><h5 class="fw-normal mb-0 ">Qty: ${cartItem.quantity}</h5></div>
                  <div style="width: 200px;"><h5 class="mb-0">$${cartItem.product.cost}</h5>
                </div><a href="#!" style="color: #cecece;"><i class="fas fa-trash-alt"></i></a></div></div>
              <!-- <div class="d-flex justify-content-between">
                <span>Total: $${total + total*0.1}</span>
            </div> -->
            </div></div>
        </c:forEach>

 
        
          <div class="d-flex justify-content-between">
            <p class="mb-2">Tax</p>
            <p class="mb-2">$<c:out value="${sessionScope.tax}"></c:out></p>
          </div>


          
    
      <hr class="my-4">
    
    
      <div class="d-flex justify-content-between mb-4">
        <p class="mb-2">Total Paid</p>
        <p class="mb-2">$<c:out value="${sessionScope.totalPaid}"></c:out></p>
      </div>
      
    <script>
     const header = document.querySelector(".navbar-header");
    window.addEventListener("scroll", () => {
    header.classList.toggle("sticky", window.scrollY > 0);
});
    </script>
    </body></html>