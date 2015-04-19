/********************************************************************************
 * Separador silábico para el Español                                           *
 * Autor  : Zenón J. Hernández Figueroa                                         *
 *          Gustavo Rodríguez Rodríguez                                         *
 *          Francisco Carreras Riudavets                                        *
 * Version: 1.1                                                                 *
 * Date   : 12-02-2010                                                          *
 *                                                                              *
 *------------------------------------------------------------------------------*
 * Copyright (C) 2009 TIP: Text & Information Processing                        *
 * (http://tip.dis.ulpgc.es)                                                    *
 * All rights reserved.                                                         *
 *                                                                              *
 * This file is part of SeparatorOfSyllables                                    *
 * SeparatorOfSyllables is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU General Public License                  *
 * as published by the Free Software Foundation; either version 3               *
 * of the License, or (at your option) any later version.                       *
 *                                                                              *
 * This program is distributed in the hope that it will be useful,              *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of               *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                *
 * GNU General Public License for more details.                                 *
 *                                                                              *
 * You should have received a copy of the GNU General Public License            *
 * along with this program; if not, write to the Free Software                  *
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307,USA.   *
 *                                                                              *
 * The "GNU General Public License" (GPL) is available at                       *
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html                        *
 *                                                                              *
 * When citing this resource, please use the following reference:               *
 * Hernández-Figueroa, Z; Rodríguez-Rodríguez, G; Carreras-Riudavets, F (2009). *
 * Separador de sílabas del español - Silabeador TIP.                           *
 * Available at http://tip.dis.ulpgc.es                                         *
 ********************************************************************************/

#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include "syllable.h"

/***************/
/* Constructor */
/***************/

SeparatorOfSyllables::SeparatorOfSyllables () {
	lastWord[0] = '\0';
}

/*********************************************/
/* Returns the number of syllables in a word */
/*********************************************/

int SeparatorOfSyllables::NumberOfSyllables (const char *word) {
	Process (word);
	return numSyl;
}

/****************************************************************/
/* Returns an array with the start positions of every syllables */
/****************************************************************/

int * SeparatorOfSyllables::SyllablePositions (const char *word) {
	Process (word);
	return positions;
}	

/*************************************************/
/* Returns the position of the stressed syllable */
/*************************************************/
	
int SeparatorOfSyllables::StressedSyllable (const char *word) {
	Process (word);
	return stressed;
}

/*********************************************************/
/*********************************************************/
/**             PRIVATE OPERATIONS                      **/
/*********************************************************/
/*********************************************************/

/*************************************************************/
/* Determines whether to call SyllablePositions              */
/* (if word is the same as last processed, it is not needed) */
/*************************************************************/

void SeparatorOfSyllables::Process (const char *word) {
	if (strcmp (word, lastWord) != 0) {
		strcpy (lastWord, word);
    	        SyllablePositions ();
	}
}

/**************************************************************/
/* Returns an array with the start positions of the syllables */
/**************************************************************/
	
void SeparatorOfSyllables::SyllablePositions () {
	wordLength      = strlen (lastWord);
	stressedFound   = false;
	stressed        = 0;
	numSyl       = 0;
	letterAccent = -1;

	// It looks for syllables in the word

	for (int i = 0; i < wordLength;) {
		positions [numSyl++] = i;  // It marks the beginning of the current syllable

		// Syllables consist of three parts: onset, nucleus and coda
		
		Onset   (lastWord, i);
		Nucleus (lastWord, i);
		Coda    (lastWord, i);
		
		if ((stressedFound) && (stressed == 0)) stressed = numSyl; // It marks the stressed syllable
	}
	
	// If the word has not written accent, the stressed syllable is determined
	// according to the stress rules
	
	if (!stressedFound) {
		if (numSyl < 2) stressed = numSyl;  // Monosyllables
		else {                              // Polysyllables
			char endLetter      = tolower (lastWord [wordLength - 1]);
			char previousLetter = tolower (lastWord [wordLength - 2]);

			if ((!IsConsonant (endLetter) || (endLetter == 'y')) ||
			    (((endLetter == 'n') || (endLetter == 's') && !IsConsonant (previousLetter))))
				stressed = numSyl - 1;	// Stressed penultimate syllable
			else
				stressed = numSyl;		// Stressed last syllable
		}
	}
	
	positions [numSyl] = -1;  // It marks the end of found syllables
}

/************************************/
/* Determines whether hiatus exists */
/************************************/

bool SeparatorOfSyllables::Hiatus () {
	char accented = tolower (lastWord [letterAccent]);
	
	if ((letterAccent > 1) &&  // Hiatus is only possible if there is accent
	    (tolower(lastWord [letterAccent - 1]) == 'u') &&
	    (tolower(lastWord [letterAccent - 2]) == 'q'))
	    return false; // The 'u' letter belonging "qu" doesn't form hiatus

	// The central character of a hiatus must be a close-vowel with written accent

	if ((accented == 'í') || (accented == 'ì') || (accented == 'ú') || (accented == 'ù')) {

		if ((letterAccent > 0) && OpenVowel (lastWord [letterAccent - 1])) return true;
		    
		if ((letterAccent < (wordLength - 1)) && OpenVowel (lastWord [letterAccent + 1])) return true;		
	}
	    
	return false;
}

/********************************************************************/
/* Determines the onset of the current syllable whose begins in pos */
/* and pos is changed to the follow position after end of onset     */
/********************************************************************/

