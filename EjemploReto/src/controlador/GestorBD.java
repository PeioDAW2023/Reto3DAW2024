package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorBD {
	
	private static Connection conexion;

	public GestorBD() {
		IniciarConexion();
	}

	private void IniciarConexion() {
		System.out.println("Conectando...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/cines", "root", "");
		} catch (ClassNotFoundException e) {
			System.out.println("Libreria no encontrada");
		} catch (SQLException e) {
			System.out.println("Base de datos no encontrada");
		}
		System.out.println("Conexion establecida.");

	}

	public void CerrarConexion() {
		System.out.println("Desconectando...");
		try {
			if (!conexion.isClosed()) {
				conexion.close();
			}

			conexion = DriverManager.getConnection("jdbc:mysql://localhost/cines", "root", "");
		} catch (SQLException e) {
			System.out.println("No hay conexion con la Base de Datos");
		}
		System.out.println("Conexion cerrada.");
	}
	
}
