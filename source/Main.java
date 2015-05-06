import java.io.*;
import static java.lang.Math.random;
import java.util.*;

public class Main {
	public static void main(String[] args) {
	boolean bandera = true;
		
	//se crean los objetos
	Random random = new Random();
        FileManager file = new FileManager();
	Scanner scan = new Scanner(System.in);
        GeneradorHaikus gen = new GeneradorHaikus();
		
	//se crean los string que almacenan la dirreción de los archivos
        String Regla5 = "Reglas5.pl";
        String Regla7 = "Reglas7.pl";
	String verbos = "verbos.txt";
	String articulos = "articulos.txt";
        String adejtivos = "adjetivos.txt";
        String pronombres = "pronombres.txt";
        String sustantivos = "sustantivos.txt";
        String preposiciones = "preposiciones.txt";

        while (bandera) { //mientras bandera sea True se repite el ciclo
        	String siguiente= scan.nextLine();
            	String haiku = "";
            	if (siguiente.equals(";")) {
				
			//variables tipo int que almacenan un numero aleatorio del todal de lineas del archivo de las reglas DCG
			int ran1 = random.nextInt(file.numberOfLines(Regla5))+1;
			int ran2 = random.nextInt(file.numberOfLines(Regla7))+1;
			int ran3 = random.nextInt(file.numberOfLines(Regla5))+1;
			
			//String que almacenan una regla del archivo con los DCG
			String oracion1 = file.getLine(Regla5, ran1);
			String oracion2 = file.getLine(Regla7, ran2);
			String oracion3 = file.getLine(Regla5, ran3);
				
			//se introducen en el archivo las reglas de los haikus
			oracion1 = oracion1.replace("oracion", "oracion1");
			oracion2 = oracion2.replace("oracion", "oracion2");
			oracion3 = oracion3.replace("oracion", "oracion3");
			String oracion4 = "cambio -->[#].";
			String oracion5 = "haiku --> cambio, oracion1, cambio, oracion2, cambio, oracion3.";
			
			//lista que almacena la dirreción a archivos con los tipos de atomos (articulos, adjetivos, preposiciones, pronombres, sustantivos y verbos)
			List <String> list = new ArrayList<String>();
			list.add(articulos); list.add(adejtivos); list.add(preposiciones); list.add(pronombres);
			list.add(sustantivos); list.add(verbos);
			
			//intenta abrir el archivo main.pl
			try (PrintWriter writer = new PrintWriter("main.pl", "UTF-8"); ) {
				
				//escribe las reglas en el archivo
				writer.println(oracion1+"\n"+oracion2+"\n"+oracion3+"\n"+oracion4+"\n"+oracion5);
					
				//ciclo que recorre cada archivo de atomos disponible
				//cuenta la cantidad de palabras en en archivo
				//saca cinco palabras de cada archivo de forma aleatoria, las convierte en atomos y las escribe en el archivo
				for (String i: list) {
					int cantLineas = file.numberOfLines(i);
					for (int j = 0; j < 5; j++) {
						String linea = file.getLine(i, random.nextInt(cantLineas)+1);
						writer.println(file.textToPl(i, linea));
					}
				}
				writer.flush();
				writer.close(); //cierra el archivo
			} catch (IOException e) { //excepción al abrir el archivo
				System.out.println(e.getMessage());
			}
			haiku = gen.getHaiku("main.pl","haiku"); //abre el archivo main.pl y retorna un string que contiene un haiku
                System.out.println(haiku);
            }
            else if(siguiente.equals(".")){ //cambia la bandera, se sale del ciclo y termina la ejecución
                bandera = false;
                System.out.println("Desarrollado por Esteban, Gustavo y Mauricio. !Gracias!");
            }
		}
        scan.close();
    }
}
