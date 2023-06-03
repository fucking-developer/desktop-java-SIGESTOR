package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>BaseDatosCiclo</code>.
 * <p>
 * Las características de la clase <code>ExcepcionBaseDatosCiclo</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_INSERTA_CICLO</code> Para mostrar la causa del
 * error al no poder insertar un ciclo en la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ELIMINA_CICLO</code> Para mostrar la causa del
 * error al no poder eliminar un ciclo en la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_SOLUCION</code> Para mostrar la solución del
 * error presentado.</li>
 * <li><code>MENSAJE_EXCEPCION_ELIMINA_CICLOS</code> Para mostrar la causa al
 * no poder eliminar todos los ciclos de la base de datos.</li>
 * </ul>
 * 
 * @version 02/06/2023
 * 
 * @author Beatriz Andrea Jiménez Ríos
 * @author Victor Triste Pérez
 * @author Erik Vasquez Policarpo
 */
public class ExcepcionBaseDatosCiclo extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Primera parte del mensaje que indica que no ha sido posible insertar un ciclo
	 * en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_INSERTA_CICLO = "No se ha conseguido guardar ";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible eliminar un ciclo
	 * en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ELIMINA_CICLO = "No se ha conseguido eliminar ";
	/**
	 * Segunda parte del mensaje que indica la solución del error presentado.
	 */
	public static final String MENSAJE_EXCEPCION_SOLUCION = ".\nIntente de nuevo, si el error persiste contacte con soporte técnico.";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible eliminar los
	 * ciclos del torneo en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ELIMINA_CICLOS = "No se ha conseguido eliminar todos los ciclos del torneo ";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionBaseDatosCiclo</code>.
	 */
	public ExcepcionBaseDatosCiclo(String mensaje) {
		super(mensaje);
	}
}
