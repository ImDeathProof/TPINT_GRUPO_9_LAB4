package entidad;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cuota {
	public int IDCuota;
	public BigDecimal MontoAPagar;
	public String Estado; //('Pagado','No Pagado','Vencido') 
	public LocalDate FechaDePago;
	public int IDPrestamo;
	public int IDUsuario;
	public int IDCuenta;
	public int Nro_Cuota;
	public int Cuotas_Totales;
	
	public Cuota() {
		
	}
	
	public int getIDCuota() {
		return IDCuota;
	}
	public void setIDCuota(int iDCuota) {
		IDCuota = iDCuota;
	}
	public BigDecimal getMontoAPagar() {
		return MontoAPagar;
	}
	public void setMontoAPagar(BigDecimal montoAPagar) {
		MontoAPagar = montoAPagar;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public LocalDate getFechaDePago() {
		return FechaDePago;
	}
	public void setFechaDePago(LocalDate fechaDePago) {
		FechaDePago = fechaDePago;
	}
	public int getIDPrestamo() {
		return IDPrestamo;
	}
	public void setIDPrestamo(int iDPrestamo) {
		IDPrestamo = iDPrestamo;
	}
	public int getIDUsuario() {
		return IDUsuario;
	}
	public void setIDUsuario(int iDUsuario) {
		IDUsuario = iDUsuario;
	}
	public int getIDCuenta() {
		return IDCuenta;
	}
	public void setIDCuenta(int iDCuenta) {
		IDCuenta = iDCuenta;
	}
	public int getNro_Cuota() {
		return Nro_Cuota;
	}
	public void setNro_Cuota(int nro_Cuota) {
		Nro_Cuota = nro_Cuota;
	}
	public int getCuotas_Totales() {
		return Cuotas_Totales;
	}
	public void setCuotas_Totales(int cuotas_Totales) {
		Cuotas_Totales = cuotas_Totales;
	}
	
}
