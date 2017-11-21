//George Polyak
//Prog4
//CSCD320

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TopoSort {

    private static int time;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File(args[0]));
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
        LinkedList temp1;
        String[] temp2;
        while (file.hasNextLine()) {

            temp = file.nextLine().split(":");
            temp1 = new LinkedList();
            if (temp.length > 1) {

                temp2 = temp[1].split(",");
                for (String s : temp2) {

                    int a = Integer.parseInt(s);
                    for (Node vert : verts) {

                        if (vert.data != a) {
                            temp1.add(a);
                        } else {
                            temp1.add(vert);
                        }

                    }

                }

            }
            gList[Integer.parseInt(temp[0])] = temp1;

        }
        topoSort(gList, verts);

    }

    private static void topoSort(LinkedList[] gList, Node[] verts) {

        DFS(gList, verts);
        LinkedList end = new LinkedList();
        Node[] copy = verts.clone();
        Arrays.sort(copy);
        for (Node n : copy) {
            end.addFirst(n.data);
        }
        System.out.print(end);

    }

    private static void DFS(LinkedList[] gList, Node[] verts) {

        time = 0;
        for (Node u : verts) {

            if (!u.visited) {
                graph_DFS(gList, u);
            }

        }

    }

    private static void graph_DFS(LinkedList[] gList, Node u) {

        time++;
        u.d = time;
        for (int i = 0; i < gList[u.data].getSize(); i++) {

            Node v = gList[u.data].getNode(i);
            if (!v.visited) {
                graph_DFS(gList, v);
            }

        }
        time++;
        u.f = time;
        u.visited = true;

    }

}
