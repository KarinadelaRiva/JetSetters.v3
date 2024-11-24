package org.jetsettersv2.collections;

import org.jetsettersv2.exceptions.ElementoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ArrayListGeneric<T> extends ArrayList<T> {
    private final List<T> lista;

    public ArrayListGeneric() {
        this.lista = new ArrayList<>();
    }

    public ArrayListGeneric(int dim) {
        this.lista = new ArrayList<>(dim);
    }

    public List<T> getLista() {
        return lista;
    }

    public void agregarElemento(T elemento) {
        this.lista.add(elemento);
    }

    public void copiarLista(List<T> origen){
        for (T elemento : origen){
            this.agregarElemento(elemento);
        }
    }

    public void mostrarLista(){
        for (T elemento : this.lista) {
            System.out.println(elemento);
        }
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

    public T buscarElemento(Predicate<T> criterio, String mensajeException) throws ElementoNoEncontradoException {

        for (T elemento : this.lista) {
            if (criterio.test(elemento)) {
                return elemento;
            }
        }
        throw new ElementoNoEncontradoException(mensajeException);
    }

    public List<T> filtrarListaElementos(Predicate<T> criterio, String mensajeException) {
        List<T> resultado = new ArrayList<>();

        for (T elemento : lista) {
            if (criterio.test(elemento)) {
                resultado.add(elemento);
            }
        }
        // Si no hay coincidencias, lanzamos la excepción
        if (resultado.isEmpty()) {
            throw new ElementoNoEncontradoException(mensajeException);
        }
        return resultado;
    }
}
