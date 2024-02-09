package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Pelicula;
import modelo.Sesion;

public class GestionBD {

	private Connection conexion;
	public String nomPelicula;
	private Scanner sc = new Scanner(System.in);
	public ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
	public ArrayList<Sesion> sesiones = new ArrayList<Sesion>();

	public GestionBD() {
		IniciarConexion();
	}

	public ArrayList<Pelicula> devolverArraylistPeliculas() {
		return peliculas;
	}
	public ArrayList<Sesion> devolverArrayListSesiones() {
		return sesiones;
	}
	private void IniciarConexion() {
		System.out.println("Conectando...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/cines", "root", "");
		} catch (ClassNotFoundException e) {
			System.out.println("Libreria no encontrada");
		} catch (SQLException e) {
			System.out.println("Base de datos no encontrada");
		}
		System.out.println("Conexion establecida");
	}

	public void CerrarConexion() {
		System.out.println("Desconectando...");
		try {
			if (!conexion.isClosed()) {
				conexion.close();
			}
			conexion = DriverManager.getConnection("jdbc.mysql://localhost/cines", "root", "");
		} catch (SQLException e) {
			System.out.println("No hay conexion con la Base de Datos");
		}
		System.out.println("Desconectado");
	}

	public void pedirDatos() {
		System.out.println("Por favor inserte los datos para mostrarles las sesiones disponibles:");

		System.out.print("Cine: ");
		String cine = sc.nextLine();
		System.out.print("Fecha: ");
		String fecha = sc.nextLine();
		mostrarDatos(cine, fecha);
	}

	public ArrayList<Sesion> querySesionesSegunCineYFecha(String cine, String fecha) {
		// ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
		String cineElegido;
		try {
			if (cine.equals("Zubiarte")) {
				cineElegido = "Z";
			} else {
				cineElegido = "E";
			}
			Statement consulta = conexion.createStatement();
			String query = "SELECT * FROM Sesion se WHERE se.CodSala LIKE '" + cineElegido + "%' AND se.Fecha LIKE '"
					+ fecha + "'";
			ResultSet resultadoConsulta = consulta.executeQuery(query);
			System.out.println("Sesiones para la fecha " + fecha + " y el cine " + cine);
			// Recorremos el resultado para visualizar todas las filas
			// Se hace un bucle mientras haya regisrtos en resultado

			while (resultadoConsulta.next()) {
				sesiones.add(new Sesion(resultadoConsulta.getInt(1), resultadoConsulta.getString(2),
						resultadoConsulta.getString(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5),
						resultadoConsulta.getString(6)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sesiones;
	}

	public ArrayList<Pelicula> queryPeliculasDeUnaSesion(String codigoPelicula) {
		// ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();

		try {
			Statement consulta = conexion.createStatement();
			String query = "SELECT * FROM Pelicula p WHERE p.CodPelicula LIKE '" + codigoPelicula + "'";
			ResultSet resultadoConsulta = consulta.executeQuery(query);
			System.out.println("Pelicula con el codigo" + codigoPelicula);

			while (resultadoConsulta.next()) {
				peliculas.add(new Pelicula(resultadoConsulta.getString(1), resultadoConsulta.getString(2),
						resultadoConsulta.getInt(3), resultadoConsulta.getString(4), resultadoConsulta.getString(5)));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return peliculas;

	}

	public void mostrarDatos(String cine, String fecha) {
		// ArrayList<Pelicula> peliculas ;
		// ArrayList<Sesion> sesiones;
		sesiones.clear();
		peliculas.clear();
		sesiones = querySesionesSegunCineYFecha(cine, fecha);
		for (int i = 0; i < sesiones.size(); i++) {
			System.out.println(sesiones.get(i).toString());
			System.out.println("-------------------------");
			peliculas = queryPeliculasDeUnaSesion(sesiones.get(i).getCodPelicula());
			System.out.println(peliculas.get(i).toString());
//			for (int j = 0; j < peliculas.size(); j++) {
//				
//				System.out.println("-------------------------");
//			}
		}

	}

	public void cargarDatos(String cine, String fecha) {
		// ArrayList<Sesion> sesiones;
		sesiones.clear();
		sesiones = querySesionesSegunCineYFecha(cine, fecha);
		for (int i = 0; i < sesiones.size(); i++) {
		peliculas = queryPeliculasDeUnaSesion(sesiones.get(i).getCodPelicula());
		}

	}
//	public void consultarPeliculasDeUnaSesion() {
//		ArrayList<Pelicula> peliculas;
//		for(int i = 0; i < peliculas.size();i++) {
//			
//		}
//	}
//	public ArrayList<Sesion> querySesionesDeUnaFechaYSala(String eleccionFecha, String eleccionCine)
//			throws SQLException {
//		ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
//
//		String query = "SELECT se.Fecha, se.CodSala FROM Sesion se";
//
//		PreparedStatement consulta = conexion.prepareStatement(query);
//		ResultSet resultadoConsulta = consulta.executeQuery();
//
//		String fechaElegida = "";
//		String cineElegido = "";
//		boolean encontrado = false;
//		while (resultadoConsulta.next() && !encontrado) {
//
//			String fecha = resultadoConsulta.getString(1);
//			String codSala = resultadoConsulta.getString(2);
//
//			if (fecha.contains(eleccionFecha) && codSala.contains(eleccionCine)) {
//				fechaElegida = fecha;
//				cineElegido = codSala;
//				encontrado = true;
//			}
//
//		}
//		if (fechaElegida == "") {
//			System.out.println("no se ha encontrado esa fecha");
//		} else if (cineElegido == "") {
//			System.out.println("no se ha encontrado el cine");
//		} else {
//			query = "SELECT * FROM Sesion";
//			consulta = conexion.prepareStatement(query);
//			resultadoConsulta = consulta.executeQuery();
//			while (resultadoConsulta.next()) {
//				if (fechaElegida.equals(resultadoConsulta.getString(4))
//						&& cineElegido.equals(resultadoConsulta.getString(2))) {
//					sesiones.add(new Sesion(resultadoConsulta.getInt(1), resultadoConsulta.getString(2),
//							resultadoConsulta.getString(3), resultadoConsulta.getString(4),
//							resultadoConsulta.getString(5), resultadoConsulta.getString(6)));
//				}
//			}
//		}
//		return sesiones;
//	}
//
//	public void getSesionesFechaYSala(String fecha, String cine) {
//		ArrayList<Sesion> sesiones;
//		try {
//			sesiones = querySesionesDeUnaFechaYSala(fecha, cine);
//			System.out.println("Sesiones para la fecha " + fecha + " y para el cine " + cine);
//			if (sesiones.isEmpty()) {
//				System.out.println("No hay sesiones para esa fecha");
//			} else {
//				for (int i = 0; i < sesiones.size(); i++) {
//					System.out.println(sesiones.get(i).getCodPelicula());
//					System.out.println("-------------------------");
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

//	public ArrayList<Sesion> querySesionesDeUnaFecha(String eleccionFecha) throws SQLException {
//		ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
//
//		String query = "SELECT se.Fecha FROM Sesion se";
//
//		PreparedStatement consulta = conexion.prepareStatement(query);
//		ResultSet resultadoConsulta = consulta.executeQuery();
//
//		String fechaElegida = "";
//		boolean encontrado = false;
//		while (resultadoConsulta.next() && !encontrado) {
//
//			String fecha = resultadoConsulta.getString(1);
//
//			if (fecha.contains(eleccionFecha)) {
//				fechaElegida = fecha;
//				encontrado = true;
//			}
//
//		}
//		if (fechaElegida == "") {
//			System.out.println("no se ha encontrado esa fecha");
//		} else {
//			query = "SELECT * FROM Sesion";
//			consulta = conexion.prepareStatement(query);
//			resultadoConsulta = consulta.executeQuery();
//			while (resultadoConsulta.next()) {
//				if (fechaElegida.equals(resultadoConsulta.getString(4))) {
//					sesiones.add(new Sesion(resultadoConsulta.getInt(1), resultadoConsulta.getString(2),
//							resultadoConsulta.getString(3), resultadoConsulta.getString(4),
//							resultadoConsulta.getString(5), resultadoConsulta.getString(6)));
//				}
//			}
//		}
//		return sesiones;
//	}
//
//	public void getSesionesFecha(String fecha) {
//		ArrayList<Sesion> sesiones;
//		try {
//			sesiones = querySesionesDeUnaFecha(fecha);
//			System.out.println("Sesiones para la fecha " + fecha);
//			if (sesiones.isEmpty()) {
//				System.out.println("No hay sesiones para esa fecha");
//			} else {
//				for (int i = 0; i < sesiones.size(); i++) {
//					System.out.println(sesiones.get(i).getCodSala());
//					System.out.println("-------------------------");
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
