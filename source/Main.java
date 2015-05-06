
//import javaapplication3.GeneradorHaikus;
import java.io.*;
import static java.lang.Math.random;
import java.util.*;


public class Main {
    public static void main(String[] args){
        boolean bandera = true;
        FileManager file = new FileManager();
        Scanner scan = new Scanner(System.in);
       
       
        String Regla5 = "Reglas5.pl";
        String Regla7 = "Reglas7.pl";    
	String articulos = "articulos.txt";
        String adejtivos = "adjetivos.txt";
        String preposiciones = "preposiciones.txt";
        String pronombres = "pronombres.txt";
        String sustantivos = "sustantivos.txt";
        String verbos = "verbos.txt";

        while (bandera){
            Random random = new Random();
            ReglasRandom reglas = new ReglasRandom();
            int ran1 = random.nextInt(file.numberOfLines(Regla5))+1;
            int ran2 = random.nextInt(file.numberOfLines(Regla7))+1;
            int ran3 = random.nextInt(file.numberOfLines(Regla5))+1;
            
            String oracion1 = file.getLine(Regla5, ran1);
            String oracion2 = file.getLine(Regla7, ran2);
            String oracion3 = file.getLine(Regla5, ran3);
        
            oracion1 = oracion1.replace("oracion", "oracion1");
            oracion2 = oracion2.replace("oracion", "oracion2");
            oracion3 = oracion3.replace("oracion", "oracion3");
            String oracion4 = "haiku --> oracion1, oracion2, oracion3.";
            

            
            List <String> list = new ArrayList<String>();
            list.add(articulos);list.add(adejtivos);list.add(preposiciones);list.add(pronombres);
            list.add(sustantivos);list.add(verbos);
            try (PrintWriter writer = new PrintWriter("main.pl", "UTF-8"); ) {
                writer.println(oracion1+"\n"+oracion2+"\n"+oracion3+"\n"+oracion4);
                for (String i: list) {
                    int cantLineas = file.numberOfLines(i);
                    for (int j = 0; j < 5; j++){
                        String linea = file.getLine(i, random.nextInt(cantLineas)+1);
                        //System.out.println(file.textToPl(i, linea));
                        writer.println(file.textToPl(i, linea));
                        //System.out.println(linea);      la f s
                        //file.txt_to_pl(linea,"main");
                    }
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
           String siguiente= scan.nextLine();
            String haiku = "";
            GeneradorHaikus gen = new GeneradorHaikus();
            haiku = gen.getHaiku("main.pl","haiku");
            //System.out.println(haiku);
            if (siguiente.equals(";")){
            	 haiku = gen.getHaiku("main.pl","haiku");
                 System.out.println(haiku);
            }
            else if(siguiente.equals(".")){
                bandera = false;
                System.out.println("Desarrollado por Esteban, Gustavo y Mauricio. !Gracias!");
            }
            else{
                
            }
        }
        scan.close(); 
    }

}
    

