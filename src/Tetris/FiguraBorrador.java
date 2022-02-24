package Tetris;

import java.awt.Color;

public class FiguraBorrador extends Forma {

	private int tamanio;
	private Color color;
	
	private void asignarColor(){
	// Asignación de colores a las figuras
	Color negro = new Color(0, 0, 0); // negro
	
	this.color=negro;
	}
	
	public Color getColor() {
		asignarColor();
		return color;
	}
	
	
}











