<!DOCTYPE html>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .sidenav {
                height: 100%;
                width: 0;
                position: fixed;
                z-index: 1;
                top: 0;
                left: 0;
                background-color: #111;
                overflow-x: hidden;
                transition: 0.5s;
                padding-top: 60px;
            }

            .sidenav a {
                padding: 8px 8px 8px 32px;
                text-decoration: none;
                font-size: 25px;
                color: #818181;
                display: block;
                transition: 0.3s;
            }

            .sidenav a:hover {
                color: #f1f1f1;
            }

            .sidenav .closebtn {
                position: absolute;
                top: 0;
                right: 25px;
                font-size: 36px;
                margin-left: 50px;
            }

            #main {
                transition: margin-left .5s;
                padding: 16px;
            }

            @media screen and (max-height: 450px) {
                .sidenav {
                    padding-top: 15px;
                }
                .sidenav a {
                    font-size: 18px;
                }
            }
            #progress {
                position: relative;
                margin-bottom: 30px;
            }
            #progress-bar {
                position: absolute;
                background: lightseagreen;
                height: 5px;
                width: 0%;
                top: 25%;
                left: 0;
                right: 50px;
            }
            #progress-num {
                margin: 0;
                padding: 0;
                list-style: none;
                display: flex;
                justify-content: space-between;
            }
            #progress-num::before {
                content: "";
                background-color: lightgray;
                position: absolute;
                top: 25%;
                left: 0;
                height: 5px;
                width: 100%;
                z-index: -1;
            }
            #progress-num .step {
                border: 3px solid lightgray;
                border-radius: 100%;
                width: 25px;
                height: 25px;
                line-height: 25px;
                text-align: center;
                background-color: #fff;
                font-family: sans-serif;
                font-size: 14px;
                position: relative;
                z-index: 1;
            }
            #progress-num .step.active {
                border-color: lightseagreen;
                background-color: lightseagreen;
                color: #fff;
            }
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background: url('page/images/vanilla.jpg') no-repeat center center fixed;
                background-size: cover;
                 position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                backdrop-filter: blur(5px);
                -webkit-backdrop-filter: blur(5px);
                z-index: 0;
            }
/*            .blur-overlay {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                backdrop-filter: blur(5px);
                -webkit-backdrop-filter: blur(5px);
                z-index: 0;
            }*/
        </style>
    </head>
    <body>
        <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
            <a href="#">Completed</a>
            <a href="#">Unsettled</a>

        </div>
        <div id="main">



            <h1>Track Order Progress</h1>
            <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Search</span>


            <h2>Order no: 2</h2>
            <p> hello Ria, our professional bakers are busy preparing your order</p>
            <br>
            <div id="progress">
              
                <div id="progress-bar"></div>
                <ul id="progress-num">
                    <li class="step">1</li>
                    <li class="step">2</li>
                    <li class="step">3</li>
                    <li class="step">4</li>
                    <li class="step">5</li>
                </ul>
                  <ul id="progress-num">
                    <li style="color:green" class="labels">Outstanding</li>
                    <li style="color:green" class="labels">Baking</li>
                    <li style="color:green" class="labels">Packaging</li>
                    <li style="color:green" class="labels">Out-for-delivery</li>
                    <li style="color:green" class="labels">Delivered</li>
                </ul>

               

                <br>


            </div>
                 

        </div>
        <script>
            const progressBar = document.getElementById("progress-bar");
            const steps = document.querySelectorAll(".step");
            let active = 1;

            const updateProgress = () => {
                // Update active step
                steps.forEach((step, i) => {
                    if (i === active - 1) {
                        step.classList.add("active");
                    } else {
                        step.classList.remove("active");
                    }
                });

                // Update progress bar width
                progressBar.style.width = ((active - 1) / (steps.length - 1)) * 100 + "%";
            };

            // Set the initial value of active progress
            updateProgress();

            // Change the value of active progress (Modify this value as needed)
            active = 2;

            // Update the progress display
            updateProgress();
        </script>

        <script>
            function openNav() {
                document.getElementById("mySidenav").style.width = "250px";
                document.getElementById("main").style.marginLeft = "250px";
                document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
            }

            function closeNav() {
                document.getElementById("mySidenav").style.width = "0";
                document.getElementById("main").style.marginLeft = "0";
                document.body.style.backgroundColor = "white";
            }
        </script>


    </body>
</html>
