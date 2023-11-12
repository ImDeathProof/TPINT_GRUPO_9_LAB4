package entidad;
import java.math.BigDecimal;
import java.time.LocalDate;



public class Movimiento {
	private int Id_Movimiento;
	private TipoMovimiento tipoMov;
	private BigDecimal Monto;
	private LocalDate Fecha;
	private String detalles;
	private int idCliente;
	private int idCuenta;
	
	public Movimiento()
	{
		
	}
	public int getId_Movimiento() {
		return Id_Movimiento;
	}

	public void setId_Movimiento(int id_Movimiento) {
		Id_Movimiento = id_Movimiento;
	}

	public TipoMovimiento getTipoMov() {
		return tipoMov;
	}

	public void setTipoMov(TipoMovimiento tipoMov) {
		this.tipoMov = tipoMov;
	}

	public BigDecimal getMonto() {
		return Monto;
	}

	public void setMonto(BigDecimal monto) {
		Monto = monto;
	}

	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDate fecha) {
		Fecha = fecha;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}


	@Override
	public String toString() {
		return "Movimiento [Id_Movimiento=" + Id_Movimiento + ", tipoMov=" + tipoMov + ", Monto=" + Monto + ", Fecha="
				+ Fecha + ", detalles=" + detalles + ", idCliente=" + idCliente + ", idCuenta=" + idCuenta + "]";
	}
	
	
	
}
