package Tetris;

/**
 * @tittle		Ventana Principal
 * @param Ventana Principald
 * @author  Javier Santiago Borbón Borbón y José Andrés Sanabria Arias
 * @version 2.0
 * 
 * xd
 */
/*
 * NOMBRE DEL PROYECTO: TETRIS
 * 

 * Códigos: 20182020085 y 20182020095
 * 
 * 
 * FECHA: 28/03/2020 (Marzo 28 de 2020)
 * 
 * 
 * Versión 1.0;
 * 
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.Font;

public class VentanaPrincipal extends JFrame implements ActionListener {
// Objetos para la persistencia mediante Archivos.

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelPuntajes;
	private JPanel panelInstrucciones;
	private String ubicacionNombre = null;
	private String ubicacionPuntaje = null;
	private JLabel puntajes[] = new JLabel[10];
	private JLabel nombres[] = new JLabel[10];
	private String nombre[];
	private String puntaje[];
	// BOTONES
	private JButton btnSalir;
	private JButton btnPuntuacion;
	private JButton btnNewGame;
	private JButton btnInstrucciones;

	public VentanaPrincipal(String ubicacionNombre, String ubicacionPuntaje) {
		this.ubicacionNombre = ubicacionNombre;
		this.ubicacionPuntaje = ubicacionPuntaje;
		setBounds(100, 100, 600, 300);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Asignación de las ubicaciones de los archivos a las variables de la clase
		// Interfaz.
		this.ubicacionNombre = ubicacionNombre;
		this.ubicacionPuntaje = ubicacionPuntaje;

		// Creación de los archivos.
		Archivo archivoNombre = new Archivo(ubicacionNombre);
		Archivo archivoPuntaje = new Archivo(ubicacionPuntaje);
		nombre = archivoNombre.leerArchivoString(ubicacionNombre);
		puntaje = archivoPuntaje.leerArchivoIntAString(ubicacionPuntaje);

		// Creacion del Panel en donde se muestran las instrucciones
		panelInstrucciones = new JPanel();
		panelInstrucciones.setBackground(Color.BLACK);
		panelInstrucciones.setBounds(170, 0, 259, 262);
		contentPane.add(panelInstrucciones);
		panelInstrucciones.setLayout(null);
		JTextPane textoInstruccion = new JTextPane();
		textoInstruccion.setEditable(false);
		textoInstruccion.setForeground(new Color(255, 255, 255));
		textoInstruccion.setBackground(Color.BLACK);
		textoInstruccion.setText(
				" P : Pausar el juego.\r\n \u2191 : Rotar la figura.\r\n \u2192 : Moverse a la derecha.\r\n \u2190 : Moverse a la izquierda.\r\n \u2193 : Moverse hacia abajo.\r\n Barra Espaciadora: Bajar la figura.");
		textoInstruccion.setBounds(42, 161, 217, 101);
		panelInstrucciones.add(textoInstruccion);

		JLabel lblIcono = new JLabel("");
		lblIcono.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagen/iconoInstrucciones.jpg")));
		lblIcono.setBounds(0, 0, 259, 160);
		panelInstrucciones.add(lblIcono);
		panelInstrucciones.setVisible(false);
		// Creacion del Panel en donde se muestran los puntajes
		panelPuntajes = new JPanel();
		panelPuntajes.setBounds(112, 0, 375, 262);
		contentPane.add(panelPuntajes);
		JLabel lblTituloPuntajes = new JLabel("NUESTROS CAMPEONES");
		lblTituloPuntajes.setForeground(Color.BLUE);
		lblTituloPuntajes.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 27));
		lblTituloPuntajes.setBounds(72, 5, 240, 30);
		colocarPuntajes();
		panelPuntajes.add(lblTituloPuntajes);
		JLabel lblName = new JLabel("NOMBRE");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblName.setBounds(30, 40, 244, 14);
		panelPuntajes.add(lblName);

		JLabel lblPuntos = new JLabel("PUNTOS");
		lblPuntos.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPuntos.setBounds(266, 40, 99, 14);
		panelPuntajes.add(lblPuntos);

		JLabel lblImagenPFondo = new JLabel();
		lblImagenPFondo.setBounds(0, 0, 375, 262);
		lblImagenPFondo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagen/fondoPuntaje.jpg")));
		panelPuntajes.add(lblImagenPFondo);
		panelPuntajes.setVisible(false);
		btnInstrucciones = new JButton("Instrucciones");
		btnInstrucciones.setBounds(170, 266, 118, 23);
		btnInstrucciones.addActionListener(this);
		contentPane.add(btnInstrucciones);

		btnNewGame = new JButton("Nuevo Juego");
		btnNewGame.setBounds(26, 266, 124, 23);
		btnNewGame.addActionListener(this);
		contentPane.add(btnNewGame);

		btnPuntuacion = new JButton("Puntuaci\u00F3n");
		btnPuntuacion.setBounds(312, 266, 100, 23);
		btnPuntuacion.addActionListener(this);
		contentPane.add(btnPuntuacion);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		btnSalir.setBounds(445, 266, 106, 23);
		contentPane.add(btnSalir);

		JLabel lblImgVentana = new JLabel("");
		lblImgVentana.setBounds(0, 0, 600, 300);
		lblImgVentana.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagen/logoLauncher.jpg")));
		contentPane.add(lblImgVentana);

	}

	private void colocarPuntajes() {
		panelPuntajes.setLayout(null);

		for (int i = 0; i < puntajes.length; i++) {
			nombres[i] = new JLabel(nombre[i]);
			nombres[i].setBounds(10, 60 + 20 * i, 264, 14);
			panelPuntajes.add(nombres[i]);
			if (Integer.parseInt(puntaje[i]) < 10) {
				puntajes[i] = new JLabel("  " + puntaje[i]);
			} else if (Integer.parseInt(puntaje[i]) > 100) {
				puntajes[i] = new JLabel(puntaje[i]);
			} else {
				puntajes[i] = new JLabel(" " + puntaje[i]);
			}
			puntajes[i].setBounds(285, 60 + 20 * i, 80, 14);
			panelPuntajes.add(puntajes[i]);
		}

	}

	// Aquí es donde se le da funcionamiento a los botones
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnNewGame)) {
			panelPuntajes.setVisible(false);
			panelInstrucciones.setVisible(false);
			String name="";
			name=JOptionPane.showInputDialog("Ingrese el nombre del jugador:");
			if (name==null||name.isEmpty()||name=="") { //Si el jugador no ingresa nombre alguno
				name = "Player 1";
			}
			Interfaz juegoTetris = new Interfaz (name, ubicacionNombre, ubicacionPuntaje);
			dispose();
			juegoTetris.marcoPrograma.setVisible(true);

		}

		if (e.getSource().equals(btnPuntuacion)) {
			panelInstrucciones.setVisible(false);
			if (panelPuntajes.isVisible()) {
				panelPuntajes.setVisible(false);
			} else {
				panelPuntajes.setVisible(true);
			}

		}
		if (e.getSource().equals(btnSalir)) {
			System.exit(0);
		}
		if (e.getSource().equals(btnInstrucciones)) {
			panelPuntajes.setVisible(false);

			if (panelInstrucciones.isVisible()) {
				panelInstrucciones.setVisible(false);
			} else {
				panelInstrucciones.setVisible(true);
			}

		}
	}
}
