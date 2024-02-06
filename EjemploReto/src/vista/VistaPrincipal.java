package vista;

import java.awt.Toolkit;

import javax.swing.JFrame;

import paneles.PanelBienvenida;
import paneles.PanelLogin;
import paneles.PanelPeliculas;
import paneles.PanelRegistro;
import paneles.PanelResumen;

public class VistaPrincipal extends JFrame{

	private static final long serialVersionUID = 1L;

	public VistaPrincipal() {
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Cine Elorrieta");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VistaPrincipal.class.getResource("/multimedia/seventh.jpg")));
		
	}

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
			setContentPane(new PanelPeliculas(this));
			break;
		case 4:
			setContentPane(new PanelResumen(this));
			break;
		}
	}

	public void lanzarVentana() {
		this.cambiarPanel(0);
		this.setVisible(true);
	}

}
