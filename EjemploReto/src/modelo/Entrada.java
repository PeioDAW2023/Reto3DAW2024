package modelo;

public class Entrada {

	private String codEntrada;
	private String codSesion;
	private int cantidad;
	private int codCompra;
	
	public Entrada(String codEntrada, int codCompra, String codSesion, int cantidad) {
		this.codEntrada = codEntrada;
		this.cantidad = cantidad;
		this.codCompra = codCompra;
		this.codSesion = codSesion;
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

	public int getCodCompra() {
		return codCompra;
	}

	public void setCompra(int codCompra) {
		this.codCompra = codCompra;
	}

	public String getSesion() {
		return codSesion;
	}

	public void setSesion(String codSesion) {
		this.codSesion = codSesion;
	}

	@Override
	public String toString() {
		return "Entrada [codEntrada=" + codEntrada + ", cantidad=" + cantidad + ", compra=" + codCompra + ", sesion="
				+ codSesion + "]";
	}

}
