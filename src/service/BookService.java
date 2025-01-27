package service;

import data.LibraryData;
import model.Book;
import interfaces.BookOperations;

import java.util.List;

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
}