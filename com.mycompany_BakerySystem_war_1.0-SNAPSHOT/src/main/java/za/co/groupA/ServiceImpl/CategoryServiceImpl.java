package za.co.groupA.ServiceImpl;

import za.co.groupA.Dao.CategoryDao;
import java.util.List;
import za.co.groupA.Model.Category;
import za.co.groupA.Service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public boolean addCategory(Category category) throws NullPointerException {
        return categoryDao.addCategory(category);
    }

   

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories() != null ? categoryDao.getAllCategories() : null;
    }

  

    @Override
    public Category getCategory(int categoryId) {
    return categoryDao.getCategory(categoryId)!=null?categoryDao.getCategory(categoryId):null;
    }


    @Override
    public boolean updateCategory(Category category) {
        return categoryDao.updateCategory(category);
    }

    @Override
    public boolean deleteCategory(int categoryId, boolean delete) {
        
        return categoryDao.deleteCategory(categoryId, delete);
    }

}
