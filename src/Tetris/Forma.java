package Tetris;

/*
 * NOMBRE DEL PROYECTO: TETRIS
 * 
 * AUTORES: Javier Santiago Borb�n Borb�n y Jos� Andr�s Sanabria Arias
 * C�digos: 20182020085 y 2018202095
 * 
 * 
 * FECHA: 28/03/2020 (Marzo 28 de 2020)
 * 
 * 
 * Versi�n 1.0;
 * 
 */

import java.util.Random;
import java.awt.Color;
import java.lang.Math;

public class Forma {
	// Varibles fundamentales para manipular las piezas en el tablero, mediante su
	// forma y coordenadas.

	private Pieza pieceShape;
	private int coords[][];
	private int[][][] coordsTable;
	
	protected int tamanio;
	protected Color color;
	
	protected void rotar() {
		//M�TODO POR EL CUAL LAS FICHAS ROTAN
	}
	
	protected void mover() {
		//M�TODO POR EL CUAL LAS FICHAS SE MUEVEN
	}

	// M�todo constructor que genera un contorno de pieza en el tablero. Ya en el
	// tablero toma una forma gracias al ramdon.
	public Forma() {

		coords = new int[4][2];
		setShape(Pieza.figuraBorrador);

	}

	// M�todo fundamental que crea una pieza con su forma, ubicacion.
	public void setShape(Pieza shape) {

		coordsTable = new int[][][] { { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } },
				{ { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } },
				{ { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } },
				{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } },
				{ { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } } };

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; ++j) {
				coords[i][j] = coordsTable[shape.ordinal()][i][j]; //CoordsTable guarda el #de la ficha segun la clase Piezas y la ubicacion de esta, tanto en x como en y
			}
		}
		pieceShape = shape;

	}

	private void setX(int index, int x) {
		coords[index][0] = x;
	}

	private void setY(int index, int y) {
		coords[index][1] = y;
	}

	public int x(int index) {
		return coords[index][0];
	}

	public int y(int index) {
		return coords[index][1];
	}

	public Pieza getShape() {
		return pieceShape;
	}

	// M�todo por el cual crea una pieza aleatoria de 7 posibles.
	public void setRandomShape() {
		Random r = new Random();
		int x = Math.abs(r.nextInt()) % 7 + 1;
		Pieza[] values = Pieza.values();
		setShape(values[x]);
	}

	// M�todo que devuelve el m�nimo de la ficha en X.
	public int minX() {
		int m = coords[0][0];
		for (int i = 0; i < 4; i++) {
			m = Math.min(m, coords[i][0]);
		}
		return m;
	}

	// M�todo que devuelve el m�nimo de la ficha en Y.
	public int minY() {
		int m = coords[0][1];
		for (int i = 0; i < 4; i++) {
			m = Math.min(m, coords[i][1]);
		}
		return m;
	}

	// M�todo que rota la pieza
	public Forma rotateLeft() {
		if (pieceShape == Pieza.figuraCuadrado)
			return this;

		Forma result = new Forma();
		result.pieceShape = pieceShape;

		for (int i = 0; i < 4; ++i) {
			result.setX(i, y(i));
			result.setY(i, -x(i));
		}
		return result;
	}

	// M�todo que rota la pieza
	public Forma rotateRight() {
		if (pieceShape == Pieza.figuraCuadrado)
			return this;

		Forma result = new Forma();
		result.pieceShape = pieceShape;

		for (int i = 0; i < 4; ++i) {
			result.setX(i, -y(i));
			result.setY(i, x(i));
		}
		return result;
	}
}