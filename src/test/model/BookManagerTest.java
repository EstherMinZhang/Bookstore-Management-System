package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BookManagerTest {
    private BookManager bookManager;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void runBefore() {
        BookManager bookManager = new BookManager();
        Book book1 = new Book("My Life", "Helen", 5.6);
        Book book2 = new Book("Twlight", "Esther Disney", 12.56);

    }

    @Test
    public void addBookTest() { // books.add(book);

        bookManager.addBook(book1);
        ArrayList<Book> books = bookManager.getBooks();

        assertEquals(1, books.size());

        assertEquals("My Life", books.get(0).getBookName());
        assertEquals("Helen", books.get(0).getAuthor());
        assertEquals(5.6, books.get(0).getPrice(), 0.01);

        bookManager.addBook(book2);
        books = bookManager.getBooks();

        assertEquals(2, books.size());

        assertEquals("My Life", books.get(0).getBookName());
        assertEquals("Helen", books.get(0).getAuthor());
        assertEquals(5.6, books.get(0).getPrice(), 0.01);

        assertEquals("Twilight", books.get(1).getBookName());
        assertEquals("Esther Disney", books.get(1).getAuthor());
        assertEquals(12.56, books.get(1).getPrice(), 0.01);
    }

    @Test
    public void testRemoveBook() {
        // Add two books first
        bookManager.addBook(book1);
        bookManager.addBook(book2);

        bookManager.removeBook("My Life");
        ArrayList<Book> books = bookManager.getBooks();

        assertEquals(1, books.size());

        // Check that the remaining book is book2
        assertEquals("Twilight", books.get(0).getBookName());
        assertEquals("Esther Disney", books.get(0).getAuthor());
        assertEquals(12.56, books.get(0).getPrice(), 0.01);
    }

    @Test
    public void testEditBook() {

        bookManager.addBook(book1);

        bookManager.editBook("My Life",  "Helen Joy", 6.99);
        ArrayList<Book> books = bookManager.getBooks();

        assertEquals(1, books.size());

        assertEquals("My New Life", books.get(0).getBookName());
        assertEquals("Helen Joy", books.get(0).getAuthor());
        assertEquals(6.99, books.get(0).getPrice(), 0.01);
    }

    @Test
    public void testShowBooks() {

        bookManager.addBook(book1);
        bookManager.addBook(book2);

        String expectedOutput = "Book Name: My Life, Author: Helen, Price: 5.6\n" +
                "Book Name: Twilight, Author: Esther Disney, Price: 12.56\n";

    }
}
