package com.ed_p1_grupo_08.tres_en_raya;
import java.util.ArrayList;
import java.util.List;
public class TreeNode<T> {
    private T dato;
    private List<TreeNode<T>> hijos;
    private int utilidad;

    public TreeNode(T dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
        this.utilidad = 0;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public List<TreeNode<T>> getHijos() {
        return hijos;
    }

    public void addHijo(TreeNode<T> hijo) {
        this.hijos.add(hijo);
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }

    public boolean esHoja() {
        return hijos.isEmpty();
    }
}
