package simu.model;

import simu.framework.*;
import dao.MetroDao;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import controller.IKontrolleriForM;

import java.util.ArrayList;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;

	private Palvelupiste[] palvelupisteet;

	private PalveluKeskAika palveluKeskAika;

	private MetroDao metroDao;


	public OmaMoottori(IKontrolleriForM kontrolleri){

		super(kontrolleri);

		metroDao = new MetroDao();
		palvelupisteet = new Palvelupiste[4];
		palveluKeskAika = new PalveluKeskAika();

		palvelupisteet[0] = new Palvelupiste(new Normal(4,2), tapahtumalista, TapahtumanTyyppi.LT);
		palvelupisteet[1] = new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.LAIT);
		palvelupisteet[2] = new Palvelupiste(new Normal(100,20), tapahtumalista, TapahtumanTyyppi.METRO);
		palvelupisteet[3] = new Palvelupiste(new Normal(20,10), tapahtumalista, TapahtumanTyyppi.POISTU);

		saapumisprosessi = new Saapumisprosessi(new Negexp(3,1), tapahtumalista, TapahtumanTyyppi.SAAP);

	}


	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

		Asiakas a;
		switch ((TapahtumanTyyppi)t.getTyyppi()){
			case SAAP:
					a = new Asiakas();
					palvelupisteet[0].lisaaJonoon(a);
					saapumisprosessi.generoiSeuraava();
					kontrolleri.visualisoiAsiakas();

					palveluKeskAika.setSaapumisSaap(a.getId());
					break;

			case LT: a = (Asiakas)palvelupisteet[0].otaJonosta();

					palveluKeskAika.setSaapumisPois(a.getId());
					palveluKeskAika.setLippuSaap(a.getId());

					palvelupisteet[1].lisaaJonoon(a);
				break;

			case LAIT: 
					a = (Asiakas)palvelupisteet[1].otaJonosta();

					palveluKeskAika.setLippuPois(a.getId());
					palveluKeskAika.setLaituriSaap(a.getId());

					palvelupisteet[2].lisaaJonoon(a);
				break;

			case METRO:
				int jononkoko;

				// Voisitteko jatkossa kommentoida näitä koodinpätkiä, jotta olisi helpompaa ymmärtää mitä koodi tekee?

				if (palvelupisteet[2].jononKoko() < 5) {
					jononkoko = palvelupisteet[2].jononKoko();
				} else {
					jononkoko = 5;
				}

				for (int i = 0; i < jononkoko; i++) {
					a = (Asiakas)palvelupisteet[2].otaJonosta();

					palveluKeskAika.setLaituriPois(a.getId());
					palveluKeskAika.setMetroSaap(a.getId());

					palvelupisteet[3].lisaaJonoon(a);
				}

				jononkoko = 0;
				break;
			case POISTU:
				System.out.println("Poistu");

				int poistokoko;

				if (palvelupisteet[3].jononKoko() < 5) {
					poistokoko = palvelupisteet[3].jononKoko();
				} else {
					poistokoko = 5;
				}

				for (int i = 0; i < poistokoko; i++) {
					a = (Asiakas)palvelupisteet[3].otaJonosta();

					palveluKeskAika.setMetroPois(a.getId());

					a.setPoistumisaika(Kello.getInstance().getAika());
					a.raportti();
				}

				poistokoko = 0;
		}
	}

	@Override
	protected void yritaCTapahtumat(){
		for (Palvelupiste p: palvelupisteet){
			if (!p.onVarattu() && p.onJonossa()){
				p.aloitaPalvelu();
			}
		}
	}

	@Override
	protected void tulokset() {
		System.out.println("Metro simulaattori päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Matkustajien määrä: " + Asiakas.getI());
		System.out.println("Matkustajia kuljetettu: " + Asiakas.getcompletedi());

		if (Asiakas.getcompletedi() == 0) {
			System.out.println("Keskimääräinen palvelu aika: 0");
		} else {
			System.out.println("Keskimääräinen palvelu aika: " + (double)(Asiakas.getSum() / Asiakas.getcompletedi()));
		}

		// Tallenetaan tiedot tietokantaan
		try {
			metroDao.setData("Metroasema", palveluKeskAika.getSaapPalveltu(), palveluKeskAika.getSaapKeskiaika(), Kello.getInstance().getAika());
			metroDao.setData("Lippuhalli", palveluKeskAika.getLippuPalveltu(), palveluKeskAika.getLippuKeskiaika(), Kello.getInstance().getAika());
			metroDao.setData("Laituri", palveluKeskAika.getLaitPalveltu(), palveluKeskAika.getLaituriKeskiaika(), Kello.getInstance().getAika());
			metroDao.setData("Metro", palveluKeskAika.getMetroPalveltu(), palveluKeskAika.getMetroKeskiaika(),Kello.getInstance().getAika());
			System.out.println("Tiedot tallennettu tietokantaan");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Integer> getJono() {
		return new ArrayList<Integer>() {{
			add(palvelupisteet[0].jononKoko());
			add(palvelupisteet[1].jononKoko());
			add(palvelupisteet[2].jononKoko());
			add(palvelupisteet[3].jononKoko());

		}};
	}
}
