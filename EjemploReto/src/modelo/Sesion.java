package modelo;

public class Sesion {

	private int codSesion;
	private String codSala, codPelicula, fecha, hora, precio;

	public Sesion(int codSesion, String codSala, String codPelicula, String fecha, String hora, String precio) {
		super();
		this.codSesion = codSesion;
		this.codSala = codSala;
		this.codPelicula = codPelicula;
		this.fecha = fecha;
		this.hora = hora;
		this.precio = precio;
	}

	public int getCodSesion() {
		return codSesion;
	}

	public void setCodSesion(int codSesion) {
		this.codSesion = codSesion;
	}

	public String getCodSala() {
		return codSala;
	}

	public void setCodSala(String codSala) {
		this.codSala = codSala;
	}

	public String getCodPelicula() {
		return codPelicula;
	}

	public void setCodPelicula(String codPelicula) {
		this.codPelicula = codPelicula;
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

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Sesion [codSesion=" + codSesion + ", codSala=" + codSala + ", codPelicula=" + codPelicula + ", fecha="
				+ fecha + ", hora=" + hora + ", precio=" + precio + "]";
	}

}
