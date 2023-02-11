package binary_tree;

public class Node {
    private final String data;

    private Node parent;
    private Node left;
    private Node right;

    public Node(String data) {
        this.data = data;
    }

    Node getParent() {
        return parent;
    }

    void setParent(Node parent) {
        this.parent = parent;
    }

    Node getLeft() {
        return left;
    }

    void setLeft(Node left) {
        this.left = left;
    }

    Node getRight() {
        return right;
    }

    void setRight(Node right) {
        this.right = right;
    }

    String getData() {
        return data;
    }
}