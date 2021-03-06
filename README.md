Creador de Haikus en Prolog
===========================
Equipo de trabajo
-----------------
- Esteban Vargas Mora.
- Mauricio Arce Fernandez.
- José Gustavo González González.

Especificación
--------------
Construir un programa en Java+Prolog que intente generar textos en español que cumplan con las características estructurales de un haiku, ejecutado desde linux.

¿Pero, qué es un haiku?
-----------------------
Un haiku es una forma de poesía originaria de Japón. Consiste de tres versos cortos, de 5, 7 y 5 sílabas cada uno. Los versos usualmente presentan la yuxtaposición de dos ideas o imágenes separadas por un término. La temática de los haikus 
usualmente profundiza en la admiración y entendimiento de la naturaleza.

furu ike ya / Kawazu tobikomu / mizu no oto </br>
Un viejo estanque / Una rana se salta / el sonido del agua </br>
**Matsuo Bashō**(1686)

¿Es un imperio </br>
esa luz que se apaga </br>
o una luciérnaga? </br>
**Jorge Luis Borges** (1981)

¿Cómo funciona el programa?
---------------------------

**Se requiere que todos los archivos se encuentren en la misma carpeta**

> Exportar la libreria de swi-prolog<br>
Computadoras de 32bit
```bash
$ export LD_LIBRARY_PATH="/usr/lib/swi-prolog/lib/i386"
```
Computadoras de 64bits
```bash
$ export LD_LIBRARY_PATH="/usr/lib/swi-prolog/lib/amd64"
```

Compilar el programa
```
$ javac -cp /usr/lib/swi-prolog/lib/jpl.jar *.java
```

Ejecuta el programa
```
$ java -cp /usr/lib/swi-prolog/lib/jpl.jar:. Main
```

Pedir un nuevo haiku
```bash
$ ;
```

Termina el programa
```bash
$ .
```

Puntos Extra
------------

**Haiku Gustavo** </br>
Cielo perlado: </br>
¿Dioses omnipotentes, </br>
o bolas de gas? </br>

------------

**Haiku Mauricio** </br>
Camino oscuro </br>
sueños sin almohada </br>
luz de esperanza </br>

------------

**Haiku Esteban** </br>
The morning sunshine </br>
it moves and its gone again </br>
blind by the dark light </br>
