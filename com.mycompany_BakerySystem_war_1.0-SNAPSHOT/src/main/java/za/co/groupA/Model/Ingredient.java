package za.co.groupA.Model;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Serializable  {

    private int ingredientId;
    private String ingredientName;
    private int quantity;
    private int unitId;
    private int minimumQty;
    private boolean isAvailable;

    public Ingredient(){
        
    }
    
    public Ingredient(int ingredientId, String ingredientName, int quantity, int unitId, int minimumQty, boolean isAvailable) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unitId = unitId;
        this.minimumQty = minimumQty;
        this.isAvailable = isAvailable;
    }

    public Ingredient(int ingredientId, int quantity, int unitId, boolean isAvailable) {
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.unitId = unitId;
        this.isAvailable = isAvailable;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getMinimumQty() {
        return minimumQty;
    }

    public void setMinimumQty(int minimumQty) {
        this.minimumQty = minimumQty;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.ingredientId;
        hash = 97 * hash + Objects.hashCode(this.ingredientName);
        hash = 97 * hash + this.quantity;
        hash = 97 * hash + this.unitId;
        hash = 97 * hash + this.minimumQty;
        hash = 97 * hash + (this.isAvailable ? 1 : 0);
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
        final Ingredient other = (Ingredient) obj;
        if (this.ingredientId != other.ingredientId) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.unitId != other.unitId) {
            return false;
        }
        if (this.minimumQty != other.minimumQty) {
            return false;
        }
        if (this.isAvailable != other.isAvailable) {
            return false;
        }
        if (!Objects.equals(this.ingredientName, other.ingredientName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "ingredientId=" + ingredientId + ", ingredientName=" + ingredientName + ", quantity=" + quantity + ", unitId=" + unitId + ", minimumQty=" + minimumQty + ", isAvailable=" + isAvailable + '}';
    }
    
    

    

}
