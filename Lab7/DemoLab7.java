import java.*;
import java.util.*;

// la colectii, in principiu, o sa va folositi in prostie de documentatie pentru metode (nici eu nu le stiu pe toate pe de rost lel)

class SetComparator implements Comparator<Integer> {
    public int compare (Integer a, Integer b) {
                return b - a;
    }
}

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

        // set bazat pe hash-uri, insertia si stergerea sunt in timp constant
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
        System.out.println("\n\nHashtable\n");

        // dictionar / tabela de dispersie, in cazul nostru cu cheia de tip String si valoarea de tip Integer
        Hashtable<String, Integer> hash1 = new Hashtable<>();
        hash1.put("Malone", 69);
        hash1.put("este", 420);
        hash1.put("BO$$", 69);
        Integer elem = hash1.get("BO$$");
        System.out.println("Hashtable - value of BO$$: " + elem);
        Iterator<String> hashIter = hash1.keySet().iterator(); // keySet returneaza un set de chei din dictionar
        while (hashIter.hasNext()) {
            String key = hashIter.next();
            System.out.println("Key: " + key + " Value: " + hash1.get(key));
        }

        System.out.println("\n\nHashMap\n");

        // spre deosebire de Hashtable, HashMap permite sa ai o cheie null si valori nule
        HashMap<String, Integer> hash2 = new HashMap<>();
        hash2.put("Malone", 69);
        hash2.put("este", 420);
        hash2.put("BO$$", 69);
        elem = hash2.get("BO$$");
        System.out.println("HashMap - value of BO$$: " + elem);
        Iterator<String> hashIter2 = hash2.keySet().iterator(); // keySet returneaza un set de chei din dictionar
        while (hashIter2.hasNext()) {
            String key = hashIter2.next();
            System.out.println("Key: " + key + " Value: " + hash2.get(key));
        }

        // o alta modalitate de a parcurge un Map
        Set<Map.Entry<String, Integer>> entries = hash2.entrySet(); // avem o multime de intrari de tip cheie-valoare
        for (Map.Entry<String, Integer> entry: entries) {
            System.out.println("Cheie: " + entry.getKey() + " Valoare: " + entry.getValue());
        }

        System.out.println("\n\nTreeMap\n");

        // dictionar / tabela de dispersie, in cazul nostru cu cheia de tip String si valoarea de tip Integer
        TreeMap<String, Integer> hash3 = new TreeMap<>();
        hash3.put("Malone", 69);
        hash3.put("este", 420);
        hash3.put("BO$$", 69);
        elem = hash3.get("BO$$");
        System.out.println("TreeMap - value of BO$$: " + elem);
        Iterator<String> hashIter3 = hash3.keySet().iterator(); // keySet returneaza un set de chei din dictionar
        while (hashIter3.hasNext()) {
            String key = hashIter3.next();
            System.out.println("Key: " + key + " Value: " + hash3.get(key));
        }
        System.out.println("\n\nLinkedHashMap\n");

        // dictionar / tabela de dispersie, in cazul nostru cu cheia de tip String si valoarea de tip Integer
        LinkedHashMap<String, Integer> hash4 = new LinkedHashMap<>();
        hash4.put("Malone", 69);
        hash4.put("este", 420);
        hash4.put("BO$$", 69);
        elem = hash4.get("BO$$");
        System.out.println("LinkedHashMap - value of BO$$: " + elem);
        Iterator<String> hashIter4 = hash4.keySet().iterator(); // keySet returneaza un set de chei din dictionar
        while (hashIter4.hasNext()) {
            String key = hashIter4.next();
            System.out.println("Key: " + key + " Value: " + hash4.get(key));
        }
    }
}