package paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import controlador.GestorDatos;
import vista.VistaPrincipal;

public class PanelResumen extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel peli;
	private int posicionY = 90;

	private ArrayList<String> prueba = new ArrayList<String>();

	public PanelResumen() {
		
	}

	public PanelResumen(VistaPrincipal vp, GestorDatos img) {
		setSize(vp.getSize());
		setLayout(null);
		
		prueba.add("Hola1");
		prueba.add("Hola2");
		prueba.add("Hola3");
		
		JLabel lblCineElorrieta = new JLabel("Registro de Compras");
		lblCineElorrieta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCineElorrieta.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 35));
		lblCineElorrieta.setBounds(10, 5, 765, 75);
		add(lblCineElorrieta);
		
		for (int i = 0; i < prueba.size(); i++) {
			
			JLabel jLabelPrueba = new JLabel("");
			jLabelPrueba.setBorder(new LineBorder(Color.red, 1, true));
			jLabelPrueba.setBounds(25, (posicionY-5), 735, 125);
			add(jLabelPrueba);
						
			crearInformacionPelicula();
			img.ajustarImagen(peli, "/multimedia/Dune: Parte 2.jpg");
			posicionY += 130;

		}
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBackground(UIManager.getColor("Button.background"));
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}

		});
		btnSiguiente.setBounds(665, 515, 95, 25);
		add(btnSiguiente);
		
		
		
		
		
		
		
		
	}

	private void crearInformacionPelicula() {
		peli = new JLabel("");
		peli.setBorder(new LineBorder(Color.black, 1, true));
		peli.setBounds(40, posicionY, 70, 115);
		add(peli);
	}
}
