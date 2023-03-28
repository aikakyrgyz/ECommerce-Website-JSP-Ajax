
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Website</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <link rel="stylesheet" type="text/css" href="../css/cartContent.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>

<body>


  <header class="navbar-header">
    <a href="#" class="logo">Chocoland</a>
    <ul class="nav-bar">
        <li><a href="http://localhost:8080/chocoland/">Home</a></li>
        <li><a href="http://localhost:8080/chocoland/products.jsp">Products</a></li>
        <li><a href="http://localhost:8080/chocoland/previousOrders.jsp">My Orders</a></li>
        <li><a href="http://localhost:8080/chocoland/about.jsp">About</a></li>
    </ul>
    <div class="header-icons">
        <a href="cart"><i class='bx bx-cart-alt'></i></a>
        <div id="side-bar-icon"><i class='bx bx-sidebar'></i></div>
    </div>
</header>

<body class="confirmation-body">
  <body class="bg-light" data-new-gr-c-s-check-loaded="14.1098.0" data-gr-ext-installed="">
  <div class="container">
  <div class="row">                
    <div class="col-lg-12">

      <!-- <div class="col-lg-7"> -->
        <h5 class="mb-3"><a href="http://localhost:8080/chocoland/products.jsp" class="text-body"><i
              class="fas fa-long-arrow-alt-left me-2"></i>Continue shopping</a></h5>
        <hr>

        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
              <h3 class="mb-1">Shopping cart</h3>
    <p class="mb-0">You have ${sessionScope.cart.size()} item(s) in your cart</p>
            </div>
          </div>

    <c:set var="total" value="0"></c:set>
    <c:forEach var="cartItem" items="${sessionScope.cart}">
      <c:set var="total" value="${total + cartItem.product.cost * cartItem.quantity }"></c:set>
        <div class="card mb-3">
          <div class="card-body">
            <div class="d-flex justify-content-between">
              <div class="d-flex flex-row align-items-center">
                <div>
                  <img
                    src=${cartItem.product.image}
                    class="img-fluid rounded-3" alt="Your chocolate" style="width: 65px;">
                </div>
                <div class="ms-3">
                  <h5>${cartItem.product.name}</h5>
                  <p class="small mb-0">ID: ${cartItem.product.id}</p>
                </div>
              </div>
              <div class="d-flex flex-row align-items-center">
                <div style="width: 300px;">
                  <h5 class="fw-normal mb-0 ">Qty: ${cartItem.quantity}</h5>
                </div>
                <div style="width: 200px;">
                  <h5 class="mb-0">$${cartItem.product.cost}</h5>
                </div>
                <a href="#!" style="color: #cecece;"><i class="fas fa-trash-alt"></i></a>
              </div>
            </div>
          </div>
        </div>
  </c:forEach>
    </div>


      <div class="d-flex justify-content-between">
        <p class="mb-2" id="subtotal">SubTotal</p>
        <p class="mb-2">$${total}</p>

      </div>

    
      <div class="d-flex justify-content-between">
        <p class="mb-2">Tax</p>
        <p class="mb-2" id="tax">$${total*0.1}</p>

      </div>

  <hr class="my-4">


  <div class="d-flex justify-content-between mb-4">
    <p class="mb-2">Total (Incl. taxes)</p>
    <p class="mb-2" id="totalwithtax">$${total + total*0.1}</p>

  </div>
    </div>

   


    <div class="col-md-8 order-md-1">
      <h4 class="mb-3">Shipping & Billing</h4>
      <form class="needs-validation" novalidate="" action="order-submit" method="POST" id="order-submit-form">
              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="firstName">First name</label>
                  <input type="text" class="form-control" name="firstName" id="firstName" placeholder="" value="" required pattern ="[a-zA-Z]{1,32}">
                  <div class="invalid-feedback">
                    Valid first name is required.
                  </div>
                </div>
                <div class="col-md-6 mb-3">
                  <label for="lastName">Last name</label>
                  <input type="text" class="form-control" name="lastName" id="lastName" placeholder="" value=""required pattern="[a-zA-Z]{1,32}">
                  <div class="invalid-feedback">
                    Valid last name is required.
                  </div>
                </div>
              </div>
      
              <div class="mb-3">
                <label for="phoneNumber">Phone Number</label>
                <div class="input-group">
                  <div class="input-group-prepend">
                    <span class="input-group-text">+1</span>
                  </div>
                  <input name="phoneNumber" type="text" class="form-control" id="phoneNumber" placeholder="123-123-1212" required="" required pattern="^(1-)?\d{3}-\d{3}-\d{4}$">
                  <div class="invalid-feedback" style="width: 100%;">
                    Your phone number is required with the following style "123-123-1212".
                  </div>
                </div>
              </div>
      
              <div class="mb-3">
                <label for="email">Email</label>
                <input type="email" class="form-control" name="email" id="email" placeholder="you@example.com" required="">
                <div class="invalid-feedback">
                  Please enter a valid email address for shipping updates.
                </div>
              </div>
      
              <div class="mb-3">
                <label for="address">Address</label>
                <input type="text" class="form-control" name="address" id="address" placeholder="1234 Main St" required="" required pattern="[A-Za-z0-9 ]+{2, 50}">
                <div class="invalid-feedback">
                  Please enter your shipping address.
                </div>
              </div>
      
              <!-- <div class="mb-3">
                <label for="address2">Address 2 <span class="text-muted">(Optional)</span></label>
                <input type="text" class="form-control" name="address2" id="address2" placeholder="Apartment or suite">
              </div> -->
              <div class="mb-3">
                <label for="address2">City</label>
                <input type="text" class="form-control" name="city" id="city" placeholder="Irvine">
              </div>
              
              <div class="row">
                <div class="col-md-5 mb-3">
                  <label for="country">Country</label>
                  <select class="custom-select d-block w-100" name="country" id="country" required="" >
                    <option value="">Choose...</option>
                    <option>United States</option>
                    <option>Canada</option>
                  </select>
                  <div class="invalid-feedback">
                    Please select a valid country.
                  </div>
                </div>
                <div class="col-md-4 mb-3">
                  <label for="state">State</label>
                  <input type="text" class="form-control" name="state" id="state" placeholder="CA" required="" required pattern="[A-Z]+{2}" onKeyUp="showStateSuggestions(this.value)">
                  <div id="stateResults"></div>
                  <!-- <select class="custom-select d-block w-100" name="state" id="state" required="">
                    <option value="">Choose...</option>
                    <option>California</option>
                    <option>New York</option>
                    <option>Texas</option>
                    <option>Massachusetts</option>
                    <option>Illinois</option>
                    <option>Michigan</option>
                  </select> -->
                  <div class="invalid-feedback">
                    Please provide a valid state.
                  </div>
                </div>
                <div class="col-md-3 mb-3">
                  <label for="zip">Zip</label>
                  <input type="text" class="form-control" name="zip" id="zip" placeholder="" required="" required pattern="\d{5}">
                  <div class="invalid-feedback">
                    Zip code required.
                  </div>
                </div>
              </div>
              <hr class="mb-4">
              <div id="outerDiv"></div>
      
              <h4 class="mb-3">Payment</h4>
      
              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="cc-name">Name on card</label>
                  <input type="text" class="form-control" name="ccName" id="cc-name" placeholder="" required="" required pattern="[a-zA-Z ]{2,32}">
                  <small class="text-muted">Full name as displayed on card</small>
                  <div class="invalid-feedback">
                    Name on card is required
                  </div>
                </div>
                <div class="col-md-6 mb-3">
                  <label for="cc-number">Credit card number</label>
                  <input type="text" class="form-control" name="ccNumber" id="cc-number" placeholder="1234-1234-1234-1234" required="" required pattern="\d{4}-\d{4}-\d{4}-\d{4}">
                  <div class="invalid-feedback">
                    Credit card number is required with the following style "1234-1234-1234-1234".
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-3 mb-3">
                  <label for="cc-expiration">Expiration</label>
                  <input type="text" class="form-control" name="ccExpiration" id="cc-expiration" placeholder="01/23" required="" required pattern="\d{2}/\d{2}">
                  <div class="invalid-feedback">
                    Expiration date required with the following style "01/23".
                  </div>
                </div>
                <div class="col-md-3 mb-3">
                  <label for="cc-cvv">CVV</label>
                  <input type="text" class="form-control" name="ccCvv" id="cc-cvv" placeholder="123" required=""  required pattern="\d{3}">
                  <div class="invalid-feedback">
                    Security code required with the following style "123".
                  </div>
                </div>
              </div>
              <hr class="mb-4">
              <input type="hidden" name="tax" id="taxValue" placeholder="" value="${total*0.1}">
              <input type="hidden" name="totalWithTax" id="totalWithTaxValue" placeholder="" value="${total + total*0.1}">


              <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
            </form>
          </div>
        </div>
      </div>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
      <script>
        (function () {
          'use strict'
      
          window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation')
      
            // Loop over them and prevent submission
            Array.prototype.filter.call(forms, function (form) {
              form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                  event.preventDefault()
                  event.stopPropagation()
                }
      
                form.classList.add('was-validated')
              }, false)
            })
          }, false)
        })()

        const header = document.querySelector(".navbar-header");
        window.addEventListener("scroll", () => {
        header.classList.toggle("sticky", window.scrollY > 0);});
      </script>

    <script>
      $(document).ready(function() {
        $("#zip").blur(function() {
          var zip = $(this).val();
          $.ajax({
            url: "zip?zip=" + zip + "&subtotal=" + String('${total}'),
            type: "GET",
            success: function(response) {
              console.log("This is json in console.log ", response)
              // var taxData = JSON.parse(response);
              var taxData = response;
              console.log(typeof response);
              $("#tax").html(taxData.tax);
              $("#taxValue").val(taxData.tax);
              $("#totalwithtax").html(taxData.total)
              $("#totalWithTaxValue").val(taxData.total)
              $("#city").val(taxData.city);
              $("#state").val(taxData.state);
              $("#country").val("United States").change();
              $("#outerDiv").html(taxData);
            }
          });
        });
      });

      function showStateSuggestions(val) {
        res = document.getElementById("stateResults");
        res.innerHTML = '';
        let list = '';
        if (val == '') {
          return;
        }
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "zip?q=" + val);
        xhr.responseType = 'json';
        xhr.onload = function() {
          if (xhr.status === 200) {
            console.log('response', xhr.response); // JSON response  
            const data = xhr.response.stateMatches;
            console.log(data)
            for (i=0; i<data.length; i++) {
                list += '<li>' + data[i] + '</li>';
          }
          res.innerHTML = '<ul>' + list + '</ul>';
          }
        };
       xhr.send(null);

      $('#stateResults').on('click', 'li', function() {
      var chosenOption = $(this).text();
      $('#state').val(chosenOption);
      $('#stateResults').empty();
  });

      }
    </script>
          </body>
      </html>