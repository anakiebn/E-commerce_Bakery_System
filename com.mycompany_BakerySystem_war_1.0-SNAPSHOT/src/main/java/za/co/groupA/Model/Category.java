package za.co.groupA.Model;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {

    private int categoryId;
    private String categoryName;
    private boolean isActive;

    public Category() {
    }

    public Category(int categoryId, String categoryName, boolean isActive) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.isActive = isActive;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.categoryId;
        hash = 97 * hash + Objects.hashCode(this.categoryName);
        hash = 97 * hash + (this.isActive ? 1 : 0);
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
        final Category other = (Category) obj;
        if (this.categoryId != other.categoryId) {
            return false;
        }
        if (this.isActive != other.isActive) {
            return false;
        }
        return Objects.equals(this.categoryName, other.categoryName);
    }

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + categoryId + ", categoryName=" + categoryName + ", isActive=" + isActive + '}';
    }

}
