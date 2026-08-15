package com.ed_p1_grupo_08.tres_en_raya;
import java.util.ArrayList;
import java.util.List;
public class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> children;
    private int utility;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
        this.utility = 0;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public List<TreeNode<T>> getChildren() {
        return children;
    }
    public void addChild(TreeNode<T> child) {
        this.children.add(child);
    }
    public int getUtility() {
        return utility;
    }
    public void setUtility(int utility) {
        this.utility = utility;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
}
