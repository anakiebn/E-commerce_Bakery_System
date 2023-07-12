<%-- 
    Document   : AddCategory
    Created on : Jun 1, 2023, 1:19:17 PM
    Author     : Train
--%>

<!DOCTYPE html>
<html>
<head>
  <title>Add Category</title>
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

    .add-category-form {
      position: relative;
      z-index: 1;
      width: 600px;
      padding: 20px;
      background-color: rgba(255, 255, 255, 0.8);
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .add-category-form input[type="text"] {
      width: 95%;
      padding: 10px;
      margin-bottom: 10px;
      border: 1px solid #ccc;
      border-radius: 3px;
    }

 .add-category-form button {
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
    
  </style>
</head>
<body>
    
      <div class="container">
   <div class="add-category-form">
    <h3>Add Category</h3>
    <form id="category-form" action="addControllerServlet" method="POST">
        <input type="text" id="category-input" name="categoryName" placeholder="Enter category name" required>
      <button type="submit">Add</button>
    </form>
    
    <div id="notification-container"></div>
  </div>

  
   <div class="blur-overlay"></div>
  </div>

</body>
</html>
