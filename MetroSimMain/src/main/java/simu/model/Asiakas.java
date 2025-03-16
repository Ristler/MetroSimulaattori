package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;


/**
 * Tämä luokka kuvaa asiakasta, joka saapuu ja poistuu metrolinjalle.
 * Asiakas saa satunnaiset arvot metrolle, lipulle ja saapumisajalle.
 * Luokka tallentaa tietoja asiakkaan saapumisajasta, poistumisajasta ja viipymisestä.
 * Lisäksi luokka seuraa asiakkaiden käsittelyjen määrää sekä asiakkaiden läpimenoaikojen keskiarvoa.
 * Luokka tarjoaa raportointiominaisuuden, joka tulostaa asiakkaan saapumis- ja poistumisajat sekä viipyminen.
 * Luokan muuttujat seuraavat asiakkaiden käsittelyjä ja viipymisten yhteissummaa koko järjestelmän ajalta.
 */

public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static int completedi = 0;
	private int metro;
	private int lippu;
	private static long sum = 0;

	/**
	 * Luo uuden asiakasolion, joka saa satunnaiset arvot metrolle, lipulle ja saapumisajalle.
	 * Asiakas saa yksilöllisen id:n ja metron, joka voi olla joko Metro M1 (1) tai Metro M2 (2).
	 * Lisäksi asiakas saa satunnaisen lipun tyypin, joka voi olla joko Lipun osto (1) tai Kausilippu (2).
	 * Asiakkaan saapumisaika asetetaan kellon hetkellisen ajan mukaan.
	 * Tämä metodi tulostaa myös lokiin uuden asiakkaan tiedot.
	 */
	public Asiakas(){
	    id = i++;
		metro = (int)(Math.random() * 2) + 1; // 1 = Metro M1 , 2 = Metro M2
		lippu = (int)(Math.random() * 2) + 1; // 1 = Lipun osto , 2 = Kausilippu
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas:" + id + ":"+saapumisaika+ " metro: "+metro);
	}

	/**
	 * Palauttaa asiakkaan yksilöllisen id:n.
	 * Tämä metodi palauttaa asiakkaalle luodun id:n, joka on uniikki koko sovelluksen aikana.
	 * @return Asiakkaan id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Palauttaa asiakkaan yksilöllisen poistumisajan.
	 * @return Asiakkaan poistumisaika.
	 */
	public double getPoistumisaika() {
		return poistumisaika;
	}

	/**
	 * Asettaa asiakkaan poistumisajan.
	 * @param poistumisaika
	 */
	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	/**
	 * Palauttaa asiakkaan saapumisajan.
	 * @return
	 */
	public double getSaapumisaika() {
		return saapumisaika;
	}

	/**
	 * Asettaa asiakkaan saapumisajan.
	 * @param saapumisaika
	 */
	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}



	/**
	 * Palauttaa kaikkien asiakkaiden läpimenoaikojen yhteissumman.
	 * Tämä metodi palauttaa `sum`-muuttujan, joka pitää sisällään kaikkien asiakkaiden
	 * viipymien (poistumisajan ja saapumisajan erotus) yhteenlasketun arvon.
	 * Tämä summa kasvaa aina, kun asiakas saapuu ja poistuu, ja sitä käytetään myöhemmin
	 * laskemaan keskiarvo asiakkaiden läpimenoajoista.
	 * @return Kaikkien asiakkaiden läpimenoaikojen yhteissumma.
	 */
	static public long getSum() {
		return sum;
	}

	/**
	 * Palauttaa asiakasmäärän, joka on myös seuraavan luodun asiakkaan ID.
	 * Tämä metodi palauttaa muuttujan `i`, joka toimii asiakastunnisteena (ID:nä).
	 * Muuttuja `i` kasvaa aina, kun uusi asiakas luodaan, ja seuraavalla asiakkaalla
	 * on edellisen asiakkaan ID:n seuraava arvo. ID:tä käytetään yksilöimään jokainen asiakas.
	 * @return Asiakasmäärä (seuraavan luodun asiakkaan ID).
	 */
	static public int getI() {
		return i;
	}

	/**
	 * Palauttaa asiakkaiden käsittelyjen kokonaismäärän.
	 * Tämä metodi palauttaa kuinka monta asiakasta on käsitelty, eli kuinka monta kertaa
	 * asiakas on saapunut ja poistunut, joka lisätään `completedi`-muuttujaan.
	 * @return Asiakkaiden käsittelyjen kokonaismäärä.
	 */
	static public int getcompletedi() {
		return completedi;
	}

	/**
	 * Palautetaan millä metrolla asiakas haluaa matkustaa.
	 * @return metro
	 */
	public int getMetro() {
		return metro;
	}

	/**
	 * Palautetaan minkä tyyppinen lippu asiakkaalla on / haluaa.
	 * @return lippu
	 */
	public int getLippu(){
		return lippu;
	}

	/**
	 * Tuottaa raportin asiakkaan saapumisajasta, poistumisajasta ja viipyymisajasta.
	 * Lisäksi metodi laskee asiakkaiden läpimenoaikojen keskiarvon ja tulostaa sen konsoliin.
	 * Tämä metodi seuraa asiakkaan saapumista ja poistumista, ja laskee kuinka kauan asiakas on ollut järjestelmässä.
	 * Raportissa näytetään yksittäisen asiakkaan tiedot sekä koko järjestelmän asiakaskannan läpimenoaikojen keskiarvo.
	 */
	public void raportti(){
		Trace.out(Trace.Level.INFO, "Asiakas "+id+ " saapui:" +saapumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui:" +poistumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " viipyi:" +(poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		completedi++;
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo "+ keskiarvo);
	}

}
