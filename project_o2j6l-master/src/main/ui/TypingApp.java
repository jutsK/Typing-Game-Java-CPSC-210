package ui;


import model.Score;
import model.PastScores;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TypingApp {
    private Scanner input;
    private Score currentScore;
    private Score highScore;
    private PastScores scoreList;
    private static final String JSON_STORE = "./data/pastscores.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    List<String> words;


    // EFFECTS: runs the typing application
    public TypingApp() throws FileNotFoundException {
        runApp();
    }

    // refer to Teller application
    // MODIFIES: this
    // EFFECTS: keeps app running until told to close
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Thanks for playing!");
    }

    // MODIFIES: this
    // EFFECTS: processes user commands at main menu
    public void processCommand(String command) {
        if (command.equals("start")) {
            startGame();
        }
        if (command.equals("scores")) {
            viewScores();
        }
        if (command.equals("save")) {
            savePastScores();
        }
        if (command.equals("load")) {
            loadPastScores();
        }
    }

    // EFFECTS: displays main menu
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tstart -> start");
        System.out.println("\tquit -> quit");
        System.out.println("\tscores -> scores");
        System.out.println("\tsave -> save");
        System.out.println("\tload -> load");
    }

    // MODIFIES: this
    // EFFECTS: runs the typing game
    private void startGame() {
        String command = null;
        Boolean continuing = true;
        Random r = new Random();
        String randomWord;
        int high = words.size();


        while (continuing) {
            randomWord = words.get(r.nextInt(high));
            System.out.println(randomWord);
            command = input.next();
            if (command.equals(randomWord)) {
                currentScore.addOne();
            } else {
                scoreList.addScore(currentScore);
                break;
            }
        }
        if (scoreList.addHighScore(currentScore)) {
            System.out.println("You have set a new high score of " + currentScore.getScoreValue() + "!");
        } else {
            System.out.println("Your score is " + currentScore.getScoreValue());
        }
        currentScore = new Score(0);

    }

    // EFFECTS: displays past scores (up to 10) and average
    public void viewScores() {
        for (int i = 1; i < scoreList.length() + 1; i++) {
            System.out.println(i + ".   " + scoreList.get(i));
        }

        System.out.println("Your 10-game average score is " + scoreList.scoresAverage());
        System.out.println("Your high score is " + scoreList.get(0));

    }


    // EFFECTS: saves the workroom to file
    private void savePastScores() {
        try {
            jsonWriter.open();
            jsonWriter.write(scoreList);
            jsonWriter.close();
            System.out.println("Saved scores to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadPastScores() {
        try {
            scoreList = jsonReader.read();
            System.out.println("Loaded scores from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load scores from " + JSON_STORE);
        }
    }


    // refer to teller app
    // MODIFIES: this
    // EFFECTS: initiates needed variables
    private void init() {
        currentScore = new Score(0);
        highScore = new Score(0);
        scoreList = new PastScores();
        scoreList.addScore(highScore);
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input.useDelimiter("\n");
        words = Arrays.asList(
                "I", "can't", "think", "of", "any", "words", "but", "this", "should", "be", "enough", "for", "the",
                "time", "being", "watermelon", "bananas", "keyboard", "papaya", "pineapple", "raspberries", "because",
                "without", "quickly", "America", "eyeball", "tiredness", "something", "nothing", "what", "help", "one",
                "school", "hummingbird", "leather", "theatre", "cocktail", "alfalfa", "Volvo", "vegemite", "probable",
                "halfpastv.com", "information", "one", "exactly", "technology", "course", "very", "difficult", "why",
                "a", "wizard", "is", "never", "late", "he", "early", "precisely", "when", "means", "precious",
                "potatoes", "strawberries", "second", "breakfast", "fireworks", "forest", "mischievous", "spelling");
    }
}
