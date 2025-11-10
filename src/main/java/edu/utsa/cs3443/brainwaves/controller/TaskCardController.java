package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TaskCardController {
    @FXML private Label titleLabel;
    @FXML private Label descLabel;
    @FXML private Label categoryChip;
    @FXML private Label priorityChip;
    @FXML private Label statusChip;
    @FXML private Label dueLabel;
    @FXML private Label timeLabel;

    private Task task;

    public void setTask(Task task) {
        this.task = task;

        titleLabel.setText(task.getTaskTitle());

        // Determines if the task has a description
        setLabel(descLabel, task.getTaskDesc());
        setLabel(categoryChip, task.getCategory());
        setLabel(priorityChip, task.getPriority() != null ? task.getPriority().toString() : "");
        if (task.getPriority() != null) {
            priorityChip.setStyle("-fx-background-color: " + task.getPriority().getColor() + ";");
        }
        setLabel(statusChip, task.getStatus() != null ? task.getStatus().toString() : "");
        if (task.getStatus() != null) {
            statusChip.setStyle("-fx-background-color: " + task.getStatus().getColor() + ";");
        }
        setLabel(dueLabel, task.getDueDate() != null ? task.getDueDate().toString() : "");
        setLabel(timeLabel, task.getTimeEstimate() != 0 ? String.valueOf(task.getTimeEstimate()) : "");
    }

    // Determines if
    private void setLabel(Label label, String text) {
        boolean isEmpty = text != null && !text.isBlank();
        label.setText(isEmpty ? text : "");
        label.setVisible(isEmpty);
        label.setManaged(isEmpty);
    }
}
