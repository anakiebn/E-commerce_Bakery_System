<%-- 
    Document   : Role
    Created on : Jun 6, 2023, 2:41:35 PM
    Author     : Train
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Role</title>
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
    <form class="confirm-form" action="http://localhost:8080/BakerySystem/Menu.jsp" method="POST">
      <h2>Add Role</h2>
      <input type="password" name="password" placeholder="Enter Role name" required>
       <a href=http://localhost:8080/BakerySystem/Menu.jsp>
      <button type="submit">Add</button> 
       </a>
    </form>
    <div class="blur-overlay"></div>
  </div>
    
    
    <script>
    function validateForm() {
      var username = document.getElementById('username').value;
      var password = document.getElementById('password').value;

      // Send an AJAX request to the server to check if the user exists
      var xhr = new XMLHttpRequest();
      xhr.open('POST', 'check_user.php', true);
      xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
      xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
          if (xhr.status === 200) {
            var response = xhr.responseText;
            if (response === 'exists') {
              // User exists, proceed with login
              document.forms['loginForm'].submit();
            } else {
              // User doesn't exist, prompt to register
              alert('User not found. Please register.');
              // Redirect to the registration page
              window.location.href = 'register.html';
            }
          } else {
            // Handle errors
            alert('An error occurred.');
          }
        }
      };
      xhr.send('username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password));

      return false; // Prevent form submission
    }
   </script>
</body>
</html>
