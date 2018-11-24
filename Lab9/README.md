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
- Folosim tipuri formale (K si V in acest caz) pentru a realiza o implementare generala, indiferent de tipul de date ce vor inlocui K si V (asta daca nu restrictionam, va urma mai jos un exemplu), astfel evitand sa scriem aceeasi implementare de structuri iarasi doar pentru tipuri de date diferite.
```java
class Pair <K, V> {
    private K key;
    private V value;

    public Pair (K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey (K key) {
        this.key = key;
    }

    public void setValue (V value) {
        this.value = value;
    }
}
Pair<Integer, Integer> pair1 = new Pair<>(69, 69); // aici, K si V sunt inlocuite automat cu tipuri efective (Integer la K si V)
Pair<Double, String> pair2 = new Pair<>(420.69, "TS e veatza"); // K -> Double, V -> String
```
- Sunt situatii totusi dorim sa restrictionam tipul de date pentru tipurile formale (adica sa restrangem aria de generalitate):
```java
// daca vrem sa restrictionam tipurile de date, facem in felul urmator:
// K sa fie doar Number (adica sa fie subtip de Number aka sa fie doar Integer, Double etc), iar V sa fie de tip Comparable
class RestrictedPair <K extends Number, V extends Comparable> {
    private K key;
    private V value;

    public RestrictedPair (K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setKey(K key) {
        this.key = key;
    }
}
RestrictedPair<Integer, String> p1 = new RestrictedPair<>(69, "SLN");
RestrictedPair<Double, String> p2 = new RestrictedPair<>(420.0, "SLD");
```
## Wildcards
```java
// aici putem folosi doar List-uri care contin doar Object, deci nu pot folosi liste care contin Integer, String etc.
    private static void print(List<Object> list) {
        for (Object e: list) {
            System.out.print(e + " ");
        }
        System.out.println("");
    }

    // prin wildcard-uri elimin restrictia de mai sus, putand sa trimit orice tip de lista (cu Integer, String etc)
    private static void printWithWilcard(List<?> list) {
        for (Object e: list) {
            System.out.print(e + " ");
        }
        System.out.println("");
    }

    /**
     * Bounded wildcards:
     * 1) lower bounded
     * 2) upper bounded
     */
    // upper bounded - doar colectii cu elemente ce extind clasa Number in acest caz
    private static void printRestricted (List<? extends Number> list) {
        for (Number e: list) {
            // se putea si Object
            System.out.print(e + " ");
        }
        System.out.println("");
    }

    // lower bounded - doar colectii cu elemente ce sunt superioare lui Number
    private static void printSuperRestricted (List<? super Number> list) {
        for (Object e: list) {
            // aici nu putem cu Number in loc de Object
            System.out.print(e + " ");
        }
        System.out.println("");
    }

    // metode generice 
    private static <T> void doStuff1 (T[] obj, List<T> list) {
        for (T lel: obj)
            list.add(lel);
    }

    private static void doStuff2 (Object[] obj, List<?> list) {
        for (Object lel: obj) {
            // eroare de tip incompatible types: Object cannot be converted to CAP#1
            //list.add(lel);
        }
    }

    // ca sa evitam eroarea de mai sus, folosim bounded wildcards
    private static <T> void doStuff3 (List<? extends T> orig, List<T> list) {
        for (T lel: orig)
            list.add(lel);
    }

    private static <T extends Number> void doStuff4 (List<T> orig, List<T> list) {
        for (T lel: orig)
            list.add(lel);
    }
```
- Wildcards-urile ne ajuta la eliminarea restrictiilor legate de tipurile de date (vezi exemplu mai sus cu metodele `print` si `printRestricted`, care ne ajuta la intelegerea avantajului folosirii de wildcards)
- Insa la wildcards trebuie sa fim atenti la partea de colectii, daca dorim sa tipizam o colectie cu un wildcard (?), caci pot aparea probleme la insertia in colectie, unde avem nevoie de castare la colectie (singura situatie in care merge sa adaugam fara castare este atunci cand inseram `null` in colectie, deoarece `null` este la nivel general).
```java
Vector<?> vector = new Vector<Integer>();
// vector.add(10); // vom avea eroare, caci nu se stie tipul de date prezent in colectie, desi am instantiat cu Vector<Integer>
// Solutia
((Vector<Integer>) vector).add(10); // trebuie cast
// ((Vector<Long>) vector).add(10); // tzeapa aici, castul trebuie sa fie la fel cu instantierea
vector.add(null); // fara castare, am putea sa adaugam doar null in colectie
```
- Uneori, faptul ca eliminam restrictiile este o problema si trebuie sa adaugam niste restrictii de date -> in acest caz folosim bounded wildcards, care sunt de 2 tipuri:
1) upper bounded wildcards: `T extends Number` -> doar tipuri de date care sunt Number sau subtipuri de Number
2) lower bounded wildcards:  `? super Number` -> doar tipuri de date care sunt Number sau supratipuri de Number 
```
Vector<Number> list2 = new Vector<>();
list2.add(new Integer(69));
list2.add(new Double(69));
list2.add(new Long(69));
printSuperRestricted(list2); // e mai sus metoda
```
