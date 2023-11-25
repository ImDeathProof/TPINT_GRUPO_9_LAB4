package entidad;

public class Localidad {
	private int IdLocalidad;
	private String Descripcion;
	private Provincia provincia;
	
	

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
	
	
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
}
