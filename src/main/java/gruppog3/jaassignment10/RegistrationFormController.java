package gruppog3.jaassignment10;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegistrationFormController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
