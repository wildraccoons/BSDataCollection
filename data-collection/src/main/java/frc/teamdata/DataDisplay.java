package frc.teamdata;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;

public class DataDisplay {
    SQLManager teamdata;
    @FXML
    public TableView<Team> TeamData;
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

    TableViewSelectionModel<Team> selectionModel;
    public static ScheduledExecutorService executorService;

    Runnable runnableTask = () -> {
        try {
            if(teamdata.checkForUpdates()) {
                updateTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
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
        selectionModel = TeamData.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(runnableTask, 10, 180, TimeUnit.SECONDS);
    }

    @FXML
    private void goBack() throws IOException, InterruptedException {
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        App.setRoot("primary");
    }

    


    @FXML
    private void updateTable() throws SQLException {
        TeamData.getItems().removeAll(TeamData.getItems());
        teamdata.updateFXTable(TeamData);
    }

    @FXML
    private void clearAll() throws SQLException {
        teamdata.clearAllData();
        updateTable();
    }

    @FXML
    private void clearSelectedTeams() throws SQLException {
        teamdata.clearSelectedData(selectionModel.getSelectedItems().toArray());
        selectionModel.clearSelection();
        updateTable();
    }

}
