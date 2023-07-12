<!DOCTYPE html>
<html>
    <head>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background: url('page/images/vanilla.jpg') no-repeat center center fixed;
                background-size: cover;
            }
            .popup {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #f9f9f9;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
                z-index: 9999;

            }
            
            .overlay {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 78%;
                background-color: rgba(0, 0, 0, 0.7);
                color: white;
                display: none;
                align-items: center;
                justify-content: center;
                text-align: center;
                transition: opacity 0.4s;
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
        <script>
            window.onload = function () {
                showPopup();
            };

            function showPopup() {
                // Display the popup message
                var popup = document.getElementById("popup");
                popup.style.display = "block";


            }
        </script>
    </head>
    <body>
        <!-- Popup message -->
        <div id="popup" class="popup">
            <h3>Payment Error</h3>
            <p>Your payment has failed to be processed</p>
            <a href="Payment.jsp"> Back Payment</a>
            <a href="GetCategoryMenuServlet"> Back Menu</a>


        </div>
    </body>
</html>
