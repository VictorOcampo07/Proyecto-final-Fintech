package fintech.vistas;

import fintech.modelo.PlataformaFintech;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogNuevaBilleteraController {

    @FXML private TextField txtIdUsuario;
    @FXML private TextField txtIdBilletera;
    @FXML private ComboBox<String> comboTipoBilletera;
    @FXML private TextField txtSaldoInicial;

    private PlataformaFintech fintech;
    private Stage stage;

    @FXML
    public void initialize() {
        comboTipoBilletera.setItems(FXCollections.observableArrayList(
                "Ahorro", "Gastos diarios", "Compras", "Transporte", "Inversion"
        ));
        comboTipoBilletera.getSelectionModel().selectFirst();
    }

    public void setFintech(PlataformaFintech fintech, Stage stage) {
        this.fintech = fintech;
        this.stage = stage;
    }

    @FXML
    private void handleGuardar() {
        try {
            String uId = txtIdUsuario.getText().trim();
            String bId = txtIdBilletera.getText().trim();
            String tipo = comboTipoBilletera.getValue();
            double saldo = Double.parseDouble(txtSaldoInicial.getText());

            fintech.crearBilletera(uId, bId, tipo, saldo);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Billetera creada con éxito.");
            alert.showAndWait();
            stage.close();
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