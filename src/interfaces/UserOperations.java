package interfaces;

import model.Reader;

public interface UserOperations {
    void addReader(Reader reader);
    void borrowBook(String readerId, String bookId);
    void returnBook(String readerId, String bookId);
}