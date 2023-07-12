<%-- 
    Document   : newMenu
    Created on : Jun 5, 2023, 9:04:44 AM
    Author     : Train
--%>

<%@page import="java.util.stream.Collectors"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.HashMap"%>
<%@page import="za.co.groupA.Model.CartLineItem"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="za.co.groupA.Model.User"%>
<%@page import="za.co.groupA.Model.Product"%>
<%@page import="za.co.groupA.Model.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    DecimalFormat df = new DecimalFormat("#0.00");
    Map<Integer, CartLineItem> viewCart = (HashMap<Integer, CartLineItem>) session.getAttribute("viewCart");
    System.out.println(viewCart);
    if (viewCart != null) {
        request.setAttribute("viewCart", viewCart);
    } %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Menu</title>
        <!--  <link rel="stylesheet" href="cart/css/style.css">-->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <link rel="stylesheet" href="https://fonts.google.com/icons?selected=Material%20Icons%20Outlined%3Acategory%3A">
        <link href="https://fontawesome.com/icons/cupcake?f=classic&s=solid" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700;800;900&display=swap');
            @import url('../node_modules/@fortawesome/fontawesome-svg-core/styles.css');
            *{
                font-family: 'Poppins', sans-serif;
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body{
                background: url('page/images/vanilla.jpg') no-repeat center center fixed;
            }

            header{
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                z-index: 99;
                background-color: khaki;
            }

            .nav{
                max-width: 1200px;
                margin: auto;
                width: 100%;
                height: 50px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                text-align: center;
            }
            ul {
                list-style-type: none;
            }

            .centered {
                display: inline-block;
                margin: 0 10px;
            }

            .logo{
                font-size: 1.1rem;
                font-weight: 500;
                text-decoration: none;
                text-transform: uppercase;
                color: black;
            }
            .home{
                font-size: 1.1rem;
                font-weight: 500;
                text-decoration: none;
                text-transform: uppercase;
                color: #333;
            }

            .box{
                color:white;
                width: 30px;
                height: 30px;
                text-align: center;
                position: relative;
            }

            .cart-count{
                position: absolute;
                background-color: #2f3542;
                top: -5px;
                right: 0;
                padding: 3px;
                height: 20px;
                width: 20px;
                line-height:20px ;
                border-radius: 50%;
                z-index: 99;
            }

            #cart-icon{
                font-size: 1.5rem;
                cursor: pointer;
                padding-top: 10px;
                color: black;
            }

            img{
                width: 100%;
            }

            .container{
                max-width: 1200px;
                padding: 4rem;
                margin: auto;
                width: 100%;
            }

            h2.title{
                font-size: 1.1rem;
                font-weight: 500;
                margin-bottom: 1.5rem;
                color:black;
                text-align:center;

            }


            .shop-content{
                display: grid;
                grid-template-columns: repeat(auto-fit,minmax(220px,auto));
                gap:1.5rem;
                justify-content: center;
                align-content: center;
            }

            .food-box{
                position: relative;
                background-color: khaki;
                padding: 10px;
                box-shadow: 0 1px 4px rgba(40, 37, 37, 0.1);
                border-radius: 3px;
            }

            .pic {
                overflow: hidden;
            }

            .image-container {
                position: relative;
                width: 235px;
                height: 300px;
            }

            .overlay {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 78%;
                background-color: rgba(0, 0, 0, 0.7);
                color: white;
                display: none;
                align-items: center;
                justify-content: center;
                text-align: center;
                transition: opacity 0.4s;
            }

            .pic:hover .overlay {
                display: flex;
            }

            .pic:hover img {
                transform: rotateY(180deg);
                transition: transform 0.4s;
            }

            .pic img.flip {
                transform: rotateY(180deg);
            }

            .food-img{
                transition: 5.0s;
                aspect-ratio: 1/1;
                object-fit: cover;
            }

            .food-title{
                font-size: 1rem;
                font-weight: 600;
                color:black;
            }

            .food-price{
                font-weight:500 ;
            }

            .add-cart{
                position: absolute;
                bottom: 0;
                right: 0;
                background-color: black;
                color:khaki;
                padding: 10px;
                font-size: 1.1rem;
                cursor: pointer;
                transition: 0.5s;
            }

            .add-cart:hover{
                background-color:rgba(255, 0, 0, 0.786);
            }


            .cart{
                position: fixed;
                top: 0;
                right: -100%;
                width: 400px;
                height: 100vh;
                overflow-y: auto;
                overflow-x: hidden;
                padding: 20px;
                background-color: khaki;
                box-shadow: 0 1px 4px rgba(40, 37, 37, 0.1);
                z-index: 100;
            }

            .cart-active{
                right:0;
                transition: 0.5s;
            }

            .cart-title{
                text-align: center;
                font-size: 1.5rem;
                font-weight: 500;
                margin-bottom: 1rem;
                padding-bottom:20px ;
                border-bottom: 1px solid rgba(0,0,0,0.1);
                text-transform:uppercase;
            }

            .cart-box{
                display: grid;
                grid-template-columns: 32% 50% 18%;
                align-items: center;
                gap: 1rem;
                margin-top: 1rem;
                border-bottom: 1px solid rgba(0,0,0,0.1);
                padding-bottom: 10px;
            }

            .cart-img{
                width: 75px;
                height: 75px;
                object-fit: cover;
                border:2px solid  rgba(0,0,0,0.1);
                padding: 5px;
            }
            .detail-box{
                display: grid;
                row-gap: 0.5rem;
            }

            .price-box{
                display: flex;
                justify-content: space-between;
            }

            .cart-food-title{
                font-size: 1rem;
                text-transform: uppercase;
                color:#ff6348;
                font-weight: 500;
                text-align: left;
            }

            .cart-price{
                font-weight: 500;
            }

            .cart-quantity{
                border:1px solid rgba(0,0,0,0.1);
                outline:none ;
                width: 2.4rem;
                text-align: center;
                font-size: 1rem;
            }

            .cart-remove{
                font-size: 24px;
                color:red;
                cursor: pointer;
            }

            .total{
                display: flex;
                justify-content: flex-end;
                margin-top: 1.5rem;
            }

            .total-title{
                font-size: 1rem;
                font-weight: 600;
            }

            .total-price{
                margin-left: 0.5rem;
            }

            .btn-buy{
                padding: 12px 20px;
                background-color:black;
                color:#fff;
                border: none;
                font-size: 1rem;
                font-weight: 500;
                cursor: pointer;
            }
            .btn-product-info{
                padding: 6px 10px;
                background-color:black;
                color:#fff;
                border: none;
                font-size: 0.5rem;
                align-content: flex-end;
                font-weight: 100;
                cursor: pointer;
            }

            #cart-close{
                position: absolute;
                top: 1rem;
                right: 0.8rem;
                font-size: 2rem;
                cursor: pointer;
            }
            /* Style for the dropdown button */
            .dropbtn {
                display: inline-block;
                text-decoration: none;
                font-size: 1rem;
                font-weight: 500;
                color: black;
                padding: 12px 16px;
                text-transform:uppercase;
            }

            /* Style for the dropdown container */
            .dropdown-content {
                display: none;
                position: absolute;
                background-color: khaki;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            /* Style for the dropdown links */
            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            /* Style for the dropdown links on hover */
            .dropdown-content a:hover {
                background-color: #f1f1f1;
            }

            /* Show the dropdown menu when hovering over the dropdown button */
            .dropdown:hover .dropdown-content {
                display: block;
            }

            .logo,
            .dropbtn {
                font-family: 'Poppins', sans-serif;
            }
            /* Style for the dropdown button */
            .dropdowns {
                position: relative;
                display: inline-block;
                cursor: pointer;
            }

            /* Style for the dropdown container */
            .dropdown-contents {
                display: none;
                position: absolute;
                background-color: khaki;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
                z-index: 1;
            }

            /* Style for the dropdown links */
            .dropdown-contents a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            /* Style for the dropdown links on hover */
            .dropdown-contents a:hover {
                background-color: #f1f1f1;
            }

            /* Show the dropdown menu when hovering over the dropdown button */
            .dropdowns:hover .dropdown-contents {
                display: block;
            }

            .btn {
                display: inline-block;
                width: 30px;
                height: 30px;
                background-color: #000000;
                border: none;
                color: #fff;
                text-align: center;
                cursor: pointer;
                font-size: 18px;
            }

            .plus {
                border-radius: 50%;
            }

            .minus {
                border-radius: 50%;
            }
            a{
                text-decoration: none;
            }

            /* Popup container - can be anything you want */
            .popup {
                position: relative;
                display: inline-block;
                cursor: pointer;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            /* The actual popup */
            .popup .popuptext {
                visibility: hidden;
                width: 160px;
                background-color: #555;
                color: #fff;
                text-align: center;
                border-radius: 6px;
                padding: 8px 0;
                position: absolute;
                z-index: 1;
                bottom: 125%;
                left: 50%;
                margin-left: -80px;
            }

            /* Popup arrow */
            .popup .popuptext::after {
                content: "";
                position: absolute;
                top: 100%;
                left: 50%;
                margin-left: -5px;
                border-width: 5px;
                border-style: solid;
                border-color: #555 transparent transparent transparent;
            }

            /* Toggle this class - hide and show the popup */
            .popup .show {
                visibility: visible;
                -webkit-animation: fadeIn 1s;
                animation: fadeIn 1s;
            }

            /* Add animation (fade in the popup) */
            @-webkit-keyframes fadeIn {
                from {
                    opacity: 0;
                }
                to {
                    opacity: 1;
                }
            }

            @keyframes fadeIn {
                from {
                    opacity: 0;
                }
                to {
                    opacity:1 ;
                }
            }


        </style>

    </head>
    <body>
        <header>
            <div class="nav">
                <div> 
                    <a href="#" class="logo">
                        TO Pie For
                        <ion-icon name="storefront-outline"></ion-icon>

                    </a>
                </div>
                <ul>
                    <li><a href=http://localhost:8080/BakerySystem class="home">Home</a> <ion-icon name="home"></ion-icon></li>
                </ul> 
                <ul>
                    <li class="dropdown">
                        <a href="#" class="dropbtn" class="centered">Shop by Category</a>
                        <span class="material-icons-outlined"></span>
                        <div class="dropdown-content">
                            <%
                                String catName;
                                int catId;
                                List<Category> catList = (List<Category>) request.getAttribute("menuCate");
                                for (Category unit : catList) {
                                    catName = unit.getCategoryName();
                                    catId = unit.getCategoryId();

                            %>  
                            <a  href="http://localhost:8080/BakerySystem/GetCategoryMenuServlet?cat=<%=catId%>"< value="<%=catId%>" ><%=catName%></a>  
                            <%}%>
                        </div>
                    </li>
                </ul>
                <%
                    User user = (User) request.getSession().getAttribute("user");

                    if (user != null && user.getRoleId() == 1) {%>
                <ul>
                    <li class="dropdown">
                        <a href="#" class="dropbtn" class="centered" >Management</a><ion-icon name="settings"></ion-icon>
                    <div class="dropdown-content">
                        <a href="http://localhost:8080/BakerySystem/addControllerServlet">Categories</a>
                        <a href="http://localhost:8080/BakerySystem/GetIngredientServlet">Ingredients</a>
                        <a href="http://localhost:8080/BakerySystem/GetRecipeServlet">Recipes</a>
                        <a href="http://localhost:8080/BakerySystem/GetProductServlet?action=get">Products</a> 
                        <a href="http://localhost:8080/BakerySystem/AddUnit.jsp">Add Unit</a>
                        <a href="http://localhost:8080/BakerySystem/AddUnitServlet">View Unit</a>
                        <a href="http://localhost:8080/BakerySystem/GetOrdersServlet?type=management">Orders</a>


                    </div>
                    </li>
                </ul> 
                <ul>
                    <li class="dropdown"> 

                        <a href="#" class="dropbtn" class="centered" >Reports</a> <ion-icon name="document-text-outline"></ion-icon>

                    <div class="dropdown-content">
                        <a href="http://localhost:8080/BakerySystem/GetOrdersServlet?type=report"> Orders</a>
                        <a href="">Users</a>
                        <a href="">Products</a>
                        <a href="">Ingredients</a>


                    </div>
                    </li>
                </ul>

                <% } %>   
                <ul>
                    <li class="dropdown"> 


                        <a href="#" class="dropbtn" class="centered"  >Track_Orders</a> <ion-icon name="locate-outline"></ion-icon>


                    <div class="dropdown-content">
                        <a href="http://localhost:8080/BakerySystem/TrackOrderSerlet?action=get">Orders</a>



                    </div>
                    </li>
                </ul>

                <div class="dropdowns">

                    <%if (user == null) {%>
                    <a href="Login.jsp" class="fa fa-user-circle" style="font-size:20px; color: black"> login</a>
                    <%} else {%>
                    <i class="fa fa-user-circle" style="font-size:20px; color: black">  <%=user.getUserName()%></i>             
                    <%}%>

                    <div class="dropdown-contents">
                        <% if (user != null) { %>  
                        <a href="http://localhost:8080/BakerySystem/LogoutServlet">logout</a>
                        <% }%>
                    </div>

                </div>

                <div class="box">
                    <% if (true) { %>
                    <% if (viewCart != null && !viewCart.isEmpty()) {%>
                    <%
                        int count = 0;
                        for (int key : viewCart.keySet()) {
                            count += viewCart.get(key).getProductQty();
                        }
                    %>

                    <div class="cart-count"><%=count%></div>
                    <ion-icon name="cart"  id="cart-icon" ></ion-icon>
                        <%}
                            }%>
                </div>
                <!--<form action="AddressFetcherServlet" method="GET">-->

                <div class="cart">
                    <div class="cart-title">Cart Items</div>
                    <%int total = 0;%>
                    <%if (viewCart != null) {%>


                    <% for (int key : viewCart.keySet()) {

                            total += viewCart.get(key).getProductQty() * viewCart.get(key).getProduct().getPrice();

                    %>

                    <div class="cart-c ontent">
                        <div class="cart-box">
                            <img src=" page/images/<%=viewCart.get(key).getProduct().getImage()%>" class="cart-img">
                            <div class="detail-box">
                                <div class="cart-food-title" style="text-align: left;"><%=viewCart.get(key).getProduct().getProductName()%></div>
                                <div class="price-box">
                                    <div class="cart-price">R<%=df.format(viewCart.get(key).getProduct().getPrice())%></div>

                                </div>

                                <a href="QuantityServlet?ac=plus&key=<%=key%>" class="btn btn-secondary" >+</a>
                                <span id="count"> <%=viewCart.get(key).getProductQty()%></span>
                                <a href="QuantityServlet?ac=minus&key=<%=key%> "class="btn btn-secondary" >-</a>
                            </div>

                            <a href="TheDeleteBinServlet?key=<%=key%>" ><ion-icon name="trash" class="cart-remove"></ion-icon></a>


                        </div>
                    </div>
                    <% }

                        }%>

                    <div class="total">


                        <div class="total-title">Total</div>
                        <div class="total-price">R<%=df.format(total)%>></div>
                    </div>

                    <!--<input type="submit" name="placeOrderBtn" value="order" class="btn-buy"  >Place Order-->

                    <% if (user != null) {%>
                    <a  id="placeOrderBtn" class="btn-buy" href="Payment.jsp">Place Order</a>
                    <%} else {%>
                    <!--<p style="color:Tomato;">login first</p>-->
                    <br>
                    <a  id="placeOrderBtn" class="btn-buy" href="Login.jsp">Place Order</a>
                    <%}%>
                    <ion-icon name="close" id="cart-close"></ion-icon>

                </div>
            </div>
            <!--</form>-->

        </header>
        <div class="container">
            <h2 class="title">Discover The Best Bakery Ever <i class="fa-solid fa-cupcake"></i></h2> 


            <div class="shop-content">
                <%
                    List<Product> productList = (ArrayList<Product>) request.getAttribute("menuAllProducts");

                    for (Product product : productList) {
                %>
                <div class="food-box">


                    <div class="pic">
                        <div class="image-container">
                            <form action="AddToCartServlet" method="GET">
                                <img src=" page/images/<%= product.getImage()%>" class="food-img">
                                <h2 class="food-title"><%= product.getProductName()%></h2>
                                <span class="food-price">R<%= df.format(product.getPrice())%></span>

                                <div class="popup" onclick="myFunction()">
                                    <button class="btn-product-info" data-popup="popup1">Product Info</button>
                                    <span class="popuptext" id="myPopup">A Simple Popup!</span>
                                </div>
                                

                                <input type="hidden" name="productID" value="<%=product.getProductId()%>">
                                <% if (true) { %>
                                <button> <ion-icon name="cart" class="add-cart"></ion-icon></button>
                                        <% }%>
                                <div class="overlay">
                                    <p class="details"><%=  product.getProductDescription()%></p>

                                </div></form>
                        </div>

                    </div>
                </div>
                <% }%>
            </div>

        </div>
        <script src="cart/js/script.js"></script>
    </body>
</html>
<script>
    <%
        if (viewCart != null) {
    %>
                                    var cartItems = '<%=viewCart.size()%>';
                                    var open = '<%=session.getAttribute("open")%>';
                                    if (open !== 'null' && open !== '' && cartItems !== '0') {
                                        console.log(open);
                                        const btnCart = document.querySelector('#cart-icon');
                                        cart.classList.add('cart-active');

                                    }<%}%>
</script>

<script>
// When the user clicks on div, open the popup
    function myFunction() {
        var popup = document.getElementById("myPopup");
        popup.classList.toggle("show");
    }
</script>


<script>
    // Get all the buttons with the class "btn-product-info"
    var buttons = document.querySelectorAll('.btn-product-info');

    // Loop through each button and add a click event listener
    buttons.forEach(function (button) {
        button.addEventListener('click', function (event) {
            event.stopPropagation(); // Prevent click event propagation to the cart button

            // Get the data attribute value of the clicked button
            var popupId = this.getAttribute('data-popup');

            // Get the corresponding pop-up element
            var popup = document.getElementById(popupId);

            // Toggle the visibility of the pop-up
            popup.classList.toggle('show');
        });
    });
</script>
