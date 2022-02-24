package Tetris;

import java.awt.Color;

public class FiguraS extends Forma {

	private int tamanio;
	private Color color;
	private void asignarColor(){
	// Asignación de colores a las figuras
	
	Color verde = new Color(5, 162, 11); // S	
	this.color=verde;
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

