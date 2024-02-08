package paneles;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import controlador.GestorDatos;
import vista.VistaPrincipal;

public class PanelPeliculas extends JPanel {
	private static final long serialVersionUID = 1L;

	public PanelPeliculas(VistaPrincipal vp) {

	}

	private JLabel peli;

	public PanelPeliculas(VistaPrincipal vp, GestorDatos img) {

		setSize(vp.getSize());
		setLayout(null);
		crearInformacionPelicula();
		img.ajustarImagen(peli, "/multimedia/Dune: Parte 2.jpg");

		JButton btnResumen = new JButton("Resumen");
		btnResumen.setBackground(UIManager.getColor("Button.background"));
		btnResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarPanel(5);
			}

		});
		btnResumen.setBounds(480, 400, 95, 25);
		add(btnResumen);

	}

	private void crearInformacionPelicula() {
		peli = new JLabel("");
		peli.setBorder(new LineBorder(Color.black, 1, true));
		peli.setBounds(30, 100, 200, 325);
		add(peli);
		peli.repaint();
	}

}