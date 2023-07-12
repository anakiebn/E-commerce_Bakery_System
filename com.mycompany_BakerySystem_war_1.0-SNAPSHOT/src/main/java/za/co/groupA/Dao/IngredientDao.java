package za.co.groupA.Dao;

import za.co.groupA.Model.Ingredient;
import java.util.List;

public interface IngredientDao {

    public boolean addIngredient(Ingredient ingredient);

    public List<Ingredient> getAllIngredients();

    public Ingredient getIngredient(int ingredientId);

//    public boolean deleteIngredient(Ingredient ingredient);
    public boolean updateIngredient(Ingredient ingredient);

    public boolean ingredientQtyAvailable(int ingredientId, int amount);

    public boolean ingredientAvailable(int ingredientId);

    public boolean subtractIngredientQty(int ingredientId, int amount);

    public boolean addIngredientQty(int ingredientId, int amount);

    public boolean deleteIngredient(int ingredientId, boolean delete) ;
}
