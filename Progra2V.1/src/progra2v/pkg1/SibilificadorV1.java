/*Hernández-Figueroa, Z; Rodríguez-Rodríguez, G; Carreras-Riudavets, F (2009). *
Separador de sílabas del español - Silabeador TIP.                               *
Available at http://tip.dis.ulpgc.es */   

package progra2v.pkg1;


public class SibilificadorV1{
    int  wordLength;		            // Word length
    int  numSyl;                        // Number of syllables of the word
    int  stressed;                      // Position of stressed syllable (start at 1)
    boolean stressedFound;                 // Whether the stressed syllable have been found
    int  letterAccent;                  // Position of letter with accent, if any
    int[]  positions = new int[21]; // Starting postions of the syllables
    String[] lastWord = new String[51];// Last word processed, saved to avoid
       
    private void SeparadorSilabas() {
	lastWord[0] = '\0';
    }
    private int NumeroSilabas(String word) {
	Proceso(word);
	return numSyl;
    }
    private int SylablaPosicion(String word) {
	Proceso(word);
	return positions;
    }	
    private int Silaba(String word) {
	Proceso(word);
	return stressed;
    }
    private void Proceso(String word) {
	if (word.equals(lastWord)) {
		lastWord = word;
    	        PosicionSilaba();
	}
    }
    private void PosicionSilaba () {
	wordLength      = lastWord.length;
	stressedFound   = false;
	stressed        = 0;
	numSyl       = 0;
	letterAccent = -1;

	// It looks for syllables in the word

	for (int i = 0; i < wordLength;) {
		positions [numSyl++] = i;  // It marks the beginning of the current syllable

		// Syllables consist of three parts: onset, nucleus and coda
		
		Inicio(lastWord, i);
		Nucleo(lastWord, i);
		Coda(lastWord, i);
		
		if ((stressedFound) && (stressed == 0)) stressed = numSyl; // It marks the stressed syllable
	}
	
	// If the word has not written accent, the stressed syllable is determined
	// according to the stress rules
	
	if (!stressedFound) {
		if (numSyl < 2) stressed = numSyl;  // Monosyllables
		else {                              // Polysyllables
			char endLetter      = tolower (lastWord [wordLength - 1]);
			char previousLetter = tolower (lastWord [wordLength - 2]);

			if ((!EsConsonante(endLetter) || (endLetter == 'y')) ||
			    (((endLetter == 'n') || (endLetter == 's') && !EsConsonante(previousLetter))))
				stressed = numSyl - 1;	// Stressed penultimate syllable
			else
				stressed = numSyl;		// Stressed last syllable
		}
	}
	
	positions [numSyl] = -1;  // It marks the end of found syllables
    }
    
