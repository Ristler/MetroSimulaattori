package simu.model;

import java.util.HashMap;

import simu.framework.Kello;

public class PalveluKeskAika {
    private HashMap<Integer, Double> Saap_saapumislista =   new HashMap<Integer, Double>();
    private HashMap<Integer, Double> lippu_saapumislista =  new HashMap<Integer, Double>();
    private HashMap<Integer, Double> lait_saapumislista =   new HashMap<Integer, Double>();
    private HashMap<Integer, Double> metro_saapumislista =  new HashMap<Integer, Double>();

    // Palveltujen asiakkaiden määrä palvelupisteittäin
    private static int saapPalveltu =   0;          
    private static int lippuPalveltu =  0;       
    private static int laitPalveltu =   0;
    private static int metroPalveltu =  0;        
    
    // Keskimääräinen käsittelyaika palvelupisteittäin
    private static double saapKeskiaika =     0;    
    private static double lippuKeskiaika =    0;   
    private static double laituriKeskiaika =  0;
    private static double metroKeskiaika =    0;

    // Metodit tallentavat asiakkaan saapumisajan palvelupisteelle

    public void setSaapumisSaap(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        Saap_saapumislista.put(id, saapumisaika);
    }
    
    public void setLippuSaap(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        lippu_saapumislista.put(id, saapumisaika);
    }

    public void setLaituriSaap(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        lait_saapumislista.put(id, saapumisaika);        
    }

    public void setMetroSaap(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        metro_saapumislista.put(id, saapumisaika);
    }

    // Metodit tallentavat asiakkaan poistumisajan palvelupisteeltä ja laskevat käsittelyajan

    public void setSaapumisPois(int id) {
        Double saapumisaika = Saap_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        saapKeskiaika += poistumisaika - saapumisaika;
        saapPalveltu++;
    }

    public void setLippuPois(int id) {
        Double saapumisaika = lippu_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        lippuKeskiaika += poistumisaika - saapumisaika;
        lippuPalveltu++;
    }

    public void setLaituriPois(int id) {
        Double saapumisaika = lait_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        laituriKeskiaika += poistumisaika - saapumisaika;
        laitPalveltu++;
    }

    public void setMetroPois(int id) {
        Double saapumisaika = metro_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        metroKeskiaika += poistumisaika - saapumisaika;
        metroPalveltu++;
    }

    // Metodit palauttavat palvelupisteiden keskimääräisen käsittelyajan
    public double getSaapKeskiaika() {
        return (saapKeskiaika / saapPalveltu);
    }

    public double getLippuKeskiaika() {
        return (lippuKeskiaika / lippuPalveltu);
    }

    public double getLaituriKeskiaika(){
        return (laituriKeskiaika / laitPalveltu);
    }

    public double getMetroKeskiaika() {
        return (metroKeskiaika / metroPalveltu);
    }
}


