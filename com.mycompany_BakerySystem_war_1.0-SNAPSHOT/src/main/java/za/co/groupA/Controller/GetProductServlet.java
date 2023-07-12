/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.ProductDaoImpl;
import za.co.groupA.Model.Product;
import za.co.groupA.Service.ProductService;
import za.co.groupA.ServiceImpl.ProductServiceImpl;

@WebServlet(name = "GetProductServlet", urlPatterns = {"/GetProductServlet"})
public class GetProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        ProductService pro = new ProductServiceImpl(ProductDaoImpl.getInstance(db));
        List<Product> productList = pro.getAllProducts();
        request.setAttribute("allproduct", productList);
        request.getRequestDispatcher("ViewProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String proId = request.getParameter("productId");
       
        int productId = 0;
        if (proId != null) {

            try {
                productId = Integer.parseInt(proId);

            } catch (NumberFormatException nfe) {
                System.out.println("error" + nfe.getMessage());
            }
        }
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        ProductService pro = new ProductServiceImpl(ProductDaoImpl.getInstance(db));
        if (pro.getProductByProductId(productId).isIsAvailable()) {

            pro.deleteProduct(productId, false);

        } else {
            pro.deleteProduct(productId, true);

        }

    }
}
