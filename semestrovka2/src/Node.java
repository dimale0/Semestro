import java.util.List;

public class Node {

    private Object data;
    private Integer key;

    private Node parent, child, left, right;

    private int degree;

    private boolean mark;

    public Node(Object data, Integer key) {
        this.data = data;
        this.key = key;
        left = this;
        right = this;
    }

    public void link(Node parent) {
        left.right = right;
        right.left = left;

        this.parent = parent;

        if(parent.child == null) {
            parent.child = this;
            right = this;
            left = this;
        } else {
            left = parent.child;
            right = parent.child.right;
            parent.child.right.left = this;
            parent.child.right = this;
        }

        parent.degree++;
        mark = false;
    }

    public void cut(Node removeChild, Node min) {
        removeChild.left.right = removeChild.right;
        removeChild.right.left = removeChild.left;

        degree--;

        if(degree == 0) {
            child = null;
        } else {
            if(removeChild == child) child = removeChild.right;
        }

        removeChild.left = min.left;
        removeChild.right = min;
        min.left.right = removeChild;
        min.left = removeChild;

        removeChild.parent = null;
        removeChild.mark = false;
    }

    public void cascadeCut(Node min) {
        Node z = parent;
        while (z!=null) {
            if(mark) {
                z.cut(this, min);
                z.cascadeCut(min);
            } else {
                mark = true;
            }
        }
    }

    public Integer getKey() { return key; }

    public Object getData() { return data; }

    public Node getLeft() { return left; }

    public Node getRight() { return right; }

    public void setLeft(Node l) { left = l; }

    public void setRight(Node r) { right = r; }

    public Node getChild() { return child; }

    public void setParent(Node p) { parent = p; }

    public Node getParent() { return parent; }

    public void setKey(Integer k) { key = k; }

    public int getDegree() { return degree; }
}