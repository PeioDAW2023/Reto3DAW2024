package modelo;

public class Sala {

	private String codSala, nomSala, codCine;

	public Sala(String codSala, String nomSala, String codCine) {
		this.codSala = codSala;
		this.nomSala = nomSala;
		this.codCine = codCine;
	}

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

	public String getCine() {
		return codCine;
	}

	public void setCine(String codCine) {
		this.codCine = codCine;
	}

	@Override
	public String toString() {
		return "Sala [codSala=" + codSala + ", nomSala=" + nomSala + ", cine=" + codCine + "]";
	}

}
