import java.util.*;


public class Sibilificador {
    private final int MAX_SILABAS = 20;
    private int lonPal;		      	// Longitud de la palabra
    private int numSil;			// Número de silabas de la palabra
    private int tonica;			// Posición de la silaba tónica (empieza en 1)
    private boolean encTonica;		// Indica si se ha encontrado la silaba tónica
    private int letraTildada;		// Posición de la letra tildda, si la hay 
    private ArrayList posiciones;	// Posiciones de inicio de las silabas
    private String ultPal;		// Última palabra tratada, se guarda para
					// no repetir el proceso si se pide la misma

    public void SeparadorDeSilabas(){
	ultPal = ""; 
	posiciones = new ArrayList ();
    }
    
   /**
    * Devuleve un estructura de array con las posiciones de inicio de las silabas de cada palabra.
    * El argumento es la palabra que se va a evaluar.
    * Se llama al metodo calcular para realizar una evaluacion de la palabra ingresada.
    * Se retornara el array de posiciones, que ha sido evaluado en otros metodos del codigo.
    * @param   la palabra que se va a evaluar
    * @return  un array de la palabra con sus respectivas posiciones, separadas por sus silabas
    */
    public ArrayList PosicionSilabas (String palabra){
	Calcular (palabra);
	return posiciones;
    }
   
   /**
    * Devuleve el numero de silabas que tiene la palabra.
    * El argumento es la palabra que se va a evaluar.
    * Se llama al metodo calcular para realizar una evaluacion de la palabra ingresada.
    * Se retornara el numero de silabas.
    * @param   la palabra que se va a evaluar
    * @return  la cantidad numerica entera de silabas en la palabra
    */
    public int NumeroSilabas (String palabra){
	Calcular (palabra);
	return numSil;
    }
   
   /**
    * Devuleve la posicion de la palabra en la cual se encuentra la silaba tonica.
    * El argumento es la palabra que se va a evaluar.
    * Se llama al metodo calcular para realizar una evaluacion de la palabra ingresada.
    * Se retornara el numero de la posicion de la palabra que contiene la silaba tonica.
    * @param   la palabra que se va a evaluar
    * @return  la posicion dentro de la palabra
    */
    public int SilabaTonica (String palabra){
	Calcular (palabra);
	return tonica;
    }
    
    /**
    * Cuenta la cantidad de tildes que tiene la palabra.
    * El argumento es la palabra que se va a evaluar.
    * Se llama al metodo tiene tilde para realizar una evaluacion de la palabra ingresada.
    * Se retornara el cantidad de tildes de la palabra.
    * @param   la palabra que se va a evaluar
    * @return  cantidad de tildes de la palabra
    */
    public int contarTildes(String palabra){
        int contador = 0;
        for(int i = 0; i < palabra.length(); i++){
            if(TieneTilde(palabra.charAt(i)) == true){
                contador +=1;
            }
        }
        return contador;
    }
    /**
    * Determina si la palabra evaluada esta correctamente tildada..
    * El argumento es la palabra que se va a evaluar.
    * Se retornara un numero que tiene los siguientes significado:
    * 0 - bien tildada
    * 7 - varias tildes en la palabra
    * 8 - aguda mal tildada
    * 9 - llana mal tildada
    * @param   la palabra que se va a evaluar
    * @return  un numero que indica si la palabra esta bien tildada, si posee varias tildes, 
    * si es aguda o si es llana
    */
    public int BienTildada(ArrayList silabeo, String palabra){
        int numSilabas = (int)silabeo.get(0);
	// Comprueba si hay má de una tilde en la palabra
	if (contarTildes(palabra.toLowerCase()) > 1) return 7;
            int posTónica =  (int)silabeo.get(numSilabas + 1);
	if (numSilabas - posTónica < 2){ // Si la palabra no es esdrújula
            char ultCar = palabra.charAt(palabra.length() - 1);
            int finalPal = (posTónica < numSilabas? (int) silabeo.get(posTónica + 1): palabra.length()) - (int)silabeo.get(posTónica);
            
            String silaba = palabra.toLowerCase().substring((int)silabeo.get(posTónica), finalPal);
            int i;
            // Se busca si hay tilde en la sílaba tónica
            for (i= 0; i < silaba.length(); i++){
                if ("áéíóú".indexOf(silaba.charAt(i)) > -1)
                    break;
            }

            if (i < silaba.length()){ // Hay tilde en la sílaba tónica
		// La palabra es aguda y no termina en n, s, vocal -> error
		if ((posTónica == numSilabas) && ("nsáéíióúu".indexOf(ultCar) == -1))
                    return 8;
                // La palabra es llana y termina en n, s, vocal -> error
		if ((posTónica == numSilabas - 1) && ("nsaeiou".indexOf(ultCar) != -1))
                    return 9;
		}
	}

	return 0; // La palabra está correctamente tildada
    }

