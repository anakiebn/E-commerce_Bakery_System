<%-- 
    Document   : Payment
    Created on : Jun 12, 2023, 11:51:44 AM
    Author     : Train
--%>

<%@page import="za.co.groupA.DaoImpl.AddressDaoImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="za.co.groupA.Model.User"%>
<%@page import="za.co.groupA.ServiceImpl.AddressServiceImpl"%>
<%@page import="za.co.groupA.Service.AddressService"%>
<%@page import="za.co.groupA.Service.AddressService"%>
<%@page import="za.co.groupA.Dao.DBPoolManagerBasic"%>
<%@page import="za.co.groupA.Model.Address"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            body {
                font-family: Arial;
                font-size: 17px;
                padding: 8px;
                background: transparent;
            }

            * {
                box-sizing: border-box;
            }
            .blur-overlay {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                z-index: -1;
                background: url('page/images/vanilla.jpg') no-repeat center center fixed;
                background-size: cover;
                filter: blur(5px);
                -webkit-filter: blur(5px);
            }

            .row {
                display: -ms-flexbox; /* IE10 */
                display: flex;
                -ms-flex-wrap: wrap; /* IE10 */
                flex-wrap: wrap;
                margin: 0 -16px;
            }

            .col-25 {
                -ms-flex: 25%; /* IE10 */
                flex: 25%;
            }

            .col-50 {
                -ms-flex: 50%; /* IE10 */
                flex: 50%;
            }

            .col-75 {
                -ms-flex: 75%; /* IE10 */
                flex: 75%;
            }

            .col-25,
            .col-50,
            .col-75 {
                padding: 0 16px;
            }

            .container {
                background-color: rgba(255, 255, 255, 0.4);
                padding: 5px 20px 15px 20px;
                border: 1px solid lightgrey;
                border-radius: 3px;
            }

            input[type=text] {
                width: 100%;
                margin-bottom: 20px;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            label {
                margin-bottom: 10px;
                display: block;

            }

            .icon-container {
                margin-bottom: 20px;
                padding: 7px 0;
                font-size: 24px;
                background-color: yellow;
            }

            .btn {
                background-color: #04AA6D;
                color: white;
                padding: 12px;
                margin: 10px 0;
                border: none;
                width: 100%;
                border-radius: 3px;
                cursor: pointer;
                font-size: 17px;
            }

            .btn:hover {
                background-color: #45a049;
            }

            a {
                color: #2196F3;
            }

            hr {
                border: 1px solid lightgrey;
            }

            span.price {
                float: right;
                color: grey;
            }
            .centered-button {
                display: flex;
                justify-content: center;
                align-items: center;
                width:50%;
                height: 40px;
                color: white;
                font-size: 17px;

            }

            /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
            @media (max-width: 800px) {
                .row {
                    flex-direction: column-reverse;
                }
                .col-25 {
                    margin-bottom: 20px;
                }
            }



        </style>
    </head>
    <body>

        <a href="GetCategoryMenuServlet"  > &larr; Back to Menu</a>

        <h2>Checkout</h2>
        <p>Your Order will be delivered in less than 48 hours</p>
        <div class="row">
            <div class="col-75">
                <div class="container">
                    <form  action="PaymentServlet" method="POST">

                        <div class="row">
                            <div class="col-50">
                                <h3>Confirm Details</h3>

                                <label for="fname"><i class="fa fa-user"></i> Full Name</label>
                                <input type="text" id="fname" name="firstname" value=${user.userName}>
                                <label for="email"><i class="fa fa-envelope"></i> Email</label>
                                <input type="text" id="email" name="email" value=${user.emailAddress}>
                                <label for="Address"> <i class="fa fa-truck" style="font-size:25px;color:black"></i> Choose Address   </label>
                                <select  name="Address" id="Address" style="width:100%; height: 40px">

                                    <%
                                        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
                                        User user = (User) request.getSession().getAttribute("user");
//                                        db.getConnection().prepareStatement("SELECT * FROM `bakerysystem`.`address` LIMIT 1000 ");

                                        AddressService as = new AddressServiceImpl(AddressDaoImpl.getInstance(db));
                                        List<Address> loa = null;
                                        if (user != null) {

                                            loa = as.getAddressByUserEmail(user.getEmailAddress());
//                                           
                                        } else {
                                            System.out.println("dsdsds");
                                        }

                                    %>

                                    <%for (Address address : loa) {%>
                                    <div class="row">
                                        <div class="col-50">
                                            <option value=<%= address.getAddressId()%>><%= address.getStreetName()%>, <%= address.getHouseNumber()%>, <%= address.getTown()%>, <%= address.getPostalCode()%></option>
                                        </div>

                                    </div>

                                    <%}%>

                                </select>

                                <div class="row">
                                    <div class="col-50">

                                        <div class="centered-button">
                                            <a  href="PaymentAddAddress.jsp">Add Address</a>
                                        </div>

                                    </div>
                                </div>


                            </div>

                            <div class="col-50">
                                <h3>Payment</h3>
                                <label for="fname">Accepted Cards</label>
                                <div class="icon-container">
                                    <i></i>
                                    <i class="fa fa-cc-visa" style="color:navy;"></i>
                                    <i class="fa fa-cc-amex" style="color:blue;"></i>
                                    <i class="fa fa-cc-mastercard" style="color:red;"></i>
                                    <i class="fa fa-cc-discover" style="color:orange;"></i>
                                </div>
                                <label for="cname">Name on Card</label>
                                <input type="text" id="cname" name="cardname" placeholder="Card_Name">
                                <label for="ccnum">Credit card number</label>
                                <input type="text" id="ccnum" name="cardnumber" placeholder="Card_Number">
                                <label for="expmonth">Exp Month</label>
                                <input type="text" id="expmonth" name="expmonth" placeholder="Exp_Month">
                                <div class="row">
                                    <div class="col-50">
                                        <label for="expyear">Exp Year</label>
                                        <input type="text" id="expyear" name="expyear" placeholder="Exp_Year">
                                    </div>
                                    <div class="col-50">
                                        <label for="cvv">CVV</label>
                                        <input type="text" id="cvv" name="cvv" placeholder="Cvv">
                                    </div>
                                </div>
                            </div>

                        </div>

                        <input type="submit" value="Make Payment" class="btn">
                    </form>
                    <div class="blur-overlay"></div>
                </div>  
            </div>

        </div>


    </body>
</html>

