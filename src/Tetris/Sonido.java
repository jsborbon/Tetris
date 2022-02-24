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
 
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {
	public void play(String musicLocation) {
		try {
			File musicPath = new File(musicLocation);
			if (musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);

			} else {
				System.out.println("No se encuentra la m�sica");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
