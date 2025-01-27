package service;

import data.LibraryData;
import model.Admin;
import model.Book;
import model.Category;
import interfaces.AdminOperations;

import java.util.List;
import java.util.stream.Collectors;

public class AdminService implements AdminOperations {

    private static final String APPROVAL_CODE = "APPROVED";

    @Override
    public void addAdmin(Admin admin) {
        LibraryData.getUsers().put(admin.getId(), admin);
    }

    @Override
    public Admin findAdminById(String adminId) {
        return (Admin) LibraryData.getUsers().get(adminId);
    }

    @Override
    public void addCategory(Category category) {
        LibraryData.getCategories().put(category.getId(), category);
    }

    @Override
    public void addBook(Book book) {
        LibraryData.getBooks().put(book.getId(), book);
        System.out.println("Book added successfully: " + book.getTitle());
    }

    @Override
    public void removeBook(String bookId) {
        if (LibraryData.getBooks().remove(bookId) != null) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return LibraryData.getBooks().values().stream().collect(Collectors.toList());
    }

    public boolean isApprovalCodeValid(String code) {
        return APPROVAL_CODE.equals(code);
    }
}