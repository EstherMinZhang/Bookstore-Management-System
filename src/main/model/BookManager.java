package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

/**
 * Manages a collection of books. Provides methods to add, remove, edit, save, load and
 * display books.
 */
public class BookManager implements Writable {
    private ArrayList<Book> books;

    // MODIFIES: this
    // EFFECTS: declares an new ArrayList<Book>
    public BookManager() {
        books = new ArrayList<>();
    }

    // REQUIRES; book cannot be null
    // MODIFIES: this.Book
    // EFFECTS: adds a book to the book lists.
    //
    public void addBook(Book book) {
        books.add(book);
    }

    // REQUIRES; bookName cannot be null
    // MODIFIES: this.books
    // EFFECTS: removes a book from the book list which mathces the book name and
    // ingore case.
    public void removeBook(String bookName) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                books.remove(i);
                break;
            }
        }
    }

    // REQUIRES; bookName and newAuthor cannot be null; newPrive >= 0
    // MODIFIES: this.books
    // EFFECTS: edits the author and price of the book which mathces the book name
    // and ingore case.
    public void editBook(String bookName, String newAuthor, double newPrice) {
        for (Book book : books) {
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                book.setAuthor(newAuthor);
                book.setPrice(newPrice);
                break;
            }
        }
    }

    // EFFECTS: return the current line of book list.
    public ArrayList<Book> getBooks() {
        return books;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("books", booksToJson());
        return json;
    }

    // EFFECTS: returns books in this bookmanager as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : books) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }
}
