package main.java.fintech.vistas;

import main.java.fintech.modelo.Billetera;
import main.java.fintech.modelo.PlataformaFintech;
import main.java.fintech.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BilleterasController {

    @FXML private ComboBox<String> comboUsuarios;
    @FXML private TableView<Billetera> tablaBilleteras;
    @FXML private TableColumn<Billetera, String> colIdBilletera;
    @FXML private TableColumn<Billetera, String> colTipo;
    @FXML private TableColumn<Billetera, Double> colSaldo;
    @FXML private TableColumn<Billetera, String> colEstado;

    private PlataformaFintech fintech;
    private ObservableList<Billetera> listaBilleteras = FXCollections.observableArrayList();

    public void setFintech(PlataformaFintech fintech) {
        this.fintech = fintech;
        configurarTabla();
        cargarComboUsuarios();
    }

    private void configurarTabla() {
        colIdBilletera.setCellValueFactory(new PropertyValueFactory<>("idBilletera"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void cargarComboUsuarios() {
        if (fintech != null) {
            comboUsuarios.setItems(FXCollections.observableArrayList(fintech.getUsuarios().keySet()));
        }
    }

    @FXML
    private void handleSeleccionarUsuario() {
        String idUsuario = comboUsuarios.getValue();
        if (idUsuario != null && fintech.getUsuarios().containsKey(idUsuario)) {
            Usuario u = fintech.getUsuarios().get(idUsuario);
            listaBilleteras.setAll(u.getBilleteras().values());
            tablaBilleteras.setItems(listaBilleteras);
        }
    }

    @FXML
    private void handleAbrirModalCrearBilletera() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/modales/DialogNuevaBilletera.fxml"));
            Parent root = loader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Crear Billetera");
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setScene(new Scene(root));

            DialogNuevaBilleteraController controller = loader.getController();
            controller.setFintech(this.fintech, modalStage);

            modalStage.showAndWait();
            handleSeleccionarUsuario();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}