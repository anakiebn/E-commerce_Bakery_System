package za.co.groupA.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Model.User;

//@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
@WebServlet(name = "UserInfoServlet", urlPatterns = {"/UserInfoServlet"})

public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("i'm here userServlet");
        User user = new User();
        user.setUserName(request.getParameter("userName"));
        user.setUserSurname(request.getParameter("userSurname"));
        user.setEmailAddress(request.getParameter("userEmail"));
        user.setUserPassword(request.getParameter("userPassword"));
        user.setUserTitle(request.getParameter("title"));
        user.setUserTel(request.getParameter("userTel"));
        user.setIdNumber(request.getParameter("userIdNumber"));
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher("/AddAddress.jsp").forward(request, response);
    }

}
