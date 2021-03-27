// there is probably a better way to do this in the future
public class Converter {
	public static int convertSymbolToInt(String airportCode) {

	    switch(airportCode) {

	    case "AUS":
	        return 0;

	    case "CHS":
	        return 1;

	    case "BDL":
	        return 2;

	    case "MSP":
	        return 3;

	    case "TPA":
	    	
	        return 4;
	        
	    case "PDX":
	        return 5;

	    case "PBI":
	        return 6;

	    case "SAV":
	        return 7;

	    case "PVD":
	    	
	        return 8;
	    case "IND":
	        return 9;

	    default:
	        return -1;

	    }
	}
	public static String convertIntToSymbol(int code) {

	    switch(code) {

	    case 0:
	        return "AUS";

	    case 1:
	        return"CHS";

	    case 2:
	        return "BDL";

	    case 3:
	        return "MSP";

	    case 4:
	        return "TPA";
	    case 5:
	        return"PDX";

	    case 6:
	        return "PBI";

	    case 7:
	        return "SAV";

	    case 8:
	        return "PVD";
	        
	    case 9:
	        return "IND";

	    default:
	        return "NaN";

	    }
	}
}
