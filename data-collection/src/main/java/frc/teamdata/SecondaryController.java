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
    public ChoiceBox<String> driveTrain1, driveTrain2, driveTrain3, driveTrain4, driveTrain5, driveTrain6;
    @FXML
    public CheckBox a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25,  e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16, g17, g18, g19, g20, g21, g22, g23, g24, g25;
    @FXML
    public CheckBox RedWon, BlueWon, won1, won2, won3, won4, won5;
    
    public static int getTeamNumbers(StringBuffer Team1, StringBuffer Team2, StringBuffer Team3, StringBuffer Team4, StringBuffer Team5, StringBuffer Team6) throws IOException {
        String[] teamNumbers = {Team1.toString(), Team2.toString(), Team3.toString(), Team4.toString(), Team5.toString(), Team6.toString()};
        int i = 0;
        for (String a: teamNumbers) {
            try {
                Integer.valueOf(a);
                i++;
            } catch (NumberFormatException e) {

            }
        }
        return i;

}   
    public void setTeamNumbers() {
        String[] teamNumbers = {PrimaryController.Team1.toString(), PrimaryController.Team2.toString(), PrimaryController.Team3.toString(), PrimaryController.Team4.toString(), PrimaryController.Team5.toString(), PrimaryController.Team6.toString()};
        int i = 0;
        for (String a: teamNumbers) {
            try {
                Integer.valueOf(a);
                i++;
                switch (i) {
                    case 1:
                    LTeam1.setText(a);
                        break;
                    case 2:
                    LTeam2.setText(a);
                        break;
                    case 3:
                    LTeam3.setText(a);
                        break;
                    case 4:
                    LTeam4.setText(a);
                        break;
                    case 5:
                    LTeam5.setText(a);
                        break;
                    case 6:
                    LTeam6.setText(a);
                        break;
                    }
            } catch (NumberFormatException e) {}
        }
    }
   
    @FXML
    private void initialize() throws IOException {
        setTeamNumbers();
        switch (PrimaryController.amountOfTeams.toString()) {
            case "1":
            driveTrain1.setValue("Swerve Drive");
            driveTrain1.setItems(driveTrainList);
            won1.setAllowIndeterminate(false);
            break;
            case "2":
            driveTrain1.setValue("Swerve Drive");
            driveTrain1.setItems(driveTrainList);
            driveTrain2.setValue("Swerve Drive");
            driveTrain2.setItems(driveTrainList);
            won1.setAllowIndeterminate(false);
            won2.setAllowIndeterminate(false);
            break;
            case "3":
            driveTrain1.setValue("Swerve Drive");
            driveTrain1.setItems(driveTrainList);
            driveTrain2.setValue("Swerve Drive");
            driveTrain2.setItems(driveTrainList);
            driveTrain3.setValue("Swerve Drive");
            driveTrain3.setItems(driveTrainList);
            won1.setAllowIndeterminate(false);
            won2.setAllowIndeterminate(false);
            won3.setAllowIndeterminate(false);
        break;
            case "4":
            driveTrain1.setValue("Swerve Drive");
            driveTrain1.setItems(driveTrainList);
            driveTrain2.setValue("Swerve Drive");
            driveTrain2.setItems(driveTrainList);
        driveTrain3.setValue("Swerve Drive");
        driveTrain3.setItems(driveTrainList);
        driveTrain4.setValue("Swerve Drive");
        driveTrain4.setItems(driveTrainList);
        won1.setAllowIndeterminate(false);
            won2.setAllowIndeterminate(false);
            won3.setAllowIndeterminate(false);
            won4.setAllowIndeterminate(false);
        break;
            case "5":
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
            won1.setAllowIndeterminate(false);
            won2.setAllowIndeterminate(false);
            won3.setAllowIndeterminate(false);
            won4.setAllowIndeterminate(false);
            won5.setAllowIndeterminate(false);
            break;
            case "6":
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
        break;
        }
    }
    @FXML
    private void goBack() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToPrimary() throws IOException{
        try {
            setTeamNumbers();
        switch (PrimaryController.amountOfTeams.toString()) {
            case "1":
            scoreBoard.evaluateScore(LTeam1.getText(), (String) driveTrain1.getValue(), won1.isSelected(), a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25);
            break;
            case "2":
            scoreBoard.evaluateScore(LTeam1.getText(), (String) driveTrain1.getValue(), won1.isSelected(), a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25);
        scoreBoard.evaluateScore(LTeam2.getText(), (String) driveTrain2.getValue(), won2.isSelected(), b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25);
            break;
            case "3":
            scoreBoard.evaluateScore(LTeam1.getText(), (String) driveTrain1.getValue(), won1.isSelected(), a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25);
        scoreBoard.evaluateScore(LTeam2.getText(), (String) driveTrain2.getValue(), won2.isSelected(), b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25);
        scoreBoard.evaluateScore(LTeam3.getText(), (String) driveTrain3.getValue(), won3.isSelected(), c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25);
        break;
            case "4":
            scoreBoard.evaluateScore(LTeam1.getText(), (String) driveTrain1.getValue(), won1.isSelected(), a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25);
        scoreBoard.evaluateScore(LTeam2.getText(), (String) driveTrain2.getValue(), won2.isSelected(), b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25);
        scoreBoard.evaluateScore(LTeam3.getText(), (String) driveTrain3.getValue(), won3.isSelected(), c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25);
        scoreBoard.evaluateScore(LTeam4.getText(), (String) driveTrain4.getValue(), won4.isSelected(), d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25);
        break;
            case "5":
            scoreBoard.evaluateScore(LTeam1.getText(), (String) driveTrain1.getValue(), won1.isSelected(), a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25);
        scoreBoard.evaluateScore(LTeam2.getText(), (String) driveTrain2.getValue(), won2.isSelected(), b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25);
        scoreBoard.evaluateScore(LTeam3.getText(), (String) driveTrain3.getValue(), won3.isSelected(), c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25);
        scoreBoard.evaluateScore(LTeam4.getText(), (String) driveTrain4.getValue(), won4.isSelected(), d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25);
        scoreBoard.evaluateScore(LTeam5.getText(), (String) driveTrain5.getValue(), won5.isSelected(), e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25);
            break;
            case "6":
            scoreBoard.evaluateScore(LTeam1.getText(), (String) driveTrain1.getValue(), RedWon.isSelected(), a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25);
        scoreBoard.evaluateScore(LTeam2.getText(), (String) driveTrain2.getValue(), RedWon.isSelected(), b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25);
        scoreBoard.evaluateScore(LTeam3.getText(), (String) driveTrain3.getValue(), RedWon.isSelected(), c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25);
        scoreBoard.evaluateScore(LTeam4.getText(), (String) driveTrain4.getValue(), BlueWon.isSelected(), d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25);
        scoreBoard.evaluateScore(LTeam5.getText(), (String) driveTrain5.getValue(), BlueWon.isSelected(), e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25);
        scoreBoard.evaluateScore(LTeam6.getText(), (String) driveTrain6.getValue(), BlueWon.isSelected(), g1, g2, g3, g4, g5, g6, g7, g8, g9, g10, g11, g12, g13, g14, g15, g16, g17, g18, g19, g20, g21, g22, g23, g24, g25);
        break;
        }
        
        App.setRoot("primary");
        } catch (SQLException | ClassNotFoundException e) {
            ErrorStatement.setText("DataBase not found, please check if mydb exists, is connected to the right server and contains the right column names and datatypes!");
        }
    }

}