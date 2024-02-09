package modelo;

public class Compra {

	private int codCompra, cantPel, descuento;
	private Double precioTotal, precioFinal;

	private Cliente cliente;

	public Compra(int codCompra, int cantPel, int descuento, Double precioTotal, Double precioFinal, Cliente cliente) {
		super();
		this.codCompra = codCompra;
		this.cantPel = cantPel;
		this.descuento = descuento;
		this.precioTotal = precioTotal;
		this.precioFinal = precioFinal;
		this.cliente = cliente;
	}

	public int getCodCompra() {
		return codCompra;
	}

	public void setCodCompra(int codCompra) {
		this.codCompra = codCompra;
	}

	public int getCantPel() {
		return cantPel;
	}

	public void setCantPel(int cantPel) {
		this.cantPel = cantPel;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Compra [CodCompra=" + codCompra + ", CantPel=" + cantPel + ", Descuento=" + descuento + ", PrecioTotal="
				+ precioTotal + ", PrecioFinal=" + precioFinal + ", cliente=" + cliente + "]";
	}

}