    /**
    * Determina si un caracter tiene tilde.
    * El argumento es un caracter.
    * Se retornara un booleano de True o False segun el resultado evaluado.
    * @param   el caracter que se va a evaluar
    * @return  un booleano de la evaluacion echa sobre el caracter
    */
    private boolean TieneTilde(char c){
        return "áéíóú".indexOf(c) != -1;
    }

    /**
    * Determina si hay que llamar a el metodo de PosicionSilabas.
    * El argumento es la palabra a evaluar.
    * @param   la palabra a evaluar
    */
    public void  Calcular (String palabra){
	if (!palabra.equals(ultPal)) {
            ultPal = palabra.toLowerCase();
            PosicionSilabas ();
	}
    }
	
    /**
    * Determina si c es una vocal fuerte o debil acentuada.
    * El argumento es el caracter c.
    * Se retornara un booleano de True o False segun el resultado evaluado.
    * @param   el caracter c que se va a evaluar
    * @return  un booleano de la evaluacion echa sobre el caracter
    */
    public boolean VocalFuerte (char c){
	switch (c) {
            case 'a': case 'á': case 'A': case 'Á': case 'à': case 'À':
            case 'e': case 'é': case 'E': case 'É': case 'è': case 'È':
            case 'í': case 'Í': case 'ì': case 'Ì':
            case 'o': case 'ó': case 'O': case 'Ó': case 'ò': case 'Ò':
            case 'ú': case 'Ú': case 'ù': case 'Ù':
	
            return true;
	}
	
        return false;
    }

    /**
    * Determina si c no es una vocal.
    * El argumento es el caracter c.
    * Se retornara un booleano de True o False segun el resultado evaluado.
    * @param   el caracter c que se va a evaluar
    * @return  un booleano de la evaluacion echa sobre el caracter
    */   
    public boolean esConsonante (char c){
        if (!VocalFuerte(c)){
            switch (c){
                // Vocal débil
		case 'i': case 'I': 
		case 'u': case 'U': case 'ü': case 'Ü':
		
                return false;
            }

            return true;
	}
       
        return false;
    }

    /**
    * Determina si se forma un hiato.
    * Se retornara un booleano de True o False segun el resultado evaluado.
    * @return  un booleano de la evaluacion sobre un atributo
    */
    public boolean Hiato(){
	char tildado = ultPal.charAt(letraTildada);
        // Sólo es posible que haya hiato si hay tilde
	if ((letraTildada > 1) && (ultPal.charAt(letraTildada - 1) == 'u') && (ultPal.charAt(letraTildada- 2) == 'q'))
            return false; // La 'u' de "qu" no forma hiato
        
	// El caracter central de un hiato debe ser una vocal cerrada con tilde
	if ((tildado == 'í') || (tildado == 'ì') || (tildado == 'ú') || (tildado == 'ù')) {
            if ((letraTildada > 0) && VocalFuerte(ultPal.charAt(letraTildada - 1))) return true;
            if ((letraTildada < (lonPal - 1)) && VocalFuerte(ultPal.charAt(letraTildada + 1))) return true;		
	}

	return false;
    }

