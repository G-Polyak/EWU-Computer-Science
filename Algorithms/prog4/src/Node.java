//George Polyak
//Prog4
//CSCD320

public class Node implements Comparable<Node> {

    Integer data;
    Node next;
    boolean visited;
    int d;
    int f;

    Node(Integer data) {
        this.data = data;
        this.visited = false;
    }

    @Override
    public int compareTo(Node n) {
        return this.f - n.f;
    }

}
