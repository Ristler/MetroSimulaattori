package simu.model;

import simu.framework.*;
import dao.MetroDao;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import controller.IKontrolleriForM;
import view.SimulaattorinGUI;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * OmaMoottori on ohjelman logiikka. OmaMoottorissa käsitellään tapahtumat, luodaan metromatkustajat
 * ja vuorotellaan metrolinjojen saapuminen asemalle.
 */

public class OmaMoottori extends Moottori {

    private Saapumisprosessi saapumisprosessi;

    private Palvelupiste[] palvelupisteet;

    private PalveluKeskAika palveluKeskAika;

    private MetroDao metroDao;

    private SimulaattorinGUI gui;

    private Kello kello;

    private boolean M1_turn;

    private double lastTime = 0;

    private VuoroVali waitTime;

    public OmaMoottori(IKontrolleriForM kontrolleri) {

        super(kontrolleri);

        metroDao = new MetroDao();
        palvelupisteet = new Palvelupiste[5];
        palveluKeskAika = new PalveluKeskAika();
        kello = Kello.getInstance();
        waitTime = VuoroVali.getInstance();
        M1_turn = true;
        gui = new SimulaattorinGUI();

        palvelupisteet[0] = new Palvelupiste(new Normal(11, 2), tapahtumalista, TapahtumanTyyppi.LT);
        palvelupisteet[1] = new Palvelupiste(new Normal(10, 2), tapahtumalista, TapahtumanTyyppi.LAIT);
        palvelupisteet[2] = new Palvelupiste(new Normal(5, 2), tapahtumalista, TapahtumanTyyppi.NOUSU);
        palvelupisteet[3] = new Palvelupiste(new Normal(60, 7), tapahtumalista, TapahtumanTyyppi.POISTU);
        palvelupisteet[4] = new Palvelupiste(new Normal(60, 7), tapahtumalista, TapahtumanTyyppi.POISTU);

        saapumisprosessi = new Saapumisprosessi(new Negexp(20, 1), tapahtumalista, TapahtumanTyyppi.SAAP);
    }

    /**
     * Alustetaan uusi asiakas järjestelmään
     */
    @Override
    protected void alustukset() {
        saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
    }

    /**
     *
     * @param t suorittaa B-vaiheen tapahtuman
     */

    @Override
    protected void suoritaTapahtuma(Tapahtuma t) {  // B-vaiheen tapahtumat

        /**
         * @param TapahtumanTyyppi määrittelee metromatkustajalle tapahtuman. Saap on saapuminen metroasemalle,
         * LT on lippuhallin lipunosto tapahtuma, LAIT on laituritason odotustapahtuma
         * ja METROM1 sekä METROM2 ovat poistuminen asemalta. Nousu-tapahtumassa on metrolinjojen vuorottelu-logiikka.
         * If-lausekkeet tarkistavat, milloin metro on ollut viimeksi laiturilla ja
         * milloin seuraava voi saapua laiturille.
         */

        Asiakas a;
        switch ((TapahtumanTyyppi) t.getTyyppi()) {
            case SAAP:
                a = new Asiakas();

                palvelupisteet[0].lisaaJonoon(a);

                saapumisprosessi.generoiSeuraava();
                kontrolleri.visualisoiAsiakas();
                System.err.println("Asiakas " + a.getId() + " tila on M" + a.getMetro() + " ja lippu on " + a.getLippu());

                palveluKeskAika.setSaapumisSaap(a.getId());
                break;

            case LT:
                a = (Asiakas) palvelupisteet[0].otaJonosta(TapahtumanTyyppi.LT);

                palveluKeskAika.setSaapumisPois(a.getId());

                if (a.getLippu() == 1) {
                    palvelupisteet[1].lisaaJonoon(a);
                    palveluKeskAika.setLippuSaap(a.getId());
                } else if (a.getLippu() == 2){
                    palvelupisteet[2].lisaaJonoon(a);
                    palveluKeskAika.setLaituriSaap(a.getId());
                }

                break;
            case LAIT:
                a = (Asiakas) palvelupisteet[1].otaJonosta(TapahtumanTyyppi.LAIT);

                palveluKeskAika.setLippuPois(a.getId());
                palveluKeskAika.setLaituriSaap(a.getId());

                palvelupisteet[2].lisaaJonoon(a);
                break;

            case NOUSU:


                 // make an if statement that will check if kello.getAika() is around 500,
                 // with like a 10-second margin, keep the contents empty


                System.err.println("Kello: " + kello.getAika());

                double currentTime = kello.getAika();


                if (currentTime > (lastTime + waitTime.getAika())) {
                            
                    if (M1_turn == true) {
                        int jononkoko;

                        if (palvelupisteet[2].jononKoko() < 500) {
                            jononkoko = palvelupisteet[2].getJononKokoM1();
                        } else {
                            jononkoko = 500;
                        }

                        System.err.println("METROM1: " + jononkoko);

                        for (int i = 0; i < jononkoko; i++) {
                            a = (Asiakas) palvelupisteet[2].otaJonosta(TapahtumanTyyppi.METROM1);
                            System.out.println("Asiakas " + a.getId() + " tila on " + a.getMetro());

                            palveluKeskAika.setLaituriPois(a.getId());
                            palveluKeskAika.setMetro1Saap(a.getId());

                            palvelupisteet[3].lisaaJonoon(a);
                        }
                        jononkoko = 0;
                        lastTime = currentTime;
                        M1_turn = !M1_turn;

                      } else {
                        int jononkoko2;

                        if (palvelupisteet[2].jononKoko() < 500) {
                            jononkoko2 = palvelupisteet[2].getJononKokoM2();
                        } else {
                            jononkoko2 = 500;
                        }

                        System.err.println("METROM2: " + jononkoko2);

                        for (int i = 0; i < jononkoko2; i++) {
                            a = (Asiakas) palvelupisteet[2].otaJonosta(TapahtumanTyyppi.METROM2);

                            palveluKeskAika.setLaituriPois(a.getId());
                            palveluKeskAika.setMetro2Saap(a.getId());

                            palvelupisteet[4].lisaaJonoon(a);
                        }
                        lastTime = currentTime;
                        M1_turn = !M1_turn;
                        jononkoko2 = 0;
                    }
                }

                palvelupisteet[2].avaaPalvelu();

                break;
            case POISTU:
                int poistokoko;


                if (palvelupisteet[3].jononKoko() < 500) {
                    poistokoko = palvelupisteet[3].jononKoko();
                } else {
                    poistokoko = 5;
                }

                for (int i = 0; i < poistokoko; i++) {
                    a = (Asiakas) palvelupisteet[3].otaJonosta(TapahtumanTyyppi.POISTU);

                    palveluKeskAika.setMetro1Pois(a.getId());

                    a.setPoistumisaika(Kello.getInstance().getAika());
                    a.raportti();
                }

                poistokoko = 0;


                if (palvelupisteet[4].jononKoko() < 500) {
                    poistokoko = palvelupisteet[4].jononKoko();
                } else {
                    poistokoko = 5;
                }

                for (int i = 0; i < poistokoko; i++) {
                    a = (Asiakas) palvelupisteet[4].otaJonosta(TapahtumanTyyppi.POISTU);

                    palveluKeskAika.setMetro2Pois(a.getId());

                    a.setPoistumisaika(Kello.getInstance().getAika());
                    a.raportti();
                }

                poistokoko = 0;


        }
    }

