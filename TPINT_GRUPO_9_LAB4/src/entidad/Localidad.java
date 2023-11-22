package entidad;

public class Localidad {
	private int IdLocalidad;
	private String Descripcion;
	
	
	public Localidad()
	{
		
	}
	
	public Localidad(String desc)
	{
		this.Descripcion = desc;
	}
	
	public int getIdLocalidad() {
		return IdLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		IdLocalidad = idLocalidad;
	}
	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	
}
