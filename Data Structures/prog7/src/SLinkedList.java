//George Polyak
//Prog7
//CSCD300

public class SLinkedList {

    private class Node {

        int id;
        String value;
        Node next;

        Node(int id, String value) {

            this.id = id;
            this.value = value;
            next = null;

        }

    }

    private int size;
    private Node head;

    SLinkedList() {

        head = null;
        size = 0;

    }

    public int size() {
        return size;
    }

    void add(int id, String value) {

        Node n = new Node(id, value);
        if (head == null) {
            head = n;
        } else {

            n.next = head;
            head = n;

        }
        size++;

    }

    void update(int id, String value) {
        helper(id).value = value;
    }

    private Node helper(int id) {

        Node curr = head;
        while (curr != null) {

            if (curr.id == id) {
                return curr;
            }
            curr = curr.next;

        }
        return null;

    }

    String search(int id) {

        if (size > 0) {

            Node n = helper(id);
            if (n != null) {
                return n.value;
            }

        }
        return null;

    }

    String remove(int id) {

        if (size > 0) {

            String s = null;
            Node curr = head;
            if(curr.id == id) {

                s = curr.value;
                head = head.next;

            } else {

                while (curr.next != null) {

                    if (curr.next.id == id) {

                        s = curr.next.value;
                        curr.next = curr.next.next;
                        size--;
                        return s;

                    }
                    curr = curr.next;

                }

            }
            return s;

        }
        return null;

    }

    @Override
    public String toString() {

        String s = "";
        Node curr = head;
        while (curr != null) {

            s += "(" + curr.id + "," + curr.value + ") ";
            curr = curr.next;

        }
        return s;

    }

}
