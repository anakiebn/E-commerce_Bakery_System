package za.co.groupA.Service;

import java.util.List;
import za.co.groupA.Model.Ingredient;

public interface IngredientService {

    public boolean addIngredient(Ingredient ingredient);

    public List<Ingredient> getAllIngredients();

    public Ingredient getIngredient(int ingredientId);

    public boolean deleteIngredient(int ingredientId, boolean delete);

    public boolean updateIngredient(Ingredient ingredient);

    public boolean ingredientQtyAvailable(int ingredientId, int amount);

    public boolean ingredientAvailable(int ingredientId);

    public boolean subtractIngredientQty(int ingredientId, int amount);

    public boolean addIngredientQty(int ingredientId, int amount);

}
