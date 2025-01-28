package service;

import data.LibraryData;
import model.Book;
import interfaces.BookOperations;

import java.util.List;
import java.util.stream.Collectors;


public class BookService implements BookOperations {

    @Override
    public void addBook(Book book) {
        LibraryData.getBooks().put(book.getId(), book);
    }

    @Override
    public void removeBook(String bookId) {
        LibraryData.getBooks().remove(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        return LibraryData.getBooks().values().stream().toList();
    }

    @Override
    public void updateBook(String bookId, String title, String author, String category) {
        Book book = LibraryData.getBooks().get(bookId);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            System.out.println("Book updated successfully: " + title);
        } else {
            System.out.println("Book not found.");
        }
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return LibraryData.getBooks().values().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public Book getBookById(String bookId) {
        return LibraryData.getBooks().get(bookId);
    }

    public List<Book> getBooksByTitle(String title) {
        return LibraryData.getBooks().values().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByCategory(String category) {
        return LibraryData.getBooks().values().stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}