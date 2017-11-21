import java.util.*;

class Graph {
    private final HashMap<Integer, SortedSet<Edge>> adjacencyList;

    Graph() {
        adjacencyList = new HashMap<>();
    }

    /*void breadthFirstPrint(int node) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        q.add(node);
        do {
            int i = q.remove();
            visited.add(i);
            System.out.print(i);
            adjacencyList.get(i)
                    .forEach(x -> {
                        if (!visited.contains(x.dest)) {
                            q.add(x.dest);
                            visited.add(x.dest);
                        }
                    });
            if (!q.isEmpty()) System.out.print("->");
        } while (!q.isEmpty());

    }*/

    void addEdge(int src, int dest, int weight) {
        addEdge(src, new Edge(dest, weight));
    }

    private void addEdge(int src, Edge edge) {
        if (adjacencyList.containsKey(src)) {
            adjacencyList.get(src).add(edge);
        } else {
            SortedSet<Edge> edges = new TreeSet<>();
            edges.add(edge);
            adjacencyList.put(src, edges);
        }
    }

    private class Edge implements Comparable<Edge> {
        final Integer dest;
        final Integer weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return weight - e.weight;
        }
    }

}