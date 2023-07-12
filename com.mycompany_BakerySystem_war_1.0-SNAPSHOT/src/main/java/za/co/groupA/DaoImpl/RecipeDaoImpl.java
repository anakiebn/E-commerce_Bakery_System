package za.co.groupA.DaoImpl;

import za.co.groupA.Dao.RecipeDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Model.Ingredient;
import za.co.groupA.Model.Recipe;

public class RecipeDaoImpl implements RecipeDao {

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static RecipeDao recipeDaoImpl = null;
    List<Recipe> allRecipeList;

// *********************************************************************************
    private RecipeDaoImpl(Connection con) {
        this.con = con;
        allRecipeList = getAllRecipeFromDb();

    }

// *********************************************************************************
   public static RecipeDao getInstance(DBPoolManagerBasic db) {
        if (recipeDaoImpl == null) {
            try {
                recipeDaoImpl = new RecipeDaoImpl(db.getConnection());
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection");
            }
        }
        return recipeDaoImpl;
    }

// ********************************************************************************* 
    private List<Recipe> getAllRecipeFromDb() {
        List<Recipe> recipeList = new ArrayList<>();
        if (con != null) {
            try {
                preparedStatement = con.prepareStatement("SELECT recipeId, recipeName,recipeDescription, isActive FROM recipe");
                
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    recipeList.add(new Recipe(resultSet.getInt("recipeId"), resultSet.getString("recipeName"), resultSet.getString("recipeDescription"), resultSet.getBoolean("isActive")));
                }
            } catch (SQLException ex) {
                System.out.println(" getAllRecipeFromDb ERROR!" + ex.getMessage());
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
        return recipeList;
    }

// *********************************************************************************    
    @Override
    public boolean addRecipe(Recipe recipe) {
        boolean retVal = false;
        boolean inOk = true;

        if (con != null && !idExists(recipe.getRecipeId())) {

            try {
                con.setAutoCommit(false);
                preparedStatement = con.prepareStatement("INSERT INTO recipe(recipeId,recipeName,recipeDescription,isActive) values(?,?,?,?)");
                preparedStatement.setInt(1, recipe.getRecipeId());
                preparedStatement.setString(2, recipe.getRecipeName());
                preparedStatement.setString(3, recipe.getRecipeDescription());
                preparedStatement.setBoolean(4, true);
                if (preparedStatement.executeUpdate() > 0) {
                    preparedStatement = con.prepareStatement("SELECT LAST_INSERT_ID()");
                    resultSet = preparedStatement.executeQuery();
                    int recipeId = 1;
                    if (resultSet.next()) {
                        recipeId = resultSet.getInt(1);
                    }
                    preparedStatement = con.prepareStatement("INSERT INTO recipe_ingredient (recipeId, ingredientId , quantity , unit ) VALUES(?,?,?,?) ");
                    for (Ingredient ingredient : recipe.getIngredients()) {
                        preparedStatement.setInt(1, recipeId);
                        preparedStatement.setInt(2, ingredient.getIngredientId());
                        preparedStatement.setInt(3, ingredient.getQuantity());
                        preparedStatement.setInt(4, ingredient.getUnitId());
                        if (preparedStatement.executeUpdate() < 1) {
                            con.rollback();
                            inOk = false;
                            break;
                        }
                    }
                }
                if (inOk) {
                    con.commit();
                    allRecipeList.add(recipe);
                    retVal = true;
                }
            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                }
                System.out.println(" add ingredient Error!!: " + ex.getMessage());
            } finally {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                }
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

// *********************************************************************************   
    @Override
    public List<Recipe> getAllRecipes() {
        return new ArrayList(allRecipeList);
    }

// *********************************************************************************   
    public boolean idExists(int recipeId){
        return allRecipeList.stream().anyMatch(recp -> recp.getRecipeId() == recipeId);
    }
    //******************************************************************************
    public boolean nameExists(String recipename) {
        return allRecipeList.stream().anyMatch(rec -> rec.getRecipeName().equalsIgnoreCase(recipename));
    }

// *********************************************************************************   
    @Override
    public Recipe getRecipe(int recipeId) {
        return allRecipeList.stream().filter(recipe -> recipe.getRecipeId() == recipeId).findFirst().orElse(null);
    }

// *********************************************************************************
    @Override
    public boolean deleteRecipe(int recipeId, boolean delete) {
        boolean retVal = false;

        if (con != null && idExists(recipeId) ) {
            try {
                preparedStatement = con.prepareStatement("UPDATE recipe SET isActive = ? WHERE recipeId = ?");
                preparedStatement.setBoolean(1, delete);
                preparedStatement.setInt(2, recipeId);
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println(" deleteRecipe ERROR" + ex.getMessage());
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
            allRecipeList = getAllRecipeFromDb();
        }
        return retVal;
    }

// *********************************************************************************
    @Override
    public boolean updateRecipe(Recipe recipe) {
        boolean retVal = false;
        if (con != null && idExists(recipe.getRecipeId())) {
            try {
                preparedStatement = con.prepareStatement("UPDATE recipe SET recipeName = ? , recipeDescription =? WHERE recipeId = ?");
                preparedStatement.setString(1, recipe.getRecipeName());
                preparedStatement.setString(2, recipe.getRecipeDescription());
                preparedStatement.setInt(3, recipe.getRecipeId());
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println(" updateRecipe ERROR" + ex.getMessage());
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
            allRecipeList = getAllRecipeFromDb();
        }
        return retVal;
    }
    //***********************************************************************************************
    
    @Override
      public List<Ingredient> getRecipeIngredients(int recipeId){
          List<Ingredient> recipeIngredient = new ArrayList();
          if (con != null){
                  try {
                      preparedStatement = con.prepareStatement("SELECT ingredientId, quantity, unit FROM recipe_ingredient WHERE recipeId = ?");
                      preparedStatement.setInt(1, recipeId);
                      resultSet = preparedStatement.executeQuery();
                      while(resultSet.next()){
                          recipeIngredient.add(new Ingredient(resultSet.getInt("ingredientId"), resultSet.getInt("quantity"),resultSet.getInt("unit"), resultSet.getBoolean("isAvailable")));
                      }
                              } catch (SQLException ex) {
                      System.out.println ("error"+ex);
                  }
          }
          return recipeIngredient;
      }

//    #####################################################################################################
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
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(27, "milk", 1, 12,1,true));
        ingredients.add(new Ingredient(24, "flour", 2, 13,1,true));
        ingredients.add(new Ingredient(26, "oil", 1, 12,1,true));
        ingredients.add(new Ingredient(32, "cocoa powder", 1, 12,1,true));
        ingredients.add(new Ingredient(37, "essence", 1, 12,1,true));
        ingredients.add(new Ingredient(35, "eggs", 5, 12,1,true));
       // System.out.println(new RecipeDaoImpl(con).addRecipe(new Recipe(0, "chocolate cake recipe", "add the warm milk, add the flour, add 1kg flour,add 2 teaspoon of salt", ingredients, true)));
          System.out.println(new RecipeDaoImpl(con).getAllRecipeFromDb());
//          System.out.println(new RecipeDaoImpl(con).getRecipe(2).getIngredients());
        System.out.println(new RecipeDaoImpl(con).deleteRecipe(13, false));
//        System.out.println(new RecipeDaoImpl(con).updateRecipe(new Recipe(5,"riah", "water only")));
//        System.out.println(new RecipeDaoImpl(con).getAllRecipeFromDb());

//        System.out.println("adding ingredient to recipe..." + new RecipeDaoImpl(con).addIngredientToRecipe(1, 2));
//          System.out.println(new RecipeDaoImpl(con).getRecipeIngredients(12));
//                    System.out.println(new RecipeDaoImpl(con).getRecipe(12));

    }
}
