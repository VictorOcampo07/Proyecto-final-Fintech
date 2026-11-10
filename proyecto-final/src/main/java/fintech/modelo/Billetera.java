package fintech.modelo;

public class Billetera {
    private String idBilletera;
    private String idUsuario;
    private String tipo; // Ahorro, Gastos diarios, Compras, Transporte, Inversion
    private double saldo;
    private String estado;

    public Billetera(String idBilletera, String idUsuario, String tipo, double saldoInicial) {
        this.idBilletera = idBilletera;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.saldo = saldoInicial;
        this.estado = "ACTIVA";
    }

    public void recargar(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("El monto a recargar debe ser mayor a 0.");
        this.saldo += monto;
    }

    public void retirar(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("El monto a retirar debe ser mayor a 0.");
        if (this.saldo < monto) throw new IllegalArgumentException("Saldo insuficiente en la billetera.");
        this.saldo -= monto;
    }

    public String getIdBilletera() { return idBilletera; }
    public String getIdUsuario() { return idUsuario; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}
