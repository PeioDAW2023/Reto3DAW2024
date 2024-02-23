package paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import controlador.GestionBD;
import controlador.GestionImagenes;
import modelo.EntradaCompleta;
import modelo.Pelicula;
import modelo.Sala;
import modelo.Sesion;
import vista.VistaPrincipal;

public class PanelPeliculas extends JPanel {
	private static final long serialVersionUID = 1L;

	// Declaracion de campos, variables y ArrayList
	
	private JLabel lblcantidadEntradas;
	private JLabel lblImagenPeli;
	private JLabel lblGenero;
	private JLabel lblNombre; 
	private JLabel lblDuracion;
	private JLabel lblSinopsis;
	private JButton btnSiguiente; 
	private JButton btnAnterior; 
	private JButton btnSesion1;
	private JButton btnSesion2;
	private JButton btnMenosEntradas;
	private JButton btnMasEntradas;
	private JButton btnAñadirEntrada;
	private JButton btnSalir;
	private JButton btnCarrito;
	private JButton btnCines;
	
	private boolean botonSesion1Seleccionado;
	private int peliculaActual = 0;
	private String rutaIcono;
	private Integer cantidad = 0;
	private String codEntrada;

	private ArrayList<Sala> salas = new ArrayList<Sala>();
	public ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	public ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
	public ArrayList<EntradaCompleta> entradas = new ArrayList<EntradaCompleta>();
	
	// Constructor del panel
	public PanelPeliculas() {
		setLayout(null);
	}

