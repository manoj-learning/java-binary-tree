import javax.sound.midi.SysexMessage;

public class BinaryTree {

    Node root;

    public void addNode(int key, String name) {
        Node newNode = new Node(key, name);

        if (root == null) {
            root = newNode;
        } else {
            Node focusNode = root;

            Node parent;

            while (true) {

                parent = focusNode;

                if (key < focusNode.key) {
                    focusNode = focusNode.leftChild;

                    if (focusNode == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    focusNode = focusNode.rightChild;
                    if (focusNode == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }

    }

    public void inOrderTraverse(Node focusNode) {
        if (focusNode != null) {
            inOrderTraverse(focusNode.leftChild);
            System.out.println(focusNode);
            inOrderTraverse(focusNode.rightChild);
        }
    }

    public void preOrderTraverse(Node focusNode) {
        if (focusNode != null) {
            System.out.println(focusNode);
            preOrderTraverse(focusNode.leftChild);
            preOrderTraverse(focusNode.rightChild);
        }
    }

    public void postOrderTraverse(Node focusNode) {
        if (focusNode != null) {

            postOrderTraverse(focusNode.leftChild);
            postOrderTraverse(focusNode.rightChild);
            System.out.println(focusNode);
        }
    }

    public Node findNode(int key) {
        Node focusNode = root;

        while (focusNode.key != key) {
            if (key < focusNode.key) {
                focusNode = focusNode.leftChild;
            } else {
                focusNode = focusNode.rightChild;
            }

            if (focusNode == null) {
                return null;
            }
        }

        return focusNode;
    }

    public boolean remove(int key) {
        Node focusNode = root;

        Node parent = root;

        boolean isItALeftChild = true;

        while (focusNode.key != key) {
            parent = focusNode;
            if (key < focusNode.key) {
                isItALeftChild = true;

                focusNode = focusNode.leftChild;

            } else {
                isItALeftChild = false;
                focusNode = focusNode.rightChild;
            }

            if (focusNode == null) {
                return false;
            }

            if (focusNode.leftChild == null && focusNode.rightChild == null) {
                if (focusNode == root) {
                    root = null;
                } else if (isItALeftChild) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            } else if (focusNode.rightChild == null) {
                if (focusNode == root) {
                    root = focusNode.leftChild;
                } else if (isItALeftChild) {
                    parent.leftChild = focusNode.leftChild;
                } else {
                    parent.rightChild = focusNode.leftChild;
                }
            } else if (focusNode.leftChild == null) {
                if (focusNode == root) {
                    root = focusNode.rightChild;
                } else if (isItALeftChild) {
                    parent.leftChild = focusNode.rightChild;
                } else {
                    parent.rightChild = focusNode.leftChild;
                }
            } else {
                Node replacement = getNodeReplacement(focusNode);

                if (focusNode == root) {
                    root = replacement;
                } else if (isItALeftChild) {
                    parent.leftChild = replacement;
                } else {
                    parent.rightChild = replacement;
                }
                replacement.leftChild = focusNode.leftChild;
            }

        }
        return true;
    }

    public Node getNodeReplacement(Node replacedNode) {

        Node replacementParent = replacedNode;

        Node replacement = replacedNode;

        Node focusNode = replacedNode.rightChild;

        while (focusNode != null) {
            replacementParent = replacement;

            replacement = focusNode;

            focusNode = focusNode.leftChild;
        }

        if (replacement != replacedNode.rightChild) {
            replacementParent.leftChild = replacement.rightChild;

            replacement.rightChild = replacedNode.rightChild;
        }
        return replacement;
    }

    public static void main(String[] args) {

        BinaryTree theTree = new BinaryTree();
        theTree.addNode(50, "Sir");
        theTree.addNode(25, "MR");
        theTree.addNode(15, "Yohani");
        theTree.addNode(30, "Namal Anna");
        theTree.addNode(75, "Savendraaa");
        theTree.addNode(85, "Podi Sir");

        System.out.println();

        System.out.println("===== IN ORDER TRAVERSAL =====");
        theTree.inOrderTraverse(theTree.root);

        System.out.println();

        System.out.println("===== PRE ORDER TRAVERSAL =====");
        theTree.preOrderTraverse(theTree.root);
        System.out.println();

        System.out.println("===== POST ORDER TRAVERSAL =====");
        theTree.postOrderTraverse(theTree.root);
        System.out.println();

        System.out.println("Search for 30");
        System.out.println(theTree.findNode(30));

        System.out.println("Remove 25");
        theTree.remove(25);

        System.out.println("===== PRE ORDER TRAVERSAL =====");
        theTree.preOrderTraverse(theTree.root);
        System.out.println();

    }

    class Node {
        int key;
        String name;

        Node leftChild;
        Node rightChild;

        Node(int key, String name) {
            this.key = key;
            this.name = name;
        }

        public String toString() {
            return name + " has a key " + key;
        }
    }
}