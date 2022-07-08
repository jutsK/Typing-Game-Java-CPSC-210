package persistence;

import model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkScore(Integer scoreValue, Score score) {
        assertEquals(scoreValue, score.getScoreValue());
    }
}
