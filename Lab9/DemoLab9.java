import java.*;
import java.util.*;

// folosim K si V (tipuri formale) pentru a face un cod mai generalizat, in sensul ca nu ar mai trebui sa scriu clase separate Pair pentru <Integer, Integer>
// <Integer, String> etc. -> salvez cod si draci (cine dracu sta sa scrie cod la fel doar ca difera tipul de date?)
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

public class DemoLab9 {

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

    // metode generice - TODO

    public static void main (String[] args) {
        /* ASA NU */
        Vector vect = new Vector(); // neprecizand tipul de date, se va interpreta ca avem by default Object-uri in Vector
        vect.add(69);
        int value = (Integer) vect.get(0); // ca sa luam 69 hihi

        /* ASA DA */
        Vector<Integer> vect2 = new Vector<Integer>(); // sau new Vector<>(); -> acum stim ca avem int-uri in Vector
        vect2.add(69);
        value = vect2.get(0); // ca sa luam 69 hihi

        Pair<Integer, Integer> pair1 = new Pair<>(69, 69); // aici, K si V sunt inlocuite automat cu tipuri efective (Integer la K si V)
        Pair<Double, String> pair2 = new Pair<>(420.69, "TS e veatza"); // K -> Double, V -> String
        Double pairKey = pair2.getKey(); // nu avem nevoie de cast
        String pairValue = pair2.getValue();

        // Pair<Object, Object> pair3 = pair2; // chiar daca pair3 e mai general, avem eroare de compilare 
        // incompatible types: Pair<Double,String> cannot be converted to Pair<Object,Object>
        // adica, chiar daca Double si String mostenesc Object (subclase) =!> <Double, String> subtip al lui <Object, Object>

        // Integer si Double extind clasa Number, String "extinde" (implementeaza) Comparable
        RestrictedPair<Integer, String> p1 = new RestrictedPair<>(69, "SLN");
        RestrictedPair<Double, String> p2 = new RestrictedPair<>(420.0, "SLD");
        System.out.println(p1.getKey());
        System.out.println(p1.getValue());
        System.out.println(p2.getKey());
        System.out.println(p2.getValue());
        // mai jos avem eroare, deoarece String nu extinde Number, iar Pair nu e Comparable
        // RestrictedPair<String, Pair<Integer, Integer>> p3 = new RestrictedPair<>("TDC", new Pair<Integer, Integer>(69, 69));

        Random rand = new Random();
        Vector<Integer> list = new Vector<>();
        for (int i = 0; i < 10; ++i) {
            list.add(rand.nextInt(50));
        }
        // mai jos vom avea eroare, chiar daca Integer extinde Object:  incompatible types: Vector<Integer> cannot be converted to List<Object>
        // print(list);
        printWithWilcard(list);
        printRestricted(list);

        Vector<?> vector = new Vector<Integer>();
        // vector.add(10); // vom avea eroare, caci nu se stie tipul de date prezent in colectie, desi am instantiat cu Vector<Integer>
        // Solutia
        ((Vector<Integer>) vector).add(10); // trebuie cast
        // ((Vector<Long>) vector).add(10); // tzeapa aici, castul trebuie sa fie la fel cu instantierea
        vector.add(null); // fara castare, am putea sa adaugam doar null in colectie

        Vector<Number> list2 = new Vector<>();
        list2.add(new Integer(69));
        list2.add(new Double(69));
        list2.add(new Long(69));
        printSuperRestricted(list2);
    }
}