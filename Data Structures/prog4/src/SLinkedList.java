//George Polyak
//Prog4
//CSCD300

public class SLinkedList implements StringStack {

    private class Node{

        String element;
        Node next;
        Node(String element) {
            this.element = element;
        }

    }

    private int size;
    private Node head;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String peek() {

        if(isEmpty()) {

            try {
                throw new Exception("List is empty");
            } catch (Exception e) {
                e.getMessage();
            }

        }
        return head.element;

    }

    @Override
    public String pop() {

        if(isEmpty()) {

            try {
                throw new Exception("List is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        Node ret = head;
        head = head.next;
        size--;
        return ret.element;

    }

    @Override
    public void push(String element) {

        Node n = new Node(element);
        n.next = head;
        head = n;
        size++;

    }

    @Override
    public int size() {
        return size;
    }

}