	public PanelPeliculas(VistaPrincipal vp, GestionBD gestion, GestionImagenes img) {
		
		// Adaptar el panel al frame
		setSize(vp.getSize());
		setLayout(null);
		
		// Cargar los ArrayLists de peliculas, sesiones y entradas
		gestion.cargarPeliculas();
		peliculas = gestion.devolverArraylistPeliculas();
		gestion.cargarSesionesDePeliculas(peliculas.get(0).getCodPelicula());
		sesiones = gestion.devolverArrayListSesiones();
		entradas = gestion.devolverArraylistEntradas();

		// AGREGACIÓN DE CAMPOS
		// Labels
		
		lblNombre = new JLabel("Nombre: " + peliculas.get(0).getNomPelicula());
		lblNombre.setBounds(365, 97, 242, 14);
		add(lblNombre);

		lblGenero = new JLabel("Género: " + peliculas.get(0).getGenero());
		lblGenero.setBounds(365, 145, 242, 14);
		add(lblGenero);
		
		lblSinopsis = new JLabel(
				"<html>Sinopsis: " + peliculas.get(0).getDcp().replaceAll("\\n", "<br>") + "</html>");
		lblSinopsis.setVerticalAlignment(SwingConstants.TOP);
		lblSinopsis.setBounds(365, 170, 242, 200);
		add(lblSinopsis);
		
		lblDuracion = new JLabel("Duracion: " + peliculas.get(0).getDuracion());
		lblDuracion.setBounds(365, 122, 242, 14);
		add(lblDuracion);
		
		lblcantidadEntradas = new JLabel(cantidad.toString());
		lblcantidadEntradas.setVisible(false);
		lblcantidadEntradas.setBorder(new LineBorder(Color.black, 1, true));
		lblcantidadEntradas.setHorizontalAlignment(JTextField.CENTER);
		add(lblcantidadEntradas);
		
		lblImagenPeli = new JLabel("");
		lblImagenPeli.setBounds(100, 100, 200, 300);
		lblImagenPeli.setBorder(new LineBorder(Color.black, 1, true));
		add(lblImagenPeli);
		cambiarImagen();
		
		// Botones
		
		btnSiguiente = new JButton(">");
		btnSiguiente.setBounds(732, 274, 41, 23);
		add(btnSiguiente);

		btnAnterior = new JButton("<");
		btnAnterior.setBounds(10, 274, 41, 23);
		add(btnAnterior);
		btnAnterior.setEnabled(false);

		btnSalir = new JButton("");
		btnSalir.setBounds(10, 11, 31, 31);
		// Cargamos el icono de salida en el botón, y redimensionamos la imagen para que quepa dentro
		btnSalir.setIcon(new ImageIcon(new ImageIcon(VistaPrincipal.class.getResource("/multimedia/IconoSalir.png"))
				.getImage().getScaledInstance(btnSalir.getWidth() - 7, btnSalir.getHeight() - 6, Image.SCALE_DEFAULT)));
		add(btnSalir);
		
		// Carga el ArrayList de salas, para poder mostrarlo en el primer boton de sesion
		gestion.cargarNombreSalaDeSesion(peliculas.get(0).getCodPelicula(), sesiones.get(0).getCodSala());
		salas  = gestion.devolverArraylistSalas();
		btnSesion1 = new JButton("<html>" + sesiones.get(0).getHora().replaceAll("\\n", "<br>") + " " + salas.get(0).getNomSala().replaceAll("\\n", "<br>") +  "</html>");
		btnSesion1.setBounds(365, 350, 75, 50);
		add(btnSesion1);
		
		// Carga el ArrayList de salas, para poder mostrarlo en el segundo boton de sesion
		gestion.cargarNombreSalaDeSesion(peliculas.get(1).getCodPelicula(), sesiones.get(1).getCodSala());
		salas  = gestion.devolverArraylistSalas();
		btnSesion2 = new JButton("<html>" + sesiones.get(1).getHora().replaceAll("\\n", "<br>") + " " + salas.get(0).getNomSala().replaceAll("\\n", "<br>") +  "</html>");
		btnSesion2.setBounds(490, 350, 77, 49);
		add(btnSesion2);
		
		btnMenosEntradas = new JButton("-");
		btnMenosEntradas.setVisible(false);
		add(btnMenosEntradas);
		
		btnMasEntradas = new JButton("+");
		btnMasEntradas.setVisible(false);
		add(btnMasEntradas);
		
		btnAñadirEntrada = new JButton("Añadir");
		btnAñadirEntrada.setVisible(false);
		btnAñadirEntrada.setFont(new Font("Arial", Font.PLAIN, 10));
		add(btnAñadirEntrada);
		
		btnCarrito = new JButton("Carrito");
		btnCarrito.setBounds(686, 11, 89, 23);
		btnCarrito.setBackground(UIManager.getColor("Button.background"));
		add(btnCarrito);
		
		btnCines = new JButton("Cines");
		btnCines.setBounds(25, 516, 89, 23);
		add(btnCines);
		
		// ACCIONES DE BOTONES
		
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

		btnSesion1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Revela y coloca los el label de cantidad de entradas, y los botones de menos, más y añadir entradas debajo de si mismo
				btnMenosEntradas.setBounds(btnSesion1.getX() - 20, btnSesion1.getY() + 60, 41, 20);
				btnMasEntradas.setBounds(btnMenosEntradas.getX() + 75, btnMenosEntradas.getY(), 41, 20);
				btnAñadirEntrada.setBounds(btnSesion1.getX(), btnMenosEntradas.getY() + 30,
						btnSesion1.getWidth(), 20);
				lblcantidadEntradas.setBounds(btnMenosEntradas.getX() + 40, btnMenosEntradas.getY(), 36, 20);
				botonSesion1Seleccionado = true; 
				// Esto evita que cada vez que pulses el boton alterne entre visible en invisible
				if (btnMenosEntradas.isVisible() || btnMasEntradas.isVisible() || btnAñadirEntrada.isVisible()
						|| lblcantidadEntradas.isVisible()) {
					desactivarBotones(); // Llama al metodo que vuelve invisible los botones
				} else {
					btnMenosEntradas.setVisible(true);
					btnMasEntradas.setVisible(true);
					btnAñadirEntrada.setVisible(true);
					lblcantidadEntradas.setVisible(true);
				}

			}
		});
		
		btnSesion2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Revela y coloca los el label de cantidad de entradas, y los botones de menos, más y añadir entradas debajo de si mismo
				btnMenosEntradas.setBounds(btnSesion2.getX() - 20, btnSesion2.getY() + 60, 41, 20);
				btnMasEntradas.setBounds(btnMenosEntradas.getX() + 75, btnMenosEntradas.getY(), 41, 20);
				btnAñadirEntrada.setBounds(btnSesion2.getX(), btnMenosEntradas.getY() + 30,
						btnSesion1.getWidth(), 20);
				lblcantidadEntradas.setBounds(btnMenosEntradas.getX() + 40, btnMenosEntradas.getY(), 36, 20);
				botonSesion1Seleccionado = false;
				// Esto evita que cada vez que pulses el boton alterne entre visible en invisible
				if (btnMenosEntradas.isVisible() || btnMasEntradas.isVisible() || btnAñadirEntrada.isVisible()
						|| lblcantidadEntradas.isVisible()) {
					desactivarBotones(); // Llama al metodo que vuelve invisible los botones
				} else {
					btnMenosEntradas.setVisible(true);
					btnMasEntradas.setVisible(true);
					btnAñadirEntrada.setVisible(true);
					lblcantidadEntradas.setVisible(true);

				}
			}
		});

		btnMenosEntradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Coje la variable cantidad, y baja 1 unidad, nunca por debajo de 0, y lo muestra en el label de cantidad de entradas
				if (cantidad > 0) { 
					cantidad--;
					lblcantidadEntradas.setText(Integer.toString(cantidad));
				}

			}
		});

		btnMasEntradas.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				// Coje la variable cantidad, y le suma 1 unidad, sin límite, y lo muestra en el label de cantidad de entradas
				cantidad++;
				lblcantidadEntradas.setText(Integer.toString(cantidad));

			}
		});

		btnAñadirEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Al darle al boton cogerá la hora de la sesion escogida, y agregará una nueva entrada al Araylist de entradas
				String horaEscogida = "";
				String codSesion = "";
				String precioPelicula = "";
				String salaSesion = "";
				if (botonSesion1Seleccionado == true) {
					horaEscogida = sesiones.get(0).getHora();
					codSesion = sesiones.get(0).getCodSesion();
					precioPelicula = sesiones.get(0).getPrecio();
					gestion.cargarNombreSalaDeSesion(peliculas.get(0).getCodPelicula(), sesiones.get(0).getCodSala());
					salas  = gestion.devolverArraylistSalas();
					salaSesion = salas.get(0).getNomSala();
					
				} else {
					horaEscogida = sesiones.get(1).getHora();
					codSesion = sesiones.get(1).getCodSesion(); 
					precioPelicula = sesiones.get(1).getPrecio();
					gestion.cargarNombreSalaDeSesion(peliculas.get(1).getCodPelicula(), sesiones.get(1).getCodSala());
					salas  = gestion.devolverArraylistSalas();
					salaSesion = salas.get(0).getNomSala();
				}
				crearCodigoEntrada(); // Llama al método para generar un nuevo código de entrada
				// Agrega la entrada al ArrayList
				entradas.add(new EntradaCompleta(gestion.cineSeleccionado,
						peliculas.get(peliculaActual).getNomPelicula(),codSesion, salaSesion,gestion.fechaSeleccionada, horaEscogida,
						codEntrada, Integer.parseInt(lblcantidadEntradas.getText()), precioPelicula));
				JOptionPane.showMessageDialog(null, "Se ha añadido la sesión de la película seleccionada", "AÑADIDO AL CARRITO", JOptionPane.INFORMATION_MESSAGE);
			}

		});
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pasa a la siguiente pelicula, y por lo tanto cambia los campos
				if (peliculaActual < peliculas.size() - 1) {
					peliculaActual++; // variable para elegir la pelicula del ArrayList, para recorrerlo
					cambiarCampos(gestion); // Cambia los valores de los campos
					cambiarImagen(); // Cambia la imagen de la película
					btnAnterior.setEnabled(true); // Habilita el botón para ir a la película anterior
					
				} else { 
					btnSiguiente.setEnabled(false);
					btnAnterior.setEnabled(true);
					cambiarCampos(gestion);
				}
				// En caso de que haya llegado a la última película, el boton se deshabilitará
				if(peliculaActual == peliculas.size() - 1) {
					btnSiguiente.setEnabled(false);
				}
				// Se harán invisibles los botones de cantidades
				desactivarBotones();
			}
		});
		
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pasa a la pelicula anterior, y por lo tanto cambia los campos
				if (peliculaActual > 0 && peliculaActual <= peliculas.size() - 1) {
					peliculaActual--; // variable para elegir la pelicula del ArrayList, para recorrerlo
					cambiarCampos(gestion); // Cambia los valores de los campos
					cambiarImagen(); // Cambia la imagen de la película
					btnSiguiente.setEnabled(true); // Habilita el botón para ir a la siguiente película

				} else {
					btnSiguiente.setEnabled(true);
					btnAnterior.setEnabled(false);
					cambiarCampos(gestion);
				}
				// Si llega a la primera película del ArrayList el boton se deshabilita
				if(peliculaActual == 0) { 
					btnAnterior.setEnabled(false);
				}
				// Se harán invisibles los botones de cantidades
				desactivarBotones();

			}
		});
		

		btnCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Le enviará al Panel del carrito
				gestion.cargarEntradas(entradas); // Pasará el ArrayList de entradas a la clase de GestioniB para no perder el contenido
				vp.cambiarPanel(5); // Cambia al Panel de Carrito
			}

		});

		btnCines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gestion.cargarEntradas(entradas);  // Pasará el ArrayList de entradas a la clase de GestioniB para no perder el contenido
				vp.cambiarPanel(3); // Cambia al Panel de Cines
			}
		});

	}

	// MÉTODOS
	// Creará un código completamente aleatorio entre 10 millones y 99.999.999
	private void crearCodigoEntrada() {
		int min = 10000000;
		int max = 99999999;

		Integer randomNum = (int) (Math.random() * (max - min + 1)) + min; // Operación para crear el número aleatorio, estableciendo un mínimo y un máximo
		codEntrada = "#" + randomNum.toString(); // Lo guardará en la variable codEntrada para agregarla posteriormente al Arraylist
	}

	// Cambia la imagen del labelimagenPeli
	public void cambiarImagen() {
		rutaIcono = "/multimedia/" + (peliculas.get(peliculaActual).getNomPelicula() + ".jpg");
		lblImagenPeli.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
				.getScaledInstance(lblImagenPeli.getWidth(), lblImagenPeli.getHeight(), Image.SCALE_DEFAULT)));
		lblImagenPeli.repaint();
	}

	// cambia los valores de los campos, para mostrar la pelicula a continuacion
	public void cambiarCampos(GestionBD gestion) {
		sesiones.clear(); // Limpia el ArrayList de sesiones para evitar acumulacion
		gestion.cargarSesionesDePeliculas(peliculas.get(peliculaActual).getCodPelicula());
		sesiones = gestion.devolverArrayListSesiones();

		lblNombre.setText("Nombre: " + peliculas.get(peliculaActual).getNomPelicula());
		lblDuracion.setText("Duración: " + String.valueOf(peliculas.get(peliculaActual).getDuracion()));
		lblGenero.setText("Género: " + peliculas.get(peliculaActual).getGenero());
		lblSinopsis.setText(
				"<html>Sinopsis: " + peliculas.get(peliculaActual).getDcp().replaceAll("\\n", "<br>") + "</html>"); // HTML se utiliza para que el texto se adapte al tamaño del label

		// Carga las Salas en las que se proyecta la sesion 1
		gestion.cargarNombreSalaDeSesion(peliculas.get(0).getCodPelicula(), sesiones.get(0).getCodSala());
		salas  = gestion.devolverArraylistSalas();
		
		btnSesion1.setText("<html>" + sesiones.get(0).getHora().replaceAll("\\n", "<br>") + " " + salas.get(0).getNomSala().replaceAll("\\n", "<br>") +  "</html>");
	
		// Carga las Salas en las que se proyecta la sesion 2
		gestion.cargarNombreSalaDeSesion(peliculas.get(1).getCodPelicula(), sesiones.get(1).getCodSala());
		salas  = gestion.devolverArraylistSalas();
		
		btnSesion2.setText("<html>" + sesiones.get(1).getHora().replaceAll("\\n", "<br>") + " " + salas.get(0).getNomSala().replaceAll("\\n", "<br>") +  "</html>");
	}

	// Vuelve invisibles los botones a relacionados con la cantidad de entradas
	private void desactivarBotones() {
		btnMenosEntradas.setVisible(false);
		btnMasEntradas.setVisible(false);
		btnAñadirEntrada.setVisible(false);
		lblcantidadEntradas.setVisible(false);
		// Resetea el texto del Label de Cantidad a 0
		cantidad = 0;
		lblcantidadEntradas.setText(Integer.toString(cantidad));
	}
	
}
