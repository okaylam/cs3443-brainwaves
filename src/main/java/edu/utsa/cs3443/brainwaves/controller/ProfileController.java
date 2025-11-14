package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Badge;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//**
 * Controller for profile-view.fxml
 * Displays user info, XP progress, and earned badges.
 *//
public class ProfileController {

    @FXML private Label usernameLabel;
    @FXML private ProgressBar xpBar;
    @FXML private Label xpLabel;
    @FXML private FlowPane badgeContainer;

    private int currentXP = 0;
    private int maxXP = 100;
    private List<Badge> badges = new ArrayList<>();

    public void initialize() {
        // Example user data (later you can load from a user file/settings)
        usernameLabel.setText("Hey Snoopy!");

        // Initialize XP
        updateXP(50); 

        // Load badges from CSV
        loadBadges();

        // Render badge cards
        renderBadges();
    }

    private void loadBadges() {
        try (BufferedReader br = new BufferedReader(new FileReader(
                "src/main/resources/edu/utsa/cs3443/brainwaves/data/badges.csv"))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String description = parts[2];
                    String iconPath = parts[3];
                    badges.add(new Badge(id, name, description, iconPath));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renderBadges() {
        badgeContainer.getChildren().clear();
        for (Badge badge : badges) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/edu/utsa/cs3443/brainwaves/fxml/badge-card.fxml"));
                Node card = loader.load();
                BadgeCardController controller = loader.getController();
                controller.setBadge(badge);

                // Store controller reference for updates
                card.setUserData(controller);

                badgeContainer.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //** Update XP bar and label *//
    public void updateXP(int amount) {
        currentXP = Math.min(maxXP, currentXP + amount);
        xpBar.setProgress((double) currentXP / maxXP);
        xpLabel.setText("XP: " + currentXP + "/" + maxXP);
    }

    //** Unlock a badge when earned *//
    public void unlockBadge(int badgeId) {
        for (Node node : badgeContainer.getChildren()) {
            BadgeCardController controller = (BadgeCardController) node.getUserData();
            if (controller.getBadge().getId() == badgeId) {
                controller.getBadge().setEarned(true);
                controller.refresh(); // update UI (locked → earned)
            }
        }
    }
}


