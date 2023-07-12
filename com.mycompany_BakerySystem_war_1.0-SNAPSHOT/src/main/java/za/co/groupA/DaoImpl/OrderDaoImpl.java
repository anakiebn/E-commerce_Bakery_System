package za.co.groupA.DaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Dao.OrderDao;
import za.co.groupA.Dao.ProductDao;
import za.co.groupA.Model.CartLineItem;
import za.co.groupA.Model.Order;

public class OrderDaoImpl implements OrderDao {

    private Connection con = null;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet = null;
    private static OrderDao orderDaoImpl = null;
    List<Order> allOrdersList;
//***********************************************************************************************************

    private OrderDaoImpl(Connection con) {
        this.con = con;
        allOrdersList = getAllOrdersFromDb();

    }
//***********************************************************************************************************

    public static OrderDao getInstance(DBPoolManagerBasic db) {
        if (orderDaoImpl == null) {
            try {
                orderDaoImpl = new OrderDaoImpl(db.getConnection());
            } catch (SQLException ex) {
                System.out.println("Cannot obtain a connection " + ex.getMessage());
            }
        }
        return orderDaoImpl;
    }
//****************************************************************************************************************

    private List<Order> getAllOrdersFromDb() {
        List<Order> allOrders = new ArrayList<>();
        if (con != null) {
            try {

                preparedStatement = con.prepareStatement("SELECT order.orderId, order.deliveryNoteId, order.emailAddress, order.addressId,DATE (order.orderDate) as orderDate,TIME (order.orderDate)as orderTime, order.status, order.totalAmount FROM `order` JOIN deliverynote ON order.deliveryNoteId = deliverynote.noteId JOIN user ON order.emailAddress = user.emailAddress JOIN address ON order.addressId = address.addressId JOIN status ON order.status = status.statusId ");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    allOrders.add(new Order(resultSet.getInt("orderId"),
                            resultSet.getInt("deliveryNoteId"),
                            resultSet.getString("emailAddress"),
                            resultSet.getInt("addressId"),
                            resultSet.getDate("orderDate"),
                            resultSet.getTime("orderTime"),
                            resultSet.getInt("status"),
                            resultSet.getDouble("totalAmount")));
                }
            } catch (SQLException ex) {
                System.out.println("could not get the orders " + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return allOrders;
    }

    //****************************************************************************************************
    public boolean idExists(int orderId) {
        return allOrdersList.stream().anyMatch(ord -> ord.getOrderId() == orderId);
    }

//*******************************************************************************************************
    @Override
    public boolean addOrder(Order order) {
        boolean retVal = false;
        boolean inOk = true;
        if (con != null && !idExists(order.getOrderId())) {

            try {
                con.setAutoCommit(false);
                preparedStatement = con.prepareStatement("INSERT INTO `bakerysystem`.`order` (`orderId`, `deliveryNoteId`, `emailAddress`, `addressId`, `orderDate`, `status`, `orderTime`, `totalAmount`) VALUES (?,?,?,?,?,?,?,?)");
                preparedStatement.setInt(1, order.getOrderId());
                preparedStatement.setInt(2, order.getDeliveryNoteId());
                preparedStatement.setString(3, order.getEmailAddress());
                preparedStatement.setInt(4, order.getAddressId());
                Timestamp orderDateTime = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                preparedStatement.setTimestamp(5, orderDateTime);
                preparedStatement.setInt(6, order.getStatus());
                preparedStatement.setTimestamp(7, orderDateTime);
                preparedStatement.setDouble(8, order.getTotalAmount());

                if (preparedStatement.executeUpdate() > 0) {
                    preparedStatement = con.prepareStatement("SELECT LAST_INSERT_ID()");
                    resultSet = preparedStatement.executeQuery();
                    int orderId = 1;
                    if (resultSet.next()) {
                        orderId = resultSet.getInt(1);
                    }

                    preparedStatement = con.prepareStatement("INSERT INTO order_product (orderId, productId, quantity) VALUES (?,?,?)");
                    for (Integer id : order.getProducts_In_A_Cart().keySet()) {
                        preparedStatement.setInt(1, orderId);
                        preparedStatement.setInt(2, id);
                        preparedStatement.setInt(3, order.getProducts_In_A_Cart().get(id).getProductQty());
                        if (preparedStatement.executeUpdate() < 1) {
                            con.rollback();
                            inOk = false;
                            break;

                        }

                    }

                }
                if (inOk) {
                    con.commit();
                    allOrdersList.add(order);
                    retVal = true;
                }

            } catch (SQLException ex) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                }
                System.out.println(" add order Error!!: " + ex.getMessage());
            } finally {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        return retVal;
    }
    //****************************************************************************************************

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList(allOrdersList);
    }

    //***********************************************************************************************************
    @Override
    public Order getOrder(int orderId) {
        return allOrdersList.stream().filter(ordr -> ordr.getOrderId() == orderId).findFirst().orElse(null);
    }
    //********************************************************************************************************

    @Override
    public boolean updateOrder(Order order) {
        boolean retVal = false;
        if (con != null && idExists(order.getOrderId())) {
            try {
                preparedStatement = con.prepareStatement("UPDATE `order` SET deliveryNoteId = ? , emailAddress =?, addressId=?, status= ?, totalAmount = ? WHERE orderId = ?");
                preparedStatement.setInt(1, order.getDeliveryNoteId());
                preparedStatement.setString(2, order.getEmailAddress());
                preparedStatement.setInt(3, order.getAddressId());
                preparedStatement.setInt(4, order.getStatus());
                preparedStatement.setDouble(5, order.getTotalAmount());
                preparedStatement.setInt(6, order.getOrderId());

                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println("could not update order" + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        if (retVal) {
            allOrdersList = getAllOrdersFromDb();
        }
        return retVal;

    }

    //*******************************************************************************************    
    @Override
    public boolean deleteOrder(int orderId, int statusId) {
        boolean retVal = false;

        if (con != null && idExists(orderId)) {
            try {
                preparedStatement = con.prepareStatement("UPDATE `order` SET status = ? WHERE orderId = ?");
                preparedStatement.setInt(1, statusId);
                preparedStatement.setInt(2, orderId);
                if (preparedStatement.executeUpdate() > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
                System.out.println(" deleteOrder ERROR" + ex.getMessage());
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException ex) {
                        System.out.println("Could not close: " + ex.getMessage());
                    }
                }
            }
        }
        if (retVal) {
            allOrdersList = getAllOrdersFromDb();
        }
        return retVal;

    }

    //***************************************************************************************************
    @Override
    public Map<Integer, CartLineItem> getProductsOrdered(int orderId) {
        Map<Integer, CartLineItem> productsOrdered = new HashMap<>();
        if (con != null) {
            ProductDao productDao = ProductDaoImpl.getProductDaoImplInstance();

            try {
                preparedStatement = con.prepareStatement("SELECT productId, quantity FROM order_product WHERE orderId = ?");
                preparedStatement.setInt(1, orderId);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    productsOrdered.put(resultSet.getInt("productId"), new CartLineItem(resultSet.getInt("quantity"), productDao.getProductByProductId(resultSet.getInt("productId"))));
                }
            } catch (SQLException ex) {
                System.out.println("error " + ex.getMessage());
            }
        }
        return productsOrdered;
    }

    //**********************************************************************************************************************
    public static void main(String[] args) {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bakerySystem", "root", "root"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (con != null) {
            System.out.println("Got connection");
        }
//        ProductDao productDao = ProductDaoImpl.getProductDaoImplInstance();
//        productDao.getAllProducts().stream().forEach(System.out::println);
//        Map<Integer, CartLineItem> cart = new HashMap<>();
////
//        cart.put(11, new CartLineItem(5, productDao.getProductByProductId(11)));
//        cart.put(12, new CartLineItem(3, productDao.getProductByProductId(12)));
//        cart.put(15, new CartLineItem(4, productDao.getProductByProductId(5)));

//        System.out.println(new OrderDaoImpl(con).addOrder(new Order(0, cart, 1, "sherman@gmail", 8, 1, 300.0)));
//        System.out.println(new OrderDaoImpl(con).getAllOrders());
//      Order o= new OrderDaoImpl(con).getOrder(25);
//      o.setStatus(1);
//       if(new OrderDaoImpl(con).updateOrder(o)){
//           System.out.println("updated");
//       }

                int year=2023;
                int month=06;
                int dayOfMonth=1;
                String monthh;
             
                LocalDate date=LocalDate.of(year, month, dayOfMonth);
                
        System.out.println(date.getMonth().toString());
//       
//         System.out.println(new OrderDaoImpl(con).deleteOrder(23, 1));
//        System.out.println(new OrderDaoImpl(con).getProductsOrdered(23));
//           System.out.println(new OrderDaoImpl(con).getAllOrdersFromDb());
//        System.out.println(new OrderDaoImpl(con).updateOrder(new Order(23, products, 1, "sherman@gmail", 9, 2, 300.0)));
    }

}
