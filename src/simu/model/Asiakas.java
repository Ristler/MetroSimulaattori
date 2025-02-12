package simu.model;

import simu.framework.*;

// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static int completedi = 0;
	private static long sum = 0;
	
	public Asiakas(){
	    id = i++;
	    
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi matkustaja nro " + id + " saapui klo "+saapumisaika);
	}

	public double getPoistumisaika() {
		return poistumisaika;
	}

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}

	public int getId() {
		return id;
	}

	static public long getSum() {
		return sum;
	}

	static public int getI() {
		return i;
	}

	static public int getcompletedi() {
		return completedi;
	}
	
	public void raportti(){
		Trace.out(Trace.Level.INFO, "\nMatkustaja "+id+ " valmis! ");
		Trace.out(Trace.Level.INFO, "Matkustaja "+id+ " saapui: " +saapumisaika);
		Trace.out(Trace.Level.INFO,"Matkustaja "+id+ " poistui: " +poistumisaika);
		Trace.out(Trace.Level.INFO,"Matkustaja "+id+ " viipyi: " +(poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		completedi++;
		double keskiarvo = sum/id;
		System.out.println("Matkustajien läpimenoaikojen keskiarvo tähän asti "+ keskiarvo);
	}

}
