package modelo;

import java.util.Objects;

public class Compra {
	
	private int codCompra, cantPel, descuento;
	private Double precioTotal, precioFinal;
	
	private Cliente cliente;

	
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
	public int hashCode() {
		return Objects.hash(cantPel, codCompra, descuento, precioFinal, precioTotal, cliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		return cantPel == other.cantPel && codCompra == other.codCompra && descuento == other.descuento
				&& Objects.equals(precioFinal, other.precioFinal) && Objects.equals(precioTotal, other.precioTotal)
				&& Objects.equals(cliente, other.cliente);
	}

	@Override
	public String toString() {
		return "Compra [CodCompra=" + codCompra + ", CantPel=" + cantPel + ", Descuento=" + descuento + ", PrecioTotal="
				+ precioTotal + ", PrecioFinal=" + precioFinal + ", cliente=" + cliente + "]";
	}
	
	
}
