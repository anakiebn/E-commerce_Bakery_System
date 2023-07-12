package za.co.groupA.ServiceImpl;

import za.co.groupA.Dao.IngredientDao;
import java.util.List;
import java.util.stream.Collectors;
import za.co.groupA.Model.Ingredient;
import za.co.groupA.Service.IngredientService;

public class IngredientServiceImpl implements IngredientService {

    IngredientDao ingredientDao;

    public IngredientServiceImpl(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @Override
    public boolean addIngredient(Ingredient ingredient) {
        return ingredientDao.addIngredient(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientDao.getAllIngredients() != null ? ingredientDao.getAllIngredients() : null;
    }

    @Override
    public Ingredient getIngredient(int ingredientId) {
        return ingredientDao.getIngredient(ingredientId) ;
    }

    @Override
    public boolean deleteIngredient(int ingredientId, boolean delete) {

        return ingredientDao.deleteIngredient(ingredientId,delete);
    }

    public List<Ingredient> ingredientsToOrder() {
        return ingredientDao.getAllIngredients().stream().filter(order -> order.getMinimumQty() > order.getQuantity()).collect(Collectors.toList());
    }

    @Override
    public boolean updateIngredient(Ingredient ingredient) {
        return ingredientDao.updateIngredient(ingredient);
    }

    @Override
    public boolean ingredientQtyAvailable(int ingredientId, int amount) {
        return ingredientDao.ingredientQtyAvailable(ingredientId, amount);
    }

    @Override
    public boolean ingredientAvailable(int ingredientId) {
        return ingredientDao.ingredientAvailable(ingredientId);
    }

    @Override
    public boolean subtractIngredientQty(int ingredientId, int amount) {

        return ingredientDao.subtractIngredientQty(ingredientId, amount);
    }

    @Override
    public boolean addIngredientQty(int ingredientId, int amount) {
        return ingredientDao.addIngredientQty(ingredientId, amount);
    }



}
