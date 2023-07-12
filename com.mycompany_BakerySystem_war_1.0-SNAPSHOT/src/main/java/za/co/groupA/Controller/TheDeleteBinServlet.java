package za.co.groupA.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
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

@WebServlet(name = "TheDeleteBinServlet", urlPatterns = {"/TheDeleteBinServlet"})
public class TheDeleteBinServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext sc = request.getServletContext();
        DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
        ShoppingCartServiceImpl cart = new ShoppingCartServiceImpl(db);

        Map<Integer, CartLineItem> viewCart = (HashMap<Integer, CartLineItem>) request.getSession().getAttribute("viewCart");
        if (!(viewCart == null) && !(request.getParameter("key") == null)) {
            cart.setCart(viewCart);

            if (request.getParameter("key") != null) {
                int key = Integer.parseInt(request.getParameter("key"));
                Product product = viewCart.get(key).getProduct();

                for (int i = 0; i < viewCart.get(key).getProductQty(); i++) {
                    try {
                        cart.removeFromCart(product);
                    } catch (NoProductException ex) {
                        request.setAttribute("msg", "No Product found");
                    }
                }

                request.getSession().setAttribute("viewCart", cart.getCart());
                response.sendRedirect("GetCategoryMenuServlet");
            } else {
                request.setAttribute("msg", "Product is not found");
            }

        } else {
            request.setAttribute("msg", "There's nothing to delete");
                response.sendRedirect("GetCategoryMenuServlet");
        }

    }

}
