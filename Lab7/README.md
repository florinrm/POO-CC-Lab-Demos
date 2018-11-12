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
### LinkedList
### Stack
### HashSet
### TreeSet
### Hashtable
### HashMap
### TreeMap

## Iteratori
- Iteratorii reprezinta o modalitate de a itera (de a parcurge) colectiile
