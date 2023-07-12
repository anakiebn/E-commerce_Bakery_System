<%-- 
    Document   : Product
    Created on : Jun 1, 2023, 11:58:10 AM
    Author     : Train
--%>

<%@page import="za.co.groupA.Model.Recipe"%>
<%@page import="za.co.groupA.Model.Category"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Product</title>

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

            .add-product-form {
                position: relative;
                z-index: 1;
                width: 600px;
                padding: 20px;
                background-color: rgba(255, 255, 255, 0.8);
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .add-product-form h3 {
                margin-bottom: 10px;
            }

            .add-product-form input,
            .add-product-form textarea,
            .add-product-form select {
                display: block;
                width: 100%;
                margin-bottom: 10px;
                margin-top: 10px;
            }

            .add-product-form button {
                width: 98%;
                padding: 10px;
                background-color: #ffff00;
                color: #333;
                border: none;
                border-radius: 3px;
                cursor: pointer;

            }

        </style>
    </head>
    <body>


        <div class="container">
            <div class="add-product-form">
                <h3>Add Product</h3>
                <form id="product-form" onsubmit="submitForm(event)" action="addProductServlet" method="POST">
                    <label>Product Name</label>
                    <input type="text" id="product-name" placeholder="Product Name" required name="productName">
                    <label>Product Image</label>
                    <input type="file" id="product-image" accept="image/*" required name="image">
                    <input type="hidden" id="product-image" name="action" value="add">
                    <label>Product Price</label>
                    <input type="text" id="product-price" value="0.00"  placeholder="Product Price" min="1" required name="price">
                    <label>Product Description</label>
                    <textarea id="Product Nutrient" placeholder="Product Description" required name="productDes"></textarea>
                    <label>Product Nutrient</label>
                    <textarea id="product-NutrientInfo" placeholder="Product Nutrient" required name="productNu"></textarea>
                    <label>Product Warnings</label>
                    <textarea id="product-warnings" placeholder="Product Warnings" required name="productWa"></textarea>
                    <label>Select Category</label>
                    <select name="categoryId">
                        <%
                            String categoryName;
                            int categoryId;
                            List<Category> categoryList = (List<Category>) request.getAttribute("allCategories");
                            for (Category category : categoryList) {
                                categoryName = category.getCategoryName();
                                categoryId = category.getCategoryId();

                        %>
                        <option value="<%=categoryId%>" ><%=categoryName%></option>  
                        <%}%>
                    </select>
                    <label>Select Recipe</label>
                    <select name="recipeId">
                        <%
                            String recipeName;
                            int recipeId;
                            List<Recipe> recipeList = (List<Recipe>) request.getAttribute("allRecipe");
                            for (Recipe recipe : recipeList) {
                                recipeName = recipe.getRecipeName();
                                recipeId = recipe.getRecipeId();

                        %>
                        <option value="<%=recipeId%>" ><%=recipeName%></option>  
                        <%}%>
                    </select>

                    <button type="submit">Add</button>
                </form>
            </div>
            <div class="blur-overlay"></div>
        </div>




        <script>
            window.addEventListener('DOMContentLoaded', (event) => {
                var numberInput = document.getElementById("numberInput").value;
                var formattedNumber = parseFloat(numberInput).toFixed(2);
                document.getElementById("result").value = formattedNumber;
            });

      

    </script>

</body>
</html>
