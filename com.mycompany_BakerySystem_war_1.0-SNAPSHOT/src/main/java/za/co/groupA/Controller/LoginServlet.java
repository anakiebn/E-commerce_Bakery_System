/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.ServiceImpl.Login;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("emailaddress");
        String pass = request.getParameter("password");

        ServletContext sc = request.getServletContext();
        DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
       
        if (email != null && pass != null) {
            Login login = new Login(email, pass, db);
            if (!login.userExists()) {
                request.setAttribute("msg", "Username doesn't exist");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                if (login.passwordsMatch()) {
                    request.getSession().setAttribute("user", login.getUser());
                    response.sendRedirect("GetCategoryMenuServlet");

                } else {
                    request.setAttribute("msg", "Invalid password");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("msg", "Enter all parameters");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
      
    }
}
