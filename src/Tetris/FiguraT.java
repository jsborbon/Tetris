package Tetris;

import java.awt.Color;

public class FiguraT extends Forma {

	private int tamanio;
	private Color color;

	private void asignarColor() {
		// Asignación de colores a las figuras

		Color marron = new Color(158, 114, 15); // T
		this.color = marron;
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