    /**
    * Determina si la silaba pal empieza en po y avanza pos hasta la posicion siguiente.
    * Los argumentos son una silaba y una posicion.
    * Se retornara una posicion.
    * @param  una silaba de una palabra
    * @param una posicion especifica
    * @return la posicion del ataque
    */  
    int Ataque (String pal, int pos) {
    // Se considera que todas las consonantes iniciales forman parte del ataque
        char ultimaConsonante = 'a';
        while ((pos < lonPal) && ((esConsonante(pal.charAt(pos))) && (pal.charAt(pos) != 'y'))) {
            ultimaConsonante = pal.charAt(pos);
            pos++;
	}
	// (q | g) + u (ejemplo: queso, gueto)
        if (pos < lonPal - 1)
            if (pal.charAt(pos) == 'u'){
                if (ultimaConsonante == 'q') pos++;
		else
                    if (ultimaConsonante == 'g') {
                        char letra = pal.charAt(pos + 1);
                        if ((letra == 'e') || (letra == 'é') || (letra == 'i') || (letra == 'í')) pos ++;
                    }
            }
            // La u con diéresis se añade a la consonante
            else { 					
		if (((char) pal.charAt(pos) == 'ü') || ((char) pal.charAt(pos) == 'Ü'))
                    if (ultimaConsonante == 'g') pos++;
            }

        return pos;
    }

   /**
    * Determina si el nucleo de la silaba cuyo ataque termina en pos -1, y si esto ocurre 
    * avanza la posicion siguiente hasta el final del nucleo 
    * Los argumentos son una silaba y una posicion.
    * Se retornara una posicion.
    * @param  una silaba de una palabra
    * @param una posicion especifica
    * @return la posicion donde termina el nucleo
    */  
    public int Nucleo(String pal, int pos){
	int anterior = 0;					// Sirve para saber el tipo de vocal anterior cuando hay dos seguidas
								// 0 = fuerte
								// 1 = débil acentuada
								// 2 = débil
	if (pos >= lonPal) return pos; 				// ¡¿No tiene núcleo?!
								// Se salta una 'y' al principio del núcleo, considerándola consonante
        if (pal.charAt(pos) == 'y') pos++;
 
			// Primera vocal	
        if (pos < lonPal) {
            char c = pal.charAt(pos);
            switch (c) {
		// Vocal fuerte o débil acentuada		
		case 'á': case 'Á': case 'à': case 'À':
                case 'é': case 'É': case 'è': case 'È':
                case 'ó':case 'Ó': case 'ò': case 'Ò':
		letraTildada = pos;
 		encTonica    = true;
		anterior = 0;
		pos++;
                
                break;
				// Vocal fuerte
		case 'a': case 'A':
		case 'e': case 'E':
		case 'o': case 'O':
		anterior = 0;
		pos++;
		break;
		// Vocal débil acentuada, rompe cualquier posible diptongo
		case 'í': case 'Í': case 'ì': case 'Ì':
		case 'ú': case 'Ú': case 'ù': case 'Ù': case 'ü': case 'Ü':
                letraTildada = pos;
		anterior = 1;
		pos++;
		encTonica = true;
		
                return pos; 
		// Vocal débil
		case 'i': case 'I':
		case 'u': case 'U':
		anterior = 2;
		pos++;
		
                break;
            }
	}
	
	// 'h' intercalada en el núcleo, no condiciona diptongos o hiatos
	boolean hache = false;
	if (pos < lonPal) {
            if (pal.charAt(pos) == 'h') {
		pos++;
		hache = true;
            }
	}
	// Segunda vocal
	if (pos < lonPal) {
            char c = pal.charAt(pos);
            switch (c) {
            	// Vocal fuerte o débil acentuada
                case 'á': case 'Á': case 'à': case 'À':
		case 'é': case 'É': case 'è': case 'È':
		case 'ó':case 'Ó': case 'ò': case 'Ò':
		letraTildada = pos;
                if(anterior != 0){
                    encTonica    = true;
		}
		if(anterior == 0){    		// Dos vocales fuertes no forman silaba
                    if (hache) pos--;
                        return pos;
		}
                else{
                    pos++;
		}

                break;
		// Vocal fuerte 
		case 'a': case 'A':
		case 'e': case 'E':
		case 'o': case 'O':	
		if(anterior == 0){    		// Dos vocales fuertes no forman silaba
                    if(hache) pos--;
			return pos;
		}
		else{
                    pos++;
		}
				
		break;

		// Vocal débil acentuada, no puede haber triptongo, pero si diptongo
		case 'í': case 'Í': case 'ì': case 'Ì':
		case 'ú': case 'Ú': case 'ù': case 'Ù':
		letraTildada = pos;
		if (anterior != 0) {  		// Se forma diptongo
                    encTonica    = true;
                    pos++;
                }
		else
                    if (hache) pos--;
                        return pos;
		// Vocal débil
		case 'i': case 'I':
		case 'u': case 'U': case 'ü': case 'Ü':
		if (pos < lonPal - 1) { // ¿Hay tercera vocal?
                    char siguiente = pal.charAt(pos + 1);
                    if(!esConsonante (siguiente)){
			char letraAnterior = pal.charAt(pos - 1);
                        if (letraAnterior == 'h') pos--;
                        return pos;
                }
            }

            	// dos vocales débiles iguales no forman diptongo
                if (pal.charAt(pos) != pal.charAt(pos - 1)) pos++;
		return pos;  			// Es un diptongo plano o descendente	
            }
	}
						// ¿tercera vocal?
	
	if (pos < lonPal) {
            char c = pal.charAt(pos);
            if ((c == 'i') || (c == 'u')) { 	// Vocal débil
		pos++;
		return pos;  			// Es un triptongo	
            }
	}
    
        return pos;
    }
    
