package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import simu.model.PalveluKeskAika;
import view.ISimulaattorinUI;
import view.IVisualisointi;
import view.Visualisointi;

import java.text.DecimalFormat;
import java.util.ArrayList;

import simu.framework.Kello;

public class Kontrolleri implements IKontrolleriForM, IKontrolleriForV {

	@FXML
	private Canvas SAAPlista;

	@FXML
	private Canvas LTlista;

	@FXML
	private Canvas LAITlista;

	@FXML
	private Canvas METROlista;

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
	private Label METROaika;// UUSI

	private IMoottori moottori;
	private Kello kello;
	private ISimulaattorinUI ui;
	private PalveluKeskAika pka;

	private IVisualisointi naytto;
	ISimulaattorinUI x;

	public Kontrolleri() {

	}


	// Moottorin ohjausta:
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.setSimulointiaika(getAika());
		naytto = new Visualisointi(SAAPlista, LTlista, LAITlista, METROlista);
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



	// Visualisoi ajastimen ja palvelupisteiden keskimääräiset palveluajat
	@Override
	public void setAjat() {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		Platform.runLater(() -> kokonaisaika.setText(formatter.format(kello.getAika())));
		Platform.runLater(() -> SAAPaika.setText(formatter.format(pka.getSaapKeskiaika())));
		Platform.runLater(() -> LTaika.setText(formatter.format(pka.getLippuKeskiaika())));
		Platform.runLater(() -> LAITaika.setText(formatter.format(pka.getLaituriKeskiaika())));
		Platform.runLater(() -> METROaika.setText(formatter.format(pka.getMetroKeskiaika())));
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
				ArrayList<Integer> jono = moottori.getJono();

				naytto.uusiAsiakas(jono.get(0), jono.get(1), jono.get(2), jono.get(3));
			}
		});
	}

}
