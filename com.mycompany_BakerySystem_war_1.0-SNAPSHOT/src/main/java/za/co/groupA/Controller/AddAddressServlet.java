package za.co.groupA.Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Model.Address;
import za.co.groupA.Model.User;
import za.co.groupA.ServiceImpl.Registration;

@WebServlet(name = "AddAddressServlet", urlPatterns = {"/AddAddressServlet"})
public class AddAddressServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("i'm here addressServlet");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("User missing from Session");
        }
        Address address = new Address();
        int houseNumber = 0;
        try {
            houseNumber = Integer.parseInt(request.getParameter("houseNumber"));
        } catch (NumberFormatException nfe) {
        }
        address.setHouseNumber(houseNumber);
        address.setStreetName(request.getParameter("streetName"));
        address.setTown(request.getParameter("town"));
        address.setPostalCode(request.getParameter("postalCode"));
        user.setAddress(address);
        ServletContext sc = request.getServletContext();
        DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
        Registration registration = new Registration(db, user);
        session.setAttribute("user", user);
        RequestDispatcher requestDispatcher = null;
        if (registration.sendCode()) {
            session.setAttribute("sentCode", registration.getCode());
            requestDispatcher = request.getRequestDispatcher("Confirmation.jsp");
        } else {
            requestDispatcher = request.getRequestDispatcher("Error.jsp");
        }
        requestDispatcher.forward(request, response);
    }
}
