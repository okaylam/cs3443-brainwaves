package edu.utsa.cs3443.brainwaves.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class AppController {
    private enum View { HOME, TASKS }
    private StackPane contentHolder;
    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() {
        show(View.TASKS);
    }

    private void show(View v) {
        Parent view = loadView(v);
        contentHolder.getChildren().setAll(view);
    }

    private Parent loadView(View v) {
        String fxml = switch (v) {
            case View.HOME -> "edu/utsa/cs3443/brainwaves/fxml/home-view.fxml";
            case View.TASKS -> "edu/utsa/cs3443/brainwaves/fxml/task-view.fxml";
        };

        try {
            return FXMLLoader.load(getClass().getResource(fxml));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + fxml, e);
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
