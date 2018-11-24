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

public class DemoLab9 {
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
    }
}