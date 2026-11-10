package fintech.vistas;

import fintech.modelo.OperacionProgramada;
import fintech.modelo.PlataformaFintech;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProgramadasController {

    @FXML private TextField txtOrigen;
    @FXML private TextField txtDestino;
    @FXML private TextField txtMonto;
    @FXML private DatePicker dpFecha;

    @FXML private TableView<OperacionProgramada> tablaProgramadas;
    @FXML private TableColumn<OperacionProgramada, LocalDateTime> colFecha;
    @FXML private TableColumn<OperacionProgramada, String> colOrigen;
    @FXML private TableColumn<OperacionProgramada, String> colDestino;
    @FXML private TableColumn<OperacionProgramada, Double> colMonto;

    private PlataformaFintech fintech;
    private ObservableList<OperacionProgramada> listaProgramadas = FXCollections.observableArrayList();

    public void setFintech(PlataformaFintech fintech) {
        this.fintech = fintech;
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaEjecucion"));
        colOrigen.setCellValueFactory(new PropertyValueFactory<>("origenId"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destinoId"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        actualizarTabla();
    }

    @FXML
    private void handleProgramar() {
        try {
            String orig = txtOrigen.getText();
            String dest = txtDestino.getText();
            double monto = Double.parseDouble(txtMonto.getText());
            LocalDate fecha = dpFecha.getValue();

            if (fecha == null) throw new IllegalArgumentException("Debe seleccionar una fecha.");

            fintech.programarOperacion(orig, dest, monto, fecha.atStartOfDay());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Operación programada en la PriorityQueue.");
            actualizarTabla();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void handleSimularEjecucion() {
        int procesadas = fintech.procesarOperacionesProgramadas();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Simulación", "Se ejecutaron " + procesadas + " operaciones pendientes de la Cola de Prioridad.");
        actualizarTabla();
    }

    private void actualizarTabla() {
        if (fintech != null) {
            listaProgramadas.setAll(fintech.getColaProgramadas());
            tablaProgramadas.setItems(listaProgramadas);
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert a = new Alert(tipo);
        a.setTitle(titulo);
        a.setContentText(msg);
        a.showAndWait();
    }
}