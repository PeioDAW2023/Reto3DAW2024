package ficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import controlador.GestionBD;
import modelo.EntradaCompleta;
import modelo.Sala;

public class Factura {
	private static final DecimalFormat decfor = new DecimalFormat("0.00"); 
	public ArrayList<Sala> salas = new ArrayList<Sala>();
	public Factura() {
		
	}
	
	public void escribirFactura(GestionBD gestion, ArrayList<EntradaCompleta> entradas, double precioTotal, int descuento, double precioFinal, Integer codCompra) {
		try {
			File facturaCompra = new File ("C:\\Users\\Peio\\Documents\\GitHub\\eclipse\\PruebasReto3\\Facturas\\FacturaCompra.txt");
			BufferedWriter fichero = new BufferedWriter(new FileWriter(facturaCompra));
			
			LocalDate fechaLocal = LocalDate.now();//For reference
			LocalDateTime horaLocal = LocalDateTime.now();
			DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
			String fecha = fechaLocal.format(formatoFecha);
			String hora = horaLocal.format(formatoHora);
			
			double dineroADescontar = (descuento * precioTotal) / 100;
			fichero.write("\t     CINES SEVENTH");
			fichero.newLine();
			fichero.newLine();
			fichero.write("Usuario: " + gestion.DNIGuardado + "\tBilbao BI");
			fichero.newLine();
			fichero.newLine();
			fichero.write("Fecha: " + fecha + "\tCódigo: " + codCompra);
			fichero.newLine();
			fichero.newLine();
			fichero.write("Hora: " + hora + "\t\tTipo: Online");
			fichero.newLine();
			fichero.newLine();
			fichero.newLine();
			fichero.write("\t         COMPRA");
			fichero.newLine();
			for (int i = 0; i < entradas.size(); i++) {
				double precioTotalEntrada = entradas.get(i).getCantidadEntradas() * Double.parseDouble(entradas.get(i).getPrecioPelicula());
				fichero.newLine();
				fichero.write("Cine:\t\t" + entradas.get(i).getCine());
				fichero.newLine();
				fichero.write("Código:\t\t" + entradas.get(i).getCodEntrada());
				fichero.newLine();
				fichero.write("Título:\t\t" + entradas.get(i).getNomPelicula());
				fichero.newLine();
				fichero.write("Sesión:\t\t" + entradas.get(i).getHora() + "  /  " + entradas.get(i).getSalaSesion());
				fichero.newLine();
				fichero.write("CANT\t\tFECHA\t\tPRECIO");
				fichero.newLine();
				fichero.write(" " + entradas.get(i).getCantidadEntradas() + "\t      " + entradas.get(i).getFecha() + "\t" + decfor.format(precioTotalEntrada) + " €");
				fichero.newLine();
			}
			fichero.newLine();
			fichero.write("..........................................");
			fichero.newLine();
			fichero.write("SUB TOTAL:\t\t\t" + decfor.format(precioTotal) + " €");
			fichero.newLine();
			fichero.write(descuento + "%" + " DESC.:\t\t\t" + decfor.format(dineroADescontar) + " €");
			fichero.newLine();
			fichero.write("------------------------------------------");
			fichero.newLine();
			fichero.write("TOTAL:\t\t\t\t" + decfor.format(precioFinal) + " €");
			
	
			
			
			fichero.close();
		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
		} catch (IOException io) {
			System.out.println("Error de E/S ");
		}
	
	}
}
