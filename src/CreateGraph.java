import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateGraph {
	 public static List<Edge> createGraph() throws IOException{
			// save edges of graph in arraylist 
		 ArrayList<Edge> list = new ArrayList<>();
		  
		
			try {
			FileInputStream fis = new FileInputStream("./data/data.xlsx");
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			

			
			for(int i=0; i<lastRowNum; i++) {
				Row row = sheet.getRow(i);
				
				Cell originKey = row.getCell(0);
				int origin = (int) originKey.getNumericCellValue();
				
				Cell destKey= row.getCell(1);
				int destination = (int) destKey.getNumericCellValue();
				
				Cell priceKey = row.getCell(2);
				double price = priceKey.getNumericCellValue();
				double cost = ((price))*((price));
				
				Cell timeKey = row.getCell(3);
				double time = (timeKey.getNumericCellValue())/65;
				
				double totalCost = (cost*time);
				list.add(new Edge(origin, destination, (int) (totalCost)));
				
		
		}
				
		}catch(FileNotFoundException e){
			e.printStackTrace();
			
		}
			
			return list;
		
			}
	

}
