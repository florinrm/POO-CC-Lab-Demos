import java.*;
import java.util.*;

// la colectii, in principiu, o sa va folositi in prostie de documentatie pentru metode (nici eu nu le stiu pe toate pe de rost lel)

class SetComparator implements Comparator<Integer> {
    public int compare (Integer a, Integer b) {
                return b - a;
    }
}

public class DemoLab7 {
    public static void main (String[] args) {
        // acum precizam explicit tipul de date din ArrayList si asa vom face la celelalte colectii
        // colectiile contin obiecte, nu valori, de aceea aici tipizam cu Integer si nu cu int
        // Integer fiind o clasa wrapper (face din valoare obiect) pentru int (Double - double, Character - char etc.)
        // asadar la get nu mai trebuie sa facem cast (yaay), ca sa nu avem un cod greu de citit
        // iar colectii va contine doar obiecte de tip Integer in acest caz
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4); // alte metode -> documentatia JavaDoc
        // il parcurgem 
        System.out.println("ArrayList");
        for (int i = 0; i < arr.size(); ++i)
            System.out.print(arr.get(i) + " "); // asa accesam un element din ArrayList 
        System.out.print("\n\nVector - exemplificare iteratori\nIterator\n");

        Vector<Integer> vect = new Vector<>();
        vect.add(6);
        vect.add(9);
        vect.add(6);
        vect.add(9); // alte metode -> documentatia JavaDoc

        // putem sa parcurgem ca la ArrayList (diferente minore intre aceste 2 colectii)
        // dar alegem, pentru exemplificare, sa folosim iteratori
        Iterator<Integer> iter = vect.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.print("\n\nEnumeration\n");

        Enumeration<Integer> en = vect.elements();
        while (en.hasMoreElements()) {
            System.out.print(en.nextElement() + " ");
        }
        System.out.print("\n\nListIterator\n");

        ListIterator<Integer> iter2 = vect.listIterator();
        while (iter2.hasNext()) {
            System.out.print(iter2.next() + " ");
        }
        System.out.print("\n\nStack\n");

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < 5; ++i) {
            st.push(i * 69);
        }
        st.pop();
        // putem folosi iterator la Stack sau modul clasic de la SD - cat timp are elemente - afisezi si dai pop (desi poate fi destructiv)
        Iterator<Integer> stackIter = st.iterator();
        while (stackIter.hasNext()) {
            System.out.print(stackIter.next() + " ");
        }
        System.out.print("\n\nQueue\n");
        // folosim o coada, care e implementata cu LinkedList
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < 5; ++i) {
            q.add(i * 69);
        }
        Iterator<Integer> queueIter = q.iterator();
        while (queueIter.hasNext()) {
            System.out.print(queueIter.next() + " ");
        }
        System.out.print("\n\nTreeSet\n");

        // TreeSet - multime cu elemente sortate - putem sa folosim un Comparator ca argument la constructorul de la TreeSet
        // sau elementele sa fie Comparable
        TreeSet<Integer> set1 = new TreeSet<>(new SetComparator());
        TreeSet<Integer> set2 = new TreeSet<>(); // ordonare crescatoare - by default
        set1.add(1);
        set1.add(2);
        set1.add(2);
        set1.add(0);
        set1.add(-1);
        set1.add(1);

        set2.add(1);
        set2.add(2);
        set2.add(2);
        set2.add(0);
        set2.add(-1);
        set2.add(1);

        // la seturi avem doar iteratori, nu putem accesa ca la ArrayList sau Vector
        Iterator<Integer> set1Iter = set1.iterator();
        Iterator<Integer> set2Iter = set2.iterator();
        System.out.println("Descrescator");
        while (set1Iter.hasNext()) {
            System.out.print(set1Iter.next() + " ");
        }
        System.out.println("\nCrescator");
        while (set2Iter.hasNext()) {
            System.out.print(set2Iter.next() + " ");
        }
        System.out.print("\n\nHashSet\n");

        HashSet<Integer> set3 = new HashSet<>();
        Iterator<Integer> set3Iter = set3.iterator(); // daca facem aici nu vom aveam nimic in iterator aka inainte sa bagam in colectie
        set3.add(1);
        set3.add(2);
        set3.add(2);
        set3.add(0);
        set3.add(-1);
        set3.add(1);
        set3Iter = set3.iterator();
        while (set3Iter.hasNext()) {
            System.out.print(set3Iter.next() + " ");
        }


    }
}