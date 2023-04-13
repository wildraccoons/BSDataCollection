package frc.teamdata;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    public TextField Red1;
    @FXML
    public TextField Red2;
    @FXML
    public TextField Red3;
    @FXML
    public TextField Blue1;
    @FXML
    public TextField Blue2;
    @FXML
    public TextField Blue3;
    @FXML
    public Label ErrorStatement;

    public static StringBuffer Team1  = new StringBuffer();
    public static StringBuffer Team2 = new StringBuffer();
    public static StringBuffer Team3 = new StringBuffer();
    public static StringBuffer Team4 = new StringBuffer();
    public static StringBuffer Team5 = new StringBuffer();
    public static StringBuffer Team6 = new StringBuffer();
    public static StringBuffer amountOfTeams = new StringBuffer("");

    @FXML
    private void switchToSecondary() throws IOException {
        Team1.replace(0, Team1.length(), Red1.getText());
        Team2.replace(0, Team2.length(), Red2.getText());
        Team3.replace(0, Team3.length(), Red3.getText());
        Team4.replace(0, Team4.length(), Blue1.getText());
        Team5.replace(0, Team5.length(), Blue2.getText());
        Team6.replace(0, Team6.length(), Blue3.getText());
        amountOfTeams.replace(0, amountOfTeams.length(), String.valueOf(SecondaryController.getTeamNumbers(Team1, Team2, Team3, Team4, Team5, Team6)));
        switch (SecondaryController.getTeamNumbers(Team1, Team2, Team3, Team4, Team5, Team6)) {
            case 1:
                App.setRoot("singleteam");
                break;
            case 2:
                App.setRoot("twoteam");
                break;
            case 3:
                App.setRoot("threeteam");
                break;
            case 4:
                App.setRoot("quadteam");
                break;
            case 5:
                App.setRoot("fiveteam");
                break;
            case 6:
                App.setRoot("secondary");
                break;
                default:
                ErrorStatement.setText("Please submit valid team numbers!");
                break;
            }
    }

    @FXML
    private void switchToTeamData() throws IOException {
        App.setRoot("teamdata");
    }
}
