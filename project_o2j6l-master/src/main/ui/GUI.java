package ui;

import model.Event;
import model.EventLog;
import model.PastScores;
import model.Score;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GUI implements ActionListener {

    private static JLabel currentWordLabel;
    private static JLabel currentScoreLabel;
    private static JLabel highScoreLabel;
    private static JLabel highScoreImage;
    private static JTextField wordField;
    private static ImageIcon iconLogo;
    private JFrame frame;
    private JPanel panel;
    private JButton pastScoresButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton submitButton;
    private JButton averageButton;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    private Score currentScore;
    private Score highScore;
    private PastScores scoreList;
    private static final String JSON_STORE = "./data/pastscores.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    List<String> words;
    Boolean check;

    private enum Actions {
        SCORES,
        SAVE,
        LOAD,
        CHECK,
        MEAN
    }


    // Constructor for GUI
    // MODIFIES: this
    // EFFECTS: constructs a GUI
    public GUI() throws InterruptedException {


        gameInit();
        initGraphics();


        placeLabels();
        placeButtons();

        panel.add(highScoreImage);
        highScoreImage.setVisible(false);
        check = false;

        frame.getRootPane().setDefaultButton(submitButton);

        wordField = new JTextField(20);
        wordField.setBounds(315, 200, 170, 25);
        panel.add(wordField);

        frame.setVisible(true);
        frame.setResizable(false);


        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog(EventLog.getInstance());

                System.exit(0);
            }
        });

    }


    // EFFECTS: prints event log to console
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }

    }


    // Main method for GUI
