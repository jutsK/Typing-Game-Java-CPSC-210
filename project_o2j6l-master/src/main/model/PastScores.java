package model;

import java.util.ArrayList;
import java.util.List;

import persistence.Writable;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a list of consecutive game scores, with index 0 being the high score
public class PastScores implements Writable {
    public static final int MAX_PAST_SIZE = 11;

    private List<Score> scoreList;

    public PastScores() {
        scoreList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds Score to end of PastScores, if length of PastScores exceeds MAX_SIZE, remove first Score
    //          adds event to event log that keep tracks of adding and removing scores
    public void addScore(Score s) {
        if (scoreList.size() != 0) {
            EventLog.getInstance().logEvent((new Event("added Score to log: " + s.getScoreValue())));
        }
        scoreList.add(s);
        if (scoreList.size() > MAX_PAST_SIZE) {
            EventLog.getInstance().logEvent((new Event("Removed first score: " + scoreList.get(1).getScoreValue())));
            scoreList.remove(1);

        }

    }


    // REQUIRES: scoreList is not empty
    // EFFECTS: if current score > high score(index 0), replace high score with current score and return true
    public boolean addHighScore(Score s) {
        if (s.getScoreValue() > scoreList.get(0).getScoreValue()) {
            scoreList.remove(0);
            scoreList.add(0, s);
            return true;
        }
        return false;
    }

    // EFFECTS: returns length of PastScores disregarding high score
    public int length() {
        return scoreList.size() - 1;
    }

    // EFFECTS: returns sum of PastScores
    public int sum() {
        int sum = 0;
        for (int i = 1; i < scoreList.size(); i++) {
            sum = sum + scoreList.get(i).getScoreValue();
        }
        return sum;
    }

    // EFFECTS: returns average of PastScores disregarding high score
    public int scoresAverage() {
        if (this.length() > 0) {
            return this.sum() / this.length();
        } else {
            return 0;
        }

    }

    // EFFECTS: returns integer value of i-th Score in PastScores
    public int get(int i) {
        if (i <= scoreList.size()) {
            return scoreList.get(i).getScoreValue();
        }
        return 0;
    }

    // EFFECTS: returns i-th Score in PastScores
    public Score getScore(int i) {
        if (i <= scoreList.size() && scoreList.size() != 0) {
            return scoreList.get(i);
        } else {
            return null;
        }
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("scoreList", scoreListToJson());
        return json;
    }


    // EFFECTS: returns scores in PastScores as a JSON array
    private JSONArray scoreListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Score s : scoreList) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }


}
