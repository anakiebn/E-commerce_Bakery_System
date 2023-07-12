
package za.co.groupA.ServiceImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
;
import java.util.stream.Collectors;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Dao.IngredientDao;
import za.co.groupA.Dao.OrderDao;
import za.co.groupA.Dao.ProductDao;
import za.co.groupA.Dao.RecipeDao;
import za.co.groupA.DaoImpl.IngredientDaoImpl;
import za.co.groupA.DaoImpl.OrderDaoImpl;
import za.co.groupA.DaoImpl.ProductDaoImpl;
import za.co.groupA.DaoImpl.RecipeDaoImpl;
import za.co.groupA.Model.CartLineItem;
import za.co.groupA.Model.Ingredient;
import za.co.groupA.Model.Order;
import za.co.groupA.Service.OrderService;
import za.co.groupA.exception.NoProductException;



public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final IngredientDao ingredientDao;
    private final RecipeDao recipeDao;
    private final ProductDao productDao;

    public OrderServiceImpl(DBPoolManagerBasic db) {

        this.orderDao = OrderDaoImpl.getInstance(db);
        recipeDao = RecipeDaoImpl.getInstance(db);
        ingredientDao = IngredientDaoImpl.getInstance(db);
        productDao = ProductDaoImpl.getInstance(db);

    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> ordersPlacedByDay(Date date) {
        return orderDao.getAllOrders().stream().filter(order -> order.getOrderDate().equals(date)).collect(Collectors.toList());
    }

    public List<Order> outstandingOrders() {
        return orderDao.getAllOrders().stream().filter(order -> order.getStatus() == 1).collect(Collectors.toList());
        //assuming status 1=outstanding
    }

    public List<Order> deliveredOrders() {
        //assuming status 2=delivered

        return orderDao.getAllOrders().stream().filter(order -> order.getStatus() == 2).collect(Collectors.toList());
    }

    @Override
    public List<Integer> noOfOrdersPerDay(int month, int year) {

        LocalDate date = LocalDate.of(year, month, 1);
        List<Integer> ordersPerDay = new ArrayList();
        int count = 0;
        for (int i = 0; i < date.lengthOfMonth();) {

            for (Order order : orderDao.getAllOrders()) {
                if (date.getDayOfWeek().getValue() <= 5 && date.equals(order.getOrderDate().toLocalDate())) {
                    count++;
                }
            }

            if (date.getDayOfWeek().getValue() == 5) {
                date = date.plusDays(3);
                i += 3;
                ordersPerDay.add(count);
                count = 0;

                continue;
            }

            ordersPerDay.add(count);
            date = date.plusDays(1);
            count = 0;
            i++;

        }
        return ordersPerDay;
    }

    @Override
    public List<Integer> noOfOrdersPerWeek(int month, int year) {
        LocalDate date = LocalDate.of(year, month, 1);
        List<Integer> ordersPerWeek = new ArrayList();
        int count = 0;
        for (int i = 0; i < date.lengthOfMonth(); i++) {

            for (Order order : orderDao.getAllOrders()) {

                if (date.getDayOfWeek().getValue() <= 5 && date.equals(order.getOrderDate().toLocalDate())) {
                    count++;
                }

            }
            if (date.getDayOfWeek().getValue() == 5) {
                ordersPerWeek.add(count);

                date = date.plusDays(2);
                count = 0;
                continue;
            }
            date = date.plusDays(1);

        }

        return ordersPerWeek;
    }

    @Override
    public Order getOrder(int orderId) {
        return orderDao.getOrder(orderId);
    }

    @Override
    public boolean deleteOrder(int orderId, int statusId) {
        return orderDao.deleteOrder(orderId, statusId);
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }

    @Override
    public Map<Integer, CartLineItem> getProductsOrdered(int orderId) {
        return orderDao.getProductsOrdered(orderId);
    }

    @Override
    public boolean addOrder(Order order) {
        if (order.getProducts_In_A_Cart().isEmpty()) {
            try {
                throw new NoProductException("There are no products in the cart");
            } catch (NoProductException ex) {
                System.out.println(ex);
                return false;
            }
        }

        for (int key : order.getProducts_In_A_Cart().keySet()) {
            int recipeId = order.getProducts_In_A_Cart().get(key).getProduct().getRecipeId();
            List<Ingredient> recipeIngredients = recipeDao.getRecipeIngredients(recipeId);
            for (Ingredient recipeIngr : recipeIngredients) {
                ingredientDao.subtractIngredientQty(recipeIngr.getIngredientId(), recipeIngr.getQuantity() * order.getProducts_In_A_Cart().get(key).getProductQty());
            }
        }

        return orderDao.addOrder(order);
    }

    public static void main(String[] args) throws NoProductException {
        LocalDate date = LocalDate.of(2023, 6, 2);
        List<Integer> ordersPerDay = new ArrayList();

            if (date.getDayOfWeek().getValue() == 5) {
                date = date.plusDays(3);
                System.out.println(date);
                
            }

        

    }

}