   /**
    * Determina si la coda de la silaba cuyo ataque termina en pos -1, y si esto ocurre 
    * avanza la posicion siguiente hasta el final de la coda 
    * Los argumentos son una silaba y una posicion.
    * Se retornara una posicion.
    * @param  una silaba de una palabra
    * @param una posicion especifica
    * @return la posicion donde termina la coda
    */ 
    public int Coda (String pal, int pos) {	
	if ((pos >= lonPal) || (!esConsonante(pal.charAt(pos))))
            return pos; // No hay coda
	else {
            if (pos == lonPal - 1){ // Final de palabra
		pos++;
		return pos;
            }
            
        // Si sólo hay una consonante entre vocales, pertenece a la siguiente silaba
        if(!esConsonante (pal.charAt(pos + 1))) return pos;
		char c1 = pal.charAt(pos);
		char c2 = pal.charAt(pos + 1);	
	// ¿Existe posibilidad de una tercera consonante consecutina?	
        if ((pos < lonPal - 2)){
		char c3 = pal.charAt(pos + 2);		
		if (!esConsonante (c3)) { // No hay tercera consonante
						// Los grupos ll, lh, ph, ch y rr comienzan silaba
                    if ((c1 == 'l') && (c2 == 'l')) return pos;
                    if ((c1 == 'c') && (c2 == 'h')) return pos;
                    if ((c1 == 'r') && (c2 == 'r')) return pos;
                    
                    ///////// grupos nh, sh, rh, hl son ajenos al español(DPD)
                    if ((c1 != 's') && (c1 != 'r') &&(c2 == 'h'))
			return pos;

                    // Si la y está precedida por s, l, r, n o c (consonantes alveolares),
                    // una nueva silaba empieza en la consonante previa, si no, empieza en la y
				
                    if((c2 == 'y')){
			if ((c1 == 's') || (c1 == 'l') || (c1 == 'r') || (c1 == 'n') || (c1 == 'c'))
                            return pos;
                        pos++;
                        return pos;
                    }

                    // gkbvpft + l

                    if ((((c1 == 'b')||(c1 == 'v')||(c1 == 'c')||(c1 == 'k')||(c1 == 'f')||(c1 == 'g')||(c1 == 'p')||(c1 == 't')) && (c2 == 'l'))) {
			return pos;
                    }

                    // gkdtbvpf + r

                    if ((((c1 == 'b')||(c1 == 'v')||(c1 == 'c')||(c1 == 'd')||(c1 == 'k')||(c1 == 'f')||(c1 == 'g')||(c1 == 'p')||(c1 == 't')) && (c2 == 'r'))){
                        return pos;
                    }

                    pos++;
                    return pos;
		}
		else{ 					// Hay tercera consonante
                    if ((pos + 3) == lonPal) { 		// Tres consonantes al final ¿palabras extranjeras?
			if ((c2 == 'y')) { 		// 'y' funciona como vocal
                            if ((c1 == 's') || (c1 == 'l') || (c1 == 'r') || (c1 == 'n') || (c1 == 'c'))
				return pos;
                        }
			if (c3 == 'y'){ 		// 'y' final funciona como vocal con c2
                            pos++;
			}
			else {				// Tres consonantes al final ¿palabras extranjeras?
                            pos += 3;
			}
			
                        return pos;
                    }

                    if ((c2 == 'y')){ 			// 'y' funciona como vocal
			if ((c1 == 's') || (c1 == 'l') || (c1 == 'r') || (c1 == 'n') || (c1 == 'c'))
                            return pos;
						
			pos++;
                        return pos;
                    }

                    // Los grupos pt, ct, cn, ps, mn, gn, ft, pn, cz, tz, ts comienzan silaba (Bezos)
				
                    if ((c2 == 'p') && (c3 == 't') ||(c2 == 'c') && (c3 == 't') ||(c2 == 'c') && (c3 == 'n') ||(c2 == 'p') && (c3 == 's') ||
			(c2 == 'm') && (c3 == 'n') ||(c2 == 'g') && (c3 == 'n') ||(c2 == 'f') && (c3 == 't') ||(c2 == 'p') && (c3 == 'n') ||
			(c2 == 'c') && (c3 == 'z') ||(c2 == 't') && (c3 == 's') ||(c2 == 't') && (c3 == 's')){
			
                        pos++;
			return pos;
                    }
                    // Los grupos consonánticos formados por una consonante
                    // seguida de 'l' o 'r' no pueden separarse y siempre inician
                    // sílaba 
                    if ((c3 == 'l') || (c3 == 'r') ||((c2 == 'c') && (c3 == 'h')) ||(c3 == 'y')) {                   // 'y' funciona como vocal
			pos++;  			// Siguiente sílaba empieza en c2
                    }
                    else 
			pos += 2; 			// c3 inicia la siguiente sílaba
                }
            }
            else{
		if ((c2 == 'y')) return pos;
		pos +=2; 				// La palabra acaba con dos consonantes
            }
	}
	
        return pos;
    }

