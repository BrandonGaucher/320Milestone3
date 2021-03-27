// this implementation of dijkstras was designed based on our psuedo code from milestone 1 and some
// helpful information from: https://www.techiedelight.com/single-source-shortest-paths-dijkstras-algorithm/
// It has been modified to work with our data and problem
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Main
{
    private static void getRoute(int[] prev, int i, List<Integer> route)
    {
        if (i >= 0)
        {
            getRoute(prev, prev[i], route);
            route.add(i);
        }
    }
 
    public static void dijkstras(Graph graph, int source, int N, int dest)
    {

    	  List<Integer> dist;
          // set dist[v] = infinity for each vertex in the graph
          dist = new ArrayList<>(Collections.nCopies(N, Integer.MAX_VALUE));
   
          // creating prev array
          int[] prev = new int[N];
          // prev[source] = undefined
          prev[source] = -1;
          
          // dist[source] = 0;
          dist.set(source, 0);
   
          List<Integer> route = new ArrayList<>();
   
          // boolean array to track vertices for which minimum
          // cost is already found
          boolean[] done = new boolean[N];
          done[source] = true;
        
     // Q will be a set of all nodes in graph
  	  PriorityQueue<Node> Q;
  	  Q = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
  	  Q.add(new Node(source, 0));
 
        // while Q is not empty
        while (!Q.isEmpty())
        {
            // finding u (the node in Q with the smallest distance
            Node node = Q.poll();
            int u = node.vertex;
 
            // for each neighbour v of u
            for (Edge edge: graph.adjList.get(u))
            {
                int v = edge.dest;
                double weight = edge.weight;
 
                // Relaxation step
                if (!done[v] && (dist.get(u) + weight) < dist.get(v))
                {
                    dist.set(v, (int) (dist.get(u) + weight));
                    prev[v] = u;
                    Q.add(new Node(v, dist.get(v)));
                }
            }
            // mark vertex u "done" so it is not calculated again
            done[u] = true;
        }
        	// returning the best route to the user
            if (dest != source && dist.get(dest) != Integer.MAX_VALUE)
            {
                getRoute(prev, dest, route);
                String string_source = Converter.convertIntToSymbol(source);
                String string_dest = Converter.convertIntToSymbol(dest);
                List<String> string_route = new ArrayList<>();
                //convert the route of integers to strings for user 
                for(int i=0; i< route.size(); i++) {
                	String node = Converter.convertIntToSymbol(route.get(i));
                	string_route.add(i, node);
                }
                System.out.println("Flight from " + string_source +" to "+ string_dest);
                System.out.printf("Cost of trip: = %d, Best route = %s\n",
                        dist.get(dest), string_route);
                route.clear();
            }
        }
    
    
 
    public static void main(String[] args) throws IOException
    {
    	//calculate how many nodes in graph from datasheet
    	FileInputStream fis = new FileInputStream("./data/data.xlsx");
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
        final int N = lastRowNum;
        //create graph from the data
        List<Edge> edges =  CreateGraph.createGraph();
        // construct graph with edges
        Graph graph = new Graph(edges, N);
 
        // ask user for source and destination inputs
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your departing airport:");
		String user_source = scanner.nextLine();
		System.out.println("Please enter a destination airport:");
		String user_dest = scanner.nextLine();
		scanner.close();
		//convert user input to int 
		int source = Converter.convertSymbolToInt(user_source);
		int dest =  Converter.convertSymbolToInt(user_dest);
		
		long startTime = System.nanoTime();
		
		// perform dijkstras on the graph based on where the user wants to go
        dijkstras(graph, source, N, dest);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println("\nThe algorithm took: " + duration +"ms.");
    }
}
	



