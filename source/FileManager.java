import java.io.*;
import java.util.*;

public class FileManager {
	
	/*
	 * Esta funcion nos indica cuantas lineas tiene un archivo.
	 * Entradas: ruta del archivo.
	 * Salidas: numero de lineas de un archivo.
	 */
	public int numberOfLines(String root){
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
		} catch (IOException e) {
			e.getMessage();
		}
		return counter;
	}
	

	/*
	 * Esta función lee un archivo y devuelve una linea en especifico de ese archivo
	 * Entrada: la ruta del archivo y el numero de linea que quiero obtener.
	 * Salida: el string de la linea que le solicité
	 */
	
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
	
	/*
	 * Funcion que me permite tomar un archivo .txt y pasarlo a sentencias
	 * de prolog.
	 * Entradas: un archivo .txt
	 * Salidas: un archivo .pl
	 */
	public void txt_to_pl(String origin, String destiny){
		FileReader file = null;
		BufferedReader reader = null;
		PrintWriter writer = null;
		String temp = "";
		int syllable = 0;
		try {
			file = new FileReader(origin);
			reader = new BufferedReader(file);
			writer = new PrintWriter(destiny+".pl", "UTF-8");
			temp = reader.readLine();
			Sibilificador syll = new Sibilificador();
			syll.SeparadorDeSilabas();
			while(temp != null){
				
				if(origin.contains("verbos")){
					String[] spliter = new String[1];
					spliter = temp.split(" ");
					syllable = syll.NumeroSilabas(spliter[0]);
					writer.println(destiny+"(M,T) --> ["+spliter[0]+"], {M is "+syllable+"}"+ ", {T="+spliter[1]+"}.");     
				}
				else if(origin.contains("preposiciones")){
					syllable = syll.NumeroSilabas(temp);
					writer.println(destiny+"(M) --> ["+temp+"], {M is "+syllable+"}."); 
				}
				else{   
					String[] spliter = new String[2];
					spliter = temp.split(" ");
					syllable = syll.NumeroSilabas(spliter[0]);
					writer.println(destiny+"(G,M,T) --> ["+spliter[0]+"], {G="+spliter[1]+"}, {M is "+syllable+"}"+ ", {T="+spliter[2]+"}."); 
				}
				temp = reader.readLine();
			}
			file.close();
			reader.close();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
        
        public String textToPl(String root, String line){
            String res = "";
            int syllable = 0;
            Sibilificador syll = new Sibilificador();
            syll.SeparadorDeSilabas();
            if(root.contains("verbos")){
                String[] spliter = new String[1];
		spliter = line.split(" ");
		syllable = syll.NumeroSilabas(spliter[0]);
                root = root.replace("s.txt", "");
		return root+"(M,T) --> ["+spliter[0]+"], {M is "+syllable+"}"+ ", {T="+spliter[1]+"}.";     
            }
            else if(root.contains("preposiciones")){
		syllable = syll.NumeroSilabas(line);
                root = root.replace(".txt", "");
		return root+"(M) --> ["+line+"], {M is "+syllable+"}."; 
            }
            else{   
		String[] spliter = new String[2];
		spliter = line.split(" ");
		syllable = syll.NumeroSilabas(spliter[0]);
                root = root.replace(".txt", "");
		return root+"(G,M,T) --> ["+spliter[0]+"], {G="+spliter[1]+"}, {M is "+syllable+"}"+ ", {T="+spliter[2]+"}."; 
            }
        }
	/*
	 * funcion que toma varios archivos y los une en uno solo
	 */
	public void createWholePl(List<FileReader> list, String name){
		String temp = "";
		PrintWriter writer = null;
		BufferedReader reader = null;
		try{
			writer = new PrintWriter(name+".pl", "UTF-8");
			for (FileReader i: list){
				reader = new BufferedReader(i);
				temp = reader.readLine();
				while(temp != null){
					writer.println(temp);
					temp = reader.readLine();
				}
				i.close();
				
			}
			writer.flush();
			writer.close();
		}catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	/*public static void main(String[] args){
		FileManager file = new FileManager();
		file.txt_to_pl("verbos.txt", "verbo");
		file.txt_to_pl("articulos.txt", "articulos");
		file.txt_to_pl("pronombres.txt", "pronombres");
		file.txt_to_pl("sustantivos.txt", "sustantivos");
		file.txt_to_pl("adjetivos.txt", "adjetivos");
		file.txt_to_pl("preposiciones.txt", "preposiciones");
	}*/
}
