package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Challenge;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ChallengeCardController {
    @FXML private Label challengeTitle;
    @FXML private Label challengeDescription;
    @FXML private ImageView challengeIcon;

    public void setChallenge(Challenge challenge) {
        challengeTitle.setText(challenge.getName());
        challengeDescription.setText(challenge.getText());

        if (challenge.getIconPath() != null) {
            challengeIcon.setImage(new Image(getClass().getResourceAsStream(challenge.getIconPath())));
        }
    }
}
