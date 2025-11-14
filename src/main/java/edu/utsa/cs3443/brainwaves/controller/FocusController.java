package edu.utsa.cs3443.brainwaves.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class FocusController {
    @FXML private Label timerLabel;

    private Timeline timeline;
    private int secondsRemaining = 25 * 60; // default 25 minutes

    @FXML
    private void handleStart() {
        if (timeline != null) timeline.stop();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> tick()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void handlePause() {
        if (timeline != null) timeline.pause();
    }

    @FXML
    private void handleReset() {
        if (timeline != null) timeline.stop();
        secondsRemaining = 25 * 60;
        updateLabel();
    }

    private void tick() {
        if (secondsRemaining > 0) {
            secondsRemaining--;
            updateLabel();
        } else {
            timeline.stop();
        }
    }

    private void updateLabel() {
        int minutes = secondsRemaining / 60;
        int seconds = secondsRemaining % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }
}
