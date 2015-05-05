import java.io.*;
import java.util.*;

import jpl.Query;


public class GeneradorHaikus {

	
	public String getHaiku(String file, String comando){
		Query q;
	    q = new Query("consult('"+file+"')");
	    /*System.out.println(*/q.hasSolution()/*)*/;
	    q = new Query(comando+"(O,[]).");
	    /*System.out.println(*/q.hasSolution()/*)*/;
	    String res = "";
	    Hashtable[] list;
	    list = q.allSolutions();
	    int size = list.length;
	    Random random = new Random();
	    int ran = random.nextInt(size)+1;
	    while (q.hasMoreElements()) {	
	    	if (ran == size){
	    		res = q.nextElement().toString();
	    		//System.out.println(q.nextElement());
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
	    res = res.replace((char)39, '\0');
	    q.close();
	    return res;
	}
	
	
	/*public static void main(String[] args) {
		GeneradorHaikus gen = new GeneradorHaikus();
		//System.out.println(gen.getOracion("01-03.pl"));
		gen.printHaiku("pruebas.pl", "01-07.pl", "01-03.pl");
	}*/

}
