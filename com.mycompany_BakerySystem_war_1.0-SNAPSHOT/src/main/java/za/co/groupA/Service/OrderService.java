package za.co.groupA.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import za.co.groupA.Model.CartLineItem;
import za.co.groupA.Model.Order;
import za.co.groupA.Model.Product;

public interface OrderService {

    public boolean addOrder(Order order);

    public List<Order> getAllOrders();

    public Order getOrder(int orderId);

    public boolean deleteOrder(int orderId, int statusId);

    public boolean updateOrder(Order order);

    public Map<Integer, CartLineItem> getProductsOrdered(int orderId);

    public List<Order> ordersPlacedByDay(Date date);

    public List<Integer> noOfOrdersPerDay(int month, int year);

    public List<Integer> noOfOrdersPerWeek(int month, int year);

}
