import java.*;


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

    public Dog getDoggy() {
        return doggy;
    }
}
/*
 * intre clasa Human si clasa Cat (si Dog) exista o relatie de tip Has-A (agregare), in sensul ca Human are un Cat (si un Dog - suna aiurea in romana, csf)
 */

public class DemoLab4 {
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
    }
}