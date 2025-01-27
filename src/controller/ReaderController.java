package controller;

import model.Reader;
import service.ReaderService;

import java.util.Scanner;

public class ReaderController {
    private ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
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
            System.out.println("4. Exit");
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
                    System.out.println("Exiting reader panel.");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}