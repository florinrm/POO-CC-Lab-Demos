import java.*;

class Politician {
	private String name, surname, party;
	public static boolean steal = true;

	/*
	* static - legat de clasa, de general
	* non-static - legat de obiect, de particular - got the idea?
	*/

    /* 
    * blocurile statice se executa doar o odata, doar la prima instantiere a clasei
    */
    static {
        System.out.println("Muie PSD");
    }
    /* 
    * blocurile non-statice se executa la fiecare instantiere a clasei
    */
    {
        System.out.println("Muie Dragnea");
    }

	public Politician (String name, String nume_familie, String party) {
		this.name = name;
		/*
		* folosim this pentru ca avem conflict de nume si in asa mod
		* prin this facem referire in mod explicit la obiectul care reprezinta
		* instanta clasei Politician
		* fara this, am avea name = name, care nu face nimic, ambele facand
		* referire la parametrul dat constructorului clasei
		*/
		surname = nume_familie;
		/* nu avem conflict de nume => nu e musai sa
		* folosim this pentru a face referire la obiect
		* putem sa folosim this in acest caz, insa nu e obligatoriu
		*/
		this.party = party;
	}

	public Politician (String name, String surname) {
		this(name, surname, "Independent");
		/*
			prin acest mod supraincarcam constructorul clasei (e si ea o metoda)
			si prin this-ul de aici apelam constructorul cu 3 parametri in
			acest constructor cu 2 parametri, bagand ca al treilea parametru
			o valoare (sa zicem ca e default)
		*/
	}

	@Override
	public String toString () {
		/*
			prin aceasta metoda (mai bine zis, prin supradefinirea ei, ea fiind
			parte din clasa Object, mostenita de toate clasele din Java),
			putem realiza o afisare de informatii despre obiectul care e instanta
			acestei clase
			astfel, apeland System.out.println(obj), se va afisa de fapt obj.toString
			(poti sa apelezi si System.out.println(obj.toString()), insa e acelasi
			lucru)
		*/
		if (party.equals("Independent") || party.equals("USR"))
			return name + " " + surname + " este un politician ok-ish";
		else
			return name + " " + surname + " din " + party + " ia la pula";
	}

	// getteri si setteri -> modalitate de a accesa membri privati din clasa
	// aka ii infasori in metode publice pentru a ii accesa din afara clasei
	// vezi principii POO

	public void setParty(String party) {
		this.party = party;
	}

	public String getParty() {
		return party; // se poate si this, insa la proasta de profa nu faceti asta
	}

	public void setName(String name) {
		this.name = name; // conflict de nume -> this - got the idea?
	}

	public String getName() {
		return name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurname() {
		return surname;
	}

    public void doStuff() {
        // smth
        doStaticStuff1();
        // putem apela metode statice in metode nonstatice
        System.out.println(steal); // si sa folosim campuri statice in metode non-statice (general -> particular)
    }

    public static void doStaticStuff1() {
        //...
    }

    public static void doStaticStuff2() {
        // doStuff(); 
        // nu putem apela metode non statice in metode statice, doar statice in statice - sesizati? (cu limba in obraz)
        doStaticStuff1();
        // System.out.println(this.name);
        // ... si nici sa folosim membri non - statici in metode statice (nu prea are logica sa folosesti ce tine de obiect 
        // in ce tine de clasa aka particular -> general)
    }
}

public class DemoLab3 {
	public static void main (String[] args) {
		Politician muist = new Politician("Liviu", "Dragnea", "PSD");
		Politician proasta = new Politician("Viorica", "Dancila", "PSD");
		Politician labagiu = new Politician("Calin", "Popescu-Tariceanu", "ALDE");
		Politician jmek = new Politician("Ioan Cezar", "Coraci"); // stiu ca era la PNL, dar bag si eu asa
		// afisam informatii despre obiectele de mai sus
		System.out.println(muist);
		System.out.println(proasta);
		System.out.println(labagiu);
		System.out.println(jmek);

		// afisam info despre clasa
		System.out.println("Sunt toti politicienii hoti? Fals sau adevarat: " + Politician.steal);
		// putem accesa si asa - obj.cevaStatic, insa nu e recomandat, caci static tine de clasa
		System.out.println(muist.steal);
		System.out.println(proasta.steal);
		System.out.println(labagiu.steal);
		System.out.println(jmek.steal);

		// acum schimbam valoarea campului static steal din clasa
		Politician.steal = false;
		System.out.println("Si acum e vreun politician hot? Fals sau adevarat: " + Politician.steal);
		// putem accesa si asa - obj.cevaStatic, insa nu e recomandat, caci static tine de clasa
		System.out.println(muist.steal);
	}
}
