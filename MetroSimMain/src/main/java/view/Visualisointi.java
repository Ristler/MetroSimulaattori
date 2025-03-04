package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Visualisointi implements IVisualisointi{
	private final GraphicsContext SAAPgc;
	private final GraphicsContext LTgc;
	private final GraphicsContext LAITgc;
	private final GraphicsContext METROgc;

	private final Canvas SAAPcanvas;
	private final Canvas LTcanvas;
	private final Canvas LAITcanvas;
	private final Canvas METROcanvas;

	public Visualisointi(Canvas SAAPcanvas, Canvas LTcanvas, Canvas LAITcanvas, Canvas METROcanvas) {
		//super();

		this.SAAPcanvas = SAAPcanvas;
		this.LTcanvas = LTcanvas;
		this.LAITcanvas = LAITcanvas;
		this.METROcanvas = METROcanvas;

		SAAPgc = this.SAAPcanvas.getGraphicsContext2D();
		LTgc = this.LTcanvas.getGraphicsContext2D();
		LAITgc = this.LAITcanvas.getGraphicsContext2D();
		METROgc = this.METROcanvas.getGraphicsContext2D();

		tyhjennaNaytto();
	}
	

	public void tyhjennaNaytto() {
		SAAPgc.setFill(Color.YELLOW);
		SAAPgc.fillRect(0, 0, this.SAAPcanvas.getWidth(), this.SAAPcanvas.getHeight());

		LTgc.setFill(Color.YELLOW);
		LTgc.fillRect(0, 0, this.LTcanvas.getWidth(), this.LTcanvas.getHeight());

		LAITgc.setFill(Color.YELLOW);
		LAITgc.fillRect(0, 0, this.LAITcanvas.getWidth(), this.LAITcanvas.getHeight());

		METROgc.setFill(Color.YELLOW);
		METROgc.fillRect(0, 0, this.METROcanvas.getWidth(), this.METROcanvas.getHeight());
	}

	public void uusiAsiakas(int SAAPasiaakaat, int LTasiakkaat, int LAITasiakkaat, int METROasiakkaat) {
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

		for (int x = 0; x < METROasiakkaat; x++) {
			METROgc.setFill(Color.RED);
			METROgc.fillOval(i, j, 10, 10);

			i = (i + 10) % this.METROcanvas.getWidth();
			//j = (j + 12) % this.canvas.getHeight();
			if (i==0) j+=10;
		}
	}
	
}
