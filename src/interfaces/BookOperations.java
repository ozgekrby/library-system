package interfaces;

import model.Book;

import java.util.List;

public interface BookOperations {
    void addBook(Book book);
    void removeBook(String bookId);
    List<Book> getAllBooks();
}