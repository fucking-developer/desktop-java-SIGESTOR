package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>BaseDatosParticipante</code>.
 * <p>
 * Las características de la clase <code>ExcepcionBaseDatosParticipante</code>
 * son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_INSERTA_PARTICIPANTE</code> para mostrar la causa
 * del error al no poder insertar los datos de un participante en la base de
 * datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ACTUALIZA_PARTICIPANTE</code> para mostrar la
 * causa del error al no poder actualizar los datos de un participante en la
 * base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ELIMINA_PARTICIPANTE</code> para mostrar la causa
 * del error al no poder eliminar los datos de un participante en la base de
 * datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ACTUALIZA_LUGAR_PARTICIPANTE</code> para mostrar
 * la causa del error al no poder actualizar el lugar del participante en la
 * base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ACTUALIZA_RESULTADO_PARTICIPANTE</code> para
 * mostrar la causa del error al no poder actualizar el resultado de un
 * participante en la base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_OBTIENE_PARTICIPANTE</code> para mostrar la causa
 * del error al no poder obtener los datos de un participante en la base de
 * datos.</li>
 * <li><code>MENSAJE_EXCEPCION_OBTIENE_PUNTAJE</code> para mostrar la causa del
 * error al no poder obtener el puntaje de un participante en la base de
 * datos.</li>
 * <li><code>MENSAJE_EXCEPCION_SOLUCION</code> para mostrar la solución del
 * error presentado.</li>
 * </ul>
 * 
 * @version 20/04/2022
 * 
 * @author Beatriz Andrea Jiménez Ríos
 * @author Victor Triste Pérez
 */
public class ExcepcionBaseDatosParticipante extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Primera parte del mensaje que indica que no ha sido posible insertar un
	 * participante en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_INSERTA_PARTICIPANTE = "No se pudieron guardar los datos del participante";

	/**
	 * Primera parte del mensaje que indica que no ha sido posible eliminar al
	 * participante en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ELIMINA_PARTICIPANTE = "No se pudieron eliminar los datos del participante";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible actualizar el
	 * lugar del participante en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ACTUALIZA_LUGAR_PARTICIPANTE = "No se pudieron actualizar los datos de ";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible actualizar el
	 * resultado del participante en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ACTUALIZA_RESULTADO_PARTICIPANTE = "No se pudieron actualizar los datos de ";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible consultar la
	 * lista de participantes en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_OBTIENE_PARTICIPANTE = "Error del sistema al consultar la lista de participantes";

	/**
	 * Segunda parte del mensaje que indica la solución del error presentado.
	 */
	public static final String MENSAJE_EXCEPCION_SOLUCION = ".\nIntente de nuevo, si el error persiste contacte con soporte técnico.";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionBaseDatosParticipante</code>.
	 */
	public ExcepcionBaseDatosParticipante(String mensaje) {
		super(mensaje);
	}

}
