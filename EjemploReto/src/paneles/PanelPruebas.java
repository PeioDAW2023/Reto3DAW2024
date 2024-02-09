package paneles;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controlador.GestionBD;
import modelo.Pelicula;
import vista.VistaPrincipal;

public class PanelPruebas extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	
	public PanelPruebas(VistaPrincipal vp,  GestionBD gestion) {
		setSize(vp.getSize());
		setLayout(null);
		
		setBackground(Color.GRAY);

		//g.ArrayList<Pelicula> peliculas;
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setBounds(10, 11, 414, 239);
		add(tabbedPane);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("01/03/2024", null, panel_4, null);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("02/03/2024", null, panel_1, null);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("03/03/2024", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNombrePelicula1 = new JLabel("New label");
		lblNombrePelicula1.setBounds(23, 33, 142, 14);
		panel_2.add(lblNombrePelicula1);

		JLabel lblNombrePelicula2 = new JLabel("New label");
		lblNombrePelicula2.setBounds(23, 141, 128, 14);
		panel_2.add(lblNombrePelicula2);

		JLabel lblDcpPelicula1 = new JLabel("New label");
		lblDcpPelicula1.setBounds(23, 58, 128, 14);
		panel_2.add(lblDcpPelicula1);

		JLabel lblDcpPelicula2 = new JLabel("New label");
		lblDcpPelicula2.setBounds(23, 166, 142, 14);
		panel_2.add(lblDcpPelicula2);

		JButton btnHoraSesion1 = new JButton("New button");
		btnHoraSesion1.setBounds(247, 54, 89, 23);
		panel_2.add(btnHoraSesion1);

		JButton btnHoraSesion2 = new JButton("New button");
		btnHoraSesion2.setBounds(247, 162, 89, 23);
		panel_2.add(btnHoraSesion2);
		

		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//peliculas.clear();
				//g.cargarDatos("Elorrieta", tabbedPane.getTitleAt(2));
				//lblNombrePelicula1.setText(peliculas.get(1).getNomPelicula());
				gestion.mostrarDatos("Elorrieta", tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
				peliculas = gestion.devolverArraylistPeliculas();
				for (int i = 0; i<peliculas.size();i++) {
				lblNombrePelicula1.setText(peliculas.get(i).getCodPelicula());	
				}
				
			}
		});
	}
}

