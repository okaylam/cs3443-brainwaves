package edu.utsa.cs3443.brainwaves.model;

public class Challenge {
    private final int id;
    private final String name;
    private final String text;
    private final String type;
    private final String difficulty;
    private final int xp;
    private final String iconPath;
    private boolean completed;


    public Challenge(int id, String name, String text, String type, String difficulty, int xp, String iconPath) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.type = type;
        this.difficulty = difficulty;
        this.xp = xp;
        this.iconPath = iconPath;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getText() { return text; }
    public String getType() { return type; }
    public String getDifficulty() { return difficulty; }
    public int getXP() { return xp; }
    public String getIconPath() { return iconPath; }
    public boolean isCompleted() { return completed; }
}
