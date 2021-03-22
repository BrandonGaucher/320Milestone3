
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;






class Data{
	
	 public static LinkedList<String> getMapData() throws IOException{
		 //returns the list of airports
		 
		LinkedList<String> airports = new LinkedList<>();
		
			try {
			FileInputStream fis = new FileInputStream("./data/data.xlsx");
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			
				
			
			
			for(int i=0; i<=lastRowNum; i++) {
				Row row = sheet.getRow(i);
				Cell keyCell= row.getCell(0);
				String origin = keyCell.getStringCellValue().trim();
				
		if(!airports.contains(origin)) {
			airports.add(origin);
		}
		}
				
		}catch(FileNotFoundException e){
			e.printStackTrace();
			
		}
			return airports;
		
			}
}

class Graph<T> { 
	  
    // We use Hashmap to store the edges in the graph 
    private Map<T, List<T> > map = new HashMap<>(); 
  
    // This function adds a new vertex to the graph 
    public void addVertex(T s) 
    { 
        map.put(s, new LinkedList<T>()); 
    } 
  
    // This function adds the edge 
    // between source to destination 
    public void addEdge(T source, 
                        T destination, 
                        double cost, boolean bidirectional) 
    { 
  
        if (!map.containsKey(source)) 
            addVertex(source); 
  
        if (!map.containsKey(destination)) 
            addVertex(destination); 
  
        map.get(source).add(destination); 
        if (bidirectional == true) { 
            map.get(destination).add(source); 
        } 
    } 
  
    // This function gives the count of vertices 
    public  int getVertexCount() 
    { 
        
              int count = map.keySet().size(); 
                          
        return count;
    } 
  
    // This function gives the count of edges 
    public void getEdgesCount(boolean bidirection) 
    { 
        int count = 0; 
        for (T v : map.keySet()) { 
            count += map.get(v).size(); 
        } 
        if (bidirection == true) { 
            count = count / 2; 
        } 
        System.out.println("The graph has "
                           + count 
                           + " edges."); 
    } 
  
    // This function gives whether 
    // a vertex is present or not. 
    public void hasVertex(T s) 
    { 
        if (map.containsKey(s)) { 
            System.out.println("The graph contains "
                               + s + " as a vertex."); 
        } 
        else { 
            System.out.println("The graph does not contain "
                               + s + " as a vertex."); 
        } 
    } 
  
    // This function gives whether an edge is present or not. 
    public void hasEdge(T s, T d) 
    { 
        if (map.get(s).contains(d)) { 
            System.out.println("The graph has an edge between "
                               + s + " and " + d + "."); 
        } 
        else { 
            System.out.println("The graph has no edge between "
                               + s + " and " + d + "."); 
        } 
    } 
  
    // Prints the adjancency list of each vertex. 
    @Override
    public String toString() 
    { 
        StringBuilder builder = new StringBuilder(); 
  
        for (T v : map.keySet()) { 
            builder.append(v.toString() + ": "); 
            for (T w : map.get(v)) { 
                builder.append(w.toString() + " "); 
            } 
            builder.append("\n"); 
        } 
  
        return (builder.toString()); 
    } 
} 
class CreateGraph{
	 public static Graph<String> createGraph(int numAirports) throws IOException{
		
		 
		Graph<String> g = new Graph<>();
		
			try {
			FileInputStream fis = new FileInputStream("./data/data.xlsx");
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			

			
			for(int i=0; i<=lastRowNum; i++) {
				Row row = sheet.getRow(i);
				
				Cell originKey = row.getCell(0);
				String origin = originKey.getStringCellValue().trim();
				
				Cell destKey= row.getCell(1);
				String destination = destKey.getStringCellValue().trim();
				
				Cell priceKey = row.getCell(2);
				double price = priceKey.getNumericCellValue();
				double cost = (price*1200)*(price*1200);
				
				Cell timeKey = row.getCell(3);
				double time = timeKey.getNumericCellValue()/65;
				
				
				g.addEdge(origin, destination, cost*time, false);
		
		}
				
		}catch(FileNotFoundException e){
			e.printStackTrace();
			
		}
			return g;
		
			}
	
}

class Dijkstras{
	public static LinkedList<String> solve(Graph<String> routes, String source, String dest){
	//TODO
		
		
		return null;
		
	}
	
}

class Main {

	public static void main(String[] args) throws IOException  {
		
		
		LinkedList<String> airports = Data.getMapData();
	
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter a source:");
		String source = scanner.nextLine();
		System.out.println("Please enter a destination:");
		String dest = scanner.nextLine();
		int numAirports = airports.size();
		
		Graph<String> routes = CreateGraph.createGraph(numAirports);
		System.out.println(routes.toString());
//		LinkedList<String> bestRoute = Dijkstras.solve(routes, source, dest);
		
	}
	
	
	
}
		
	