//    public static void main(String[] args) {
//        try {
//            new GUI();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


    // EFFECTS: perform different actions based on button pushed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(Actions.SCORES.name())) {
            displayScores();
        } else if (e.getActionCommand().equals(Actions.SAVE.name())) {
            savePastScores();
            JOptionPane.showMessageDialog(null, "your scores have been saved");
        } else if (e.getActionCommand().equals(Actions.LOAD.name())) {
            loadPastScores();
            JOptionPane.showMessageDialog(null, "your scores have been loaded");
        } else if (e.getActionCommand().equals(Actions.CHECK.name())) {
            runGame();
        } else if (e.getActionCommand().equals(Actions.MEAN.name())) {
            JOptionPane.showMessageDialog(null, "Your 10-game average score is "
                    + scoreList.scoresAverage());
        }
    }

    // MODIFIES: this
    // EFFECTS: generates random word, checks against user-entered text, adds to score if correct
    private void runGame() {
        String wordToType = currentWordLabel.getText();
        String typedWord = wordField.getText();
        Random r = new Random();
        String randomWord;
        int high = words.size();

        if (typedWord.equals(wordToType) || wordToType.equals("")) {
            currentScore.addOne();
            submitButton.setText("Next");
            currentScoreLabel.setText("Current Score: " + currentScore.getScoreValue());
            wordField.setText("");
            randomWord = words.get(r.nextInt(high));
            currentWordLabel.setText(randomWord);
            highScoreImage.setVisible(false);

        } else {
            if (scoreList.addHighScore(currentScore)) {
                JOptionPane.showMessageDialog(null,
                        "You have set a new high score of " + currentScore.getScoreValue());
                highScoreLabel.setText("High Score: " + currentScore.getScoreValue());

                highScoreImage.setVisible(true);


            }
            resetGame();
        }
    }


    // MODIFIES: this
    // EFFECTS: resets game to default settings
    private void resetGame() {
        scoreList.addScore(currentScore);
        currentScore = new Score(-1);
        submitButton.setText("Start");
        currentWordLabel.setText("");
        currentScoreLabel.setText("Current Score: 0");
        wordField.setText("");
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadPastScores() {
        try {
            scoreList = jsonReader.read();
            highScoreLabel.setText("High Score: " + scoreList.get(0));
        } catch (IOException e) {
            System.out.println("Unable to load scores from " + JSON_STORE);
        }
    }

    // EFFECTS: saves workroom to file
    private void savePastScores() {
        try {
            jsonWriter.open();
            jsonWriter.write(scoreList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: shows list of past 10 scores, average, high score in a JOptionPane
    private void displayScores() {
        JOptionPane.showMessageDialog(null, listScores() + "\n"
                + "\n" + "Your high score is " + scoreList.get(0));
    }

    // EFFECTS: displays past scores as a string / numbered list
    private String listScores() {
        String str = "";
        for (int i = 1; i < scoreList.length() + 1; i++) {
            str += i + ".   " + scoreList.get(i) + "\n";
        }
        return str;
    }


    // MODIFIES: this
    // EFFECTS: initiates needed variables
    private void gameInit() {
        currentScore = new Score(-1);
        highScore = new Score(0);
        scoreList = new PastScores();
        scoreList.addScore(highScore);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        words = Arrays.asList(
                "I", "can't", "think", "of", "any", "words", "but", "this", "should", "be", "enough", "for", "the",
                "time", "being", "watermelon", "bananas", "keyboard", "papaya", "pineapple", "raspberries", "because",
                "without", "quickly", "America", "eyeball", "tiredness", "something", "nothing", "what", "help", "one",
                "school", "hummingbird", "leather", "theatre", "cocktail", "alfalfa", "Volvo", "vegemite", "probable",
                "halfpastv.com", "information", "one", "exactly", "technology", "course", "very", "difficult", "why",
                "a", "wizard", "is", "never", "late", "he", "early", "precisely", "when", "means", "precious",
                "potatoes", "strawberries", "second", "breakfast", "fireworks", "forest", "mischievous", "spelling");
    }

    // MODIFIES: this
    // EFFECTS: initiates graphical elements of the GUI
    public void initGraphics() {
        frame = new JFrame();
        panel = new JPanel();
        iconLogo = new ImageIcon("./data/penguin2.png", "image");
        frame.setIconImage(iconLogo.getImage());

        pastScoresButton = new JButton("See Past Scores");
        saveButton = new JButton("Save Scores");
        loadButton = new JButton("Load Scores");
        submitButton = new JButton("start");
        averageButton = new JButton("Average Score");

        currentWordLabel = new JLabel("");
        currentScoreLabel = new JLabel("Current Score: 0");
        highScoreLabel = new JLabel("High Score: 0");
        highScoreImage = new JLabel("Nice");
        highScoreImage.setIcon(iconLogo);
        highScoreImage.setBounds(0, 300, 400, 200);


        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setTitle("Typing Game");
        frame.add(panel);

        panel.setLayout(null);
    }


    // MODIFIES: this
    // EFFECTS: places instantiated labels onto GUI panel
    public void placeLabels() {
        highScoreLabel.setBounds(10, 50, 150, 25);
        panel.add(highScoreLabel);

        currentScoreLabel.setBounds(10, 80, 150, 25);
        panel.add(currentScoreLabel);

        currentWordLabel.setBounds(315, 170, 300, 25);
        panel.add(currentWordLabel);

    }

    // MODIFIES: this
    // EFFECTS: places instantiated buttons onto panel
    public void placeButtons() {
        GUI instance = this;

        pastScoresButton.setBounds(10, 20, 140, 25);
        pastScoresButton.setActionCommand(Actions.SCORES.name());
        pastScoresButton.addActionListener(instance);
        panel.add(pastScoresButton);

        saveButton.setBounds(520, 20, 140, 25);
        saveButton.setActionCommand(Actions.SAVE.name());
        saveButton.addActionListener(instance);
        panel.add(saveButton);

        averageButton.setBounds(180, 20, 140, 25);
        averageButton.setActionCommand(Actions.MEAN.name());
        averageButton.addActionListener(instance);
        panel.add(averageButton);

        loadButton.setBounds(350, 20, 140, 25);
        loadButton.setActionCommand(Actions.LOAD.name());
        loadButton.addActionListener(instance);
        panel.add(loadButton);


        submitButton.setBounds(360, 250, 80, 25);
        submitButton.setActionCommand(Actions.CHECK.name());
        submitButton.addActionListener(instance);
        panel.add(submitButton);
    }


}
