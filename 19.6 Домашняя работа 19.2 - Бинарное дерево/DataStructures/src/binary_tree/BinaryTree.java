package binary_tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private Node root;

    public void addNode(String data) {
        if (root == null) {
            root = new Node(data);
        } else {
            Node newNode = new Node(data);
            Node currentNode = root;
            while (true) {
                if (data.compareTo(currentNode.getData()) < 0) {
                    if (currentNode.getLeft() == null) {
                        currentNode.setLeft(newNode);
                        newNode.setParent(currentNode);
                        break;
                    } else {
                        currentNode = currentNode.getLeft();
                    }
                } else {
                    if (currentNode.getRight() == null) {
                        currentNode.setRight(newNode);
                        newNode.setParent(currentNode);
                        break;
                    } else {
                        currentNode = currentNode.getRight();
                    }
                }
            }
        }
    }

    public List<Node> searchNodes(String data) {
        List<Node> nodes = new ArrayList<>();
        Node currentNode = root;

        if (currentNode == null) {
            return nodes;
        }

        while (true) {
            switch (data.compareTo(currentNode.getData())) {
                case -1: {
                    if (currentNode.getLeft() != null) {
                        currentNode = currentNode.getLeft();
                    } else {
                        return nodes;
                    }
                }
                case 1: {
                    if (currentNode.getRight() != null) {
                        currentNode = currentNode.getRight();
                    } else {
                        return nodes;
                    }
                }
                case 0: {
                    nodes.add(currentNode);
                    if (currentNode.getRight() != null) {
                        currentNode = currentNode.getRight();
                    } else {
                        return nodes;
                    }
                }
            }
        }
    }
}