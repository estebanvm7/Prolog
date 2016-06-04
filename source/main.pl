oracion1 --> pronombres(Gp, Mp, Tp), articulos(Ga, Ma, Ta), sustantivos(Gs, Ms, Ts), {N is Mp + Ma + Ms}, {N >= 3},{7 >= N }, {Gp = Ga}, {Ga = Gs}, {Tp = Ta}, {Ta = Ts}.
oracion2 --> verbo(Mv, Tv), pronombres(Gp, Mp, Tp), adjetivos(Gad, Mad, Tad), sustantivos(Gs, Ms, Ts), {N is Mv + Mp + Mad + Ms},{N >= 5},{9 >= N }, {Gp = Gad}, {Gad = Gs}, {Tv = Tp}, {Tp = Tad}, {Tad = Ts}.
oracion3 --> articulos(Ga, Ma, Ta), sustantivos(Gs, Ms, Ts), verbo(Mv,Tv), {Ga = Gs}, {N is Ma + Ms + Mv}, {N >= 3},{7 >= N }, {Ta = Ts}, {Ts = Tv}.
cambio -->[#].
haiku --> cambio, oracion1, cambio, oracion2, cambio, oracion3.
articulos(G,M,T) --> [el], {G=m}, {M is 1}, {T=s}.
articulos(G,M,T) --> [el], {G=m}, {M is 1}, {T=s}.
articulos(G,M,T) --> [la], {G=f}, {M is 1}, {T=s}.
articulos(G,M,T) --> [la], {G=f}, {M is 1}, {T=s}.
articulos(G,M,T) --> [unas], {G=f}, {M is 2}, {T=p}.
adjetivos(G,M,T) --> [fea], {G=f}, {M is 2}, {T=s}.
adjetivos(G,M,T) --> [real], {G=m}, {M is 2}, {T=s}.
adjetivos(G,M,T) --> [triste], {G=f}, {M is 2}, {T=s}.
adjetivos(G,M,T) --> [aceleradas], {G=f}, {M is 5}, {T=p}.
adjetivos(G,M,T) --> [blanco], {G=m}, {M is 2}, {T=s}.
preposiciones(M) --> [versus], {M is 2}.
preposiciones(M) --> [sobre], {M is 2}.
preposiciones(M) --> [según], {M is 2}.
preposiciones(M) --> [de], {M is 1}.
preposiciones(M) --> [con], {M is 1}.
pronombres(G,M,T) --> [ése], {G=m}, {M is 2}, {T=s}.
pronombres(G,M,T) --> [nuestro], {G=m}, {M is 2}, {T=s}.
pronombres(G,M,T) --> [ninguno], {G=m}, {M is 3}, {T=s}.
pronombres(G,M,T) --> [nuestras], {G=f}, {M is 2}, {T=p}.
pronombres(G,M,T) --> [mía], {G=f}, {M is 2}, {T=s}.
sustantivos(G,M,T) --> [maicol], {G=m}, {M is 2}, {T=s}.
sustantivos(G,M,T) --> [república], {G=f}, {M is 4}, {T=s}.
sustantivos(G,M,T) --> [juanete], {G=m}, {M is 3}, {T=s}.
sustantivos(G,M,T) --> [descanso], {G=m}, {M is 3}, {T=s}.
sustantivos(G,M,T) --> [elmo], {G=m}, {M is 2}, {T=s}.
verbo(M,T) --> [fue], {M is 1}, {T=s}.
verbo(M,T) --> [limpiaste], {M is 3}, {T=s}.
verbo(M,T) --> [llenar], {M is 2}, {T=s}.
verbo(M,T) --> [contábamos], {M is 4}, {T=p}.
verbo(M,T) --> [traer], {M is 2}, {T=s}.
