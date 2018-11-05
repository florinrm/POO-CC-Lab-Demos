import java.*;
import java.util.*;

interface A {

}

interface B {

}

/*
* o interfata poate sa extinda una sau mai multe interfete in acelasi timp
*/

interface C extends A, B {

}

interface D extends A {

}

//--------------------------------------------------------------------------------------------------------------------------//

interface IAnimal {
    int magicValue = 69; // campurile sunt by default publice si statice
    void makeSound();
    static int anotherMagicValue = 420; // e ca mai sus, doar ca e explicita
    // in interfata, metodele sunt by default publice (altfel ar fi irelevant, caci scopul e ca aceste metode sa fie accesate din afara interfetei pentru implementare)
    static void muie() {
        System.out.println("Coraci este un profesor model");
    }

    // pe scurt, intr-o interfata putem avea membri statici care se comporta normal ca intr-o clasa
}


abstract class Animal implements IAnimal {
    protected String origin; 
    // un membru protected e vizibil in clasa in care se afla (duuuh, e logic) 
    //si in clasele mostenitoare, unde acesta (fiind mostenit de ele) este privit ca un membru privat

    public String name;

    public Animal (String origin, String name) {
        this.origin = origin;
        this.name = name;
    } 

    // metoda makeSound() din interfata IAnimal este tratata by default ca o metoda abstracta in clasa abstracta care o implementeaza

    public abstract void doSomething(); // metoda abstracta

    // intr-o clasa abstracta putem sa avem metode deja implementate
    // chiar mai mult, tehnic, putem sa avem toate metodele dintr-o clasa abstracta deja implementate, insa nu mai are niciun rost ca clasa (ne place cacofonia)
    // sa mai fie abstracta

    public void doStuff() {
        System.out.println("Teoria sistemelor e cea mai tare materie! <3");
    }
}

class StrangeAnimal implements IAnimal {
    // fiind o clasa normala, deci nu abstracta (obvious), trebuie neaparat sa implementam metoda makeSound
    public void makeSound() {
        System.out.println("Coraci este o persoana cinstita, care nu fura"); // vrajeala
    }
}

/*
* o clasa poate:
* sa implementeze mai multe interfete in acelasi timp
* sa extinda doar o clasa o data(vezi ambiguitate)
* sa extinda o clasa si sa implementeze interfete in acelasi timp
* o clasa nu poate extinde mai multe clase in acelasi timp din cauza ambiguitatii, in sensul ca putem sa avem 2 clase, X si Y, care sa aiba o metoda, sa zicem,
* public void servitaLaTS(), cu functionalitate diferita in ambele clase, ceea ce duce la ambiguitate, caci clasa Z, care ar vrea sa mosteneasca X si Y, s-ar gandi asa:
* boss, le mostenesc pe X si Y, ambele au o metoda servitaLaTS(), insa in ambele clase se comporta diferit si nu stiu nu am cum sa aleg --> nu pot mosteni 2 clase in acelasi timp
*/

class Dog extends Animal {
    public Dog (String name, String origin) {
        super (origin, name); // super, intr-o clasa copil, se refera la clasa parinte (e ca this-ul, doar ca face referire la obiectul din clasa parinte, nu cea actuala \ copil)
    }

    // trebuie sa implementam doSomething din clasa abstracta Animal
    public void doSomething() {
        System.out.println("I like to eat my own poop, because I'm a stupid dog");
    }

    // acum trebuie sa implementez makeSound din IAnimal
    public void makeSound() {
        System.out.println("Ham ham haaaaaam un bajetzel");
    }

    // pot sa adaug metode specifice clasei
    public void shakeDoggy() {
        System.out.println("Shake shake");
    }
}

class Cat extends Animal {
    public Cat (String name, String origin) {
        super (name, origin);
    }

     // trebuie sa implementam doSomething din clasa abstracta Animal
    public void doSomething() {
        System.out.println("FEED ME HOOMAN");
    }

    // acum trebuie sa implementez makeSound din IAnimal
    public void makeSound() {
        System.out.println("MEOOOOOOWWWWWW");
    }

    // vrem sa suprascriem o metoda din clasa parinte in clasa copil
    @Override
    public void doStuff() {
        super.doStuff(); // asa apelam o metoda din clasa parinte
        System.out.println("Origin with super: " + super.origin);
        System.out.println("Origin with this: " + this.origin);
        // campurile unei clase sunt mostenite -> le putem accesa cu this (numai daca nu sunt private in clasa parinte)
        System.out.println("I do nothing, just sleep, eat, shit and repeat");
    }
}

