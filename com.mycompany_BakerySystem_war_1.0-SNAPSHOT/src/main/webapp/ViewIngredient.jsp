<%-- 
    Document   : ViewIngredient
    Created on : Jun 2, 2023, 1:20:09 PM
    Author     : Train
--%>

<%@page import="za.co.groupA.ServiceImpl.UnitServiceImpl"%>
<%@page import="za.co.groupA.DaoImpl.UnitDaoImpl"%>
<%@page import="za.co.groupA.Service.UnitService"%>
<%@page import="za.co.groupA.Dao.DBPoolManagerBasic"%>
<%@page import="java.util.ArrayList"%>
<%@page import="za.co.groupA.Model.Ingredient"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%

            UnitService us = (UnitServiceImpl) request.getAttribute("us");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Ingredient</title>

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
                position:relative;
                overflow: hidden; /* Prevent the content from overflowing the container */

            }

            .view-category-form {
                position: relative;
                z-index: 1;
                width: 600px;
                padding: 20px;
                background-color: rgba(255, 255, 255, 0.8);
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                overflow-y: auto; /* Add scrolling functionality */
                max-height: 500px; /* Adjust the maximum height as needed */
            }

            .view-category-form button {
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

            .add-category-popup {
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
                z-index: 1; /* Ensure the search bar is positioned above
                            the blur overlay */
            }

            a{
                text-decoration: none;
                color: black;
            }

        </style>
    </head>
    <body>
        <div class="container">



            <div class="view-category-form">
                <h3>My Ingredients</h3>
                <div class="search-bar">
                    <input type="text" id="searchInput" placeholder="Search Ingredient" oninput="searchIngredient()">
                </div>
                <p>


                    <%
                        List<Ingredient> ingrList = (ArrayList<Ingredient>) request.getAttribute("viewIngr");
                    %>
                    <a href=http://localhost:8080/BakerySystem/GetCategoryMenuServlet?action=get>
                        <button type="submit">Back to Menu</button> 
                    </a>
                <table>
                    <tr>
                        <th>Ingredient Name</th>
                        <th>Quantity</th>
                        <th>Unit Type</th>
                        <th>minimum quantity</th>
                        <th>is Ingredient available?</th>
                        <th> <button><a href="http://localhost:8080/BakerySystem/AddIngredientsServlet?action=Get">Add Ingredient</a></button></th>
                    </tr>

                    <% for (Ingredient ingredient : ingrList) {%>
                    <tr>
                        <td><%= ingredient.getIngredientName()%></td>
                        <td><%= ingredient.getQuantity()%></td>
                        <td><%=us.getUnitByName(ingredient.getUnitId())%></td>
                        <td><%= ingredient.getMinimumQty()%></td>
                        <td><%= ingredient.isIsAvailable()%></td>
                        
                         <th> <button><a href="http://localhost:8080/BakerySystem/UpdateIngredientsServlet?ingredientId=<%=ingredient.getIngredientId()%>">Update Ingredient</a></button></th>
                        <%if (ingredient.isIsAvailable()) {%>
                        <td><button onclick="restoreIngredient('<%= ingredient.getIngredientId()%>')">Deactivate</button></td>
                        <%} else {%>
                        <td><button onclick="deleteIngredient('<%= ingredient.getIngredientId()%>')">Activate</button></td>
                        <%}%> 

                    </tr>

                    <% }%>

                </table>
                </p>


            </div>
            <div class="blur-overlay"></div>
        </div>

        <div id="addIngredientPopup" class="popup-overlay" style="display: none;">
            <div class="add-category-popup">
                <input type="text" id="newIngredientName" placeholder="New Ingredient Name">
                <input type="number" id="Quantity" placeholder="New Quantity Number">
                <button onclick="addIngredient()">Add</button>
                <button onclick="hideAddIngredientPopup()">Cancel</button>
            </div>
        </div>


        <script  src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
                    function deleteIngredient(ingredientId) {
                        var confirmed = confirm("Are you sure you want to activate this Ingredient?");

                        if (confirmed) {

                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", "DeleteIngredientServlet", true);
                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    console.log("Ingredient deleted successfully");
                                    location.reload(); // Reload the page or update the category list
                                } else if (xhr.readyState === 4) {
                                    console.error("Error deleting ingredient:", xhr.statusText);
                                    // Display an error message or handle the error as needed
                                }
                            };
                            xhr.send("ingredientId=" + encodeURIComponent(ingredientId));
                        }
                    }

                    function restoreIngredient(ingredientId) {
                        var confirmed = confirm("Are you sure you want to deactivate this Ingredient?");

                        if (confirmed) {
                            // Send a request to the server to delete the category with the specified ID
                            // using AJAX
                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", "DeleteIngredientServlet", true);
                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    console.log("Ingredient deleted successfully");
                                    location.reload(); // Reload the page or update the category list
                                } else if (xhr.readyState === 4) {
                                    console.error("Error deleting ingredient:", xhr.statusText);
                                    // Display an error message or handle the error as needed
                                }
                            };
                            xhr.send("ingredientId=" + encodeURIComponent(ingredientId));
                        }
                    }

                    function updateIngredient(ingredientId, currentName) {
                        var newName = prompt("Enter the new Ingredient name:", currentName);
                        var newQuantity = document.getElementById("newQuantity").value;
                        var newUnit = document.getElementById("newUnit").value;
                        // Create a dropdown menu for unit selection
                        var unitDropdown = document.createElement("select");
                        unitDropdown.id = "newUnit";
                        var unitOptions = ["Unit 1", "Unit 2", "Unit 3"]; // Replace with your actual unit options from the database

                        // Populate the dropdown options
                        for (var i = 0; i < unitOptions.length; i++) {
                            var option = document.createElement("option");
                            option.value = unitOptions[i];
                            option.text = unitOptions[i];
                            unitDropdown.appendChild(option);
                        }

                        var div = document.createElement("div");
                        div.appendChild(document.createTextNode("Enter the new Quantity: "));
                        var newQuantityInput = document.createElement("input");
                        newQuantityInput.type = "text";
                        newQuantityInput.id = "newQuantity";
                        div.appendChild(newQuantityInput);

                        // Add the unit dropdown and quantity input to the prompt
                        var promptContent = document.createElement("div");
                        promptContent.appendChild(document.createTextNode("Enter the new Unit Type: "));
                        promptContent.appendChild(unitDropdown);
                        promptContent.appendChild(div);

                        var promptResult = prompt({
                            title: "Update Ingredient",
                            content: promptContent.innerHTML,
                            buttons: {
                                confirm: "Update",
                                cancel: "Cancel"
                            },
                            closeOnEscape: false,
                            closeOnClickOutside: false
                        });

                        if (promptResult) {


                            if (newName && newQuantity && newUnit) {
                                var xhr = new XMLHttpRequest();
                                xhr.open("POST", "UpdateIngredientServlet", true);
                                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                xhr.onreadystatechange = function () {
                                    if (xhr.readyState === 4 && xhr.status === 200) {
                                        console.log("Ingredient updated successfully");
                                        location.reload(); // Reload the page or update the ingredient list
                                    } else if (xhr.readyState === 4) {
                                        console.error("Error updating ingredient:", xhr.statusText);
                                        // Display an error message or handle the error as needed
                                    }
                                };
                                var params = "action=update" +
                                        "&ingredientId=" + encodeURIComponent(ingredientId) +
                                        "&ingredientName=" + encodeURIComponent(newName) +
                                        "&quantity=" + encodeURIComponent(newQuantity) +
                                        "&unit=" + encodeURIComponent(newUnit);
                                xhr.send(params);
                            }
                        }
                    }


                    function showAddIngredientPopup() {
                        var popup = document.getElementById("addIngredientPopup");
                        popup.style.display = "block";
                    }

                    function hideAddIngredientPopup() {
                        var popup = document.getElementById("addIngredientPopup");
                        popup.style.display = "none";
                    }

                    function addIngredient() {
                        var ingredientNameInput = document.getElementById("newIngredientName");
                        var ingredientName = ingredientNameInput.value;

                        if (ingredientName) {
                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", "addIngredientServlet", true);
                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    console.log("Ingredient added successfully");
                                    hideAddCategoryPopup();
                                    location.reload();
                                } else if (xhr.readyState === 4) {
                                    console.error("Error adding ingredient:", xhr.statusText);
                                }
                            };
                            xhr.send("ingredientName=" + encodeURIComponent(ingredientName));
                        }
                    }

                    function searchIngredient() {
                        var searchInput = document.getElementById("searchInput");
                        var searchTerm = searchInput.value.toLowerCase();

                        // Get all the table rows
                        var rows = document.getElementsByTagName("tr");

                        // Loop through the rows and hide/show based on the search term
                        for (var i = 1; i < rows.length; i++) { // Start from index 1 to exclude the table header row
                            var ingredientName = rows[i].getElementsByTagName("td")[0].textContent.toLowerCase();

                            if (ingredientName.includes(searchTerm)) {
                                rows[i].style.display = ""; // Show the row
                            } else {
                                rows[i].style.display = "none"; // Hide the row
                            }
                        }
                    }
        </script>
    </body>
</html>
