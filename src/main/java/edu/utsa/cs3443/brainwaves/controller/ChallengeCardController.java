package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Challenge;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChallengeCardController {
    private Challenge challenge;

    @FXML private ImageView challengeIcon;
    @FXML private Label titleLabel;
    @FXML private Label descLabel;
    @FXML private Label xpLabel;
    @FXML private Button completeButton;

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;

        titleLabel.setText(challenge.getName());
        descLabel.setText(challenge.getText());
        xpLabel.setText("+" + challenge.getXp() + " XP");

        if (challenge.getIconPath() != null) {
            challengeIcon.setImage(new Image(getClass().getResourceAsStream(challenge.getIconPath())));
        }
    }
}
