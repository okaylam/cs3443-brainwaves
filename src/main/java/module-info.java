module edu.utsa.cs3443.brainwaves {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens edu.utsa.cs3443.brainwaves to javafx.fxml;
    opens edu.utsa.cs3443.brainwaves.controller to javafx.fxml;
    opens edu.utsa.cs3443.brainwaves.model to javafx.fxml;

    exports edu.utsa.cs3443.brainwaves;
}