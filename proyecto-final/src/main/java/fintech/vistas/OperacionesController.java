package fintech.vistas;

import fintech.modelo.PlataformaFintech;
import fintech.modelo.Transaccion;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OperacionesController {

    @FXML private ComboBox<String> comboTipoOp;
    @FXML private TextField txtOrigen;
    @FXML private TextField txtDestino;
    @FXML private TextField txtMonto;

    private PlataformaFintech fintech;

    // Recibe la instancia compartida desde el controlador principal
    public void setFintech(PlataformaFintech fintech) {
        this.fintech = fintech;
    }

    @FXML
    private void handleEjecutarOperacion() {
        try {
            String tipo = comboTipoOp.getValue();
            String orig = txtOrigen.getText();
            String dest = txtDestino.getText();
            double monto = Double.parseDouble(txtMonto.getText());

            Transaccion tx = fintech.realizarOperacion(tipo, orig, dest, monto);
            mostrarMensaje("Éxito", "Transacción " + tx.getIdTx() + " realizada.");
        } catch (Exception e) {
            mostrarMensaje("Error", e.getMessage());
        }
    }

    private void mostrarMensaje(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}