package controller;

import dao.MetroDao;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

	private IMoottori moottori;
	private Kello kello;
	private ISimulaattorinUI ui;
	private PalveluKeskAika pka;

	private MetroDao metroDao;
	private SimulaattorinGUI gui;




	private IVisualisointi naytto;
	ISimulaattorinUI x;

	public Kontrolleri() {

	}


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
		//ui.getVisualisointi().tyhjennaNaytto();
		getVisualisointi(x);
		((Thread) moottori).start();
		//((Thread)moottori).run(); // Ei missään tapauksessa näin. Miksi?		
	}



	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() * 1.10));
	}


	public void nopeuta() { // nopeutetaan moottorisäiettä
		moottori.setViive((long) (moottori.getViive() * 0.9));
	}


	public long getAika() {
		return Long.parseLong(simaikafield.getText());
	}


	public long getViive() {
		return Long.parseLong(simviivefield.getText());
	}


	//Fetchaa dataa tietokannasta ja näyttää sen käyttöliittymässä simulaation päätyttyä.
	public void getData() {
		ArrayList<String> metroasema = metroDao.getData("Metroasema");
		ArrayList<String> lippuhalli = metroDao.getData("Lippuhalli");
		ArrayList<String> laituri = metroDao.getData("Laituri");

		ArrayList<String> metrom1 = metroDao.getData("Metro_M1");
		ArrayList<String> metrom2 = metroDao.getData("Metro_M2");

		//Asiakkaiden määrä palveltu
		String asiakkaitapalveltuMetroasema = metroasema.get(3);
		String asiakkaitapalveltuLippuhalli = lippuhalli.get(3);
		String asiakkaitapalveltuLaituri = laituri.get(3);

		//Keskimääräinen palveluaika per palvelupiste
		Double kaMetroasema = Double.parseDouble(metroasema.get(4));
		Double kaLippuhalli = Double.parseDouble(lippuhalli.get(4));
		Double kaLaituri = Double.parseDouble(laituri.get(4));
		Double kaM1 = Double.parseDouble(metrom1.get(4));
		Double kaM2 = Double.parseDouble(metrom2.get(4));


		//Laske kaikkien keskiarvo
		double palveluKeskiarvo = kaMetroasema+kaLippuhalli+kaLaituri+kaM1+kaM2 / 5;

				//Näytä UI:ssa
		Platform.runLater(() -> {
			gui.showData(asiakkaitapalveltuMetroasema, asiakkaitapalveltuLippuhalli, asiakkaitapalveltuLaituri, palveluKeskiarvo);
		});
	}

	// Visualisoi ajastimen ja palvelupisteiden keskimääräiset palveluajat
	@Override
	public void setAjat() {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		Platform.runLater(() -> kokonaisaika.setText(formatter.format(kello.getAika())));
		Platform.runLater(() -> SAAPaika.setText(formatter.format(pka.getSaapKeskiaika())));
		Platform.runLater(() -> LTaika.setText(formatter.format(pka.getLippuKeskiaika())));
		Platform.runLater(() -> LAITaika.setText(formatter.format(pka.getLaituriKeskiaika())));
		Platform.runLater(() -> METROM1aika.setText(formatter.format(pka.getMetro1Keskiaika())));
		Platform.runLater(() -> METROM2aika.setText(formatter.format(pka.getMetro2Keskiaika())));
	}

	public ISimulaattorinUI getVisualisointi(ISimulaattorinUI naytto) {
		return naytto;
	}



	// Simulointitulosten välittämistä käyttöliittymään.
	// Koska FX-ui:n päivitykset tulevat moottorisäikeestä, ne pitää ohjata JavaFX-säikeeseen:

	@Override
	public void naytaLoppuaika(double aika) {
		//Platform.runLater(() -> ui.setLoppuaika(aika));
	}


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
