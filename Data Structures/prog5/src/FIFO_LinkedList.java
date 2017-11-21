//George Polyak
//Prog5
//CSCD300

public class FIFO_LinkedList implements IntegerFIFO_Queue {

    private class Node {

        Integer element;
        Node next;

        Node(int element) {
            this.element = element;
        }

    }

    private int size;
    private Node head;
    private Node tail;

    boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Integer getTop() {

        if (size == 0) {
            return null;
        }
        return head.element;
    }

    @Override
    public Integer getBottom() {

        if (size == 0) {
            return null;
        }
        return tail.element;

    }

    @Override
    public void enqueue(Integer item) {

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

    @Override
    public Integer dequeue() {

        if (!isEmpty()) {

            int ret = head.element;
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
