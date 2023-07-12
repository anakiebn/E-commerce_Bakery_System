package za.co.groupA.Controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.CategoryDaoImpl;
import za.co.groupA.DaoImpl.ProductDaoImpl;
import za.co.groupA.Model.Category;
import za.co.groupA.Model.Product;
import za.co.groupA.Service.CategoryService;
import za.co.groupA.Service.ProductService;
import za.co.groupA.ServiceImpl.CategoryServiceImpl;
import za.co.groupA.ServiceImpl.ProductServiceImpl;

@WebServlet(name = "GetCategoryMenuServlet", urlPatterns = {"/GetCategoryMenuServlet"})
public class GetCategoryMenuServlet extends HttpServlet implements Serializable {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        CategoryService cats = new CategoryServiceImpl(CategoryDaoImpl.getInstance(db));
        List<Category> result = cats.getAllCategories();
        request.setAttribute("cate", result);
          request.setAttribute("menuCate", result.stream().filter(cat -> cat.isIsActive() == true).collect(Collectors.toList()));
        ProductService pro = new ProductServiceImpl(ProductDaoImpl.getInstance(db));
        List<Product> productList = null;
        String cat = request.getParameter("cat");
        int catId = 0;
        if (cat == null || cat.equals("1")) {
            productList = pro.getAllProducts();
        } else {

            try {
                catId = Integer.parseInt(cat);
            } catch (NumberFormatException nfe) {
            }
            productList = pro.getProductByCategoryId(catId);
        }

        request.setAttribute("allproduct", productList);
        request.setAttribute("menuAllProducts", productList.stream().filter(pr -> pr.isIsAvailable() == true).collect(Collectors.toList()));

        request.getRequestDispatcher("newMenu.jsp").forward(request, response);
    }

}
