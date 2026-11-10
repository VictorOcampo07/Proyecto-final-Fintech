package fintech.modelo;

import java.time.LocalDateTime;

public class OperacionProgramada implements Comparable<OperacionProgramada>{

    private String origenId;
    private String destinoId;
    private double monto;
    private LocalDateTime fechaEjecucion;

    public OperacionProgramada(String origenId, String destinoId, double monto, LocalDateTime fechaEjecucion) {
        this.origenId = origenId;
        this.destinoId = destinoId;
        this.monto = monto;
        this.fechaEjecucion = fechaEjecucion;
    }

    @Override
    public int compareTo(OperacionProgramada o) {
        return this.fechaEjecucion.compareTo(o.fechaEjecucion);
    }

    public String getOrigenId() { return origenId; }
    public String getDestinoId() { return destinoId; }
    public double getMonto() { return monto; }
    public LocalDateTime getFechaEjecucion() { return fechaEjecucion; }
    
}
