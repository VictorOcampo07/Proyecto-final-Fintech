package fintech.vistas;

import fintech.modelo.PlataformaFintech;
import fintech.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuariosController {

    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, String> colId;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, Integer> colPuntos;
    @FXML private TableColumn<Usuario, String> colNivel;

    private PlataformaFintech fintech;
    private ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

    public void setFintech(PlataformaFintech fintech) {
        this.fintech = fintech;
        configurarTabla();
        actualizarTabla();
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPuntos.setCellValueFactory(new PropertyValueFactory<>("puntos"));
        colNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
    }

    public void actualizarTabla() {
        if (fintech != null) {
            listaUsuarios.setAll(fintech.getUsuarios().values());
            tablaUsuarios.setItems(listaUsuarios);
        }
    }

    @FXML
    private void handleAbrirModalCreacion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/modales/DialogNuevoUsuario.fxml"));
            Parent root = loader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Registrar Nuevo Usuario");
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setScene(new Scene(root));

            DialogNuevoUsuarioController controller = loader.getController();
            controller.setFintech(this.fintech, modalStage);

            modalStage.showAndWait();
            actualizarTabla();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}