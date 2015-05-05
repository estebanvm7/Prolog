import java.io.*;
import java.util.*;

public class ReglasRandom {
    
    /*Cuenta la cantidad de Oraciones, solo tener en cuenta de que todas las
    oraciones deben estar con sin enter a gusto, deben estar seguidas 
    */
    public int numberOfPoints(String root){
		FileReader file = null;
		BufferedReader reader = null;
		String line = "";
		int counter = 0;
		try{		
                    file = new FileReader(root);
                    reader = new BufferedReader(file);
                    line = reader.readLine();
                    while (line != null){
                        line = reader.readLine();
			counter++;
                    }
                    file.close();
                    reader.close();
		}  catch (IOException e) {
			e.getMessage();
		}
		return counter;
	}
    public String getLine(String root, int line){
		FileReader file = null;
		BufferedReader reader = null;
		int counter = 1;
		String result = "";
		if(line < 1){
			result = "Sorry, index must be greater than cero";
		}
		else{
			try{		
				file = new FileReader(root);
				reader = new BufferedReader(file);
				result = reader.readLine();	
				if (result == null){
					result = "Sorry, empty file";
				}
				else{
					while (counter != line){
						result = reader.readLine();		
						counter++;
					}
					if (result == null){
						result = "Sorry, index out of file";
					}
				}
				file.close();
				reader.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
		
    }
    
    public static void main(String[] args) {
        ReglasRandom f = new ReglasRandom();
	String Regla5 = "/home/mauricio/NetBeansProjects/ReglasRandom/src/reglasrandom/Reglas5.pl";
        String Regla7 = "/home/mauricio/NetBeansProjects/ReglasRandom/src/reglasrandom/Reglas7.pl";
        
        Random random = new Random();
        int ran1 = random.nextInt(f.numberOfPoints(Regla5))+1;
        int ran2 = random.nextInt(f.numberOfPoints(Regla7))+1;
        int ran3 = random.nextInt(f.numberOfPoints(Regla5))+1;
        
        String oracion1 = f.getLine(Regla5, ran1);
        String oracion2 = f.getLine(Regla7, ran2);
        String oracion3 = f.getLine(Regla5, ran3);
        
        oracion1 = oracion1.replace("oracion", "oracion1");
        oracion2 = oracion2.replace("oracion", "oracion2");
        oracion3 = oracion3.replace("oracion", "oracion3");
        
        System.out.println(oracion1);
        System.out.println(oracion2);
        System.out.println(oracion3);
        
    }
    
}
