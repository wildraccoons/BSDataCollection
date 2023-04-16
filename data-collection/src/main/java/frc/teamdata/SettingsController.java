package frc.teamdata;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class SettingsController {
    @FXML
    public PasswordField password;
    @FXML
    public Label passwordError;
    @FXML
    public void submitPassword() throws IOException {
        if (password.getText().equals("frccoons")) {
            App.setRoot("settings");
        } else {
            passwordError.setText("Incorrect Password!");
        }
    }
    @FXML
    private void goBack() throws IOException {
        App.setRoot("primary");
    }
}