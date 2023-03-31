package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>CriteriosDesempate</code>.
 * <p>
 * Las características de la clase <code>ExcepcionCriteriosDesempate</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_LISTA_VACIA</code> Para informar que la lista de
 * criterios de desempate contenga más de un criterio de desempate
 * seleccionado.</li>
 * <li><code>MENSAJE_EXCEPCION_CRITERIOS_INVALIDOS</code> Para informar que no
 * se pueden utilizar los criterios de desempate, diferencia de marcadores,
 * marcador a favor, marcador en contra y marcador de participante final, si el marcador no fue
 * seleccionado.</li>
 * <li><code>MENSAJE_EXCEPCION_CRITERIOS_TIPO_TORNEO</code> Para informar que no
 * se pueden utilizar el criterio de desempate Bucholtz , cuando el torneo es de
 * tipo Round Robin.</li>
 * <li><code>MENSAJE_EXCEPCION_CRITERIOS_INVALIDOS_ELIMINACION_DIRECTA</code>
 * Para informar que no se pueden inciar un torneo de tipo Eliminación directa
 * sin haber seleccionado los criterios de Puntuacion y Marcador de participante
 * final.
 * <li>
 * </ul>
 * 
 * @version 30/03/2023
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * @author German Luis Cruz Martínez
 * 
 */
public class ExcepcionCriteriosDesempate extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Mensaje 2.3 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_LISTA_VACIA = "Verifique que exista al menos un criterio de desempate seleccionado.";

	/**
	 * Mensaje 2.3.2 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_CRITERIOS_INVALIDOS = "No se pueden utilizar los siguientes criterios de desempate: Diferencia de marcadores, Marcador en contra, Marcador a favor y Marcador de participante final, si no hay marcadores selecionados.";

	/**
	 * Mensaje 2.3.1 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_CRITERIOS_TIPO_TORNEO = "El desempate Bucholtz no puede ser utilizado en el torneo de tipo Round Robin. Seleccione otro criterio de desempate.";

	/**
	 * Mensaje 2.3.3 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_CRITERIOS_INVALIDOS_ELIMINACION_DIRECTA = "El torneo de tipo Eliminación directa solo puede utilizar los criterios de desempate Puntuación y Marcador de participante final.";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionCriteriosDesempate</code>.
	 */
	public ExcepcionCriteriosDesempate(String mensaje) {
		super(mensaje);

	}

}
