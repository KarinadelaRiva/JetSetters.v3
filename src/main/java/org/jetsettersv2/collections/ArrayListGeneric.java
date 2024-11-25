package org.jetsettersv2.collections;

import org.jetsettersv2.exceptions.ElementoNoEncontradoException;

import java.util.ArrayList;

public class ArrayListGeneric<T> extends ArrayList<T> {
    private final ArrayList<T> lista;

    public ArrayListGeneric() {
        this.lista = new ArrayList<>();
    }

    public ArrayListGeneric(int dim) {
        this.lista = new ArrayList<>(dim);
    }

    public ArrayList<T> getLista() {
        return lista;
    }

    public void agregarElemento(T elemento) {
        this.lista.add(elemento);
    }

    public void copiarLista(ArrayList<T> origen){
        this.clear(); // Limpia la lista actual antes de copiar
        this.addAll(origen);
    }

    public void eliminarElemento(T elemento, String mensajeException) throws ElementoNoEncontradoException {

        for (int i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).equals(elemento)) {
                this.lista.remove(elemento);
                return;
            }
        }
        throw new ElementoNoEncontradoException(mensajeException);
    }
}
