package za.co.groupA.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.IngredientDaoImpl;
import za.co.groupA.DaoImpl.RecipeDaoImpl;
import za.co.groupA.Model.Ingredient;
import za.co.groupA.Model.Recipe;
import za.co.groupA.Service.IngredientService;
import za.co.groupA.Service.RecipeService;
import za.co.groupA.ServiceImpl.IngredientServiceImpl;
import za.co.groupA.ServiceImpl.RecipeServiceImpl;

@WebServlet(name = "RecipeServlet", urlPatterns = {"/RecipeServlet"})
public class RecipeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        IngredientService ingr = new IngredientServiceImpl(IngredientDaoImpl.getInstance(db));
        List<Ingredient> result = ingr.getAllIngredients();
        request.setAttribute("viewIng", result);
        request.getRequestDispatcher("AddRecipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        IngredientService ingredientService = new IngredientServiceImpl(IngredientDaoImpl.getInstance(db));

        String recipeName = request.getParameter("RecipeName");
        String recipeDes = request.getParameter("RecipeDes");
        String[] ingredientIds = request.getParameterValues("IngredientId[]");
        String[] quantities = request.getParameterValues("Quantity[]");
        List<Ingredient> recipeIngredients = new ArrayList();

        int a = 0;
        for (String ingrId : ingredientIds) {
            System.out.println("in");
            for (int j = a; j < quantities.length; j++) {
                Ingredient ingredient = ingredientService.getIngredient(Integer.parseInt(ingrId));
                ingredient.setQuantity(Integer.parseInt(quantities[a]));
                recipeIngredients.add(ingredient);
                a++;
                break;
            }
        }
        RecipeService rs = new RecipeServiceImpl(RecipeDaoImpl.getInstance(db));
        if (rs.addRecipe(new Recipe(0, recipeName, recipeDes, recipeIngredients, true))) {
            request.setAttribute("recipeList", rs.getAllRecipes());
            request.getRequestDispatcher("ViewRecipe.jsp").forward(request, response);

        }
                request.getRequestDispatcher("Error.jsp").forward(request, response);

    }

}
