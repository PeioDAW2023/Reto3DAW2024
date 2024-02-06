package modelo;

import java.util.Objects;

public class Entrada {

	private String codEntrada;
	private int cantidad;
	
	private Compra compra;
	private Sesion sesion;
	public String getCodEntrada() {
		return codEntrada;
	}
	public void setCodEntrada(String codEntrada) {
		this.codEntrada = codEntrada;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public Sesion getSesion() {
		return sesion;
	}
	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cantidad, codEntrada, compra, sesion);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrada other = (Entrada) obj;
		return cantidad == other.cantidad && Objects.equals(codEntrada, other.codEntrada)
				&& Objects.equals(compra, other.compra) && Objects.equals(sesion, other.sesion);
	}
	@Override
	public String toString() {
		return "Entrada [codEntrada=" + codEntrada + ", cantidad=" + cantidad + ", compra=" + compra + ", sesion="
				+ sesion + "]";
	}
	
	
}
