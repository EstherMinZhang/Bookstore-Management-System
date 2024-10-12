package ui;

import model.Book;
import model.BookManager;

import java.util.Scanner;

/**
 * Represents the user interface for the book management system.
 * Handles user input and invokes methods from BookManager to perform actions.
 */

public class BookStoreApp {
    private BookManager bookManager;
    private Scanner scanner;

    /**
     * MODIFIES: this
     * EFFECTS: Initializes BookManager and Scanner, and calls the init() method to
     * handle user interaction.
     */
    public BookStoreApp() {
        bookManager = new BookManager();
        scanner = new Scanner(System.in);
        init();

    }

    /**
     * MODIFIES: this.books
     * EFFECTS: Executes add, remove, edit, or show books from user slection.
     */
    @SuppressWarnings("methodlength")
    public void init() {
        while (true) {
            System.out.println("1. Add a book.");
            System.out.println("2. Remove a book.");
            System.out.println("3. Edit a book.");
            System.out.println("4. Show all books.");
            System.out.println("5. Exit");
            System.out.println("Please select what do you want to do. Number only.");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    editBook();
                    break;
                case 4:
                    bookManager.showBooks();
                    break;
                case 5:
                    System.out.println("App will be closed.");
                    return;
                default:
                    System.out.println("Invalid input. Please input a integer number. Please try agian. ");

            }
        }
    }

    /**
     * REQUIRES: User input book name and author cannot be null; price >= 0.
     * MODIFIES: this.books
     * EFFECTS: Adds a new book to the books list based on user input.
     */
    private void addBook() {
        System.out.println("Please enter book name");
        String name = scanner.nextLine();
        System.out.println("Please enter author");
        String author = scanner.nextLine();
        System.out.println("Please enter price.");
        double price = scanner.nextDouble();
        scanner.nextLine();
        Book newBook = new Book(name, author, price);
        bookManager.addBook(newBook);
        System.out.println("Book has been added succusssfully.");
    }

    /**
     * REQUIRES: User input book name cannot be null.
     * MODIFIES: this.books
     * EFFECTS: Removes a book from the list based on the user's input.
     */
    private void removeBook() {
        System.out.println("Please enter the book name to edit.");
        String name = scanner.nextLine();
        bookManager.removeBook(name);
        System.out.println("Book has been removed successfully.");
    }

    /**
     * REQUIRES: User input book name, new author cannot be null; new price >= 0.
     * MODIFIES: this.books
     * EFFECTS: Updates a book's author and price based on book name which is from
     * user input.
     */
    private void editBook() {
        System.out.println("Please enter the book name to edit.");
        String name = scanner.nextLine();
        System.out.println("Please enter new author.");
        String newAuthor = scanner.nextLine();
        System.out.println("Please enter new price");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();
        bookManager.editBook(name, newAuthor, newPrice);
        System.out.println("Book has been edited successfully");
    }
}