/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Service.OrderService;
import za.co.groupA.ServiceImpl.OrderServiceImpl;

@WebServlet(name = "BarGraphServlet", urlPatterns = {"/BarGraphServlet"})
public class BarGraphServlet extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBPoolManagerBasic db=(DBPoolManagerBasic)request.getServletContext();
        OrderService orderService=new OrderServiceImpl(db);
        request.setAttribute("orderService",orderService);
        request.getRequestDispatcher("OrdersPerDayPieChart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
