package interfaces;

import model.Book;

import java.util.List;

public interface BookOperations {
    void addBook(Book book);
    void removeBook(String bookId);
    List<Book> getAllBooks();
    void updateBook(String bookId, String title, String author, String category);
    List<Book> getBooksByAuthor(String author);
}