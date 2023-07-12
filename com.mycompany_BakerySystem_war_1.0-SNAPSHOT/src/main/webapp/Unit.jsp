<%-- 
    Document   : Unit
    Created on : Jun 12, 2023, 8:47:48 AM
    Author     : Train
--%>

<%@page import="za.co.groupA.Model.Unit"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Unit</title>
        
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

    .view-category-form {
      position: relative;
      z-index: 1;
      width: 600px;
      padding: 20px;
      background-color: rgba(255, 255, 255, 0.8);
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
  z-index: 1; /* Ensure the search bar is positioned above the blur overlay */
}
    
  </style>
    </head>
    <body>
         <div class="container">
             
             
             
             <div class="view-category-form">
                 <h3>My Units</h3>
                  <div class="search-bar">
                 <input type="text" id="searchInput" placeholder="Search Unit" oninput="searchCategory()">
              </div>
             <p>

    
    <%
    List<Unit> uniList = (List<Unit>) request.getAttribute("unit");
%>

<table>
  <tr>
    <th>Unit Type</th>
     <th> <button onclick="showAddCategoryPopup()">Add Unit</button></th>
  </tr>
  
  <% for (Unit uni : uniList) { %>
    <tr>
      <td><%= uni.getUnitType() %></td>
        <td><button onclick="updateCategory('<%= uni.getUnitId() %>', '<%= uni.getUnitType() %>')">Update</button></td>
        <td><button onclick="deleteCategory('<%= uni.getUnitId() %>')">Delete</button></td>   

    </tr>

  <% } %>
  
</table>
   </p>
     <a href=http://localhost:8080/BakerySystem/GetCategoryMenuServlet?action=get>
     <button type="submit">Back to Menu</button> 
     </a>
    
      </div>
     <div class="blur-overlay"></div>
  </div>
  
<div id="addCategoryPopup" class="popup-overlay" style="display: none;">
        <div class="add-category-popup">
            <input type="text" id="newCategoryName" placeholder="New Unit Type">
            <button onclick="addCategory()">Add</button>
            <button onclick="hideAddCategoryPopup()">Cancel</button>
        </div>
    </div>
  
  
  <script  src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script>
 function deleteCategory(categoryId) {
  var confirmed = confirm("Are you sure you want to delete this UnitType?");

  if (confirmed) {
    // Send a request to the server to delete the category with the specified ID
    // using AJAX
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "DeleteCategoryServlet", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        console.log("Category deleted successfully");
        location.reload(); // Reload the page or update the category list
      } else if (xhr.readyState === 4) {
        console.error("Error deleting category:", xhr.statusText);
        // Display an error message or handle the error as needed
      }
    };
    xhr.send("action=delete&categoryId=" + encodeURIComponent(categoryId));
  }
}

function updateCategory(categoryId, currentName) {
  var newName = prompt("Enter the new unit type:", currentName);

  if (newName) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "UpdateCategoryServlet", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        console.log("Category updated successfully");
        location.reload(); // Reload the page or update the category list
      } else if (xhr.readyState === 4) {
        console.error("Error updating category:", xhr.statusText);
        // Display an error message or handle the error as needed
      }
    };
    xhr.send("action=update&categoryId=" + encodeURIComponent(categoryId) + "&categoryName=" + encodeURIComponent(newName));
  }
}

function showAddCategoryPopup() {
            var popup = document.getElementById("addCategoryPopup");
            popup.style.display = "block";
        }
        
        function hideAddCategoryPopup() {
            var popup = document.getElementById("addCategoryPopup");
            popup.style.display = "none";
        }
        
        function addCategory() {
            var categoryNameInput = document.getElementById("newCategoryName");
            var categoryName = categoryNameInput.value;
        
            if (categoryName) {
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "addControllerServlet", true);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        console.log("Category added successfully");
                        hideAddCategoryPopup();
                        location.reload();
                    } else if (xhr.readyState === 4) {
                        console.error("Error adding category:", xhr.statusText);
                    }
                };
                xhr.send("categoryName=" + encodeURIComponent(categoryName));
            }
        }
        
        function searchCategory() {
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
