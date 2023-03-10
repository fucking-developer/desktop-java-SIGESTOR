package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>BaseDatosPersonalizacion</code>.
 * <p>
 * Las características de la clase
 * <code>ExcepcionBaseDatosPersonalizacion</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_INSERTA_PERSONALIZACION</code> para mostrar la
 * causa del error al no poder guardar los datos de personalización en la base
 * de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ACTUALIZA_PERSONALIZACION</code> para mostrar la
 * causa del error al no poder actualizar los datos de personalización en la
 * base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_SOLUCION</code> para mostrar la solución del
 * error presentado.</li>
 * </ul>
 * 
 * @version 05/06/2022
 * 
 * @author Beatriz Andrea Jiménez Ríos
 * @author Victor Triste Pérez
 */
public class ExcepcionBaseDatosPersonalizacion extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Primera parte del mensaje que indica que no ha sido posible guardar los datos
	 * de personalización en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_INSERTA_PERSONALIZACION = "No se pudieron guardar los datos de personalización";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible consultar los
	 * datos de personalización en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONSULTAR_PERSONALIZACION = "No se pudieron consultar los datos de personalización";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible actualizar los
	 * datos de personalización en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ACTUALIZA_PERSONALIZACION = "No se pudieron modificar los datos de personalización";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible actualizar los
	 * datos de personalización en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_OBTENER_PERSONALIZACION = "No se pudieron obtener los datos de personalización";
	/**
	 * Segunda parte del mensaje que indica la solución del error presentado.
	 */
	public static final String MENSAJE_EXCEPCION_SOLUCION = ".\nIntente de nuevo, si el error persiste contacte con soporte técnico.";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionBaseDatosPersonalizacion</code>.
	 */
	public ExcepcionBaseDatosPersonalizacion(String mensaje) {
		super(mensaje);
	}
}
