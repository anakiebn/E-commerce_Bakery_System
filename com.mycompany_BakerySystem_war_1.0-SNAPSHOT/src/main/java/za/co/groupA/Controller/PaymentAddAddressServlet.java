/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.AddressDaoImpl;
import za.co.groupA.Model.Address;
import za.co.groupA.Model.User;
import za.co.groupA.Service.AddressService;
import za.co.groupA.ServiceImpl.AddressServiceImpl;

/**
 *
 * @author Train
 */
@WebServlet(name = "PaymentAddAddressServlet", urlPatterns = {"/PaymentAddAddressServlet"})
public class PaymentAddAddressServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext sc = request.getServletContext();
        DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
        AddressService addressService = new AddressServiceImpl(AddressDaoImpl.getInstance(db));

        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
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
//            addressService.addAddressForExistingUser(user.getEmailAddress(), address);
            System.out.println(addressService.addAddressForExistingUser(user.getEmailAddress(), address)?"added":"not added");
               
            
//            response.sendRedirect("AddressFetcherServlet");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);

        } else {
            request.setAttribute("msg", "User Not Found!");
            request.getRequestDispatcher("Payment.jsp").forward(request, response);
            
        }

//        addressService.addAddressForExistingUser(, address)
    }

}
