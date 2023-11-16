package entidad;

public class Provincia {
	private int IdProvincia;
	private String Descripcion;
	
	public Provincia() {
		
	}
	
	public Provincia(String desc)
	{
		this.Descripcion = desc;
	}
	
	public int getIdProvincia() {
		return IdProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		IdProvincia = idProvincia;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	

}
