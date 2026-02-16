

**CUM CITESC?**
Ma folosesc de clasele implementate in schelet (target -> classes -> fileio).
Acestea gestioneaza citirea inputului si imi furnizeaza datele necesare pentru initializarea entitatilor si a comenzilor.

**STRUCTURA PE PACHETE**

1. **Pachetul air**
a) **Air** - Clasa abstracta care defineste metodele generale pentru aer. Aceste metode sunt implementate diferit in functie de tipul concret de aer.
b) **DesertAir, MountainAir, PolarAir, TemperateAir, TropicalAir** - Clasele copil implementeaza formulele specifice pentru calitatea aerului. Pentru comanda de schimbare a vremii am adaugat campuri suplimentare (ex: rainfall) si am modificat formula calitatii aerului.

2. **Pachetul animal**
a) **Animal** - Clasa abstracta ce defineste comportamentul general al animalelor.
b) **Carnivores, Detritivores, Herbivores, Omnivores, Parasites** - Clasele copil implementeaza comportamente specifice fiecarui tip de animal. La interactiune, animalul cauta o patratica mai buna in functie de fiecare caz. Apoi, in functie de tip (carnivor, parazit etc.), executa mutari si interactiuni specifice.

3. **Pachetul plant**
a) **Plant** - Clasa abstracta care defineste metodele generale pentru plante.
b) **Algae, Ferns, FloweringPlants, GymnospermsPlants, Mosses** - Clasele sunt de tip final deoarece contin suficiente informatii si nu mai trebuie extinse.
Observatie: daca o planta devine dead, nu o sterg din lista, ci ii setez maturitatea la "out".

4. **Pachetul soil**
a) **Soil** - Clasa abstracta care defineste comportamentul general al solului.
b) **DesertSoil, ForestSoil, GrasslandSoil, SwampSoil, TundraSoil** - Clasele copil implementeaza formulele specifice pentru calitatea solului.

5. **Pachetul commands**
Acest pachet contine anumite comenzi din simulare.

a) **PRINT ENV CONDITIONS** - Afisez detalii despre patratica pe care se afla robotul. Parcurg lista de entitati si verific tipul fiecareia in ordinea din enunt. Am creat metode separate pentru printare pentru a evita repetarea codului.
b) **PRINT MAP** - Parcurg fiecare patratica din matrice. Trebuie sa printez numarul de obiecte de pe patratica (numar doar water, plant, animal). Calculez calitatea aerului si a solului folosind metodele abstracte implementate in clasele copil.
c) **MOVE ROBOT** - Verific mai intai daca exista vecini pentru a evita IndexOutOfBounds. Pentru fiecare vecin calculez scorul patratelei. Setez probabilitatea de atac pentru toate entitatile din lista, astfel incat sa pot folosi valoarea din entities(0) ulterior. Aleg scorul minim dintre cei maxim 4 vecini, mut robotul si scad bateria. Ma folosesc si de fosta baterie pentru a verifica daca mutarea este posibila. Daca nu este, afisez eroare.
d) **PRINT KNOWLEDGE BASE** - Parcurg matricea si printez cheile si valorile daca valoarea nu este null. La un test nu functiona deoarece nu era in ordine, asa ca am sortat alfabetic.
e) **GET ENERGY STATUS** - Printez bateria robotului folosind getter-ul din clasa Robot.

