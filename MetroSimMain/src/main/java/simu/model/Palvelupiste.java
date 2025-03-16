package simu.model;

import simu.framework.*;
import java.util.LinkedList;
import eduni.distributions.ContinuousGenerator;

/**
 * Luokka pyörittää palvelupisteiden methodehja.
 */
public class Palvelupiste {

	private final LinkedList<Asiakas> jono = new LinkedList<>(); // Tietorakennetoteutus
	private final ContinuousGenerator generator;
	private final Tapahtumalista tapahtumalista;
	private final TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
	
	//JonoStartegia strategia; //optio: asiakkaiden järjestys
	
	private boolean varattu = false;

	/**
	 * Palvelupisteen luonti
	 * <p>
	 * @param generator Antaa ContinousGeneraattorin jolla on Mean ja Variance
	 * @param tapahtumalista Antaa Tapahtumalistan
	 * @param tyyppi Antaa Tapahtumatyypin
	 */
	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;

		System.out.println(tyyppi.name());
				
	}

	/**
	 * Asiakkaan lisäys
	 * <p>
	 * Lisää asiakkaan jonoon
	 * @param a Asiakas
	 */
	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
	}

	/**
	 * MetroM1 jonon saanti
	 * <p>
	 * @return palauttaa jononkoon
	 */
	public int getJononKokoM1() {
		return (int) jono.stream().filter(asiakas -> asiakas.getMetro() == 1).count();
	}

	/**
	 * MetroM2 jonon saanti
	 * <p>
	 * @return palauttaa jononkoon
	 */
	public int getJononKokoM2() {
		return (int) jono.stream().filter(asiakas -> asiakas.getMetro() == 2).count();
	}

	/**
	 * Jonon saanti
	 * <p>
	 * @return palauttaa jononkoon
	 */
	public Asiakas otaJonosta(TapahtumanTyyppi tuotutapahtumanTyyppi){  // Poistetaan palvelussa ollut
		varattu = false;

		if (tuotutapahtumanTyyppi == TapahtumanTyyppi.METROM1 || tuotutapahtumanTyyppi == TapahtumanTyyppi.METROM2) {
			for (Asiakas asiakas : jono) {
				if (asiakas.getMetro() == 1 && tuotutapahtumanTyyppi == TapahtumanTyyppi.METROM1) {
					System.err.println("METROM1");
					return jono.remove(jono.indexOf(asiakas));
				} else if (asiakas.getMetro() == 2 && tuotutapahtumanTyyppi == TapahtumanTyyppi.METROM2) {
					System.err.println("METROM2");
					return jono.remove(jono.indexOf(asiakas));
				}
			}
		}

		return jono.poll();
	}

	/**
	 * Avaa palvelun
	 */
	public void avaaPalvelu(){
		varattu = false;
	}


	/**
	 * Avaa palvelun
	 */
	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		
		Trace.out(Trace.Level.INFO, this.skeduloitavanTapahtumanTyyppi.name() + " Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
		
		varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
	}

	/**
	 * Jononkoon palautus
	 * <p>
	 * @return Palauttaa jonon koon
	 */
	public int jononKoko(){
		return jono.size();
	}

	/**
	 * Jonon palautus
	 * <p>
	 * @return Palauttaa jonon
	 */
	public LinkedList<Asiakas> getJono() {
		return jono;
	}

	/**
	 * Varauksen katsominen
	 * <p>
	 * @return Palauttaa onko palvelupiste varattu
	 */
	public boolean onVarattu(){
		return varattu;
	}
	/**
	 * Jonon katsominen
	 * <p>
	 * @return Palauttaa onko jonossa asiakkaita
	 */
	public boolean onJonossa(){
		return jono.size() != 0;
	}

}
