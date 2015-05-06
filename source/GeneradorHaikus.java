import java.io.*;
import java.util.*;

import jpl.Query;


public class GeneradorHaikus {
	
	/**
	 * Retorna una hilera de caracteres correspondiante a un haiku.
	 * Este metodo siempre retorna, en caso de que no existan haikus
	 * para las reglas dadas y sus respectivos átomos, el metodo retornaria
	 * una hilera de caracteres indicando que no se encontraron posibles combinaciones.
	 * El metodo tambien imprime por medio de un System.out.println(); la cantidad de combinaciones
	 * posibles asi como el numero de combinacion en especifico que estamos utilizando.
	 * 
	 * @param	ruta del archivo prolog a analizar
	 * @param	nombre del comando principal para correr el archivo.pl
	 * @return	hilera conteniendo el resultado de busqueda
	 * @see 	libraria de swi-prolog
	 */ 
	public String getHaiku(String file, String comando){
		Query q;
	    	q = new Query("consult('"+file+"')");
	    	q.hasSolution();
	    	q = new Query(comando+"(O,[]).");
	    	q.hasSolution();
	    	String res = "";
	    	Hashtable[] list;
	    	list = q.allSolutions();
	    	int size = list.length;
        	System.out.println("Total de haikus encontrados: "+size);
        	if (size == 0){
			return "No se encontraron haikus para esta combinación";
		}
		Random random = new Random();
	    	int ran = random.nextInt(size)+1;
	    	System.out.println("Tomamos el haiku número: "+ran);
	    	while (q.hasMoreElements()) {	
	    		if (ran == size){
	    			res = q.nextElement().toString();
	    		}
	    		ran++;
	    		q.nextElement();
	    	}
	    	res = res.replace('O', '\0');
	    	res = res.replace('=', '\0');
	    	res = res.replace('.', '\0');
	    	res = res.replace('(', '\0');
	    	res = res.replace(')', '\0');
	    	res = res.replace('[', '\0');
	    	res = res.replace(']', '\0');
	    	res = res.replace(',', '\0');
	    	res = res.replace('}', '\0');
	    	res = res.replace('{', '\0');
	     	res = res.replace('#', '\n');
	    	res = res.replace((char)39, '\0');
	    	q.close();
	    	return res;
	}
}
