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
import za.co.groupA.DaoImpl.CategoryDaoImpl;
import za.co.groupA.DaoImpl.ProductDaoImpl;
import za.co.groupA.DaoImpl.RecipeDaoImpl;
import za.co.groupA.Model.Category;
import za.co.groupA.Model.Product;
import za.co.groupA.Service.CategoryService;
import za.co.groupA.Service.ProductService;
import za.co.groupA.Service.RecipeService;
import za.co.groupA.ServiceImpl.CategoryServiceImpl;
import za.co.groupA.ServiceImpl.ProductServiceImpl;
import za.co.groupA.ServiceImpl.RecipeServiceImpl;

/**
 *
 * @author Train
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        CategoryService cats = new CategoryServiceImpl(CategoryDaoImpl.getInstance(db));
        request.setAttribute("allCategories", cats.getAllCategories());
        RecipeService rec = new RecipeServiceImpl(RecipeDaoImpl.getInstance(db));
        request.setAttribute("allRecipe", rec.getAllRecipes());

        ProductService ps = new ProductServiceImpl(ProductDaoImpl.getInstance(db));

        String productId = request.getParameter("productId");
        int productID = 0;
        if (productId != null && !productId.isEmpty()) {
            try {
                productID = Integer.parseInt(productId);
            } catch (NumberFormatException ex) {
                System.out.println("Error" + ex.getMessage());
            }
            Product product = ps.getProductByProductId(productID);

            request.setAttribute("product", product);
            request.setAttribute("productCat", cats.getCategory(product.getCategoryId()));
            request.setAttribute("productRecipe", rec.getRecipe(product.getRecipeId()));
            request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");

        ProductService ps = new ProductServiceImpl(ProductDaoImpl.getInstance(db));
        Product product = new Product();

        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productPrice = request.getParameter("price");
        String productDescr = request.getParameter("productDes");
        String productNutr = request.getParameter("productNu");
        String productWa = request.getParameter("productWa");
        String image = request.getParameter("image");
        String categoryId = request.getParameter("categoryId");
        String recipeId = request.getParameter("recipeId");

        if (productId == null && productName == null && productPrice == null && productDescr == null && productNutr == null && productWa == null && recipeId == null && image == null && categoryId == null) {
            System.out.println("null");
        } else {

            product.setProductName(productName);
            double price = 0.00;
            try {

                price = Double.parseDouble(productPrice);
            } catch (NumberFormatException nfe) {
            }
            product.setPrice(price);
            product.setProductDescription(productDescr);
            product.setProductNutrientsInfo(productNutr);
            product.setProductWarnings(productWa);
            product.setImage(image);

            int catId = 0;
            try {
                catId = Integer.parseInt(categoryId);
            } catch (NumberFormatException nfe) {
            }
            product.setCategoryId(catId);

            int rId = 0;
            try {
                rId = Integer.parseInt(recipeId);
            } catch (NumberFormatException nfe) {
            }
            product.setProductId(Integer.parseInt(productId));
            product.setRecipeId(rId);
            product.setIsAvailable(true);
            if (ps.updateProduct(product)) {
                System.out.println("Added");
            } else {
                System.out.println("NOt added");
            }

            request.setAttribute("allproduct", ps.getAllProducts());
            request.getRequestDispatcher("ViewProduct.jsp").forward(request, response);
        }

    }
}
