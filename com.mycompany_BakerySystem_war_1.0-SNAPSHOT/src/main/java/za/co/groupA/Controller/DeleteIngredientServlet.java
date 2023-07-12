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
import za.co.groupA.DaoImpl.IngredientDaoImpl;
import za.co.groupA.Service.IngredientService;
import za.co.groupA.ServiceImpl.IngredientServiceImpl;

/**
 *
 * @author Train
 */
@WebServlet(name = "DeleteIngredientServlet", urlPatterns = {"/DeleteIngredientServlet"})
public class DeleteIngredientServlet extends HttpServlet {
 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String ingreId = request.getParameter("ingredientId");
       int ingredientId = 0;
        if (ingreId != null) {
            try {
                ingredientId = Integer.parseInt(ingreId);
            } catch (NumberFormatException nfe) {
            }
        }
          DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
          IngredientService ingr = new IngredientServiceImpl(IngredientDaoImpl.getInstance(db));
          if (ingr.getIngredient(ingredientId).isIsAvailable()) {
            
            ingr.deleteIngredient(ingredientId, false);
           
        } else {
            ingr.deleteIngredient(ingredientId, true);
     

        }
//        processRequest(request, response);
    }



}
