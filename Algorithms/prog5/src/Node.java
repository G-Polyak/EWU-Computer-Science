//George Polyak
//Prog5
//CSCD320

public class Node implements Comparable<Node> {

    Integer data;
    Node next;
    boolean visited;
    int d;
    int f;
    Node p;

    Node(Integer data) {
        this.data = data;
        this.visited = false;
        this.d = Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(Node n) {
        return this.f - n.f;
    }

}
