package service;

import data.LibraryData;
import model.Category;
import interfaces.CategoryOperations;

import java.util.List;

public class CategoryService implements CategoryOperations {

    @Override
    public void addCategory(Category category) {
        LibraryData.getCategories().put(category.getId(), category);
    }

    @Override
    public List<Category> getAllCategories() {
        return LibraryData.getCategories().values().stream().toList();
    }
}