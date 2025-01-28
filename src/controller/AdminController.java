package controller;

import model.Admin;
import model.Category;
import model.Book;
import service.AdminService;
import service.CategoryService;
import service.BookService;

import java.util.Scanner;

public class AdminController {
    private AdminService adminService;
    private CategoryService categoryService;
    private BookService bookService;

    public AdminController(AdminService adminService, CategoryService categoryService, BookService bookService) {
        this.adminService = adminService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    public void registerAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Admin ID: ");
        String adminId = scanner.nextLine();
        System.out.print("Enter Admin Name: ");
        String adminName = scanner.nextLine();
        System.out.print("Enter Secret Key: ");
        String secretKey = scanner.nextLine();
        System.out.print("Enter Approval Code (provided by existing admin): ");
        String approvalCode = scanner.nextLine();

        if (!adminService.isApprovalCodeValid(approvalCode)) {
            System.out.println("Invalid approval code. Admin registration failed.");
            return;
        }

        Admin admin = new Admin(adminId, adminName, secretKey);
        adminService.addAdmin(admin);
        System.out.println("Admin registered successfully.");
    }

    public void handleAdminOperations() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Admin Operations ---");
            System.out.println("1. Register Admin");
            System.out.println("2. Add Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Update Book");
            System.out.println("5. Add Category");
            System.out.println("6. View All Books");
            System.out.println("7. View All Categories");
            System.out.println("8. View Books by Author");
            System.out.println("9. View Books by Title");
            System.out.println("10. View Books by Category");
            System.out.println("11. View Book by ID");
            System.out.println("12. Exit");
            System.out.print("Choose an option (1-12): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> registerAdmin();
                case 2 -> {
                    System.out.print("Enter book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book category: ");
                    String category = scanner.nextLine();
                    Book book = new Book(bookId, title, author, category);
                    bookService.addBook(book);
                }
                case 3 -> {
                    System.out.print("Enter book ID to remove: ");
                    String bookId = scanner.nextLine();
                    bookService.removeBook(bookId);
                }
                case 4 -> {
                    System.out.print("Enter book ID to update: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter new title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter new author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter new category: ");
                    String category = scanner.nextLine();
                    bookService.updateBook(bookId, title, author, category);
                }
                case 5 -> {
                    System.out.print("Enter category ID: ");
                    String categoryId = scanner.nextLine();
                    System.out.print("Enter category name: ");
                    String categoryName = scanner.nextLine();
                    Category category = new Category(categoryId, categoryName);
                    categoryService.addCategory(category);
                }
                case 6 -> {
                    System.out.println("All Books:");
                    bookService.getAllBooks().forEach(book -> printBookDetails(book));
                }
                case 7 -> {
                    System.out.println("All Categories:");
                    categoryService.getAllCategories().forEach(category -> System.out.println(category.getName()));
                }
                case 8 -> {
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    bookService.getBooksByAuthor(author).forEach(book -> printBookDetails(book));
                }
                case 9 -> {
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    bookService.getBooksByTitle(title).forEach(book -> printBookDetails(book));
                }
                case 10 -> {
                    System.out.print("Enter category name: ");
                    String category = scanner.nextLine();
                    bookService.getBooksByCategory(category).forEach(book -> printBookDetails(book));
                }
                case 11 -> {
                    System.out.print("Enter book ID: ");
                    String bookId = scanner.nextLine();
                    Book book = bookService.getBookById(bookId);
                    if (book != null) {
                        printBookDetails(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                }
                case 12 -> {
                    System.out.println("Exiting admin panel.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 11.");
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