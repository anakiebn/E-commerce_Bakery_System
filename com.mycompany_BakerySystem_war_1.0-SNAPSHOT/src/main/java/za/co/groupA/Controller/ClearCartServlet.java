package za.co.groupA.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Model.CartLineItem;

@WebServlet(name = "ClearCartServlet", urlPatterns = {"/ClearCartServlet"})
public class ClearCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Map<Integer, CartLineItem> cart=(HashMap<Integer, CartLineItem>)request.getSession().getAttribute("viewCart");
        cart.clear();
        response.sendRedirect("GetCategoryMenuServlet");
        
        
    }

}
