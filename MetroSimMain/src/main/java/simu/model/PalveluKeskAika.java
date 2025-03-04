package simu.model;

import java.util.HashMap;

import simu.framework.Kello;

public class PalveluKeskAika {
    private HashMap<Integer, Double> lippu_saapumislista = new HashMap<Integer, Double>();
    private HashMap<Integer, Double> lait_saapumislista = new HashMap<Integer, Double>();

    private static int lippuPalveltu = 0;       // Palveltujen asiakkaiden määrä
    private static int laitPalveltu = 0;        

    private static double lippuKeskiaika = 0;   // Keskimääräinen käsittelyaika
    private static double laituriKeskiaika = 0;

    // Metodi tallentaa asiakkaan saapumisajan ja id lippu_aikalistaan
    public void setLippu(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        lippu_saapumislista.put(id, saapumisaika);
    }

    // Metodi tallentaa asiakkaan saapumisajan ja id lait_aikalistaan
    public void setLaituri(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        lait_saapumislista.put(id, saapumisaika);
    }

    // Metodi poimii asiakkaan saapumisajan ja laskee keskiarvon
    public void getLippu(int id) {
        Double saapumisaika = lippu_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        lippuKeskiaika += poistumisaika - saapumisaika;
        lippuPalveltu++;
    }

    // Metodi poimii asiakkaan saapumisajan ja laskee keskiarvon
    public void getLaituri(int id) {
        Double saapumisaika = lait_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        laituriKeskiaika += poistumisaika - saapumisaika;
        laitPalveltu++;
    }

    // Palauttaa lipunmyyntipisteen keskimääräisen käsittelyajan
    public double getLippuKeskiaika() {
        return lippuKeskiaika / lippuPalveltu;
    }

    // Palauttaa laiturin keskimääräisen käsittelyajan
    public double getLaituriKeskiaika(){
        return laituriKeskiaika / laitPalveltu;
    }
}


