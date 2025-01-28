package controller;

import model.Reader;
import model.Book;
import service.BookService;
import service.ReaderService;

import java.util.Scanner;

public class ReaderController {
    private ReaderService readerService;
    private BookService bookService;

    public ReaderController(ReaderService readerService, BookService bookService) {
        this.readerService = readerService;
        this.bookService = bookService;
    }

    public void registerReader() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Reader ID: ");
        String readerId = scanner.nextLine();
        System.out.print("Enter Reader Name: ");
        String readerName = scanner.nextLine();

        Reader reader = new Reader(readerId, readerName);
        readerService.addReader(reader);
        System.out.println("Reader registered successfully.");
    }

    public void handleReaderOperations(String readerId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nReader Operations");
            System.out.println("1. Register Reader");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Search Book by Title");
            System.out.println("5. Search Book by Author");
            System.out.println("6. Search Book by Category");
            System.out.println("7. Search Book by ID");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> registerReader();
                case 2 -> {
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    readerService.borrowBook(readerId, bookId);
                }
                case 3 -> {
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    readerService.returnBook(readerId, bookId);
                }
                case 4 -> {
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    bookService.getBooksByTitle(title).forEach(book -> printBookDetails(book));
                }
                case 5 -> {
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    bookService.getBooksByAuthor(author).forEach(book -> printBookDetails(book));
                }
                case 6 -> {
                    System.out.print("Enter category name: ");
                    String category = scanner.nextLine();
                    bookService.getBooksByCategory(category).forEach(book -> printBookDetails(book));
                }
                case 7 -> {
                    System.out.print("Enter book ID: ");
                    String bookId = scanner.nextLine();
                    Book book = bookService.getBookById(bookId);
                    if (book != null) {
                        printBookDetails(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                }
                case 8 -> {
                    System.out.println("Exiting reader panel.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printBookDetails(Book book) {
        System.out.println("ID: " + book.getId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Category: " + book.getCategory());
        System.out.println("Available: " + book.isAvailable());
        System.out.println("---------------------------");
    }
}