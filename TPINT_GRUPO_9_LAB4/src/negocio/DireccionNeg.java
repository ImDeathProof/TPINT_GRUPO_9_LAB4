package negocio;

import entidad.DBException;
import entidad.Direccion;
import entidad.GenericException;
import entidad.Localidad;
import entidad.Provincia;

public interface DireccionNeg {

	public int modificarDireccion(Direccion direc);
	public Direccion obtenerDireccionPorID(int idDireccion) throws DBException, GenericException;
	public Localidad obtenerLocalidadPorID(int idLocalidad) throws DBException, GenericException;
	public Provincia obtenerProvinciaPorID(int idProvincia) throws DBException, GenericException;
	public Localidad obtenerLocalidadPorDesc(String desc) throws DBException, GenericException;
	public Provincia obtenerProvinciaPorDesc(String desc) throws DBException, GenericException;
}
