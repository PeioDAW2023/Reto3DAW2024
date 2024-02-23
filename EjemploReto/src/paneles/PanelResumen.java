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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import controlador.GestionBD;
import controlador.GestionImagenes;
import modelo.Entrada;
import modelo.EntradaCompleta;
import modelo.Pelicula;
import vista.VistaPrincipal;

public class PanelResumen extends JPanel {
	private static final long serialVersionUID = 1L;

	// Declaracion de campos, variables y ArrayLists

	private JLabel lblTituloCompras;
	private JLabel lblImagenPeli1;
	private JLabel lblImagenPeli2;
	private JLabel lblImagenPeli3;
	private JLabel lblContainerEntrada1;
	private JLabel lblContainerEntrada2;
	private JLabel lblContainerEntrada3;
	private JLabel lblNombrePelicula1;
	private JLabel lblNombrePelicula2;
	private JLabel lblNombrePelicula3;
	private JLabel lblCine1;
	private JLabel lblCine2;
	private JLabel lblCine3;
	private JLabel lblFecha1;
	private JLabel lblFecha2;
	private JLabel lblFecha3;
	private JLabel lblHora1;
	private JLabel lblHora2;
	private JLabel lblHora3;
	private JLabel lblCantidad1;
	private JLabel lblCantidad2;
	private JLabel lblCantidad3;
	private JLabel lblCodigoEntrada1;
	private JLabel lblCodigoEntrada2;
	private JLabel lblCodigoEntrada3;
	private JLabel lblPrecioPelicula1;
	private JLabel lblPrecioPelicula2;
	private JLabel lblPrecioPelicula3;
	private JButton btnSubir;
	private JButton btnCines;
	private JButton btnSiguiente;
	private JButton btnBajar;

	private int posicionY = 90;
	private int contador = 0;
	private int tamañoArrayList = 0;
	private int buscadorPelicula = 0;

	public ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	public ArrayList<EntradaCompleta> entradas = new ArrayList<EntradaCompleta>();
	public ArrayList<Entrada> entradaBBDD = new ArrayList<Entrada>();

	// Constructor del panel
	public PanelResumen() {

	}

