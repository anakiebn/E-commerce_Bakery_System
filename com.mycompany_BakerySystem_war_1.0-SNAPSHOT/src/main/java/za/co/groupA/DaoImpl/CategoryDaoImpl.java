package za.co.groupA.DaoImpl;

import za.co.groupA.Model.Category;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.groupA.Dao.CategoryDao;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Dao.DeliveryNoteDao;

public class CategoryDaoImpl implements CategoryDao {

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static CategoryDao categoryDaoImpl = null;
    private List<Category> allCategoryList;

    private CategoryDaoImpl(Connection con) {
        this.con = con;
        allCategoryList = getAllCategoriesFromDb();
    }
 
    public static CategoryDao getInstance(DBPoolManagerBasic db) {
        if (categoryDaoImpl == null) {
            try {
                categoryDaoImpl = new CategoryDaoImpl(db.getConnection());
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection");
            }
        }
        return categoryDaoImpl;
    }
    // ********************************************************************************************

    private List<Category> getAllCategoriesFromDb() {
        List<Category> categoryList = new ArrayList<>();
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT categoryId, categoryName, isActive FROM category");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    categoryList.add(new Category(resultSet.getInt("categoryId"), resultSet.getString("categoryName"), resultSet.getBoolean("isActive")));
                }
            } catch (SQLException ex) {
                System.out.println("ERROR!" + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        preparedStatement = null;
                    }

                }
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        resultSet = null;
                    }
                }
            }

        }
        return categoryList;
    }
    // ********************************************************************************************

    public boolean idExists(int categoryId) {
        return allCategoryList.stream().anyMatch(cat -> cat.getCategoryId() == categoryId);
    }
    // ********************************************************************************************

    public boolean nameExists(String name) {
        return allCategoryList.stream().anyMatch(cat -> cat.getCategoryName().equalsIgnoreCase(name));
    }
    // ********************************************************************************************

    @Override
    public boolean addCategory(Category category) {
        boolean retVal = false;
        if (con != null && !idExists(category.getCategoryId()) && !nameExists(category.getCategoryName())) {
            try {
                preparedStatement = con.prepareStatement("INSERT INTO category(categoryId,categoryName,isActive) values(?,?,?)");
                preparedStatement.setInt(1, category.getCategoryId());
                preparedStatement.setString(2, category.getCategoryName());
                preparedStatement.setBoolean(3, category.isIsActive());
                if (preparedStatement.executeUpdate() > 0) {
                    allCategoryList.add(category);
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not add the category " + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        preparedStatement = null;
                    }
                }
            }
        }
        return retVal;
    }
    // ********************************************************************************************

    @Override
    public List<Category> getAllCategories() {
        return new ArrayList(allCategoryList);
    }
    // ********************************************************************************************

    @Override
    public Category getCategory(int categoryId) {
        return allCategoryList.stream().filter(cat -> cat.getCategoryId() == categoryId).findFirst().orElse(null);
    }
    // ********************************************************************************************

    @Override
    public boolean deleteCategory(int categoryId, boolean delete) {
        boolean retVal = false;
        if (con != null && idExists(categoryId)) {
            try {
                preparedStatement = con.prepareStatement("UPDATE category SET isActive = ? WHERE categoryId = ?");
                preparedStatement.setBoolean(1, delete);
                preparedStatement.setInt(2, categoryId);
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not delete category" + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        preparedStatement = null;
                    }
                }
            }
        }
        if (retVal) {
            allCategoryList = getAllCategoriesFromDb();
        }
        return retVal;
    }
    // ********************************************************************************************

    @Override
    public boolean updateCategory(Category category) {
        boolean retVal = false;
        if (con != null && idExists(category.getCategoryId())) {
            try {
                preparedStatement = con.prepareStatement("UPDATE category SET categoryName = ?, isActive = ?  WHERE categoryId = ?");
                preparedStatement.setString(1, category.getCategoryName());
                preparedStatement.setBoolean(2, true);
                preparedStatement.setInt(3, category.getCategoryId());
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not update category " + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    } finally {
                        preparedStatement = null;
                    }
                }
            }
        }
        if (retVal) {
            allCategoryList = getAllCategoriesFromDb();
        }
        return retVal;
    }

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
//        System.out.println(new CategoryDaoImpl(con).addCategory(new Category(0, "lungisani", true)));
//        System.out.println("Ans:"+ new CategoryDaoImpl(con).getCategory(5));
//        System.out.println(new CategoryDaoImpl(con).getAllCategories());
        System.out.println(new CategoryDaoImpl(con).addCategory(new Category(0, "Cakes", true)));
        System.out.println(new CategoryDaoImpl(con).addCategory(new Category(0, "Cookies", true)));
        System.out.println(new CategoryDaoImpl(con).addCategory(new Category(0, "Bread", true)));
        System.out.println(new CategoryDaoImpl(con).addCategory(new Category(0, "Muffins", true)));
        System.out.println(new CategoryDaoImpl(con).addCategory(new Category(0, "Desserts", true)));
        System.out.println(new CategoryDaoImpl(con).addCategory(new Category(0, "Pie and Tarts", true)));
        System.out.println(new CategoryDaoImpl(con).addCategory(new Category(0, "Pizza", true)));
        System.out.println(new CategoryDaoImpl(con).addCategory(new Category(0, "Scones", true)));
        System.out.println(new CategoryDaoImpl(con).addCategory(new Category(0, "CupCakes", true)));
         System.out.println(new CategoryDaoImpl(con).addCategory(new Category(0, "Doughnuts ", true)));

    }
    // ********************************************************************************************

}
