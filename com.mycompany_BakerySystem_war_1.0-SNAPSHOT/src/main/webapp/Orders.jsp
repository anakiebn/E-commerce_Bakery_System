<%-- 
    Document   : ViewOrder
    Created on : May 29, 2023, 10:27:06 AM
    Author     : Train
--%>

<%@page import="za.co.groupA.Service.StatusService"%>
<%@page import="za.co.groupA.Model.Status"%>
<%@page import="java.util.ArrayList"%>
<%@page import="za.co.groupA.Model.Order"%>
<%@page import="java.util.List"%>
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

            .dropbtn {
                background-color: #298066;
                color: black;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropbtn:hover, .dropbtn:focus {
                background-color:#298066;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                overflow: auto;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown a:hover {
                background-color: #ddd;
            }

            .show {
                display: block;
            }

        </style>
    </head>

    <body>
        <div class="container">

            <a href=http://localhost:8080/BakerySystem/GetCategoryMenuServlet?action=get>
                <button type="submit">Back to Menu</button> 
            </a>
            <div class="card-header my-3">Orders</div>


            <div class="search-bar">
                <input type="text" id="searchInput" placeholder="Search Order" oninput="searchOrder()">
            </div>

            <table  class="table table-light">
                <thead>
                    <tr>
                        <th  scope="col">Order Number</th>
                        <th scope="col">User</th>
                        <th scope="col">Date</th>
                        <th scope="col">Time</th>
                        <th scope="col">Status</th>
                        <th scope="col">Total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Order> ol = (ArrayList<Order>) request.getAttribute("orderList");
                        StatusService ss = (StatusService) request.getAttribute("ss");
                        List<Status> sl = (ArrayList<Status>) request.getAttribute("status");
                        int dropdownCounter = 1;
                        for (Order order : ol) {%>
                    <tr>

                        <td><%=order.getOrderId()%></td>
                        <td><%=order.getEmailAddress()%></td>
                        <td><%=order.getOrderDate()%></td>
                        <td><%=order.getOrderTime()%></td>
                        <td><%=ss.getStatus(order.getStatus()).getStatusDescr()%></td>
                        <td><%=order.getTotalAmount()%></td>
                        <td>
                <li class="dropdown">
                    <button onclick="myFunction(<%= dropdownCounter%>)" class="dropbtn">Status</button>
                    <div id="myDropdown<%= dropdownCounter%>" class="dropdown-content">
                        <% for (Status status : sl) {%>
                        <a class="dropbtn" href="ChangeOrderStatusServlet?statusId=<%= status.getStatusId()%>&orderId=<%= order.getOrderId()%>">
                            <%= status.getStatusDescr()%>
                        </a>
                        <% } %>
                    </div>
                </li>
                </td>

                </tr>

                <%
                        dropdownCounter++;
                    }%>
                </tbody>

            </table>
        </p>

    </div>
    <div class="blur-overlay"></div>
</div>

<div id="addOrderPopup" class="popup-overlay" style="display: none;">
    <div class="add-order-popup">
        <input type="text" id="newOrderName" placeholder="New Order Name">
        <button onclick="addOrder()">Add</button>
        <button onclick="hideAddOrderPopup()">Cancel</button>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
            /* When the user clicks on the button, 
             toggle between hiding and showing the dropdown content */
            function myFunction(dropdownCounter) {
                var dropdown = document.getElementById("myDropdown" + dropdownCounter);
                dropdown.classList.toggle("show");
            }



// Close the dropdown if the user clicks outside of it
            window.onclick = function (event) {
                if (!event.target.matches('.dropbtn')) {
                    var dropdowns = document.getElementsByClassName("dropdown-content");
                    var i;
                    for (i = 0; i < dropdowns.length; i++) {
                        var openDropdown = dropdowns[i];
                        if (openDropdown.classList.contains('show')) {
                            openDropdown.classList.remove('show');
                        }
                    }
                }
            }
</script>


<script>
    function showAddOrderPopup() {
        var popup = document.getElementById("addOrderPopup");
        popup.style.display = "block";
    }

    function hideAddOrderPopup() {
        var popup = document.getElementById("addOrderPopup");
        popup.style.display = "none";
    }

    function searchOrder() {
        var searchInput = document.getElementById("searchInput");
        var searchTerm = searchInput.value.toLowerCase();

        // Get all the table rows
        var rows = document.getElementsByTagName("tr");

        // Loop through the rows and hide/show based on the search term
        for (var i = 1; i < rows.length; i++) { // Start from index 1 to exclude the table header row
            var orderName = rows[i].getElementsByTagName("td")[0].textContent.toLowerCase();

            if (orderName.includes(searchTerm)) {
                rows[i].style.display = ""; // Show the row
            } else {
                rows[i].style.display = "none"; // Hide the row
            }
        }
    }


</script>
</body>
</html>
