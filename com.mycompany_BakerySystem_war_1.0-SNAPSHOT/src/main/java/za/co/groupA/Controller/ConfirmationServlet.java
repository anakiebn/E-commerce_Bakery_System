/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Model.User;
import za.co.groupA.ServiceImpl.Registration;

/**
 *
 * @author t
 */
@WebServlet(name = "ConfirmationServlet", urlPatterns = {"/ConfirmationServlet"})
public class ConfirmationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("i'm here confirmationCodeServlet");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String sentCode = (String) session.getAttribute("sentCode");
        String enteredCode = (String) request.getParameter("enteredCode");
        RequestDispatcher requestDispatcher = null;
        if (sentCode != null && enteredCode != null) {
            if (sentCode.equals(enteredCode)) {
                user.setRoleId(2);
                DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
                Registration register = new Registration(db, user);
                if (register.registerUser()) {
                    requestDispatcher = request.getRequestDispatcher("index.jsp");
                } else {
                    requestDispatcher = request.getRequestDispatcher("Error.jsp");
                }
            } else {
                requestDispatcher = request.getRequestDispatcher("Error.jsp");
            }
        } else {
            requestDispatcher = request.getRequestDispatcher("Error.jsp");
        }
        
        session.removeAttribute("sentCode");
        requestDispatcher.forward(request, response);
    }

}
