package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String bookName, String author, double price, Book book) {
        assertEquals(bookName, book.getBookName());
        assertEquals(author, book.getAuthor());
        assertEquals(price, book.getPrice());
    }
}
