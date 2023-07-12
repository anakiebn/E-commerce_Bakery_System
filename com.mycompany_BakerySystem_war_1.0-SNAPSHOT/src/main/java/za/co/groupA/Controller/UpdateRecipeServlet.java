/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet(name = "UpdateRecipeServlet", urlPatterns = {"/UpdateRecipeServlet"})
public class UpdateRecipeServlet extends HttpServlet {

  
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        String recipeName = request.getParameter("recipeName");
          String recipeDescription = request.getParameter("recipeDescription");
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
       RecipeService rec = new RecipeServiceImpl(RecipeDaoImpl.getInstance(db));
        rec.updateRecipe(new Recipe(recipeId, recipeName, recipeDescription, true));
    }
}
