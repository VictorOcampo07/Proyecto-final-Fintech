package main.java.fintech;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/VistaFintech.fxml"));
        primaryStage.setTitle("Plataforma Fintech - JavaFX & Scene Builder 2026");
        primaryStage.setScene(new Scene(root, 850, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
