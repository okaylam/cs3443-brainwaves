package edu.utsa.cs3443.brainwaves.model;

import java.time.LocalDate;

public class Task {
    private final String id;
    private String taskTitle;
    private String taskDesc;
    private LocalDate dueDate;
    private int timeEstimate;
    private Priority priority;
    private Status status;
    private String category;

    // TODO: Change enum names, and maybe implement a different way to show these values
    public enum Priority { 
        LOW("Low", "#e8f5e9"), 
        MEDIUM("Medium", "#fff3cd"), 
        HIGH("High", "#ffe0db");

        private final String label;
        private final String color;

        Priority(String label, String color) {
            this.label = label;
            this.color = color;
        }

        public String getColor() {
            return color;
        }
        
        @Override
        public String toString() {
            return label;
        }
    }

    public enum Status {
        NOT_STARTED("Not Started", "#F0F2FA"), 
        IN_PROGRESS("In Progress", "#CD26FF"), 
        COMPLETED ("Completed", "#C2BED8");

        private final String label;
        private final String color;
        
        Status(String label) {
            this.label = label;
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    // Constructor
    public Task(String id, String taskTitle, String taskDesc, LocalDate dueDate, int timeEstimate,
                Priority priority, Status status, String category) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
        this.dueDate = dueDate;
        this.timeEstimate = timeEstimate;
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
    public int getTimeEstimate() { return timeEstimate; }
    public Priority getPriority() { return  priority; }
    public Status getStatus() { return status; }
    public String getCategory() { return category; }

    // Setters
    public void setTaskTitle(String taskTitle) { this.taskTitle = taskTitle; }
    public void setTaskDesc(String taskDesc) { this.taskDesc = taskDesc; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setTimeEstimate(int timeEstimate) { this.timeEstimate = timeEstimate; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setStatus(Status status) { this.status = status; }
    public void setCategory(String category) { this.category = category; }
}