    @Override
    protected void yritaCTapahtumat() {
        for (Palvelupiste p : palvelupisteet) {
            if (!p.onVarattu() && p.onJonossa()) {
                p.aloitaPalvelu();
            }
        }
    }

    /**
     * Tulokset() metodissa tulostetaan terminaaliin simuloinnin tapahtumat. Metodissa myös tallenetaan
     * tiedot tietokantaan. Palvelupisteiden tiedot tallenetaan omiin tauluihin tietokannassa. Tauluihin
     * tallenetaan kyseisen palvelupisteen keskimääräisen palveluajan, palveltujen asiakkaiden määrä
     * sekä simuloinnin ajan
     */

    @Override
    protected void tulokset() {
        System.out.println("Metro simulaattori päättyi kello " + Kello.getInstance().getAika());
        System.out.println("Matkustajien määrä: " + Asiakas.getI());
        System.out.println("Matkustajia kuljetettu: " + Asiakas.getcompletedi());

        if (Asiakas.getcompletedi() == 0) {
            System.out.println("Keskimääräinen palvelu aika: 0");
        } else {
            System.out.println("Keskimääräinen palvelu aika: " + (double) (Asiakas.getSum() / Asiakas.getcompletedi()));
        }

        // Tallenetaan tiedot tietokantaan
        try {
            metroDao.setData("Metroasema", palveluKeskAika.getSaapPalveltu(), palveluKeskAika.getSaapKeskiaika(), Kello.getInstance().getAika());
            metroDao.setData("Lippuhalli", palveluKeskAika.getLippuPalveltu(), palveluKeskAika.getLippuKeskiaika(), Kello.getInstance().getAika());
            metroDao.setData("Laituri", palveluKeskAika.getLaitPalveltu(), palveluKeskAika.getLaituriKeskiaika(), Kello.getInstance().getAika());
            metroDao.setData("Metro_M1", palveluKeskAika.getMetro1Palveltu(), palveluKeskAika.getMetro1Keskiaika(), Kello.getInstance().getAika());
            metroDao.setData("Metro_M2", palveluKeskAika.getMetro2Palveltu(), palveluKeskAika.getMetro2Keskiaika(), Kello.getInstance().getAika());
            System.out.println("Tiedot tallennettu tietokantaan");

            kontrolleri.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<LinkedList<Asiakas>> getJononAsiakkaat() {
        return new ArrayList<LinkedList<Asiakas>>() {{
            add(palvelupisteet[0].getJono());
            add(palvelupisteet[1].getJono());
            add(palvelupisteet[2].getJono());
            add(palvelupisteet[3].getJono());
            add(palvelupisteet[4].getJono());
        }};
    }
}
