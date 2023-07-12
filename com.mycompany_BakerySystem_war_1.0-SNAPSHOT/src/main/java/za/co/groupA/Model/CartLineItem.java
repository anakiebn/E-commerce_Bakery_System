
package za.co.groupA.Model;

import java.io.Serializable;
import java.util.Objects;

public class CartLineItem implements Serializable  {
    private int productQty;
    private Product product;

    public CartLineItem(int productQty, Product product) {
        this.productQty = productQty;
        this.product = product;
    }
    
    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.product);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CartLineItem other = (CartLineItem) obj;
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CartLineItem{" + "productQty=" + productQty + ", product=" + product + '}';
    }
    
    
}
