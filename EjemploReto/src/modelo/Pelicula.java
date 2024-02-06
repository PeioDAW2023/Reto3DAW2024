package modelo;

import java.util.Objects;

public class Pelicula {

	private String codPelicula, nomPelicula, dcp, genero;
	private int duracion;
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
	public int hashCode() {
		return Objects.hash(codPelicula, dcp, duracion, genero, nomPelicula);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return Objects.equals(codPelicula, other.codPelicula) && Objects.equals(dcp, other.dcp)
				&& duracion == other.duracion && Objects.equals(genero, other.genero)
				&& Objects.equals(nomPelicula, other.nomPelicula);
	}
	@Override
	public String toString() {
		return "Pelicula [codPelicula=" + codPelicula + ", nomPelicula=" + nomPelicula + ", dcp=" + dcp + ", genero="
				+ genero + ", duracion=" + duracion + "]";
	}
	
	
	
}
