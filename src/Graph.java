import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<List<Edge>> adjList = null;
    Graph(List<Edge> edges, int N)
    {
        adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }
        for (Edge edge: edges) {
            adjList.get(edge.source).add(edge);
        }
    }
}