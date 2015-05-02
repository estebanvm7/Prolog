oracion --> articulo(Ga, Ma, Ta), sustantivo(Gs, Ms, Ts), verbo(Mv, Tv), adjetivo(Gad, Mad, Tad), {N is Ma + Ms + Mv + Mad},
	 {N >= 5},{9 >= N }, {Ga = Gs}, {Gs = Gad}, {Ta = Ts}, {Ts = Tv}, {Tv = Tad}.

articulo(G, M, T) --> [el], { G = masc}, {M is 1}, {T = sing}.
articulo(G, M, T) --> [unos], { G = masc}, {M is 2}, {T = plu}.
articulo(G, M, T) --> [la], { G = fem}, {M is 1}, {T = sing}.
articulo(G, M, T) --> [unas], { G = fem}, {M is 2}, {T = plu}.
articulo(G, M, T) --> [los], { G = masc}, {M is 1}, {T = plu}.

sustantivo(G, M, T) --> [abuelos], {G = masc}, {M is 3}, {T = plu}.
sustantivo(G, M, T) --> [gallinas], {G = fem}, {M is 3}, {T = plu}.
sustantivo(G, M, T) --> [pareja], {G = fem}, {M is 3}, {T = sing}.
sustantivo(G, M, T) --> [quetzal], {G = masc}, {M is 2}, {T = sing}.
sustantivo(G, M, T) --> [padre], {G = masc}, {M is 2}, {T = sing}.
sustantivo(G, M, T) --> [lagunas], {G = fem}, {M is 3}, {T = plu}.

verbo(M, T) --> [pasea], {M is 3}, {T = sing}.
verbo(M, T) --> [pasean], {M is 3}, {T = plu}.
verbo(M, T) --> [contó], {M is 2}, {T = sing}.
verbo(M, T) --> [explicaban], {M is 4}, {T = plu}.
verbo(M, T) --> [corre], {M is 2}, {T = sing}.
verbo(M, T) --> [aprenderemos], {M is 5}, {T = plu}.

adjetivo(G, M, T) --> [callado], {G = masc}, {M is 3}, {T = sing}.
adjetivo(G, M, T) --> [cansados], {G = masc}, {M is 3}, {T = plu}.
adjetivo(G, M, T) --> [alegres], {G = masc}, {M is 3}, {T = plu}.
adjetivo(G, M, T) --> [alegres], {G = fem}, {M is 3}, {T = plu}.
adjetivo(G, M, T) --> [sumiso], {G = masc}, {M is 3}, {T = sing}.
adjetivo(G, M, T) --> [sumisa], {G = fem}, {M is 3}, {T = sing}.
adjetivo(G, M, T) --> [toxico], {G = masc}, {M is 3}, {T = sing}.
