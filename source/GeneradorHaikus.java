import java.io.*;
import java.util.*;

import jpl.Query;


public class GeneradorHaikus {

	/*
	 * 
	 * 
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
