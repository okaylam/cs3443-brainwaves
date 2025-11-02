package edu.utsa.cs3443.brainwaves.model;

import java.time.LocalDate;
import java.util.Locale;

public class Task {
    private final String id;
    private String taskTitle;
    private String taskDesc;
    private LocalDate dueDate;
    private int timeToComplete;
    private Priority priority;
    private Status status;
    private String category;

    public enum Priority { LOW, MEDIUM, HIGH }
    public enum Status { TODO, INPROGRESS, COMPLETE }

    // Constructor
    public Task(String id, String taskTitle, String taskDesc, LocalDate dueDate, int timeToComplete,
                Priority priority, Status status, String category) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
        this.dueDate = dueDate;
        this.timeToComplete = timeToComplete;
        this.priority = priority;
        this.status = status;
        this.category = category;
    }

    // Checks if a task is overdue by looking at the due date and if the status is not set to COMPLETE
    public boolean isOverdue() {
        return dueDate != null
                && status != Status.COMPLETE
                && dueDate.isBefore(java.time.LocalDate.now());
    }

    // Checks if a task is completed by looking if the status is set to COMPLETE
    public boolean isCompleted() {
        return status == Status.COMPLETE;
    }

    // Getters
    public String getId() { return id; }
    public String getTaskTitle() { return  taskTitle; }
    public String getTaskDesc() { return  taskDesc; }
    public LocalDate getDueDate() { return dueDate; }
    public int getTimeToComplete() { return timeToComplete; }
    public Priority getPriority() { return  priority; }
    public Status getStatus() { return status; }
    public String getCategory() { return category; }

    // Setters
    public void setTaskTitle(String taskTitle) { this.taskTitle = taskTitle; }
    public void setTaskDesc(String taskDesc) { this.taskDesc = taskDesc; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setTimeToComplete(int timeToComplete) { this.timeToComplete = timeToComplete; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setStatus(Status status) { this.status = status; }
    public void setCategory(String category) { this.category = category; }
}
