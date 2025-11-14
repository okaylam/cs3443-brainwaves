package edu.utsa.cs3443.brainwaves.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.io.*;
import java.nio.file.*;

public class NotesController {
    @FXML private TextArea notesArea;

    private static final String FILE_PATH = "src/main/resources/edu/utsa/cs3443/brainwaves/data/notes.txt";

    @FXML
    public void initialize() {
        // Load saved notes when the tab opens
        try {
            Path path = Paths.get(FILE_PATH);
            if (Files.exists(path)) {
                notesArea.setText(Files.readString(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void saveNotes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write(notesArea.getText());

            // Confirmation popup
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notes Saved");
            alert.setHeaderText(null);
            alert.setContentText("Your notes were saved successfully!");
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
