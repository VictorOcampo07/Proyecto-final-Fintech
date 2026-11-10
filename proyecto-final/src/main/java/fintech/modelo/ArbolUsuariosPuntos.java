package fintech.modelo;

import java.util.ArrayList;
import java.util.List;

public class ArbolUsuariosPuntos {
    private NodoBST raiz;

    public void insertar(Usuario usuario) {
        raiz = insertarRec(raiz, usuario);
    }

    private NodoBST insertarRec(NodoBST nodo, Usuario usuario) {
        if (nodo == null) return new NodoBST(usuario);
        if (usuario.getPuntos() < nodo.usuario.getPuntos()) {
            nodo.izq = insertarRec(nodo.izq, usuario);
        } else {
            nodo.der = insertarRec(nodo.der, usuario);
        }
        return nodo;
    }

    public List<Usuario> obtenerEnOrden() {
        List<Usuario> lista = new ArrayList<>();
        inorder(raiz, lista);
        return lista;
    }

    private void inorder(NodoBST nodo, List<Usuario> lista) {
        if (nodo != null) {
            inorder(nodo.izq, lista);
            lista.add(nodo.usuario);
            inorder(nodo.der, lista);
        }
    }
}