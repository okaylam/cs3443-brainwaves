module edu.utsa.cs3443.brainwaves {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.utsa.cs3443.brainwaves to javafx.fxml;
    exports edu.utsa.cs3443.brainwaves;
}