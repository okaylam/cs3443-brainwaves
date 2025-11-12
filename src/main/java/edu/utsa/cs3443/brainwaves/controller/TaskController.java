package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Task;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskController {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "src/main/resources/edu/utsa/cs3443/brainwaves/data/tasks.csv";

    public void loadTasks() throws IOException {
        tasks.clear();

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            String line;

            // Reads the header and ignores it
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String id = parts[0];
                String taskTitle = parts[1];
                String taskDesc = parts[2];
                LocalDate dueDate = LocalDate.parse(parts[3]);
                int timeEstimate = Integer.parseInt(parts[4]);
                Task.Priority priority = Task.Priority.valueOf(parts[5]);
                Task.Status status = Task.Status.valueOf(parts[6]);
                String category = parts[7];

                Task task = new Task(id, taskTitle, taskDesc, dueDate, timeEstimate, priority, status, category);
                tasks.add(task);
            }
        } catch (Exception e) {
            // FIXME: Improve error handling and add user-friendly alerts
            e.printStackTrace();
        }
    }

    public void saveTasks() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));

            bw.write("id,tasktitle,taskDesc,dueDate,timeToComplete,priority,status,category");
            bw.newLine();

            for (Task t : tasks) {
                String dueDateString = t.getDueDate().toString();
                bw.write(t.getId() + "," + t.getTaskTitle() + "," +
                        t.getTaskDesc() + "," + dueDateString + "," +
                        t.getTimeEstimate() + "," + t.getPriority() + "," +
                        t.getStatus() + "," + t.getCategory());
                bw.newLine();
            }

            bw.close();
        } catch (Exception e) {
            // FIXME: Improve error handling and add user-friendly alerts
            e.printStackTrace();
        }

    }


    // TODO: Input validation & normalization
    // Currently we assume valid inputs only, but later we need to expand for input validation and null values
    // Later add-ons:
    // 1. Require taskTitle is not null
    // 2. Default priority = "MEDIUM" if none selected
    // 3. Default status = "TODO" if none selected
    // 4. Allow dueDate, taskDesc, and category to be optional
    public static Task createTask(String taskTitle, String taskDesc, LocalDate dueDate, int timeEstimate,
                                  Task.Priority priority, Task.Status status, String category) {
        String id = createNextID();
        Task task = new Task(id, taskTitle, taskDesc, dueDate, timeEstimate, priority, status, category);
        tasks.add(task);
        return task;
    }

    // Creates IDs for tasks incrementally
    private static String createNextID() {
        int max = 0;

        // Loops through tasks to find the highest ID number
        for (Task t : tasks) {
            String id = t.getId();

            if (id != null && id.startsWith("t")) {
                String idNumber = id.substring(1); // Retrieves the numerical part of the task IDs
                int num = Integer.parseInt(idNumber);
                if (num > max) {
                    max = num;
                }
            }
        }

        int next = max + 1;

        // Returns task ID in the correct format
        if (next < 10) {
            return "t0" + next;
        } else {
            return "t" + next;
        }
    }

    // Getter
    public ArrayList<Task> getTasks() { return tasks; }
}
