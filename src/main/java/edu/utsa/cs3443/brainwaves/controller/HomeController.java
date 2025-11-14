package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Challenge;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController {
    @FXML private VBox homeContainer;

    @FXML
    public void initialize() {
        try {
          
            // Load a random challenge
            ChallengeController challengeController = new ChallengeController();
            challengeController.loadChallenges();
            Challenge randomChallenge = ChallengeController.getRandomChallenge();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/edu/utsa/cs3443/brainwaves/fxml/challenge-card.fxml"));
            Node card = loader.load();
            ChallengeCardController controller = loader.getController();
            controller.setChallenge(randomChallenge);

            homeContainer.getChildren().add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
