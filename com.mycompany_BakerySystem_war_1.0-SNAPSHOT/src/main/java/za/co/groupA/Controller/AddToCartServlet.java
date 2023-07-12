/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.ProductDaoImpl;
import za.co.groupA.Model.CartLineItem;
import za.co.groupA.Model.Product;
import za.co.groupA.Service.ProductService;
import za.co.groupA.ServiceImpl.ProductServiceImpl;
import za.co.groupA.ServiceImpl.ShoppingCartServiceImpl;
import za.co.groupA.exception.NoProductException;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
       ProductService pro = new ProductServiceImpl(ProductDaoImpl.getInstance(db));
        response.setContentType("text/html;charset=UTF-8");

        int productID = 0;
        try (PrintWriter out = response.getWriter()) {

            ShoppingCartServiceImpl cartWeHave = new ShoppingCartServiceImpl(db);

            String productId = request.getParameter("productID");
            if (productId != null && !productId.isEmpty()) {
                productId = productId.trim();

                productID = Integer.parseInt(productId);

            }

            Product product = pro.getProductByProductId(productID);

            if (product != null) {
                HttpSession session = request.getSession();
                Map<Integer, CartLineItem> viewCart = (HashMap<Integer, CartLineItem>) session.getAttribute("viewCart");

                if (viewCart == null) {
                    try {
                        cartWeHave.addToCart(product);
                        session.setAttribute("viewCart", cartWeHave.getCart());
                        response.sendRedirect("GetCategoryMenuServlet");
                    } catch (NoProductException ex) {
                        System.out.println("No product found");
                    }

                } else {
                    try {
                        cartWeHave.setCart(viewCart);
                        cartWeHave.addToCart(product);
                        response.sendRedirect("GetCategoryMenuServlet");

                    } catch (NoProductException ex) {
                        System.out.println("No product found");
                    }

                }

                
               
            }else{
                
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
