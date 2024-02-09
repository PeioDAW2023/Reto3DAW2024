package modelo;

public class Cliente {

	private String dni, nomCliente, apeCliente, sexo, contrasena;

	public Cliente(String dni, String nomCliente, String apeCliente, String sexo, String contrasena) {
		super();
		this.dni = dni;
		this.nomCliente = nomCliente;
		this.apeCliente = apeCliente;
		this.sexo = sexo;
		this.contrasena = contrasena;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNomCliente() {
		return nomCliente;
	}

	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	public String getApeCliente() {
		return apeCliente;
	}

	public void setApeCliente(String apeCliente) {
		this.apeCliente = apeCliente;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Cliente [DNI=" + dni + ", NomCliente=" + nomCliente + ", ApeCliente=" + apeCliente + ", Sexo=" + sexo
				+ ", Contrasena=" + contrasena + "]";
	}

}
