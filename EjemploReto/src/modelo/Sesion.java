package modelo;

import java.util.Objects;

public class Sesion {

	private int codSesion;
	private String fecha, hora;
	private Double precio;
	
	private Sala sala;
	private Pelicula pelicula;
	public int getCodSesion() {
		return codSesion;
	}
	public void setCodSesion(int codSesion) {
		this.codSesion = codSesion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codSesion, fecha, hora, pelicula, precio, sala);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sesion other = (Sesion) obj;
		return codSesion == other.codSesion && Objects.equals(fecha, other.fecha) && Objects.equals(hora, other.hora)
				&& Objects.equals(pelicula, other.pelicula) && Objects.equals(precio, other.precio)
				&& Objects.equals(sala, other.sala);
	}
	@Override
	public String toString() {
		return "Sesion [codSesion=" + codSesion + ", fecha=" + fecha + ", hora=" + hora + ", precio=" + precio
				+ ", sala=" + sala + ", pelicula=" + pelicula + "]";
	}
	
	
	
}
