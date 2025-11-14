package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Challenge;
import edu.utsa.cs3443.brainwaves.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.text.html.ImageView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class ChallengeController {
    private static final ArrayList<Challenge> challenges = new ArrayList<>();
    private static final String FILE_PATH = "src/main/resources/edu/utsa/cs3443/brainwaves/data/challenges.csv";

    // Loads all the challenges from the csv file into an arraylist
    public void loadChallenges() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            String line;

            // Reads the header and ignores it
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String text = parts[2].trim();
                String type = parts[3].trim();
                String difficulty = parts[4].trim();
                int xp = Integer.parseInt(parts[5].trim());
                String iconFile = parts[6].trim();

                String iconPath = "/edu/utsa/cs3443/brainwaves/icons/" + iconFile;

                challenges.add(new Challenge(id, name, text, type, difficulty, xp, iconPath));
            }
        } catch (Exception e) {
            // FIXME: Improve error handling and add user-friendly alerts
            e.printStackTrace();
        }
    }

    // Randomly chooses a challenge from the arraylist
    public static Challenge getRandomChallenge() {
        Random rand = new Random();
        return challenges.get(rand.nextInt(challenges.size()));
    }
}
