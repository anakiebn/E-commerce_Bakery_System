package za.co.groupA.Dao;

import java.util.List;
import java.util.Map;
import za.co.groupA.Model.CartLineItem;
import za.co.groupA.Model.Order;

public interface OrderDao {

    public boolean addOrder(Order order);

    public List<Order> getAllOrders();

    public Order getOrder(int orderId);

    public boolean deleteOrder(int orderId, int statusId);
    
    public boolean updateOrder(Order order);
    
    public Map <Integer, CartLineItem> getProductsOrdered(int orderId);

}
