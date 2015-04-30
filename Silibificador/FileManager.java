import java.io.*;

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
				e.getMessage();
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
		//String gender = "";
		int syllable = 0;
		try {
			file = new FileReader(origin);
			reader = new BufferedReader(file);
			writer = new PrintWriter(destiny+".pl", "UTF-8");
			temp = reader.readLine();
			String[] spliter = temp.split(" ");
			//crear instancia se silibificador y aplicar
			//numerosilabas sobre temp y asignarlo a syllable
			
			//faltaria detectar el genero y asignarlo a gender
			while(temp != null){
				writer.println(destiny+"(G,N) --> ["+spliter[0]+"], {G="+spliter[1]+"}, {N is "+syllable+"}.");
				temp = reader.readLine();
			}
			file.close();
			reader.close();
			writer.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}
}