**CE COMENZI NU AM IMPLEMENTAT IN PACHET, dar am implementat in Main:**
a) **START SIMULATION** - Porneste simularea. Setez o variabila pentru a sti daca simularea a inceput. Aceasta este necesara pentru tratarea erorilor (ex: daca se vrea printarea hartii inainte de start). Afisez mesajul corespunzator.
b) **END SIMULATION** - Se termina simularea. Setez variabila de start la 0 pentru a putea trata erorile daca se incearca alte operatii dupa terminare.
c) **SCAN OBJECT** - Creez un camp nou in Entity, scanned, setat false implicit. Verific daca obiectul scanat este planta, animal sau apa conform regulilor din enunt. Dupa identificare, setez scanned la true. Creez si o variabila care verifica daca scanarea este posibila (daca exista entitate valida in lista). Daca obiectul a fost scanat, incepe sa interactioneze cu mediul.
d) **LEARN FACT** - Verific daca obiectul este scanat si daca numele corespunde cu cel din entitate. Folosesc un LinkedHashMap pentru a pastra ordinea. Adaug topicul si subiectul primit din input. Daca scanez ceva, adaug in LinkedHashMap doar subiectul si setez lista de facts la null (altfel nu trecea testul 20). Totusi, fiecare entitate are si o lista proprie de facts, dar daca entitatea dispare, lista se pierde.
e) **IMPROVE ENVIRONMENT** - Verific daca entitatea este scanata, daca numele corespunde si daca lista de subiecte nu este goala. Exista 4 cazuri (plantvegetation, fertilizesoil etc.). Am creat metode separate pentru fiecare caz, care fac modificarile corespunzatoare. Daca nu sunt indeplinite conditiile, afisez mesaj de eroare.
f) **RECHARGE BATTERY** - Incarc robotul folosind timpul primit din input.
g) **CHANGE WEATHER CONDITIONS** - Adaug campuri specifice in clasele copil ale aerului (ex: rainfall). Citesc evenimentul din input si modific formula de calcul a calitatii aerului.
h) **MULTIPLE SIMULATIONS** - Pentru testele multiple parcurg fiecare simulare separat. Folosesc doi contori pentru a sti cand se opreste o simulare si cand incepe urmatoarea.

6. **Pachetul createTerritory**
Aceste clase sunt folosite pentru construirea initiala a matricei. Matricea contine patratele, iar fiecare patratica include o lista de entitati (plante, animale, sol, apa, aer). Pentru fiecare obiect citit creez o noua instanta si o adaug in lista corespunzatoare.

a) **AddAir** - adauga Aer pe patratica
b) **AddAnimals** - adauga Animal pe patratica
c) **AddPlants** - adauga Planta pe patratica
d) **AddSoil** - adauga Sol pe patratica
e) **AddWater** - adauga Apa pe patratica

7. **Pachetul helpers**
a) **CalculateHelper** - clasa ce contine metode ajutatoare pentru calcul
b) **ExistingHelper** - clasa ce contine metode ajutatoare pentru verificarea existentei unei entitati pe patratica.
c) **InteractionsHelper** - clasa ce contine metode ajutatoare pentru interactiuni intre entitati.
d) **MovingAnimalHelper** - clasa ce contine metode ajutatoare pentru mutarea animalului.
e) **MovingRobotHelper** - clasa ce contine metode ajutatoare pentru mutarea robotului.
f) **PrintHelper** - clasa ce contine metode ajutatoare pentru printare
g) **ReturnHelper** - clasa ce contine metode ajutatoare pentru returnarea unor entitati
h) **SetHelper** - clasa ce contine metode ajutatoare pentru setarea anumitor campuri
i) **UpdateHelper** - clasa ce contine metode ajutatoare pentru updatare.
j) **WeatherUpdateHelper** - clasa ce contine metode ajutatoare pentru updatarea conditiilor meteo
]
8. **Pachetul magicNumbers**
a) **MagicNumbersDouble** - clasa ce contine numere de tip Double
b) **MagicNumbersInt** - clasa ce contine numere de tip Integer

9. **Clase din radacina**
a) **Entity** - Contine informatii generale despre fiecare obiect: nume, tip, scanned (false implicit), lista de facts
b) **Robot** - Contine: baterie, pozitie, stare incarcare, fosta baterie (folosita pentru verificari)
c) **Main** - Creez matricea, citesc entitatile si comenzile si tratez fiecare caz in parte.
