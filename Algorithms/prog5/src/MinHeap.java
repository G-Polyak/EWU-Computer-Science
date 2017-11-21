//George Polyak
//Prog5
//CSCD320

public class MinHeap {

    private int[] heap;
    private int size;

    MinHeap(int size) {
        heap = new int[size+1];
        this.size = 0;
        heap[0] = -999999999;
    }

    int size() {
        return size;
    }

    private void minHeapify(int i) {

        int left = 2*i;
        int right = left+1;
        if(left > heap.length || right > heap.length) {
            return;
        }
        int smallest;
        if(left <= size && heap[left] < heap[i]) {
            smallest = left;
        } else {
            smallest = i;
        }
        if(right <= size && heap[right] < heap[smallest]) {
            smallest = right;
        }
        if(smallest != i) {

            exchange(i, smallest);
            minHeapify(smallest);

        }

    }

    private void exchange(int i1, int i2) {

        int temp = heap[i1];
        heap[i1] = heap[i2];
        heap[i2] = temp;

    }

    private void buildMinHeap() {

        for(int i = heap.length; i >= 1; i--) {
            minHeapify(i);
        }

    }

    void heapSort() {

        for(int i = size; i >= 2; i--) {

            exchange(1, i);
            size--;
            minHeapify(1);

        }

    }

    void replaceMin(int key) {

        if (size >= 1) {
            heap[1] = key;
            minHeapify(1);
        }

    }

    int heapMin() {
        return heap[1];
    }

    public int extractMin() {

        if(size >= 1) {

            int min = heap[1];
            heap[1] = heap[size];
            size--;
            minHeapify(1);
            return min;

        }
        return Integer.MIN_VALUE;
    }

    private void increaseKey(int i, int key) {

        if(key > heap[i]) {
            throw new IndexOutOfBoundsException();
        }
        heap[i] = key;
        while (i > 1 && heap[i/2] > heap[i]) {

            exchange(i, i/2);
            i = i/2;

        }

    }

    void insert(int key) {

        size++;
        heap[size] = Integer.MAX_VALUE;
        increaseKey(size, key);

    }

    int[] getHeapArray() {
        return heap;
    }

}
