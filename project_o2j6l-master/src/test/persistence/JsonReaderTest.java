package persistence;

import model.PastScores;
import model.Score;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/this\0IsNotAFile.json");
        try {
            PastScores ps = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPastScores() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPastScores.json");
        try {
            PastScores ps = reader.read();
            assertEquals(-1, ps.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPastScores() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPastScores.json");
        try {
            PastScores ps = reader.read();
            assertEquals(3, ps.length());
            checkScore(10, ps.getScore(0));
            checkScore(5, ps.getScore(1));
            checkScore(0, ps.getScore(2));
            checkScore(10, ps.getScore(3));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
