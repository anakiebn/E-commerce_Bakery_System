package za.co.groupA.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.DaoImpl.IngredientDaoImpl;
import za.co.groupA.DaoImpl.RecipeDaoImpl;
import za.co.groupA.Model.CartLineItem;
import za.co.groupA.Model.Ingredient;
import za.co.groupA.Model.Product;
import za.co.groupA.Service.IngredientService;
import za.co.groupA.Service.RecipeService;
import za.co.groupA.Service.ShoppingCartService;
import za.co.groupA.exception.NoProductException;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    private Map<Integer, CartLineItem> cart;
    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private boolean isProductFound;

    public ShoppingCartServiceImpl(DBPoolManagerBasic db) {
        ingredientService = new IngredientServiceImpl(IngredientDaoImpl.getInstance(db));
        recipeService = new RecipeServiceImpl(RecipeDaoImpl.getInstance(db));
        cart = new HashMap();
        isProductFound = false;
    }

    @Override
    public boolean removeFromCart(Product product) throws NoProductException {
        boolean retVal = false;
        List<Ingredient> recipeIngredients = recipeService.getRecipeIngredients(product.getRecipeId());

        if (product == null) {
            throw new NoProductException("No Product found");
        }
        if (cart.containsKey(product.getProductId())) {
            for (Ingredient recipeIngr : recipeIngredients) {
                ingredientService.ingredientQtyAvailable(recipeIngr.getIngredientId(),-recipeIngr.getQuantity());
            }
                int qty = cart.get(product.getProductId()).getProductQty();
                qty -= 1;
                if (qty > 0) {
                    this.cart.put(product.getProductId(), new CartLineItem((qty), product));
                } else {
                    this.cart.remove(product.getProductId());
                }
            
            retVal = true;

        }

        return retVal;
    }

    //***********************************************************************************************************************************
    @Override
    public boolean addToCart(Product product) throws NoProductException {
        if (product == null) {
            throw new NoProductException("No Product to add ");
        }

        int qty = 1;
        if (cart.containsKey(product.getProductId())) {
            qty = cart.get(product.getProductId()).getProductQty();
            qty += 1;
        }

        List<Ingredient> recipeIngredients = recipeService.getRecipeIngredients(product.getRecipeId());
        boolean gotAll = true;
        for (Ingredient recipeIngr : recipeIngredients) {
            if (ingredientService.ingredientAvailable(recipeIngr.getIngredientId())) {
                gotAll &= ingredientService.ingredientQtyAvailable(recipeIngr.getIngredientId(), recipeIngr.getQuantity() * qty);
                if (!gotAll) {
                    break;
                }
            } else {
                gotAll = false;
                break;
            }

        }
        if (gotAll) {
            this.cart.put(product.getProductId(), new CartLineItem((qty), product));
        }

        return gotAll;

    }

//***********************************************************************************************************************************
    public Map<Integer, CartLineItem> getCart() {
        return new HashMap<>(cart);
    }

    public void setCart(Map<Integer, CartLineItem> cart) {
        this.cart = cart;
    }

}
