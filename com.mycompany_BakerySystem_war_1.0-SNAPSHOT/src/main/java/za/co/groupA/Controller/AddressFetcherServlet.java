/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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

@WebServlet(name = "AddressFetcherServlet", urlPatterns = {"/AddressFetcherServlet"})
public class AddressFetcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            if(request.getSession().getAttribute("loa")!=null){
                request.getSession().removeAttribute("loa");
            }
            AddressService as = new AddressServiceImpl(AddressDaoImpl.getInstance(db));
            List<Address> loa = as.getAddressByUserEmail(user.getEmailAddress());
            request.getSession().setAttribute("loa", loa);

            request.getRequestDispatcher("Payment.jsp").forward(request, response);

        } else {
            System.out.println("dsdsds");
        }
    }

}
