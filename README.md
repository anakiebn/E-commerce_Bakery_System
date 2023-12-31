# Online Bakery System

- It's an e-commerce website but this time for bakery products such as cakes, cookies, biscuits etc

# Description

This was a group project that was initially of 3 members but finished by 2. My role was to work with the business logic and the controller side of the website. With the use of MVC model, for our Controller we used Servlets to handle Http requests, as for our View, we've utilized JavaScript, JSPs, CSS, Html and Bootstrap.

# Features 

- Login : A user can create an account, upon registration they will receive an email to confirm their email, also if they exist in our system they can just login.
- Order : A customer can place an order, view their products in a cart and place payment.
- Add To Cart: When we don't have enough stock, a product will be out of stock, therefore it can't be placed to cart, otherwise they can add as many products they desire.
- Manage stock: Admin has features to add, delete, update stock ingredients, products, recipes, manage orders, users and see reports.

# What i learned

- Got to use JSPs,  writting java code inside html file was amazing.
- Got to use javax.mail library and SMTP protocols on my Service to send confirmation emails, that was cool!
- Using Servlets on my controller helped provide my data for the view, making the back-end communicate with the front-end was just a thing i needed to learn.
- Was also amazing learning about Sessions and Cookies work in java all that ability to carry data through multiple pages.
- Using JDBC, Connection the preparedStatements and learning SQL gave a deep understanding of data storage
- also Javascript fundamentals
- Got to understand the importance of team work, how to communcate effectively with others and to respect and understand each others' views, mostly know that 
the best idea always wins, not a person!


# Challenges

- Had connection problem, we kept on creating new connections everytime an object was called, later decided to use a Database pool management system just so we have at-least a centralized connection system instead of creating multiple of connection even when it wasn't neccesary, so it solved our problem.
- Front-end guy got an opportunity else where so he had to leave, the 2 of us had to adapt to that change, so i had to learn Javascript in short period of time just to understand his code, it was hard due to time sensivity but eventually got the hang of it and continued the work.
- My TrackOrder.jsp is not yet done, it's a matter of me improving my JavaScript so i can fully design what i really desire.

# Demo

 ### Welcome Page

![Welcome page](https://github.com/anakiebn/OnlineBakeryShoppingSytem/blob/a1de62efe30e2201e8e1c5ef415df3f45eca5545/welcom%20p.png)

 ### Registering

![registration](https://github.com/anakiebn/OnlineBakeryShoppingSytem/blob/a1de62efe30e2201e8e1c5ef415df3f45eca5545/register.png)

 ### Confirm Registration

![confirmation email](https://github.com/anakiebn/OnlineBakeryShoppingSytem/blob/93d42232617ba05b7758266b6ec64fcbfc1e1b9f/email.png)

 ### Customer view

![customer view](https://github.com/anakiebn/OnlineBakeryShoppingSytem/blob/a1de62efe30e2201e8e1c5ef415df3f45eca5545/customer%20menu.png)
 
 ### Placing an order

![place order](https://github.com/anakiebn/OnlineBakeryShoppingSytem/blob/a1de62efe30e2201e8e1c5ef415df3f45eca5545/place%20order.png)


 ### Confirm order

![confirm order](https://github.com/anakiebn/OnlineBakeryShoppingSytem/blob/a1de62efe30e2201e8e1c5ef415df3f45eca5545/pay%20conf.png)


 ### Successful order

![succ](https://github.com/anakiebn/OnlineBakeryShoppingSytem/blob/a1de62efe30e2201e8e1c5ef415df3f45eca5545/pay%20succ.png)

 ### Track order

![track order](https://github.com/anakiebn/OnlineBakeryShoppingSytem/blob/419a706be4d3f958f247247e4b7ef4f63821bbb3/track_order.png)

 ### Admin panel has Management Options

![management](https://github.com/anakiebn/OnlineBakeryShoppingSytem/blob/b21687c56084eb384c5679fdaffd9f13b0d34d9d/cat%20s.png)

### Manage products
- ##### The management interface for categories, receipts, units and ingredients is the same as product interface
![pr](https://github.com/anakiebn/OnlineBakeryShoppingSytem/blob/b21687c56084eb384c5679fdaffd9f13b0d34d9d/add%20product.png)


