package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Challenge;
import edu.utsa.cs3443.brainwaves.model.UserStats;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeViewController {
    private final ChallengeController challengeData = new ChallengeController();
    private UserStats stats;

    @FXML private VBox challengeContainer;
    @FXML private Label levelChip;
    @FXML private Label xpProgress;

    public void initialize() {
        // Loads the challenges from the csv file
        try {
            challengeData.loadChallenges();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Chooses a random challenge
        Challenge current = ChallengeController.getCurrentChallenge();
        if (current != null) {
            createChallengeCard(current);
        }
    }

    public void setStats(UserStats stats) {
        this.stats = stats;
        refreshStats();
    }

    private void refreshStats() {
        levelChip.setText("Level " + stats.getLevel());
        xpProgress.setText(stats.getXPLabel());
    }

    // Creates a challenge card
    @FXML
    private void createChallengeCard(Challenge challenge) {
        try {
            var loader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/brainwaves/fxml/challenge-card.fxml"));
            var card = loader.load();
            ChallengeCardController controller = loader.getController();

            controller.setChallenge(challenge);
            challengeContainer.getChildren().add((Node) card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
