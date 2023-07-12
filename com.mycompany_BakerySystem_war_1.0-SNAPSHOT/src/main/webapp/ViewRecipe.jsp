<%-- 
    Document   : ViewCategory
    Created on : May 29, 2023, 10:27:06 AM
    Author     : Train
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="za.co.groupA.Model.Recipe"%>
<%@page import="za.co.groupA.Model.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Recipe</title>

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

            }

            .view-recipe-form {
                position: relative;
                z-index: 1;
                width: 600px;
                padding: 20px;
                background-color: rgba(255, 255, 255, 0.8);
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .view-recipe-form button {
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

            .add-recipe-popup {
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

            a{
                text-decoration: none;
                color: black;
            }

        </style>
    </head>
    <body>
        <div class="container">



            <div class="view-recipe-form">
                <h3>My recipes</h3>
                <div class="search-bar">
                    <input type="text" id="searchInput" placeholder="Search Recipe" oninput="searchRecipe()">
                </div>
                <p>


                    <%
                        List<Recipe> recList = (ArrayList<Recipe>) request.getAttribute("recipeList");

                    %>
                    <a href=http://localhost:8080/BakerySystem/GetCategoryMenuServlet?action=get>
                        <button type="submit">Back to Menu</button> 
                    </a>
                <table>
                    <tr>
                        <th>Recipe Name</th>
                        <th>Recipe Description</th>
                        <th>is Recipe Active?</th>
                        <th> <button><a href="http://localhost:8080/BakerySystem/RecipeServlet?action=Get">Add Recipe</a></button></th>
                    </tr>

                    <% for (Recipe recipe : recList) {
                    %>
                    <tr>
                        <td><%= recipe.getRecipeName()%></td>
                        <td><%= recipe.getRecipeDescription()%></td>
                        <td><%= recipe.isIsActive()%></td>
                        <td><button onclick="updateRecipe('<%= recipe.getRecipeId()%>', '<%= recipe.getRecipeName()%>')">Update</button></td>
                        <%if (recipe.isIsActive()) {%>
                        <td><button onclick="restoreRecipe('<%= recipe.getRecipeId()%>')">Deactivate</button></td>
                        <%} else {%>
                        <td><button onclick="deleteRecipe('<%= recipe.getRecipeId()%>')">Activate</button></td>
                        <%}%>



                    </tr>

                    <% }%>



                </table>
                </p>


            </div>
            <div class="blur-overlay"></div>
        </div>

        <div id="addRecipePopup" class="popup-overlay" style="display: none;">
            <div class="add-recipe-popup">
                <input type="text" id="newRecipeName" placeholder="New Recipe Name">
                <button onclick="addRecipe()">Add</button>
                <button onclick="hideAddRecipePopup()">Cancel</button>
            </div>
        </div>


        <script  src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
                    function deleteRecipe(recipeId) {
                        var confirmed = confirm("Are you sure you want to activate?");

                        if (confirmed) {
                            // Send a request to the server to delete the category with the specified ID
                            // using AJAX
                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", "DeleteRecipeServlet", true);
                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    console.log("Recipe deleted successfully");
                                    location.reload(); // Reload the page or update the category list
                                } else if (xhr.readyState === 4) {
                                    console.error("Error deleting category:", xhr.statusText);
                                    // Display an error message or handle the error as needed
                                }
                            };
                            xhr.send("recipeId=" + encodeURIComponent(recipeId));
                        }
                    }


                    function restoreRecipe(recipeId) {
                        var confirmed = confirm("Are you sure you want to deactivate?");

                        if (confirmed) {

                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", "DeleteRecipeServlet", true);
                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    console.log("Recipe deleted successfully");
                                    location.reload(); // Reload the page or update the category list
                                } else if (xhr.readyState === 4) {
                                    console.error("Error deleting category:", xhr.statusText);
                                    // Display an error message or handle the error as needed
                                }
                            };
                            xhr.send("recipeId=" + encodeURIComponent(recipeId));
                        }
                    }
                    function updateRecipe(recipeId, currentName) {
                        var newName = prompt("Enter the new recipe name", currentName);

                        if (newName) {
                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", "UpdateRecipeServlet", true);
                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    console.log("Recipe updated successfully");
                                    location.reload(); // Reload the page or update the category list
                                } else if (xhr.readyState === 4) {
                                    console.error("Error updating recipe:", xhr.statusText);
                                    // Display an error message or handle the error as needed
                                }
                            };
                            xhr.send("recipeId=" + encodeURIComponent(recipeId) + "&recipeName=" + "&recipeName="+ encodeURIComponent(newName));
                        }
                    }

                    function showAddRecipePopup() {
                        var popup = document.getElementById("addRecipePopup");
                        popup.style.display = "block";
                    }

                    function hideAddRecipePopup() {
                        var popup = document.getElementById("addRecipePopup");
                        popup.style.display = "none";
                    }

                    function addRecipe() {
                        var recipeNameInput = document.getElementById("newRecipeName");
                        var recipeName = recipeNameInput.value;

                        if (recipeName) {
                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", "addControllerServlet", true);
                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    console.log("recipe added successfully");
                                    hideAddRecipePopup();
                                    location.reload();
                                } else if (xhr.readyState === 4) {
                                    console.error("Error adding category:", xhr.statusText);
                                }
                            };
                            xhr.send("RecipeName=" + encodeURIComponent(categoryName));
                        }
                    }

                    function searchRecipe() {
                        var searchInput = document.getElementById("searchInput");
                        var searchTerm = searchInput.value.toLowerCase();

                        // Get all the table rows
                        var rows = document.getElementsByTagName("tr");

                        // Loop through the rows and hide/show based on the search term
                        for (var i = 1; i < rows.length; i++) { // Start from index 1 to exclude the table header row
                            var categoryName = rows[i].getElementsByTagName("td")[0].textContent.toLowerCase();

                            if (categoryName.includes(searchTerm)) {
                                rows[i].style.display = ""; // Show the row
                            } else {
                                rows[i].style.display = "none"; // Hide the row
                            }
                        }
                    }
        </script>
    </body>
</html>
