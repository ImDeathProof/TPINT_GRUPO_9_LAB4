package Dominio;
import java.time.LocalDate;
import java.math.BigDecimal;

public class Cuenta {
	private int Id_Cuenta;
	private int Id_Usuario;
	private float CBU;
	private BigDecimal Saldo; 
	private int TipoCuenta; // Caja de ahorro en pesos/dólares, cuenta corriente, etc.
	private LocalDate FechaCreacion;
	
}
