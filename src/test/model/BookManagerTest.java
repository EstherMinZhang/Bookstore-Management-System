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
        bookManager = new BookManager();
        book1 = new Book("My Life", "Helen", 5.6);
        book2 = new Book("Twlight", "Esther Disney", 12.56);
    }

    @Test
    public void testConstruction() {
        assertEquals(0, bookManager.getBooks().size());
    }

    @Test
    public void addBookTest() { // books.add(book);

        bookManager.addBook(book1);// put one book into book list/books.
        ArrayList<Book> books = bookManager.getBooks(); // book manager get current books/book list. one book inside

        assertEquals(1, books.size());

        assertEquals("My Life", books.get(0).getBookName());
        assertEquals("Helen", books.get(0).getAuthor());
        assertEquals(5.6, books.get(0).getPrice(), 0.01);

        bookManager.addBook(book2); // put another book into books.
        books = bookManager.getBooks(); // book manager get current books/book list, which is 2 books now.

        assertEquals(2, books.size());

        assertEquals("My Life", books.get(0).getBookName());
        assertEquals("Helen", books.get(0).getAuthor());
        assertEquals(5.6, books.get(0).getPrice(), 0.01);

        assertEquals("Twlight", books.get(1).getBookName());
        assertEquals("Esther Disney", books.get(1).getAuthor());
        assertEquals(12.56, books.get(1).getPrice(), 0.01);
    }

    @Test
    public void testRemoveBook() {
        bookManager.addBook(book1);
        bookManager.addBook(book2);

        bookManager.removeBook("My Life");
        ArrayList<Book> books = bookManager.getBooks();

        assertEquals(1, books.size());
        assertEquals("Twlight", books.get(0).getBookName());
        assertEquals("Esther Disney", books.get(0).getAuthor());
        assertEquals(12.56, books.get(0).getPrice(), 0.01);
        bookManager.removeBook("no such book");
        
    

    }

    @Test
    public void testEditBook() {

        bookManager.addBook(book1);

        bookManager.editBook("My Life", "Helen Joy", 6.99);
        ArrayList<Book> books = bookManager.getBooks();

        assertEquals(1, books.size());

        assertEquals("Helen Joy", books.get(0).getAuthor());
        assertEquals(6.99, books.get(0).getPrice(), 0.01);

        bookManager.editBook("No this book", "no this author", 15);
        
    }
    
    @Test
    public void testForLogShowAll() {

        bookManager.forLogShowAll();
        
        EventLog eventLog = EventLog.getInstance();
    
        boolean foundEvent = false;
        for (Event event : eventLog) {
            if (event.getDescription().equals("All books printed.")) {
                foundEvent = true;
                break;
            }
        }
        assertTrue(foundEvent);
    }

    // @Test
    // public void testShowBooks() {

    //     bookManager.addBook(book1);
    //     bookManager.addBook(book2);

    //     String expectedOutput = "[book name : My Life\n"
    //                     + "author : Helen\n"
    //                     + "price : 5.6\n"
    //                     + ", "
    //                     + "book name : Twlight\n"
    //                     + "author : Esther Disney\n"
    //                     + "price : 12.56\n]";
    //     String printString = bookManager.getBooks().toString();
    //     assertEquals(expectedOutput, printString);
    //     bookManager.getBooks().clear();
    //     bookManager.showBooks();
    // }
}
