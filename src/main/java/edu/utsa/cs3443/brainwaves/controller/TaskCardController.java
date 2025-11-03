package edu.utsa.cs3443.brainwaves.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TaskCardController {
    @FXML private Label titleLabel;
    @FXML private Label descLabel;
    @FXML private Label categoryChip;
    @FXML private Label priorityChip;
    @FXML private Label statusChip;
    @FXML private Label dueLabel;
    @FXML  private Label timeLabel;

    public void setTaskCard(String taskTitle, String taskDesc) {
        titleLabel.setText(taskTitle);

        // Determines if the task has a description
        boolean hasDesc = taskDesc != null && !taskDesc.isBlank();

        //
        if (hasDesc) {
            descLabel.setText(taskDesc);
        } else {
            descLabel.setText("");
        }

        descLabel.setVisible(hasDesc);
        descLabel.setManaged(hasDesc);

    }
}
