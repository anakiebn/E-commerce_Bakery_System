
package za.co.groupA.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.RecipeDaoImpl;
import za.co.groupA.Model.Recipe;
import za.co.groupA.Service.RecipeService;
import za.co.groupA.ServiceImpl.RecipeServiceImpl;


@WebServlet(name = "GetRecipeServlet", urlPatterns = {"/GetRecipeServlet"})
public class GetRecipeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext sc = request.getServletContext();
        DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
        RecipeService rec = new RecipeServiceImpl(RecipeDaoImpl.getInstance(db));
        List<Recipe> result = rec.getAllRecipes();
        request.setAttribute("recipeList", result);
        request.getRequestDispatcher("ViewRecipe.jsp").forward(request, response);

    }

   



}
