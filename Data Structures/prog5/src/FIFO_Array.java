//George Polyak
//Prog5
//CSCD300

public class FIFO_Array implements IntegerFIFO_Queue {

    private int size;
    private int head;
    private int tail;
    private int capacity;
    private Integer[] q;

    FIFO_Array() {

        capacity = 10;
        q = new Integer[capacity];
        head = -1;
        tail = -1;
        size = 0;

    }

    @Override
    public int size() {
        return size;
    }

    private boolean isFull() {
        return size == capacity;
    }

    @Override
    public Integer getTop() {

        if (size == 0) {
            return null;
        }
        return q[head];

    }

    @Override
    public Integer getBottom() {

        if (size == 0) {
            return null;
        }
        return q[tail];

    }

    @Override
    public void enqueue(Integer item) {

        if (size == 0) {

            q[0] = item;
            head = 0;
            tail = 0;

        } else if (isFull()) {
            return;
        } else {

            tail = (tail + 1) % capacity;
            q[tail] = item;

        }
        size++;

    }

    @Override
    public Integer dequeue() {

        if (size > 0) {

            Integer ret = q[head];
            if (size == 1) {

                head = -1;
                tail = -1;

            } else {
                head = (head + 1) % capacity;
            }
            size--;
            return ret;

        }
        return null;

    }

}
