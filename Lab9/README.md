# Genericitate
## Introducere
- Genericitatea este o modalitate de a paramatriza tipurile de date in structurile de date, mai ales in colectii
- Este destul de similara cu template-urile din C++, folosite in STL (echivalentul colectiilor din Java in C++)
## De ce genericitate?
```java
/*
ASA NU
*/
Vector vect = new Vector(); // neprecizand tipul de date, se va interpreta ca avem by default Object-uri in Vector
vect.add(69);
int value = (Integer) vect.get(0); // ca sa luam 69 hihi
```
- daca am proceda ca mai sus, neprecizand tipul de date la Vector (adica sa precizez tipul de date prezent in Vector, in acest caz avem by default Object si nu Integer in Vector, Integer fiind o [clasa wrapper](https://www.geeksforgeeks.org/wrapper-classes-java/), care face conversie de la tipuri-valoare la tipuri-obiecte, colectiile continand obiecte), suntem obligati daca, stiind ca punem, de exemplu numere intregi in Vector, sa facem cast, ceea ce duce la un cod greu de citit si care poate produce erori (daca nu suntem atenti)
```java
/*
ASA DA
*/
Vector<Integer> vect = new Vector<Integer>(); // sau new Vector<>(); -> acum stim ca avem int-uri in Vector
vect.add(69);
int value = vect.get(0); // ca sa luam 69 hihi
```
## Wildcards
