package main.java.fintech.vistas;

import main.java.fintech.modelo.PlataformaFintech;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Parent;
import java.io.IOException;

public class MainWindowController {

    @FXML private BorderPane mainContainer;

    // Instancia única compartida por toda la aplicación
    private PlataformaFintech fintech = new PlataformaFintech();

    @FXML
    public void initialize() {
        // Cargar datos demo en el motor
        fintech.registrarUsuario("U001", "Ana Gomez", "ana@mail.com");
        fintech.registrarUsuario("U002", "Carlos Ruiz", "carlos@mail.com");
        fintech.crearBilletera("U001", "B001", "Gastos diarios", 1500.0);
        fintech.crearBilletera("U002", "B002", "Ahorro", 500.0);
    }

    // Método para cambiar o cargar sub-vistas dinámicamente
    @FXML
    private void abrirVistaOperaciones() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaOperaciones.fxml"));
            Parent root = loader.load();

            // Inyectar la instancia compartida de fintech al controlador hijo
            OperacionesController controller = loader.getController();
            controller.setFintech(this.fintech);

            mainContainer.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}