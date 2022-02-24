package Tetris;

import java.awt.Color;

public class FiguraL extends Forma {

	private int tamanio;
	private Color color;
	private void asignarColor(){
	// Asignación de colores a las figuras
	
	Color magenta = new Color(205, 46, 150); // L
	this.color=magenta;
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



