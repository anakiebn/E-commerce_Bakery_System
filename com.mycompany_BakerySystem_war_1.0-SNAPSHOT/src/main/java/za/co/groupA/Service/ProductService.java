package za.co.groupA.Service;

import java.util.List;
import za.co.groupA.Model.Product;

public interface ProductService {

    public boolean addProduct(Product product);

    public List<Product> getAllProducts();

    public Product getProductByName(String productName);

    public Product getProductByProductId(int productId);

    public List<Product> popularProductPerCatagory();

    public boolean updateProduct(Product product);

    public boolean deleteProduct(int productId, boolean delete);

    public List<Product> getProductByCategoryId(int categoryId);

}
