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
			this.TipoMovimiento = "Alta de cuenta";
			
		} 
		else if(idTipo == 2)
		{
			this.TipoMovimiento = "Alta pr�stamo";
		} 
		else if(idTipo == 3)
		{
			this.TipoMovimiento = "Pago de pr�stamo";
		} 
		else if(idTipo == 4)
		{
			this.TipoMovimiento = "Transferencia";
		}
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
