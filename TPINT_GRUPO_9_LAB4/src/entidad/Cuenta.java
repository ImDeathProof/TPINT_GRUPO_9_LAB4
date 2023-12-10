package entidad;
import java.time.LocalDate;

import daoImpl.CuentaDAO;

import java.math.BigDecimal;
import entidad.DBException;

public class Cuenta {
    private int IdCuenta;
    private Cliente Usuario;
    private String TipoCuenta;
    private String NumeroCuenta;
    private String CBU;
    private BigDecimal Saldo;
    private LocalDate FechaCreacion;
    private boolean Estado;

    public int getIdCuenta() {
        return IdCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.IdCuenta = idCuenta;
    }

    public Cliente getUsuario() {
        return Usuario;
    }

    public void setUsuario(Cliente idUsuario) {
        this.Usuario = idUsuario;
    }

    public String getTipoCuenta() {
        return TipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.TipoCuenta = tipoCuenta;
    }

    public String getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.NumeroCuenta = numeroCuenta;
    }

    public String getCBU() {
        return CBU;
    }

    public void setCBU(String cbu) {
        this.CBU = cbu;
    }

    public BigDecimal getSaldo() {
        return Saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.Saldo = saldo;
    }

    public LocalDate getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.FechaCreacion = fechaCreacion;
    }

    public boolean getEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}
	
	public String getNombre() throws DBException, GenericException
	{
		String nombre = this.Usuario.get_Nombre() + " " + this.Usuario.get_Apellido();
		
		return nombre;		
	}
	
	

	@Override
    public String toString() {
        return "Cuenta [IdCuenta=" + IdCuenta + ", IdUsuario=" + Usuario.toString() + ", TipoCuenta=" + TipoCuenta
                + ", NumeroCuenta=" + NumeroCuenta + ", CBU=" + CBU + ", Saldo=" + Saldo + ", FechaCreacion="
                + FechaCreacion + "]";
    }
}

