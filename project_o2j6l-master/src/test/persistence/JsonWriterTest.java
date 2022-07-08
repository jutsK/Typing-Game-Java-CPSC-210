package persistence;

import model.PastScores;
import model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PastScores ps = new PastScores();
            JsonWriter writer = new JsonWriter("/data/notArealFile.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPastScores() {
        try {
            PastScores ps = new PastScores();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPastScores.json");
            writer.open();
            writer.write(ps);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPastScores.json");
            ps = reader.read();
            assertEquals(-1, ps.length());
        } catch (IOException e) {
            fail("Exception should have been thrown");
        }
    }

    @Test
    void testWriterGeneralPastScores() {
        try {
            PastScores ps = new PastScores();
            ps.addScore(new Score(10));
            ps.addScore(new Score(5));
            ps.addScore(new Score(0));
            ps.addScore(new Score(10));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPastScores.json");
            writer.open();
            writer.write(ps);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPastScores.json");
            ps = reader.read();
            assertEquals(3, ps.length());
            checkScore(10, ps.getScore(0));
            checkScore(5, ps.getScore(1));
            checkScore(0, ps.getScore(2));
            checkScore(10, ps.getScore(3));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
