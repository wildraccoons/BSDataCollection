package frc.teamdata;

import java.io.IOException;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class SecondaryController {
    ObservableList<String> driveTrainList = FXCollections.observableArrayList("Swerve Drive", "Tank Drive");
    ScoreCalculator scoreBoard = new ScoreCalculator();
    @FXML
    public Label LTeam1;
    @FXML
    public Label LTeam2;
    @FXML
    public Label LTeam3;
    @FXML
    public Label LTeam4;
    @FXML
    public Label LTeam5;
    @FXML
    public Label LTeam6;
    @FXML
    public Label ErrorStatement;
    @FXML
    public ChoiceBox driveTrain1, driveTrain2, driveTrain3, driveTrain4, driveTrain5, driveTrain6;
    @FXML
    public CheckBox a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25,  e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16, g17, g18, g19, g20, g21, g22, g23, g24, g25;
    @FXML
    public CheckBox RedWon, BlueWon;
    @FXML
    public void setTeamNumbers() {
    LTeam1.setText(PrimaryController.Team1.toString());
    LTeam2.setText(PrimaryController.Team2.toString());
    LTeam3.setText(PrimaryController.Team3.toString());
    LTeam4.setText(PrimaryController.Team4.toString());
    LTeam5.setText(PrimaryController.Team5.toString());
    LTeam6.setText(PrimaryController.Team6.toString());
}
   
    @FXML
    private void initialize() {
        setTeamNumbers();
        driveTrain1.setValue("Swerve Drive");
        driveTrain1.setItems(driveTrainList);
        driveTrain2.setValue("Swerve Drive");
        driveTrain2.setItems(driveTrainList);
        driveTrain3.setValue("Swerve Drive");
        driveTrain3.setItems(driveTrainList);
        driveTrain4.setValue("Swerve Drive");
        driveTrain4.setItems(driveTrainList);
        driveTrain5.setValue("Swerve Drive");
        driveTrain5.setItems(driveTrainList);
        driveTrain6.setValue("Swerve Drive");
        driveTrain6.setItems(driveTrainList);
        RedWon.setAllowIndeterminate(false);
        BlueWon.setAllowIndeterminate(false);
    }
    @FXML
    private void switchToPrimary() throws IOException{
        boolean databaseFound = false;
        try {
        scoreBoard.evaluateScore(PrimaryController.Team1.toString(), (String) driveTrain1.getValue(), RedWon.isSelected(), a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25);
        scoreBoard.evaluateScore(PrimaryController.Team2.toString(), (String) driveTrain2.getValue(), RedWon.isSelected(), b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25);
        scoreBoard.evaluateScore(PrimaryController.Team3.toString(), (String) driveTrain3.getValue(), RedWon.isSelected(), c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25);
        scoreBoard.evaluateScore(PrimaryController.Team4.toString(), (String) driveTrain4.getValue(), BlueWon.isSelected(), d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25);
        scoreBoard.evaluateScore(PrimaryController.Team5.toString(), (String) driveTrain5.getValue(), BlueWon.isSelected(), e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25);
        scoreBoard.evaluateScore(PrimaryController.Team6.toString(), (String) driveTrain6.getValue(), BlueWon.isSelected(), g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16, g17, g18, g19, g20, g21, g22, g23, g24, g25);
        databaseFound = true;
        } catch (SQLException | ClassNotFoundException e) {
            ErrorStatement.setText("DataBase not found, please check if mydb exists, is connected to the right server and contains the right column names and datatypes!");
        }
        if (databaseFound) {
        App.setRoot("primary");
        }
    }
}