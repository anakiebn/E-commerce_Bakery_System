package za.co.groupA.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Dao.ProductDao;
import za.co.groupA.Model.Product;

public class ProductDaoImpl implements ProductDao {

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static ProductDao productDaoImpl = null;
    List<Product> allProductList;

    //***************************************************************************************************************
    private ProductDaoImpl(Connection con) {
        this.con = con;
        allProductList = getAllProductsFromDb();

    }

    //************************************************************************************************
    public static ProductDao getInstance(DBPoolManagerBasic dbm) {
        if (productDaoImpl == null) {
            try {
                productDaoImpl = new ProductDaoImpl(dbm.getConnection());
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection " + ex.getMessage());
            }
        }
        return productDaoImpl;
    }

    //****************************************************************************************************
    public static ProductDao getProductDaoImplInstance() {
        return productDaoImpl;
    }
//  ********************************************************************************************************

    private List<Product> getAllProductsFromDb() {
        List<Product> productList = new ArrayList<>();
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT product.productId, product.productName, product.productDescription, product.productWarnings, product.productNutrientsInfo, product.price,product.categoryId, product.recipeId,product.isAvailable, product.image FROM product JOIN category ON product.categoryId = category.categoryid JOIN  recipe ON recipe.recipeId = product.recipeId");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    productList.add(new Product(
                            resultSet.getInt("productId"),
                            resultSet.getString("productName"),
                            resultSet.getString("productDescription"),
                            resultSet.getString("productWarnings"),
                            resultSet.getString("productNutrientsInfo"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("categoryId"),
                            resultSet.getInt("recipeId"),
                            resultSet.getBoolean("isAvailable"),
                            resultSet.getString("image")));
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
        return productList;
    }

    //***********************************************************************************************************************************
    public boolean idExists(int productId) {
        return allProductList.stream().anyMatch(pro -> pro.getProductId() == productId);
    }

//*******************************************************************************************************************************************************
    @Override
    public boolean addProduct(Product product) {
        boolean retVal = false;
        if (con != null && !idExists(product.getProductId())) {
            try {
                preparedStatement = con.prepareStatement("INSERT INTO product (productId, productName, productDescription, productWarnings, productNutrientsInfo,categoryId, recipeId, isAvailable, price, image) VALUES(?, ?, ? ,?,?,?, ?,?,?, ?)");
                preparedStatement.setInt(1, product.getProductId());
                preparedStatement.setString(2, product.getProductName());
                preparedStatement.setString(3, product.getProductDescription());
                preparedStatement.setString(4, product.getProductWarnings());
                preparedStatement.setString(5, product.getProductNutrientsInfo());
                preparedStatement.setObject(6, product.getCategoryId());
                preparedStatement.setObject(7, product.getRecipeId());
                preparedStatement.setBoolean(8, true);
                preparedStatement.setDouble(9, product.getPrice());
                preparedStatement.setString(10, product.getImage());

                if (preparedStatement.executeUpdate() > 0) {
                    allProductList.add(product);
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not add product: " + ex.getMessage());
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
    public List<Product> getAllProducts() {
        return new ArrayList(allProductList);
    }

    //**************************************************************************************************************************************
    @Override
    public Product getProductByName(String productName) {
        return allProductList.stream().filter(prod -> prod.getProductName().equalsIgnoreCase(productName)).findFirst().orElse(null);
    }

    //**********************************************************************************************************************************************
    @Override
    public Product getProductByProductId(int productId) {
        return allProductList.stream().filter(pro -> pro.getProductId() == productId).findFirst().orElse(null);
    }

    //******************************************************************************************************
    @Override
    public List<Product> getProductByCategoryId(int categoryId) {
        return allProductList.stream().filter(pr -> pr.getCategoryId() == categoryId).collect(Collectors.toList());
    }

    //*****************************************************************************************************
    @Override
    public boolean updateProduct(Product product) {
        boolean retVal = false;
        if (con != null && idExists(product.getProductId())) {
            try {
                preparedStatement = con.prepareStatement("UPDATE product SET productName = ? , productDescription = ?, productWarnings = ? ,productNutrientsInfo = ?,isAvailable = ?, price = ?, image = ?, categoryId = ?, recipeId = ? WHERE productId = ?");
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setString(2, product.getProductDescription());
                preparedStatement.setString(3, product.getProductWarnings());
                preparedStatement.setString(4, product.getProductNutrientsInfo());
                preparedStatement.setBoolean(5, retVal);
                preparedStatement.setDouble(6, product.getPrice());
                preparedStatement.setString(7, product.getImage());
                preparedStatement.setInt(8, product.getCategoryId());
                preparedStatement.setInt(9, product.getRecipeId());
                preparedStatement.setInt(10, product.getProductId());

                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not update product " + ex.getMessage());
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
            allProductList = getAllProductsFromDb();
        }
        return retVal;

    }

    //*************************************************************************************************************************************
    @Override
    public boolean deleteProduct(int productId, boolean delete) {
        boolean retVal = false;

        if (con != null && idExists(productId)) {
            try {
                preparedStatement = con.prepareStatement("UPDATE product set isAvailable = ? WHERE productId = ?");
                preparedStatement.setBoolean(1, delete);
                preparedStatement.setInt(2, productId);
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
            allProductList = getAllProductsFromDb();
        }
        return retVal;
    }

    //===========================================================================================
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
// public Product(int productId, String productName, String productDescription, String productWarnings, String productNutrientsInfo, double price, int categoryId, int recipeId, boolean isAvailable, String image) {
        System.out.println(new ProductDaoImpl(con).updateProduct(new Product(26, "ria", "short", "sturbbon", "nice heart", 300.0, 1, 13, true, "riiii")));
//        System.out.println(new ProductDaoImpl(con).getAllProducts());
//        System.out.println(new ProductDaoImpl(con).getAllProductsFromDb());
//        System.out.println(new ProductDaoImpl(con).updateProduct(product));
//           System.out.println(new ProductDaoImpl(con).getProduct("ria"));
//           System.out.println(new ProductDaoImpl(con).deleteProduct(1, true));
    }

}
