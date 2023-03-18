package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>BaseDatosTorneo</code>.
 * <p>
 * Las características de la clase <code>ExcepcionBaseDatosTorneo</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_CREA_BASE_DATOS_PARTE_1</code> para mostrar la
 * causa del error al no poder crear la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_CREA_BASE_DATOS_PARTE_2</code> para mostrar la
 * causa del error al no poder crear la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_INSERTA_DATOS_GENERALES</code> para mostrar la
 * causa del error al no poder guardar los datos generales en la base de
 * datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ACTUALIZA_DATOS_GENERALES</code> para mostrar la
 * causa del error al no poder actualizar los datos generales en la base de
 * datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ACTUALIZA_CICLO_ACTUAL_PARTE_1</code> para
 * mostrar la causa del error al no poder actualizar el ciclo actual en la base
 * de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ACTUALIZA_CICLO_ACTUAL_PARTE_2</code> para
 * mostrar la causa del error al no poder actualizar el ciclo actual en la base
 * de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_VALIDA_TABLAS</code> para mostrar la causa del
 * error al no contener en el archivo las tablas de la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_INSERTA_TORNEO_ROUND_ROBIN</code> para mostrar la
 * causa del error al no poder crear el torneo Round Robin en la base de
 * datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ELIMINA_TORNEO_ROUND_ROBIN</code> para mostrar la
 * causa del error al no poder eliminar el torneo Round Robin en la base de
 * datos.</li>
 * <li><code>MENSAJE_EXCEPCION_INSERTA_TORNEO_SUIZO</code> para mostrar la causa
 * del error al no poder crear el torneo Suizo en la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ELIMINA_TORNEO_SUIZO</code> para mostrar la causa
 * del error al no poder eliminar el torneo Suizo en la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_INSERTA_TORNEO_ELIMINACION_DIRECTA</code> para
 * mostrar la causa del error al no poder crear el torneo Eliminación directa en
 * la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ELIMINA_TORNEO_ELIMINACION_DIRECTA</code> para
 * mostrar la causa del error al no poder eliminar el torneo Eliminación directa
 * en la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_CONSULTA_TORNEO</code> para indicar que no ha
 * sido posible consultar los datos del torneo.</li>
 * <li><code> MENSAJE_EXCEPCION_CONSULTA_CICLO_SUIZO</code> para indicar que no
 * ha sido posible obtener los ciclos del torneo Suizo.</li>
 * <li><code>MENSAJE_EXCEPCION_SOLUCION </code> para mostrar la solución del
 * error presentado.</li>
 * </ul>
 * 
 * @version 17/03/2023
 * 
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * @author Victor Triste Pérez
 */
public class ExcepcionBaseDatosTorneo extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Primera parte del mensaje que indica que no ha sido posible crear la base de
	 * datos.
	 */
	public static final String MENSAJE_EXCEPCION_CREA_BASE_DATOS_PARTE_1 = "La base de datos ";
	/**
	 * Segunda parte del mensaje que indica que no ha sido posible crear la base de
	 * datos.
	 */
	public static final String MENSAJE_EXCEPCION_CREA_BASE_DATOS_PARTE_2 = " no se pudo crear";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible guardar los datos
	 * generales en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_INSERTA_DATOS_GENERALES = "No se pudieron guardar los datos generales";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible modificar los
	 * datos generales en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ACTUALIZA_DATOS_GENERALES = "No se pudieron modificar los datos generales del torneo";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible modificar el
	 * ciclo actual en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ACTUALIZA_CICLO_ACTUAL_PARTE_1 = "No se pudo modificar el ";
	/**
	 * Segunda parte del mensaje que indica que no ha sido posible modificar el
	 * ciclo actual en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ACTUALIZA_CICLO_ACTUAL_PARTE_2 = " actual";
	/**
	 * Primera parte del mensaje que indica que el archivo no contiene las tablas de
	 * la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_VALIDA_TABLAS = "El archivo no es del sistema";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible crear el torneo
	 * Round Robin en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_INSERTA_TORNEO_ROUND_ROBIN = "No se ha conseguido guardar los datos del torneo Round Robin";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible eliminar el
	 * torneo Round Robin en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ELIMINA_TORNEO_ROUND_ROBIN = "No se ha conseguido eliminar los datos del torneo Round Robin";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible crear el torneo
	 * Suizo en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_INSERTA_TORNEO_SUIZO = "No se ha conseguido crear el torneo Suizo";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible eliminar el
	 * torneo Suizo en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ELIMINA_TORNEO_SUIZO = "No se ha conseguido cancelar el torneo Suizo";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible crear el torneo
	 * Eliminación directa en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_INSERTA_TORNEO_ELIMINACION_DIRECTA = "No se ha conseguido crear el torneo Eliminación directa";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible eliminar el
	 * torneo Eliminación directa en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ELIMINA_TORNEO_ELIMINACION_DIRECTA = "No se ha conseguido cancelar el torneo Eliminación directa";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible consultar los
	 * datos del torneo en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONSULTA_TORNEO = "No se pudo consultar los datos del torneo ";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible obtener los
	 * ciclos del torneo suizo en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONSULTA_CICLO_SUIZO = "No se pudo obtener el numero de ciclos del torneo suizo";

	/**
	 * Mensaje que indica la solución del error presentado.
	 */
	public static final String MENSAJE_EXCEPCION_SOLUCION = ".\nIntente de nuevo, si el error persiste contacte con soporte técnico.";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje
	 *            Recibe la constante declarada en la clase
	 *            <code>ExcepcionBaseDatosTorneo</code>.
	 */
	public ExcepcionBaseDatosTorneo(String mensaje) {
		super(mensaje);
	}
}
