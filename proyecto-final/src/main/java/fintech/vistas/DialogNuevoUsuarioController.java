package main.java.fintech.vistas;

import main.java.fintech.modelo.PlataformaFintech;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogNuevoUsuarioController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;

    private PlataformaFintech fintech;
    private Stage stage;

    public void setFintech(PlataformaFintech fintech, Stage stage) {
        this.fintech = fintech;
        this.stage = stage;
    }

    @FXML
    private void handleGuardar() {
        try {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String email = txtEmail.getText().trim();

            if (id.isEmpty() || nombre.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos obligatorios deben estar llenos.");
            }

            fintech.registrarUsuario(id, nombre, email);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Usuario registrado con éxito.");
            alert.showAndWait();
            
            stage.close(); // Cerrar ventana modal
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancelar() {
        stage.close();
    }
}