	public PanelResumen(VistaPrincipal vp, GestionImagenes img, GestionBD gestion) {

		// Adapta el panel al frame
		setSize(vp.getSize());
		setLayout(null);

		// Carga las entradas guardadas previamente, carga las variables necesarias y
		// carga los campos iniciales
		entradas = gestion.devolverArraylistEntradas();
		tamañoArrayList = entradas.size();
		crearCamposIniciales();
		condicionesMostrarCamposIniciales();
		buscadorPelicula = 0;

		// AGREGACION DE CAMPOS

		// Labels
		lblTituloCompras = new JLabel("Registro de Compras");
		lblTituloCompras.setBounds(10, 5, 765, 75);
		lblTituloCompras.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloCompras.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 35));
		add(lblTituloCompras);

		// Botones
		btnBajar = new JButton("Bajar");
		btnBajar.setBackground(UIManager.getColor("Button.background"));
		btnBajar.setBounds(292, 515, 80, 25);
		add(btnBajar);

		btnSubir = new JButton("Subir");
		btnSubir.setBackground(UIManager.getColor("Button.background"));
		btnSubir.setBounds(416, 515, 80, 25);
		add(btnSubir);

		btnSiguiente = new JButton("Terminar");
		btnSiguiente.setBounds(665, 515, 95, 25);
		btnSiguiente.setBackground(UIManager.getColor("Button.background"));
		add(btnSiguiente);

		btnCines = new JButton("Cines");
		btnCines.setBounds(10, 11, 89, 23);
		btnCines.setBackground(UIManager.getColor("Button.background"));
		add(btnCines);

		// ACCIONES DE BOTONES

		btnBajar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tamañoArrayList = tamañoArrayList - contador;
				condicionBotonesSubirYBajar(); // Llama al método para ver si los botones cumplen unas características o
												// no
				condicionesMostrarCamposBajar(); // Llama al método que hará que pase algo al darle al boton

			}
		});

		btnSubir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cambia algunos valores para poder recorrer los ArrayLists y otros eventos
				if (entradas.size() == tamañoArrayList) {
					contador = 3;
					buscadorPelicula = 0;
				}
				// Ejecuta ciertos métodos para poder mostrar algunos campos u otros
				condicionBotonesSubirYBajar();
				condicionesMostrarCamposSubir();
				condicionBotonesSubirYBajar();
			}
		});

		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (entradas.size() == 0) {
					// En caso de no haber entradas, se enviará un mensaje
					JOptionPane.showMessageDialog(null, "No se ha guardado nada ya que el carrito está vacío",
							"SALIENDO", JOptionPane.INFORMATION_MESSAGE);
					vp.cambiarPanel(1);

				} else {

					double precioTotalEntrada = 0.0;
					int cantidadEntradasTotales = 0;
					double precioTotal = 0.0;
					Integer codCompra = crearCodigoCompra();
					for (int i = 0; i < entradas.size(); i++) {

						cantidadEntradasTotales += entradas.get(i).getCantidadEntradas(); // Suma las entradas totales
						precioTotalEntrada = entradas.get(i).getCantidadEntradas()
								* Double.parseDouble(entradas.get(i).getPrecioPelicula()); // El precio total de cada
																							// entrada
						precioTotal += precioTotalEntrada; // El precio total global, sumando todas las entradas
						gestion.agregarEntrada(entradas.get(i).getCodEntrada(), codCompra,
								entradas.get(i).getCodSesion(), entradas.get(i).getCantidadEntradas()); // Llama al
																										// método para
																										// agregar una
																										// nueva entrada
																										// a la abse de
																										// datos
					}
					int numeroDePeliculasTotales = gestion.queryNumeroTotalDePeliculas(codCompra); // Llama al método
																									// para ver cuantas
																									// peliculas
																									// distintas hay en
																									// las entradas
					double precioFinal = 0.0;
					int descuento = 0;

					if (numeroDePeliculasTotales <= 4) {
						descuento = numeroDePeliculasTotales * 10; // Calcula el porcentaje de descuento

					} else {
						descuento = 40; // Calcula el porcentaje de descuento
					}
					precioFinal = precioTotal - ((precioTotal * descuento) / 100); // Calcula el precio final
					gestion.agregarCompra(codCompra, gestion.DNIGuardado, precioTotal, cantidadEntradasTotales,
							descuento, precioFinal); // Llama al método para agregar nueva compra a la base de datos
					gestion.imprimirFactura(gestion, entradas, precioTotal, descuento, precioFinal, codCompra); // Te
																												// imprime
																												// la
																												// factura
																												// y te
																												// envía
																												// al
																												// Login
																												// reseteando
																												// todo
					vp.cambiarPanel(1);
				}
			}
		});

		btnCines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestion.cargarEntradas(entradas); // Envía las entradas para que no se pierdan
				vp.cambiarPanel(3); // Cambia al Panel de Cines
			}

		});

		// Condiciones para los botones iniciales
		if (tamañoArrayList <= 2 || tamañoArrayList == contador) {
			btnBajar.setEnabled(false);
			btnSubir.setEnabled(false);
		} else if (tamañoArrayList == entradas.size()) {
			btnBajar.setEnabled(true);
			btnSubir.setEnabled(false);
		}
	}

	// MÉTODOS

	// Metodo para generar un único código de compra, para registrarlo después en la
	// base de datos
	private int crearCodigoCompra() {
		int min = 100000000;
		int max = 999999999;
		int codigoNuevo = 0;

		Integer randomNum = (int) (Math.random() * (max - min + 1)) + min; // Operación para crear el número aleatorio,
																			// estableciendo un mínimo y un máximo
		codigoNuevo = randomNum; // Lo guardará en la variable codigoNuevo para agregarla posteriormente a la
									// variable codCompra
		return codigoNuevo;
	}

	// Se encarga de ver si se cumplen unos requisitos para mostrar un boton u otro
	private void condicionBotonesSubirYBajar() {
		if (tamañoArrayList <= 2 || tamañoArrayList == contador) {
			btnBajar.setEnabled(false);
			btnSubir.setEnabled(true);
		} else if (tamañoArrayList == entradas.size()) {
			btnBajar.setEnabled(true);
			btnSubir.setEnabled(false);
		}

	}

	// Condiciones a cumplir para mostrar y rellenar unos campos u otros
	private void condicionesMostrarCamposSubir() {

		if (entradas.size() > tamañoArrayList && tamañoArrayList > contador) {

			tamañoArrayList += contador;
			buscadorPelicula -= 3;

			// Se hace visible la primera entrada
			lblContainerEntrada1.setVisible(true);
			lblCodigoEntrada1.setVisible(true);
			lblNombrePelicula1.setVisible(true);
			lblCine1.setVisible(true);
			lblFecha1.setVisible(true);
			lblHora1.setVisible(true);
			lblCantidad1.setVisible(true);
			lblImagenPeli1.setVisible(true);
			lblPrecioPelicula1.setVisible(true);

			// Se modifican los campos de la primera entrada
			lblNombrePelicula1.setText("Nombre: " + entradas.get(buscadorPelicula).getNomPelicula());
			lblCodigoEntrada1.setText(entradas.get(buscadorPelicula).getCodEntrada());
			lblCine1.setText("Cine: " + entradas.get(buscadorPelicula).getCine());
			lblFecha1.setText("Fecha: " + entradas.get(buscadorPelicula).getFecha());
			lblHora1.setText("Hora: " + entradas.get(buscadorPelicula).getHora());
			lblCantidad1.setText("Cantidad: " + entradas.get(buscadorPelicula).getCantidadEntradas().toString());
			String rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula).getNomPelicula() + ".jpg";
			lblImagenPeli1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula1.setText("Precio: " + entradas.get(buscadorPelicula).getPrecioPelicula() + "€/U");

			// Se hace visible la segunda entrada
			lblContainerEntrada2.setVisible(true);
			lblCodigoEntrada2.setVisible(true);
			lblNombrePelicula2.setVisible(true);
			lblCine2.setVisible(true);
			lblFecha2.setVisible(true);
			lblHora2.setVisible(true);
			lblCantidad2.setVisible(true);
			lblImagenPeli2.setVisible(true);
			lblPrecioPelicula2.setVisible(true);

			// Se modifican los campos de la segunda entrada
			lblNombrePelicula2.setText("Nombre: " + entradas.get(buscadorPelicula + 1).getNomPelicula());
			lblCodigoEntrada2.setText(entradas.get(buscadorPelicula + 1).getCodEntrada());
			lblCine2.setText("Cine: " + entradas.get(buscadorPelicula + 1).getCine());
			lblFecha2.setText("Fecha: " + entradas.get(buscadorPelicula + 1).getFecha());
			lblHora2.setText("Hora: " + entradas.get(buscadorPelicula + 1).getHora());
			lblCantidad2.setText("Cantidad: " + entradas.get(buscadorPelicula + 1).getCantidadEntradas().toString());
			rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula + 1).getNomPelicula() + ".jpg";
			lblImagenPeli2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula2.setText("Precio: " + entradas.get(buscadorPelicula + 1).getPrecioPelicula() + "€/U");

			// Se hace visible la primera entrada
			lblContainerEntrada3.setVisible(true);
			lblCodigoEntrada3.setVisible(true);
			lblNombrePelicula3.setVisible(true);
			lblCine3.setVisible(true);
			lblFecha3.setVisible(true);
			lblHora3.setVisible(true);
			lblCantidad3.setVisible(true);
			lblImagenPeli3.setVisible(true);
			lblPrecioPelicula3.setVisible(true);

			// Se modifican los campos de la primera entrada
			lblNombrePelicula3.setText("Nombre: " + entradas.get(buscadorPelicula + 2).getNomPelicula());
			lblCodigoEntrada3.setText(entradas.get(buscadorPelicula + 2).getCodEntrada());
			lblCine3.setText("Cine: " + entradas.get(buscadorPelicula + 2).getCine());
			lblFecha3.setText("Fecha: " + entradas.get(buscadorPelicula + 2).getFecha());
			lblHora3.setText("Hora: " + entradas.get(buscadorPelicula + 2).getHora());
			lblCantidad3.setText("Cantidad: " + entradas.get(buscadorPelicula + 2).getCantidadEntradas().toString());
			rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula + 2).getNomPelicula() + ".jpg";
			lblImagenPeli3.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula3.setText("Precio: " + entradas.get(buscadorPelicula + 2).getPrecioPelicula() + "€/U");

		} else if (tamañoArrayList == contador) {

			contador = 3;
			tamañoArrayList += contador;
			buscadorPelicula -= 3;

			// Se hace visible la primera entrada
			lblContainerEntrada1.setVisible(true);
			lblCodigoEntrada1.setVisible(true);
			lblNombrePelicula1.setVisible(true);
			lblCine1.setVisible(true);
			lblFecha1.setVisible(true);
			lblHora1.setVisible(true);
			lblCantidad1.setVisible(true);
			lblImagenPeli1.setVisible(true);
			lblPrecioPelicula1.setVisible(true);

			// Se modifican los campos de la primera entrada
			lblNombrePelicula1.setText("Nombre: " + entradas.get(buscadorPelicula).getNomPelicula());
			lblCodigoEntrada1.setText(entradas.get(buscadorPelicula).getCodEntrada());
			lblCine1.setText("Cine: " + entradas.get(buscadorPelicula).getCine());
			lblFecha1.setText("Fecha: " + entradas.get(buscadorPelicula).getFecha());
			lblHora1.setText("Hora: " + entradas.get(buscadorPelicula).getHora());
			lblCantidad1.setText("Cantidad: " + entradas.get(buscadorPelicula).getCantidadEntradas().toString());
			String rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula).getNomPelicula() + ".jpg";
			lblImagenPeli1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula1.setText("Precio: " + entradas.get(buscadorPelicula).getPrecioPelicula() + "€/U");

			// Se hace visible la segunda entrada
			lblContainerEntrada2.setVisible(true);
			lblCodigoEntrada2.setVisible(true);
			lblNombrePelicula2.setVisible(true);
			lblCine2.setVisible(true);
			lblFecha2.setVisible(true);
			lblHora2.setVisible(true);
			lblCantidad2.setVisible(true);
			lblImagenPeli2.setVisible(true);
			lblPrecioPelicula2.setVisible(true);

			// Se modifican los campos de la segunda entrada
			lblNombrePelicula2.setText("Nombre: " + entradas.get(buscadorPelicula + 1).getNomPelicula());
			lblCodigoEntrada2.setText(entradas.get(buscadorPelicula + 1).getCodEntrada());
			lblCine2.setText("Cine: " + entradas.get(buscadorPelicula + 1).getCine());
			lblFecha2.setText("Fecha: " + entradas.get(buscadorPelicula + 1).getFecha());
			lblHora2.setText("Hora: " + entradas.get(buscadorPelicula + 1).getHora());
			lblCantidad2.setText("Cantidad: " + entradas.get(buscadorPelicula + 1).getCantidadEntradas().toString());
			rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula + 1).getNomPelicula() + ".jpg";
			lblImagenPeli2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula2.setText("Precio: " + entradas.get(buscadorPelicula + 1).getPrecioPelicula() + "€/U");

			// Se hace visible la tercera entrada
			lblContainerEntrada3.setVisible(true);
			lblCodigoEntrada3.setVisible(true);
			lblNombrePelicula3.setVisible(true);
			lblCine3.setVisible(true);
			lblFecha3.setVisible(true);
			lblHora3.setVisible(true);
			lblCantidad3.setVisible(true);
			lblImagenPeli3.setVisible(true);
			lblPrecioPelicula3.setVisible(true);

			// Se modifican los campos de la tercera entrada
			lblNombrePelicula3.setText("Nombre: " + entradas.get(buscadorPelicula + 2).getNomPelicula());
			lblCodigoEntrada3.setText(entradas.get(buscadorPelicula + 2).getCodEntrada());
			lblCine3.setText("Cine: " + entradas.get(buscadorPelicula + 2).getCine());
			lblFecha3.setText("Fecha: " + entradas.get(buscadorPelicula + 2).getFecha());
			lblHora3.setText("Hora: " + entradas.get(buscadorPelicula + 2).getHora());
			lblCantidad3.setText("Cantidad: " + entradas.get(buscadorPelicula + 2).getCantidadEntradas().toString());
			rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula + 2).getNomPelicula() + ".jpg";
			lblImagenPeli3.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula3.setText("Precio: " + entradas.get(buscadorPelicula + 2).getPrecioPelicula() + "€/U");

		}

	}

	// Muestra los campos inciales (Nada más entrar en el panel), dependiendo de
	// ciertos requisitos
	private void condicionesMostrarCamposIniciales() {

		if (tamañoArrayList >= 3) {

			contador = 3;

			// Se hace visible la primera entrada
			lblContainerEntrada1.setVisible(true);
			lblCodigoEntrada1.setVisible(true);
			lblNombrePelicula1.setVisible(true);
			lblCine1.setVisible(true);
			lblFecha1.setVisible(true);
			lblHora1.setVisible(true);
			lblCantidad1.setVisible(true);
			lblImagenPeli1.setVisible(true);
			lblPrecioPelicula1.setVisible(true);

			// Se modifican los campos de la primera entrada
			lblNombrePelicula1.setText("Nombre: " + entradas.get(buscadorPelicula).getNomPelicula());
			lblCodigoEntrada1.setText(entradas.get(buscadorPelicula).getCodEntrada());
			lblCine1.setText("Cine: " + entradas.get(buscadorPelicula).getCine());
			lblFecha1.setText("Fecha: " + entradas.get(buscadorPelicula).getFecha());
			lblHora1.setText("Hora: " + entradas.get(buscadorPelicula).getHora());
			lblCantidad1.setText("Cantidad: " + entradas.get(buscadorPelicula).getCantidadEntradas().toString());
			String rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula).getNomPelicula() + ".jpg";
			lblImagenPeli1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula1.setText("Precio: " + entradas.get(buscadorPelicula).getPrecioPelicula() + "€/U");

			// Se hace visible la segunda entrada
			lblContainerEntrada2.setVisible(true);
			lblCodigoEntrada2.setVisible(true);
			lblNombrePelicula2.setVisible(true);
			lblCine2.setVisible(true);
			lblFecha2.setVisible(true);
			lblHora2.setVisible(true);
			lblCantidad2.setVisible(true);
			lblImagenPeli2.setVisible(true);
			lblPrecioPelicula2.setVisible(true);

			// Se modifican los campos de la segunda entrada
			lblNombrePelicula2.setText("Nombre: " + entradas.get(buscadorPelicula + 1).getNomPelicula());
			lblCodigoEntrada2.setText(entradas.get(buscadorPelicula + 1).getCodEntrada());
			lblCine2.setText("Cine: " + entradas.get(buscadorPelicula + 1).getCine());
			lblFecha2.setText("Fecha: " + entradas.get(buscadorPelicula + 1).getFecha());
			lblHora2.setText("Hora: " + entradas.get(buscadorPelicula + 1).getHora());
			lblCantidad2.setText("Cantidad: " + entradas.get(buscadorPelicula + 1).getCantidadEntradas().toString());
			rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula + 1).getNomPelicula() + ".jpg";
			lblImagenPeli2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula2.setText("Precio: " + entradas.get(buscadorPelicula + 1).getPrecioPelicula() + "€/U");

			// Se hace visible la tercera entrada
			lblContainerEntrada3.setVisible(true);
			lblCodigoEntrada3.setVisible(true);
			lblNombrePelicula3.setVisible(true);
			lblCine3.setVisible(true);
			lblFecha3.setVisible(true);
			lblHora3.setVisible(true);
			lblCantidad3.setVisible(true);
			lblImagenPeli3.setVisible(true);
			lblPrecioPelicula3.setVisible(true);

			// Se modifican los campos de la tercera entrada
			lblNombrePelicula3.setText("Nombre: " + entradas.get(buscadorPelicula + 2).getNomPelicula());
			lblCodigoEntrada3.setText(entradas.get(buscadorPelicula + 2).getCodEntrada());
			lblCine3.setText("Cine: " + entradas.get(buscadorPelicula + 2).getCine());
			lblFecha3.setText("Fecha: " + entradas.get(buscadorPelicula + 2).getFecha());
			lblHora3.setText("Hora: " + entradas.get(buscadorPelicula + 2).getHora());
			lblCantidad3.setText("Cantidad: " + entradas.get(buscadorPelicula + 2).getCantidadEntradas().toString());
			rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula + 2).getNomPelicula() + ".jpg";
			lblImagenPeli3.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula3.setText("Precio: " + entradas.get(buscadorPelicula + 2).getPrecioPelicula() + "€/U");

		} else if (tamañoArrayList == 2) {

			contador = tamañoArrayList;

			// Se hace visible la primera entrada
			lblContainerEntrada1.setVisible(true);
			lblCodigoEntrada1.setVisible(true);
			lblNombrePelicula1.setVisible(true);
			lblCine1.setVisible(true);
			lblFecha1.setVisible(true);
			lblHora1.setVisible(true);
			lblCantidad1.setVisible(true);
			lblImagenPeli1.setVisible(true);
			lblPrecioPelicula1.setVisible(true);

			// Se modifican los campos de la primera entrada
			lblNombrePelicula1.setText("Nombre: " + entradas.get(buscadorPelicula).getNomPelicula());
			lblCodigoEntrada1.setText(entradas.get(buscadorPelicula).getCodEntrada());
			lblCine1.setText("Cine: " + entradas.get(buscadorPelicula).getCine());
			lblFecha1.setText("Fecha: " + entradas.get(buscadorPelicula).getFecha());
			lblHora1.setText("Hora: " + entradas.get(buscadorPelicula).getHora());
			lblCantidad1.setText("Cantidad: " + entradas.get(buscadorPelicula).getCantidadEntradas().toString());
			String rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula).getNomPelicula() + ".jpg";
			lblImagenPeli1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula1.setText("Precio: " + entradas.get(buscadorPelicula).getPrecioPelicula() + "€/U");

			// Se hace visible la segunda entrada
			lblContainerEntrada2.setVisible(true);
			lblCodigoEntrada2.setVisible(true);
			lblNombrePelicula2.setVisible(true);
			lblCine2.setVisible(true);
			lblFecha2.setVisible(true);
			lblHora2.setVisible(true);
			lblCantidad2.setVisible(true);
			lblImagenPeli2.setVisible(true);
			lblPrecioPelicula2.setVisible(true);

			// Se modifican los campos de la segunda entrada
			lblNombrePelicula2.setText("Nombre: " + entradas.get(buscadorPelicula + 1).getNomPelicula());
			lblCodigoEntrada2.setText(entradas.get(buscadorPelicula + 1).getCodEntrada());
			lblCine2.setText("Cine: " + entradas.get(buscadorPelicula + 1).getCine());
			lblFecha2.setText("Fecha: " + entradas.get(buscadorPelicula + 1).getFecha());
			lblHora2.setText("Hora: " + entradas.get(buscadorPelicula + 1).getHora());
			lblCantidad2.setText("Cantidad: " + entradas.get(buscadorPelicula + 1).getCantidadEntradas().toString());
			rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula + 1).getNomPelicula() + ".jpg";
			lblImagenPeli2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula2.setText("Precio: " + entradas.get(buscadorPelicula + 1).getPrecioPelicula() + "€/U");

			// Se hace invisible la tercera entrada, ya que no hace falta
			lblCantidad3.setVisible(false);
			lblCine3.setVisible(false);
			lblCodigoEntrada3.setVisible(false);
			lblFecha3.setVisible(false);
			lblHora3.setVisible(false);
			lblImagenPeli3.setVisible(false);
			lblNombrePelicula3.setVisible(false);
			lblContainerEntrada3.setVisible(false);
			lblPrecioPelicula3.setVisible(false);

		} else if (tamañoArrayList == 1) {

			contador = tamañoArrayList;

			// Se hace visible la primera entrada
			lblContainerEntrada1.setVisible(true);
			lblCodigoEntrada1.setVisible(true);
			lblNombrePelicula1.setVisible(true);
			lblCine1.setVisible(true);
			lblFecha1.setVisible(true);
			lblHora1.setVisible(true);
			lblCantidad1.setVisible(true);
			lblImagenPeli1.setVisible(true);
			lblPrecioPelicula1.setVisible(true);

			// Se modifican los campos de la primera entrada
			lblNombrePelicula1.setText("Nombre: " + entradas.get(buscadorPelicula).getNomPelicula());
			lblCodigoEntrada1.setText(entradas.get(buscadorPelicula).getCodEntrada());
			lblCine1.setText("Cine: " + entradas.get(buscadorPelicula).getCine());
			lblFecha1.setText("Fecha: " + entradas.get(buscadorPelicula).getFecha());
			lblHora1.setText("Hora: " + entradas.get(buscadorPelicula).getHora());
			lblCantidad1.setText("Cantidad: " + entradas.get(buscadorPelicula).getCantidadEntradas().toString());
			String rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula).getNomPelicula() + ".jpg";
			lblImagenPeli1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula1.setText("Precio: " + entradas.get(buscadorPelicula).getPrecioPelicula() + "€/U");

			// Se hace invisible la segunda entrada, ya que no hace falta
			lblCantidad2.setVisible(false);
			lblCine2.setVisible(false);
			lblCodigoEntrada2.setVisible(false);
			lblFecha2.setVisible(false);
			lblHora2.setVisible(false);
			lblImagenPeli2.setVisible(false);
			lblNombrePelicula2.setVisible(false);
			lblContainerEntrada2.setVisible(false);
			lblPrecioPelicula2.setVisible(false);

			// Se hace invisible la tercera entrada, ya que no hace falta
			lblCantidad3.setVisible(false);
			lblCine3.setVisible(false);
			lblCodigoEntrada3.setVisible(false);
			lblFecha3.setVisible(false);
			lblHora3.setVisible(false);
			lblImagenPeli3.setVisible(false);
			lblNombrePelicula3.setVisible(false);
			lblContainerEntrada3.setVisible(false);
			lblPrecioPelicula3.setVisible(false);

		} else {

			contador = 0;

			// Se hace invisible la primera entrada, ya que no hace falta
			lblCantidad1.setVisible(false);
			lblCine1.setVisible(false);
			lblCodigoEntrada1.setVisible(false);
			lblFecha1.setVisible(false);
			lblHora1.setVisible(false);
			lblImagenPeli1.setVisible(false);
			lblNombrePelicula1.setVisible(false);
			lblContainerEntrada1.setVisible(false);
			lblPrecioPelicula1.setVisible(false);

			// Se hace invisible la segunda entrada, ya que no hace falta
			lblCantidad2.setVisible(false);
			lblCine2.setVisible(false);
			lblCodigoEntrada2.setVisible(false);
			lblFecha2.setVisible(false);
			lblHora2.setVisible(false);
			lblImagenPeli2.setVisible(false);
			lblNombrePelicula2.setVisible(false);
			lblContainerEntrada2.setVisible(false);
			lblPrecioPelicula2.setVisible(false);

			// Se hace invisible la tercera entrada, ya que no hace falta
			lblCantidad3.setVisible(false);
			lblCine3.setVisible(false);
			lblCodigoEntrada3.setVisible(false);
			lblFecha3.setVisible(false);
			lblHora3.setVisible(false);
			lblImagenPeli3.setVisible(false);
			lblNombrePelicula3.setVisible(false);
			lblContainerEntrada3.setVisible(false);
			lblPrecioPelicula3.setVisible(false);
		}

	}

	// Condiciones a cumplir
	private void condicionesMostrarCamposBajar() {

		if (tamañoArrayList >= 3) {
			contador = 3;
			buscadorPelicula += 3;

			// Se hace visible la primera entrada
			lblContainerEntrada1.setVisible(true);
			lblCodigoEntrada1.setVisible(true);
			lblNombrePelicula1.setVisible(true);
			lblCine1.setVisible(true);
			lblFecha1.setVisible(true);
			lblHora1.setVisible(true);
			lblCantidad1.setVisible(true);
			lblImagenPeli1.setVisible(true);
			lblPrecioPelicula1.setVisible(true);

			// Se modifican los campos de la primera entrada
			lblNombrePelicula1.setText("Nombre: " + entradas.get(buscadorPelicula).getNomPelicula());
			lblCodigoEntrada1.setText(entradas.get(buscadorPelicula).getCodEntrada());
			lblCine1.setText("Cine: " + entradas.get(buscadorPelicula).getCine());
			lblFecha1.setText("Fecha: " + entradas.get(buscadorPelicula).getFecha());
			lblHora1.setText("Hora: " + entradas.get(buscadorPelicula).getHora());
			lblCantidad1.setText("Cantidad: " + entradas.get(buscadorPelicula).getCantidadEntradas().toString());
			String rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula).getNomPelicula() + ".jpg";
			lblImagenPeli1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula1.setText("Precio: " + entradas.get(buscadorPelicula).getPrecioPelicula() + "€/U");

			// Se hace visible la segunda entrada
			lblContainerEntrada2.setVisible(true);
			lblCodigoEntrada2.setVisible(true);
			lblNombrePelicula2.setVisible(true);
			lblCine2.setVisible(true);
			lblFecha2.setVisible(true);
			lblHora2.setVisible(true);
			lblCantidad2.setVisible(true);
			lblImagenPeli2.setVisible(true);
			lblPrecioPelicula2.setVisible(true);

			// Se modifican los campos de la segunda entrada
			lblNombrePelicula2.setText("Nombre: " + entradas.get(buscadorPelicula + 1).getNomPelicula());
			lblCodigoEntrada2.setText(entradas.get(buscadorPelicula + 1).getCodEntrada());
			lblCine2.setText("Cine: " + entradas.get(buscadorPelicula + 1).getCine());
			lblFecha2.setText("Fecha: " + entradas.get(buscadorPelicula + 1).getFecha());
			lblHora2.setText("Hora: " + entradas.get(buscadorPelicula + 1).getHora());
			lblCantidad2.setText("Cantidad: " + entradas.get(buscadorPelicula + 1).getCantidadEntradas().toString());
			rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula + 1).getNomPelicula() + ".jpg";
			lblImagenPeli2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula2.setText("Precio: " + entradas.get(buscadorPelicula + 1).getPrecioPelicula() + "€/U");

			// Se hace visible la tercera entrada
			lblContainerEntrada3.setVisible(true);
			lblCodigoEntrada3.setVisible(true);
			lblNombrePelicula3.setVisible(true);
			lblCine3.setVisible(true);
			lblFecha3.setVisible(true);
			lblHora3.setVisible(true);
			lblCantidad3.setVisible(true);
			lblImagenPeli3.setVisible(true);
			lblPrecioPelicula3.setVisible(true);

			// Se modifican los campos de la tercera entrada
			lblNombrePelicula3.setText("Nombre: " + entradas.get(buscadorPelicula + 2).getNomPelicula());
			lblCodigoEntrada3.setText(entradas.get(buscadorPelicula + 2).getCodEntrada());
			lblCine3.setText("Cine: " + entradas.get(buscadorPelicula + 2).getCine());
			lblFecha3.setText("Fecha: " + entradas.get(buscadorPelicula + 2).getFecha());
			lblHora3.setText("Hora: " + entradas.get(buscadorPelicula + 2).getHora());
			lblCantidad3.setText("Cantidad: " + entradas.get(buscadorPelicula + 2).getCantidadEntradas().toString());
			rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula + 2).getNomPelicula() + ".jpg";
			lblImagenPeli3.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula3.setText("Precio: " + entradas.get(buscadorPelicula + 2).getPrecioPelicula() + "€/U");

		} else if (tamañoArrayList == 2) {

			contador = tamañoArrayList;
			buscadorPelicula += 3;

			// Se hace visible la primera entrada
			lblContainerEntrada1.setVisible(true);
			lblCodigoEntrada1.setVisible(true);
			lblNombrePelicula1.setVisible(true);
			lblCine1.setVisible(true);
			lblFecha1.setVisible(true);
			lblHora1.setVisible(true);
			lblCantidad1.setVisible(true);
			lblImagenPeli1.setVisible(true);
			lblPrecioPelicula1.setVisible(true);

			// Se modifican los campos de la primera entrada
			lblNombrePelicula1.setText("Nombre: " + entradas.get(buscadorPelicula).getNomPelicula());
			lblCodigoEntrada1.setText(entradas.get(buscadorPelicula).getCodEntrada());
			lblCine1.setText("Cine: " + entradas.get(buscadorPelicula).getCine());
			lblFecha1.setText("Fecha: " + entradas.get(buscadorPelicula).getFecha());
			lblHora1.setText("Hora: " + entradas.get(buscadorPelicula).getHora());
			lblCantidad1.setText("Cantidad: " + entradas.get(buscadorPelicula).getCantidadEntradas().toString());
			String rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula).getNomPelicula() + ".jpg";
			lblImagenPeli1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula1.setText("Precio: " + entradas.get(buscadorPelicula).getPrecioPelicula() + "€/U");

			// Se hace visible la segunda entrada
			lblContainerEntrada2.setVisible(true);
			lblCodigoEntrada2.setVisible(true);
			lblNombrePelicula2.setVisible(true);
			lblCine2.setVisible(true);
			lblFecha2.setVisible(true);
			lblHora2.setVisible(true);
			lblCantidad2.setVisible(true);
			lblImagenPeli2.setVisible(true);
			lblPrecioPelicula2.setVisible(true);

			// Se modifican los campos de la segunda entrada
			lblNombrePelicula2.setText("Nombre: " + entradas.get(buscadorPelicula + 1).getNomPelicula());
			lblCodigoEntrada2.setText(entradas.get(buscadorPelicula + 1).getCodEntrada());
			lblCine2.setText("Cine: " + entradas.get(buscadorPelicula + 1).getCine());
			lblFecha2.setText("Fecha: " + entradas.get(buscadorPelicula + 1).getFecha());
			lblHora2.setText("Hora: " + entradas.get(buscadorPelicula + 1).getHora());
			lblCantidad2.setText("Cantidad: " + entradas.get(buscadorPelicula + 1).getCantidadEntradas().toString());
			rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula + 1).getNomPelicula() + ".jpg";
			lblImagenPeli2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula2.setText("Precio: " + entradas.get(buscadorPelicula + 1).getPrecioPelicula() + "€/U");

			// Se hace invisible la tercera entrada, ya que no hace falta
			lblCantidad3.setVisible(false);
			lblCine3.setVisible(false);
			lblCodigoEntrada3.setVisible(false);
			lblFecha3.setVisible(false);
			lblHora3.setVisible(false);
			lblImagenPeli3.setVisible(false);
			lblNombrePelicula3.setVisible(false);
			lblContainerEntrada3.setVisible(false);
			lblPrecioPelicula3.setVisible(false);

		} else if (tamañoArrayList == 1) {

			contador = tamañoArrayList;
			buscadorPelicula += 3;

			// Se hace visible la primera entrada
			lblContainerEntrada1.setVisible(true);
			lblCodigoEntrada1.setVisible(true);
			lblNombrePelicula1.setVisible(true);
			lblCine1.setVisible(true);
			lblFecha1.setVisible(true);
			lblHora1.setVisible(true);
			lblCantidad1.setVisible(true);
			lblImagenPeli1.setVisible(true);
			lblPrecioPelicula1.setVisible(true);

			// Se modifican los campos de la primera entrada
			lblNombrePelicula1.setText("Nombre: " + entradas.get(buscadorPelicula).getNomPelicula());
			lblCodigoEntrada1.setText(entradas.get(buscadorPelicula).getCodEntrada());
			lblCine1.setText("Cine: " + entradas.get(buscadorPelicula).getCine());
			lblFecha1.setText("Fecha: " + entradas.get(buscadorPelicula).getFecha());
			lblHora1.setText("Hora: " + entradas.get(buscadorPelicula).getHora());
			lblCantidad1.setText("Cantidad: " + entradas.get(buscadorPelicula).getCantidadEntradas().toString());
			String rutaIcono = "/multimedia/" + entradas.get(buscadorPelicula).getNomPelicula() + ".jpg";
			lblImagenPeli1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaIcono)).getImage()
					.getScaledInstance(lblImagenPeli1.getWidth(), lblImagenPeli1.getHeight(), Image.SCALE_DEFAULT)));
			lblPrecioPelicula1.setText("Precio: " + entradas.get(buscadorPelicula).getPrecioPelicula() + "€/U");

			// Se hace invisible la segunda entrada, ya que no hace falta
			lblCantidad2.setVisible(false);
			lblCine2.setVisible(false);
			lblCodigoEntrada2.setVisible(false);
			lblFecha2.setVisible(false);
			lblHora2.setVisible(false);
			lblImagenPeli2.setVisible(false);
			lblNombrePelicula2.setVisible(false);
			lblContainerEntrada2.setVisible(false);
			lblPrecioPelicula2.setVisible(false);

			// Se hace invisible la tercera entrada, ya que no hace falta
			lblCantidad3.setVisible(false);
			lblCine3.setVisible(false);
			lblCodigoEntrada3.setVisible(false);
			lblFecha3.setVisible(false);
			lblHora3.setVisible(false);
			lblImagenPeli3.setVisible(false);
			lblNombrePelicula3.setVisible(false);
			lblContainerEntrada3.setVisible(false);
			lblPrecioPelicula3.setVisible(false);

		} else {

			contador = 0;

			// Se hace invisible la primera entrada, ya que no hace falta
			lblCantidad1.setVisible(false);
			lblCine1.setVisible(false);
			lblCodigoEntrada1.setVisible(false);
			lblFecha1.setVisible(false);
			lblHora1.setVisible(false);
			lblImagenPeli1.setVisible(false);
			lblNombrePelicula1.setVisible(false);
			lblContainerEntrada1.setVisible(false);
			lblPrecioPelicula1.setVisible(false);

			// Se hace invisible la segunda entrada, ya que no hace falta
			lblCantidad2.setVisible(false);
			lblCine2.setVisible(false);
			lblCodigoEntrada2.setVisible(false);
			lblFecha2.setVisible(false);
			lblHora2.setVisible(false);
			lblImagenPeli2.setVisible(false);
			lblNombrePelicula2.setVisible(false);
			lblContainerEntrada2.setVisible(false);
			lblPrecioPelicula2.setVisible(false);

			// Se hace invisible la tercera entrada, ya que no hace falta
			lblCantidad3.setVisible(false);
			lblCine3.setVisible(false);
			lblCodigoEntrada3.setVisible(false);
			lblFecha3.setVisible(false);
			lblHora3.setVisible(false);
			lblImagenPeli3.setVisible(false);
			lblNombrePelicula3.setVisible(false);
			lblContainerEntrada3.setVisible(false);
			lblPrecioPelicula3.setVisible(false);
		}

	}

	public void crearCamposIniciales() {

		lblContainerEntrada1 = new JLabel("");
		lblContainerEntrada1.setBounds(25, posicionY, 724, 125);
		lblContainerEntrada1.setBorder(new LineBorder(Color.red, 1, true));
		add(lblContainerEntrada1);

		lblNombrePelicula1 = new JLabel("Nombre: El Señor de los Anillos 3");
		lblNombrePelicula1.setBounds(158, posicionY + 69, 214, 14);
		add(lblNombrePelicula1);

		lblCine1 = new JLabel("Cine: Cine Elorrieta");
		lblCine1.setBounds(158, posicionY + 31, 181, 14);
		add(lblCine1);

		lblFecha1 = new JLabel("Fecha: 01/03/2024");
		lblFecha1.setBounds(400, posicionY + 31, 181, 14);
		add(lblFecha1);

		lblHora1 = new JLabel("Hora: 18:00");
		lblHora1.setBounds(400, posicionY + 69, 181, 14);
		add(lblHora1);

		lblCantidad1 = new JLabel("Cantidad: 3");
		lblCantidad1.setBounds(613, posicionY + 69, 95, 14);
		add(lblCantidad1);

		lblCodigoEntrada1 = new JLabel("#12345678");
		lblCodigoEntrada1.setBounds(130, posicionY + 6, 95, 14);
		add(lblCodigoEntrada1);

		lblImagenPeli1 = new JLabel("");
		lblImagenPeli1.setBounds(40, posicionY + 5, 80, 115);
		lblImagenPeli1.setBorder(new LineBorder(Color.black, 1, true));
		add(lblImagenPeli1);

		lblPrecioPelicula1 = new JLabel("Precio: 8.10");
		lblPrecioPelicula1.setBounds(613, posicionY + 31, 95, 14);
		add(lblPrecioPelicula1);

		posicionY += 130;

		lblContainerEntrada2 = new JLabel("");
		lblContainerEntrada2.setBounds(25, posicionY, 724, 125);
		lblContainerEntrada2.setBorder(new LineBorder(Color.red, 1, true));
		add(lblContainerEntrada2);

		lblNombrePelicula2 = new JLabel("Nombre: El Señor de los Anillos 3");
		lblNombrePelicula2.setBounds(158, posicionY + 69, 214, 14);
		add(lblNombrePelicula2);

		lblCine2 = new JLabel("Cine: Cine Elorrieta");
		lblCine2.setBounds(158, posicionY + 31, 181, 14);
		add(lblCine2);

		lblFecha2 = new JLabel("Fecha: 01/03/2024");
		lblFecha2.setBounds(400, posicionY + 31, 181, 14);
		add(lblFecha2);

		lblHora2 = new JLabel("Hora: 18:00");
		lblHora2.setBounds(400, posicionY + 69, 181, 14);
		add(lblHora2);

		lblCantidad2 = new JLabel("Cantidad: 3");
		lblCantidad2.setBounds(613, posicionY + 69, 95, 14);
		add(lblCantidad2);

		lblCodigoEntrada2 = new JLabel("#12345678");
		lblCodigoEntrada2.setBounds(130, posicionY + 6, 95, 14);
		add(lblCodigoEntrada2);

		lblImagenPeli2 = new JLabel("");
		lblImagenPeli2.setBounds(40, posicionY + 5, 80, 115);
		lblImagenPeli2.setBorder(new LineBorder(Color.black, 1, true));
		add(lblImagenPeli2);

		lblPrecioPelicula2 = new JLabel("Precio: 8.10");
		lblPrecioPelicula2.setBounds(613, posicionY + 31, 95, 14);
		add(lblPrecioPelicula2);

		posicionY += 130;

		lblContainerEntrada3 = new JLabel("");
		lblContainerEntrada3.setBounds(25, posicionY, 724, 125);
		lblContainerEntrada3.setBorder(new LineBorder(Color.red, 1, true));
		add(lblContainerEntrada3);

		lblNombrePelicula3 = new JLabel("Nombre: El Señor de los Anillos 3");
		lblNombrePelicula3.setBounds(158, posicionY + 69, 214, 14);
		add(lblNombrePelicula3);

		lblCine3 = new JLabel("Cine: Cine Elorrieta");
		lblCine3.setBounds(158, posicionY + 31, 181, 14);
		add(lblCine3);

		lblFecha3 = new JLabel("Fecha: 01/03/2024");
		lblFecha3.setBounds(400, posicionY + 31, 181, 14);
		add(lblFecha3);

		lblHora3 = new JLabel("Hora: 18:00");
		lblHora3.setBounds(400, posicionY + 69, 181, 14);
		add(lblHora3);

		lblCantidad3 = new JLabel("Cantidad: 3");
		lblCantidad3.setBounds(613, posicionY + 69, 95, 14);
		add(lblCantidad3);

		lblCodigoEntrada3 = new JLabel("#12345678");
		lblCodigoEntrada3.setBounds(130, posicionY + 6, 95, 14);
		add(lblCodigoEntrada3);

		lblImagenPeli3 = new JLabel("");
		lblImagenPeli3.setBounds(40, posicionY + 5, 80, 115);
		lblImagenPeli3.setBorder(new LineBorder(Color.black, 1, true));
		add(lblImagenPeli3);

		lblPrecioPelicula3 = new JLabel("Precio: 8.10");
		lblPrecioPelicula3.setBounds(613, posicionY + 31, 95, 14);
		add(lblPrecioPelicula3);

	}
}
