package sigestor.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sigestor.excepcion.ExcepcionBaseDatos;

/**
 * Sirve para implementar la base de datos a todo el sistema.
 * <p>
 * Las características de la clase <code>BaseDatos</code> son:
 * <ul>
 * <li><code>conexion</code> para guardar la conexión a la base de datos.</li>
 * <li><code>controlador</code> para guardar el nombre del controlador de
 * SQLite.</li>
 * <li><code>url</code> para guardar la URL del archivo.</li>
 * <li><code>nombreArchivo</code> para guardar el nombre del archivo de la base
 * de datos.</li>
 * </ul>
 * 
 * @version 20/04/2022
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * @author Victor Triste Pérez
 */
public class BaseDatos {
	/**
	 * Conexión a la base de datos.
	 */
	protected Connection conexion;
	/**
	 * Nombre del controlador de SQLite.
	 */
	private String controlador;
	/**
	 * URL del archivo.
	 */
	private String url;
	/**
	 * Nombre del archivo de la base de datos.
	 */
	private String nombreArchivo;

	/**
	 * Permite inicializar el nombre del controlador y la URL del archivo.
	 * 
	 * @param nombreArchivo
	 *            Nombre personalizado del archivo de la base de datos.
	 */
	public BaseDatos(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
		controlador = "org.sqlite.JDBC";
		url = "jdbc:sqlite:" + getNombreArchivo();
	}

	/**
	 * Permite regresar el nombre del archivo de la base de datos con extensión
	 * .torn.
	 * 
	 * @return Regresa el nombre del archivo de la base de datos.
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * Permite realizar una conexión a la base de datos.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí ocurre un error al cargar el
	 *             controlador de SQLite o sí ocurre un error al conectarse a la
	 *             base de datos.
	 */
	protected void realizarConexion() throws ExcepcionBaseDatos {
		try {
			Class.forName(controlador);
			conexion = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e1) {
			throw new ExcepcionBaseDatos(ExcepcionBaseDatos.MENSAJE_EXCEPCION_CONTROLADOR);
		} catch (SQLException e2) {
			throw new ExcepcionBaseDatos(ExcepcionBaseDatos.MENSAJE_EXCEPCION_CONEXION_BD_PARTE_1 + getNombreArchivo()
					+ ExcepcionBaseDatos.MENSAJE_EXCEPCION_CONEXION_BD_PARTE_2);
		}
	}

	/**
	 * Permite cerrar la conexión a la base de datos.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí ocurre un error al cerrar la conexión a
	 *             la base de datos.
	 */
	protected void cerrarConexion() throws ExcepcionBaseDatos {
		try {
			conexion.close();
		} catch (SQLException e) {
			throw new ExcepcionBaseDatos(ExcepcionBaseDatos.MENSAJE_EXCEPCION_CIERRA_CONEXION);
		}
	}

	/**
	 * Permite obtener el objeto con el resultado de la consulta.
	 * 
	 * @param consulta
	 *            La consulta SQL que se desea realizar a la base de datos.
	 * @return Regresa un objeto con el resultado de la consulta SQL.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí ocurre un error al realizar una
	 *             consulta a la base de datos.
	 */
	protected ResultSet realizarConsulta(String consulta) throws ExcepcionBaseDatos {
		try {
			Statement instruccion = conexion.createStatement();
			ResultSet resultado = instruccion.executeQuery(consulta);
			return resultado;
		} catch (SQLException e) {
			throw new ExcepcionBaseDatos(ExcepcionBaseDatos.MENSAJE_EXCEPCION_CONSULTA_BD);
		}
	}

	/**
	 * Permite realizar una acción a la base de datos.
	 * 
	 * @param instruccion
	 *            La instrucción SQL que se va a ejecutar.
	 * @return Regresa el número de filas afectadas por la instrucción.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí ocurre un error al realizar una acción
	 *             a la base de datos.
	 */
	protected int realizarAccion(String instruccion) throws ExcepcionBaseDatos {
		try {
			Statement instruction = conexion.createStatement();
			int valor = instruction.executeUpdate(instruccion);
			instruction.close();
			return valor;
		} catch (SQLException e) {
			System.err.println(instruccion);
			e.printStackTrace();
			throw new ExcepcionBaseDatos(ExcepcionBaseDatos.MENSAJE_EXCEPCION_EJECUTA_INSTRUCCION);

		}
	}

	/**
	 * Permite obtener el nombre de las tablas creadas en la base de datos.
	 * 
	 * @return Regresa una lista con el nombre de las tablas.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí ocurre un error al obtener las tablas.
	 */
	protected ArrayList<String> obtenerTablas() throws ExcepcionBaseDatos {
		ArrayList<String> nombreTablas = new ArrayList<String>();
		try {
			realizarConexion();
			String[] tipoTabla = { "TABLE" };
			ResultSet tablas = conexion.getMetaData().getTables(null, null, null, tipoTabla);
			while (tablas.next()) {
				nombreTablas.add(tablas.getString(3));
			}
		} catch (SQLException e) {
			throw new ExcepcionBaseDatos(ExcepcionBaseDatos.MENSAJE_EXCEPCION_OBTIENE_TABLAS);
		} finally {
			cerrarConexion();
		}
		return nombreTablas;
	}
}
