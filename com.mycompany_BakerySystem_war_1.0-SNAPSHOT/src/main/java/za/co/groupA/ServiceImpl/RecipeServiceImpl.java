package za.co.groupA.ServiceImpl;

import za.co.groupA.Dao.RecipeDao;
import java.util.List;
import za.co.groupA.Model.Ingredient;
import za.co.groupA.Model.Recipe;
import za.co.groupA.Service.IngredientService;
import za.co.groupA.Service.RecipeService;

public class RecipeServiceImpl implements RecipeService {
    
    RecipeDao recipeDao;
    IngredientService ingredientService;
    
    public RecipeServiceImpl(RecipeDao recipeDao) {
        this.ingredientService = ingredientService;
        this.recipeDao = recipeDao;
    }
    
    @Override
    public boolean addRecipe(Recipe recipe) {
        return recipeDao.addRecipe(recipe);
    }
    
    @Override
    public List<Recipe> getAllRecipes() {
        return recipeDao.getAllRecipes() != null ? recipeDao.getAllRecipes() : null;
    }
    
    @Override
    public Recipe getRecipe(int recipeId) {
        return recipeDao.getRecipe(recipeId) != null ? recipeDao.getRecipe(recipeId) : null;
    }
    
    @Override
    public boolean deleteRecipe(int recipeId, boolean delete) {
        return recipeDao.deleteRecipe(recipeId, delete);
    }
    
    @Override
    public boolean updateRecipe(Recipe recipe) {
        return recipeDao.updateRecipe(recipe);
    }
    
    @Override
    public List<Ingredient> getRecipeIngredients(int recipeId) {
        return recipeDao.getRecipeIngredients(recipeId);
    }
}
