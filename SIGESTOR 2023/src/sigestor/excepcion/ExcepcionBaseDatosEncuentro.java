package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>BaseDatosEncuentro</code>.
 * <p>
 * Las características de la clase <code>ExcepcionBaseDatosEncuentro</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_INSERTA_ENCUENTRO</code> para mostrar la causa
 * del error al no poder insertar el encuentro en la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ACTUALIZA_ENCUENTRO</code> para mostrar la causa
 * del error al no poder actualizar el encuentro en la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ELIMINA_ENCUENTRO</code> para mostrar la causa
 * del error al no poder eliminar el encuentro en la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_SOLUCION</code> para mostrar la solución del
 * error presentado.</li>
 * </ul>
 * 
 * @version 20/04/2022
 * 
 * @author Beatriz Andrea Jiménez Ríos
 * @author Victor Triste Pérez
 */
public class ExcepcionBaseDatosEncuentro extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Primera parte del mensaje que indica que no ha sido posible guardar el
	 * encuentro en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_INSERTA_ENCUENTRO = "No se ha conseguido guardar los datos de ";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible actualizar el
	 * encuentro en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ACTUALIZA_ENCUENTRO = "No se ha conseguido actualizar los datos de ";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible eliminar los
	 * encuentros en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ELIMINA_ENCUENTROS = "No se ha conseguido eliminar los encuentros del torneo ";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible eliminar el
	 * encuentro en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ELIMINA_ENCUENTRO = "No se ha conseguido eliminar los datos de ";
	/**
	 * Segunda parte del mensaje que indica la solución del error presentado.
	 */
	public static final String MENSAJE_EXCEPCION_SOLUCION = ".\nIntente de nuevo, si el error persiste contacte con soporte técnico.";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionBaseDatosEncuentro</code>.
	 */
	public ExcepcionBaseDatosEncuentro(String mensaje) {
		super(mensaje);
	}
}
