package simu.model;

import simu.framework.*;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;

public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;

	private Palvelupiste[] palvelupisteet;

	public OmaMoottori(){

		palvelupisteet = new Palvelupiste[3];

		palvelupisteet[0]=new Palvelupiste(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.LT);
		palvelupisteet[1]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.LAIT);
		palvelupisteet[2]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.METRO);

		saapumisprosessi = new Saapumisprosessi(new Negexp(15,5), tapahtumalista, TapahtumanTyyppi.SAAP);

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
				break;
			case LT: a = (Asiakas)palvelupisteet[0].otaJonosta();
				   	   palvelupisteet[1].lisaaJonoon(a);
				break;
			case LAIT: a = (Asiakas)palvelupisteet[1].otaJonosta();
				   	   palvelupisteet[2].lisaaJonoon(a);
				break;
			case METRO:
				       a = (Asiakas)palvelupisteet[2].otaJonosta();
					   a.setPoistumisaika(Kello.getInstance().getAika());
			           a.raportti();
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
		System.out.println("Asiakkaiden määrä: " + Asiakas.getI());
		System.out.println("Asiakkaita palveltu: " + Asiakas.getcompletedi());
		System.out.println("Keskimääräinen palvelu aika: " + (double)(Asiakas.getSum() / Asiakas.getcompletedi()));
	}
}
