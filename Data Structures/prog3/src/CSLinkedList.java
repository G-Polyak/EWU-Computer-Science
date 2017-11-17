//George Polyak
//Prog3
//CSCD300

public class CSLinkedList {

    static class Node {

        int id;
        int serviceTime;
        Node next;
        Node(int id, int serviceTime) {
            this.id = id;
            this.serviceTime = serviceTime;
        }

    }

    public Node cursor;
    private int size;

    public int getSize() {
        return size;
    }

    public void addByID(Node n) {

        if(size == 0) {

            cursor = n;
            cursor.next = cursor;
            size++;

        } else if(size == 1) {

            n.next = cursor.next;
            cursor.next = n;
            size++;

        } else {

            goToStart();
            Node temp = cursor;
            while(cursor.next != temp) {

                if(cursor.next.id > n.id && n.id > cursor.id) {

                    n.next = cursor.next;
                    cursor.next = n;
                    size++;
                    break;

                }
                advance();

            }
            if (cursor.next == temp) {

                n.next = temp;
                cursor.next = n;
                size++;

            }

        }

    }

    public Node removeAtCursor() {

        Node temp = cursor;
        Node ret = cursor;
        while(temp.next != cursor) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        advance();
        size--;
        return ret;


    }

    public void advance(){
        cursor = cursor.next;
    }

    public void goToStart() {

        if(size == 0 || size == 1) {
            return;
        } else {

            while (cursor.next.id > cursor.id) {
                advance();
            }
            advance();

        }

    }

    public boolean isEmpty() {
        return size == 0;
    }

}
