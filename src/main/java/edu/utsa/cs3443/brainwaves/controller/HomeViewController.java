package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Challenge;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeViewController {
    private final ChallengeController challengeData = new ChallengeController();

    @FXML private VBox challengeContainer;

    public void initialize() {
        // Loads the challenges from the csv file
        try {
            challengeData.loadChallenges();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Challenge random = ChallengeController.getRandomChallenge();
        if (random != null) {
            createChallengeCard(random);
        }
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
