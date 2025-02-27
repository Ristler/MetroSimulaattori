package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Visualisointi implements IVisualisointi{

	private final GraphicsContext gc;
	private final Canvas canvas;
	
	double i = 0;
	double j = 10;
	
	
	public Visualisointi(Canvas canvas) {
		//super();
        this.canvas = canvas;

        gc = this.canvas.getGraphicsContext2D();
		tyhjennaNaytto();
	}
	

	public void tyhjennaNaytto() {
		gc.setFill(Color.YELLOW);
		gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
	}
	
	public void uusiAsiakas() {
		gc.setFill(Color.RED);
		gc.fillOval(i,j,10,10);
		
		i = (i + 10) % this.canvas.getWidth();
		//j = (j + 12) % this.canvas.getHeight();
		if (i==0) j+=10;			
	}
	
}
