TEMA 1 POO


CUM CITESC?
Ma folosesc de clasele implementate in schelet(target->classes->fileio).

CLASELE CREATE:
1. CLASA ENTITATE
Clasa entitate cuprinde informatiile generale(nume si tip) pentru fiecare viitor obiect creat.
2. CLASELE PLANT/ANIMAL/SOIL/WATER/AIR
Clasele acestea sunt clase abstracte pentru ca DEFINESC Niste metode pe care le voi dezvolta in functie de tipul de planta/animal/sol/apa/aer.
3. CLASELE SPECIFICE FIECAREI CLASE PARINTE(clasele de la 2)
Clasele sunt de tip final pentru ca nu le mai pot extinde si detin informatii suficiente.
4. CLASA ROBOT
Clasa Robot implementeaza principalele informatii despre Robot- baterie, pozitia in matrice, verificarea daca se incarca sau nu, si fosta baterie(am nevoie de ea la unele teste).

Pentru fiecare apar campuri de tip private, pe care ii accesez cu getteri si setteri si le initializez cu constructori.

IN MAIN:
Matricea mea contine patratele. Patratica include o lista cu mai multe entitati, care pot fi plante/animale/sol/etc. Citesc informatie despre fiecare clasa definita in schelet si creez cate un nou obiect la fiecare alt obiect citit. Apoi citesc comenzile. Compar comanda cu numele comenzii si apoi pentru fiecare caz in parte rezolv.

1. START SIMULATION
Incepe simularea. Setez o variabila locala sa stiu daca a inceput comanda sau nu(este necesara in cazul in care trebuie sa tratez anumite erori- ex: se vrea printarea mapei inainte sa inceapa simularea). AFisez mesajul ca a inceput simularea.
2. PRINTENVCONDITIONS
Trebuie sa afisez detalii despre patratica pe care se afla robotelul meu. Parcurg lista de entitati si verific pentru fiecare daca e planta/animal/sol/etc, in ordinea in care apare in ref-uri si in care e data in enuntul temei. Creez 5 metode in afara clasei action care sa ma ajute sa printez. (E NEVOIE CA SA NU SCRIU DE 100 ORI ACELASI COD. DE ASEMENEA, pentru unele teste trebuie sa afisez anumite campuri mai specifice, de aceea este mai bine sa afisez doar campurile de baza- la clasa Air, cand se schimba vremea).
3. PRINTMAP
Printeaza mapa. Parcurg fiecare patratica din matrice. Trebuie sa printez numarul de obiecte de pe patratica(trebuie sa numar doar water, plant, animal). Parcurg lista de entitati pentru fiecare si numar. TRebuie sa calculez calitatea aerului si a solului de pe patratica. Ma folosesc de metodele abstracte din fiecare clasa PARINTE pe care le-am implementat ulterior in clasele COPIL. 
3. ENDSIMULATION
Se termina simularea. Setez variabila definita la STARTSIMULATION cu 0 ca sa afisez anumite mesaje de eroare in caz ca se mai vrea alta manipulare a datelor dupa aceasta.
4. MOVEROBOT
Ma ajut de metoda mutarobotelul. Mai intai verific daca exista vecin(AM PRIMIT DE MULTE ORI INDEXOUTOFBOUND). Pentru fiecare vecin, daca exista, calculez cat de rea e patratica. Vreau sa setez probabilitatea de atac pentru TOATE ENTITATILE din clasa ca sa nu ma complic. Cand voi avea nevoie de probabilitatea de atac, pot sa o gasesc in entities(0) pentru ca toate au aceeasi probabilitate dupa calcul.
Dupa ce gasesc scorul minim din cei maxim 4 vecini, si il pun in scorul patratelei. APoi setez x si y pentru robot si scad din baterie scorul gasit. Aici ma folosesc si de fosta baterie, sa vad daca se poate efectua mutarea. Daca nu, EROARE.
5. SCANOBJECT
Pentru asta creez un nou camp in entity care este false default. Acum verific daca obiectul scanat e planta, animal sau apa, dupa regula din enunt. Daca toate sunt none, atunci e apa, si asa mai departe. Dupa ce am gasit ce e, setez campul scanned la true pentru entitatea respectiva. Creez si o variabila sa retin daca se poate scana obiectul sau nu(adica daca exista apa/planta/animal in lista de entitati). Daca s-a scanat obiectul, incepe sa interactioneze cu mediul.

INTERACTIUNI LA FIECARE ITERATIE:
sunt cele prezentate in enunt, nu am ce sa zic mai mult
INTERACTIUNI LA CATE 2 ITERATII:
sunt cele prezentate in enunt, nu am ce sa zic mai mult
6. LEARNFACT
Verific daca mai intai obiectul e scanat si daca numele corespunde cu cel din entitate. APoi setez lista de subiecte din entitate cu inputul pe care ni-l dau ei. Din nou, fac o variabila care imi verifica daca se poate adauga acest fact(daca indeplineste conditiile).
7. PRINTKNOWLEDGEBASE
Printez listele de subiecte facute mai sus. Parcurg matricea si vad unde nu e null aceasta lista, printez atat topicul(care e numele) cat si subiectul. La un test nu mergea pentru ca efectiv nu era pus in ordine, dar le am facut eu sa fie in ordine alfabetica si acum merge, nu stiu cat de bine e sincer.
8. IMPROVEENVIRONMENT
Vad daca pot dezvolta mediul. Trebuie sa am obiectul scanat, sa corespunda numele si lista de subiecte sa nu fie goala. Apoi apar 4 cazuri: plantvegetation, fertilizesoil, etc. Creez patru metode care imi fac modificarile corespunzatoare din enunt si apoi fac si o variabila care imi arata daca s-a efectuat cu succes. Daca nu, afisez mesajul de eroare ca nu s-a salvat subiectul.
9. GETENERGYSTATUS
Imi printeaza bateria robotului. O am in clasa robot si o printez cu un getter.
10. RECHARGEBATTERY
Incarc robotul cu ajutorul timpului dat in inputuri. 
11. CHANGEWEATHERCONDITIONS
Pentru aceasta comanda mai adaug la CLASELE COPIL ale aerului anumite campuri specifice(rainfall, etc.). Citesc din inputuri ce fel de eveniment se intampla si modific si calitatea aerului care are alta formula acum. 

CEFACEANIMALUL
Aici am explicat ce face animalul. Mai intai cauta patratica mai buna, in functie de fiecare caz. Apoi pentru fiecare caz(daca e carnivor, parazit sau nu), face anumite mutari.

Pentru toate comenzile fac verificarile pentru baterie, pentru incepere simulare si pentru reincarcare baterie.


OBSERVATIE: Mi-am dat seama ca daca o planta devine dead, trebuie stearsa.
(Se putea o explicatie mai buna in README, dar nu am mai avut timp. La deadline o sa fie mai bine;).


