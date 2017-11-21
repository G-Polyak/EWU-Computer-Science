//George Polyak
//Prog8
//CSCD300

class BinarySearchTree {

    class BST_Node {

        int id;
        BST_Node parent;
        BST_Node left;
        BST_Node right;

        BST_Node(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "" + id;
        }

    }

    BST_Node root;
    int size;

    BinarySearchTree() {
        size = 0;
    }

    BST_Node search(int id) {

        BST_Node temp = root;
        while (temp != null && id != temp.id) {

            if (id < temp.id) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }

        }
        return temp;

    }

    BST_Node min(BST_Node subRoot) {

        BST_Node temp = subRoot;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;

    }

    BST_Node max(BST_Node subRoot) {

        BST_Node temp = subRoot;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;

    }

    BST_Node findSuccessor(BST_Node x) {

        if (x.right != null) {
            return min(x.right);
        }
        BST_Node y = x.parent;
        while (y != null && x == y.right) {

            x = y;
            y = y.parent;

        }
        return y;

    }

    BST_Node findPredecessor(BST_Node x) {

        if (x.left != null) {
            return max(x.left);
        }
        BST_Node y = x.parent;
        while (y != null && x == y.left) {

            x = y;
            y = y.parent;

        }
        return y;

    }

    void inOrderTraversal(BST_Node subRoot) {

        if (subRoot != null) {

            inOrderTraversal(subRoot.left);
            System.out.print(" " + subRoot.id);
            inOrderTraversal(subRoot.right);

        }

    }

    void preOrderTraversal(BST_Node subRoot) {

        if (subRoot != null) {

            System.out.print(" " + subRoot.id);
            preOrderTraversal(subRoot.left);
            preOrderTraversal(subRoot.right);

        }

    }

    void postOrderTraversal(BST_Node subRoot) {

        if (subRoot != null) {

            postOrderTraversal(subRoot.left);
            postOrderTraversal(subRoot.right);
            System.out.print(" " + subRoot.id);

        }

    }

    void levelOrderTraversal(BST_Node subRoot) {

        FIFO_LinkedList q = new FIFO_LinkedList();
        q.enqueue(subRoot);
        while (q.size() > 0) {

            BST_Node n = (BST_Node) q.dequeue();
            System.out.print(" " + n.id);
            if (n.left != null) {
                q.enqueue(n.left);
            }
            if (n.right != null) {
                q.enqueue(n.right);
            }

        }

    }

    BST_Node delete(int id) {

        BST_Node n = search(id);
        if (n != null) {
            delete(n);
        }
        return n;

    }

    private void delete(BST_Node n) {

        if (n.left == null && n.right == null) {
            transplant(n, null);
        } else if (n.left == null) {
            transplant(n, n.right);
        } else if (n.right == null) {
            transplant(n, n.left);
        } else {

            BST_Node min = min(n.right);
            if (min.parent != n) {

                transplant(min, min.right);
                min.right = n.right;
                min.right.parent = min;

            }
            transplant(n, min);
            min.left = n.left;
            min.left.parent = min;

        }

    }

    private void transplant(BST_Node oldTree, BST_Node newTree) {

        if (oldTree.parent == null) {
            root = newTree;
        } else if (oldTree == oldTree.parent.left) {
            oldTree.parent.left = newTree;
        } else {
            oldTree.parent.right = newTree;
        }
        if (newTree != null) {
            newTree.parent = oldTree.parent;
        }

    }

    BST_Node insert(int id) {

        BST_Node n = new BST_Node(id);
        BST_Node x = root;
        BST_Node y = null;
        while (x != null) {

            y = x;
            if (id == x.id) {
                return null;
            } else if (id < x.id) {
                x = x.left;
            } else {
                x = x.right;
            }

        }
        n.parent = y;
        if (y == null) {
            root = n;
        } else if (id < y.id) {
            y.left = n;
        } else {
            y.right = n;
        }
        size++;
        return n;

    }

}
