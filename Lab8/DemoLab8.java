import java.*;
import java.util.*;
import java.util.Random;

class Outer {
    private int x; // membru al clasei 

    public Outer (int x) {
        this.x = x;
    }

    int getX() {
        return x;
    }

    public class InnerClass {
        private int x, y;

        public InnerClass(int x, int y) {
            Outer.this.x = x; // avem x atat in clasa externa / mare, cat si in clasa interna
            // prin acest mod ne referim la x-ul din clasa interna
            //this.x = 10;
            System.out.println("Outer: " + this.x); // va afisa 0
            System.out.println("Inner:" + Outer.this.x);
            this.y = y;
        }

        public int getInnerX() {
            return Outer.this.x; // daca avem doar return x -> va returna 0
        }

        public int getX() {
            return x;
        }

        public int getInnerY() {
            return y;
        }
    }

    // daca clasa interna are modificatorul private, ne folosim de un getter (clasic, ca inainte cu membrii privati)
    public InnerClass getInstance (int x, int y) {
        return new InnerClass(x, y);
    }

    public static class StaticInnerClass {
        private int x, y;

        public StaticInnerClass(int x, int y) {
            this.x = x; // aici nu merge cu Outer.this.x
            //Outer.this.x = x;
            // decomentand linia de mai sus, vom avea eroarea "non-static variable this cannot be referenced from a static context"
            System.out.println("Inner static:" + this.x);
            this.y = y;
        }

        public int getInnerX() {
            //return Outer.this.x; // daca avem doar return x -> va returna 0
            return x;
        }

        public int getX() {
            return x;
        }

        public int getInnerY() {
            return y;
        }
    }

    public void someStuff() {
        // clasa locala -> nu poate fi abstracta sau finala
        class Student {
            public String name, surname;
            public Student (String name, String surname) {
                this.name = name;
                this.surname = surname;
            }

            @Override
            public String toString () {
                return name + " " + surname;
            }
        }

        Student first = new Student ("Bogdanel", "Poponel");
        Student second = new Student ("Malonel", "Frumushel");
        System.out.println(first);
        System.out.println(second);
    }
}


public class DemoLab8 {
    public static void main (String[] args) {
        Outer out = new Outer(420);
        System.out.println(out.getX());
        
        Outer.InnerClass in = out.new InnerClass(69, 69); // instantierea unei clase nested non-statice
        System.out.println("Inner x: " + in.getInnerX());
        System.out.println("Inner y: " + in.getInnerY());
        System.out.println("Just x in inner class: " + in.getX());

        Outer.InnerClass in2 = out.getInstance(100, 100);
        System.out.println("Inner x: " + in2.getInnerX());
        System.out.println("Inner y: " + in2.getInnerY());
        System.out.println("Just x in inner class: " + in2.getX());

        Outer.StaticInnerClass in3 = new Outer.StaticInnerClass(69, 69);
        System.out.println("Static inner x: " + in3.getInnerX());
        System.out.println("Static inner y: " + in3.getInnerY());
        System.out.println("Just x in static inner class: " + in3.getX());

        // cream o clasa anonima prin extinderea clasei Vector, in care suprascriem metoda add
        // pentru a face astfel incat sa se adauge doar numere pare
        Vector<Integer> vect = new Vector<Integer>() {
            @Override
            public boolean add (Integer e) {
                if (e % 2 == 0)
                    return super.add(e);
                return false;
            }
        };
        for (int i = 0; i < 30; i++) {
            Random rand = new Random();
            vect.add(rand.nextInt(100));
        }
        System.out.println(vect);
        // vrem sa sortam vect in ordine descrescatoare
        // astfel cream o clasa anonima care implementeaza Comparator -> criteriu de comparare la sortare
        // Collection.sort primeste ca parametru un obiect de tip Comparator
        Collections.sort(vect, new Comparator<Integer>(){
            @Override
            public int compare (Integer e1, Integer e2) {
                return e2 - e1;
            }
        }); // clasa anonima implementeaza interfata Comparator in acest caz
        // pe scurt, clasele anonime sunt mostenire in forma minimalista

        System.out.println(vect);
    }
}