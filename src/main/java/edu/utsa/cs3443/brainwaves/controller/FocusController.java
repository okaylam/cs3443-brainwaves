package edu.utsa.cs3443.brainwaves.controller;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

public class FocusController {

    @FXML private Label timerLabel;
    @FXML private ProgressBar focusProgress;
    @FXML private Label focusQuoteLabel;

    private Timeline timeline;
    private Timeline quoteTimeline;
    private int secondsRemaining;
    private int focusDuration = 25; // default 25 minutes
    private int quoteIndex = 0;

    private final String[] focusQuotes = {
            "“Stay focused and never give up.”",
            "“Small steps every day lead to big results.”",
            "“Your future is created by what you do today.”",
            "“Discipline is the bridge between goals and achievement.”",
            "“Focus is the key to unlocking your potential.”",
            "“One task at a time. One win at a time.”"
    };

    @FXML
    public void initialize() {
        resetTimer();
        showNextQuote();
    }

    @FXML
    private void startTimer() {
        showNextQuote();
        startQuoteRotation();

        if (timeline != null) timeline.stop();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (secondsRemaining > 0) {
                secondsRemaining--;
                updateLabel();
            } else {
                timeline.stop();
                stopQuoteRotation();
                showClockPing();
                showCompletionAlert();
                showNextQuote();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML private void pauseTimer() {
        if (timeline != null) timeline.pause();
        if (quoteTimeline != null) quoteTimeline.pause();
    }

    @FXML private void resumeTimer() {
        if (timeline != null) timeline.play();
        if (quoteTimeline != null) quoteTimeline.play();
    }

    @FXML private void resetTimer() {
        secondsRemaining = focusDuration * 60;
        updateLabel();
        stopQuoteRotation();
    }

    @FXML private void setDuration5() { focusDuration = 5; resetTimer(); }
    @FXML private void setDuration15() { focusDuration = 15; resetTimer(); }
    @FXML private void setDuration25() { focusDuration = 25; resetTimer(); }
    @FXML private void setDuration30() { focusDuration = 30; resetTimer(); }

    @FXML
    private void handleCustomTime() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Custom Focus Time");
        dialog.setHeaderText("Set your own focus duration");
        dialog.setContentText("Enter time in minutes:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int customMinutes = Integer.parseInt(input.trim());
                if (customMinutes > 0 && customMinutes <= 180) {
                    focusDuration = customMinutes;
                    resetTimer();
                } else {
                    showError("Please enter a number between 1 and 180.");
                }
            } catch (NumberFormatException e) {
                showError("Invalid input. Please enter a whole number.");
            }
        });
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateLabel() {
        int minutes = secondsRemaining / 60;
        int seconds = secondsRemaining % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
        focusProgress.setProgress((double) secondsRemaining / (focusDuration * 60));
    }

    private void showCompletionAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Session Complete");
        alert.setHeaderText("🎉 Great job!");
        alert.setContentText("You stayed focused for " + focusDuration + " minutes. XP earned!");
        alert.showAndWait();
    }

    private void showClockPing() {
        timerLabel.setStyle("-fx-background-color: #55CBCD; -fx-text-fill: white; -fx-font-weight: bold;");
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> timerLabel.setStyle(""));
        pause.play();
    }

    private void showNextQuote() {
        focusQuoteLabel.setText(focusQuotes[quoteIndex]);
        quoteIndex = (quoteIndex + 1) % focusQuotes.length;
    }

    private void startQuoteRotation() {
        if (quoteTimeline != null) quoteTimeline.stop();
        quoteTimeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> showNextQuote()));
        quoteTimeline.setCycleCount(Timeline.INDEFINITE);
        quoteTimeline.play();
    }

    private void stopQuoteRotation() {
        if (quoteTimeline != null) {
            quoteTimeline.stop();
            quoteTimeline = null;
        }
    }
}
