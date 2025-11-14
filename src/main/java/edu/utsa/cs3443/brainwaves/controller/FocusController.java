package edu.utsa.cs3443.brainwaves.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

public class FocusController {
    @FXML private Label timerLabel;
    @FXML private ProgressBar focusProgress;

    private Timeline timeline;
    private int secondsRemaining;
    private int focusDuration = 25; // default 25 minutes

    @FXML
    public void initialize() {
        resetTimer();
    }

    @FXML
    private void startTimer() {
        if (timeline != null) timeline.stop();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            secondsRemaining--;
            updateLabel();
            if (secondsRemaining <= 0) {
                timeline.stop();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Focus session complete!");
                alert.showAndWait();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void resetTimer() {
        secondsRemaining = focusDuration * 60;
        updateLabel();
    }

    private void updateLabel() {
        int minutes = secondsRemaining / 60;
        int seconds = secondsRemaining % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
        focusProgress.setProgress((double) secondsRemaining / (focusDuration * 60));
    }
}
