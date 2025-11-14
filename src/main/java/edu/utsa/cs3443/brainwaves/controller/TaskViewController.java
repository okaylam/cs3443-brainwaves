package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;

public class TaskViewController {
    private final TaskController taskData = new TaskController();

    @FXML private VBox taskContainer;
    @FXML private Button addTaskButton;
    @FXML private Button cancelTaskButton;
    @FXML private StackPane addTaskOverlay;
    @FXML private Label progressLabel;
    @FXML private TextField titleField;
    @FXML private TextArea descField;
    @FXML private TextField subjectField;
    @FXML private ChoiceBox<Task.Priority> priorityBox;
    @FXML private DatePicker dueDatePicker;
    @FXML private Spinner<Integer> timeSpinner;

    @FXML
    public void initialize() throws IOException {
        // Sets up the time spinner
        SpinnerValueFactory<Integer> spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 240, 0, 15);
        timeSpinner.setValueFactory(spinner);

        // Sets up the Priority dropdown choicebox
        priorityBox.getItems().setAll(Task.Priority.values());
        
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
            createTaskCard(task);
        }
    }

    // Creates a task card
    @FXML
    private void createTaskCard(Task task) {
        try {
            var loader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/brainwaves/fxml/task-card.fxml"));
            var card = loader.load();
            TaskCardController controller = loader.getController();

            controller.setTask(task);
            taskContainer.getChildren().add((Node) card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Opens the "Add Task" menu
    @FXML
    private void handleAddTask() {
        addTaskOverlay.setVisible(true);
        addTaskOverlay.setManaged(true);
    }

    // Exits the "Add Task" menu
    @FXML
    private void handleCancelAddTask() {
        addTaskOverlay.setVisible(false);
        addTaskOverlay.setManaged(false);
    }

    // Adds a new task to the task list and creates a card for it. 
    @FXML
    private void handleConfirmAddTask() {
        String taskTitle = titleField.getText().trim();

        // Required: Task Title
        if (taskTitle.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Missing required field. Please enter task title.");
            alert.showAndWait();
            return;
        }

        // Optional: Task Description
        String taskDesc;
        if (descField.getText() == null || descField.getText().isEmpty()) {
            taskDesc = null;
        } else {
            taskDesc = descField.getText().trim();
        }

        // Optional: Task Category
        String taskCategory;
        if (subjectField.getText() == null || subjectField.getText().isEmpty()) {
            taskCategory = null;
        } else {
            taskCategory = subjectField.getText().trim();
        }

        // Optional: Task Priority
        Task.Priority taskPriority = null;
        if (priorityBox != null) {
            taskPriority = priorityBox.getValue();
        }

        // Optional: Task Due Date
        LocalDate taskDueDate = null;
        if (dueDatePicker != null) {
            taskDueDate = dueDatePicker.getValue();
        }

        // Optional: Task Time Estimate
        int taskTimeEstimate = 0;
        if (timeSpinner.getValue() != null && timeSpinner.getValue() > 0) {
            taskTimeEstimate = timeSpinner.getValue();
        }

        // Sets initial status to NOT_STARTED
        Task.Status initialStatus = Task.Status.NOT_STARTED;

        // Creates a new task using the inputted information
        Task newTask = TaskController.createTask(taskTitle, taskDesc, taskDueDate, taskTimeEstimate, taskPriority, initialStatus, taskCategory);
        createTaskCard(newTask);

        // Hides overlay and clears text fields after task is added
        addTaskOverlay.setVisible(false);
        addTaskOverlay.setManaged(false);
        clearAddTaskForm();  
    }

    // Clears the "Add Task" form
    @FXML
    private void clearAddTaskForm() {
        titleField.clear();
        descField.clear();
        subjectField.clear();
        priorityBox.setValue(null);
        dueDatePicker.setValue(null);
        timeSpinner.getValueFactory().setValue(0);
    }
}
    
