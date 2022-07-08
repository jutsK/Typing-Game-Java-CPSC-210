package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {

    private Score s1;
    private Score s2;

    @BeforeEach
    public void runBefore() {
        s1 = new Score(0);
        s2 = new Score(1);
    }

    @Test
    public void testGetScore() {
        assertEquals(0, s1.getScoreValue());
        assertEquals(1, s2.getScoreValue());

    }

    @Test
    public void testAddOne() {
        assertEquals(0, s1.getScoreValue());
        s1.addOne();
        assertEquals(1, s1.getScoreValue());
        s1.addOne();
        assertEquals(2, s1.getScoreValue());
    }
}
