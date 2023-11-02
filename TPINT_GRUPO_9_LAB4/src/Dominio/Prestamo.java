package Dominio;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Prestamo {
	private int Id_Prestamo;
	private int Id_Usuario;
	private float CBU;
	private BigDecimal Monto;
	private int PlazoPago; //cantidad de días
	private int Id_Banco;
	private float CBU_a_Depositar;
	private boolean Estado;
	private LocalDate FechaPedido;
	private BigDecimal ImporteCuota;
	
}
