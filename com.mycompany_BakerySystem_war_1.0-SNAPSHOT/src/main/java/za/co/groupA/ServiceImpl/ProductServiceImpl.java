package za.co.groupA.ServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import za.co.groupA.Dao.ProductDao;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import za.co.groupA.Dao.DBPoolManagerBasic;
import za.co.groupA.Dao.OrderDao;
import za.co.groupA.DaoImpl.OrderDaoImpl;
import za.co.groupA.DaoImpl.ProductDaoImpl;
import za.co.groupA.Model.Order;
import za.co.groupA.Model.Product;
import za.co.groupA.Service.ProductService;

public class ProductServiceImpl implements ProductService {

    private  ProductDao productDao;
    private OrderDao orderDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }


    @Override
    public boolean addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int productId, boolean delete) {
        return productDao.deleteProduct(productId, delete);
    }

    @Override
    public Product getProductByName(String productName) {
        return productDao.getProductByName(productName);
    }

    @Override
    public Product getProductByProductId(int productId) {
        return productDao.getProductByProductId(productId);
    }

    @Override
    public List<Product> getProductByCategoryId(int categoryId) {
        return productDao.getProductByCategoryId(categoryId);
    }

    @Override
    public List<Product> popularProductPerCatagory() {
        Map<Integer, Integer> productQty = new LinkedHashMap();
        List<Product> popularProducts = new ArrayList<>();
        for (Order order : orderDao.getAllOrders()) {
            for (int key : orderDao.getProductsOrdered(order.getOrderId()).keySet()) {
                int qty = orderDao.getProductsOrdered(order.getOrderId()).get(key).getProductQty();
                if (!productQty.containsKey(key)) {
                    productQty.put(key, orderDao.getProductsOrdered(order.getOrderId()).get(key).getProductQty());
                    continue;
                }
                productQty.put(key, productQty.get(key) + qty);
            }
        }
        productQty = productQty.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        for (int key : productQty.keySet()) {
            popularProducts.add(productDao.getProductByProductId(key));
        }
        return popularProducts;
    }

}
