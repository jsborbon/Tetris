package Tetris;

import java.awt.Color;

public class FiguraLinea extends Forma {

	private int tamanio;
	private Color color;
	
	private void asignarColor(){
	// Asignación de colores a las figuras
	Color rojo = new Color(216, 29, 43); // Linea
	this.color=rojo;
	}
	protected void rotar() {
		
	}
	
	protected void mover() {
		
	}
	public Color getColor() {
		asignarColor();
		return color;
	}
	
	
}


