package model;

import java.util.HashMap;
import java.util.Map;

public class Reader extends Person {
    private static final int MAX_BOOKS = 5;
    private Map<Book, String> borrowedBooks = new HashMap<>();

    public Reader(String id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "Reader";
    }

    public boolean canBorrow() {
        return borrowedBooks.size() < MAX_BOOKS;
    }

    public void borrowBook(Book book, String dueDate) {
        borrowedBooks.put(book, dueDate);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public Map<Book, String> getBorrowedBooks() {
        return borrowedBooks;
    }
}