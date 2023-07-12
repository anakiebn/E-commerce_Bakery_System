package za.co.groupA.Controller;

import za.co.groupA.Service.ProductService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.CategoryDaoImpl;
import za.co.groupA.DaoImpl.ProductDaoImpl;
import za.co.groupA.DaoImpl.RecipeDaoImpl;
import za.co.groupA.Model.Product;
import za.co.groupA.Service.CategoryService;
import za.co.groupA.Service.RecipeService;
import za.co.groupA.ServiceImpl.CategoryServiceImpl;
import za.co.groupA.ServiceImpl.ProductServiceImpl;
import za.co.groupA.ServiceImpl.RecipeServiceImpl;

@WebServlet(name = "addProductServlet", urlPatterns = {"/addProductServlet"})
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        CategoryService cats = new CategoryServiceImpl(CategoryDaoImpl.getInstance(db));
        request.setAttribute("allCategories", cats.getAllCategories());
        RecipeService rec = new RecipeServiceImpl(RecipeDaoImpl.getInstance(db));
        request.setAttribute("allRecipe", rec.getAllRecipes());
        request.getRequestDispatcher("Product.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Product product = new Product();
        product.setProductName(request.getParameter("productName"));
        double price = 0.00;
        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (NumberFormatException nfe) {
        }
        product.setPrice(price);
        product.setProductDescription(request.getParameter("productDes"));
        product.setProductNutrientsInfo(request.getParameter("productNu"));
        product.setProductWarnings(request.getParameter("productWa"));
        product.setImage(request.getParameter("image"));
        int categoryId = 0;
        try {
            categoryId = Integer.parseInt(request.getParameter("productId"));
        } catch (NumberFormatException nfe) {
        }
        product.setCategoryId(categoryId);

        int recipeId = 0;
        try {
            recipeId = Integer.parseInt(request.getParameter("recipeId"));
        } catch (NumberFormatException nfe) {
        }
        product.setRecipeId(recipeId);
        product.setIsAvailable(true);
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        ProductService pro = new ProductServiceImpl(ProductDaoImpl.getInstance(db));
        RequestDispatcher requestDispatcher = null;
        if (pro.addProduct(product)) {
            requestDispatcher = request.getRequestDispatcher("index.jsp");
        } else {
            requestDispatcher = request.getRequestDispatcher("Error.jsp");
        }
        requestDispatcher.forward(request, response);
    }


}
