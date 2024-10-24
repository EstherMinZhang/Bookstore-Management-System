package persistence;

import model.Book;
import model.BookManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");// how to change?
        try {
            BookManager bm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookManager() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookManager.json");
        try {
            BookManager bm = reader.read();
            assertEquals(0, bm.getBooks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBookManager() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBookManager.json");
        try {
            BookManager bm = reader.read();
            List<Book> books = bm.getBooks();
            assertEquals(2, books.size());//why 2? need create different test data
            checkBook("Good", "Andrew", 18, books.get(0));
            checkBook("Goodtoo", "Nathan", 19, books.get(1));
       
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
