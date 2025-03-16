/**
 * Moottori-luokka on abstrakti luokka, joka toimii simulaation moottorina.
 * Se perii Thread-luokan ja toteuttaa IMoottori-rajapinnan.
 * Moottori vastaa tapahtumien suorittamisesta ja ajan päivittämisestä simulaation aikana.
 */

package simu.framework;


import controller.IKontrolleriForM; // UUSI
import simu.model.Asiakas;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Moottori extends Thread implements IMoottori{  // UUDET MÄÄRITYKSET
	
	private double simulointiaika = 0;
	private long viive = 0;
	
	private Kello kello;
	
	protected Tapahtumalista tapahtumalista;

	protected IKontrolleriForM kontrolleri; // UUSI

	/**
	 * Konstruktori, joka alustaa Moottorin ja tapahtumalistan.
	 * @param kontrolleri Simulaation ohjaamiseen käytettävä kontrolleri.
	 */

	public Moottori(IKontrolleriForM kontrolleri){  // UUSITTU
		
		this.kontrolleri = kontrolleri;  //UUSI

		kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia
		
		tapahtumalista = new Tapahtumalista();
		
		// Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa
	}

	/**
	 * Asettaa simulaation kokonaiskeston.
	 * @param aika Simulointiaika sekunteina.
	 */

	@Override
	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}

	/**
	 * Asettaa simulaation viiveen.
	 * @param viive Viive millisekunteina.
	 */
	
	@Override // UUSI
	public void setViive(long viive) {
		this.viive = viive;
	}

	/**
	 * Palauttaa simulaation viiveen.
	 * @return Viive millisekunteina.
	 */
	
	@Override // UUSI 
	public long getViive() {
		return viive;
	}

	/**
	 * Käynnistää simulaation suorittamalla tapahtumia, kunnes simulointiaika päättyy.
	 */
	
	@Override
	public void run(){ // Entinen aja()
		alustukset(); // luodaan mm. ensimmäinen tapahtuma
		while (simuloidaan()){
			viive(); // UUSI
			kello.setAika(nykyaika());
			suoritaBTapahtumat();
			yritaCTapahtumat();
			kontrolleri.setAjat();
		}
		tulokset();
		
	}


	/**
	 * Suorittaa tapahtumat, joiden tapahtuma-aika on sama kuin nykyinen kellonaika.
	 */
	
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}


	protected abstract void yritaCTapahtumat();

	/**
	 * Palauttaa seuraavan tapahtuman ajan.
	 * @return Seuraavan tapahtuman aika.
	 */
	
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}

	/**
	 * Tarkistaa, jatkuuko simulaatio edelleen.
	 * @return true, jos simulaation aikaa on jäljellä, muuten false.
	 */
	
	private boolean simuloidaan(){
		Trace.out(Trace.Level.INFO, "Kello on: " + kello.getAika());
		return kello.getAika() < simulointiaika;
	}

	/**
	 * Odottaa määritetyn viiveen ennen seuraavaa tapahtumaa.
	 */
			
	private void viive() { // UUSI
		Trace.out(Trace.Level.INFO, "Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Alustaa simulaation. Toteutetaan Moottorin aliluokassa.
	 */

	protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

	/**
	 * Suorittaa yksittäisen tapahtuman. Toteutetaan Moottorin aliluokassa.
	 * @param t Suoritettava tapahtuma.
	 */

	protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

	/**
	 * Tallentaa tai näyttää simulaation tulokset. Toteutetaan Moottorin aliluokassa.
	 */

	protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

	/**
	 * Palauttaa listan asiakkaista, jotka ovat jonossa palvelupisteissä.
	 * @return Lista palvelupisteiden jonoista ja niiden asiakkaista.
	 */

    public abstract ArrayList<LinkedList<Asiakas>> getJononAsiakkaat();
}