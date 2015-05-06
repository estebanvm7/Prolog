import java.io.*;
import java.util.*;

public class FileManager {
	
	/**
	 * Retorna el numero de lineas de un archivo en especifico. Podria verse como un 
	 * length de file con numero de lineas como elemento a contar.
	 * En caso de no poder abrir un archivo, se denota como length = 0 por defecto.
	 * @param	ruta de ubicacion del archivo a analizar
	 * @return      un numero con la cantidad de lineas de un archivo.
	 * @see		FileReader, BufferedReader, IOException.
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
	 * Retorna una linea en especifico de un archivo. Podria verse como un find(int n) sobre el archivo
	 * donde el valor de retorno seria una linea en especifico de un archivo.
	 * En caso de que se le de un valor fuera del rango del archivo, retornaria un error.
	 * @param 	ruta del archivo a analizar.
	 * @param 	entero con el nuemero de linea a consultar.
	 * @see 	FileReader, BufferedReader, IOException.
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
	
	/**
	 * Retorna un atomo para nuestro programa en prolog, dependiendo de las entradas que obtenga la funcion.
	 * en este caso tomamos la ruta y se toma como header del atomo y mediante el resto de sus datos, crea asi
	 * sus respectivas reglas.
	 * En este caso se toma la ruta, se abre el archivo y se elige una linea la cual convertiremos en
	 * atomo y convertimos su informacion en un atomo.
	 * @param 	hilera con la ruta donde se encuentra el archivo original.
	 * @param 	linea a actualizar a atomo
	 * @return 	hilera correspondiente al atomo de prolog
	 * @see 	String[]
	 */
        
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
}
