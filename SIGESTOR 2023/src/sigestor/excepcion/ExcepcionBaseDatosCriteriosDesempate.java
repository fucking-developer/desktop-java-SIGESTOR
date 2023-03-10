package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de
 * <code>BaseDatosCriteriosDesempate</code>.
 * <p>
 * Las características de la clase
 * <code>ExcepcionBaseDatosCriteriosDesempate</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_INSERTA_CRITERIOS_DESEMPATE</code> para mostrar
 * la causa del error al no poder guardar los criterios de desempate en la base
 * de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ELIMINA_CRITERIOS_DESEMPATE</code> para mostrar
 * la causa del error al no poder modificar los criterios de desempate en la
 * base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_SOLUCION</code> para mostrar la solución del
 * error presentado.</li>
 * </ul>
 * 
 * @version 17/04/2022
 * 
 * @author Beatriz Andrea Jiménez Ríos
 * @author Victor Triste Pérez
 */
public class ExcepcionBaseDatosCriteriosDesempate extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Primera parte del mensaje que indica que no ha sido posible guardar los
	 * criterios de desempate en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_INSERTA_CRITERIOS_DESEMPATE = "No se pudieron guardar los datos de criterios de desempate";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible eliminar los
	 * criterios de desempate en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ELIMINA_CRITERIOS_DESEMPATE = "No se pudieron eliminar los criterios de desempate";
	/**
	 * Segunda parte del mensaje que indica la solución del error presentado.
	 */
	public static final String MENSAJE_EXCEPCION_SOLUCION = ".\nIntente de nuevo, si el error persiste contacte con soporte técnico.";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible consultar los
	 * criterios de desempate en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONSULTA_DESEMPATE = "No se pudo consultar los criterios de desempate del torneo ";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionBaseDatosCriteriosDesempate</code>.
	 */
	public ExcepcionBaseDatosCriteriosDesempate(String mensaje) {
		super(mensaje);
	}

}
