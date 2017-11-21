public class LinkedList {

    private class Node {

        public Integer data;
        public Node next;

        public Node(Integer data) {
            this.data = data;
        }

    }

    private Node head;
    private int size;

    public int getSize() {
        return size;
    }

    public void add(Integer n) {

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

    public void add(Integer n, int index) {

        if (index >= 0 && index < size) { //I think it's weird to specify an index that
            //doesn't exist yet, so that's why this doesn't
            Node newNode = new Node(n);   //say index >= 0 && index <= size
            if (index == 0) {
                newNode.next = head;
                head = newNode;
            } else {
                Node prev = find(index - 1);
                newNode.next = prev.next;
                prev.next = newNode;
            }
            size++;

        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }

    }

    public void remove(int index) {

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

        int num = 0;
        while (find(n) != -1) {
            remove(find(n));
            num++;
        }
        System.out.println("Removed " + num + " items from the list");

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

    public void set(Integer n, int index) {

        if (index >= 0 && index < size) {

            Node curr = find(index);
            curr.data = n;

        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }

    }

    public void sort() {

        int current;
        int smallestIndex;
        int posToFill;
        Integer temp;

        for (posToFill = 0; posToFill < size - 1; posToFill++) {

            smallestIndex = posToFill;
            for (current = posToFill + 1; current < size; current++) {

                if (find(current).data.compareTo(find(smallestIndex).data) < 0) {
                    smallestIndex = current;
                }

            }
            temp = find(posToFill).data;
            find(posToFill).data = find(smallestIndex).data;
            find(smallestIndex).data = temp;

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
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + curr.data + "\n";
            curr = curr.next;
        }
        return s;

    }

    public static void printReverse(LinkedList list) {

        for (int i = list.size - 1; i > -1; i--) {
            System.out.print(list.get(i) + "\n");
        }

    }

    public static void printNthNodes(LinkedList list, int n) {

        for (int i = 0; i < list.size; i += n) {
            System.out.print(list.get(i) + " ");
        }

    }

}
