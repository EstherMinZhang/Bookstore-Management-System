package model;

import java.util.ArrayList;

/**
 * Manages a collection of books. Provides methods to add, remove, edit, and
 * display books.
 */
public class BookManager {
    private ArrayList<Book> books;

    /**
     * MODIFIES: this
     * EFFECTS: Initializes an empty ArrayList<Book>.
     */
    public BookManager() {
        books = new ArrayList<>();
    }

    /**
     * REQUIRES; book cannot be null
     * MODIFIES: this.Book
     * EFFECTS: adds a book to the book lists.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * REQUIRES; bookName cannot be null
     * MODIFIES: this.books
     * EFFECTS: removes a book from the book list which mathces the book name and
     * ingore case.
     */
    public void removeBook(String bookName) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                books.remove(i);
                break;
            }
        }
    }

    /**
     * REQUIRES; bookName and newAuthor cannot be null; newPrive >= 0
     * MODIFIES: this.books
     * EFFECTS: edits the author and price of the book which mathces the book name
     * and ingore case.
     */
    public void editBook(String bookName, String newAuthor, double newPrice) {
        for (Book book : books) {
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                book.setAuthor(newAuthor);
                book.setPrice(newPrice);
                break;
            }
        }
    }

    /**
     * EFFECTS: prints the information of all books in the list.
     */
    public void showBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    /**
     * EFFECTS: return the current line of book list.
     */
    public ArrayList<Book> getBooks() {
        return books;
    }
}
