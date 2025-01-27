package data;

import model.Book;
import model.Category;
import model.Person;
import model.Reader;
import model.Admin;

import java.util.HashMap;
import java.util.Map;

public class LibraryData {
    private static final Map<String, Person> users = new HashMap<>();
    private static final Map<String, Book> books = new HashMap<>();
    private static final Map<String, Category> categories = new HashMap<>();

    public static Map<String, Person> getUsers() {
        return users;
    }

    public static Map<String, Book> getBooks() {
        return books;
    }

    public static Map<String, Category> getCategories() {
        return categories;
    }

    static {
        categories.put("1", new Category("1", "Fiction"));
        categories.put("2", new Category("2", "Non-Fiction"));
        categories.put("3", new Category("3", "Science"));

        books.put("101", new Book("101", "1984", "George Orwell", "Fiction"));
        books.put("102", new Book("102", "Sapiens", "Yuval Noah Harari", "Non-Fiction"));
        books.put("103", new Book("103", "A Brief History of Time", "Stephen Hawking", "Science"));

        Admin admin = new Admin("admin01", "LibraryAdmin", "secretKey123");
        users.put(admin.getId(), admin);

        users.put("reader01", new Reader("reader01", "John Doe"));
    }
}