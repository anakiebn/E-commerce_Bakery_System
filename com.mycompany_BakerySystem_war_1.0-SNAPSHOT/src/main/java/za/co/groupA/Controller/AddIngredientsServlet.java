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

@WebServlet(name = "AddIngredientsServlet", urlPatterns = {"/AddIngredientsServlet"})
public class AddIngredientsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
//         UnitService un = new UnitServiceImpl(UnitDaoImpl.getInstance(getDbConnection(request)));
        UnitService un = new UnitServiceImpl(UnitDaoImpl.getInstance(db));
        request.setAttribute("allUnit", un.getAllUnits());
        request.getRequestDispatcher("AddIngredients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ingredientName = request.getParameter("IngredientName");
        String quantity = request.getParameter("Quantity");
        int unitId = Integer.parseInt(request.getParameter("unitId"));
        String minimumQty = request.getParameter("minimumQty");
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        IngredientService ingr = new IngredientServiceImpl(IngredientDaoImpl.getInstance(db));
        System.out.println(ingr.addIngredient(new Ingredient(0, ingredientName, Integer.parseInt(quantity), unitId, Integer.parseInt(minimumQty), true )));
        response.sendRedirect("GetIngredientServlet");
    }

}
