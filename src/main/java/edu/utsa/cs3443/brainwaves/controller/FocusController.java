package edu.utsa.cs3443.brainwaves.controller;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
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
            if (secondsRemaining > 0) {
                secondsRemaining--;
                updateLabel();
            } else {
                timeline.stop();
                showClockPing();       // Visual ping
                showCompletionAlert(); //Alert
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML private void pauseTimer() {
        if (timeline != null) timeline.pause();
    }

    @FXML private void resumeTimer() {
        if (timeline != null) timeline.play();
    }

    @FXML private void resetTimer() {
        secondsRemaining = focusDuration * 60;
        updateLabel();
    }

    @FXML private void setDuration5() { focusDuration = 5; resetTimer(); }
    @FXML private void setDuration15() { focusDuration = 15; resetTimer(); }
    @FXML private void setDuration25() { focusDuration = 25; resetTimer(); }
    @FXML private void setDuration30() { focusDuration = 30; resetTimer(); }

    private void updateLabel() {
        int minutes = secondsRemaining / 60;
        int seconds = secondsRemaining % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
        focusProgress.setProgress((double) secondsRemaining / (focusDuration * 60));
    }

    private void showCompletionAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Session Complete");
        alert.setHeaderText(null);
        alert.setContentText("Focus session complete!");
        alert.showAndWait();
    }

    //Visual clock ping method
    private void showClockPing() {
        timerLabel.setStyle("-fx-background-color: #55CBCD; -fx-text-fill: white; -fx-font-weight: bold;");
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> timerLabel.setStyle(""));
        pause.play();
    }
}
