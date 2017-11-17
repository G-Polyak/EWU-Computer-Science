//George Polyak
//Prog4
//CSCD320

public class LinkedList {

    private Node head;
    private int size;

    int getSize() {
        return size;
    }

    void add(Integer n) {

        Node newNode = new Node(n);
        if (size == 0) {
            head = newNode;
        } else {

            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;

        }
        size++;

    }

    void add(Node n) {

        if (size == 0) {
            head = n;
        } else {

            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = n;

        }
        size++;

    }

    void addFirst(Integer n) {

        Node newNode = new Node(n);
        if (size == 0) {
            head = newNode;
        } else {

            newNode.next = head;
            head = newNode;

        }
        size++;

    }

    private void remove(int index) {

        if (index >= 0 && index < size) {

            if (index == 0) {
                head = head.next;
            } else {
                Node prev = find(index - 1);
                prev.next = prev.next.next;
            }
            size--;

        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }

    }

    public void remove(Integer n) {

        while (find(n) != -1) {
            remove(find(n));
        }

    }

    public void clear() {

        head = null;
        size = 0;

    }

    public Integer get(int index) {

        Integer n;
        if (index >= 0 && index < size) {

            Node curr = find(index);
            n = curr.data;

        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return n;

    }

    Node getNode(int index) {

        Node curr;
        if (index >= 0 && index < size) {
            curr = find(index);
        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return curr;

    }

    public void set(Integer n, int index) {

        if (index >= 0 && index < size) {

            Node curr = find(index);
            curr.data = n;

        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }

    }

    private Node find(int index) {

        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;

    }

    private int find(Integer n) {

        Node curr = head;
        for (int i = 0; i < size; i++) {

            if (curr.data.equals(n)) {
                return i;
            }
            curr = curr.next;

        }
        return -1;

    }

    public String toString() {

        Node curr = head;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(curr.data).append(" ");
            curr = curr.next;
        }
        return s.toString();

    }

}