void SeparatorOfSyllables::Onset (const char *pal, int &pos) {
	// Every initial consonant belongs to the onset
	
	char lastConsonant = 'a';
	while ((pos < wordLength) && ((IsConsonant (pal [pos])) && (tolower (pal [pos]) != 'y'))) {
		lastConsonant = tolower (pal [pos]);
		pos++;
	}
	
	// (q | g) + u (example: queso, gueto)
	
	if (pos < wordLength - 1)
		if (tolower (pal [pos]) == 'u') {
			if (lastConsonant == 'q') pos++;
			else
				if (lastConsonant == 'g') {
					char letter = tolower (pal [pos + 1]);
					if ((letter == 'e') || (letter == 'é') ||
                                            (letter == 'i') || (letter == 'í')) pos ++;
				}
		}
		else { // The 'u' with diaeresis is added to the consonant
			if ((pal [pos] == 'ü') || (pal [pos] == 'Ü'))
				if (lastConsonant == 'g') pos++;
		}
}

/****************************************************************************/
/* Determines the nucleus of current syllable whose onset ending on pos - 1 */
/* and changes pos to the follow position behind of nucleus                 */
/****************************************************************************/

void SeparatorOfSyllables::Nucleus (const char *pal, int &pos) {
	int previous; // Saves the type of previous vowel when two vowels together exists
	              // 0 = open
	              // 1 = close with written accent
	              // 2 = close

	if (pos >= wordLength) return; // ¡¿Doesn't it have nucleus?!

	// Jumps a letter 'y' to the starting of nucleus, it is as consonant
	            
	if (tolower(pal [pos]) == 'y') pos++;
	 
	// First vowel
	
	if (pos < wordLength) {
		switch (pal [pos]) {
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
			break;
		// Close-vowel
		case 'i': case 'I':
		case 'u': case 'U':
			previous = 2;
			pos++;
			break;
		}
	}
	
	// If 'h' has been inserted in the nucleus then it doesn't determine diphthong neither hiatus

	bool aitch = false;
	if (pos < wordLength) {
		if (tolower(pal [pos]) == 'h') {
			pos++;
			aitch = true;
		}
	}

	// Second vowel

	if (pos < wordLength) {
		switch (pal [pos]) {
		// Open-vowel with written accent
		case 'á': case 'Á': case 'à': case 'À':
		case 'é': case 'É': case 'è': case 'È':
		case 'ó': case 'Ó': case 'ò': case 'Ò':
			letterAccent = pos;
			if (previous != 0) {
				stressedFound    = true;
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
			if (pos < wordLength - 1) { // ¿Is there a third vowel?
				if (!IsConsonant (pal [pos + 1])) {
					if (tolower(pal [pos - 1]) == 'h') pos--;
					return;
				}
			}

			// Two equals close-vowels don't form diphthong
			if (pal [pos] != pal [pos - 1]) pos++; 

			return;  // It is a descendent diphthong
			break;
		}
	}
	
	// Third vowel?
	
	if (pos < wordLength) {
		if ((tolower(pal [pos]) == 'i') || (tolower(pal [pos]) == 'u')) { // Close-vowel
			pos++;
			return;  // It is a triphthong	
		}
	}
}

/*****************************************************************************/
/* Determines the coda of the current syllable whose nucleus ends in pos - 1 */
/* and changes pos to follow position to the end of the coda                 */
/*****************************************************************************/
                                       
void SeparatorOfSyllables::Coda (const char *pal, int &pos) {	
	if ((pos >= wordLength) || (!IsConsonant (pal [pos])))
		return; // Syllable hasn't coda
	else {
		if (pos == wordLength - 1) // End of word
		{
			pos++;
			return;
		}

		// If there is only a consonant between vowels, it belongs to the following syllable

		if (!IsConsonant (pal [pos + 1])) return;

		char c1 = tolower (pal [pos]);
		char c2 = tolower (pal [pos + 1]);
		
		// Has the syllable a third consecutive consonant?
		
		if ((pos < wordLength - 2)) {
			char c3 = tolower (pal [pos + 2]);
			
			if (!IsConsonant (c3)) { // There isn't third consonant
				// The groups ll, ch and rr begin a syllable

				if ((c1 == 'l') && (c2 == 'l')) return;
				if ((c1 == 'c') && (c2 == 'h')) return;
				if ((c1 == 'r') && (c2 == 'r')) return;

				// A consonant + 'h' begins a syllable, except for groups sh and rh
				if ((c1 != 's') && (c1 != 'r') &&
					(c2 == 'h'))
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
				if ((pos + 3) == wordLength) { // Three consonants to the end, foreign words?
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

/***************************************************************************/
/* Determines whether c is a open-vowel or close vowel with written accent */
/***************************************************************************/

bool SeparatorOfSyllables::OpenVowel (char c) {
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

//**********************************/
/* Determines whether c is a vowel */
/***********************************/

bool SeparatorOfSyllables::IsConsonant (char c) {
	switch (c) {
	// Open-vowel or close-vowel with written accent
	case 'a': case 'á': case 'A': case 'Á': case 'à': case 'À':
	case 'e': case 'é': case 'E': case 'É': case 'è': case 'È':
	case 'í': case 'Í': case 'ì': case 'Ì':
	case 'o': case 'ó': case 'O': case 'Ó': case 'ò': case 'Ò':
	case 'ú': case 'Ú': case 'ù': case 'Ù':
	// Close-vowel 
	case 'i': case 'I':
	case 'u': case 'U':
	case 'ü': case 'Ü':
		return false;
		break;
	}

	return true;
}


