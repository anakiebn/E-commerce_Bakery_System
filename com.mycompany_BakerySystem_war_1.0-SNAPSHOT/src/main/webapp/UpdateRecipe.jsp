<%--
    Document   : AddRecipe
    Created on : Jun 6, 2023, 9:17:13 AM
    Author     : Train
--%>

<%@page import="za.co.groupA.Model.Ingredient"%>
<%@page import="java.util.List"%>
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

            .Ingredient-form {
                position: relative;
                z-index: 1;
                width: 600px;
                padding: 20px;
                background-color: rgba(255, 255, 255, 0.8);
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .Ingredient-form input[type="text"],
            .Ingredient-form input[type="password"] {
                width: 95%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            .Ingredient-form button {
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

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            .remove-ingredient {
                width: 80px;
                padding: 8px;
                background-color: #ff0000;
                color: #fff;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }


            a{
                text-decoration: none;
                color: black;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <form class="Ingredient-form" action="RecipeServlet" method="POST">
                <h2>Add Recipe</h2> 

                <label for="RecipeName">Name:</label>
                <input type="text" id="IngredientName" name="RecipeName" required>
                <input type="hidden"  name="action" value="add">
                <br><br>

                <div class="input_box">
                    <input type="text"  name="RecipeDes" placeholder="Enter Recipe Description" required />
                </div>
                <br><br>

                <table id="ingredient-table">
                    <thead>
                        <tr>
                            <th>Ingredient</th>
                            <th>Quantity</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="ingredient-body">
                        <tr class="ingredient-row">
                            <td>
                                <select name="IngredientId[]" required>
                                    <%
                                        String IngredientName;
                                        int IngredientId;
                                        List<Ingredient> IngList = (List<Ingredient>) request.getAttribute("viewIng");
                                        for (Ingredient in : IngList) {
                                            IngredientName = in.getIngredientName();
                                            IngredientId = in.getIngredientId();

                                    %>
                                    <option value="<%=IngredientId%>" ><%=IngredientName%></option>  
                                    <%}%>
                                </select>
                            </td>
                            <td>
                                <input type="text" name="Quantity[]" required>
                            </td>
                            <td>
                                <button type="button" class="remove-ingredient">Remove</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
              
                <button type="button" id="add-ingredient">Add Ingredient</button>
                <br><br>

                <button type="submit">Add Recipe</button>
            </form>
            <div class="blur-overlay"></div>
        </div> 

        <script>
            window.onload = function () {
                var addIngredientButton = document.getElementById("add-ingredient");
                var ingredientTable = document.getElementById("ingredient-table");
                var ingredientBody = document.getElementById("ingredient-body");

                addIngredientButton.addEventListener("click", function () {
                    var ingredientRow = document.createElement("tr");
                    ingredientRow.classList.add("ingredient-row");

                    var ingredientCell = document.createElement("td");
                    var ingredientSelect = document.createElement("select");
                    ingredientSelect.name = "IngredientId[]";
                    ingredientSelect.required = true;
            <% for (Ingredient in : IngList) {%>
                    var option = document.createElement("option");
                    option.value = "<%= in.getIngredientId()%>";
                    option.textContent = "<%= in.getIngredientName()%>";
                    ingredientSelect.appendChild(option);
            <% }%>
                    ingredientCell.appendChild(ingredientSelect);
                    ingredientRow.appendChild(ingredientCell);

                    var quantityCell = document.createElement("td");
                    var quantityInput = document.createElement("input");
                    quantityInput.type = "text";
                    quantityInput.name = "Quantity[]";
                    quantityInput.required = true;
                    quantityCell.appendChild(quantityInput);
                    ingredientRow.appendChild(quantityCell);

                    var actionsCell = document.createElement("td");
                    var removeButton = document.createElement("button");
                    removeButton.type = "button";
                    removeButton.classList.add("remove-ingredient");
                    removeButton.textContent = "Remove";
                    removeButton.addEventListener("click", function () {
                        ingredientBody.removeChild(ingredientRow);
                    });
                    actionsCell.appendChild(removeButton);
                    ingredientRow.appendChild(actionsCell);

                    ingredientBody.appendChild(ingredientRow);
                });

                var removeIngredientButtons = document.getElementsByClassName("remove-ingredient");
                for (var i = 0; i < removeIngredientButtons.length; i++) {
                    removeIngredientButtons[i].addEventListener("click", function () {
                        var ingredientRow = this.parentNode.parentNode;
                        ingredientBody.removeChild(ingredientRow);
                    });
                }
            };
        </script>
    </body>
</html>

