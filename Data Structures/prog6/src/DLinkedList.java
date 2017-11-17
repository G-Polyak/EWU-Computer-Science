//George Polyak
//Prog6
//CSCD300

public class DLinkedList {

    static class Node {

        Node prev;
        Node next;
        int data;

        Node(int data) {
            this.data = data;
        }

    }

    private Node head;
    private Node tail;
    private int size;

    DLinkedList() {

        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
        size = 0;

    }

    public int size(){
        return size;
    }

    void add(int data) {

        Node n = new Node(data);
        n.prev = tail.prev;
        n.next = tail;
        tail.prev.next = n;
        tail.prev = n;
        size++;

    }

    void sort() {

        if (size > 1) {
            quickListSort(head.next, tail.prev);
        }

    }

    private void quickListSort(Node low, Node high) {

        if (low != high && low != high.next && high != tail) {

            Node pivotIndex = partition(low, high);
            quickListSort(low, pivotIndex.prev);
            quickListSort(pivotIndex.next, high);

        }

    }

    private Node partition(Node low, Node high) {

        int pivot = high.data;
        Node index = low.prev;
        for(Node i = low; i != high; i = i.next) {

            if(i.data <= pivot) {

                index = (index == null) ? low : index.next;
                int temp = index.data;
                index.data = i.data;
                i.data = temp;

            }

        }
        index = (index == null) ? low : index.next;
        int temp = index.data;
        index.data = high.data;
        high.data = temp;
        return index;

    }

    public String toString() {

        Node curr = head.next;
        String s = "";
        while(curr != tail) {

            s += curr.data + "\n";
            curr = curr.next;

        }
        return s;

    }

}
