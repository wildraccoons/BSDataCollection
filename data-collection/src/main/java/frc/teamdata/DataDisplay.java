package frc.teamdata;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DataDisplay {
SQLManager teamdata;
@FXML
public TableView TeamData;
@FXML
public TableColumn<Team, String> TeamNumber = new TableColumn<>("Team Number");
@FXML
public TableColumn<Team, String> DriveTrain = new TableColumn<>("DriveTrain");
@FXML
public TableColumn<Team, Double> Auto = new TableColumn<>("Auto");
@FXML
public TableColumn<Team, Double> Offense = new TableColumn<>("Offense");
@FXML
public TableColumn<Team, Double> Defense = new TableColumn<>("Defense");
@FXML
public TableColumn<Team, Double> Mobility = new TableColumn<>("Mobility");
@FXML
public TableColumn<Team, Double> Total = new TableColumn<>("Total");
@FXML
public TableColumn<Team, Integer> WinStreak = new TableColumn<>("WinStreak");

@FXML
private void initialize() throws ClassNotFoundException, SQLException {
    teamdata = new SQLManager("null");
    TeamNumber.setCellValueFactory(new PropertyValueFactory<>("TeamNumber"));
    DriveTrain.setCellValueFactory(new PropertyValueFactory<>("DriveTrain"));
    Auto.setCellValueFactory(new PropertyValueFactory<>("Auto"));
    Offense.setCellValueFactory(new PropertyValueFactory<>("Offense"));
    Defense.setCellValueFactory(new PropertyValueFactory<>("Defense"));
    Mobility.setCellValueFactory(new PropertyValueFactory<>("Mobility"));
    Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
    WinStreak.setCellValueFactory(new PropertyValueFactory<>("WinStreak"));
    teamdata.updateFXTable(TeamData);
}

@FXML
private void goBack() throws IOException {
    App.setRoot("primary");
}

@FXML
private void updateTable() throws SQLException {
    TeamData.getItems().removeAll(TeamData.getItems());
    teamdata.updateFXTable(TeamData);
}
}

