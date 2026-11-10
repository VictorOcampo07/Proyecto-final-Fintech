package fintech;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        java.net.URL fxmlLocation = classLoader.getResource("vistas/VistaFintech.fxml");

        if (fxmlLocation == null) {
            
            fxmlLocation = classLoader.getResource("resources/vistas/VistaFintech.fxml");
        }

        if (fxmlLocation == null) {
            throw new IllegalStateException("No se pudo encontrar el archivo VistaFintech.fxml");
        }

        Parent root = FXMLLoader.load(fxmlLocation);
        primaryStage.setTitle("Plataforma Fintech - JavaFX & Scene Builder 2026");
        primaryStage.setScene(new Scene(root, 850, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}