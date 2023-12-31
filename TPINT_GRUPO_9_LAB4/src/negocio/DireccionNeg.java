package negocio;

import java.util.ArrayList;

import entidad.Direccion;
import entidad.Localidad;
import entidad.Provincia;
import excepciones.DBException;
import excepciones.GenericException;

public interface DireccionNeg {

	public int modificarDireccion(Direccion direc);
	public Direccion obtenerDireccionPorID(int idDireccion) throws DBException, GenericException;
	public Localidad obtenerLocalidadPorID(int idLocalidad) throws DBException, GenericException;
	public Provincia obtenerProvinciaPorID(int idProvincia) throws DBException, GenericException;
	public Localidad obtenerLocalidadPorDesc(String desc) throws DBException, GenericException;
	public Provincia obtenerProvinciaPorDesc(String desc) throws DBException, GenericException;
	public ArrayList<Provincia> getAllProvincias() throws DBException, GenericException;
	public ArrayList<Localidad> getAllLocalidades(int provId) throws DBException, GenericException;
	public Direccion addDireccion(Direccion direc) throws GenericException, DBException;
	public boolean mismaDireccion(Direccion direc);
	public Direccion obtenerDireccionSinID(Direccion direc) throws DBException, GenericException;
}
