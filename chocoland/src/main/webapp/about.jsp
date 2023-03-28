
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Website</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/about.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap" rel="stylesheet">

</head>

<body class="about-body">


  <header class="navbar-header">
    <a href="#" class="logo">Chocoland</a>
    <ul class="nav-bar">
        <li><a href="http://localhost:8080/chocoland/">Home</a></li>
        <li><a href="products.jsp">Products</a></li>
        <li><a href="previousOrders.jsp">My Orders</a></li>
        <li><a href="about.jsp">About</a></li>
    </ul>
    <div class="header-icons">
        <a href="./api/cart"><i class='bx bx-cart-alt'></i></a>
        <div id="side-bar-icon"><i class='bx bx-sidebar'></i></div>
    </div>
</header>

<script>
    const header = document.querySelector(".navbar-header");
    window.addEventListener("scroll", () => 
    {
    header.classList.toggle("sticky", window.scrollY > 0);
    });
</script>


<section class="featured-about"id="featured">
    <div class="centered-text">
        <h2>Featured Categories</h2>
    </div>

    <div class="about-content">
        <div class="row about-center">
            <img className="about-image" src="images/factory1.jpg"></img>
        </div>

        <div class="row about-center">
            <img className="about-image" src="images/factory2.jpg"></img>
        </div>

        <div class="row about-center">
            <img className="about-image" src="images/factory3.jpg"></img>
        </div>  
    </div>
</section>

<section class="featured-about">
    <div class="centered-text border-box">
        <h2>Who We Are?</h2>
    </div>
    <div class="about-content">
        <div class="row about-center border-box">
            <h1>Orhan Ozbasaran</h1>
        </div>

        <div class="row about-center border-box">
            <h1>Ayako Kuwayama</h1>
        </div>

        <div class="row about-center border-box">
            <h1>Aigerim Kubanychbek Kyzy</h1>
        </div>
    </div>
</section>

<section class="featured-about" >
    <div class="centered-text">
        <p className="border-box">
            One of the most iconic brands of the Chocoland Group was created in 1982 in Alba, a small town in the hills of Piedmont in Italy. It was inspired by the desire to make all the pleasure of a sophisticated chocolate specialty accessible to a much wider public. Initially sold in Europe, Chocoland soon became the favorite boxed chocolate for millions of people around the world. Today, it is the world leader in its category and is sold in 140 countries across five continents.
        </p>
    </div>
</section>

<section class="contact" id="contact">

    <div class="main-contact">
        <h3>ChocoLand</h3>
        <h5>Contact us</h5>
        <div class="icons">
            <a href="#"><i class='bx bxl-gmail'></i></a>
            <a href="#"><i class='bx bxl-instagram'></i></a>
            <a href="#"><i class='bx bxl-facebook-circle'></i></a>
        </div>
    </div>

    <div class="main-contact">
        <h3>Keep browsing</h3>
        <li><a href="#home">Home</a></li>
        <li><a href="#featured">Featured</a></li>
        <li><a href="#new">New</a></li>
        <li><a href="#contact">Contact</a></li>
    </div>

    <div class="main-contact">
        <h3> Services </h3>
        <li><a href="#home">Order</a></li>
        <li><a href="#home">Check order status</a></li>
    </div>
</section>

</body></html>
