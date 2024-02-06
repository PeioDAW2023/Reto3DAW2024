package modelo;

import java.util.Objects;

public class Cine {
	
	private String codCine, direccion, nomCine;

	

	public String getCodCine() {
		return codCine;
	}

	public void setCodCine(String codCine) {
		this.codCine = codCine;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNomCine() {
		return nomCine;
	}

	public void setNomCine(String nomCine) {
		this.nomCine = nomCine;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codCine, direccion, nomCine);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cine other = (Cine) obj;
		return Objects.equals(codCine, other.codCine) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(nomCine, other.nomCine);
	}

	@Override
	public String toString() {
		return "Cine [CodCine=" + codCine + ", Direccion=" + direccion + ", NomCine=" + nomCine + "]";
	}
	
	
	
	
}
