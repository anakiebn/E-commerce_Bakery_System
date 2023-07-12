<%-- 
    Document   : Confirmation
    Created on : Jun 2, 2023, 3:22:10 PM
    Author     : Train
--%>

<%@page import="za.co.groupA.Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Confirm</title>
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
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 100vh;
    position: relative;
  }

    .confirm-form {
      position: relative;
      z-index: 1;
      width: 600px;
      padding: 20px;
      background-color: rgba(255, 255, 255, 0.8);
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      margin-top: 20px;
    }

    .confirm-form input[type="text"],
    .confirm-form input[type="password"] {
      width: 95%;
      padding: 10px;
      margin-bottom: 10px;
      border: 1px solid #ccc;
      border-radius: 3px;
    }

    .confirm-form button {
      width: 95%;
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
    
   .navbar {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    height: 60px;
    padding: 0 20px;
    background-color: #ffff00;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    position: relative;
  }

   .moving-words {
    font-size: 24px;
    font-weight: bold;
    position: absolute;
    top: 50%;
    right: 0;
    transform: translateY(-50%);
    white-space: nowrap;
    animation: moveWords 10s linear infinite;
  }

  @keyframes moveWords {
    0% {
      right: 0;
    }
    100% {
      right: 100%;
    }
  }
  </style>
</head>
<body>
    
    
  <div class="container">
    <form class="confirm-form" action="ConfirmationServlet" method="POST">
      <h2>Confirm</h2>
         <%
                    User user =(User)session.getAttribute("user");
         %>
      <h4>Please enter the confirmation code sent to <%= user.getEmailAddress()%>,</h4>
      <input type="text" name="enteredCode" placeholder="Enter confirmation code" required>
      <a href=http://localhost:8080/BakerySystem/Menu.jsp>
      <button type="submit">Confirm</button> 
       </a>
    </form>
    <div class="blur-overlay"></div>
  </div>
    
    

</body>
</html>