/*
 * intre clasa Cat (sau Dog) si Animal exista o relatie de tip Is-A (Cat  / Dog este un Animal), de asemenea intre Animal si IAnimal exista o relatie de tip Is-A
 * pe scurt o relatie de tip Is-A e construita prin mostenire (extindere de clasa sau implementare de interfata)
 */

class Human {
    private Cat pussy;
    private Dog doggy; // wink wink

    public Human (Cat pussy, Dog doggy) {
        this.pussy = pussy;
        this.doggy = doggy;
    }

    public Cat getPussy() {
        return pussy;
    }

    // o metoda final nu poate fi suprascrisa in nicio clasa mostenitoare / subclasa
    final public Dog getDoggy() {
        return doggy;
    }
}
/*
 * intre clasa Human si clasa Cat (si Dog) exista o relatie de tip Has-A (agregare), in sensul ca Human are un Cat (si un Dog - suna aiurea in romana, csf)
 */


/* 
 * o clasa poate sa extinda o alta clasa si in acelasi timp sa implementeze interfete 
 *************************************************************************************
 * o clasa final este o clasa ce nu poate fi mostenita / extinsa deloc 
 */
final class Decebal extends Human implements A, B {
    public Decebal (Cat pussy, Dog doggy) {
        super(pussy, doggy);
    }
}

/*
class Ciobanu extends Decebal {
    // vom avea eroare daca facem asa 
} */

// ***********************************************************************
// Comparator vs Comparable - 2 interfete predefinite in Java, folosite pentru compararea a 2 obiecte, in principiu pentru sortarea de colectii

/* 
* Comparator - o clasa ce implementeaza interfata Comparator este folosita ca un instrument de comparare a 2 obiecte, adica cand vrem sa dorim
* sortarea a unei colectii in functie de un criteriu de comparare (de exemplu sortarea unei liste de studenti in ordinea crescatoare a mediei),
* vom da ca parametru la metoda Collections.sort si un obiect care reprezinta instanta clasei care implementeaza Comparator
*/

class Student {
    private double medie;
    private String nume, prenume;

    public Student (String nume, String prenume, double medie) {
        this.medie = medie;
        this.nume = nume;
        this.prenume = prenume;
    }

    public String toString() {
        return "Student: " + prenume + " " + nume + " -> " + medie;
    }

    public double getMedie() {
        return medie;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }
}

// o clasa ce compara studentii intre ei -> criteriu de sortare
// + aici avem warning cu raw types (genericitate)
class StudentComparator implements Comparator {
    @Override // e esential sa specificam Override
    public int compare (Object o1, Object o2) { // -> nu am tipizat Comparator aici -> compare primeste Object ca parametri by default
        Student s1 = (Student) o1;
        Student s2 = (Student) o2;
        // daca au mediile egale + e o varianta ok de a compara numere double in Java -> o recomand pe asta
        if (Double.compare(s1.getMedie(), s2.getMedie()) == 0) {
            return s1.getNume().compareTo(s2.getNume()); // ordonam crescator dupa nume -> descrescator era s2...compareTo(s1...)
        }
        return Double.compare(s1.getMedie(), s2.getMedie()); // ordonam crescator dupa medie
    } // compare -> compara 2 obiecte - returneaza 0 (egale), > / < 0 (crescator / descrescator)
}

// tipizam Comparator cu Student -> compare primeste Student ca parametri
class StudentComparator2 implements Comparator<Student> {
    @Override // e esential sa specificam Override
    public int compare (Student s1, Student s2) {
        // daca au mediile egale + e o varianta ok de a compara numere double in Java -> o recomand pe asta
        if (Double.compare(s1.getMedie(), s2.getMedie()) == 0) {
            return s1.getNume().compareTo(s2.getNume()); // ordonam descrescator dupa nume
        }
        return Double.compare(s2.getMedie(), s1.getMedie()); // ordonam descrescator dupa medie
    }
}


// Comparable -> un obiect ce este o instanta a unei clase ce implementeaza Comparable este comparabila (sesizati cu limba in obraz) cu un alt obiect care reprezinta o instanta a aceleiasi clase
// precum Comparator, este folosit drept criteriu de comparare pentru sortare, insa diferenta fata de Comparator este ca la sortarea cu Collections.sort nu mai e nevoie sa
// dam ca parametru un criteriu de comparare (adica un Comparator), sortarea se face dupa criteriul specificat in metoda compareTo

