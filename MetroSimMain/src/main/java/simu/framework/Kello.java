package simu.framework;

public class Kello {

	private double aika;
	private static Kello instanssi;

	/**
	 * Konstruktori, joka alustaa kellon ajan nollaan.
	 * Tämä on yksityinen konstruktori, koska luokka noudattaa
	 * yksittäisolion (Singleton) suunnittelumallia.
	 */
	private Kello(){
		aika = 0;
	}

	/**
	 * Palauttaa Kello-luokan yksittäisinstanssin.
	 * Jos instanssia ei ole vielä luotu, se luodaan ja palautetaan.
	 *
	 * @return Kello-luokan ainoa instanssi.
	 */
	public static Kello getInstance(){
		if (instanssi == null){
			instanssi = new Kello();	
		}
		return instanssi;
	}

	/**
	 * Asettaa kellon ajan arvon.
	 *
	 * @param aika Aika, joka asetetaan kellolle. Aika esitetään double-tyyppisenä.
	 */
	public void setAika(double aika){
		this.aika = aika;
	}

	/**
	 * Hakee kellon nykyisen ajan arvon.
	 *
	 * @return Kellon nykyinen aika, joka on tyypiltään double.
	 */
	public double getAika(){
		return aika;
	}
}
