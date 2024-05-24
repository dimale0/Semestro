import java.util.ArrayList;
import java.util.List;

public class FibonacciHeap {

    private Node min;

    private int n;

    public boolean isEmpty() { return min == null; }

    public Node getMin() { return min; }

    public int getSize() { return n; }

    public void clean() {
        for (int i = 0; i < n; i++) {
            removeMin();
        }
    }

    public static FibonacciHeap merge(FibonacciHeap H1, FibonacciHeap H2) {
        FibonacciHeap H = new FibonacciHeap();
        H.min = null;
        H.n = 0;
        if(H1 == null && H2 == null) return H;
        if(H1 != null) {
            H.min = H1.min;
            H.n = H1.n;
            if(H2!=null) {
                Node R1 = H1.min;
                Node R2 = H1.min.getRight();

                Node L1 = H2.min;
                Node L2 = H2.min.getRight();

                R1.setLeft(L1);
                L1.setLeft(R1);

                R2.setRight(L2);
                L2.setRight(R2);

                if(H1.min.getKey() > H2.min.getKey()) {
                    H.min = H2.min;
                }
            }
        } else {
            H.min = H2.min;
            H.n = H2.n;
        }
        return H;
    }

    public Object removeMin() {
        Node z = min;
        if(z==null) return null;

        Node c = z.getChild();
        if(c!=null) {
            c.setParent(null);
            for(Node x = c.getRight(); x!=c; x = x.getRight()) {
                x.setParent(null);
            }

            Node R1 = c;
            Node R2 = c.getRight();

            Node L1 = min;
            Node L2 = min.getRight();

            R1.setLeft(L1);;
            L1.setLeft(R1);

            R2.setRight(L2);
            L2.setRight(R2);
        }

        z.getLeft().setRight(z.getRight());
        z.getRight().setLeft(z.getLeft());

        if(z == z.getRight()) {
            min = null;
        } else {
            min = z.getRight();
            consolidate();
        }
        n--;
        return z.getData();
    }

    public Node insert(Object data, Integer key) {
        Node node = new Node(data, key);
        if(min==null) {
            min = node;
        } else {
            node.setRight(min.getRight());
            node.setLeft(min);
            min.getRight().setLeft(node);
            min.setRight(node);
            if(key < min.getKey()) {
                min = node;
            }
        }
        n++;
        return node;
    }


    private void decreaseKey(Node x, Integer key, boolean del) {
        if(key > x.getKey()) throw new IllegalArgumentException("Нельзя увеличить ключ");

        Node y = x.getParent();
        if(y!=null && (del || key < y.getKey())) {
            y.cut(x, min);
            y.cascadeCut(min);
        }
        if(del || key < min.getKey()) {
            min = x;
        }
    }

    public void decreaseKey(Node x, Integer key) {
        decreaseKey(x, key, false);
    }

    public void delete(Node x) {
        decreaseKey(x, Integer.MIN_VALUE, true);
        removeMin();
    }


    public void consolidate() {
        Node[] A = new Node[45];
        Node start = min;
        Node w = min;

        do {
            Node x = w;
            Node nextW = w.getRight();
            int d = x.getDegree();
            while(A[d]!=null) {
                Node y = A[d];
                if(x.getKey() > y.getKey()) {
                    Node tmp = x;
                    x = y;
                    y = tmp;
                }
                if(y==start) start = start.getRight();
                if(y==nextW) nextW = nextW.getRight();

                y.link(x);

                A[d] = null;
                d++;
            }
            A[d] = x;
            w = nextW;
        } while(w!=start);

        min = start;

        for(Node a : A) {
            if(a!=null && a.getKey() < min.getKey()) {
                min = a;
            }
        }
    }
}