// treaba cu tipizarea interfetei e fix ca mai sus
class PornStar implements Comparable {
    private String nume, prenume;
    private int varsta;

    public PornStar (String nume, String prenume, int varsta) {
        this.varsta = varsta;
        this.nume = nume;
        this.prenume = prenume;
    }

    private String getNume() {
        return nume;
    }

    private String getPrenume() {
        return prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    @Override
    public int compareTo (Object obj) {
        PornStar porn = (PornStar) obj;
        // daca au varsta egala - sortam crescator dupa nume
        if (this.getVarsta() == porn.getVarsta())
            return this.getNume().compareTo(porn.getNume());
        return this.getVarsta() - porn.getVarsta(); // crescator dupa varsta
    }

    public String toString () {
        return "Star porno: " + prenume + " " + nume + " -> " + varsta + " de ani";
    }
}


public class DemoLab6 {
    public static void main (String[] main) {
        // asa construim clase anonime (nu tine de labul asta, dar are legatura cu mostenirea), adica construim clase fara nume prin extinderea altei clase sau prin implementarea unei interfete
        IAnimal pl = new IAnimal() {
            public void makeSound() {
                System.out.println("AYYY PAPI HARDEEER");
            }
        };
        System.out.println(pl.magicValue); // warning prin faptul ca accesam un membru static prin obiect.cevaStatic
        System.out.println(IAnimal.anotherMagicValue);
        pl.makeSound();

        /*
         * nu putem instantia o clasa abstracta sau o interfata, adica, in cazul nostru, sa scriem direct Animal cezar = new Animal(); sau IAnimal saracin = new IAnimal();
         * insa mai jos putem sa facem o chestie, profitand de relatia Is-A dintre clase 
         */

        Animal kitty = new Cat("Malone", "Focsani <3"); // I <3 Focsani
        Animal vagabond = new Dog("Cockciorba", "Panciu");
        // in cazul de mai sus, vom avea obiecte de tip Animal, insa instantiate ca Cat (cacacacaca) sau ca Dog si nu putem sa folosim metode specifice unei anumite clase

        // vagabond.shakeDoggy(); //-> nu merge, caci vagabond este un Animal instantiat ca Dog, deci nu e Dog, asadar nu pot apela shakeDoggy
        // insa putem sa facem castare
        ((Dog)vagabond).shakeDoggy(); // iar acum vagabond este Dog, deci pot apela shakeDoggy

        kitty.doStuff(); // se apeleaza doStuff din Animal
        ((Cat)kitty).doStuff(); // se apeleaza doStuff din Cat

        Dog javra = new Dog("Coraci", "Puscarie");
        Cat pussy = new Cat("Tibi <3", "Constanta");

        Human jmek = new Human(pussy, javra);

        // Comparator
        ArrayList<Student> list = new ArrayList<>(); // ArrayList este o colectie, vom invata despre colectii in labul 7

        // inainte de sortare
        list.add(new Student("Mihu", "Andrei", 8.50));
        list.add(new Student("Raducanu", "Constantin", 9.20));
        list.add(new Student("Mihalache", "Florin", 8.50));
        System.out.println(list);

        StudentComparator comp = new StudentComparator(); // criterii de comparare
        StudentComparator2 comp2 = new StudentComparator2();

        Collections.sort(list, comp); // sortam crescator dupa medie
        // sortare crescatoare
        System.out.println(list);

        Collections.sort(list, comp2); // sortam descrescator dupa medie
        // sortare descrescatoare
        System.out.println(list);

        // asadar, Comparator il folosim daca pur si simplu sa ordonam o colectie la un moment dat dupa un anumit criteriu

        // Comparable
        // hai sa fim patrioti :)
        ArrayList<PornStar> porn = new ArrayList<>();
        porn.add(new PornStar("Kent", "Nelly", 21));
        porn.add(new PornStar("Romain", "Sandra", 40));
        porn.add(new PornStar("Mocanu", "Madalina", 21)); // oarecum porn star -> videochat mai degraba (a fost cu mine la liceu)
        porn.add(new PornStar("Bunny", "Sweet", 25)); // VPN pe PH ;)
        porn.add(new PornStar("Plugaru", "Alina", 30));
        // neordonat
        System.out.println(porn);

        Collections.sort(porn); // deja avem criteriul de sortare
        // ordonat
        System.out.println(porn);
        // enjoy fapping, u bastards
    }
}