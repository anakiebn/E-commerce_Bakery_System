package za.co.groupA.Controller;

import java.io.IOException;
import java.util.List;
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

@WebServlet(name = "GetIngredientServlet", urlPatterns = {"/GetIngredientServlet"})
public class GetIngredientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        //   IngredientService ingr = new IngredientServiceImpl(IngredientDaoImpl.getInstance(getDbConnection(request)));
        IngredientService ingr = new IngredientServiceImpl(IngredientDaoImpl.getInstance(db));
        List<Ingredient> result = ingr.getAllIngredients();

        UnitService us = new UnitServiceImpl(UnitDaoImpl.getInstance(db));
        request.setAttribute("us", us);
        request.setAttribute("viewIngr", result);
        request.getRequestDispatcher("ViewIngredient.jsp").forward(request, response);
    }

}
