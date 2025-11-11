package edu.utsa.cs3443.brainwaves.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TaskViewController {
    @FXML private VBox taskContainer;
    @FXML private Button addTaskButton;
    @FXML private Button cancelTaskButton;
    @FXML private StackPane addTaskOverlay;
    @FXML private Label progressLabel;

    private final TaskController taskData = new TaskController();

    @FXML
    public void initialize() throws IOException {
        // Loads the tasks from the csv file
        try {
            taskData.loadTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Clears cards on refresh
        taskContainer.getChildren().clear();

        // Loads the card FXML and the controller to build a task card and add it into taskContainer
        for (var task : taskData.getTasks()) {
            var loader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/brainwaves/fxml/task-card.fxml"));
            var card = loader.load();
            TaskCardController controller = loader.getController();

            controller.setTask(task);
            taskContainer.getChildren().add((Node) card);
        }
    }

    @FXML
    private void handleAddTask() {
        addTaskOverlay.setVisible(true);
        addTaskOverlay.setManaged(true);
    }

    @FXML
    private void handleCancelAddTask() {
        addTaskOverlay.setVisible(false);
        addTaskOverlay.setManaged(false);
    }
}
