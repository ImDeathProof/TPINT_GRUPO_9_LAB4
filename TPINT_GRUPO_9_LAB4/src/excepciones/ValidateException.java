package excepciones;

public class ValidateException extends Exception {
	public static final long serialVersionUID = 701L;
	
    public ValidateException(String mensaje) {
        super(mensaje);
    }
}