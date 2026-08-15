package com.ed_p1_grupo_08.tres_en_raya;

public class Tree <T>{
    private TreeNode<T> root;

    public Tree() {
        this.root = null;
    }

    public Tree(T rootData) {
        this.root = new TreeNode<>(rootData);
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int countNodes(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int count = 1; // Contamos este nodo
        for (TreeNode<T> hijo : node.getHijos()) {
            count += countNodes(hijo); // Llamada recursiva para los hijos
        }
        return count;
    }
}
