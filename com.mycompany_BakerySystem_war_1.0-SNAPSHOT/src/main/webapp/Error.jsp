<%-- 
    Document   : errorjsp
    Created on : Jun 9, 2023, 10:50:06 AM
    Author     : Train
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <title>ERROR JSP Page</title>
        <style>
    .icon-large {
        font-size: 24px; /* Adjust the size to your preference */
    }
</style>

    </head>
    <body>
       

    </body>
</html>

<html>
<head>
  <title>Login</title>
  <style>
          .icon-large {
        font-size: 50px; /* Adjust the size to your preference */
    }
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

    .login-form {
      position: relative;
      z-index: 1;
      width: 600px;
      padding: 20px;
      background-color: rgba(255, 255, 255, 0.8);
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      margin-top: 20px;
    }

    .login-form input[type="text"],
    .login-form input[type="password"] {
      width: 95%;
      padding: 10px;
      margin-bottom: 10px;
      border: 1px solid #ccc;
      border-radius: 3px;
    }

    .login-form button {
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
    <form class="login-form" action="http://localhost:8080/BakerySystem/newMenu.jsp" method="POST">
      <h2>Error</h2>
       <ion-icon name="bug-outline" style="color: red;" class="icon-large"></ion-icon>

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
