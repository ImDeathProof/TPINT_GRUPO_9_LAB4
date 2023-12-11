package excepciones;

public class DBException extends Exception {
	public static final long serialVersionUID = 700L;
	
    public DBException(String mensaje) {
        super(mensaje);
    }
}





