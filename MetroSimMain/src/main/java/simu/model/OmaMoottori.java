package simu.model;

import simu.framework.*;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import controller.IKontrolleriForM;

import java.util.ArrayList;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;

	private Palvelupiste[] palvelupisteet;


	public OmaMoottori(IKontrolleriForM kontrolleri){

		super(kontrolleri);

		palvelupisteet = new Palvelupiste[4];

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
			case SAAP: palvelupisteet[0].lisaaJonoon(new Asiakas());
				       saapumisprosessi.generoiSeuraava();
					   kontrolleri.visualisoiAsiakas(); // UUSI
				break;
			case LT: a = (Asiakas)palvelupisteet[0].otaJonosta();
				   	   palvelupisteet[1].lisaaJonoon(a);
				break;
			case LAIT: a = (Asiakas)palvelupisteet[1].otaJonosta();
				   	   palvelupisteet[2].lisaaJonoon(a);
				break;
			case METRO:
				int jononkoko;

				if (palvelupisteet[2].jononKoko() < 5) {
					jononkoko = palvelupisteet[2].jononKoko();
				} else {
					jononkoko = 5;
				}

				for (int i = 0; i < jononkoko; i++) {
					a = (Asiakas)palvelupisteet[2].otaJonosta();
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
