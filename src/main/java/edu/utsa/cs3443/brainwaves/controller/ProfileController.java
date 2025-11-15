package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Badge;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ProfileController {

    @FXML private Label usernameLabel;
    @FXML private ProgressBar xpBar;
    @FXML private Label xpLabel;
    @FXML private FlowPane badgeContainer;

    private int currentXP = 0;
    private final int maxXP = 100;
    private final List<Badge> badges = new ArrayList<>();
    private boolean nameUpdated = false;
    private boolean quoteEntered = false;

    public void initialize() {
        usernameLabel.setText("Hey Snoopy!");
        updateXP(50);
        loadBadges();
        renderBadges();

        // 🔹 Interactivity triggers
        usernameLabel.setOnMouseClicked(e -> promptForName());
        xpLabel.setOnMouseClicked(e -> promptForQuote());

        // 🔹 Tooltips for guidance
        usernameLabel.setTooltip(new Tooltip("Click to personalize your name"));
        xpLabel.setTooltip(new Tooltip("Click to enter your motivation quote"));
    }

    private void promptForName() {
        flashLabel(usernameLabel); //  Visual feedback
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Update Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter your name:");
        dialog.showAndWait().ifPresent(name -> {
            usernameLabel.setText("Hey " + name + "!");
            if (!nameUpdated) {
                updateXP(10);
                nameUpdated = true;
                checkProfileBadge();
            }
        });
    }

    private void promptForQuote() {
        flashLabel(xpLabel); //  Visual feedback
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Your Motivation");
        dialog.setHeaderText(null);
        dialog.setContentText("What motivates you today?");
        dialog.showAndWait().ifPresent(quote -> {
            System.out.println("Saved quote: " + quote);
            if (!quoteEntered) {
                updateXP(10);
                quoteEntered = true;
                checkProfileBadge();
            }
        });
    }

    private void checkProfileBadge() {
        if (nameUpdated && quoteEntered) {
            unlockBadge(99); // hypothetical badge ID for "Profile Pioneer"
        }
    }

    private void loadBadges() {
        try (BufferedReader br = new BufferedReader(new FileReader(
                "src/main/resources/edu/utsa/cs3443/brainwaves/data/badges.csv"))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String description = parts[2].trim();
                    String iconFile = parts[3].trim();
                    String iconPath = "/edu/utsa/cs3443/brainwaves/icons/" + iconFile;
                    badges.add(new Badge(id, name, description, iconPath));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading badges: " + e.getMessage());
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
                card.setUserData(controller);
                badgeContainer.getChildren().add(card);
            } catch (IOException e) {
                System.err.println("Error rendering badge: " + e.getMessage());
            }
        }
    }

    public void updateXP(int amount) {
        currentXP = Math.min(maxXP, currentXP + amount);
        xpBar.setProgress((double) currentXP / maxXP);
        xpLabel.setText("XP: " + currentXP + "/" + maxXP);

        //  Pulse effect on XP bar
        xpBar.setStyle("-fx-accent: #55CBCD;");
        PauseTransition pulse = new PauseTransition(Duration.seconds(0.5));
        pulse.setOnFinished(e -> xpBar.setStyle(""));
        pulse.play();
    }

    public void unlockBadge(int badgeId) {
        for (Node node : badgeContainer.getChildren()) {
            BadgeCardController controller = (BadgeCardController) node.getUserData();
            if (controller.getBadge().getId() == badgeId) {
                controller.getBadge().setEarned(true);
                controller.refresh();

                //  Glow effect on unlocked badge
                node.setStyle("-fx-effect: dropshadow(gaussian, #55CBCD, 10, 0.5, 0, 0);");
                PauseTransition glow = new PauseTransition(Duration.seconds(1));
                glow.setOnFinished(e -> node.setStyle(""));
                glow.play();
            }
        }
    }

    //  Flash effect for interactive labels
    private void flashLabel(Label label) {
        label.setStyle("-fx-background-color: #55CBCD; -fx-text-fill: white;");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> label.setStyle(""));
        pause.play();
    }
}





