package modelo;

import java.util.Objects;

public class Cliente {

	private String dni, nomCliente, apeCliente, sexo, contrasena;

	

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
	public int hashCode() {
		return Objects.hash(apeCliente, contrasena, dni, nomCliente, sexo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(apeCliente, other.apeCliente) && Objects.equals(contrasena, other.contrasena)
				&& Objects.equals(dni, other.dni) && Objects.equals(nomCliente, other.nomCliente)
				&& Objects.equals(sexo, other.sexo);
	}

	@Override
	public String toString() {
		return "Cliente [DNI=" + dni + ", NomCliente=" + nomCliente + ", ApeCliente=" + apeCliente + ", Sexo=" + sexo
				+ ", Contrasena=" + contrasena + "]";
	}
	
}
