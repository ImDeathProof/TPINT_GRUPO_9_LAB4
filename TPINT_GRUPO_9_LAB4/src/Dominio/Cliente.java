package Dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

	private int _IDCliente;
    private String _Usuario;
    private String _Contrasena;
    private String _Nombre;
    private String _Apellido;
    private long _DNI;
    private long _CUIL;
    private boolean _Sexo; // 0: Masculino, 1: Femenino
    private String _Nacionalidad;
    private LocalDate _FechaNacimiento; // Utiliza LocalDate para la fecha de nacimiento
    private String _Direccion;
    private String _Localidad;
    private String _Provincia;
    private String _Email;
    private long _Telefono;
    private boolean _Admin; //0=cliente, 1=admin
    
    public void set_Admin(boolean _Admin) {
		this._Admin = _Admin;
	}

	public Cliente() {
    	
    }
	
	public Cliente(String u, String c) {
		this._Usuario = u;
		this._Contrasena = c;
	}
    
    public Cliente(String _Usuario, String _Contrasena, String _Nombre, String _Apellido, long _DNI, long _CUIL,
			boolean _Sexo, String _Nacionalidad, LocalDate _FechaNacimiento, String _Direccion, String _Localidad,
			String _Provincia, String _Email, long _Telefono) {
		super();
		this._Usuario = _Usuario;
		this._Contrasena = _Contrasena;
		this._Nombre = _Nombre;
		this._Apellido = _Apellido;
		this._DNI = _DNI;
		this._CUIL = _CUIL;
		this._Sexo = _Sexo;
		this._Nacionalidad = _Nacionalidad;
		this._FechaNacimiento = _FechaNacimiento;
		this._Direccion = _Direccion;
		this._Localidad = _Localidad;
		this._Provincia = _Provincia;
		this._Email = _Email;
		this._Telefono = _Telefono;
		this._Admin = false;
	}
    

	public void set_IDCliente(int _IDCliente) {
		this._IDCliente = _IDCliente;
	}

	public int get_IDCliente() {
		return _IDCliente;
	}

	public String get_Usuario() {
		return _Usuario;
	}
	public void set_Usuario(String _Usuario) {
		this._Usuario = _Usuario;
	}

	public String get_Contrasena() {
		return _Contrasena;
	}
	public void set_Contrasena(String _Contrasena) {
		this._Contrasena = _Contrasena;
	}

	public String get_Nombre() {
		return _Nombre;
	}
	public void set_Nombre(String _Nombre) {
		this._Nombre = _Nombre;
	}

	public String get_Apellido() {
		return _Apellido;
	}
	public void set_Apellido(String _Apellido) {
		this._Apellido = _Apellido;
	}

	public long get_DNI() {
		return _DNI;
	}
	public void set_DNI(long _DNI) {
		this._DNI = _DNI;
	}

	public long get_CUIL() {
		return _CUIL;
	}
	public void set_CUIL(long _CUIL) {
		this._CUIL = _CUIL;
	}

	public boolean is_Sexo() {
		return _Sexo;
	}
	public void set_Sexo(boolean _Sexo) {
		this._Sexo = _Sexo;
	}

	public String get_Nacionalidad() {
		return _Nacionalidad;
	}
	public void set_Nacionalidad(String _Nacionalidad) {
		this._Nacionalidad = _Nacionalidad;
	}
	
	public LocalDate get_FechaNacimiento() {
		return _FechaNacimiento;
	}
	public void set_FechaNacimiento(LocalDate _FechaNacimiento) {
		this._FechaNacimiento = _FechaNacimiento;
	}

	public String get_Direccion() {
		return _Direccion;
	}
	public void set_Direccion(String _Direccion) {
		this._Direccion = _Direccion;
	}

	public String get_Localidad() {
		return _Localidad;
	}
	public void set_Localidad(String _Localidad) {
		this._Localidad = _Localidad;
	}

	public String get_Provincia() {
		return _Provincia;
	}
	public void set_Provincia(String _Provincia) {
		this._Provincia = _Provincia;
	}

	public String get_Email() {
		return _Email;
	}
	public void set_Email(String _Email) {
		this._Email = _Email;
	}

	public long get_Telefono() {
		return _Telefono;
	}
	public void set_Telefono(long _Telefono) {
		this._Telefono = _Telefono;
	}

	public boolean is_Admin() {
		return _Admin;
	}


	@Override
	public String toString() {
		return "Cliente [_Usuario=" + _Usuario + ", _Contrasena=" + _Contrasena + ", _Nombre=" + _Nombre
				+ ", _Apellido=" + _Apellido + ", _DNI=" + _DNI + ", _CUIL=" + _CUIL + ", _Sexo=" + _Sexo
				+ ", _Nacionalidad=" + _Nacionalidad + ", _FechaNacimiento=" + _FechaNacimiento + ", _Direccion="
				+ _Direccion + ", _Localidad=" + _Localidad + ", _Provincia=" + _Provincia + ", _Email=" + _Email
				+ ", _Telefono=" + _Telefono + ", _Admin=" + _Admin + "]";
	}

}
