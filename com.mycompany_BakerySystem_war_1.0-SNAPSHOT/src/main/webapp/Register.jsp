<%-- 
    Document   : Register
    Created on : Jun 1, 2023, 1:22:40 PM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Register</title>
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

    .register-form {
      position: relative;
      z-index: 1;
      width: 600px;
      padding: 20px;
      background-color: rgba(255, 255, 255, 0.8);
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .register-form input[type="text"],
    .register-form input[type="password"],
    .register-form input[type="email"]{
      width: 95%;
      padding: 10px;
      margin-bottom: 10px;
      border: 1px solid #ccc;
      border-radius: 3px;
    }

    .register-form button {
      width: 20%;
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
    <form class="register-form" action="UserInfoServlet" method="POST">
      <h2>Register</h2>
      <p>Title:
        <select name="title">
          <option>Mr.</option>
          <option>Mrs.</option>
          <option>Miss.</option>
          <option>Sir.</option>
          <option>Dr.</option>
          <option>Other.</option>
        </select>
      </p>
      <input type="text" name="userName" placeholder="Enter Your Name" required>
      <input type="text" name="userSurname" placeholder="Enter Your Surname" required>
       <input type="email" name="userEmail" placeholder="Enter Your Email Address" required>
      <input type="text" name="userTel" placeholder="Enter Your CellPhone Number" required>
      <input type="text" name="userIdNumber" placeholder="Enter Your ID Number" required>
      <input type="password" name="userPassword" placeholder="Password" required>
      <input type="password" name="confirmPassword" placeholder="Confirm Password" required>
     
      <button type="submit" >Next</button>
      
      <div class="register-link">
        <p>Already have an Account? <a href="http://localhost:8080/BakerySystem/Login.jsp">Login here</a></p>
      </div>
      <p class="error-message" style="color: red; display: none;"></p>
    </form>
    <div class="blur-overlay"></div>
  </div>


    </script>
</body>
</html>
