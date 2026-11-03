package main.java.fintech.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Usuario {
    private String idUsuario;
    private String nombre;
    private String email;
    private int puntos;
    private String nivel;
    private Map<String, Billetera> billeteras; // Tabla Hash
    private List<Transaccion> historialTransacciones; // Lista enlazada
    private List<String> notificaciones;

    public Usuario(String idUsuario, String nombre, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.puntos = 0;
        this.nivel = "Bronce"; // Nivel inicial
        this.billeteras = new HashMap<>();
        this.historialTransacciones = new LinkedList<>();
        this.notificaciones = new ArrayList<>();
    }

    public void actualizarPuntos(int cambioPuntos) {
        this.puntos += cambioPuntos;
        if (this.puntos < 0) this.puntos = 0;
        actualizarNivel();
    }

    private void actualizarNivel() {
        String nivelAnterior = this.nivel;
        if (this.puntos <= 500) this.nivel = "Bronce";
        else if (this.puntos <= 1000) this.nivel = "Plata";
        else if (this.puntos <= 5000) this.nivel = "Oro";
        else this.nivel = "Platino";

        if (!nivelAnterior.equals(this.nivel)) {
            this.notificaciones.add("¡Felicidades! Has subido al nivel " + this.nivel);
        }
    }

    public String getIdUsuario() { return idUsuario; }
    public String getNombre() { return nombre; }
    public int getPuntos() { return puntos; }
    public String getNivel() { return nivel; }
    public Map<String, Billetera> getBilleteras() { return billeteras; }
    public List<Transaccion> getHistorialTransacciones() { return historialTransacciones; }
}