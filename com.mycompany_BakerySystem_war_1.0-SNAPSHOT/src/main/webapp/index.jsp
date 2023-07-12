<%-- 
    Document   : index
    Created on : Jun 14, 2023, 2:15:57 PM
    Author     : Train
--%>

<%@page import="za.co.groupA.Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <title>To Pie For </title>
        <!-- Mobile Specific Meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <!-- Stylesheets -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" />
        <link rel="stylesheet" href="page/css/flaticon.css" />

        <!-- Animate -->
        <link rel="stylesheet" href="page/css/animate.css">
        <!-- Bootsnav -->
        <link rel="stylesheet" href="page/css/bootsnav.css">
        <!-- Color style -->
        <link rel="stylesheet" href="page/css/color.css">

        <!-- Custom stylesheet -->
        <link rel="stylesheet" href="page/css/custom.css" />
        <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>
    <body data-spy="scroll" data-target="#navbar-menu" data-offset="100">

        <!-- Preloader --> 
        <div id="loading">
            <div id="loading-center">
                <div id="loading-center-absolute">
                    <div class="object" id="object_one"></div>
                    <div class="object" id="object_two"></div>
                    <div class="object" id="object_three"></div>
                    <div class="object" id="object_four"></div>
                    <div class="object" id="object_five"></div>
                    <div class="object" id="object_six"></div>
                    <div class="object" id="object_seven"></div>
                    <div class="object" id="object_eight"></div>
                    <div class="object" id="object_big"></div>
                </div>
            </div>
        </div>
        <!--End Preloader -->
        <!-- Navbar -->
        <nav class="navbar navbar-default bootsnav no-background navbar-fixed black">
            <div class="container">  

                <!-- Start Atribute Navigation -->
                <div class="attr-nav">
                    <ul>
                        <li class="side-menu"><a href="#"><i class="fa fa-bars"></i></a></li>
                    </ul>
                </div>        
                <!-- End Atribute Navigation -->

                <!-- Start Header Navigation -->
