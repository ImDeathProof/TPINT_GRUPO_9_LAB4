package Dominio;
import java.time.LocalDate;

public class Usuario {
		private int Id_Usuario;
		private String Usuario;
	    private String _Nombre;
	    private String _Apellido;
	    private int _DNI;
	    private int _CUIL;
	    private boolean _Sexo; // 0: Masculino, 1: Femenino
	    private String _Nacionalidad;
	    private LocalDate _FechaNacimiento; // Utiliza LocalDate para la fecha de nacimiento
	    private Direccion _Direccion;
	    private String _Localidad;
	    private String _Provincia;
	    private String _Email;
	    private int _Telefono;
	    private boolean Tipo; //0= Cliente, 1= Admin
}
