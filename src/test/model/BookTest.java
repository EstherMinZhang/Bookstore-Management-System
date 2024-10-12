package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

    private Book book;

    @BeforeEach
    public void runBefore() {
        book = new Book("Harry Potter", "J.K.Rowling", 8.99);
    }

    @Test
    public void constructorTest() {
        assertEquals("Harry Potter", book.getBookName());
        assertEquals("J.K.Rowling", book.getAuthor());
        assertEquals(8.99, book.getPrice(), 0.01);
    }

    @Test
    public void getBookNameTest() {
        assertEquals("Harry Potter", book.getBookName());
    }

    @Test
    public void setBookNameTest() {
        book.setBookName("My Life");
        assertEquals("My Life", book.getBookName());
    }

    @Test
    public void getAuthorTest() {
        assertEquals("J.K.Rowling", book.getAuthor());
    }

    @Test
    public void setAuthorTest() {
        book.setAuthor("Esther Zhang");
        assertEquals("Esther Zhang", book.getAuthor());
    }

    @Test
    public void getPriceTest() {
        assertEquals(8.99, book.getPrice(), 0.01);// 0.01 is error range which is using in double
    }

    @Test
    public void setPricetest() {
        book.setPrice(9.85);
        assertEquals(9.85, book.getPrice(), 0.01);
    }

    @Test
    public void toStringTest() {
        String str = "book name : Harry Potter\n"
                + "author : J.K.Rowling\n"
                + "price : 8.99\n";
        assertEquals(str, book.toString());
    }

}
