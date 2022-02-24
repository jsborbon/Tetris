package Tetris;

/*
 * NOMBRE DEL PROYECTO: TETRIS
 * 
 * AUTORES: Javier Santiago Borbón Borbón y José Andrés Sanabria Arias
 * Códigos: 20182020085 y 2018202095
 * 
 * 
 * FECHA: 28/03/2020 (Marzo 28 de 2020)
 * 
 * 
 * Versión 1.0;
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Interfaz {

	// Objetos para la interfaz, ventana,paneles y botón.
	JFrame marcoPrograma;
	private JButton botonSalir;
	private JPanel panel2;

	// Objetos para la persistencia mediante Archivos.
	String ubicacionNombre = null;
	String ubicacionPuntaje = null;
	JLabel nombres[] = new JLabel[10];
	JLabel puntajes[] = new JLabel[10];
	String nombre[];
	String puntaje[];
	int puntaje1[];
	String jugador = null;
	JLabel etiquetaPunto;

	// El constructor de la clase Interfaz la cual contiene el método Inicializador
	public Interfaz(String jugador, String ubicacionNombre, String ubicacionPuntaje) {
		inicializar(jugador, ubicacionNombre, ubicacionPuntaje);

	}

	// Método inicializador, acá pasa toda la magia.
	public void inicializar(String jugador, String ubicacionNombre, String ubicacionPuntaje) {

		// Asignación del nombre del jugador a la variable de la clase Interfaz.
		
		this.jugador = jugador;

		// Creación de la ventana, con sus dimensiones, icono y estableciendo el manejo
		// de los paneles.
		marcoPrograma = new JFrame();
		marcoPrograma.setUndecorated(true);
		marcoPrograma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marcoPrograma.getContentPane().setBackground(Color.BLACK);
		marcoPrograma.setBounds(0, 0, 600, 400);
		marcoPrograma.setLocationRelativeTo(null);
		marcoPrograma
				.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Imagen/iconoTetris.png")));
		marcoPrograma.getContentPane().setLayout(null);

		// Asignación de las ubicaciones de los archivos a las variables de la clase
		// Interfaz.
		this.ubicacionNombre = ubicacionNombre;
		this.ubicacionPuntaje = ubicacionPuntaje;

		// Creación de los archivos.
		Archivo archivoNombre = new Archivo(ubicacionNombre);
		Archivo archivoPuntaje = new Archivo(ubicacionPuntaje);

		// Asignación de los datos de los archivos para trabajar durante todo el
		// programa
		nombre = archivoNombre.leerArchivoString(ubicacionNombre);
		puntaje = archivoPuntaje.leerArchivoIntAString(ubicacionPuntaje);
		puntaje1 = archivoPuntaje.leerArchivoInt(ubicacionPuntaje);

		// Creación del panel 2 con sus respectivas etiquetas, texto y botón. El panel 2
		// se encarga de llevar el nombre del jugador, el puntaje del jugador y el botón
		// para salir del juego.
		panel2 = new JPanel();
		panel2.setBounds(237, 0, 200, 420);
		panel2.setBackground(Color.DARK_GRAY);
		marcoPrograma.getContentPane().add(panel2);
		panel2.setLayout(null);

		// Logo que aparece en el panel derecho
		JLabel lblLogo = new JLabel("");
		lblLogo.setVerticalAlignment(SwingConstants.TOP);
		lblLogo.setIcon(new ImageIcon(Interfaz.class.getResource("/Imagen/logoInterfaz.jpg")));
		lblLogo.setBounds(10, 11, 180, 101);
		panel2.add(lblLogo);

		botonSalir = new JButton("SALIR");
		botonSalir.setBackground(Color.BLACK);
		botonSalir.setBounds(43, 346, 80, 36);
		botonSalir.setForeground(Color.RED);
		botonSalir.addActionListener(new ActionListener() {
			// Cuando se le da click al botón de salir se cierra el juego.
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});
		panel2.add(botonSalir);

		etiquetaPunto = new JLabel("0");
		etiquetaPunto.setForeground(Color.CYAN);
		etiquetaPunto.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		etiquetaPunto.setBackground(Color.BLACK);
		etiquetaPunto.setBounds(73, 266, 117, 36);
		panel2.add(etiquetaPunto);

		JLabel lblHighScoreTitle = new JLabel("PUNTAJE M\u00C1S ALTO:");
		lblHighScoreTitle.setForeground(Color.PINK);
		lblHighScoreTitle.setBounds(10, 123, 164, 14);
		panel2.add(lblHighScoreTitle);

		JLabel lblScore = new JLabel("999999 puntos");
		lblScore.setForeground(Color.CYAN);
		lblScore.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblScore.setBounds(20, 148, 170, 36);
		lblScore.setText(puntaje[0] + " puntos");
		panel2.add(lblScore);

		JLabel etiquetaPlayer = new JLabel("JUGADOR:");
		etiquetaPlayer.setForeground(Color.PINK);
		etiquetaPlayer.setBackground(Color.BLACK);
		etiquetaPlayer.setBounds(55, 196, 68, 23);
		panel2.add(etiquetaPlayer);

		JLabel etiquetaJugador = new JLabel("<dynamic>");
		etiquetaJugador.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaJugador.setForeground(Color.CYAN);
		etiquetaJugador.setFont(new Font("Tahoma", Font.PLAIN, 25));
		etiquetaJugador.setBackground(Color.BLACK);
		etiquetaJugador.setBounds(20, 217, 151, 23);
		etiquetaJugador.setText(jugador);
		panel2.add(etiquetaJugador);

		JLabel etiquetaPuntaje2 = new JLabel("PUNTAJE");
		etiquetaPuntaje2.setForeground(Color.PINK);
		etiquetaPuntaje2.setBackground(Color.BLACK);
		etiquetaPuntaje2.setBounds(55, 251, 68, 23);
		panel2.add(etiquetaPuntaje2);

		JLabel logo = new JLabel("");
		logo.setVerticalAlignment(SwingConstants.TOP);
		logo.setIcon(new ImageIcon(Interfaz.class.getResource("/Imagen/frameImg.png")));
		logo.setBounds(0, 0, 237, 420);
		marcoPrograma.getContentPane().add(logo);

		// Creación del panel central, panel 3 o tablero. Este tablero se encarga del
		// juego.
		Tablero tablero = new Tablero(this);
		tablero.setBounds(18, 0, 200, 400);
		marcoPrograma.getContentPane().add(tablero);
		panel2.add(lblScore);

		// Inicia el juego.
		tablero.start();

		marcoPrograma.setSize(438, 420);

	}

	// Métodos getters para que Tablero realice sus respectivos procesos.

	public JLabel getEtiquetaPunto() {
		return etiquetaPunto;
	}

	public JLabel[] getNombres() {

		return nombres;

	}

	public JLabel[] getPuntajes() {

		return puntajes;

	}

	public String[] getNombre() {

		return nombre;

	}

	public String[] getPuntaje() {

		return puntaje;

	}

	public int[] getPuntaje1() {

		return puntaje1;

	}

	public String getJugador() {

		return jugador;

	}

	public String getUbicacionNombre() {

		return ubicacionNombre;

	}

	public String getUbicacionPuntaje() {

		return ubicacionPuntaje;

	}

}
