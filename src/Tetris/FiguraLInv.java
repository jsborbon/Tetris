package Tetris;

import java.awt.Color;

public class FiguraLInv extends Forma {

	private int tamanio;
	private Color color;
	private void asignarColor(){
	// Asignación de colores a las figuras
	
	Color blanco = new Color(255, 255, 255); // L invertida
	this.color=blanco;
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


