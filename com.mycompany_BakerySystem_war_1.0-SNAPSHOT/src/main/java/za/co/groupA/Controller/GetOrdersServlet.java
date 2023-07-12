/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.StatusDaoImpl;
import za.co.groupA.Model.Order;
import za.co.groupA.Model.User;
import za.co.groupA.Service.OrderService;
import za.co.groupA.Service.StatusService;
import za.co.groupA.ServiceImpl.OrderServiceImpl;
import za.co.groupA.ServiceImpl.StatusServiceImpl;

@WebServlet(name = "GetOrdersServlet", urlPatterns = {"/GetOrdersServlet"})
public class GetOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        OrderService orderService = new OrderServiceImpl(db);
        StatusService ss = new StatusServiceImpl(StatusDaoImpl.getInstance(db));

        List<Order> orderList = orderService.getAllOrders();

        System.out.println(orderList);
        request.setAttribute("orderList", orderList);
        request.setAttribute("ss", ss);
        request.setAttribute("orderService", orderService);
        request.setAttribute("status", ss.getAllStatuss());
        if (request.getParameter("type") != null && request.getParameter("type").equals("management")) {
            request.getRequestDispatcher("Orders.jsp").forward(request, response);
        }
        if (request.getParameter("type") != null && request.getParameter("type").equals("report")) {
            request.getRequestDispatcher("OrdersPerDayPieChart.jsp").forward(request, response);
        }
        if (request.getParameter("type") != null && request.getParameter("type").equals("track")) {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                List<Order> notCompleteOrders = orderService.getAllOrders().stream()
                        .filter(order -> order.getEmailAddress().equals(user.getEmailAddress()))
                        .collect(Collectors.toList());
                List<Order> completeOrders = notCompleteOrders.stream()
                        .filter(order -> order.getStatus() == 7)
                        .collect(Collectors.toList());
                request.setAttribute("notCompleteOrders", notCompleteOrders);
                request.setAttribute("completeOrders", completeOrders);
                request.getRequestDispatcher("TrackOrder.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