<!--                <div class="navbar-header">
                    <a class="navbar-brand" href="#"><img src="page/images/logo.png" class="logo" alt=""  width="50" height="100"></a>
                </div>-->
                <!-- End Header Navigation -->
            </div>   
          
            <!-- Start Side Menu -->
            <div class="side">
                <a href="#" class="close-side"><i class="fa fa-times"></i></a>
                <div class="widget">
                    <h6 class="title">JUST FOR ME</h6>
                    <ul class="link">
                                        <%
                    User user = (User) request.getSession().getAttribute("user");

                    if (user == null) {%>
                        <li><a href=http://localhost:8080/BakerySystem/Login.jsp>Sign in</a></li>
                        <%} else {%>
                         <li><a href=http://localhost:8080/BakerySystem/LogoutServlet>Sign out</a></li>
                         <%}%>
                        <li><a href="http://localhost:8080/BakerySystem/GetCategoryMenuServlet?action=get">Menu</a></li>

                    </ul>
                        </div>
            </div>
            <!-- End Side Menu -->
        </nav>

        <!-- Header -->
        <header id="hello">
            <!-- Container -->
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <div class="banner">
                            <br></br>
                            <h3>-introducing-</h3>
                            <h1>TO PIE FOR</h1>

                            <div class="inner_banner">
                                <div class="banner_content">
                                  						
                                </div>
                                
                            </div>

                        </div>
                    </div>
                </div>
            </div><!-- Container end -->
            <p class="caption">*Group A</p>
        </header><!-- Header end -->

        <!-- Block Content -->
        <section id="block" >
            <div class="container">

                <!-- First section -->
                <div class="row">
                    <div class="col-sm-8">
                        <div class="feature">
                            <div class="shack_burger">
                                <h2>FLUFFY BLUEBERRY MUFFINS </h2>
                                <p>There’s no problem that a blueberry muffin can’t solve. Give me a Blueberry muffin and I’ll rule the world.</p>
                            </div>
                            <p class="caption">*Limited Time Only</p>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="signature">
                            <div class="shape">
                                <center><a class="navbar-brand" href="#"></a> </center>
                                 
                                <p>Chocolate Muffins</p>
                            </div>
                            <div class="signature_content">
                                <p>My new favorite? Hot chocolate muffin drizzled with melted sweet butter. Want big chocolate muffins? We have huge ones!</p>
                            </div>
                        </div>
                    </div>
                </div><!-- first section end -->

                <!-- Second section -->
                <div class="row">
                    <div class="col-md-6">
                        <div class="classic">
                            <a href="" class="classic_btn">Delicious Vanilla Cake</a>

                            <div class="overlay">
                                <h3>Vanilla Cake</h3>
                                <p>Unforgettable Sweetness You'll Keep Craving For.</p>

                                <p class="overlay_content">Baking a cake where one slice is not enough, there is happiness in every slice. Delectably delicious in every layers. </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <!-- Carousel -->
                        <div id="small_carousel" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#small_carousel" data-slide-to="0" class="active"></li>
                                <li data-target="#small_carousel" data-slide-to="1"></li>
                                <li data-target="#small_carousel" data-slide-to="2"></li>
                            </ol>

                            <div class="carousel-caption">
                                <h3>Freshly Baked Cookies</h3>
                                <hr />

                                <ul class="list-unstyled nutrition">
                                    <li><a href=""><span class="flaticon flaticon-protein"></span><p>Protein - 33g</p></a></li>
                                    <li><a href=""><span class="flaticon flaticon-carbohydrate"></span><p>Carbohydrates - 46gm</p></a></li>
                                    <li><a href=""><span class="flaticon flaticon-calories"></span><p>Calories - 750 kcal</p></a></li>
                                </ul>
                                <div class="info_btn_shadow">
                                    <a href="" class="info_btn">Cookies & Biscuit</a>
                                </div>
                            </div>

                            <!-- carousel inner -->
                            <div class="carousel-inner" role="listbox">
                                <div class="item active">
                                    <img src="page/images/choc_biscuit.jpg" alt="" />
                                </div>
                                <div class="item">
                                    <img src="page/images/chip_cookie.jpeg" alt="" />
                                </div>
                                <div class="item">
                                    <img src="page/images/fluffy.jpg"alt="" />
                                </div>
                            </div><!-- carousel inner end -->
                        </div><!-- Carousel end-->
                    </div>
                </div><!-- second section end -->

                 <!--Third section -->
                
                <div id="carousel" class="carousel slide" data-ride="carousel">
                     Indicators 
                    <ol class="carousel-indicators">
                        <li data-target="#carousel" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel" data-slide-to="1"></li>
                        <li data-target="#carousel" data-slide-to="2"></li>
                    </ol>

                     
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <img src="page/images/header_pg.jpg" alt="Cake">

                            <div class="carousel-caption">
                                <h2>Birthday Cakes</h2>
                                <h3>Freshly baked</h3>

                                <p>Where cakes become art!,Beautiful design. Exceptional taste, Our dream, your deliciousness.</p>

                                <div class="info_btn_shadow">
                                    <a href="" class="info_btn">info & nutrition</a>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <img src="page/images/sponge.jpg" alt="cake">

                            <div class="carousel-caption">
                                <h2>Sponge Cake</h2>
                                <h3>Satisfaction Guaranteed</h3>

                                <p>Delightful delicacies just for you, Share Moments, Share Cake.</p>

                                <div class="info_btn_shadow">
                                    <a href="" class="info_btn">info & nutrition</a>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <img src="page/images/yummy.webp" alt="cake">

                            <div class="carousel-caption">
                                <h2>White Cake</h2>
                                <h3>Vanilla Flavored</h3>

                                <p>White Yeahs Are What We Do!, You'll Look A Little Lovelier Each Day With Fabulous White Cake.</p>

                                <div class="info_btn_shadow">
                                    <a href="" class="info_btn">info & nutrition</a>
                                </div>
                            </div>
                        </div>
                    </div>  
                </div> 


     <div id="ourmaps"></div> 

      
      
        <!-- Footer -->
        <footer>
            <!-- Container -->
            <div class="container">
                <div class="row">

                    <div class="col-lg-3 col-sm-4 col-xs-12 col-lg-offset-1 pull-right">
                        <div class="subscribe">
                            <h4>Subscribe now</h4>
                            <p>Subscribe to the newsletter and
                                get some freshly baked biscuit every week.</p>

                            <form role="form">
                                <div class="form-group">
                                    <div class="input-group">
                                        <input type="email" class="form-control" id="em" placeholder="Enter your e-mail here">
                                        <span class="input-group-btn">
                                            <button type="submit" class="btn send_btn">
                                                <i class="glyphicon glyphicon-send"></i>
                                            </button>
                                        </span>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>

                    <div class="col-lg-3 col-sm-4 col-xs-12 col-lg-offset-1 pull-right">
                        <div class="contact_us">
                            <h4>Contact Us</h4>
                            <a href="">info@GROUPA.com</a>

                            <address>
                                2nd Floor Studio 18<br />
                                Mecer<br />
                                1234 <br />
                            </address>
                        </div>
                    </div>

                    <div class="col-lg-4 col-sm-4 col-xs-12 pull-right">
                        <div class="basic_info">
                            <a href=""><img class="footer_logo" src="page/images/logo.png" alt="logo" /></a>

                            <ul class="list-inline social">
                                <li><a href="" class="fa fa-facebook"></a></li>
                                <li><a href="" class="fa fa-twitter"></a></li>
                                <li><a href="" class="fa fa-instagram"></a></li>
                            </ul>

                            <div class="footer-copyright">
                                <p class="wow fadeInRight" data-wow-duration="1s">
                                    Made with 
                                    <i class="fa fa-heart"></i>
                                    by 
                                    <a target="_blank" href="http://bootstrapthemes.co">GROUP A(Ria, Anakie, Lungisani)</a> <br /> 
                                    2023. All Rights Reserved
                                </p>
                            </div>					
                        </div>
                    </div>

                </div>
            </div><!-- Container end -->
            
        </footer><!-- Footer end -->


        <!-- scroll up-->
        <div class="scrollup">
            <a href="#"><i class="fa fa-chevron-up"></i></a>
        </div><!-- End off scroll up->

              
              
              
        <!-- JavaScript -->
        <script src="http://code.jquery.com/jquery-1.12.1.min.js"></script>		
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <!-- Bootsnav js -->
        <script src="page/js/bootsnav.js"></script>

        <!-- JS Implementing Plugins -->
        <script src="http://maps.google.com/maps/api/js?sensor=true"></script>
        <script src="page/js/gmaps.min.js"></script>

        <script type="text/javascript">
            var map;
            $(document).ready(function () {
                map = new GMaps({
                    el: '#ourmaps',
                    lat: 3.139003,
                    lng: 101.686855,
                    scrollwheel: false
                });

                //locations request
                map.getElevations({
                    locations: [[3.139003, 101.686855]],
                    callback: function (result, status) {
                        if (status === google.maps.ElevationStatus.OK) {
                            for (var i in result) {
                                map.addMarker({
                                    lat: result[i].location.lat(),
                                    lng: result[i].location.lng(),
                                    infoWindow: {
                                        content: '<address class="tooltip_address"><b>Junky Burger</b><br />Jalan Awan Hijau, Taman OUG<br />58200 Kuala Lumpur <br />Malaysia <br /></address>'
                                    }
                                });
                            }
                        }
                    }
                });

            });
        </script>


        <!--main js-->
        <script type="text/javascript" src="page/js/main.js"></script>
    </body>	
</html>	