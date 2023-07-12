<%-- 
    Document   : Payment
    Created on : Jun 12, 2023, 11:51:44 AM
    Author     : Train
--%>

<%@page import="java.util.HashMap"%>
<%@page import="za.co.groupA.Model.CartLineItem"%>
<%@page import="za.co.groupA.Model.CartLineItem"%>
<%@page import="java.util.Map"%>
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


    </body>
</html>

