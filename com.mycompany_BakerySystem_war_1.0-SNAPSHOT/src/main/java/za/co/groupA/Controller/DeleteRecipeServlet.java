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
import za.co.groupA.DaoImpl.CategoryDaoImpl;
import za.co.groupA.DaoImpl.RecipeDaoImpl;
import za.co.groupA.Service.CategoryService;
import za.co.groupA.Service.RecipeService;
import za.co.groupA.ServiceImpl.CategoryServiceImpl;
import za.co.groupA.ServiceImpl.RecipeServiceImpl;

/**
 *
 * @author Train
 */
@WebServlet(name = "DeleteRecipeServlet", urlPatterns = {"/DeleteRecipeServlet"})
public class DeleteRecipeServlet extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String recId = request.getParameter("recipeId");
        
        int recipeId = 0;
        if (recId != null) {

            try {
                recipeId = Integer.parseInt(recId);
               
            } catch (NumberFormatException nfe) {
                System.out.println("error" + nfe.getMessage());
            }
        }
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        RecipeService rec = new RecipeServiceImpl(RecipeDaoImpl.getInstance(db));
        if (rec.getRecipe(recipeId).isIsActive()) {
            
            rec.deleteRecipe(recipeId, false);
           
        } else {
            rec.deleteRecipe(recipeId, true);
     

        }

    }
}
