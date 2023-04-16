package frc.teamdata;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String[] command =
    {
        "cmd",
    };
        Process p = Runtime.getRuntime().exec(command);
        new Thread(new SyncPipe(p.getInputStream())).start();
        PrintWriter stdin = new PrintWriter(p.getOutputStream());
        stdin.println("dir c:\\C:/Users/DesignTeam/Downloads/BSDataCollection/BSDataCollection.jar");
        stdin.println("jar -tvf BSDataCollection.jar");
        stdin.close();
        int returnCode = p.waitFor();
        System.out.println("Return code = " + returnCode);
        launch();
    }

    @Override
public void stop() throws InterruptedException{
    if (!(DataDisplay.executorService == null)) {
    DataDisplay.executorService.shutdown();
    }
}

}