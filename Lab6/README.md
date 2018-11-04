# Explicatii laborator 6

## Interfete
- o interfata reprezinta, in a nutshell, o schita a unei clase, in sensul ca dicteaza felul cum ar trebui sa arate o clasa
- o interfata contine, de regula, metode neimplementate (adica doar semnatura lor) -> aceste metode sunt non - statice
- o interfata poate sa aiba metode implementate -> metode statice (https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html - metodele default nu ne intereseaza acum, las ca lectura suplimentara)
- o interfata poate sa extinda una sau mai multe interfete in acelasi timp
- o interfata nu poate extinde o clasa (logic, caci clasa are metode implementate, interfata nu)
- exemple de interfete deja existente in Java: Comparable, Comparator (folosite pentru comparare la sortate, in caz ca avem clase facute de noi), Set, List, Map (colectii / structuri de date)
- o clasa poate sa implementeze mai multe interfete in acelasi timp, chiar daca unele interfete au metode neimplementate (semnaturi de metode) in comun

## Clase abstracte
- o clasa abstracta reprezinta, pe scurt, un schelet al clasei, in sensul ca o clasa va avea deja implementate niste metode (adica cele deja implementate in interfata) si ca va trebui sa aiba implementate alte metode (cele abstracte, care in mod obligatoriu vor fi implementate in clasele normale mostenitoare), a caror functionalitate vor diferi in functie de necesitatile noastre
- o clasa abstracta are keyword-ul abstract (obviously) si poate sa aiba 0 sau mai multe metode abstracte -> nu e de recomandat ca o clasa abstracta sa aiba zero metode abstracta, caci asa nu isi mai are rostul sa fie abstracta (get it?)
- o clasa abstracta ce implementeaza o interfata are metodele din interfata by default abstracte (ca-s neimplementate, sesizezi?)
- o clasa normala ce extinde o clasa abstracta implementeaza in mod obligatoriu metodele abstracte, la fel precum metodele din interfete

## Mostenire (adaugare fata de laboratorul 3)
- o clasa poate sa mosteneasca o singura clasa in acelasi timp, din cauza ambiguitatii, in sensul ca 2 clase care nu au legature intre ele pot avea metode cu aceeasi semnatura, insa cu functionalitati diferite, iar clasa ce le-ar mosteni pe ambele in acelasi timp nu ar stii pe care le-ar alege
- o clasa poate sa mosteneasca o alta clasa si sa implementeze interfete in acelasi timp
