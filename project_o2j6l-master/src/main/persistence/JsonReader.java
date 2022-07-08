package persistence;

import model.PastScores;
import model.Score;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Refer to JsonSerializationDemo
// Represents a reader that reads past scores from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads past scores from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PastScores read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePastScores(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses past scores from JSON object and returns it
    private PastScores parsePastScores(JSONObject jsonObject) {
        PastScores ps = new PastScores();
        addPastScores(ps, jsonObject);
        return ps;
    }


    // MODIFIES: ps
    // EFFECTS: parses scores from JSON object and adds them to past scores
    private void addPastScores(PastScores ps, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scoreList");
        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addScore(ps, nextScore);
        }
    }


    // MODIFIES: ps
    // EFFECTS: parses score from JSON object and adds it to past scores
    private void addScore(PastScores ps, JSONObject jsonObject) {
        Integer scoreValue = jsonObject.getInt("scoreValue");
        Score score = new Score(scoreValue);
        ps.addScore(score);
    }

}
