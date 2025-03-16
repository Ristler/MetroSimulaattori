package simu.model;

import java.util.HashMap;

import simu.framework.Kello;

/**
 * Luokan tarkoitus on säilyttää matkustajan saapumisaika eri palvelupisteille sekä keskimääräinen palveluaika.
 * Luokassa säilytetään myös palveltujen asiakkaiden määrä.
 */

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

    // Metodit tallentavat asiakkaan saapumisajan palvelupisteille

    /**
     *
     * @param id tallenetaan saapuminen palvelupisteen listaan. Listaan tallennetaan myös saapumisaika
     * palvelupisteelle.
     */

    public void setSaapumisSaap(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        Saap_saapumislista.put(id, saapumisaika);
    }

    /**
     * Asettaa saapumisajan lippu-id:lle.
     * Tämä metodi tallentaa asiakkaan saapumisajan `lippu_saapumislista`-kokoelmaan
     * käyttämällä asiakas-ID:tä avaimena ja saapumisaikaa arvona.
     * @param id Asiakkaan yksilöllinen tunniste (ID), joka liitetään saapumisaikaan.
     */
    public void setLippuSaap(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        lippu_saapumislista.put(id, saapumisaika);
    }

    /**
     * Asettaa saapumisajan laiturille annetulle asiakas-ID:lle.
     * Tämä metodi tallentaa asiakkaan saapumisajan `lait_saapumislista`-kokoelmaan
     * käyttämällä asiakas-ID:tä avaimena ja saapumisaikaa arvona.
     * @param id Asiakkaan yksilöllinen tunniste (ID), joka liitetään saapumisaikaan.
     */
    public void setLaituriSaap(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        lait_saapumislista.put(id, saapumisaika);        
    }

    /**
     * Asettaa saapumisajan Metro M1:lle annetulle asiakas-ID:lle.
     * Tämä metodi tallentaa asiakkaan saapumisajan `metro1_saapumislista`-kokoelmaan
     * käyttämällä asiakas-ID:tä avaimena ja saapumisaikaa arvona.
     * @param id Asiakkaan yksilöllinen tunniste (ID), joka liitetään saapumisaikaan.
     */
    public void setMetro1Saap(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        metro1_saapumislista.put(id, saapumisaika);
    }


    /**
     * Asettaa saapumisajan Metro M2:lle annetulle asiakas-ID:lle.
     * Tämä metodi tallentaa asiakkaan saapumisajan `metro2_saapumislista`-kokoelmaan
     * käyttämällä asiakas-ID:tä avaimena ja saapumisaikaa arvona.
     * @param id Asiakkaan yksilöllinen tunniste (ID), joka liitetään saapumisaikaan.
     */
    public void setMetro2Saap(int id) {
        Double saapumisaika = Kello.getInstance().getAika();
        metro2_saapumislista.put(id, saapumisaika);
    }

    // Metodit tallentavat asiakkaan poistumisajan palvelupisteeltä ja laskevat käsittelyajan

    /**
     * Asettaa asiakkaan saapumis-poistumisajan ja laskee saapumisen ja poistumisen väliin jäävän ajan.
     * Tämä metodi hakee asiakkaan saapumisajan `Saap_saapumislista`-kokoelmasta käyttäen asiakas-ID:tä,
     * asettaa asiakkaan poistumisajan ja laskee saapumisen ja poistumisen välisen ajan.
     * Saapumisen ja poistumisen välisestä ajasta pidetään kirjaa `saapKeskiaika`-muuttujassa.
     * Asiakkaan käsittelymäärä kasvaa yhdellä.
     * @param id Asiakkaan yksilöllinen tunniste (ID), jonka saapumis- ja poistumisajat käsitellään.
     */
    public void setSaapumisPois(int id) {
        Double saapumisaika = Saap_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        saapKeskiaika += poistumisaika - saapumisaika;
        saapPalveltu++;
    }

    /**
     * Asettaa asiakkaan lippu-saapumisen poistumisajan ja laskee lippu-saapumisen ja poistumisen väliin jäävän ajan.
     * Tämä metodi hakee asiakkaan saapumisajan `lippu_saapumislista`-kokoelmasta käyttäen asiakas-ID:tä,
     * asettaa asiakkaan poistumisajan ja laskee saapumisen ja poistumisen välisen ajan.
     * Lippu-saapumisen ja poistumisen välistä aikaa pidetään kirjaa `lippuKeskiaika`-muuttujassa.
     * Asiakkaan käsittelymäärä kasvaa yhdellä.
     * @param id Asiakkaan yksilöllinen tunniste (ID), jonka lippu-saapumis- ja poistumisajat käsitellään.
     */
    public void setLippuPois(int id) {
        Double saapumisaika = lippu_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        lippuKeskiaika += poistumisaika - saapumisaika;
        lippuPalveltu++;
    }

    /**
     * Asettaa asiakkaan laituri poistumisajan ja laskee laituri-saapumisen ja poistumisen väliin jäävän ajan.
     * Tämä metodi hakee asiakkaan saapumisajan `lait_saapumislista`-kokoelmasta käyttäen asiakas-ID:tä,
     * asettaa asiakkaan poistumisajan ja laskee saapumisen ja poistumisen välisen ajan.
     * Laituri-saapumisen ja poistumisen välistä aikaa pidetään kirjaa `laituriKeskiaika`-muuttujassa.
     * Asiakkaan käsittelymäärä kasvaa yhdellä.
     * @param id Asiakkaan yksilöllinen tunniste (ID), jonka laituri-saapumis- ja poistumiset käsitellään.
     */
    public void setLaituriPois(int id) {
        Double saapumisaika = lait_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        laituriKeskiaika += poistumisaika - saapumisaika;
        laitPalveltu++;
    }

    /**
     * Asettaa asiakkaan metro 1 poistumisajan ja laskee metro 1 -saapumisen ja poistumisen väliin jäävän ajan.
     * Tämä metodi hakee asiakkaan saapumisajan `metro1_saapumislista`-kokoelmasta käyttäen asiakas-ID:tä,
     * asettaa asiakkaan poistumisajan ja laskee saapumisen ja poistumisen välisen ajan.
     * Metro 1 -saapumisen ja poistumisen välistä aikaa pidetään kirjaa `metro1Keskiaika`-muuttujassa.
     * Asiakkaan käsittelymäärä kasvaa yhdellä.
     * @param id Asiakkaan yksilöllinen tunniste (ID), jonka metro 1 -saapumis- ja poistumiset käsitellään.
     */
    public void setMetro1Pois(int id) {
        Double saapumisaika = metro1_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        metro1Keskiaika += poistumisaika - saapumisaika;
        metro1Palveltu++;
    }

    /**
     * Asettaa asiakkaan metro 2 poistumisajan ja laskee metro 2 -saapumisen ja poistumisen väliin jäävän ajan.
     * Tämä metodi hakee asiakkaan saapumisajan `metro2_saapumislista`-kokoelmasta käyttäen asiakas-ID:tä,
     * asettaa asiakkaan poistumisajan ja laskee saapumisen ja poistumisen välisen ajan.
     * Metro 2 -saapumisen ja poistumisen välistä aikaa pidetään kirjaa `metro2Keskiaika`-muuttujassa.
     * Asiakkaan käsittelymäärä kasvaa yhdellä.
     * @param id Asiakkaan yksilöllinen tunniste (ID), jonka metro 2 -saapumisen ja poistumiset käsitellään.
     */
    public void setMetro2Pois(int id) {
        Double saapumisaika = metro2_saapumislista.get(id);
        Double poistumisaika = Kello.getInstance().getAika();
        metro2Keskiaika += poistumisaika - saapumisaika;
        metro2Palveltu++;
    }

    /**
     *
     * @return palauttavat eri palvelupisteiden keskimääräisen palveluajan.
     */
    public double getSaapKeskiaika() {
        if (saapPalveltu == 0 || saapKeskiaika == 0) {
            return 0;
        } else {
            return (saapKeskiaika / saapPalveltu);
        }
    }

    /**
     * Palauttaa lippupalveluiden keskimääräisen käsittelyajan.
     * Tämä metodi laskee ja palauttaa keskimääräisen käsittelyajan lippupalveluiden osalta.
     * Jos lippupalveluja ei ole käsitelty tai keskiarvo on nolla, metodi palauttaa nollan.
     * Muuten se palauttaa lippupalveluiden keskimääräisen käsittelyajan jakamalla kokonaiskäsittelyajan
     * käsiteltyjen lippupalveluiden määrällä.
     * @return Lippupalveluiden keskimääräinen käsittelyaika.
     *         Palauttaa 0, jos lippupalveluja ei ole käsitelty tai keskiarvo on nolla.
     */
    public double getLippuKeskiaika() {
        if (lippuPalveltu == 0 || lippuKeskiaika == 0) {
            return 0;
        } else {
            return (lippuKeskiaika / lippuPalveltu);
        }
    }

    /**
     * Palauttaa laituri-palveluiden keskimääräisen käsittelyajan.
     * Tämä metodi laskee ja palauttaa keskimääräisen käsittelyajan laituri-palveluiden osalta.
     * Jos laituri-palveluja ei ole käsitelty tai keskiarvo on nolla, metodi palauttaa nollan.
     * Muuten se palauttaa laituri-palveluiden keskimääräisen käsittelyajan jakamalla kokonaiskäsittelyajan
     * käsiteltyjen laituri-palveluiden määrällä.
     * @return Laituri-palveluiden keskimääräinen käsittelyaika.
     *         Palauttaa 0, jos laituri-palveluja ei ole käsitelty tai keskiarvo on nolla.
     */
    public double getLaituriKeskiaika(){
        if (laitPalveltu == 0 || laituriKeskiaika == 0) {
            return 0;
        } else {
            return (laituriKeskiaika / laitPalveltu);
        }
    }
    /**
     * Palauttaa Metro M1 -palveluiden keskimääräisen käsittelyajan.
     * Tämä metodi laskee ja palauttaa keskimääräisen käsittelyajan Metro M1 -palveluiden osalta.
     * Jos Metro M1 -palveluja ei ole käsitelty tai keskiarvo on nolla, metodi palauttaa nollan.
     * Muuten se palauttaa Metro M1 -palveluiden keskimääräisen käsittelyajan jakamalla kokonaiskäsittelyajan
     * käsiteltyjen Metro M1 -palveluiden määrällä.
     * @return Metro M1 -palveluiden keskimääräinen käsittelyaika.
     *         Palauttaa 0, jos Metro M1 -palveluja ei ole käsitelty tai keskiarvo on nolla.
     */
    public double getMetro1Keskiaika() {
        if (metro1Palveltu == 0 || metro1Keskiaika == 0) {
            return 0;
        } else {
            return (metro1Keskiaika / metro1Palveltu);
        }
    }

    /**
     * Palauttaa Metro M2 -palveluiden keskimääräisen käsittelyajan.
     * Tämä metodi laskee ja palauttaa keskimääräisen käsittelyajan Metro M2 -palveluiden osalta.
     * Jos Metro M2 -palveluja ei ole käsitelty tai keskiarvo on nolla, metodi palauttaa nollan.
     * Muuten se palauttaa Metro M2 -palveluiden keskimääräisen käsittelyajan jakamalla kokonaiskäsittelyajan
     * käsiteltyjen Metro M2 -palveluiden määrällä.
     * @return Metro M2 -palveluiden keskimääräinen käsittelyaika.
     *         Palauttaa 0, jos Metro M2 -palveluja ei ole käsitelty tai keskiarvo on nolla.
     */
    public double getMetro2Keskiaika() {
        if (metro2Palveltu == 0 || metro2Keskiaika == 0) {
            return 0;
        } else {
            return (metro2Keskiaika / metro2Palveltu);
        }
    }



    /**
     * Palauttaa käsiteltyjen saapumisten määrän.
     * Tämä metodi palauttaa kokonaismäärän siitä, kuinka monta saapumista on käsitelty.
     * Saapumisia seurataan, ja jokainen saapuminen kasvattaa tätä lukua.
     * @return Käsiteltyjen saapumisten määrä.
     */
    public int getSaapPalveltu() {
        return saapPalveltu;
    }

    /**
     * Palauttaa käsiteltyjen lippujen määrän.
     * Tämä metodi palauttaa kokonaismäärän siitä, kuinka monta lippua on käsitelty.
     * Lippuja seurataan, ja jokainen käsitelty lippu kasvattaa tätä lukua.
     * @return Käsiteltyjen lippujen määrä.
     */
    public int getLippuPalveltu() {
        return lippuPalveltu;
    }

    /**
     * Palauttaa käsiteltyjen laiturien määrän.
     * Tämä metodi palauttaa kokonaismäärän siitä, kuinka monta laituria on käsitelty.
     * Laitureita seurataan, ja jokainen käsitelty laituritapaus kasvattaa tätä lukua.
     * @return Käsiteltyjen laiturien määrä.
     */
    public int getLaitPalveltu() {
        return laitPalveltu;
    }

    /**
     * Palauttaa käsiteltyjen Metro M1 -asiakkaiden määrän.
     * Tämä metodi palauttaa kokonaismäärän siitä, kuinka monta Metro M1 -asiakasta on käsitelty.
     * Käsitellyt asiakkaat kasvattavat tätä lukua aina, kun asiakas on saapunut ja poistunut.
     * @return Käsiteltyjen Metro M1 -asiakkaiden määrä.
     */
    public int getMetro1Palveltu() {
        return metro1Palveltu;
    }

    /**
     * Palauttaa käsiteltyjen Metro M2 -asiakkaiden määrän.
     * Tämä metodi palauttaa kokonaismäärän siitä, kuinka monta Metro M2 -asiakasta on käsitelty.
     * Käsitellyt asiakkaat kasvattavat tätä lukua aina, kun asiakas on saapunut ja poistunut.
     * @return Käsiteltyjen Metro M2 -asiakkaiden määrä.
     */
    public int getMetro2Palveltu() {
        return metro2Palveltu;
    }
}


