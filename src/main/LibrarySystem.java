package main;

import controller.AdminController;
import controller.ReaderController;
import data.LibraryData;
import model.Admin;
import model.Reader;
import service.AdminService;
import service.ReaderService;
import service.BookService;
import service.CategoryService;

import java.util.Scanner;

public class LibrarySystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        CategoryService categoryService = new CategoryService();
        BookService bookService = new BookService();
        ReaderService readerService = new ReaderService();

        AdminController adminController = new AdminController(adminService, categoryService, bookService);
        ReaderController readerController = new ReaderController(readerService);

        while (true) {
            System.out.println("Welcome to the Library System");
            System.out.println("1. Admin Register");
            System.out.println("2. Admin Login");
            System.out.println("3. Reader Register");
            System.out.println("4. Reader Login");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> adminController.registerAdmin();
                case 2 -> {
                    System.out.print("Enter Admin ID: ");
                    String adminId = scanner.nextLine();
                    System.out.print("Enter Secret Key: ");
                    String secretKey = scanner.nextLine();

                    Admin admin = adminService.findAdminById(adminId);
                    if (admin != null && admin.validateSecretKey(secretKey)) {
                        adminController.handleAdminOperations();
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                }
                case 3 -> readerController.registerReader();
                case 4 -> {
                    System.out.print("Enter Reader ID: ");
                    String readerId = scanner.nextLine();
                    Reader reader = (Reader) LibraryData.getUsers().get(readerId);
                    if (reader != null) {
                        readerController.handleReaderOperations(readerId);
                    } else {
                        System.out.println("Reader not found.");
                    }
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}