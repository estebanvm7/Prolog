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
		String result = "yolo";
		int counter = 1;
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

}
