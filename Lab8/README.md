# Clase interne
## Ce reprezinta clasele interne?
- Pe scurt, aceste clase sunt clase declarate si / sau implementate in interiorul unor alte clase (parinte, am putea zice).
## Tipuri de clase interne
- Clase interne normale / non - statice -> o clasa ca membru al unei alte clase
- Clase interne statice -> de asemenea o clasa ca membry al unei alte clase
- Clase anonime -> clase fara nume ce reprezinta extinderea unei clase sau implementarea unei interfete
- Clase locale -> clase realizate in interiorul metodelor
## Clasa interna normala
```java
  class OuterClass {
    public int x;
    public OuterClass (int x) {
    	this.x = x;
    }
    class InnerClass {
	public int x, y;
	public InnerClass (int x, int y) {
		Outer.this.x = x; // face referire la x-ul in InnerClass
		this.y = y;
	}
    }
  }
  Outer out = new Outer(420);
  Outer.InnerClass in = out.new InnerClass(69, 69); // instantierea
```
- Utilizare: noi folosim clase interne (fie statice sau non-statice) daca le folosim strict in clasa mare in care se afla acestea (mai mult de design si principii POO)
## Clasa interna statica
```java
class OuterClass {
    public int x;
    public OuterClass (int x) {
    	this.x = x;
    }
    static class InnerClass {
	public int x, y;
	public InnerClass (int x, int y) {
		Outer.this.x = x; // face referire la x-ul in InnerClass
		this.y = y;
	}
    }
}
Outer out = new Outer(420);
Outer.StaticInnerClass in3 = new Outer.StaticInnerClass(69, 69);
```
## Clasa anonima
```java
  // vrem sa implementam interfata Comparator folosind clase anonime
  Comparator<String> comp = new Comparator<>() {
      @Override
      public int compare (String s1, String s2) {
          return s2.compareTo(s1);
      }
  };
  // dorim sa se adauge doar numere pare intr-un Vector de numere intregi
  Vector<Integer> vect = new Vector<Integer>(){
	@Override
	public boolean add (Integer e) {
		if (e % 2 == 0)
			return super.add(e);
		return false;
	}
   };
```
- Utilizare: adaugare / modificare / implementare de metode putine (2 - 3) in clase sau interfete, adica o mostenire, insa mai minimalista
- Lectura suplimentara legata de lambda expresii (mai multe detalii la [PP](http://elf.cs.pub.ro/pp/)):
1) https://hashnode.com/post/anonymous-functions-in-java-explained-cj1opkbj8000sml53bsq6r6cj
2) https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
## Clasa locala
```java
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
```
- Utilizare: folosirea doar la nivelul metodei respective si nu in alte metode a clasei locale
