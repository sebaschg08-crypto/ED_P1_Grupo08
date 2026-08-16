package com.ed_p1_grupo_08.test_consola;

public class Tree<T>{
    private TreeNode<T> raiz;

    public Tree() {
        this.raiz = null;
    }

    public Tree(T datoRaiz) {
        this.raiz = new TreeNode<>(datoRaiz);
    }

    public TreeNode<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(TreeNode<T> raiz) {
        this.raiz = raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int contarNodos(TreeNode<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        int contador = 1;
        for (TreeNode<T> hijo : nodo.getHijos()) {
            contador += contarNodos(hijo);
        }
        return contador;
    }
}
