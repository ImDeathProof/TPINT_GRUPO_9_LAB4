package negocio;
import java.util.List;
import entidad.Prestamo;

public interface PrestamoNeg {
	public List<Prestamo> ObtenerTodos();
	public Prestamo ObtenerUno(int id);
	public int Insertar(Prestamo prestamo);
	public int Editar(Prestamo prestamo);
	public int Borrar(int id);
	public int Aprobar(int id);
}
