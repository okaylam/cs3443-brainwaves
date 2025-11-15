package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Challenge;
import edu.utsa.cs3443.brainwaves.model.UserStats;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ChallengeCardController {
    private Challenge challenge;
    private UserStats stats;
    private HomeViewController home;

    @FXML
    private VBox challengeCard;
    @FXML
    private ImageView challengeIcon;
    @FXML
    private Label titleLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label xpLabel;
    @FXML
    private Button completeChallengeButton;

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;

        titleLabel.setText(challenge.getName());
        descLabel.setText(challenge.getText());
        xpLabel.setText("+" + challenge.getXP() + " XP");

        if (challenge.getIconPath() != null) {
            challengeIcon.setImage(new Image(getClass().getResourceAsStream(challenge.getIconPath())));
        }

        // Apply completed state if the challenge is completed
        if (challenge.isCompleted()) {
            applyCompletedStyle();
        }
    }

    public void setStats(UserStats stats) {
        this.stats = stats;
    }

    public void setHomeController(HomeViewController home) {
        this.home = home;
    }

    @FXML
    private void onCompleteClicked() {
        // Prevents double completion
        if (challenge.isCompleted()) return;

        // Updates the card visuals
        applyCompletedStyle();

        // Sends the XP reward to the home screen
        if (home != null) {
            home.completeChallenge(challenge);
        }
    }

    // Changes the UI of the challenge cards when they are completed
    private void applyCompletedStyle() {
        if (challengeCard != null) {
            challengeCard.getStyleClass().add("completed");
            completeChallengeButton.setText("Completed");
            completeChallengeButton.setDisable(true);
            completeChallengeButton.getStyleClass().add("completed");
        }
    }
}
