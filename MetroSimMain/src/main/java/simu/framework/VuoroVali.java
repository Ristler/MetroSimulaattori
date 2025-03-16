package simu.framework;

public class VuoroVali {

	private double aika;
	private static VuoroVali instanssi;

	/**
	 * Luokan tarkoitus on säilyttää metron vuorovälin. Käyttäjän voi muuttaa arvon.
	 */

	private VuoroVali(){
		aika = 500;
	}
	
	public static VuoroVali getInstance(){
		if (instanssi == null){
			instanssi = new VuoroVali();	
		}
		return instanssi;
	}

	/**
	 *
	 * @param aika Asetetaan uusi aika vuorovälille
	 */

	public void setAika(double aika){
		this.aika = aika;
	}

	/**
	 *
	 * @return Palauttaa vuorovälin
	 */

	public double getAika(){
		return aika;
	}
}
