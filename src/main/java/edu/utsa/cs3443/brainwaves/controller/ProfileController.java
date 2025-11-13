package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Badge;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.List;
/**
* Controller for prifile view.fxml
* Displays user info, XP progress, and earned badges.
*/
public class ProfileController {

    @FXML private Label usernameLabel;
    @FXML private ProgressBar xpBar;
    @FXML private HBox badgeContainer;

    public void initialize() {
      
        // Example user data
        usernameLabel.setText("Hey Snoopy!"); 
        
        // Example: 50% XP progress
        xpBar.setProgress(0.5); 

        // Example badges
        List<Badge> badges = List.of(
            new Badge(1, "Focus Master", "Complete 5 focus challenges", "/edu/utsa/cs3443/brainwaves/icons/focus.png"),
            new Badge(2, "Task Slayer", "Finish 10 tasks", "/edu/utsa/cs3443/brainwaves/icons/task.png")
        );

        // Load badge cards dynamically
        for (Badge badge : badges) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/edu/utsa/cs3443/brainwaves/fxml/badge-card.fxml"));
                Node card = loader.load();
                BadgeCardController controller = loader.getController();
                controller.setBadge(badge);
                badgeContainer.getChildren().add(card);
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
