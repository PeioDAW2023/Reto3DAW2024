package paneles;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controlador.GestionBD;
import controlador.GestionImagenes;
import modelo.Pelicula;
import modelo.Sesion;
import vista.VistaPrincipal;

public class PruebaDatos extends JPanel {
	private static final long serialVersionUID = 1L;

	public ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	public ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
	private int peliculaActual = 0;
	private JLabel peli;

	private JPanel panel, panel_1, panel_2;

	public PruebaDatos() {

	}

	public PruebaDatos(VistaPrincipal vp, GestionBD gestion, GestionImagenes img) {

		setSize(vp.getSize());
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setSize(vp.getSize());
		add(tabbedPane);

		panel = new JPanel();
		tabbedPane.addTab("01/03/2024", null, panel, null);

		panel_1 = new JPanel();
		tabbedPane.addTab("02/03/2024", null, panel_1, null);

		panel_2 = new JPanel();
		tabbedPane.addTab("03/03/2024", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(365, 97, 242, 14);
		panel_2.add(lblNombre);

		JButton btnSiguiente = new JButton(">");

		btnSiguiente.setBounds(732, 274, 41, 23);
		panel_2.add(btnSiguiente);

		JButton btnAnterior = new JButton("<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAnterior.setBounds(10, 274, 41, 23);
		panel_2.add(btnAnterior);

		JLabel lblDuracion = new JLabel("Duracion:");
		lblDuracion.setBounds(365, 122, 242, 14);
		panel_2.add(lblDuracion);

		JLabel lblSinopsis = new JLabel("Sinopsis:");
		lblSinopsis.setVerticalAlignment(SwingConstants.TOP);
		lblSinopsis.setBounds(365, 147, 242, 203);
		panel_2.add(lblSinopsis);
		String textoBoton = "22:00 Sala 1";
		JButton btnSesion1 = new JButton("<html><center>" + textoBoton.replaceAll("\\n", "<br>") + "</html>");
		// btnNewButton;
		btnSesion1.setBounds(365, 425, 77, 49);
		panel_2.add(btnSesion1);

		JButton btnSesion2 = new JButton("<html><center>22:00 Sala 1</html>");
		btnSesion2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSesion2.setBounds(492, 425, 77, 49);
		panel_2.add(btnSesion2);

		JLabel lblNewLabel = new JLabel("");
		// lblNewLabel.setIcon(new
		// ImageIcon(PruebaDatos.class.getResource("/multimedia/john_wick.jpg")).getImage().getScaledInstance(lblNewLabel.getWidth(),
		// lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setBounds(76, 77, 233, 378);
		panel_2.add(lblNewLabel);

		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// g.mostrarDatos("Elorrieta",
				// tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
				gestion.cargarDatos("Elorrieta", tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
				peliculas = gestion.devolverArraylistPeliculas();
				sesiones = gestion.devolverArrayListSesiones();
				// lblNombre.setText(peliculas.get(1).getNomPelicula());

			}
		});

		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (peliculaActual == peliculas.size() - 1) {
					peliculaActual = 0;
				} else {

					peliculaActual = peliculaActual + 1;
					lblNombre.setText("Nombre: " + peliculas.get(peliculaActual).getNomPelicula());
					lblDuracion.setText("Duración: " + String.valueOf(peliculas.get(peliculaActual).getDuracion()));
					lblSinopsis.setText("<html>Sinopsis: "+ peliculas.get(peliculaActual).getDcp().replaceAll("\\n", "<br>") + "</html>");

					for (int i = 0; i < sesiones.size(); i++)

						if (sesiones.get(i).getCodPelicula().equals(peliculas.get(peliculaActual).getCodPelicula())) {
							btnSesion1.setText(
									"<html>" + sesiones.get(i).getHora().replaceAll("\\n", "<br>") + "</html>");
						}
					crearInformacionPelicula();
					//img.ajustarImagen(peli, (peliculas.get(peliculaActual).getNomPelicula() +".jpg"));
					System.out.println(peliculas.get(peliculaActual).getNomPelicula() + ".jpg");
				}
			}
		});
	}

	public void cambiarCampos() {
	}

	private void crearInformacionPelicula() {
		peli = new JLabel("");
		peli.setBorder(new LineBorder(Color.black, 1, true));
		peli.setBounds(100, 100, 200, 300);
		panel_2.add(peli);
		peli.repaint();
	}
}
