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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tablero extends JPanel implements ActionListener {

	// Tama�o del tablero del tetris
	private final int anchoTablero = 10;
	private final int alturaTablero = 20;

	// Varianbles necesarias para procesar el juego en el tablero.

	private boolean haCaido = false;
	private boolean empezo = false;
	private boolean enPausa = false;
	private int numLineasRemovidas = 0;
	private int curX = 0;
	private int curY = 0;
	private JLabel estadoPunto;
	private Forma curPiece;
	private Pieza[] board;

	// Llamamos a las figuras

	private FiguraBorrador figBorrador = new FiguraBorrador();
	private FiguraCuadrado figC = new FiguraCuadrado();
	private FiguraL figL = new FiguraL();
	private FiguraLinea figLinea = new FiguraLinea();
	private FiguraLInv figLInv = new FiguraLInv();
	private FiguraS figS = new FiguraS();
	private FiguraT figT = new FiguraT();
	private FiguraZ figZ = new FiguraZ();
	

	// Variables necesarias para hacer la persistencia mediante Archivos.
	private Archivo archivo = new Archivo();
	private String ubicacionNombre;
	private String ubicacionPuntaje;
	private String nombre[];
	private String puntaje[];
	private int puntaje1[];
	private int auxPuntaje = 0;
	private String auxNombre = null;
	private String puntoCadena = null;
	private int puntoEntero = 0;
	private String jugador = null;
	private Timer timer;

	// Contructor de la clase Tablero, en la cual se encarga del Panel3, central o
	// tablero. Se requiere de parametro la Interfaz para hacer un traspaso de
	// Variables.
	public Tablero(Interfaz interfaz) {

		setFocusable(true);
		setBackground(Color.BLACK);
		curPiece = new Forma();
		timer = new Timer(400, this);
		timer.start();
		ubicacionNombre = interfaz.getUbicacionNombre();
		ubicacionPuntaje = interfaz.getUbicacionPuntaje();
		jugador = interfaz.getJugador();
		estadoPunto = interfaz.getEtiquetaPunto();
		nombre = interfaz.getNombre();
		puntaje = interfaz.getPuntaje();
		puntaje1 = interfaz.getPuntaje1();

		board = new Pieza[anchoTablero * alturaTablero];
		addKeyListener(new TAdapter());
		clearBoard();
	}

	// M�todo que hace que la pieza caiga.
	public void actionPerformed(ActionEvent e) {
		if (haCaido) {
			haCaido = false;
			newPiece();
		} else {
			oneLineDown();
		}
	}

	// Establece los l�mites del juego en el tablero.
	int squareWidth() {
		return (int) getSize().getWidth() / anchoTablero;
	}

	int squareHeight() {
		return (int) getSize().getHeight() / alturaTablero;
	}

	Pieza shapeAt(int x, int y) {
		return board[(y * anchoTablero) + x];
	}

	// M�todo principal del tablero, da inicio al timer, creaci�n de piezas.
	public void start() {
		if (enPausa)
			return;

		empezo = true;
		haCaido = false;
		numLineasRemovidas = 0;
		clearBoard();

		newPiece();
		timer.start();
	}

	// M�todo por el cual se logra detener el juego.
	private void pause() {
		if (!empezo)
			return;

		enPausa = !enPausa;
		if (enPausa) {
			timer.stop();
			estadoPunto.setText("Pausado");
		} else {
			timer.start();
			estadoPunto.setText(String.valueOf(numLineasRemovidas));
		}
		repaint();
	}

	// M�todo por el cual se pintan las pizas gracias a la clase Forma.
	public void paint(Graphics g) {
		super.paint(g);

		Dimension size = getSize();
		int boardTop = (int) size.getHeight() - alturaTablero * squareHeight(); // Pintar Tablero

		for (int i = 0; i < alturaTablero; ++i) {
			for (int j = 0; j < anchoTablero; ++j) {
				Pieza shape = shapeAt(j, alturaTablero - i - 1);
				if (shape != Pieza.figuraBorrador)
					drawSquare(g, 0 + j * squareWidth(), boardTop + i * squareHeight(), shape);
			}
		}

		if (curPiece.getShape() != Pieza.figuraBorrador) {
			for (int i = 0; i < 4; ++i) {
				int x = curX + curPiece.x(i);
				int y = curY - curPiece.y(i);
				drawSquare(g, 0 + x * squareWidth(), boardTop + (alturaTablero - y - 1) * squareHeight(),
						curPiece.getShape());
			}
		}
	}

	// M�todo para hacer caer la ficha (con BarraEspaciadora en el juego).
	private void dropDown() {
		int newY = curY;
		while (newY > 0) {
			if (!tryMove(curPiece, curX, newY - 1))
				break;
			--newY;
		}
		pieceDropped();
	}

	// M�todo por el cual la ficha baja si no hay un obstaculo.
	private void oneLineDown() {
		if (!tryMove(curPiece, curX, curY - 1))
			pieceDropped();
	}

	// M�todo para limpiar el rastro de las fichas al caer.
	private void clearBoard() {
		for (int i = 0; i < alturaTablero * anchoTablero; ++i)
			board[i] = Pieza.figuraBorrador;
	}

	// M�todo por el cual se verifica si la ficha ha caido
	private void pieceDropped() {
		for (int i = 0; i < 4; ++i) {
			int x = curX + curPiece.x(i);
			int y = curY - curPiece.y(i);
			board[(y * anchoTablero) + x] = curPiece.getShape();
		}

		removerLineasLlenas();

		if (!haCaido)
			newPiece();
	}

	// M�todo fundamental para crear piezas aleatorias, adem�s de ser el m�todo que
	// crea la persistencia.
	private void newPiece() {
		curPiece.setRandomShape();
		curX = anchoTablero / 2 + 1;
		curY = alturaTablero - 1 + curPiece.minY();

		if (!tryMove(curPiece, curX, curY)) {
			curPiece.setShape(Pieza.figuraBorrador);
			timer.stop();
			empezo = false;
			puntoCadena = estadoPunto.getText();
			puntoEntero = Integer.parseInt(puntoCadena);
			JOptionPane.showMessageDialog(null, "JUEGO TERMINADO!!\n\nSu puntaje es: " + puntoEntero, "GAME OVER",
					JOptionPane.INFORMATION_MESSAGE, null);

			actualizarTabla();

		}
	}

	// M�todo que ayuda a no sobre poner las fichas al rotar.
	private boolean tryMove(Forma newPiece, int newX, int newY) {
		for (int i = 0; i < 4; ++i) {
			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);
			if (x < 0 || x >= anchoTablero || y < 0 || y >= alturaTablero)
				return false;
			if (shapeAt(x, y) != Pieza.figuraBorrador)
				return false;
		}

		curPiece = newPiece;
		curX = newX;
		curY = newY;
		repaint();
		return true;
	}

	// M�todo por el cual identifica cuando hay una linea, aumenta el puntaje del
	// jugador.
	private void removerLineasLlenas() {
		int numFullLines = 0;

		for (int i = alturaTablero - 1; i >= 0; --i) {
			boolean lineIsFull = true;

			for (int j = 0; j < anchoTablero; ++j) {
				if (shapeAt(j, i) == Pieza.figuraBorrador) {
					lineIsFull = false;
					break;
				}
			}

			if (lineIsFull) {
				++numFullLines;
				for (int k = i; k < alturaTablero - 1; ++k) {
					for (int j = 0; j < anchoTablero; ++j)
						board[(k * anchoTablero) + j] = shapeAt(j, k + 1);
				}
			}
		}

		if (numFullLines > 0) {
			numLineasRemovidas += numFullLines;
			estadoPunto.setText(String.valueOf(numLineasRemovidas));
			haCaido = true;
			curPiece.setShape(Pieza.figuraBorrador);
			repaint();
		}
	}

	private void drawSquare(Graphics g, int x, int y, Pieza shape) {
			
		Color colors[] = { figBorrador.getColor(), figZ.getColor(), figS.getColor(), figLinea.getColor(), figT.getColor(), figC.getColor(), figL.getColor(), figLInv.getColor() };

		Color color = colors[shape.ordinal()]; //Se crea un arreglo de 7 figuras

		g.setColor(color);
		g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

		g.setColor(color.brighter());
		g.drawLine(x, y + squareHeight() - 1, x, y);
		g.drawLine(x, y, x + squareWidth() - 1, y);

		g.setColor(color.darker());
		g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
		g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
	}

	// Clase que capta los eventos por teclado posteriormente mueve la pieza o
	// detiene el juego.
	class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (!empezo || curPiece.getShape() == Pieza.figuraBorrador) {
				return;
			}

			int keycode = e.getKeyCode();

			if (keycode == 'p' || keycode == 'P') {
				pause();
				return;
			}

			if (enPausa)
				return;

			switch (keycode) {
			case KeyEvent.VK_LEFT:
				tryMove(curPiece, curX - 1, curY);
				break;
			case KeyEvent.VK_RIGHT:
				tryMove(curPiece, curX + 1, curY);
				break;
			case KeyEvent.VK_DOWN:
				oneLineDown();
				break;
			case KeyEvent.VK_UP:
				tryMove(curPiece.rotateLeft(), curX, curY);
				break;
			case KeyEvent.VK_SPACE:
				dropDown();
				break;
			}

		}
	}

	private void actualizarTabla() {

		nombre[10] = jugador;
		puntaje1[10] = puntoEntero;

		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < 10; j++) {

				if (puntaje1[j] < puntaje1[j + 1]) {

					auxPuntaje = puntaje1[j];
					puntaje1[j] = puntaje1[j + 1];
					puntaje1[j + 1] = auxPuntaje;

					auxNombre = nombre[j];
					nombre[j] = nombre[j + 1];
					nombre[j + 1] = auxNombre;

				}

			}

		}

		for (int i = 0; i < 10; i++) {

			puntaje[i] = Integer.toString(puntaje1[i]);

		}

		archivo.sobreEscribirArchivo(nombre, ubicacionNombre);
		archivo.sobreEscribirArchivo(puntaje1, ubicacionPuntaje);

	}

}
