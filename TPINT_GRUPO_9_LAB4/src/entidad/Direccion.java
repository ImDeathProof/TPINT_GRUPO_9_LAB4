package entidad;

public class Direccion {
	private int id;
	private String Calle;
	private int Numero;
	private Localidad _Localidad;
	private Provincia _Provincia;

	public Direccion()
	{
		
	}
	public Direccion(String Calle, int Num, Localidad loc, Provincia prov)
	{
		this.Calle = Calle;
		this.Numero = Num;
		this._Localidad = loc;
		this._Provincia = prov;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCalle() {
		return Calle;
	}
	public void setCalle(String calle) {
		Calle = calle;
	}
	public int getNumero() {
		return Numero;
	}
	public void setNumero(int numero) {
		Numero = numero;
	}
	public Localidad get_Localidad() {
		return _Localidad;
	}
	public void set_Localidad(Localidad _Localidad) {
		this._Localidad = _Localidad;
	}
	public Provincia get_Provincia() {
		return _Provincia;
	}
	public void set_Provincia(Provincia _Provincia) {
		this._Provincia = _Provincia;
	}
	
	
	
}
