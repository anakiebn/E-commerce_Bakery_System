package za.co.groupA.DaoImpl;

import za.co.groupA.Dao.StatusDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Model.Status;

public class StatusDaoImpl implements StatusDao {

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static StatusDao statusDaoImpl = null;
    List<Status> allStatusList;

    private StatusDaoImpl(Connection con) {
        this.con = con;
        allStatusList = getAllStatussFromDb();

    }
    //*****************************************************************************************************

    public static StatusDao getInstance(DBPoolManagerBasic db) {
        if (statusDaoImpl == null) {
            try {
                statusDaoImpl = new StatusDaoImpl(db.getConnection());
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection " + ex.getMessage());
            }
        }
        return statusDaoImpl;
    }

    //************************************************************************************************************
    private List<Status> getAllStatussFromDb() {
        List<Status> statusList = new ArrayList<>();
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT statusId, statusDesc FROM status");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    statusList.add(new Status(resultSet.getInt("statusId"), resultSet.getString("statusDesc")));
                }
            } catch (SQLException ex) {
                System.out.println("ERROR!" + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }

        }
        return statusList;
    }
    //*************************************************************************************************************************

    @Override
    public boolean addStatus(Status status) {
        boolean retVal = false;
        if (con != null && !exists(status)) {
            try {
                preparedStatement = con.prepareStatement("INSERT INTO status(statusId,statusDesc) values(?,?)");
                preparedStatement.setInt(1, status.getStatusId());
                preparedStatement.setString(2, status.getStatusDescr());
                if (preparedStatement.executeUpdate() > 0) {
                    allStatusList.add(status);
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("Error!!: " + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return retVal;
    }
    //********************************************************************************************************

    @Override
    public List<Status> getAllStatuss() {
        return new ArrayList(allStatusList);
    }

    //*******************************************************************************************************
    public boolean exists(Status status) {
        return allStatusList.stream().anyMatch(un -> un.equals(status));
    }

    //***********************************************************************************************************
    @Override
    public Status getStatus(int statusId) {
        return allStatusList.stream().filter(typ -> typ.getStatusId() == statusId).findFirst().orElse(null);
    }

    //*********************************************************************************************************
    @Override
    public boolean updateStatus(Status status) {
        boolean retVal = false;
        if (con != null && exists(status)) {
            try {
                preparedStatement = con.prepareStatement("UPDATE status SET statusDesc = ?  WHERE statusId = ?");
                preparedStatement.setString(1, status.getStatusDescr());
                preparedStatement.setInt(2, status.getStatusId());
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR" + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        if (retVal) {
            allStatusList = getAllStatussFromDb();
        }
        return retVal;
    }

    //***************************************************************************************************************************
//    @Override
//    public boolean deleteStatus(Status status) {
//        boolean retVal = false;
//        if (con != null && exists(status)) {
//            try {
//                preparedStatement = con.prepareStatement("DELETE FROM status WHERE statusId = ?");
//                preparedStatement.setInt(1, status.getStatusId());
//                if (preparedStatement.executeUpdate() > 0) {
//                    retVal = true;
//                }
//            } catch (SQLException ex) {
//                System.out.println("ERROR" + ex.getMessage());
//            } finally {
//                if (preparedStatement != null) {
//                    try {
//                        preparedStatement.close();
//                    } catch (SQLException ex) {
//                        System.out.println("Could not close: " + ex.getMessage());
//                    } finally {
//                        preparedStatement = null;
//                    }
//                }
//            }
//        }
//        if (retVal) {
//            allStatusList = getAllStatussFromDb(); 
//        }
//        return retVal;
//    }
    //******************************************************************************************************************************************
    public static void main(String[] args) {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bakerySystem", "root", "root"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (con != null) {
            System.out.println("Got connection");
        }

//        System.out.println(new StatusDaoImpl(con).addStatus(new Status(0, "ria")));
//        System.out.println(new StatusDaoImpl(con).addStatus(new Status(0, "malutr")));
//               System.out.println(new StatusDaoImpl(con).addStatus(new Status(0,"killoGrams")));
//               System.out.println(new StatusDaoImpl(con).addStatus(new Status(0,"Grams")));
//        System.out.println(new StatusDaoImpl(con).getAllStatuss());
                 System.out.println(new StatusDaoImpl(con).getStatus(1));
//                 System.out.println(new StatusDaoImpl(con).updateStatus(new Status(1, "luta")));
//               System.out.println(new StatusDaoImpl(con).getStatusByName(1));

//        System.out.println(new StatusDaoImpl(con).addStatus(new Status(0, "New")));
//        System.out.println(new StatusDaoImpl(con).addStatus(new Status(0, "Out-For-Delivery")));
//        System.out.println(new StatusDaoImpl(con).addStatus(new Status(0, "Delivered")));
//        System.out.println(new StatusDaoImpl(con).addStatus(new Status(0, "Baking")));
//        System.out.println(new StatusDaoImpl(con).addStatus(new Status(0, "Outstanding")));
        
//              System.out.println(new StatusDaoImpl(con).addStatus(new Status(0, "Cups")));
//              System.out.println(new StatusDaoImpl(con).addStatus(new Status(0, "Teaspoon")));
//              System.out.println(new StatusDaoImpl(con).addStatus(new Status(0, "No statuss")));
    }

}
