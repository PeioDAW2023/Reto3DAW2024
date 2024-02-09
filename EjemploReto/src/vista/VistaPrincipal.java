package vista;

import java.awt.Toolkit;

import javax.swing.JFrame;

import controlador.GestionBD;
import controlador.GestionImagenes;
import paneles.*;

public class VistaPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public VistaPrincipal() {
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Cines Seventh");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/multimedia/seventh.jpg")));
		}
	
	GestionImagenes img = new GestionImagenes();
	GestionBD gestion = new GestionBD();

	
	public void cambiarPanel(int IDCambiarPanel) {

		switch (IDCambiarPanel) {
		case 0:
			setContentPane(new PanelBienvenida(this));
			break;
		case 1:
			setContentPane(new PanelLogin(this));
			break;
		case 2:
			setContentPane(new PanelRegistro(this));
			break;
		case 3:
			setContentPane(new PanelCine(this));
			break;
		case 4:
			setContentPane(new PruebaDatos(this, gestion, img));
			break;
		case 5:
			setContentPane(new PanelResumen(this, img));
			break;
		case 6:
			setContentPane(new PanelPruebas(this, gestion));
			break;
		case 7:
			
		}
	}

	public void lanzarVentana() {
		this.cambiarPanel(4);
		this.setVisible(true);
	}

}
