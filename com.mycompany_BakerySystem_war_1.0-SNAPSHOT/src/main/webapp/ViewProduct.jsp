<%-- 
    Document   : ViewProduct
    Created on : Jun 15, 2023, 2:10:12 PM
    Author     : Train
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="za.co.groupA.Model.Product"%>
<%@page import="za.co.groupA.Model.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>

        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background: url('page/images/vanilla.jpg') no-repeat center center fixed;
                background-size: cover;
            }

            .notification {
                margin-bottom: 10px;
                padding: 10px;
                border-radius: 5px;
                font-weight: bold;
            }

            .success {
                background-color: #4CAF50;
                color: #fff;
            }

            .error {
                background-color: #FF5722;
                color: #fff;
            }

            .container {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100vh;
                position: relative;
                overflow: hidden; /* Prevent the content from overflowing the container */

            }

            .view-product-form {
                position: relative;
                z-index: 1;
                width: 1000px;
                padding: 20px;
                background-color: rgba(255, 255, 255, 0.8);
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                overflow-y: auto; /* Add scrolling functionality */
                max-height: 500px; /* Adjust the maximum height as needed */
            }

            .view-product-form button {
                width: 98%;
                padding: 10px;
                background-color: #ffff00;
                color: #333;
                border: none;
                border-radius: 3px;
                cursor: pointer;
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

            table {
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
            }


            .popup-overlay {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                display: flex;
                align-items: center;
                justify-content: center;
                z-index: 99;
            }

            .add-product-popup {
                background-color: #fff;
                width: 600px; /* Same width as the container */
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                position: absolute;
                top: 50%; /* Positions the pop-up at the vertical center */
                left: 50%; /* Positions the pop-up at the horizontal center */
                transform: translate(-50%, -50%); /* Centers the pop-up */
            }

            /*    .search-container {
                position: absolute;
                top: 10px;
                right: 10px;
                display: flex;
                align-items: center;
            }*/

            .search-bar input[type="text"] {
                padding: 5px;
                border-radius: 3px;
                border: 1px solid #ccc;
            }

            .search-bar button {
                margin-left: 5px;
                padding: 5px 10px;
                background-color: #ffff00;
                color: #333;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }
            .search-bar {
                position: absolute;
                top: 20px; /* Adjust the top value as needed */
                right: 20px; /* Adjust the right value as needed */
                z-index: 1; /* Ensure the search bar is positioned above the blur overlay */
            }
            .image{
                width: 70px;
                height: 70px;
            }
            a{
                text-decoration: none;
                color: black;
            }

        </style>
    </head>
    <body>
        <div class="container">



            <div class="view-product-form">
                <h3>My Products</h3>
                <div class="search-bar">
                    <input type="text" id="searchInput" placeholder="Search Product" oninput="searchProduct()">
                </div>
                <p>


                    <%
                        List<Product> proList = (ArrayList<Product>) request.getAttribute("allproduct");
                    %>

                <table>
                     <a href=http://localhost:8080/BakerySystem/GetCategoryMenuServlet?action=get>
                    <button type="submit">Back to Menu</button> 
                </a>
                    <tr>
                        <th>Product Picture</th>
                        <th>Product Name</th>
                        <th>Product Price</th>
                        <th>Product Description</th>
                        <th>Product Nutrients</th>
                        <th>Product Warnings</th>
                        <th>is Product Active?</th>
                        <th></th>
                        <th> <button><a href="http://localhost:8080/BakerySystem/addProductServlet?action=get">Add Product</a></button></th>
                    </tr>

                     <% for (Product product : proList) {%>
                    <tr>
                        <td> <img src=" page/images/<%= product.getImage()%>" class="image"> </td>
                        <td><%= product.getProductName()%></td>
                        <td><%= product.getPrice()%></td>
                        <td><%= product.getProductDescription()%></td>
                        <td><%= product.getProductNutrientsInfo()%></td>
                        <td><%= product.getProductWarnings()%></td>
                        <td><%= product.isIsAvailable()%></td>
                        
                        <th> <button><a href="http://localhost:8080/BakerySystem/UpdateProductServlet?productId=<%=product.getProductId()%>">Update Product</a></button></th>
                        <%if (product.isIsAvailable()) {%>
                        <td><button onclick="restoreProduct('<%= product.getProductId()%>')">Deactivate</button></td>
                        <%} else {%>
                        <td><button onclick="deleteProduct('<%= product.getProductId()%>')">Activate</button></td>
                        <%}%> 

                    </tr>

                    <% }%>

                </table>
                </p>
<!--                <a href=http://localhost:8080/BakerySystem/GetCategoryMenuServlet?action=get>
                    <button type="submit">Back to Menu</button> 
                </a>-->

            </div>
            <div class="blur-overlay"></div>
        </div>

        <div id="addProductPopup" class="popup-overlay" style="display: none;">
            <div class="add-product-popup">
                <input type="text" id="newProductName" placeholder="New Product Name">
                <button onclick="addProduct()">Add</button>
                <button onclick="hideAddProductPopup()">Cancel</button>
            </div>
        </div>


        <script  src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
                    function deleteProduct(productId) {
                        var confirmed = confirm("Are you sure you want to activate this product?");

                        if (confirmed) {
                           
                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", "GetProductServlet", true);
                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    console.log("product deleted successfully");
                                    location.reload(); // Reload the page or update the category list
                                } else if (xhr.readyState === 4) {
                                    console.error("Error deleting product:", xhr.statusText);
                                    // Display an error message or handle the error as needed
                                }
                            };
                            xhr.send("productId=" + encodeURIComponent(productId));
                        }
                    }


                    function restoreProduct(productId) {
                        var confirmed = confirm("Are you sure you want to deactivate this product?");

                        if (confirmed) {
                              var xhr = new XMLHttpRequest();
                            xhr.open("POST", "GetProductServlet", true);
                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    console.log("Product deleted successfully");
                                    location.reload(); // Reload the page or update the category list
                                } else if (xhr.readyState === 4) {
                                    console.error("Error deleting product:", xhr.statusText);
                                    // Display an error message or handle the error as needed
                                }
                            };
                            xhr.send("productId=" + encodeURIComponent(productId));
                        }
                    }

                    function updateProduct(productId, currentName) {
                        var newName = prompt("Enter the new product name:", currentName);

                        if (newName) {
                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", "UpdateProductServlet", true);
                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    console.log("Product updated successfully");
                                    location.reload(); // Reload the page or update the category list
                                } else if (xhr.readyState === 4) {
                                    console.error("Error updating product:", xhr.statusText);
                                    // Display an error message or handle the error as needed
                                }
                            };
                            xhr.send("productId=" + encodeURIComponent(productId) + "&ProductName=" + encodeURIComponent(newName));
                        }
                    }

                    function showAddProductPopup() {
                        var popup = document.getElementById("addProductPopup");
                        popup.style.display = "block";
                    }

                    function hideAddProductPopup() {
                        var popup = document.getElementById("addProductPopup");
                        popup.style.display = "none";
                    }


                      function searchProduct() {
                        var searchInput = document.getElementById("searchInput");
                        var searchTerm = searchInput.value.toLowerCase();

                        // Get all the table rows
                        var rows = document.getElementsByTagName("tr");

                        // Loop through the rows and hide/show based on the search term
                        for (var i = 1; i < rows.length; i++) { // Start from index 1 to exclude the table header row
                            var productName = rows[i].getElementsByTagName("td")[1].textContent.toLowerCase();

                            if (productName.includes(searchTerm)) {
                                rows[i].style.display = ""; // Show the row
                            } else {
                                rows[i].style.display = "none"; // Hide the row
                            }
                        }
                    }
        </script>
    </body>
</html>

