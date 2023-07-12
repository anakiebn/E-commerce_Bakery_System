package za.co.groupA.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.CategoryDaoImpl;
import za.co.groupA.Service.CategoryService;
import za.co.groupA.ServiceImpl.CategoryServiceImpl;

@WebServlet(name = "DeleteCategoryServlet", urlPatterns = {"/DeleteCategoryServlet"})
public class DeleteCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String catId = request.getParameter("categoryId");
        
        int categoryId = 0;
        if (catId != null) {

            try {
                categoryId = Integer.parseInt(catId);
               
            } catch (NumberFormatException nfe) {
                System.out.println("error" + nfe.getMessage());
            }
        }
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        CategoryService cats = new CategoryServiceImpl(CategoryDaoImpl.getInstance(db));
        if (cats.getCategory(categoryId).isIsActive()) {
            
            cats.deleteCategory(categoryId, false);
           
        } else {
            cats.deleteCategory(categoryId, true);
     

        }

    }
}
