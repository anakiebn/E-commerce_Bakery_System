package za.co.groupA.Controller;

import java.io.IOException;
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
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Model.CartLineItem;
import za.co.groupA.Model.Product;
import za.co.groupA.ServiceImpl.ShoppingCartServiceImpl;
import za.co.groupA.exception.NoProductException;

@WebServlet(name = "QuantityServlet", urlPatterns = {"/QuantityServlet"})
public class QuantityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext sc = request.getServletContext();
        DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
        ShoppingCartServiceImpl cart = new ShoppingCartServiceImpl(db);
        boolean added = false;
        boolean removed = false;
        String msg = "";

        Map<Integer, CartLineItem> viewCart = (HashMap<Integer, CartLineItem>) request.getSession().getAttribute("viewCart");

        cart.setCart(viewCart);
        String ac = request.getParameter("ac");

        int key = 0;
        try {
            key = Integer.parseInt(request.getParameter("key"));
        } catch (NumberFormatException nfe) {
            System.out.println("this is key " + key);
            System.out.println(" error");
        }

        Product product = viewCart.get(key).getProduct();
        int qty = viewCart.get(key).getProductQty();

        if (ac.equals("plus")) {
            try {
                if (cart.addToCart(product)) {
                    System.out.println("added");
                    added = true;
                    msg = "";
                    request.getSession().setAttribute("isAdded", added);
                    request.getSession().setAttribute("open", "open");
                } else {
                    System.out.println("outOfStock");
                }

            } catch (NoProductException ex) {
                System.out.println("No product found");
            }
            response.sendRedirect("GetCategoryMenuServlet");
        } else {
        }
        if (ac.equals("minus")) {
            try {
                if (cart.removeFromCart(product)) {
                    removed = true;
                    added = true;
                    request.getSession().setAttribute("open", "open");
                    request.getSession().setAttribute("isRemoved", removed);

                } else {
                }

            } catch (NoProductException ex) {
                System.out.println("No product found");
            }
            response.sendRedirect("GetCategoryMenuServlet");

        }
    }

}
