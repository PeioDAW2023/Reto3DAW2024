package paneles;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controlador.GestionBD;
import modelo.Cine;
import modelo.EntradaCompleta;
import modelo.Sesion;
import vista.VistaPrincipal;

public class PanelCine extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// Declaración de campos, Arrays y ArrayLists
	private JLabel lblCines;	
	private JButton btnSiguiente; 
	private JButton btnAtras;	
	private JButton btnSalir;
	private JButton btnCarrito;
	
	private JComboBox<String> comBoxCines;
	private JComboBox<String> comBoxFechas;
	
	private String[] arrayCines;
	private String[] arrayFechas = new String[1]; // Se carga con un espacio, para que no salte el error de null en el comboBox
	private ArrayList<Cine> cines = new ArrayList<Cine>();
	private ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
	private ArrayList<EntradaCompleta> entradas = new ArrayList<EntradaCompleta>();

	// Constructor del Panel
	public PanelCine() {
	}

	public PanelCine(VistaPrincipal vp, GestionBD gestion) {

		// Adaptar el panel al frame
		setSize(vp.getSize());
		setLayout(null);
		
		// Carga los ArrayList de cines y entradas
		gestion.cargarCines(); // Carga los ArrayList de cines
		cines = gestion.devolverArrayListCines();
		entradas = gestion.devolverArraylistEntradas(); // Se utiliza para no perder el ArrayList de entradas al cambiar de paneles
		cargarArrayCines(); // Carga el arrayCines que se utilizará para establecer el texto del combobox

		// AGREGACION DE CAMPOS
		// Labels

		lblCines = new JLabel("Elija un cine y una fecha:");
		lblCines.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 45));
		lblCines.setBounds(139, 126, 780, 75);
		add(lblCines);

		// ComboBox

		comBoxCines = new JComboBox<String>(arrayCines);
		comBoxCines.setBounds(139, 250, 247, 30);
		add(comBoxCines);

		comBoxFechas = new JComboBox<String>();
		comBoxFechas.setBounds(396, 250, 247, 30);
		add(comBoxFechas);

		// Botones

		btnCarrito = new JButton("Carrito");
		btnCarrito.setBounds(686, 11, 89, 23);
		add(btnCarrito);

		btnSalir = new JButton("");
		btnSalir.setBounds(10, 11, 31, 31);
		// Cargamos el icono de salida en el botón, y redimensionamos la imagen para que quepa dentro
		btnSalir.setIcon(new ImageIcon(new ImageIcon(VistaPrincipal.class.getResource("/multimedia/IconoSalir.png"))
				.getImage().getScaledInstance(btnSalir.getWidth() - 7, btnSalir.getHeight() - 6, Image.SCALE_DEFAULT)));
		add(btnSalir);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBackground(UIManager.getColor("Button.background"));

		btnSiguiente.setBounds(480, 400, 95, 25);
		add(btnSiguiente);

		btnAtras = new JButton("Atras");
		btnAtras.setBounds(225, 400, 65, 25);
		add(btnAtras);

		// ACCIONES DE COMBOBOX Y BOTONES

		// ComboBox
		comBoxCines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comBoxFechas.removeAllItems(); // Primero limpia el comboBox de Fechas
				cargarFechasSegunCine(comBoxCines.getSelectedItem().toString(), gestion); // Llama al método usando como parámetro el campo seleccionado en el comboBox de cines
				comBoxFechas.setSelectedItem(null); // Establece de primeras que el item seleccionado es ninguno
				for (int i = 0; i < sesiones.size(); i++) {
					comBoxFechas.addItem(arrayFechas[i]); // Agrega uno a uno las fechas de un Array para proyectarlo en el comboBox
				}
				repaint(); 
			}

		});

		// Botones
		btnCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarPanel(5); // Cambia al Panel de Carrito
			}
		});

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Muestra un mensaje de con la opción de salir de la aplicación
				int result = JOptionPane.showConfirmDialog(null, "Seguro que quiere salir?\nSe perderá lo que esté en su carrito",
						"SALIR", JLabel.CENTER, JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
				}
			}
		});

		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestion.guardarCineYFechaSeleccionadas(comBoxCines.getSelectedItem().toString(),
						comBoxFechas.getSelectedItem().toString()); // Llama a un método de la GestionBD que guarda las opciones escogidas en los comboBox
				;
				vp.cambiarPanel(4); // Cambia al Panel de Peliculas
			}

		});

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp.cambiarPanel(1); // Cambia al Panel de Login
			}

		});

	}

	// MÉTODOS
	
	// Carga los nombres obtenidos del ArrayList de cines en el arrayCines
	private void cargarArrayCines() {
		arrayCines = new String[cines.size()];
		for (int i = 0; i < cines.size(); i++) { // Recorre el ArrayList buscando nombres de cines y los agrega al arrayCines
			arrayCines[i] = cines.get(i).getNomCine();
		}

	}

	// Carga las fechas obtenidas por el cine escogido, y carga esas fechas en el arrayFechas
	private void cargarFechasSegunCine(String cine, GestionBD gestion) {
		
		sesiones.clear(); // Limpia el ArrayList para evitar acumulación
		gestion.cargarFechasPorCine(cine); // Llama al método de GestionBD para cargar el Arraylist sesiones
		sesiones = gestion.devolverArrayListSesiones(); // Guarda el ArrayList obtenido en el ArrayList local
		arrayFechas = new String[sesiones.size()];
		for (int i = 0; i < sesiones.size(); i++) { // Recorre el ArrayList en busca de las fecha y las agrega al arrayFechas
			arrayFechas[i] = sesiones.get(i).getFecha();
		}

	}
}
