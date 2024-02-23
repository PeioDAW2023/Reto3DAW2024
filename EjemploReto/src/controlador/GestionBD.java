package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import ficheros.Factura;
import modelo.Cine;
import modelo.Cliente;
import modelo.Entrada;
import modelo.EntradaCompleta;
import modelo.Pelicula;
import modelo.Sala;
import modelo.Sesion;
import vista.VistaPrincipal;

public class GestionBD {

	// Declaracion de variables y ArrayLists
	private Connection conexion;
	public String nomPelicula;
	public String cineSeleccionado = "";
	public String fechaSeleccionada;
	public String DNIGuardado;
	public Integer cantidadEntradasTotales;
	public double precioTotal;

	public ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	public ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<EntradaCompleta> entradas = new ArrayList<EntradaCompleta>();
	public ArrayList<Sala> salas = new ArrayList<Sala>();
	public ArrayList<Cine> cines = new ArrayList<Cine>();
	public ArrayList<Entrada> entradaBBDD = new ArrayList<Entrada>();

	// Constructor de GestionBD
	public GestionBD() {
		IniciarConexion();
	}

	// Método para iniciar la conexion con la base de datos
	private void IniciarConexion() {
		System.out.println("Conectando...");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/cines", "root", "");
		} catch (ClassNotFoundException e) {
			System.out.println("Libreria no encontrada");
		} catch (SQLException e) {
			System.out.println("Base de datos no encontrada");
		}
		System.out.println("Conexion establecida");
	}

	// Método para cerrar la conexion con la base de datos
	public void CerrarConexion() {
		System.out.println("Desconectando...");
		try {
			if (!conexion.isClosed()) {
				conexion.close();
			}
			conexion = DriverManager.getConnection("jdbc.mysql://localhost:3307/cines", "root", "");
		} catch (SQLException e) {
			System.out.println("No hay conexion con la Base de Datos");
		}
		System.out.println("Desconectado");
	}

	// DEVOLUCIÓN DE ARRAYLIST GUARDADOS

	// Devuelve el ArrayList de peliculas
	public ArrayList<Pelicula> devolverArraylistPeliculas() {
		return peliculas;
	}

	// Devuelve el ArrayList de sesiones
	public ArrayList<Sesion> devolverArrayListSesiones() {
		return sesiones;
	}

	// Devuelve el ArrayList de clientes
	public ArrayList<Cliente> devolverArrayListClientes() {
		return clientes;
	}

	// Devuelve el ArrayList de cines
	public ArrayList<Cine> devolverArrayListCines() {
		return cines;
	}

	// Devuelve el ArrayList de entradas
	public ArrayList<EntradaCompleta> devolverArraylistEntradas() {
		return entradas;
	}

	// Devuelve el ArrayList de salas
	public ArrayList<Sala> devolverArraylistSalas() {
		return salas;
	}

	// CARGAR ARRAYLISTS (Limpiarlo y rellenarlo con nuevos datos)

	// Carga el ArrayList sesiones (Panel Cine)
	public void cargarFechasPorCine(String cine) {
		sesiones.clear();
		sesiones = queryFechasDisponibles(cine);
	}

	// Carga el ArrayList sesiones (Panel Peliculas)
	public void cargarSesionesDePeliculas(String codPelicula) {
		sesiones.clear();
		sesiones = querySesionesDeUnaPeliculaPorCineYFecha(codPelicula);
	}

	// Carga el ArrayList salas (Panel Peliculas)
	public void cargarNombreSalaDeSesion(String codPelicula, String codSala) {
		salas.clear();
		salas = querySalasDeSesionesDeUnaPeliculaPorCineYFecha(codPelicula, codSala);
	}

	// Carga el ArrayList peliculas (Panel Peliculas)
	public void cargarPeliculas() {
		peliculas.clear();
		peliculas = queryPeliculasSegunCineYFecha();
	}

	// Carga el ArrayList cines (Panel Cines)
	public void cargarCines() {
		cines.clear();
		cines = queryCines();
	}

	// Carga el ArrayList entradas (En este caso se carga en un panel, pero luego se
	// utiliza este método para almacenarlo)
	public void cargarEntradas(ArrayList<EntradaCompleta> entradas) {
		this.entradas = entradas;
	}

	// QUERYS PARA LA BASE DE DATOS: AGREGACIÓN A LOS ARRAYLISTS E INSERTS

	// Carga en el ArrayList clientes aquellos clientes que coincidan con el DNI
	// proporcionado por parámetro
	public ArrayList<Cliente> queryClientes(String DNI) {

		try {
			Statement consulta = conexion.createStatement();
			String query = "SELECT * FROM Cliente WHERE DNI like '" + DNI + "'";

			ResultSet resultadoConsulta = consulta.executeQuery(query);
			while (resultadoConsulta.next()) {
				clientes.add(new Cliente(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4),
						resultadoConsulta.getString(5)));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return clientes;

	}

	// Carga en el ArrayList peliculas aquellas peliculas que se emitan en un cine
	// concreto y en una fecha concreta, proporcionados por parámetros
	public ArrayList<Pelicula> queryPeliculasSegunCineYFecha() {

		try {

			Statement consulta = conexion.createStatement();
			String query = "SELECT p.* FROM pelicula p join sesion se join sala sa join cine c on p.CodPelicula = se.CodPelicula and se.CodSala = sa.CodSala and sa.CodCine = c.CodCine WHERE c.NomCine like '"
					+ cineSeleccionado + "' and se.Fecha like '" + fechaSeleccionada + "' group by p.CodPelicula";
			ResultSet resultadoConsulta = consulta.executeQuery(query);
			// Recorremos el resultado para visualizar todas las filas
			// Se hace un bucle mientras haya regisrtos en resultado

			while (resultadoConsulta.next()) {
				peliculas.add(new Pelicula(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getInt(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return peliculas;
	}

	// Carga en el ArrayList salas la sala en la que se va emitir una sesion
	// específica, usando los parámetros codPelicula y codSala
	public ArrayList<Sala> querySalasDeSesionesDeUnaPeliculaPorCineYFecha(String codPelicula, String codSala) {

		try {

			Statement consulta = conexion.createStatement();
			String query = "SELECT sa.* FROM pelicula p JOIN sesion se JOIN sala sa JOIN cine c ON p.CodPelicula = se.CodPelicula AND se.CodSala = sa.CodSala AND sa.CodCine = c.CodCine WHERE c.NomCine like '"
					+ cineSeleccionado + "' and se.Fecha like '" + fechaSeleccionada + "' and p.CodPelicula like '"
					+ codPelicula + "' and se.CodSala like '" + codSala + "'";
			ResultSet resultadoConsulta = consulta.executeQuery(query);
			// Recorremos el resultado para visualizar todas las filas
			// Se hace un bucle mientras haya regisrtos en resultado

			while (resultadoConsulta.next()) {
				salas.add(new Sala(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salas;
	}

	// Carga en el ArrayList sesiones las sesiones de una pelicula en una cine y
	// fecha concreto, se pasará el codPelicula por parámetro
	public ArrayList<Sesion> querySesionesDeUnaPeliculaPorCineYFecha(String codPelicula) {

		try {

			Statement consulta = conexion.createStatement();
			String query = "SELECT se.* FROM pelicula p JOIN sesion se JOIN sala sa JOIN cine c ON p.CodPelicula = se.CodPelicula AND se.CodSala = sa.CodSala AND sa.CodCine = c.CodCine WHERE c.NomCine like '"
					+ cineSeleccionado + "' and se.Fecha like '" + fechaSeleccionada + "' and p.CodPelicula like '"
					+ codPelicula + "'";
			ResultSet resultadoConsulta = consulta.executeQuery(query);
			// Recorremos el resultado para visualizar todas las filas
			// Se hace un bucle mientras haya regisrtos en resultado

			while (resultadoConsulta.next()) {
				sesiones.add(new Sesion(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5),
						resultadoConsulta.getString(6)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sesiones;
	}

	// Carga en el ArrayList sesiones todas las sesiones de un cine, pero se
	// agruparán por fecha, ya que sólo nos interesa ese dato, se pasará el nombre
	// del cine por parámetro
	public ArrayList<Sesion> queryFechasDisponibles(String cine) {
		try {
			Statement consulta = conexion.createStatement();
			String query = "SELECT * FROM Sesion se JOIN sala sa JOIN cine c on se.CodSala = sa.CodSala AND sa.CodCine = c.CodCine WHERE c.NomCine LIKE '"
					+ cine + "' GROUP BY Fecha";
			ResultSet resultadoConsulta = consulta.executeQuery(query);

			while (resultadoConsulta.next()) {
				sesiones.add(new Sesion(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5),
						resultadoConsulta.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sesiones;
	}

	// Carga en el ArrayList cines todos los cines registrados
	public ArrayList<Cine> queryCines() {
		try {
			Statement consulta = conexion.createStatement();
			String query = "SELECT * FROM Cine";
			ResultSet resultadoConsulta = consulta.executeQuery(query);

			while (resultadoConsulta.next()) {
				cines.add(new Cine(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cines;
	}

	public int queryNumeroTotalDePeliculas(Integer codCompra) {
		peliculas.clear();
		int cantidadDePeliculasTotal = 0;

		try {
			Statement consulta = conexion.createStatement();
			String query = "Select p.* FROM pelicula p join sesion se join entrada e on p.CodPelicula = se.CodPelicula and se.CodSesion = e.CodSesion WHERE e.CodCompra like '"
					+ codCompra + "' GROUP BY p.CodPelicula";
			ResultSet resultadoConsulta = consulta.executeQuery(query);

			while (resultadoConsulta.next()) {
				peliculas.add(new Pelicula(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getInt(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		cantidadDePeliculasTotal = peliculas.size();
		return cantidadDePeliculasTotal;
	}

	// Insert para agregar el cliente que se haya registrado
	private void agregarCliente(String DNI, String nombre, String apellido, String sexo, String contraseña) {

		try {
			Statement insertarDatos = conexion.createStatement();
			String insert = "INSERT INTO cliente values ('" + DNI + "','" + nombre + "','" + apellido + "','" + sexo
					+ "','" + contraseña + "')";
			insertarDatos.executeUpdate(insert);
			insertarDatos.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void agregarEntrada(String codEntrada, int codCompra, String codSesion, int cantidad) {
		try {
			Statement insertarDatos = conexion.createStatement();
			String insert = "INSERT INTO entrada values ('" + codEntrada + "','" + codCompra + "','" + codSesion + "','"
					+ cantidad + "')";
			insertarDatos.executeUpdate(insert);
			insertarDatos.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void agregarCompra(int codCompra, String DNI, double precioTotal, int entradasTotales, int descuento,
			double precioFinal) {
		try {
			Statement insertarDatos = conexion.createStatement();
			String insert = "INSERT INTO compra values ('" + codCompra + "','" + DNI + "','" + precioTotal + "','"
					+ entradasTotales + "','" + descuento + "','" + precioFinal + "')";
			insertarDatos.executeUpdate(insert);
			insertarDatos.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// MÉTODOS USADOS EN PANELES AJENOS A BASE DE DATOS

	// Valida que los campos del registro sean válidos, y posteriormente llama a
	// queryCines
	public void validarRegistro(VistaPrincipal vp, String DNI, String nombre, String apellido, String sexo,
			String contraseña, String repeticionContraseña) {
		Pattern regexDNI = Pattern.compile("^\\d{8}[A-Z]$"); // Regex para comprobar que el DNI cumple unos requisitos
		Matcher validarDNI = regexDNI.matcher(DNI);
		Pattern regexContraseña = Pattern
				.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$_@$!%*?&])([A-Za-z\\d$@_!%*?&]|[^ ]){6,15}$"); // Regex
																												// para
																												// comprobar
																												// que
																												// la
																												// nueva
																												// contraseña
																												// a
																												// registrar
																												// cumple
																												// unos
																												// requisitos
		Matcher validarContraseña = regexContraseña.matcher(contraseña);
		if (DNI.isEmpty() | nombre.isEmpty() | apellido.isEmpty() | sexo.isEmpty() | contraseña.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Rellene todos los campos", "Error de Registro",
					JOptionPane.ERROR_MESSAGE);
		} else if (!validarDNI.find()) {
			JOptionPane.showMessageDialog(null, "DNI no válido", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (!contraseña.equals(repeticionContraseña)) {
			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error de Registro",
					JOptionPane.ERROR_MESSAGE);
		} else if (!validarContraseña.find()) {
			JOptionPane.showMessageDialog(null, "La contraseña no cumple los requisitos", "Error de Registro",
					JOptionPane.ERROR_MESSAGE);
		} else {
			agregarCliente(DNI, nombre, apellido, sexo, contraseña);
			DNIGuardado = DNI;
			JOptionPane.showMessageDialog(null, "Su usuario ha sido guardado con éxito", "Éxito al registrar",
					JOptionPane.INFORMATION_MESSAGE);
			vp.cambiarPanel(1);
		}
	}

	// Guarda en las variables cine y fecha seleccionados, para poder usarlos más
	// adelante en querys
	public void guardarCineYFechaSeleccionadas(String cine, String fecha) {
		cineSeleccionado = cine;
		fechaSeleccionada = fecha;
	}

	// Envía el ArrayList entradas al gestor de facturas, para poder utilizar la
	// información que contenga
	public void imprimirFactura(GestionBD gestion, ArrayList<EntradaCompleta> entradas, double precioTotal,
			int descuento, double precioFinal, Integer codCompra) {
		Factura factura = new Factura();
		factura.escribirFactura(gestion, entradas, precioTotal, descuento, precioFinal, codCompra);
	}

}
