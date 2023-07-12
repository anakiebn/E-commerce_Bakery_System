package za.co.groupA.Service;

import java.util.Map;
import za.co.groupA.Model.CartLineItem;
import za.co.groupA.Model.Product;
import za.co.groupA.exception.NoProductException;

public interface ShoppingCartService {

    public Map<Integer, CartLineItem> getCart();

    public boolean removeFromCart(Product product) throws NoProductException;

    public boolean addToCart(Product product) throws NoProductException;

}
