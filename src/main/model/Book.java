package model;

/**
 * Represents a book with a name, author and price.
 * Provides getter and setter methods to access and modify its fields.
 */
public class Book {
    private String bookName;
    private String author;
    private double price;

    /**
     * EFFECTS: Initializes a new Book object without the given bookName, author,
     * and price.
     */
    // public Book() {
    // }

    /**
     * REQUIRES: bookName, author cannot be null; price >= 0
     * MODIFIES: this
     * EFFECTS: initializes a new Book object with the given bookName, author and
     * pricae.
     */
    public Book(String bookName, String author, double price) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
    }

    /**
     * EFFECTS: return the current book name.
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * REQUIRES: bookName cannot be null
     * MODIFIES: this
     * EFFECTS: Sets the book name to the given value.
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * EFFECTS: return the current author name.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * REQUIRES: author cannot be null
     * MODIFIES: this
     * EFFECTS: Sets the author to the given value.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * EFFECTS: return the current book price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * REQUIRES: prive >= 0.
     * MODIFIES: this
     * EFFECTS: Sets the price to the given value.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * EFFECTS: return the information of the books,including bookName, author and
     * price.
     */
    public String toString() {
        return "book name : " + bookName + "\n" +
                "author : " + author + "\n" +
                "price : " + price + "\n";
    }
}