package frc.teamdata;

import java.io.IOException;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    @FXML
    public TableColumn<Team, String> Rank = new TableColumn<>("Rank");
    @FXML
    public ComboBox<String> rankBox;
    @FXML
    public TextField rowFilter;
    @FXML
    ProgressIndicator indicator;

    TableViewSelectionModel<Team> selectionModel;
    public static ScheduledExecutorService executorService;

    ObservableList<Integer> roundOptions = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8);

    @FXML
    public ComboBox<Integer> roundBox;

    Runnable autoUpdate = () -> {
        try {
            indicator.setVisible(true);
            LocalTime time = LocalTime.now();
            while (1 > ChronoUnit.SECONDS.between(time, LocalTime.now())) {

            }
            if(teamdata.checkForUpdates()) {
                updateTable();
            }
            indicator.setVisible(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
    @FXML
    private void initialize() throws ClassNotFoundException, SQLException {
        teamdata = new SQLManager();
        TeamNumber.setCellValueFactory(new PropertyValueFactory<>("TeamNumber"));
        DriveTrain.setCellValueFactory(new PropertyValueFactory<>("DriveTrain"));
        Auto.setCellValueFactory(new PropertyValueFactory<>("Auto"));
        Offense.setCellValueFactory(new PropertyValueFactory<>("Offense"));
        Defense.setCellValueFactory(new PropertyValueFactory<>("Defense"));
        Mobility.setCellValueFactory(new PropertyValueFactory<>("Mobility"));
        Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        WinStreak.setCellValueFactory(new PropertyValueFactory<>("WinStreak"));
        Rank.setCellValueFactory(new PropertyValueFactory<>("Rank"));
        roundBox.setValue(3);
        roundBox.setItems(roundOptions);
        rankBox.setValue("Total");
        rankBox.setItems(FXCollections.observableArrayList("Auto", "Offense", "Defense", "Mobility", "Total"));
        teamdata.updateFXTable(TeamData, rankBox.getValue());
        selectionModel = TeamData.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(autoUpdate, 10, 180, TimeUnit.SECONDS);
    }

    @FXML
    private void goBack() throws IOException, InterruptedException {
        executorService.shutdown();
        App.setRoot("primary");
    }

    @FXML
    private void updateTable() throws SQLException {
        TeamData.getItems().removeAll(TeamData.getItems());
        if (rowFilter.getText() != null) {
            teamdata.filterRows(TeamData, rowFilter.getText(), roundBox.getValue(), rankBox.getValue());
        } else {
            teamdata.updateFXTable(TeamData, roundBox.getValue(), rankBox.getValue());
        }
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
