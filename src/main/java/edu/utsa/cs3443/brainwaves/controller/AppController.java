package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.UserStats;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class AppController {
    @FXML private StackPane contentHolder;

    private enum View { HOME, TASKS, NOTES, FOCUS, PROFILE }

    private final UserStats stats = new UserStats(3, 80);

    @FXML public void initialize() {
        show(View.HOME);
    }

    @FXML private void goHome() { show(View.HOME); }
    @FXML private void goTasks() { show(View.TASKS); }
    @FXML private void goNotes() { show(View.NOTES); }
    @FXML private void goFocus() { show(View.FOCUS); }
    @FXML private void goProfile() { show(View.PROFILE); }

    private void show(View v) {
        Parent view = loadView(v);
        contentHolder.getChildren().setAll(view);
    }

    private Parent loadView(View v) {
        String fxml = switch (v) {
            case View.HOME -> "/edu/utsa/cs3443/brainwaves/fxml/home-view.fxml";
            case View.TASKS -> "/edu/utsa/cs3443/brainwaves/fxml/task-view.fxml";
            case View.NOTES -> "/edu/utsa/cs3443/brainwaves/fxml/notes-view.fxml";
            case View.FOCUS -> "/edu/utsa/cs3443/brainwaves/fxml/focus-view.fxml";
            case View.PROFILE -> "/edu/utsa/cs3443/brainwaves/fxml/profile-view.fxml";
        };

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Object controller = loader.getController();

            // Loads home view stats if on the home page
            if (controller instanceof HomeViewController home) {
                home.setStats(stats);
            }

            return root;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + fxml, e);
        }
    }
}
