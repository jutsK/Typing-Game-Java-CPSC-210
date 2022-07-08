package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents the score of the current game session
public class Score implements Writable {
    private int scoreValue;

    public Score(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    // MODIFIES: this
    // EFFECT: adds 1 to score
    public int addOne() {
        return scoreValue++;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("scoreValue", scoreValue);
        return json;
    }


}
