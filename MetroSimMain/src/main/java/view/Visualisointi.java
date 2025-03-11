package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import simu.model.Asiakas;

import java.util.LinkedList;

public class Visualisointi implements IVisualisointi{
	private final GraphicsContext SAAPgc;
	private final GraphicsContext LTgc;
	private final GraphicsContext LAITgc;
	private final GraphicsContext METROM1gc;
	private final GraphicsContext METROM2gc;

	private final Canvas SAAPcanvas;
	private final Canvas LTcanvas;
	private final Canvas LAITcanvas;
	private final Canvas METROM1canvas;
	private final Canvas METROM2canvas;

	private final Image defaultImage = new Image("/Default.png");
	private final Image waitingImage = new Image("/Waiting.png");
	private final Image ticketm1Image = new Image("/TicketM1.png");
	private final Image ticketm2Image = new Image("/TicketM2.png");
	private final Image ticketm1kImage = new Image("/TicketM1K.png");
	private final Image ticketm2kImage = new Image("/TicketM2K.png");

	public Visualisointi(Canvas SAAPcanvas, Canvas LTcanvas, Canvas LAITcanvas, Canvas METROM1canvas, Canvas METROM2canvas) {
		//super();

		this.SAAPcanvas = SAAPcanvas;
		this.LTcanvas = LTcanvas;
		this.LAITcanvas = LAITcanvas;
		this.METROM1canvas = METROM1canvas;
		this.METROM2canvas = METROM2canvas;

		SAAPgc = this.SAAPcanvas.getGraphicsContext2D();
		LTgc = this.LTcanvas.getGraphicsContext2D();
		LAITgc = this.LAITcanvas.getGraphicsContext2D();
		METROM1gc = this.METROM1canvas.getGraphicsContext2D();
		METROM2gc = this.METROM2canvas.getGraphicsContext2D();

		tyhjennaNaytto();
	}
	

	public void tyhjennaNaytto() {
		SAAPgc.setFill(Color.web("#d9e7ff"));
		SAAPgc.fillRect(0, 0, this.SAAPcanvas.getWidth(), this.SAAPcanvas.getHeight());

		LTgc.setFill(Color.web("#d9e7ff"));
		LTgc.fillRect(0, 0, this.LTcanvas.getWidth(), this.LTcanvas.getHeight());

		LAITgc.setFill(Color.web("#d9e7ff"));
		LAITgc.fillRect(0, 0, this.LAITcanvas.getWidth(), this.LAITcanvas.getHeight());

		METROM1gc.setFill(Color.web("#d9e7ff"));
		METROM1gc.fillRect(0, 0, this.METROM1canvas.getWidth(), this.METROM1canvas.getHeight());

		METROM2gc.setFill(Color.web("#d9e7ff"));
		METROM2gc.fillRect(0, 0, this.METROM1canvas.getWidth(), this.METROM1canvas.getHeight());
	}

	public void uusiAsiakas(LinkedList<Asiakas> SAAPasiakkaat, LinkedList<Asiakas> LTasiakkaat, LinkedList<Asiakas> LAITasiakkaat, LinkedList<Asiakas> METROM1asiakkaat, LinkedList<Asiakas> METROM2asiakkaat) {
		tyhjennaNaytto();

		//System.out.println("Visualisointi: uusiAsiakas");

		double increment = 40;

		double i = 0;
		double j = 0;

		for (Asiakas asiakas : SAAPasiakkaat) {
			//System.out.println("UusiAsiakas: SAAP");

			SAAPgc.drawImage(defaultImage, i, j, increment, increment);

			i = (i + increment) % this.SAAPcanvas.getWidth();
			//j = (j + 12) % this.canvas.getHeight();
			if (i==0) j+=increment;
		}

		i = 0;
		j = 0;

		for (Asiakas asiakas : LTasiakkaat) {
			//System.out.println("UusiAsiakas: SAAP");

			LTgc.drawImage(waitingImage, i, j, increment, increment);

			i = (i + increment) % this.LTcanvas.getWidth();
			//j = (j + 12) % this.canvas.getHeight();
			if (i==0) j+=increment;
		}

		i = 0;
		j = 0;

		for (Asiakas asiakas : LAITasiakkaat) {
			//System.out.println("UusiAsiakas: SAAP");

			if (asiakas.getLippu() == 2 && asiakas.getMetro() == 1) {
				LAITgc.drawImage(ticketm1kImage, i, j, increment, increment);
			} else if (asiakas.getLippu() == 2 && asiakas.getMetro() == 2) {
				LAITgc.drawImage(ticketm2kImage, i, j, increment, increment);
			} else if (asiakas.getLippu() == 1 && asiakas.getMetro() == 1) {
				LAITgc.drawImage(ticketm1Image, i, j, increment, increment);
			} else if (asiakas.getLippu() == 1 && asiakas.getMetro() == 2) {
				LAITgc.drawImage(ticketm2Image, i, j, increment, increment);
			} else {
				LAITgc.drawImage(defaultImage, i, j, increment, increment);
			}

			i = (i + increment) % this.LAITcanvas.getWidth();
			//j = (j + 12) % this.canvas.getHeight();
			if (i==0) j+=increment;
		}

		i = 0;
		j = 0;

		for (Asiakas asiakas : METROM1asiakkaat) {
			//System.out.println("UusiAsiakas: SAAP");

			if (asiakas.getLippu() == 2 && asiakas.getMetro() == 1) {
				METROM1gc.drawImage(ticketm1kImage, i, j, increment, increment);
			} else if (asiakas.getLippu() == 2 && asiakas.getMetro() == 2) {
				METROM1gc.drawImage(ticketm2kImage, i, j, increment, increment);
			} else if (asiakas.getLippu() == 1 && asiakas.getMetro() == 1) {
				METROM1gc.drawImage(ticketm1Image, i, j, increment, increment);
			} else if (asiakas.getLippu() == 1 && asiakas.getMetro() == 2) {
				METROM1gc.drawImage(ticketm2Image, i, j, increment, increment);
			} else {
				METROM1gc.drawImage(defaultImage, i, j, increment, increment);
			}

			i = (i + increment) % this.METROM1canvas.getWidth();
			//j = (j + 12) % this.canvas.getHeight();
			if (i==0) j+=increment;
		}

		i = 0;
		j = 0;

		for (Asiakas asiakas : METROM2asiakkaat) {
			//System.out.println("UusiAsiakas: SAAP");

			if (asiakas.getLippu() == 2 && asiakas.getMetro() == 1) {
				METROM2gc.drawImage(ticketm1kImage, i, j, increment, increment);
			} else if (asiakas.getLippu() == 2 && asiakas.getMetro() == 2) {
				METROM2gc.drawImage(ticketm2kImage, i, j, increment, increment);
			} else if (asiakas.getLippu() == 1 && asiakas.getMetro() == 1) {
				METROM2gc.drawImage(ticketm1Image, i, j, increment, increment);
			} else if (asiakas.getLippu() == 1 && asiakas.getMetro() == 2) {
				METROM2gc.drawImage(ticketm2Image, i, j, increment, increment);
			} else {
				METROM2gc.drawImage(defaultImage, i, j, increment, increment);
			}

			i = (i + increment) % this.METROM2canvas.getWidth();
			//j = (j + 12) % this.canvas.getHeight();
			if (i==0) j+=increment;
		}
	}
	
}
