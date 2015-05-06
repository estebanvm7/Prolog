oracion --> articulos(Ga, Ma, Ta), sustantivos(Gs, Ms, Ts), verbo(Mv,Tv), {Ga = Gs}, {N is Ma + Ms + Mv}, {N >= 3},{7 >= N }, {Ta = Ts}, {Ts = Tv}.
oracion --> sustantivos(Gs, Ms, Ts), sustantivos(Gsu, Msu, Tsu), {N is Ms + Msu}, {N >= 3},{7 >= N }, {Gs = Gsu}, {Ts = Tsu}.
oracion --> articulos(Ga, Ma, Ta), sustantivos(Gs, Ms, Ts), {N is Ma + Ms}, {N >= 3},{7 >= N }, {Ga = Gs}, {Ta = Ts}.
oracion --> pronombres(Gp, Mp, Tp), verbo(Mv, Tv), {N is Mp + Mv}, {Gp = Gp}, {N >= 3},{7 >= N }, {Tp = Tv}.
oracion --> sustantivos(Gs, Ms, Ts), articulos(Ga, Ma, Ta), adjetivos(Gad, Mad, Tad), {N is Ms + Ma + Mad}, {N >= 3},{7 >= N}, {Gs = Ga}, {Ga = Gad}, {Ts = Ta}, {Ta = Tad}.
oracion --> pronombres(Gp, Mp, Tp), verbo(Mv, Tv), sustantivos(Gs, Ms, Ts), {N is Mp + Mv + Ms}, {N >= 3},{7 >= N }, {Gp = Gs}, {Tp = Tv}, {Tv = Ts}.
oracion --> pronombres(Gp, Mp, Tp), articulos(Ga, Ma, Ta), sustantivos(Gs, Ms, Ts), {N is Mp + Ma + Ms}, {N >= 3},{7 >= N }, {Gp = Ga}, {Ga = Gs}, {Tp = Ta}, {Ta = Ts}.
oracion --> verbo(Mv, Tv), articulos(Ga, Ma, Ta), sustantivos(Gs, Ms, Ts), {N is Mv + Ma + Ms}, {N >= 3},{7 >= N }, {Ga = Gs}, {Tv = Ta}, {Ta = Ts}.
oracion --> verbo(Mv, Tv), sustantivos(Gs, Ms, Ts), {N is Mv + Ms}, {Gs = Gs}, {N >= 3},{7 >= N }, {Tv = Ts}.
