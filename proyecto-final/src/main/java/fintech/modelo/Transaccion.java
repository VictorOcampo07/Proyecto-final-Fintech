package fintech.modelo;

import java.time.LocalDateTime;

public class Transaccion {
    private String idTx;
    private LocalDateTime fecha;
    private String tipo; // RECARGA, RETIRO, TRANSFERENCIA
    private double monto;
    private String origenId;
    private String destinoId;
    private String estado;
    private int puntosGenerados;
    private String riesgo;

    public Transaccion(String idTx, String tipo, double monto, String origenId, String destinoId) {
        this.idTx = idTx;
        this.fecha = LocalDateTime.now();
        this.tipo = tipo;
        this.monto = monto;
        this.origenId = origenId;
        this.destinoId = destinoId;
        this.estado = "COMPLETADA";
        this.riesgo = "NORMAL";
        this.puntosGenerados = calcularPuntos();
    }

    public int calcularPuntos() {
        if ("RECARGA".equals(tipo)) return (int) ((monto / 100) * 1);
        if ("RETIRO".equals(tipo)) return (int) ((monto / 100) * 2);
        if ("TRANSFERENCIA".equals(tipo)) return (int) ((monto / 100) * 3);
        return 0;
    }

    public String getIdTx() { return idTx; }
    public String getTipo() { return tipo; }
    public double getMonto() { return monto; }
    public String getOrigenId() { return origenId; }
    public String getDestinoId() { return destinoId; }
    public int getPuntosGenerados() { return puntosGenerados; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setRiesgo(String riesgo) { this.riesgo = riesgo; }
}