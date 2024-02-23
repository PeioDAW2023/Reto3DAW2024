package modelo;

public class EntradaCompleta {

	private String nomPelicula, fecha, cine, hora, codEntrada, codSesion, salaSesion;
	private Integer cantidadEntradas;
	private String precioPelicula;

	public EntradaCompleta(String cine, String nomPelicula, String codSesion, String salaSesion, String fecha,
			String hora, String codEntrada, Integer cantidadEntradas, String precioPelicula) {
		this.nomPelicula = nomPelicula;
		this.codSesion = codSesion;
		this.salaSesion = salaSesion;
		this.fecha = fecha;
		this.cine = cine;
		this.hora = hora;
		this.codEntrada = codEntrada;
		this.cantidadEntradas = cantidadEntradas;
		this.precioPelicula = precioPelicula;
	}

	public String getSalaSesion() {
		return salaSesion;
	}

	public void setSalaSesion(String salaSesion) {
		this.salaSesion = salaSesion;
	}

	public String getCodSesion() {
		return codSesion;
	}

	public void setCodSesion(String codSesion) {
		this.codSesion = codSesion;
	}

	public String getNomPelicula() {
		return nomPelicula;
	}

	public void setNomPelicula(String nomPelicula) {
		this.nomPelicula = nomPelicula;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCine() {
		return cine;
	}

	public void setCine(String cine) {
		this.cine = cine;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getCodEntrada() {
		return codEntrada;
	}

	public void setCodEntrada(String codEntrada) {
		this.codEntrada = codEntrada;
	}

	public Integer getCantidadEntradas() {
		return cantidadEntradas;
	}

	public void setCantidadEntradas(Integer cantidadEntradas) {
		this.cantidadEntradas = cantidadEntradas;
	}

	public String getPrecioPelicula() {
		return precioPelicula;
	}

	public void setPrecioPelicula(String precioPelicula) {
		this.precioPelicula = precioPelicula;
	}

	@Override
	public String toString() {
		return "EntradaCompleta [nomPelicula=" + nomPelicula + ", fecha=" + fecha + ", cine=" + cine + ", hora=" + hora
				+ ", codEntrada=" + codEntrada + ", codSesion=" + codSesion + ", salaSesion=" + salaSesion
				+ ", cantidadEntradas=" + cantidadEntradas + ", precioPelicula=" + precioPelicula + "]";
	}

}
