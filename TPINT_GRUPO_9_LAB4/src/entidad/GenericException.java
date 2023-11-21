package entidad;

public class GenericException extends Exception {
	public static final long serialVersionUID = 700L;
	
    public GenericException(String mensaje) {
        super(mensaje);
    }
}