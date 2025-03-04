package simu.model;

import java.util.HashMap;

import simu.framework.Kello;

public class PalveluKeskAika {
    private HashMap<Integer, Double> Saap_saapumislista = new HashMap<Integer, Double>();
    private HashMap<Integer, Double> lippu_saapumislista = new HashMap<Integer, Double>();
    private HashMap<Integer, Double> lait_saapumislista = new HashMap<Integer, Double>();
    private HashMap<Integer, Double> metro_saapumislista = new HashMap<Integer, Double>();

    private static int saapPalveltu = 0;        // Palveltujen asiakkaiden määrä
    private static int lippuPalveltu = 0;       
    private static int laitPalveltu = 0;
    private static int metroPalveltu = 0;        

    private static double saapKeskiaika = 0;    // Keskimääräinen käsittelyaika
    private static double lippuKeskiaika = 0;
    private static double laituriKeskiaika = 0;
    private static double metroKeskiaika = 0;

    // Metodit tallentavat asiakkaan saapumisajan palvelupisteelle ja id listaan
    public void setSaapumis(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        Saap_saapumislista.put(id, saapumisaika);
    }
    
    public void setLippu(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        lippu_saapumislista.put(id, saapumisaika);
    }

    public void setLaituri(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        lait_saapumislista.put(id, saapumisaika);
    }

    public void setMetro(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        metro_saapumislista.put(id, saapumisaika);
    }

    // Metodit poimivat asiakkaan saapumisajat palvelupisteille ja laskevat keskiarvon
    public void getSaapumis(int id) {
        Double saapumisaika = Saap_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        saapKeskiaika += poistumisaika - saapumisaika;
        saapPalveltu++;
    }

    public void getLippu(int id) {
        Double saapumisaika = lippu_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        lippuKeskiaika += poistumisaika - saapumisaika;
        lippuPalveltu++;
    }

    public void getLaituri(int id) {
        Double saapumisaika = lait_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        laituriKeskiaika += poistumisaika - saapumisaika;
        laitPalveltu++;
    }

    public void getMetro(int id) {
        Double saapumisaika = metro_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        metroKeskiaika += poistumisaika - saapumisaika;
        metroPalveltu++;
    }

    // Metodit palauttavat palvelupisteiden keskimääräisen käsittelyajan

    public double getSaapKeskiaika() {
        return saapKeskiaika / saapPalveltu;
    }

    public double getLippuKeskiaika() {
        return lippuKeskiaika / lippuPalveltu;
    }

    public double getLaituriKeskiaika(){
        return laituriKeskiaika / laitPalveltu;
    }

    public double getMetroKeskiaika() {
        return metroKeskiaika / metroPalveltu;
    }
}


