package za.co.groupA.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.AddressDaoImpl;
import za.co.groupA.Model.Address;
import za.co.groupA.ServiceImpl.AddressServiceImpl;

@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession().setAttribute("cardName", request.getParameter("cardname"));
        request.getSession().setAttribute("expmonth", request.getParameter("expmonth"));
        request.getSession().setAttribute("expyear", request.getParameter("expyear"));
        request.getSession().setAttribute("cvv", request.getParameter("cvv"));
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");

        if (request.getParameter("Address") != null) {
            Address address = new AddressServiceImpl(AddressDaoImpl.getInstance(db)).getAddress(Integer.parseInt(request.getParameter("Address")));
            request.getSession().setAttribute("Address", address);
        }

        request.getRequestDispatcher("ConfirmPayment.jsp").forward(request, response);

    }
}
