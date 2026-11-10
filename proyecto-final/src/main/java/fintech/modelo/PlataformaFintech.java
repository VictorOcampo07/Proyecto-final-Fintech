package fintech.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PlataformaFintech {
    private Map<String, Usuario> usuarios = new HashMap<>(); // Tabla Hash
    private Map<String, Billetera> billeteras = new HashMap<>(); // Tabla Hash
    private Stack<Transaccion> pilaReversion = new Stack<>(); // Pila LIFO
    private GrafoTransferencias grafo = new GrafoTransferencias(); // Grafo
    private List<String> historialAuditoria = new ArrayList<>();
    private int contadorTx  = 1;


    public void registrarUsuario(String id, String nombre, String email) {
        if (usuarios.containsKey(id)) throw new IllegalArgumentException("El usuario ya existe.");
        usuarios.put(id, new Usuario(id, nombre, email));
    }

    public void crearBilletera(String idUsuario, String idBilletera, String tipo, double saldoInicial) {
        if (!usuarios.containsKey(idUsuario)) throw new IllegalArgumentException("Usuario no encontrado.");
        if (billeteras.containsKey(idBilletera)) throw new IllegalArgumentException("Billetera ya existe.");

        Billetera b = new Billetera(idBilletera, idUsuario, tipo, saldoInicial);
        billeteras.put(idBilletera, b);
        usuarios.get(idUsuario).getBilleteras().put(idBilletera, b);
    }



    public Map<String, Usuario> getUsuarios() { return usuarios; }
    public GrafoTransferencias getGrafo() { return grafo; }
    public List<String> getHistorialAuditoria() { return historialAuditoria; }



}




