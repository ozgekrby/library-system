package interfaces;

import model.Admin;
import model.Book;
import model.Category;

import java.util.List;

public interface AdminOperations {
    void addAdmin(Admin admin);
    Admin findAdminById(String adminId);
    void addCategory(Category category);
    void addBook(Book book);
    void removeBook(String bookId);
    List<Book> getAllBooks();
}