package interfaces;

import model.Category;

import java.util.List;

public interface CategoryOperations {
    void addCategory(Category category);
    List<Category> getAllCategories();
}