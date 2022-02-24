package Tetris;

import java.awt.Color;

public class FiguraCuadrado extends Forma {

	private int tamanio;
	private Color color;
	
	private void asignarColor(){
	// Asignación de colores a las figuras
	Color azul = new Color(12, 67, 194); // Cuadrado
	this.color=azul;
	}
	
	protected void mover() {
		
	}
	public Color getColor() {
		asignarColor();
		return color;
	}
	
	
}






