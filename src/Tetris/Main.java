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

import java.awt.EventQueue;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] arg) throws Exception {
 
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// Se crean los archivos
					String ubicacionNombre = "src/Archivo/archivoNombre.txt";
					String ubicacionPuntaje = "src/Archivo/archivoPuntaje.txt";
					VentanaPrincipal window = new VentanaPrincipal(ubicacionNombre, ubicacionPuntaje);
					window.setVisible(true);

					// Creacion del objeto que reproduce la m�sica en el juego.
					String filepath = "src/Sonido/sonidoTetris.wav";
					Sonido music = new Sonido();
					music.play(filepath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}