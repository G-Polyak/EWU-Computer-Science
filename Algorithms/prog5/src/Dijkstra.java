//George Polyak
//Prog5
//CSCD320

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dijkstra {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File(args[0]));
        int fromVertex = Integer.parseInt(args[1]);
        ArrayList<Edge> edges = new ArrayList<>();
        int counter = 0;
        while (file.hasNextLine()) {
            file.nextLine();
            counter++;
        }
        file.close();
        LinkedList[] gList = new LinkedList[counter];
        Node[] verts = new Node[counter];
        for (int i = 0; i < verts.length; i++) {
            verts[i] = new Node(i);
        }
        file = new Scanner(new File(args[0]));
        String temp[];
        String tempWeights[];
        LinkedList temp1;
        String[] temp2;
        while (file.hasNextLine()) {

            temp = file.nextLine().split(":");
            temp1 = new LinkedList();
            if (temp.length > 1) {

                tempWeights = temp[1].split(";"); //I think this was the source of all of the problems
                for (String s : tempWeights) {          //with the entire code...

                    temp2 = s.split(",");
                    if (temp2.length > 1) {

                        int a = Integer.parseInt(temp2[0]);
                        int b = Integer.parseInt(temp2[1]);
                        for (Node vert : verts) {

                            if (vert.data != a) {
                                temp1.add(a);
                            } else {
                                temp1.add(vert);
                            }
                            edges.add(new Edge(Integer.parseInt(temp[0]), a, b));

                        }

                    }

                }
            }
            gList[Integer.parseInt(temp[0])] = temp1;

        }
        findShortestPaths(edges, gList, verts, verts[fromVertex]);

    }

    private static void findShortestPaths(ArrayList<Edge> edges, LinkedList[] gList, Node[] verts, Node s) {

        s.d = 0;
        MinHeap Q = new MinHeap(verts.length);
        for (Node n : verts) {
            Q.insert(n.data);
        }
        ArrayList<Node> S = new ArrayList<>(); //This set is never referenced in the rest of the code
        while (Q.size() != 0) {                //despite it being referenced in the given pseudo-code...

            Node u = verts[Q.extractMin()];
            S.add(u);
            int weight;
            for(int i = 0; i < gList[u.data].getSize(); i++) {

                Node v = gList[u.data].getNode(i);
                weight = getWeight(edges, u, v);
                if(v.d > u.d + weight) {
                    v.d = u.d + weight;
                    v.p = u;
                }

            }

        }

        ArrayList<Integer> temp = new ArrayList<>();
        boolean printed = false;
        for(int i = 0; i < gList.length; i++) { //I had no idea how to print the info, I know this is
                                                //incorrect, but it's the best I could come up with...
            if (i != s.data) {

                for(int j = 0; j < gList[i].getSize(); j++) {

                    if(gList[i].getNode(i).d != Integer.MAX_VALUE) {

                        Node n = gList[i].getNode(i);
                        while (n.p != null) {
                            temp.add(n.p.data);
                            n = n.p;
                        }
                        System.out.println("[" + i + "] shortest path: " + temp + " shortest distance:" +
                                " " + n.d);
                        printed = true;

                    }

                }
                if(!printed) {
                    System.out.println("[" + i + "] unreachable");
                }

            }

        }

    }

    //I recognize that the following method is extremely inefficient for it's intended purpose...
    private static int getWeight(ArrayList<Edge> edges, Node a, Node b) {

        int weight = Integer.MAX_VALUE;
        int from = a.data;
        int to = b.data;
        for (Edge e : edges) {

            if(e.source == from && e.dest == to) {
                return e.weight;
            }

        }
        return weight;

    }

}
