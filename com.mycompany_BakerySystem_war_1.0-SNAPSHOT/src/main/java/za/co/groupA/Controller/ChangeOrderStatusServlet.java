package za.co.groupA.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Model.Order;
import za.co.groupA.Service.OrderService;
import za.co.groupA.ServiceImpl.OrderServiceImpl;

@WebServlet(name = "ChangeOrderStatusServlet", urlPatterns = {"/ChangeOrderStatusServlet"})
public class ChangeOrderStatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        OrderService orderService = new OrderServiceImpl(db);

        if (request.getParameter("statusId") != null && request.getParameter("orderId") != null) {
            int statusId = Integer.parseInt(request.getParameter("statusId"));
            Order order = orderService.getOrder(Integer.parseInt(request.getParameter("orderId")));

            order.setStatus(statusId);
            if (orderService.updateOrder(order)) {
                request.removeAttribute("statusId");
                request.removeAttribute("orderId");

                response.sendRedirect("http://localhost:8080/BakerySystem/GetOrdersServlet?type=management");
            }
        }

    }
}
