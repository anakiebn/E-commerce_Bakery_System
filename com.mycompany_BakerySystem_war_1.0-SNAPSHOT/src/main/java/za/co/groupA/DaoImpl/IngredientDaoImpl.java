package za.co.groupA.DaoImpl;

import za.co.groupA.Dao.IngredientDao;
import za.co.groupA.Model.Ingredient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import za.co.groupA.Dao.DBPoolManagerBasic;

public class IngredientDaoImpl implements IngredientDao {

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static IngredientDao ingredientDaoImpl = null;
    private List<Ingredient> allIngredientsList;

    private Map<Integer, Integer> checker;

    private IngredientDaoImpl(Connection con) {
        this.con = con;
        allIngredientsList = getAllIngredientFromDb();
    checker = new HashMap();

    }

    // ********************************************************************************************
    public static IngredientDao getInstance(DBPoolManagerBasic db) {
        if (ingredientDaoImpl == null) {
            try {
                ingredientDaoImpl = new IngredientDaoImpl(db.getConnection());
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection" + ex.getMessage());
            }
        }
        return ingredientDaoImpl;
    }


      
    // ********************************************************************************************
    private List<Ingredient> getAllIngredientFromDb() {
        List<Ingredient> ingredientList = new ArrayList<>();
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT ingredient.ingredientId, ingredient.ingredientName, ingredient.quantity, ingredient.unitId, ingredient.minimumQty, ingredient.isAvailable FROM ingredient JOIN unit ON ingredient.unitId = unit.unitId");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ingredientList.add(new Ingredient(resultSet.getInt("ingredientId"),
                            resultSet.getString("ingredientName"),
                            resultSet.getInt("quantity"),
                            resultSet.getInt("unitId"),
                            resultSet.getInt("minimumQty"),
                          resultSet.getBoolean("isAvailable")));
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
        return ingredientList;
    }

    // ********************************************************************************************
    @Override
    public boolean addIngredient(Ingredient ingredient) {
        boolean retVal = false;
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("INSERT INTO ingredient (ingredientId, ingredientName, quantity, unitId, minimumQty, isAvailable) VALUES(?, ?, ? ,?, ?, ?");
                preparedStatement.setInt(1, ingredient.getIngredientId());
                preparedStatement.setString(2, ingredient.getIngredientName());
                preparedStatement.setInt(3, ingredient.getQuantity());
                preparedStatement.setInt(4, ingredient.getUnitId());
                preparedStatement.setInt(5, ingredient.getMinimumQty());
                 preparedStatement.setBoolean(6, true);
                if (preparedStatement.executeUpdate() > 0) {
                    allIngredientsList.add(ingredient);
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

    // ********************************************************************************************
    public boolean idExists(int ingredienId) {
        return allIngredientsList.stream().anyMatch(ing -> ing.getIngredientId() == ingredienId);
    }

    // ********************************************************************************************
    @Override
    public List<Ingredient> getAllIngredients() {
        return new ArrayList(allIngredientsList);
    }

    // ********************************************************************************************
    @Override
    public Ingredient getIngredient(int ingredientId) {
        return allIngredientsList.stream().filter(ing -> ing.getIngredientId() == (ingredientId)).findFirst().orElse(null);
    }

    // ********************************************************************************************
    @Override
    public boolean ingredientQtyAvailable(int ingredientId, int amount) {
        boolean retVal = false;

        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT quantity FROM ingredient WHERE ingredientId=?");
                preparedStatement.setInt(1, ingredientId);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int qty = resultSet.getInt("quantity");
                    if (!checker.containsKey(ingredientId)) {
                        checker.put(ingredientId, qty);
                    }
                    retVal = (checker.get(ingredientId) - amount) >= 0;
                    if (retVal) {
                        checker.replace(ingredientId, checker.get(ingredientId) - amount);
                    }
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
        return retVal;
    }

    //*****************************************************************************************
    @Override
    public boolean ingredientAvailable(int ingredientId) {
        return getAllIngredients().stream().anyMatch(ing -> ing.getIngredientId() == (ingredientId));
    }

    // ********************************************************************************************
    @Override
    public boolean deleteIngredient(int ingredientId, boolean delete) {
 boolean retVal = false;

        if (con != null && idExists(ingredientId)) {
            try {
                preparedStatement = con.prepareStatement("UPDATE ingredient set isAvailable = ? WHERE ingredientId = ?");
                preparedStatement.setBoolean(1, delete);
                preparedStatement.setInt(2, ingredientId);
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
            allIngredientsList = getAllIngredientFromDb();
        }
        return retVal;
    }
    // ********************************************************************************************
    @Override
    public boolean updateIngredient(Ingredient ingredient) {

        boolean retVal = false;
        if (con != null && idExists(ingredient.getIngredientId())) {
            try {
                preparedStatement = con.prepareStatement("UPDATE ingredient SET ingredientName = ?,quantity = ?, unitId = ?, minimumQty = ?, isAvailable = ? WHERE ingredientId = ?");
                preparedStatement.setString(1, ingredient.getIngredientName());
                preparedStatement.setInt(2, ingredient.getQuantity());
                preparedStatement.setInt(3, ingredient.getUnitId());
                preparedStatement.setInt(4, ingredient.getMinimumQty());
                preparedStatement.setInt(5, ingredient.getIngredientId());
                preparedStatement.setBoolean(6, true);

                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not the ingredient" + ex.getMessage());
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
            allIngredientsList = getAllIngredientFromDb();
        }
        return retVal;
    }
//********************************************************************************************

    @Override
    public boolean subtractIngredientQty(int ingredientId, int amount) {
        boolean retVal = false;
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT quantity FROM ingredient WHERE ingredientId=?");
                preparedStatement.setInt(1, ingredientId);
                resultSet = preparedStatement.executeQuery();
                int qty = 0;
                if (resultSet.next()) {
                    qty = resultSet.getInt("quantity");
                }
                int recentQty = qty - amount;
                preparedStatement = con.prepareStatement("UPDATE ingredient set quantity = ?   WHERE ingredientId=?");
                preparedStatement.setInt(1, recentQty);
                preparedStatement.setInt(2, ingredientId);
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
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
        return retVal;
    }

    //***************************************************************************************************
    @Override
    public boolean addIngredientQty(int ingredientId, int amount) {
        boolean retVal = false;
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT quantity FROM ingredient WHERE ingredientId=?");
                preparedStatement.setInt(1, ingredientId);
                resultSet = preparedStatement.executeQuery();
                int qty = 0;
                if (resultSet.next()) {
                    qty = resultSet.getInt("quantity");
                }
                int quantity = qty + amount;
                preparedStatement = con.prepareStatement("UPDATE ingredient set quantity = ?   WHERE ingredientId=?");
                preparedStatement.setInt(1, quantity);
                preparedStatement.setInt(2, ingredientId);
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
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
        return retVal;
    }

//    ***************************************************************************************************
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

//        System.out.println(new IngredientDaoImpl(con).getAllIngredientFromDb());
//              System.out.println(new IngredientDaoImpl(con).getIngredient(1));
//              System.out.println(new IngredientDaoImpl(con).deleteIngredient("milk"));
//                  System.out.println(new IngredientDaoImpl(con).ingredientAvailable(1));
//        System.out.println(new IngredientDaoImpl(con).updateIngredient(new Ingredient(1, "malss", 5, 4)));
//        System.out.println(new IngredientDaoImpl(con).ingredientQtyAvailable(1, 3));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "flour", 25, 13, 2, true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "butter", 500, 12, 50, true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "oil", 20, 14, 2,true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "milk", 20, 14, 2,true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "baking powder", 5, 13, 2,true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "brown sugar", 10, 13, 2,true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "white sugar", 10, 13, 2,true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "salt", 5, 13, 1,true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "cocoa powder", 5, 13, 1,true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "chocolate", 500, 12, 20,true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "whipping cream", 5, 13, 1,true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "eggs", 50, 17, 2,true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "yoghurt", 5, 13, 1,true)));
        System.out.println(new IngredientDaoImpl(con).addIngredient(new Ingredient(0, "Essence", 500, 12, 20,true)));

    }

}
