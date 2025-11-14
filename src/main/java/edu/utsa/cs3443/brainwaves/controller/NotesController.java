package edu.utsa.cs3443.brainwaves.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.*;

public class NotesController {
    @FXML private TextArea notesArea;

    private static final String FILE_PATH = "src/main/resources/edu/utsa/cs3443/brainwaves/data/notes.txt";

    @FXML
    public void initialize() {
        loadNotes();
    }

    private void loadNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            notesArea.setText(sb.toString());
        } catch (IOException e) {
            // File may not exist yet — ignore
        }
    }

    @FXML
    private void saveNotes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write(notesArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
