/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.CategoryDaoImpl;
import za.co.groupA.DaoImpl.ProductDaoImpl;
import za.co.groupA.Model.Product;
import za.co.groupA.Service.CategoryService;
import za.co.groupA.Service.ProductService;
import za.co.groupA.ServiceImpl.CategoryServiceImpl;
import za.co.groupA.ServiceImpl.ProductServiceImpl;

/**
 *
 * @author Train
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
        ProductService pro = new ProductServiceImpl(ProductDaoImpl.getInstance(db));
        response.setContentType("text/html;charset=UTF-8");

        int productID = 0;
        int productQuantity = 0;
        try (PrintWriter out = response.getWriter()) {

            List<Product> cartList = new ArrayList<>();
            String productId = request.getParameter("productID");
//            String productQ = request.getParameter("productQuantity");
            if (productId != null && !productId.isEmpty() ) {
                productId = productId.trim();
              
                productID = Integer.parseInt(productId);
                
            }

            Product product = pro.getProductByProductId(productID);
            
            if (product != null) {
                HttpSession session = request.getSession();
                ArrayList<Product> cart_list = (ArrayList<Product>) session.getAttribute("cart-list");
                if (cart_list == null) {
                    cartList.add(product);
                    session.setAttribute("cart-list", cartList);
//                   response.sendRedirect("GetCategoryMenuServlet");
                } else {
                    cartList = cart_list;

                    boolean exist = false;
                    for (Product p : cart_list) {
                        if (p.getProductId() == productID) {
                            exist = true;
                            out.println("<h3 Item Already in Cart</h3>");
                        }
                    }

                    if (!exist) {
                        cartList.add(product);
                        response.sendRedirect("GetCategoryMenuServlet");
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
