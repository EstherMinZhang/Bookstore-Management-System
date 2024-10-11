package ui;

import model.Book;
import model.BookManager;

import java.util.Scanner;

/**
 * Represents the user interface for the book management system.
 * Handles user input and invokes methods from BookManager to perform actions.
 */

public class BookStoreApp {

    /**
     * MODIFIES: this
     * EFFECTS: Initializes BookManager and Scanner, and calls the init() method to
     * handle user interaction.
     */
    public BookStoreApp() {
        // stub
    }

    /**
     * MODIFIES: this.books
     * EFFECTS: Executes add, remove, edit, or show books from user slection.
     */
    public void init() {
        // stub
    }

    /**
     * REQUIRES: User input book name and author cannot be null; price >= 0.
     * MODIFIES: this.books
     * EFFECTS: Adds a new book to the books list based on user input.
     */
    private void addBook() {
        // stub
    }

    /**
     * REQUIRES: User input book name cannot be null.
     * MODIFIES: this.books
     * EFFECTS: Removes a book from the list based on the user's input.
     */
    private void removeBook() {
        // stub
    }

    /**
     * REQUIRES: User input book name, new author cannot be null; new price >= 0.
     * MODIFIES: this.books
     * EFFECTS: Updates a book's author and price based on book name which is from
     * user input.
     */
    private void editBook() {
        // stub
    }
}