    /**
    * Devuelve un array con las posiciones de inicio de las silabas de ulPal  
    */ 
    public void PosicionSilabas () {
	posiciones.clear();
                
	lonPal       = ultPal.length();
	encTonica    = false;
	tonica       = 0;
	numSil       = 0;
	letraTildada = -1;

	// Se recorre la palabra buscando las sílabas
        for (int actPos = 0; actPos < lonPal; ){
            numSil++;
            posiciones.add(actPos);
                     			// Marca el principio de la silaba actual
					// Las sílabas constan de tres partes: ataque, núcleo y coda

            actPos = Ataque(ultPal, actPos);
            actPos = Nucleo(ultPal, actPos);
            actPos = Coda(ultPal, actPos);
            if ((encTonica) && (tonica == 0)) tonica = numSil; // Marca la silaba tónica
        }
	
					// Si no se ha encontrado la sílaba tónica (no hay tilde), se determina en base a
					// las reglas de acentuación
	if (!encTonica) {
            if (numSil < 2) tonica = numSil;  	// Monosílabos
                else{                            // Polisílabos
                    char letraFinal    = ultPal.charAt(lonPal - 1);
                    char letraAnterior = ultPal.charAt(lonPal - 2);
                    if ((!esConsonante (letraFinal) || (letraFinal == 'y')) ||(((letraFinal == 'n') || (letraFinal == 's') && !esConsonante (letraAnterior))))
			tonica = numSil - 1;	// Palabra llana
                    else
			tonica = numSil;	// Palabra aguda
		}
            }
        }
}

