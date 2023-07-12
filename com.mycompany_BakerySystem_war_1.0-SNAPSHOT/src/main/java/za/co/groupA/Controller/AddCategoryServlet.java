/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import za.co.groupA.DaoImpl.CategoryDaoImpl;
import za.co.groupA.Service.CategoryService;
import za.co.groupA.ServiceImpl.CategoryServiceImpl;
import za.co.groupA.Model.Category;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;

@WebServlet(name = "addControllerServlet", urlPatterns = {"/addControllerServlet"})
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
        CategoryService cats = new CategoryServiceImpl(CategoryDaoImpl.getInstance(db));

        List<Category> result = cats.getAllCategories();
        request.setAttribute("cate", result);
        request.getRequestDispatcher("ViewCategory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryName = request.getParameter("categoryName");
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        CategoryService cats = new CategoryServiceImpl(CategoryDaoImpl.getInstance(db));
        cats.addCategory(new Category(0, categoryName, true));

    }

}
