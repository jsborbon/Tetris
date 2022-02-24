package Tetris;

import java.awt.Color;

public class FiguraZ extends Forma {

	private int tamanio;
	private Color color;
	private void asignarColor(){
	// Asignación de colores a las figuras
	Color cyan = new Color(0, 132, 144); // Z

	this.color=cyan;
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


