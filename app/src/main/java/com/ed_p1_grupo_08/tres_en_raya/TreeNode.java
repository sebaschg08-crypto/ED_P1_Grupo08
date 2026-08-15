package com.ed_p1_grupo_08.tres_en_raya;
import java.util.ArrayList;
import java.util.List;
public class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> hijos;
    private int utility;

    public TreeNode(T data) {
        this.data = data;
        this.hijos = new ArrayList<>();
        this.utility = 0;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public List<TreeNode<T>> getHijos() {
        return hijos;
    }
    public void addhijo(TreeNode<T> hijo) {
        this.hijos.add(hijo);
    }
    public int getUtility() {
        return utility;
    }
    public void setUtility(int utility) {
        this.utility = utility;
    }

    public boolean isLeaf() {
        return hijos.isEmpty();
    }
}
