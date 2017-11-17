//George Polyak
//Prog5
//CSCD320

public class Edge implements Comparable<Edge> {

    final Integer source;
    final Integer dest;
    final Integer weight;

    Edge(int source, int dest, int weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return weight - e.weight;
    }

}