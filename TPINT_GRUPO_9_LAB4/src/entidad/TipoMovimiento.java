package entidad;

public class TipoMovimiento {
	private int Id_TipoMovimiento;
	private String TipoMovimiento;
	
	public TipoMovimiento()
	{
		
	}
	public TipoMovimiento(int idTipo)
	{
		this.Id_TipoMovimiento = idTipo;
		
		if (idTipo == 1) {
			this.TipoMovimiento = "Pago de cuota";
		} 
		else if(idTipo == 2)
		{
			this.TipoMovimiento = "Alta de cuenta";
		} 
		else if(idTipo == 3)
		{
			this.TipoMovimiento = "Alta de Prestamo";
		} 
		else if(idTipo == 4)
		{
			this.TipoMovimiento = "Extracción";
		}
		else if(idTipo == 5)
		{
			this.TipoMovimiento = "Deposito";
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TipoMovimiento == null) ? 0 : TipoMovimiento.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoMovimiento other = (TipoMovimiento) obj;
		if (TipoMovimiento == null) {
			if (other.TipoMovimiento != null)
				return false;
		} else if (!TipoMovimiento.equals(other.TipoMovimiento))
			return false;
		return true;
	}
	public int getId_TipoMovimiento() {
		return Id_TipoMovimiento;
	}
	
	public void setId_TipoMovimiento(int id_TipoMovimiento) {
		Id_TipoMovimiento = id_TipoMovimiento;
	}
	
	public String getTipoMovimiento() {
		return TipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		TipoMovimiento = tipoMovimiento;
	}


	@Override
	public String toString() {
		return "TipoMovimiento [Id_TipoMovimiento=" + Id_TipoMovimiento + ", TipoMovimiento=" + TipoMovimiento + "]";
	}
	
	
}