    private boolean Hiakus() {
	char accented = tolower(lastWord [letterAccent]);
	
	if ((letterAccent > 1) &&  // Hiatus is only possible if there is accent
	    (tolower(lastWord[letterAccent - 1]) == 'u') &&
	    (tolower(lastWord [letterAccent - 2]) == 'q'))
	    return false; // The 'u' letter belonging "qu" doesn't form hiatus

	// The central character of a hiatus must be a close-vowel with written accent

	if ((accented == 'í') || (accented == 'ì') || (accented == 'ú') || (accented == 'ù')) {

		if ((letterAccent > 0) && vocalAcentuada(lastWord [letterAccent - 1])) return true;
		    
		if ((letterAccent < (wordLength - 1)) && vocalAcentuada(lastWord [letterAccent + 1])) return true;		
	}
	    
	return false;
    }
    private void Inicio(String palabra, int pos) {
	// Every initial consonant belongs to the onset
	
	char lastConsonant = 'a';
	while ((pos < palabra.length()) && ((EsConsonante(palabra.charAt(pos) )) && (palabra.toLowerCase().charAt(pos) != 'y'))) {
		lastConsonant = palabra.toLowerCase().charAt(pos);
		pos++;
	}
	
	// (q | g) + u (example: queso, gueto)
	
	if (pos < wordLength - 1)
		if (palabra.toLowerCase().charAt(pos) == 'u') {
			if (lastConsonant == 'q') pos++;
			else
				if (lastConsonant == 'g') {
					char letter = palabra.toLowerCase().charAt(pos +1);
					if ((letter == 'e') || (letter == 'é') ||
                                            (letter == 'i') || (letter == 'í')) pos ++;
				}
		}
		else { // The 'u' with diaeresis is added to the consonant
			if ((palabra.charAt(pos) == 'ü') || (palabra.charAt(pos) == 'Ü'))
				if (lastConsonant == 'g') pos++;
		}
    }    
    private void Nucleo(String palabra, int pos){
	int previous = 0; // Saves the type of previous vowel when two vowels together exists
	              // 0 = open
	              // 1 = close with written accent
	              // 2 = close

	if (pos >= palabra.length()) return; // ¡¿Doesn't it have nucleus?!

	// Jumps a letter 'y' to the starting of nucleus, it is as consonant
	            
	if (palabra.toLowerCase().charAt(pos) == 'y') pos++;
	 
	// First vowel
	
	if (pos < palabra.charAt(pos)) {
		switch (palabra.charAt(pos)) {
		// Open-vowel or close-vowel with written accent
		case 'á': case 'Á': case 'à': case 'À':
		case 'é': case 'É': case 'è': case 'È':
		case 'ó': case 'Ó': case 'ò': case 'Ò':
			letterAccent = pos;
 			stressedFound   = true;
 		// Open-vowel
		case 'a': case 'A':
		case 'e': case 'E':
		case 'o': case 'O':
			previous = 0;
			pos++;
			break;
		// Close-vowel with written accent breaks some possible diphthong
		case 'í': case 'Í': case 'ì': case 'Ì':
		case 'ú': case 'Ú': case 'ù': case 'Ù': case 'ü': case 'Ü':
			letterAccent = pos;
			previous = 1;
			pos++;
			stressedFound = true;
			return;
			//break;
		// Close-vowel
		case 'i': case 'I':
		case 'u': case 'U':
			previous = 2;
			pos++;
			break;
		}
	}
	
	// If 'h' has been inserted in the nucleus then it doesn't determine diphthong neither hiatus

	boolean aitch = false;
	if (pos < palabra.length()) {
		if (palabra.toLowerCase().charAt(pos) == 'h') {
			pos++;
			aitch = true;
		}
	}

	// Second vowel

	if (pos < palabra.length()) {
		switch (palabra.charAt(pos)) {
		// Open-vowel with written accent
		case 'á': case 'Á': case 'à': case 'À':
		case 'é': case 'É': case 'è': case 'È':
		case 'ó': case 'Ó': case 'ò': case 'Ò':
			letterAccent = pos;
			if (previous != 0) {
				stressedFound = true;
			}
		// Open-vowel
		case 'a': case 'A':
		case 'e': case 'E':
		case 'o': case 'O':
			if (previous == 0) {    // Two open-vowels don't form syllable
				if (aitch) pos--;
				return;
			}
			else {
				pos++;
			}
				
			break;

		// Close-vowel with written accent, can't be a triphthong, but would be a diphthong
		case 'í': case 'Í': case 'ì': case 'Ì':
		case 'ú': case 'Ú': case 'ù': case 'Ù':
			letterAccent = pos;
			
			if (previous != 0) {  // Diphthong
				stressedFound    = true;
				pos++;
			}
			else
				if (aitch) pos--;
			
			return;
		// Close-vowel
		case 'i': case 'I':
		case 'u': case 'U': case 'ü': case 'Ü':
			if (pos < palabra.length() - 1) { // ¿Is there a third vowel?
				if (!EsConsonante(palabra.charAt(pos +1))) {
					if (palabra.toLowerCase().charAt(pos -1) == 'h') pos--;
					return;
				}
			}

			// Two equals close-vowels don't form diphthong
			if (palabra.charAt(pos)!= palabra.charAt(pos - 1)) pos++; 

			return;  // It is a descendent diphthong
			//break;
		}
	}
	
	// Third vowel?
	
	if (pos < palabra.length()) {
		if ((palabra.toLowerCase().charAt(pos) == 'i') || (palabra.toLowerCase().charAt(pos) == 'u')) { // Close-vowel
			pos++;
			return;  // It is a triphthong	
		}
	}
    }
    private void Coda(String palabra, int pos) {	
	if ((pos >= palabra.length()) || (!EsConsonante(palabra.charAt(pos)))){
		return; // Syllable hasn't coda
        }
        else{
            if (pos == palabra.length() - 1){
			pos++;
			return;
		}

            if (!EsConsonante(palabra.charAt(pos +1)))return;
                char c1 = palabra.toLowerCase().charAt(pos);
		char c2 = palabra.toLowerCase().charAt(pos + 1);

		
		if ((pos < palabra.length() - 2)) {
			char c3 = palabra.toLowerCase().charAt(pos + 2);
			if(!EsConsonante(c3)) { 
                        if((c1 == 'l') && (c2 == 'l')) return;
                        if((c1 == 'c') && (c2 == 'h')) return;
                        if((c1 == 'r') && (c2 == 'r')) return;
				// A consonant + 'h' begins a syllable, except for groups sh and rh
                        if((c1 != 's') && (c1 != 'r') && (c2 == 'h'))
					return;

				// If the letter 'y' is preceded by the some
                //      letter 's', 'l', 'r', 'n' or 'c' then
				//      a new syllable begins in the previous consonant
                // else it begins in the letter 'y'
				if ((c2 == 'y')) {
                                    if ((c1 == 's') || (c1 == 'l') || (c1 == 'r') || (c1 == 'n') || (c1 == 'c'))
					return;

					pos++;
                                    return;
				}

				// groups: gl - kl - bl - vl - pl - fl - tl

				if ((((c1 == 'b')||(c1 == 'v')||(c1 == 'c')||(c1 == 'k')||
				       (c1 == 'f')||(c1 == 'g')||(c1 == 'p')||(c1 == 't')) && 
				      (c2 == 'l')
				     )
				    ) {
					return;
				}

				// groups: gr - kr - dr - tr - br - vr - pr - fr

				if ((((c1 == 'b')||(c1 == 'v')||(c1 == 'c')||(c1 == 'd')||(c1 == 'k')||
			    	   (c1 == 'f')||(c1 == 'g')||(c1 == 'p')||(c1 == 't')) && 
			          (c2 == 'r')
			         )
			       ) {
					return;
				}

				pos++;
				return;
			}
			else { // There is a third consonant
				if ((pos + 3) == palabra.length()) { // Three consonants to the end, foreign words?
					if ((c2 == 'y')) {  // 'y' as vowel
						if ((c1 == 's') || (c1 == 'l') || (c1 == 'r') || (c1 == 'n') || (c1 == 'c'))
							return;
					}

					if (c3 == 'y') { // 'y' at the end as vowel with c2
						pos++;
					}
					else {	// Three consonants to the end, foreign words?
						pos += 3;
					}
					return;
				}

				if ((c2 == 'y')) { // 'y' as vowel
					if ((c1 == 's') || (c1 == 'l') || (c1 == 'r') || (c1 == 'n') || (c1 == 'c'))
						return;
						
					pos++;
					return;
				}

				// The groups pt, ct, cn, ps, mn, gn, ft, pn, cz, tz and ts begin a syllable
				// when preceded by other consonant

				if ((c2 == 'p') && (c3 == 't') ||
				    (c2 == 'c') && (c3 == 't') ||
					(c2 == 'c') && (c3 == 'n') ||
					(c2 == 'p') && (c3 == 's') ||
					(c2 == 'm') && (c3 == 'n') ||
					(c2 == 'g') && (c3 == 'n') ||
					(c2 == 'f') && (c3 == 't') ||
					(c2 == 'p') && (c3 == 'n') ||
					(c2 == 'c') && (c3 == 'z') ||
					(c2 == 't') && (c3 == 's') ||
					(c2 == 't') && (c3 == 's'))
				{
					pos++;
					return;
				}

				if ((c3 == 'l') || (c3 == 'r') ||    // The consonantal groups formed by a consonant
				                                     // following the letter 'l' or 'r' cann't be
				                                     // separated and they always begin syllable
					((c2 == 'c') && (c3 == 'h')) ||  // 'ch'
					(c3 == 'y')) {                   // 'y' as vowel
					pos++;  // Following syllable begins in c2
				}
				else
					pos += 2; // c3 begins the following syllable
			}
		}
		else {
			if ((c2 == 'y')) return;

			pos +=2; // The word ends with two consonants
		}
	}	
    }
    
    private boolean vocalAcentuada(char c) {
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
    private boolean EsConsonante(char c){
	switch (c){
            case 'a': case 'á': case 'A': case 'Á': case 'à': case 'À':
            case 'e': case 'é': case 'E': case 'É': case 'è': case 'È':
            case 'í': case 'Í': case 'ì': case 'Ì':
            case 'o': case 'ó': case 'O': case 'Ó': case 'ò': case 'Ò':
            case 'ú': case 'Ú': case 'ù': case 'Ù':
             
            case 'i': case 'I':
            case 'u': case 'U':
            case 'ü': case 'Ü':
                return false;
        //break;
	}
	return true;
    }

    public static void main(String[] args) {
        char valor = 'ó';
        char valor2 = 'a';
        SibilificadorV1 s = new SibilificadorV1();
        System.out.println(s.EsConsonante(valor));
        System.out.println(s.vocalAcentuada(valor2));     
    }
    
}
