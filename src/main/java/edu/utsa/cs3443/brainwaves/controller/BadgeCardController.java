package edu.utsa.cs3443.brainwaves.controller;

import edu.utsa.cs3443.brainwaves.model.Badge;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for the badge-card.fxml layout.
 * Responsible for displaying a single badge's icon, name, and description.
 */
public class BadgeCardController {

    @FXML private ImageView badgeIcon;
    @FXML private Label badgeName;
    @FXML private Label badgeDescription;

    /**
     * Creates the badge card UI with data from a Badge object.
     *
     * Badge model containing name, description, and icon path
     */
    public void setBadge(Badge badge) {
        badgeName.setText(badge.getName());
        badgeDescription.setText(badge.getDescription());

        if (badge.getIconPath() != null) {
            badgeIcon.setImage(new Image(getClass().getResourceAsStream(badge.getIconPath())));
        }
    }
}
