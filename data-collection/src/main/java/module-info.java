module frc.teamdata {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;

    opens frc.teamdata to javafx.fxml;
    exports frc.teamdata;
}
