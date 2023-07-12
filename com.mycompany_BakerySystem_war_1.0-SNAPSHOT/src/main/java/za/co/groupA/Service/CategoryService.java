package za.co.groupA.Service;

import java.util.List;
import za.co.groupA.Model.Category;

public interface CategoryService {

    public boolean addCategory(Category category);

    public List<Category> getAllCategories();

    public Category getCategory(int categoryId);

    public boolean deleteCategory(int categoryId, boolean delete);

    public boolean updateCategory(Category category);

}
