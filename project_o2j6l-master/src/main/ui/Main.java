package ui;


import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
//        try {
//            new TypingApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
        try {
            new GUI();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


