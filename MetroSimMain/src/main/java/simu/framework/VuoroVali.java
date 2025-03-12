package simu.framework;

/*public class VuoroVali {
    private static double waitTime = 500;

    public void Vuorovali() {}

    public static void setWaitTime(double change) {
        waitTime = change;
    }

    public double getWaitTime() {
        return waitTime;
    }
}*/

public class VuoroVali {

	private double aika;
	private static VuoroVali instanssi;
	
	private VuoroVali(){
		aika = 500;
	}
	
	public static VuoroVali getInstance(){
		if (instanssi == null){
			instanssi = new VuoroVali();	
		}
		return instanssi;
	}
	
	public void setAika(double aika){
		this.aika = aika;
	}

	public double getAika(){
		return aika;
	}
}
