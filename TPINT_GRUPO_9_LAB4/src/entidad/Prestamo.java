package entidad;
import java.math.BigDecimal;
import java.time.LocalDate;

import entidad.Cliente;
import entidad.Cuenta;

public class Prestamo {
	private int Id_Prestamo;
	private BigDecimal Monto;
	private BigDecimal ImporteCuota;
	private int PlazoPago; //cantidad de dias
	private BigDecimal MontoAprobado;
	private float TasaInteres;
	private LocalDate FechaPedido;
	private String Estado;
	private Cuenta Cuenta;
	private Cliente Cliente;
	
	public Prestamo() {
		this.MontoAprobado = new BigDecimal("0.0");
	}
	
	public int getId_Prestamo() {
		return Id_Prestamo;
	}
	public void setId_Prestamo(int id_Prestamo) {
		Id_Prestamo = id_Prestamo;
	}
	public BigDecimal getMonto() {
		return Monto;
	}
	public void setMonto(BigDecimal monto) {
		Monto = monto;
	}
	public BigDecimal getImporteCuota() {
		return ImporteCuota;
	}
	public void setImporteCuota(BigDecimal importeCuota) {
		ImporteCuota = importeCuota;
	}
	public int getPlazoPago() {
		return PlazoPago;
	}
	public void setPlazoPago(int plazoPago) {
		PlazoPago = plazoPago;
	}
	public BigDecimal getMontoAprobado() {
		return MontoAprobado;
	}
	public void setMontoAprobado(BigDecimal montoAprobado) {
		MontoAprobado = montoAprobado;
	}
	public float getTasaInteres() {
		return TasaInteres;
	}
	public void setTasaInteres(float tasaInteres) {
		TasaInteres = tasaInteres;
	}
	public LocalDate getFechaPedido() {
		return FechaPedido;
	}
	public void setFechaPedido(LocalDate fechaPedido) {
		FechaPedido = fechaPedido;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public Cuenta getCuenta() {
		return Cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		Cuenta = cuenta;
	}
	public Cliente getCliente() {
		return Cliente;
	}
	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}
	@Override
	public String toString() {
		return "Prestamo [Id_Prestamo=" + Id_Prestamo + ", Monto=" + Monto + ", ImporteCuota=" + ImporteCuota
				+ ", PlazoPago=" + PlazoPago + ", MontoAprobado=" + MontoAprobado + ", TasaInteres=" + TasaInteres
				+ ", FechaPedido=" + FechaPedido + ", Estado=" + Estado + ", Cuenta=" + Cuenta + ", Cliente=" + Cliente
				+ "]";
	}
}
