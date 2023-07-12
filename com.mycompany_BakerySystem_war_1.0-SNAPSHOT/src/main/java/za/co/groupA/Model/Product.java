
package za.co.groupA.Model;

import java.io.Serializable;
import java.util.Objects;


public class Product implements Serializable  {
    
    private int productId;
    private String productName;
    private String productDescription;
    private String productWarnings;
    private String productNutrientsInfo;
    private double  price;
    private int categoryId;
    private int recipeId;
    private boolean isAvailable;
    private String image;
    
   public Product(){
       
   }
    
    public Product(int productId, String productName, String productDescription, String productWarnings, String productNutrientsInfo, double price, int categoryId, int recipeId, boolean isAvailable, String image) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productWarnings = productWarnings;
        this.productNutrientsInfo = productNutrientsInfo;
        this.price = price;
        this.categoryId = categoryId;
        this.recipeId = recipeId;
        this.isAvailable = isAvailable;
        this.image = image;
    }
    
    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int prodcutId) {
        this.productId = prodcutId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductWarnings() {
        return productWarnings;
    }

    public void setProductWarnings(String productWarnings) {
        this.productWarnings = productWarnings;
    }

    public String getProductNutrientsInfo() {
        return productNutrientsInfo;
    }

    public void setProductNutrientsInfo(String productNutrientsInfo) {
        this.productNutrientsInfo = productNutrientsInfo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.productId;
        hash = 59 * hash + Objects.hashCode(this.productName);
        hash = 59 * hash + Objects.hashCode(this.productDescription);
        hash = 59 * hash + Objects.hashCode(this.productWarnings);
        hash = 59 * hash + Objects.hashCode(this.productNutrientsInfo);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 59 * hash + this.categoryId;
        hash = 59 * hash + this.recipeId;
        hash = 59 * hash + (this.isAvailable ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.image);
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
        final Product other = (Product) obj;
        if (this.productId != other.productId) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.categoryId != other.categoryId) {
            return false;
        }
        if (this.recipeId != other.recipeId) {
            return false;
        }
        if (this.isAvailable != other.isAvailable) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.productDescription, other.productDescription)) {
            return false;
        }
        if (!Objects.equals(this.productWarnings, other.productWarnings)) {
            return false;
        }
        if (!Objects.equals(this.productNutrientsInfo, other.productNutrientsInfo)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", productDescription=" + productDescription + ", productWarnings=" + productWarnings + ", productNutrientsInfo=" + productNutrientsInfo + ", price=" + price + ", categoryId=" + categoryId + ", recipeId=" + recipeId + ", isAvailable=" + isAvailable + ", image=" + image + '}';
    } 
}
