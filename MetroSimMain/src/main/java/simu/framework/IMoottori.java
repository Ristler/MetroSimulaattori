package simu.framework;

import simu.model.Asiakas;

import java.util.ArrayList;
import java.util.LinkedList;

public interface IMoottori { // UUSI
		
	// Kontrolleri käyttää tätä rajapintaa
	
	public void setSimulointiaika(double aika);
	public void setViive(long aika);
	public long getViive();

	public ArrayList<LinkedList<Asiakas>> getJononAsiakkaat();
}
