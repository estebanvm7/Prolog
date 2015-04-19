/********************************************************************************
 * Separator of syllables for Spanaihs                                          *
 * Authors  : Zenón J. Hernández Figueroa                                       *
 *            Gustavo Rodríguez Rodríguez                                       *
 *            Francisco Carreras Riudavets                                      *
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
 * Separador de sílabas del español - Silabeador TIP.                               *
 * Available at http://tip.dis.ulpgc.es                                         *
 ********************************************************************************/

#ifndef SYLLABLE_H_
#define SYLLABLE_H_

#define MAX_SYLLABLES 20
#define MAX_WORD_LENGTH 50

class SeparatorOfSyllables {
	int  wordLength;		            // Word length
	int  numSyl;                        // Number of syllables of the word
	int  stressed;                      // Position of stressed syllable (start at 1)
	bool stressedFound;                 // Whether the stressed syllable have been found
	int  letterAccent;                  // Position of letter with accent, if any
	int  positions [MAX_SYLLABLES + 1]; // Starting postions of the syllables
	char lastWord [MAX_WORD_LENGTH + 1];// Last word processed, saved to avoid
                                        // repeating the process if it is requested

    // The general structure of a syllable consists of the following segments:
    // Onset (obligatory in some languages, optional or even restricted in others)
    // Nucleus (obligatory in all languages)
    // Coda (optional in some languages, highly restricted or prohibited in others)
	// (http://en.wikipedia.org/wiki/Syllable)
	// (http://es.wikipedia.org/wiki/Sílaba)

	void Onset   (const char *, int &);
	void Nucleus (const char *, int &);
	void Coda    (const char *, int &);

	bool Hiatus ();
	
	void Process (const char *);

	bool OpenVowel (char);
	bool IsConsonant (char);
	
	void  SyllablePositions ();
public:
	SeparatorOfSyllables ();
	
	/*********************************************/
	/* Returns the number of syllables in a word */
	/*********************************************/
	
	int NumberOfSyllables (const char *);

	/****************************************************************/
	/* Returns an array with the start positions of every syllables */
	/****************************************************************/

	int * SyllablePositions (const char *);

	/*************************************************/
	/* Returns the position of the stressed syllable */
	/*************************************************/

    int StressedSyllable (const char *);
};

#endif /*SYLLABLE_H_*/
