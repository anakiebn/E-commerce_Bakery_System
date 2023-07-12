package za.co.groupA.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Recipe implements Serializable {

    private int recipeId;
    private String recipeName;
    private String recipeDescription;
    private List<Ingredient> ingredient;
    private boolean isActive;


    public Recipe(){
        
    }
    
    public Recipe(int recipeId, String recipeName, String recipeDescription, List<Ingredient> ingredient, boolean isActive) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.ingredient = ingredient;
        this.isActive = isActive;
    }

    public Recipe(int recipeId, String recipeName, String recipeDescription, boolean isActive) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.isActive = isActive;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public List<Ingredient> getIngredients() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.recipeId;
        hash = 61 * hash + Objects.hashCode(this.recipeName);
        hash = 61 * hash + Objects.hashCode(this.recipeDescription);
        hash = 61 * hash + Objects.hashCode(this.ingredient);
        hash = 61 * hash + (this.isActive ? 1 : 0);
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
        final Recipe other = (Recipe) obj;
        if (this.recipeId != other.recipeId) {
            return false;
        }
        if (this.isActive != other.isActive) {
            return false;
        }
        if (!Objects.equals(this.recipeName, other.recipeName)) {
            return false;
        }
        if (!Objects.equals(this.recipeDescription, other.recipeDescription)) {
            return false;
        }
        if (!Objects.equals(this.ingredient, other.ingredient)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Recipe{" + "recipeId=" + recipeId + ", recipeName=" + recipeName + ", recipeDescription=" + recipeDescription + ", ingredient=" + ingredient + ", isActive=" + isActive + '}';
    }
    
    

    
}
