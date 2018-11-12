# Colectii

## Putina introducere
- Ce reprezinta colectiile in Java? Pe scurt, colectiile sunt niste [structuri de date](https://aimas.cs.pub.ro/file/2017/12/SerbanRaduPr-200x200.jpg), care sunt deja implementate in Java (ati dat pana acum de colectii la laborator de Vector si ArrayList)
- La colectii / structuri de date, este esential sa stiti, orientativ, [complexitatile operatiilor](http://bigocheatsheet.com/), deoarece ele pot fi esentiale pentru interviuri si concursuri, pentru a avea o solutie eficienta, cu o complexitate ok 

## Interfetele Collection, List, Set si Map
- Interfata Collection este interfata cea mai generalizata -> avem subinterfete care descriu colectii sortate (sau nu) sau cu elemente unice (sau nu) - [ceva lectura suplimentara](https://docs.oracle.com/javase/7/docs/api/java/util/Collection.html)
- Interfata List extinde Collection si descrie o lista -> colectii ce implementeaza List: ArrayList, Vector, LinkedList, Stack (extinde Vector)
- Interfata Set extinde Collection si descrie o multime (elemente unice) -> colectii ce implementeaza Set: TreeSet, HashSet, LinkedHashSet
- Interfata Map este o interfata generalizata, independenta de Collection, ce descrie un dictionar, cu intrari de tip cheie-valoare ([detalii](https://docs.oracle.com/javase/7/docs/api/java/util/Map.html))
- Desi avem colectia Stack implementata, Queue este doar o interfata - e implementata de LinkedList si PriorityQueue [si altele](https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html)

## Clase de colectii
### [ArrayList](https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html)
### [Vector](https://docs.oracle.com/javase/7/docs/api/java/util/Vector.html)
- Foarte similar la comportament cu ArrayList, diferentele sunt legate de sincronizare (APD anul 3) si de resize cand se depaseste marimea maxima alocata - [lectura suplimentara](https://stackoverflow.com/questions/2986296/what-are-the-differences-between-arraylist-and-vector)
### [LinkedList](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html)
- Implementarea unei liste dublu-inlantuite -> poate fi folosita ca o coada, caci implementeaza Queue
### [Stack](https://docs.oracle.com/javase/7/docs/api/java/util/Stack.html)
- Implementarea stivei
### [HashSet](https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html)
- Multime, operatiile elementare (add, remove) sunt in O(1), datorita hash-uirii
### [TreeSet](https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html)
- Multime, elementele sunt ordonate default crescator (adica daca nu avem deja un criteriu de comparare)
### [Hashtable](https://docs.oracle.com/javase/7/docs/api/java/util/Hashtable.html)
- Dictionar / tabela de dispersie - avem voie cu o cheie null si cu valori null aici
### [HashMap](https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html)
- Dictionar - fara cheie null si difera de Hashtable la sincronizare (iar APD) -> [lectura](https://stackoverflow.com/questions/40471/differences-between-hashmap-and-hashtable)
### [TreeMap](https://docs.oracle.com/javase/7/docs/api/java/util/TreeMap.html)
- Dictionar - cheile sunt sortate (default in ordine crescatoare, daca nu -> ne trebuie un Comparator)
### [LinkedHashMap](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedHashMap.html)
- Dictionar - cheile se pastreaza in ordinea insertiei - lucru garantat [detalii](https://stackoverflow.com/questions/40471/differences-between-hashmap-and-hashtable)

## Iteratori
- Iteratorii reprezinta o modalitate de a itera (de a parcurge) colectiile
- Avem trei tipuri de iteratori: Iterator, ListIterator, Enumeration
- Colectiile au metode ce returneaza un iterator (aka un fel de cursor pentru parcurgerea colectiei) - iar documentatie
- [Lectura](https://www.geeksforgeeks.org/iterators-in-java/)

## equals si hashCode
- equals - metoda din clasa [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html) (clasa parinte pentru toate clasele), dam override in cadrul unor clase realizate pentru noi, pentru a face posibila compararea a doua obiecte, pentru a vedea daca acestea sunt la fel (si nu din punctul de vedere al referintei, ci ca valoare in acest caz)
- hashCode - metoda din Object, ce calculeaza un hash bazat pe obiect - folosit pentru dictionare
```java
class Student {
    public String nume, prenume;
    public Student (String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
    }

    // daca 2 obiecte sunt egale
    @Override
    public boolean equals (Object std) {
        if (!(std instanceof Student))
            return false; // daca nu este instanta a clasei Student -> false, nu-s egale
        Student st = (Student) std;
        return nume.equals(st.nume) && prenume.equals(st.prenume);
    }

    // hashing - pentru dictionare
    @Override
    public int hashCode() {
        // fac la vrajeala aici, asa pentru demo
        int sum = 0;
        for (int i = 0; i < nume.length(); ++i)
            sum += nume.charAt(i);
            for (int i = 0; i < prenume.length(); ++i)
            sum += prenume.charAt(i);
        return sum << 3;
    }
}
```
