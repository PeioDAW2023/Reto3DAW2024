package modelo;

public class Cine {

	private String codCine, direccion, nomCine;

	public Cine(String codCine, String direccion, String nomCine) {
		super();
		this.codCine = codCine;
		this.direccion = direccion;
		this.nomCine = nomCine;
	}

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
	public String toString() {
		return "Cine [CodCine=" + codCine + ", Direccion=" + direccion + ", NomCine=" + nomCine + "]";
	}

}
