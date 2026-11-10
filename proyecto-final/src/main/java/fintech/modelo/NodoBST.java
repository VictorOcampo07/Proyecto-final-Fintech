package fintech.modelo;

class NodoBST {
    Usuario usuario;
    NodoBST izq, der;

    public NodoBST(Usuario usuario) {
        this.usuario = usuario;
        this.izq = this.der = null;
    }
}