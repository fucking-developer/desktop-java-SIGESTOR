package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>BaseDatosTorneo</code>.
 * <p>
 * Las caracter�sticas de la clase <code>ExcepcionBaseDatosTorneo</code> son:
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
 * mostrar la causa del error al no poder crear el torneo Eliminaci�n directa en
 * la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ELIMINA_TORNEO_ELIMINACION_DIRECTA</code> para
 * mostrar la causa del error al no poder eliminar el torneo Eliminaci�n directa
 * en la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_CONSULTA_TORNEO</code> para indicar que no ha
 * sido posible consultar los datos del torneo.</li>
 * <li><code> MENSAJE_EXCEPCION_CONSULTA_TORNEO_SUIZO</code> para indicar que no
 * ha sido posible obtener el n�mero de ciclos del torneo Suizo.</li>
 * <li><code> MENSAJE_EXCEPCION_CONSULTA_TORNEO_ROUND_ROBIN</code> para indicar
 * que no ha sido posible obtener el n�mero de ciclos y vueltas del torneo Round
 * Robin.</li>
 * <li><code> MENSAJE_EXCEPCION_CONSULTA_TORNEO_ELIMINACION_DIRECTA</code> para
 * indicar que no ha sido posible obtener el n�mero de ciclos y el subtipo
 * (simple o doble) del torneo Eliminaci�n directa.</li>
 * <li><code>MENSAJE_EXCEPCION_SOLUCION </code> para mostrar la soluci�n del
 * error presentado.</li>
 * </ul>
 * 
 * @version 08/04/2023
 * 
 * @author Hernan Sesai Lopez Aragon
 * @author Jennifer Cort�s P�rez
 * @author Beatriz Andrea Jim�nez R�os
 * @author Victor Triste P�rez
 * @author Eder Euclides Dionisio Diaz
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
	public static final String MENSAJE_EXCEPCION_INSERTA_TORNEO_SUIZO = "No se ha conseguido guardar los datos del torneo Suizo";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible eliminar el
	 * torneo Suizo en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ELIMINA_TORNEO_SUIZO = "No se ha conseguido eliminar los datos del torneo Suizo";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible crear el torneo
	 * Eliminaci�n directa en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_INSERTA_TORNEO_ELIMINACION_DIRECTA = "No se ha conseguido guardar los datos del torneo Eliminaci�n directa";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible eliminar el
	 * torneo Eliminaci�n directa en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ELIMINA_TORNEO_ELIMINACION_DIRECTA = "No se ha conseguido eliminar los datos del torneo Eliminaci�n directa";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible consultar los
	 * datos del torneo en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONSULTA_TORNEO = "No se pudo consultar los datos del torneo ";

	/**
	 * Primera parte del mensaje que indica que no ha sido posible obtener el n�mero
	 * de ciclos del torneo Suizo en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONSULTA_TORNEO_SUIZO = "No se pudo obtener el n�mero de ciclos del torneo Suizo";

	/**
	 * Primera parte del mensaje que indica que no ha sido posible obtener el n�mero
	 * de ciclos y vueltas del torneo Round Robin en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONSULTA_TORNEO_ROUND_ROBIN = "No se pudo obtener el n�mero de ciclos y vueltas del torneo Round Robin";

	/**
	 * Primera parte del mensaje que indica que no ha sido posible obtener el n�mero
	 * de ciclos y el subtipo (simple o doble) del torneo Eliminaci�n directa en la
	 * base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONSULTA_TORNEO_ELIMINACION_DIRECTA = "No se pudo obtener el n�mero de ciclos y el subtipo (simple o doble) del torneo Eliminaci�n directa";

	/**
	 * Mensaje que indica la soluci�n del error presentado.
	 */
	public static final String MENSAJE_EXCEPCION_SOLUCION = ".\nIntente de nuevo, si el error persiste contacte con soporte t�cnico.";

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