package modelo;

public class Pelicula {

	private String codPelicula, nomPelicula, dcp, genero;
	private int duracion;

	public Pelicula(String codPelicula, String nomPelicula, int duracion, String dcp, String genero) {
		super();
		this.codPelicula = codPelicula;
		this.nomPelicula = nomPelicula;
		this.dcp = dcp;
		this.genero = genero;
		this.duracion = duracion;
	}

	public String getCodPelicula() {
		return codPelicula;
	}

	public void setCodPelicula(String codPelicula) {
		this.codPelicula = codPelicula;
	}

	public String getNomPelicula() {
		return nomPelicula;
	}

	public void setNomPelicula(String nomPelicula) {
		this.nomPelicula = nomPelicula;
	}

	public String getDcp() {
		return dcp;
	}

	public void setDcp(String dcp) {
		this.dcp = dcp;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Pelicula [codPelicula=" + codPelicula + ", nomPelicula=" + nomPelicula + ", dcp=" + dcp + ", genero="
				+ genero + ", duracion=" + duracion + "]";
	}

}
