/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.groupA.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.UnitDaoImpl;
import za.co.groupA.Model.Unit;
import za.co.groupA.Service.UnitService;
import za.co.groupA.ServiceImpl.UnitServiceImpl;

@WebServlet(name = "AddUnitServlet", urlPatterns = {"/AddUnitServlet"})
public class AddUnitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        //UnitService uni = new UnitServiceImpl(UnitDaoImpl.getInstance(getDbConnection(request)));
        UnitService uni = new UnitServiceImpl(UnitDaoImpl.getInstance(db));
        List<Unit> result = uni.getAllUnits();
        request.setAttribute("unit", result);
        request.getRequestDispatcher("Unit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String UnitType = request.getParameter("UnitType");
        DBPoolManagerBasic db = (DBPoolManagerBasic) request.getServletContext().getAttribute("dbconn");
        UnitService uni = new UnitServiceImpl(UnitDaoImpl.getInstance(db));
      //  UnitService uni = new UnitServiceImpl(UnitDaoImpl.getInstance(getDbConnection(request)));
        uni.addUnit(new Unit(0, UnitType));

    }

//    private Connection getDbConnection(HttpServletRequest request) {
//        Connection con = null;
//        ServletContext sc = request.getServletContext();
//        DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
//        try {
//            con = db.getConnection();
//        } catch (SQLException ex) {
//            throw new RuntimeException("Error getting connection from DB Pool. " + ex.getMessage());
//        }
//        return con;
//    }

}
