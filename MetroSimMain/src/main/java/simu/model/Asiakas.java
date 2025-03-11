package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;


// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static int completedi = 0;
	private int metro;
	private int lippu;
	private static long sum = 0;
	
	public Asiakas(){
	    id = i++;
		metro = (int)(Math.random() * 2) + 1; // 1 = Metro M1 , 2 = Metro M2
		lippu = (int)(Math.random() * 2) + 1; // 1 = Lipun osto , 2 = Kausilippu
	    
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas:" + id + ":"+saapumisaika+ " metro: "+metro);
	}

	public int getId() {
		return id;
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

	static public long getSum() {
		return sum;
	}

	static public int getI() {
		return i;
	}

	static public int getcompletedi() {
		return completedi;
	}

	// Palautetaan, millä metrolla asiakas haluaa matkustaa
	public int getMetro() {
		return metro;
	}

	// Palautetaan, minkä tyyppinen lippu asiakkaalla on/haluaa
	public int getLippu(){
		return lippu;
	}
	
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
