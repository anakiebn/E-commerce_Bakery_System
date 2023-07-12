package za.co.groupA.Service;

import java.util.List;
import za.co.groupA.Model.Ingredient;
import za.co.groupA.Model.Recipe;

public interface RecipeService {

    public boolean addRecipe(Recipe recipe);

    public List<Recipe> getAllRecipes();

    public Recipe getRecipe(int recipeId);

    public boolean deleteRecipe(int recipeId, boolean delete);

    public boolean updateRecipe(Recipe recipe);

     public List<Ingredient> getRecipeIngredients(int recipeId);
}