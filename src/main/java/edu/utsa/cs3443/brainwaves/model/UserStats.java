package edu.utsa.cs3443.brainwaves.model;

public class UserStats {
    private int level;
    private int currentXP;
    private int maxXP;

    private static final int[] levelRequirements = {
            0,  // dummy
            25, // level 1
            75, // level 2
            150, // level 3
            250, // level 4
            500 // level 5
    };

    public UserStats(int level, int currentXP) {
        this.level = level;
        this.currentXP = currentXP;
        this.maxXP = levelRequirements[level];
    }

    // Returns the current level progress
    public double getProgress() {
        return (double) currentXP / maxXP;
    }

    // Returns a String showing XP progress
    public String getXPLabel() {
        return currentXP + "/" + maxXP + " XP";
    }

    // Adds XP and levels the user up if they reach the xp cap
    public void addXP(int amount) {
        currentXP += amount;
        while (currentXP >= maxXP) {
            currentXP -= maxXP;
            level++;
            maxXP = levelRequirements[level];
        }
    }

    public int getLevel() { return level; }
    public int getCurrentXP() { return currentXP; }
    public int getMaxXP() { return maxXP; }
}
