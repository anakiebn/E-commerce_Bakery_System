


<%@page import="za.co.groupA.Model.Order"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>To pie for</title>

        <style>
            /*body style*/
            body {
                     background: url('page/images/vanilla.jpg') no-repeat center center fixed;

                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
                font-family: Arial, Helvetica, sans-serif;
            }
            * {
                box-sizing: border-box;
            }
            
            a{
                color:white;
            }



        </style>
    </head>
    <body>
     

        <div class="container">
            <div class="card-header my-3">All Orders</div>
            <table class="table table-light">
                <thead>
                    <tr>
                        <th scope="col">Order ID</th>
                        <th scope="col">Date</th>
                        <th scope="col">Email</th>
                        <th scope="col">Amount</th>
                    </tr>
                </thead>
                <tbody>

                    <% List<Order> orderList = (List<Order>) request.getAttribute("orderList");
                      for (Order order : orderList) {%>
                    <tr>
                        <td><%=order.getOrderId()%></td>
                        <td><%=order.getOrderDate()%></td>
                        <td><%=order.getEmailAddress()%></td>
                        <td>R <%=order.getTotalAmount()%></td>
                        
                    </tr>
                    <%}  %>
                     <input type="submit" value="Make Payment" class="btn">
                  

                </tbody>
            </table>
        </div>

    </body>
</html>