package fintech.vistas;

import fintech.modelo.PlataformaFintech;
import javafx.event.ActionEvent;
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

    @FXML
    private void abrirVistaAnalitica() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaAnalitica.fxml"));
        Parent root = loader.load();

        AnaliticaController controller = loader.getController();
        controller.setFintech(this.fintech);

        mainContainer.setCenter(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    private void abrirVistaNotificaciones() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/VistaNotificaciones.fxml"));
        Parent root = loader.load();

        NotificacionesController controller = loader.getController();
        controller.setFintech(this.fintech);

        mainContainer.setCenter(root);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @FXML
    private void handleEjecutarOperacion(){
        System.out.println("Ejecutando operación desde MainWindowController");
    }
    @FXML
    private void handleRevertirOperacion(ActionEvent event) {
       
        System.out.println("Operación revertida.");
    }
    @FXML
    private void handleGenerarReporte(ActionEvent event) {
        
        System.out.println("Reporte generado.");
    }
}