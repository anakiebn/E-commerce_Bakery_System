<%-- 
    Document   : LoginError
    Created on : Jun 13, 2023, 2:41:40 PM
    Author     : Train
--%>

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
      max-width: 400px;
      margin: 0 auto;
      padding: 20px;
      background-color: #f8f8f8;
      border-radius: 5px;
      text-align: center;
      margin-top: 100px;
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

    .elami{
      display: inline-block;
      padding: 10px 20px;
      background-color: yellow;
      color: #333;
      text-decoration: none;
      border-radius: 4px;
      font-weight: bold;
   
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
    <form class="login-form" action="http://localhost:8080/BakerySystem/Register.jsp" method="POST">
  <h2>Error: User Not Registered</h2>
    <p>We're sorry, but it appears that you are not registered in our system. Please click the button below to create an account.</p>
    <button class="elami" href="http://localhost:8080/BakerySystem/Register.jsp">Register</button>
    </form>
    <div class="blur-overlay"></div>
  </div>
   
</body>
</html>

