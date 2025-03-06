package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

	public void uusiAsiakas(int SAAPasiaakaat, int LTasiakkaat, int LAITasiakkaat, int METROM1asiakkaat, int METROM2asiakkaat) {
		tyhjennaNaytto();

		//System.out.println("Visualisointi: uusiAsiakas");

		double i = 0;
		double j = 10;

		for (int x = 0; x < SAAPasiaakaat; x++) {
			//System.out.println("UusiAsiakas: SAAP");
			SAAPgc.setFill(Color.RED);
			SAAPgc.fillOval(i, j, 10, 10);

			i = (i + 10) % this.SAAPcanvas.getWidth();
			//j = (j + 12) % this.canvas.getHeight();
			if (i==0) j+=10;
		}

		i = 0;
		j = 10;

		for (int x = 0; x < LTasiakkaat; x++) {
			LTgc.setFill(Color.RED);
			LTgc.fillOval(i, j, 10, 10);

			i = (i + 10) % this.LTcanvas.getWidth();
			//j = (j + 12) % this.canvas.getHeight();
			if (i==0) j+=10;
		}

		i = 0;
		j = 10;

		for (int x = 0; x < LAITasiakkaat; x++) {
			LAITgc.setFill(Color.RED);
			LAITgc.fillOval(i, j, 10, 10);

			i = (i + 10) % this.LAITcanvas.getWidth();
			//j = (j + 12) % this.canvas.getHeight();
			if (i==0) j+=10;
		}

		i = 0;
		j = 10;

		for (int x = 0; x < METROM1asiakkaat; x++) {
			METROM1gc.setFill(Color.RED);
			METROM1gc.fillOval(i, j, 10, 10);

			i = (i + 10) % this.METROM1canvas.getWidth();
			//j = (j + 12) % this.canvas.getHeight();
			if (i==0) j+=10;
		}

		i = 0;
		j = 10;

		for (int x = 0; x < METROM2asiakkaat; x++) {
			METROM2gc.setFill(Color.RED);
			METROM2gc.fillOval(i, j, 10, 10);

			i = (i + 10) % this.METROM2canvas.getWidth();
			//j = (j + 12) % this.canvas.getHeight();
			if (i==0) j+=10;
		}
	}
	
}
