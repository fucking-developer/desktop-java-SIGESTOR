package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>BaseDatos</code>.
 * <p>
 * Las características de la clase <code>ExcepcionBaseDatos</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_CONTROLADOR</code> para mostrar la causa del
 * error al no encontrar el controlador de SQLite.</li>
 * <li><code>MENSAJE_EXCEPCION_CONEXION_BD_PARTE_1</code> para mostrar la causa
 * del error al no poder realizar la conexión a la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_CONEXION_BD_PARTE_2</code> para mostrar la
 * solución del error al no poder realizar la conexión a la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_CIERRA_CONEXION</code> para mostrar la causa del
 * error al no poder cerrar la conexión a la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_CONSULTA_BD</code> para mostrar la causa del
 * error al no poder realizar una consulta a la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_EJECUTA_INSTRUCCION</code> para mostrar la causa
 * del error al no poder realizar alguna acción a la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_OBTIENE_TABLAS</code> para mostrar la causa del
 * error al no poder obtener el nombre de las tablas que contiene la base de
 * datos.</li>
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
public class ExcepcionBaseDatos extends Exception {
	
	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Mensaje que indica que el controlador de SQLite no ha sido encontrado.
	 */
	public static final String MENSAJE_EXCEPCION_CONTROLADOR = "No se pudo abrir el archivo debido a un error interno en la aplicación."
			+ "\nIntente de nuevo, si el error persiste contacte con soporte técnico.";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible abrir la conexión
	 * a la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONEXION_BD_PARTE_1 = "No se pudo abrir el archivo ";
	/**
	 * Segunda parte del mensaje que indica que no ha sido posible abrir la conexión
	 * a la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONEXION_BD_PARTE_2 = ".\nIntente de nuevo, si el error persiste contacte con soporte técnico.";
	/**
	 * Mensaje que indica que no ha sido posible cerrar la conexión a la base de
	 * datos.
	 */
	public static final String MENSAJE_EXCEPCION_CIERRA_CONEXION = "No se pudo cerrar el archivo."
			+ "\nIntente de nuevo, si el error persiste contacte con soporte técnico.";
	/**
	 * Mensaje que indica que no ha sido posible realizar la consulta a la base de
	 * datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONSULTA_BD = "No se pudo realizar la consulta en el archivo."
			+ "\nIntente de nuevo, si el error persiste contacte con soporte técnico.";
	/**
	 * Mensaje que indica que no ha sido posible realizar la acción a la base de
	 * datos.
	 */
	public static final String MENSAJE_EXCEPCION_EJECUTA_INSTRUCCION = "No se pudo modificar el archivo."
			+ "\nIntente de nuevo, si el error persiste contacte con soporte técnico.";
	/**
	 * Mensaje que indica que no ha sido posible obtener el nombre de las tablas que
	 * contiene la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_OBTIENE_TABLAS = "El archivo no pertenece al sistema."
			+ "\nIntente de nuevo, si el error persiste contacte con soporte técnico.";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionBaseDatos</code>.
	 */
	public ExcepcionBaseDatos(String mensaje) {
		super(mensaje);
	}
}
