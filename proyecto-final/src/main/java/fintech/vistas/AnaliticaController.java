package fintech.vistas;

import fintech.modelo.ArbolUsuariosPuntos;
import fintech.modelo.PlataformaFintech;
import fintech.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

public class AnaliticaController {

    @FXML private TextArea areaAnalitica;

    private PlataformaFintech fintech;

    public void setFintech(PlataformaFintech fintech) {
        this.fintech = fintech;
    }

    @FXML
    private void handleGenerarReporte() {
        if (fintech == null) return;

        StringBuilder sb = new StringBuilder();

        // 1. ANÁLISIS DE GRAFOS (Detección de Ciclos / Rutas Frecuentes)
        sb.append("====================================================\n");
        sb.append("   ANÁLISIS DE GRAFOS & CICLOS DE TRANSFERENCIAS   \n");
        sb.append("====================================================\n");
        List<List<String>> ciclos = fintech.getGrafo().detectarCiclos();
        if (ciclos.isEmpty()) {
            sb.append("✓ No se detectaron patrones de transferencias cíclicas sospechosas.\n");
        } else {
            sb.append("⚠ ALERTA DE CICLOS SOSPECHOSOS DETECTADOS:\n");
            for (List<String> ciclo : ciclos) {
                sb.append("   -> Ruta de ciclo: ").append(String.join(" -> ", ciclo)).append("\n");
            }
        }
        sb.append("\n");

        // 2. AUDITORÍA DE COMPORTAMIENTO FINANCIERO INUSUAL
        sb.append("====================================================\n");
        sb.append("    AUDITORÍA DE SEGURIDAD & EVENTOS INUSUALES    \n");
        sb.append("====================================================\n");
        List<String> auditoria = fintech.getHistorialAuditoria();
        if (auditoria.isEmpty()) {
            sb.append("✓ No hay eventos de alto riesgo registrados hasta el momento.\n");
        } else {
            for (String evento : auditoria) {
                sb.append(" [!] ").append(evento).append("\n");
            }
        }
        sb.append("\n");

        // 3. CLASIFICACIÓN DE USUARIOS POR PUNTOS (BST - In-Order Traversal)
        sb.append("====================================================\n");
        sb.append(" CLASIFICACIÓN EN ÁRBOL BST (RECORRIDO EN-ORDEN) \n");
        sb.append("====================================================\n");
        ArbolUsuariosPuntos bst = new ArbolUsuariosPuntos();
        for (Usuario u : fintech.getUsuarios().values()) {
            bst.insertar(u);
        }

        List<Usuario> usuariosOrdenados = bst.obtenerEnOrden();
        if (usuariosOrdenados.isEmpty()) {
            sb.append("No hay usuarios registrados en el sistema.\n");
        } else {
            for (Usuario u : usuariosOrdenados) {
                sb.append(String.format("ID: %-6s | Nombre: %-18s | Puntos: %-5d | Nivel: %s\n",
                        u.getIdUsuario(), u.getNombre(), u.getPuntos(), u.getNivel()));
            }
        }

        areaAnalitica.setText(sb.toString());
    }
}