<%-- 
    Document   : AddAddress
    Created on : Jun 1, 2023, 1:18:24 PM
    Author     : Train
--%>

<%@page import="za.co.groupA.Model.User"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background: url('page/images/vanilla.jpg') no-repeat center center fixed;
                background-size: cover;
            }

            .container {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100vh;
                position: relative;
            }

            .address-form {
                position: relative;
                z-index: 1;
                width: 600px;
                padding: 20px;
                background-color: rgba(255, 255, 255, 0.8);
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .address-form input[type="text"],
            .address-form input[type="password"],
            .address-form input[type="number"]{
                width: 95%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            .address-form button {
                width: 98%;
                padding: 10px;
                background-color: #ffff00;
                color: #333;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }

            .register-link {
                text-align: center;
                margin-top: 10px;
            }

            .blur-overlay {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                backdrop-filter: blur(5px);
                -webkit-backdrop-filter: blur(5px);
                z-index: 0;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form class="address-form" action="AddAddressServlet" method="POST">
                <%
                    User user =(User)session.getAttribute("user");
                %>
                <h2>Hi <%=user.getUserName()%>, please add your Address</h2> 

                <label for="houseNumber">House Number:</label>
                <input type="number" id="houseNumber" name="houseNumber" required>
                <br><br>

                <label for="streetName">Street Name:</label>
                <input type="text" id="streetName" name="streetName" required>
                <br><br>

                <label for="town">Town:</label>
                <input type="text" id="town" name="town" required>
                <br><br>

                <label for="postalCode">Postal Code:</label>
                <input type="text" id="postalCode" name="postalCode" required>
                <br><br>

                <button type="submit"> Add Address</button>

            </form>
            <div class="blur-overlay"></div>
        </div>
    </body>

</html>
