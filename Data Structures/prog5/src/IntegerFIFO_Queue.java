//George Polyak
//Prog5
//CSCD300

public interface IntegerFIFO_Queue {

    int size();
    Integer getTop();
    Integer getBottom();
    void enqueue(Integer item);
    Integer dequeue();

}
