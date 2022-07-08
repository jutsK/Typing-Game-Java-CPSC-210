package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PastScoresTest {

    private PastScores p;
    private Score s1;
    private Score s2;
    private Score s3;

    @BeforeEach
    public void runBefore() {
        p = new PastScores();
        s1 = new Score(0);
        s2 = new Score(5);
        s3 = new Score(10);
    }

    @Test
    public void testAddScore() {
        assertEquals(-1, p.length());
        p.addScore(s1);
        assertEquals(0, p.length());
        p.addScore(s1);
        assertEquals(1, p.length());
        p.addScore(s1);
        assertEquals(2, p.length());
        p.addScore(s1);
        assertEquals(3, p.length());
        p.addScore(s1);
        assertEquals(4, p.length());
        p.addScore(s1);
        assertEquals(5, p.length());
        p.addScore(s1);
        assertEquals(6, p.length());
        p.addScore(s1);
        assertEquals(7, p.length());
        p.addScore(s1);
        assertEquals(8, p.length());
        p.addScore(s1);
        assertEquals(9, p.length());
        p.addScore(s1);
        assertEquals(10, p.length());
        p.addScore(s1);
        assertEquals(10, p.length());
    }

    @Test
    public void testLength() {
        assertEquals(-1, p.length());
        p.addScore(s1);
        assertEquals(0, p.length());
        p.addScore(s1);
        assertEquals(1, p.length());
        p.addScore(s1);
        assertEquals(2, p.length());

    }

    @Test
    public void testSum() {
        assertEquals(0, p.sum());
        p.addScore(s1);
        assertEquals(0, p.sum());
        p.addScore(s2);
        assertEquals(5, p.sum());
        p.addScore(s3);
        assertEquals(15, p.sum());

    }

    @Test
    public void testScoresAverage() {
        assertEquals(0, p.scoresAverage());
        p.addScore(s2);
        assertEquals(0, p.scoresAverage());
        p.addScore(s2);
        assertEquals(5, p.scoresAverage());
        p.addScore(s2);
        assertEquals(5, p.scoresAverage());
        p.addScore(s3);
        assertEquals(6, p.scoresAverage());
        p.addScore(s3);
        assertEquals(7, p.scoresAverage());
        p.addScore(s3);
        assertEquals(8, p.scoresAverage());
    }

    @Test
    public void testGet() {
        assertEquals(0, p.get(1));
        p.addScore(s1);
        assertEquals(0, p.get(0));
        p.addScore(s2);
        assertEquals(5, p.get(1));
    }

    @Test
    public void testGetScore() {
        assertEquals(null, p.getScore(0));
        assertEquals(null, p.getScore(1));
        p.addScore(s1);
        p.addScore(s2);
        p.addScore(s3);
        assertEquals(new Score(0).getScoreValue(), p.getScore(0).getScoreValue());
        assertEquals(new Score(5).getScoreValue(), p.getScore(1).getScoreValue());
        assertEquals(new Score(10).getScoreValue(), p.getScore(2).getScoreValue());
    }

    @Test
    public void testAddHighScore() {
        p.addScore(s1);
        assertEquals(0, p.length());
        assertEquals(0, p.get(0));
        assertFalse(p.addHighScore(s1));
        assertEquals(0, p.length());
        assertTrue(p.addHighScore(s2));
        assertEquals(0, p.length());
        assertEquals(0, p.length());
        p.addScore(s2);
        assertFalse(p.addHighScore(s1));
        assertFalse(p.addHighScore(s2));
        assertTrue(p.addHighScore(s3));

    }


}