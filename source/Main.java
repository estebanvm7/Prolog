
import java.util.*;


public class Main {
    public static void main(String[] args){
        boolean bandera = true;
        Scanner scan = new Scanner(System.in);
        String siguiente= scan.nextLine();
        while (bandera){
            if (siguiente.equals(";")){
                System.out.println("Haiku");
                siguiente = scan.nextLine();
            }
            else if(siguiente.equals(".")){
                bandera = false;
                System.out.println("Desarrollado por Esteban, Gustavo y Mauricio. !Gracias!");
            }
            else{
                siguiente = scan.nextLine();
            }
            
        }
        scan.close(); 
    }

}
    

