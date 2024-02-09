package modelo;

public class Entrada {

	private String codEntrada;
	private int cantidad;

	private Compra compra;
	private Sesion sesion;

	public Entrada(String codEntrada, int cantidad, Compra compra, Sesion sesion) {
		super();
		this.codEntrada = codEntrada;
		this.cantidad = cantidad;
		this.compra = compra;
		this.sesion = sesion;
	}

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
	public String toString() {
		return "Entrada [codEntrada=" + codEntrada + ", cantidad=" + cantidad + ", compra=" + compra + ", sesion="
				+ sesion + "]";
	}

}
