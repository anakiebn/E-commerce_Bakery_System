package za.co.groupA.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.IngredientDaoImpl;
import za.co.groupA.DaoImpl.UnitDaoImpl;
import za.co.groupA.Model.Ingredient;
import za.co.groupA.Service.IngredientService;
import za.co.groupA.Service.UnitService;
import za.co.groupA.ServiceImpl.IngredientServiceImpl;
import za.co.groupA.ServiceImpl.UnitServiceImpl;

@WebServlet(name = "UpdateIngredientsServlet", urlPatterns = {"/UpdateIngredientsServlet"})
public class UpdateIngredientsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        IngredientService is = new IngredientServiceImpl(IngredientDaoImpl.getInstance(db));
        UnitService us=new UnitServiceImpl(UnitDaoImpl.getInstance(db));
        
        String n=request.getParameter("ingredientId");
        if (request.getParameter("ingredientId") != null) {
            request.setAttribute("allUnit",us.getAllUnits());
            request.setAttribute("ingredient", is.getIngredient(Integer.parseInt(request.getParameter("ingredientId"))));
            
            request.getRequestDispatcher("UpdateIngredient.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ingredientId = request.getParameter("IngredientId");
        String ingredientName = request.getParameter("IngredientName");
        String quantity = request.getParameter("Quantity");
        String unitId = request.getParameter("UnitId");
        String minimumQty = request.getParameter("MinimumQty");
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        IngredientService ingredientService = new IngredientServiceImpl(IngredientDaoImpl.getInstance(db));
        if (ingredientId != null && ingredientName != null && quantity != null && unitId != null && minimumQty != null) {

            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientName(ingredientName);
            ingredient.setMinimumQty(Integer.parseInt(minimumQty));
            ingredient.setQuantity(Integer.parseInt(quantity));
            ingredient.setIngredientId(Integer.parseInt(ingredientId));
            ingredient.setUnitId(Integer.parseInt(unitId));
            if (ingredientService.updateIngredient(ingredient)) {
                response.sendRedirect("GetIngredientServlet");
            } else {
                request.setAttribute("ingrMsg", "Error occured while updating, try again");
                response.sendRedirect("GetIngredientServlet");
            }

        }

    }

}
