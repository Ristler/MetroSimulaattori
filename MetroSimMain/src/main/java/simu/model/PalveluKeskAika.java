package simu.model;

import java.util.HashMap;

public class PalveluKeskAika {
    private HashMap<Integer, Long> lippu_aikalista = new HashMap<Integer, Long>();
    private HashMap<Integer, Long> lait_aikalista = new HashMap<Integer, Long>();
    private static double lippuKeski = 0;
    private static double laituriKeski = 0;

    // Metodi tallentaa asiakkaan saapumisajan lippu_aikalistaan
    public void saapLt(int id, long aika) {
        lippu_aikalista.put(id, aika);
    }

    // Metodi tallentaa asiakkaan saapumisajan lait_aikalistaan
    public void saapLait(int id, long aika) {
        lait_aikalista.put(id, aika);
    }

    // Metodi poistaa asiakkaan saapumisajan ja laskee keskiarvon
    public void poistLt(int id, Long aika) {
        Long saapumisAika = lippu_aikalista.get(id);
        lippuKeski += aika - saapumisaika;
    }

    // Metodi poistaa asiakkaan saapumisajan ja laskee keskiarvon
    public void poistLait(int id, Long aika) {
        Long saapumisAika = lait_aikalista.get(id);
        laituriKeski += aika - saapumisaika;
    }

    // Palauttaa lipunmyyntipisteen keskimääräisen käsittelyajan
    public double getLippuKeskiaika() {
        return lippuKeski / lippu_aikalista.size();
    }

    // Palauttaa laiturin keskimääräisen käsittelyajan
    public double getLtKeski(){
        return laituriKeski / lait_aikalista.size();
    }
}


