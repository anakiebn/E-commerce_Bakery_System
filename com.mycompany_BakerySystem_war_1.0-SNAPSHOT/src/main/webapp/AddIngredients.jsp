<%-- 
    Document   : AddIngredients
    Created on : Jun 1, 2023, 1:20:27 PM
    Author     : Train
--%>

<%@page import="za.co.groupA.Model.Unit"%>
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
  </style>
</head>
<body>
   <div class="container">
    <form class="Ingredient-form" action="AddIngredientsServlet" method="POST">
      <h2>Add Ingredient</h2> 
      
     <label for="IngredientName">Name:</label>
    <input type="text" id="IngredientName" name="IngredientName" required>
    <input type="hidden"  name="action" value="add">
    <br><br>
    
    <label for="Quantity">Quantity:</label>
    <input type="number" id="Quantity" name="Quantity" required>
    <br><br>
    
   <label for="Unit">Unit:</label>
  <select name="unitId">
         <%
             String unitType;
             int unitId;
         List<Unit> unitList = (List<Unit>) request.getAttribute("allUnit");
        
         for(Unit unit : unitList){
             unitType = unit.getUnitType();
             unitId = unit.getUnitId();
         
         %>
        <option value="<%=unitId%>" ><%=unitType%></option>  
        <%}%>
     </select>
<br><br>

    
    <button type="submit"> Add Ingredient</button>
      
      </form>
    <div class="blur-overlay"></div>
  </div>
</body>

</html>
