package simu.model;

import java.util.HashMap;

import simu.framework.Kello;

public class PalveluKeskAika {
    private HashMap<Integer, Double> Saap_saapumislista   = new HashMap<Integer, Double>();
    private HashMap<Integer, Double> lippu_saapumislista  = new HashMap<Integer, Double>();
    private HashMap<Integer, Double> lait_saapumislista   = new HashMap<Integer, Double>();
    private HashMap<Integer, Double> metro1_saapumislista = new HashMap<Integer, Double>();
    private HashMap<Integer, Double> metro2_saapumislista = new HashMap<Integer, Double>();

    // Palveltujen asiakkaiden määrä palvelupisteittäin
    private static int saapPalveltu =    0;          
    private static int lippuPalveltu =   0;       
    private static int laitPalveltu =    0;
    private static int metro1Palveltu =  0;    
    private static int metro2Palveltu =  0;
    
    // Keskimääräinen käsittelyaika palvelupisteittäin
    private static double saapKeskiaika =     0;    
    private static double lippuKeskiaika =    0;   
    private static double laituriKeskiaika =  0;
    private static double metro1Keskiaika =   0;
    private static double metro2Keskiaika =   0;

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

    public void setMetro1Saap(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        metro1_saapumislista.put(id, saapumisaika);
    }

    public void setMetro2Saap(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        metro2_saapumislista.put(id, saapumisaika);
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

    public void setMetro1Pois(int id) {
        Double saapumisaika = metro1_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        metro1Keskiaika += poistumisaika - saapumisaika;
        metro1Palveltu++;
    }

    public void setMetro2Pois(int id) {
        Double saapumisaika = metro1_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        metro1Keskiaika += poistumisaika - saapumisaika;
        metro1Palveltu++;
    }

    // Metodit palauttavat palvelupisteiden keskimääräisen käsittelyajan

    // Veikko: If statment katsoo että luvut eivät ole nollia, jos ovat palauttaa nollan, muuten palauttaa keskiarvon
    // ^ Jos lukua jaetaan nollalla, tulee laskennassa virhe

    public double getSaapKeskiaika() {
        if (saapPalveltu == 0 || saapKeskiaika == 0) {
            return 0;
        } else {
            return (saapKeskiaika / saapPalveltu);
        }
    }

    public double getLippuKeskiaika() {
        if (lippuPalveltu == 0 || lippuKeskiaika == 0) {
            return 0;
        } else {
            return (lippuKeskiaika / lippuPalveltu);
        }
    }

    public double getLaituriKeskiaika(){
        if (laitPalveltu == 0 || laituriKeskiaika == 0) {
            return 0;
        } else {
            return (laituriKeskiaika / laitPalveltu);
        }
    }

    public double getMetro1Keskiaika() {
        if (metro1Palveltu == 0 || metro1Keskiaika == 0) {
            return 0;
        } else {
            return (metro1Keskiaika / metro1Palveltu);
        }
    }

    public double getMetro2Keskiaika() {
        if (metro1Palveltu == 0 || metro1Keskiaika == 0) {
            return 0;
        } else {
            return (metro2Keskiaika / metro2Palveltu);
        }
    }

    public int getSaapPalveltu() {
        return saapPalveltu;
    }

    public int getLippuPalveltu() {
        return lippuPalveltu;
    }

    public int getLaitPalveltu() {
        return laitPalveltu;
    }

    public int getMetro1Palveltu() {
        return metro1Palveltu;
    }

    public int getMetro2Palveltu() {
        return metro1Palveltu;
    }
}


