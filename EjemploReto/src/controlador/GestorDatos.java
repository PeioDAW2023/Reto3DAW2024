package controlador;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import vista.VistaPrincipal;

public class GestorDatos extends JFrame {
	private static final long serialVersionUID = 1L;

	public GestorDatos(VistaPrincipal vp) {
	}

	public GestorDatos() {
	}

	public void ajustarImagen(JLabel peli, String ruta) {
		String cambiarNombrePeli = this.reemplazarNombreDePeliculas(ruta);
		peli.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(cambiarNombrePeli)).getImage()
				.getScaledInstance(peli.getWidth(), peli.getHeight(), Image.SCALE_DEFAULT)));
	}

	public String reemplazarNombreDePeliculas(String ruta) {
		String nombrePelisBBDD = ruta;
		String nombrePelisDeMultimedia = nombrePelisBBDD.toLowerCase().replace(" ", "_").replace(":", "");
		return nombrePelisDeMultimedia;
	}
	
	
	
}
