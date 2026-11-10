package fintech.vistas;

import fintech.modelo.PlataformaFintech;
import fintech.modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class NotificacionesController {

    @FXML private ComboBox<String> comboUsuarios;
    @FXML private ListView<String> listNotificaciones;

    private PlataformaFintech fintech;
    private ObservableList<String> listaAlertas = FXCollections.observableArrayList();

    public void setFintech(PlataformaFintech fintech) {
        this.fintech = fintech;
        cargarComboUsuarios();
    }

    private void cargarComboUsuarios() {
        if (fintech != null) {
            comboUsuarios.setItems(FXCollections.observableArrayList(fintech.getUsuarios().keySet()));
            if (!fintech.getUsuarios().isEmpty()) {
                comboUsuarios.getSelectionModel().selectFirst();
                handleSeleccionarUsuario();
            }
        }
    }

    @FXML
    private void handleSeleccionarUsuario() {
        String idUsuario = comboUsuarios.getValue();
        if (idUsuario != null && fintech.getUsuarios().containsKey(idUsuario)) {
            Usuario u = fintech.getUsuarios().get(idUsuario);
            
            // Si la lista de notificaciones del usuario está vacía, mostramos un mensaje por defecto
            if (u.getNotificaciones().isEmpty()) {
                listaAlertas.setAll("Sin notificaciones pendientes para este usuario.");
            } else {
                listaAlertas.setAll(u.getNotificaciones());
            }
            listNotificaciones.setItems(listaAlertas);
        }
    }
}