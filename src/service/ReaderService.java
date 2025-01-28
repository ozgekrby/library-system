package service;

import data.LibraryData;
import model.Book;
import model.Reader;
import interfaces.UserOperations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReaderService implements UserOperations {

    @Override
    public void addReader(Reader reader) {
        LibraryData.getUsers().put(reader.getId(), reader);
    }

    @Override
    public void borrowBook(String readerId, String bookId) {
        Reader reader = (Reader) LibraryData.getUsers().get(readerId);
        Book book = LibraryData.getBooks().get(bookId);

        if (reader == null) {
            System.out.println("Reader not found.");
            return;
        }

        if (book == null || !book.isAvailable()) {
            System.out.println("Book not available.");
            return;
        }

        if (reader.canBorrow()) {
            String dueDate = LocalDate.now().plusDays(14).toString();
            reader.borrowBook(book, dueDate);
            book.setAvailable(false);
            double fee = calculateFee(book);
            System.out.println("Book borrowed successfully: " + book.getTitle() + ". Fee: $" + fee);
        } else {
            System.out.println("Borrowing limit reached.");
        }
    }

    @Override
    public void returnBook(String readerId, String bookId) {
        Reader reader = (Reader) LibraryData.getUsers().get(readerId);
        Book book = LibraryData.getBooks().get(bookId);

        if (reader == null || book == null) {
            System.out.println("Invalid reader or book.");
            return;
        }

        if (!reader.getBorrowedBooks().containsKey(book)) {
            System.out.println("Book was not borrowed by this reader.");
            return;
        }

        String dueDate = reader.getBorrowedBooks().get(book);
        LocalDate due = LocalDate.parse(dueDate);
        LocalDate returned = LocalDate.now();
        long overdueDays = ChronoUnit.DAYS.between(due, returned);

        double penalty = 0;
        if (overdueDays > 0) {
            penalty = overdueDays * 1.5;
            System.out.println("Book returned late. Penalty: $" + penalty);
        } else {
            System.out.println("Book returned on time.");
        }

        reader.returnBook(book);
        book.setAvailable(true);
        System.out.println("Total fee to be refunded: $" + penalty);
    }

    private double calculateFee(Book book) {
        return 5.0;
    }
}