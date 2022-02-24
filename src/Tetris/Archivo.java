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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class Archivo {

	public Archivo() {

	}

	// M�todo constructor, el cual crea el archivo, lo abre y cierra.
	public Archivo(String ubicacion) {

		DataOutputStream archivo = null;

		try {

			archivo = new DataOutputStream(new FileOutputStream(ubicacion, true));

			archivo.close();

		} catch (FileNotFoundException fnfe) {

		} catch (IOException ioe) {

		}

	}

	// M�todos que se utilizaron para crear el primer archivo
	public void escribirArchivoNombre(String nombre[], String ubicacion) {

		DataOutputStream archivo = null;

		try {

			archivo = new DataOutputStream(new FileOutputStream(ubicacion, true));

			for (int i = 0; i < 10; i++) {

				archivo.writeUTF(nombre[i]);

			}

			archivo.close();

		} catch (FileNotFoundException fnfe) {

		} catch (IOException ioe) {

		}

	}

	// M�todos que se utilizaron para crear el primer archivo
	public void escribirArchivoPuntaje(String puntaje[], String ubicacion) {

		int[] puntaje1 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		DataOutputStream archivo = null;

		try {

			archivo = new DataOutputStream(new FileOutputStream(ubicacion, true));

			for (int i = 0; i < 10; i++) {

				puntaje1[i] = Integer.parseInt(puntaje[i]);

			}

			for (int i = 0; i < 10; i++) {

				archivo.writeInt(puntaje1[i]);

			}

			archivo.close();

		} catch (FileNotFoundException fnfe) {

		} catch (IOException ioe) {

		}

	}

	// M�todo que hace la persistencia de los puntajes al final del juego
	public void sobreEscribirArchivo(String nombre[], String ubicacion) {

		// DataOutputStream archivo = null;

		try {

			// archivo = new DataOutputStream( new FileOutputStream(ubicacion,true));

			RandomAccessFile archivo = new RandomAccessFile(ubicacion, "rw");

			archivo.seek(0);

			for (int i = 0; i < 10; i++) {

				archivo.writeUTF(nombre[i]);

			}

			archivo.close();

		} catch (FileNotFoundException fnfe) {

		} catch (IOException ioe) {

		}

	}

	// M�todo que hace la persistencia de los puntajes al final del juego
	public void sobreEscribirArchivo(int puntaje[], String ubicacion) {

		try {

			RandomAccessFile archivo = new RandomAccessFile(ubicacion, "rw");

			archivo.seek(0);

			for (int i = 0; i < 10; i++) {

				archivo.writeInt(puntaje[i]);

			}

			archivo.close();

		} catch (FileNotFoundException fnfe) {

		} catch (IOException ioe) {

		}

	}

	// M�todo que leen el archivo, y tranfieren sus datos a variables para su uso.
	public int[] leerArchivoInt(String ubicacion) {

		DataInputStream archivo = null;
		int[] puntaje = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		try {

			archivo = new DataInputStream(new FileInputStream(ubicacion));

			for (int i = 0; i < 10; i++) {

				puntaje[i] = archivo.readInt();

			}

		} catch (FileNotFoundException fnfe) {

		} catch (IOException ioe) {

		}

		return puntaje;

	}

	// M�todo que leen el archivo, y tranfieren sus datos a variables para su uso.
	public String[] leerArchivoString(String ubicacion) {

		DataInputStream archivo = null;
		String[] nombre = { null, null, null, null, null, null, null, null, null, null, null };

		try {

			archivo = new DataInputStream(new FileInputStream(ubicacion));

			for (int i = 0; i < 10; i++) {

				nombre[i] = archivo.readUTF();

			}

		} catch (FileNotFoundException fnfe) {

		} catch (IOException ioe) {

		}

		return nombre;

	}

	// M�todo que leen el archivo, y tranfieren sus datos a variables para su uso.
	public String[] leerArchivoIntAString(String ubicacion) {

		int puntajeEntero[] = leerArchivoInt(ubicacion);

		String[] puntaje = { null, null, null, null, null, null, null, null, null, null, null };

		for (int i = 0; i < 10; i++) {

			puntaje[i] = Integer.toString(puntajeEntero[i]);

		}

		return puntaje;

	}

}
