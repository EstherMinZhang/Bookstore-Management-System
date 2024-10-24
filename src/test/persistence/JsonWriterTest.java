package persistence;

import model.Book;
import model.BookManager;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            new BookManager();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");// need change?
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            BookManager bm = new BookManager();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookManager.json");//neeed change
            writer.open();
            writer.write(bm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookManager.json");// need change
            bm = reader.read();
           
            assertEquals(0, bm.getBooks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            BookManager bm = new BookManager();
            bm.addBook(new Book("Good", "Andrew", 18));
            bm.addBook(new Book("Goodtoo", "Nathan", 19));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookManager.json");
            writer.open();
            writer.write(bm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBookManager.json");
            bm = reader.read();

            List<Book> books = bm.getBooks();
            assertEquals(2, books.size());
            checkBook("Good", "Andrew", 18, books.get(0));
            checkBook("Goodtoo", "Nathan", 19, books.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}