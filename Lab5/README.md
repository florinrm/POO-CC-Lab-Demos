# Exceptii
## Ce reprezinta exceptiile?
- Exceptiile reprezinta intreruperi de programe, aparute in timp ce, pe durata rularii lor, apar neregularitati / lucruri de nedorit, de exemplu impartirea la 0 sau deschiderea unui fisier care nu exista in calea precizata de noi.
- In Java, pentru a reprezenta o exceptie, ne folosim, in general, de clasa `Exception`, care este, in principiu, mostenita de alte clase de tip exceptii (adica cele predefinite in Java, precum `FileNotFoundException`, `ArrayIndexOutOfBoundsException`, `NullPointerException`)
## Folosire
```java
class MyException extends Exception {
  public MyException() {
      super("I've got 99 exceptions, but bitch ain't one");
  }
  
  public MyException (String str) {
      super(str);
  }
} // ne cream propria exceptie, daca vrem sa fim cu mot

class Stuff {
  public void doStuff(int smth) {
      if (smth == 69)
          throw new MyException ("U little perv"); // daca avem o conditie pe care noi am vrea sa o evitam in mod normal
          // aruncam exceptia, iar programul se va intrerupe
      System.out.println("Meh, u normie");
  }
}

// acum avem doua modalitati de a "manevra" cazuri cand pot aparea exceptii
// varianta #1
class SistemeLiniareNetede {
    public void makeSmth() {
        try {
          // in try scriem blocul pe care ni-l dorim sa se execute in mod normal
          Random rand = new Random();
          for (int i = 0; i < 10; ++i) {
              doStuff(rand.nextInt(100);
          }
        } catch (MyException e) {
          // am prins exceptia
          e.printStackTrace(); // e ok sa scriem de regula chestia asta in catch
        } catch (Exception e) {
          // daca este sa prindem mai multe exceptii, trebuie sa incepem cu cea mai particulara pana la cea mai generala
          e.printStackTrace();
        } finally {
          // blocul de cod de aici se executa indiferent daca exceptia a fost prinsa sau nu
          System.out.println("Meh");
        }
    }
}

class SistemeLiniareDiscrete {
    public void makeSmth() throws MyException {
       Random rand = new Random();
       for (int i = 0; i < 10; ++i) {
           doStuff(rand.nextInt(100);
       }
      }
    }
}

```
