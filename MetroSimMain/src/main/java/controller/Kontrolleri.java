package controller;

import dao.MetroDao;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Slider;
import simu.framework.IMoottori;
import simu.model.Asiakas;
import simu.model.OmaMoottori;
import simu.model.PalveluKeskAika;
import view.ISimulaattorinUI;
import view.IVisualisointi;
import view.SimulaattorinGUI;
import view.Visualisointi;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import simu.framework.Kello;
import simu.framework.VuoroVali;

/**
 * Luokka toimii simulaattorin kontrolli luokanana, eli visuaalisen ja model komponenttien välimiehenä.
 * <p>
 * Kontroller käsittelee myös FXML komponenttien toiminnan
 */
public class Kontrolleri implements IKontrolleriForM, IKontrolleriForV {

	@FXML
	private Canvas SAAPlista;

	@FXML
	private Canvas LTlista;

	@FXML
	private Canvas LAITlista;

	@FXML
	private Canvas METROM1lista;

	@FXML
	private Canvas METROM2lista;

	@FXML
	private TextField simviivefield;

	@FXML
	private TextField simaikafield;

	@FXML
	private Label kokonaisaika;// UUSI

	@FXML
	private Label SAAPaika;// UUSI

	@FXML
	private Label LTaika;// UUSI

	@FXML
	private Label LAITaika;// UUSI

	@FXML
	private Label METROM1aika;// UUSI

	@FXML
	private Label METROM2aika;// UUSI

	@FXML
	private Label vuorovaliLabel;

	@FXML
	private Slider vuorovaliSlider;

	private IMoottori moottori;
	private Kello kello;
	private ISimulaattorinUI ui;
	private PalveluKeskAika pka;
	private VuoroVali vv;

	private MetroDao metroDao;
	private SimulaattorinGUI gui;

	private IVisualisointi naytto;
	ISimulaattorinUI x;

	public Kontrolleri() {

	}

	/**
	 * Käynnistää simulaattorin
	 * <p>
	 * Luo uuden moottorin, database dao:n ja  Simulaattori GUI:n
	 * Asettaa simulaattori ajan ja viiveen
	 * Aloittaa moottori threadin
	 */
	// Moottorin ohjausta:
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		metroDao = new MetroDao();
		gui = new SimulaattorinGUI();

		moottori.setSimulointiaika(getAika());
		naytto = new Visualisointi(SAAPlista, LTlista, LAITlista, METROM1lista, METROM2lista);
		kello = Kello.getInstance();
		moottori.setViive(getViive());
		pka = new PalveluKeskAika();
		vv = VuoroVali.getInstance();
		//ui.getVisualisointi().tyhjennaNaytto();
		getVisualisointi(x);
		((Thread) moottori).start();
		//((Thread)moottori).run(); // Ei missään tapauksessa näin. Miksi?		
	}

	/**
	 * Hidastaa simulaattorin viiveen 10%
	 */
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() * 1.10));
	}

	/**
	 * Nopeuttaa simulaattorin viiveen 10%
	 */
	public void nopeuta() { // nopeutetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() * 0.9));
	}

	/**
	 * Asettaa metrojen vuorovälin FXML sliderin arvon perusteella
	 */
	public void mvuorovali() {
		vv.setAika(vuorovaliSlider.getValue());
	}


	/**
	 * Ajan otto
	 * <p>
	 * @return Palauttaa FXML tekstikentästä ajan
	 */
	public long getAika() {
		return Long.parseLong(simaikafield.getText());
	}

	/**
	 * Viiveen otto
	 * <p>
	 * @return Palauttaa FXML tekstikentästä viiveen
	 */
	public long getViive() {
		return Long.parseLong(simviivefield.getText());
	}

	/**
	 * Tiedonpalautus
	 * <p>
	 * Palauttaa simulaattorin lopussa dataa uuteen prompt ikkunaan
	 * Dataan kuuluu palvelupisteiden keskimääräinen palveluaika, kuinka monta asiakasta on palveltu ja kuinka paljon lippuja oli myyty
	 */
	//Fetchaa dataa tietokannasta ja näyttää sen käyttöliittymässä simulaation päätyttyä.
	public void getData() {

		//Asiakkaiden määrä palvelupisteittäin
		int palveltuMetroasema = pka.getSaapPalveltu();
		int palveltuLippuhalli = pka.getLippuPalveltu();
		int palveltuLaituri = pka.getLaitPalveltu();
		int palveltuM1 = pka.getMetro1Palveltu();
		int palveltuM2 = pka.getMetro2Palveltu();

		//Keskimääräinen palveluaika per palvelupiste
		Double kaMetroasema = pka.getSaapKeskiaika();
		Double kaLippuhalli = pka.getLippuKeskiaika();
		Double kaLaituri = pka.getLaituriKeskiaika();
		Double kaM1 = pka.getMetro1Keskiaika();
		Double kaM2 = pka.getMetro2Keskiaika();

		Double tulot = palveltuLippuhalli * 3.40;

		//Laske kaikkien keskiarvo
		double palveluKeskiarvo = (kaMetroasema + kaLippuhalli + kaLaituri + kaM1 + kaM2) / 5;

		//Näytä UI:ssa
		Platform.runLater(() -> {
			gui.showData(palveltuMetroasema, palveltuLippuhalli, palveltuLaituri, palveltuM1, palveltuM2, palveluKeskiarvo, tulot);
		});
	}

	/**
	 * Ajastimien visualisointi
	 * <p>
	 * Visualisoi simulaattorin kaikki ajastimet, funktiota kutsutaan simuloinnin ajastimen kuluessa kokoajan
	 */
	// Visualisoi ajastimen ja palvelupisteiden keskimääräiset palveluajat
	@Override
	public void setAjat() {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		Platform.runLater(() -> kokonaisaika.setText(formatter.format(kello.getAika())));
		Platform.runLater(() -> vuorovaliLabel.setText(formatter.format(vv.getAika())));
		Platform.runLater(() -> SAAPaika.setText(formatter.format(pka.getSaapKeskiaika())));
		Platform.runLater(() -> LTaika.setText(formatter.format(pka.getLippuKeskiaika())));
		Platform.runLater(() -> LAITaika.setText(formatter.format(pka.getLaituriKeskiaika())));
		Platform.runLater(() -> METROM1aika.setText(formatter.format(pka.getMetro1Keskiaika())));
		Platform.runLater(() -> METROM2aika.setText(formatter.format(pka.getMetro2Keskiaika())));
	}

	/**
	 * Visualisoinnin palaustus
	 * <p>
	 * @return Palauttaa simulaattori GUI:n
	 */
	public ISimulaattorinUI getVisualisointi(ISimulaattorinUI naytto) {
		return naytto;
	}

	// Simulointitulosten välittämistä käyttöliittymään.
	// Koska FX-ui:n päivitykset tulevat moottorisäikeestä, ne pitää ohjata JavaFX-säikeeseen:

	/**
	 * Asiakkaiden Visualisointi
	 * <p>
	 * Visualisoi asiakkaa antamalla kaikkien palvelupisteiden jonot ja niiden asiakkaat, jotta asiakkaiden tietoja voidataan verrata
	 */
	@Override
	public void visualisoiAsiakas() {
		Platform.runLater(new Runnable() {
			public void run() {
				ArrayList<LinkedList<Asiakas>> jono = moottori.getJononAsiakkaat();

				naytto.uusiAsiakas(jono.get(0), jono.get(1), jono.get(2), jono.get(3), jono.get(4));
			}
		});
	}
}
