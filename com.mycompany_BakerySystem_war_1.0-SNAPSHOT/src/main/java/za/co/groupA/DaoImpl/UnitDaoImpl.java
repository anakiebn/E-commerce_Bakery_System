package za.co.groupA.DaoImpl;

import za.co.groupA.Dao.UnitDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Model.Unit;

public class UnitDaoImpl implements UnitDao {

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static UnitDao unitDaoImpl = null;
    List<Unit> allUnitList;

    private UnitDaoImpl(Connection con) {
        this.con = con;
        allUnitList = getAllUnitsFromDb();

    }
    //*****************************************************************************************************

    public static UnitDao getInstance(DBPoolManagerBasic db) {
        if (unitDaoImpl == null) {
            try {
                unitDaoImpl = new UnitDaoImpl(db.getConnection());
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection " + ex.getMessage());
            }
        }
        return unitDaoImpl;
    }

    //************************************************************************************************************
    private List<Unit> getAllUnitsFromDb() {
        List<Unit> unitList = new ArrayList<>();
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT unitId, unitType FROM unit");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    unitList.add(new Unit(resultSet.getInt("unitId"), resultSet.getString("unitType")));
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
        return unitList;
    }
    //*************************************************************************************************************************

    @Override
    public boolean addUnit(Unit unit) {
        boolean retVal = false;
        if (con != null && !exists(unit)) {
            try {
                preparedStatement = con.prepareStatement("INSERT INTO unit(unitId,unitType) values(?,?)");
                preparedStatement.setInt(1, unit.getUnitId());
                preparedStatement.setString(2, unit.getUnitType());
                if (preparedStatement.executeUpdate() > 0) {
                    allUnitList.add(unit);
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
    public List<Unit> getAllUnits() {
        return new ArrayList(allUnitList);
    }

    //*******************************************************************************************************
    public boolean exists(Unit unit) {
        return allUnitList.stream().anyMatch(un -> un.equals(unit));
    }

    //***********************************************************************************************************
    @Override
    public Unit getUnit(String type) {
        return allUnitList.stream().filter(typ -> typ.getUnitType().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

    //*********************************************************************************************************
    @Override
    public boolean updateUnit(Unit unit) {
        boolean retVal = false;
        if (con != null && exists(unit)) {
            try {
                preparedStatement = con.prepareStatement("UPDATE unit SET unitType = ?  WHERE unitId = ?");
                preparedStatement.setString(1, unit.getUnitType());
                preparedStatement.setInt(2, unit.getUnitId());
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
            allUnitList = getAllUnitsFromDb();
        }
        return retVal;
    }

    @Override
    public String getUnitByName(int unitId) {
        return allUnitList.stream().filter(uni -> uni.getUnitId() == unitId).findFirst().get().getUnitType();

    }

    //***************************************************************************************************************************
//    @Override
//    public boolean deleteUnit(Unit unit) {
//        boolean retVal = false;
//        if (con != null && exists(unit)) {
//            try {
//                preparedStatement = con.prepareStatement("DELETE FROM unit WHERE unitId = ?");
//                preparedStatement.setInt(1, unit.getUnitId());
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
//            allUnitList = getAllUnitsFromDb(); 
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

//        System.out.println(new UnitDaoImpl(con).addUnit(new Unit(0, "ria")));
//        System.out.println(new UnitDaoImpl(con).addUnit(new Unit(0, "malutr")));
//               System.out.println(new UnitDaoImpl(con).addUnit(new Unit(0,"killoGrams")));
//               System.out.println(new UnitDaoImpl(con).addUnit(new Unit(0,"Grams")));
        System.out.println(new UnitDaoImpl(con).getAllUnits());
//                 System.out.println(new UnitDaoImpl(con).getUnit("gram"));
//                 System.out.println(new UnitDaoImpl(con).updateUnit(new Unit(1, "luta")));
//               System.out.println(new UnitDaoImpl(con).getUnitByName(1));

//              System.out.println(new UnitDaoImpl(con).addUnit(new Unit(0, "Grams")));
//              System.out.println(new UnitDaoImpl(con).addUnit(new Unit(0, "KiloGrams")));
//              System.out.println(new UnitDaoImpl(con).addUnit(new Unit(0, "Litres")));
        System.out.println(new UnitDaoImpl(con).addUnit(new Unit(0, "Millilitres")));
//              System.out.println(new UnitDaoImpl(con).addUnit(new Unit(0, "Cups")));
//              System.out.println(new UnitDaoImpl(con).addUnit(new Unit(0, "Teaspoon")));
//              System.out.println(new UnitDaoImpl(con).addUnit(new Unit(0, "No units")));

    }

}
