//George Polyak
//Prog8
//CSCD300

public class FIFO_LinkedList {

    private class Node {

        Object element;
        Node next;

        Node(Object element) {
            this.element = element;
        }

    }

    private int size;
    private Node head;
    private Node tail;

    boolean isEmpty() {
        return size == 0;
    }

    int size() {
        return size;
    }

    public Object getTop() {

        if (size == 0) {
            return null;
        }
        return head.element;
    }

    public Object getBottom() {

        if (size == 0) {
            return null;
        }
        return tail.element;

    }

    void enqueue(Object item) {

        Node n = new Node(item);
        if (size == 0) {

            tail = n;
            head = n;

        } else {

            tail.next = n;
            tail = tail.next;

        }
        size++;

    }

    Object dequeue() {

        if (!isEmpty()) {

            Object ret = head.element;
            if (size == 1) {

                head = null;
                tail = null;

            } else {
                head = head.next;
            }
            size--;
            return ret;

        }
        return null;

    }

}
