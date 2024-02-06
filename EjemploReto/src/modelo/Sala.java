package modelo;

import java.util.Objects;

public class Sala {

	private String codSala, nomSala;
	
	private Cine cine;

	public String getCodSala() {
		return codSala;
	}

	public void setCodSala(String codSala) {
		this.codSala = codSala;
	}

	public String getNomSala() {
		return nomSala;
	}

	public void setNomSala(String nomSala) {
		this.nomSala = nomSala;
	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cine, codSala, nomSala);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return Objects.equals(cine, other.cine) && Objects.equals(codSala, other.codSala)
				&& Objects.equals(nomSala, other.nomSala);
	}

	@Override
	public String toString() {
		return "Sala [codSala=" + codSala + ", nomSala=" + nomSala + ", cine=" + cine + "]";
	}
	
	
	
}
