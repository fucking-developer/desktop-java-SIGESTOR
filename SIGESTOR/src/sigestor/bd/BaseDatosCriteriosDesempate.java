package sigestor.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sigestor.dominio.CriteriosDesempate;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosCriteriosDesempate;

/**
 * Sirve para insertar y actualizar los datos de la tabla
 * <code>criteriosDeDesempate</code> en la base de datos.
 * 
 * @version 02/06/2023
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 */
public class BaseDatosCriteriosDesempate extends BaseDatos {

	/**
	 * Permite llamar el constructor de la clase padre.
	 * 
	 * @param nombreArchivo
	 *            Nombre personalizado del archivo de la base de datos.
	 */
	public BaseDatosCriteriosDesempate(String nombreArchivo) {
		super(nombreArchivo);
	}

	/**
	 * Inserta los criterios de desempate en la base de datos.
	 * 
	 * @param criterios
	 *            Contiene los criterios de desempates.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción si ocurre un error al guardar los datos en
	 *             la tabla <code>criteriosDesempate</code> de la base de datos.
	 * @throws ExcepcionBaseDatosCriteriosDesempate
	 *             Lanza la excepción si ocurre un error
	 * 
	 */
	public void insertarCriteriosDesempate(CriteriosDesempate criterios)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosCriteriosDesempate {

		for (int i = 0; i < criterios.getListaCriteriosSeleccionados().size(); i++) {
			realizarConexion();
			int filasAfectadas = realizarAccion("INSERT INTO criteriosDesempates VALUES (" + (i + 1) + ",'"
					+ criterios.getListaCriteriosSeleccionados().get(i) + "')");
			cerrarConexion();
			if (filasAfectadas != 1) {
				throw new ExcepcionBaseDatosCriteriosDesempate(
						ExcepcionBaseDatosCriteriosDesempate.MENSAJE_EXCEPCION_INSERTA_CRITERIOS_DESEMPATE
								+ ExcepcionBaseDatosCriteriosDesempate.MENSAJE_EXCEPCION_SOLUCION);
			}

		}

	}

	/**
	 * Elimina los criterios de desempate en la base de datos.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción si ocurre un error al guardar los datos en
	 *             la tabla <code>criteriosDesempate</code> de la base de datos.
	 * @throws ExcepcionBaseDatosCriteriosDesempate
	 *             Lanza la excepción si ocurre un error.
	 */
	public void eliminarCriteriosDesempate() throws ExcepcionBaseDatos, ExcepcionBaseDatosCriteriosDesempate {
		realizarConexion();
		int filasAfectadas = realizarAccion("DELETE FROM criteriosDesempates WHERE numeroCriterio>0");
		cerrarConexion();
		if (filasAfectadas < 0) {
			throw new ExcepcionBaseDatosCriteriosDesempate(
					ExcepcionBaseDatosCriteriosDesempate.MENSAJE_EXCEPCION_ELIMINA_CRITERIOS_DESEMPATE
							+ ExcepcionBaseDatosCriteriosDesempate.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Obtiene de la base de datos los criterios de desempate seleccionados del
	 * torneo.
	 * 
	 * @return Regresa un objeto de tipo <code>CriteriosDesempate</code>
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción si no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosCriteriosDesempate
	 *             Lanza la excepción si ocurre un error al obtener los
	 *             criterios de desempate de la tabla
	 *             <code>criteriosDesempate</code> de la base de datos.
	 */
	public CriteriosDesempate obtenerCriteriosDesempate()
			throws ExcepcionBaseDatos, ExcepcionBaseDatosCriteriosDesempate {
		try {
			realizarConexion();
			CriteriosDesempate criteriosDesempate = new CriteriosDesempate();
			ArrayList<String> listaCriterios = new ArrayList<String>();
			ResultSet retorno = realizarConsulta("SELECT * FROM criteriosDesempates");
			while (retorno.next()) {
				listaCriterios.add(retorno.getString(2));
			}
			criteriosDesempate.setListaCriterios(listaCriterios);
			cerrarConexion();
			return criteriosDesempate;
		} catch (SQLException e) {
			throw new ExcepcionBaseDatos(ExcepcionBaseDatosCriteriosDesempate.MENSAJE_EXCEPCION_CONSULTA_DESEMPATE
					+ ExcepcionBaseDatosCriteriosDesempate.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

}
