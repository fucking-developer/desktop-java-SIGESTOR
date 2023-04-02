package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>AbrirTorneo</code>.
 * <p>
 * Las características de la clase <code>ExcepcionAbrirTorneo</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_ARCHIVO_INVALIDO</code> Para informar al usuario
 * que el archivo seleccionado es inválido.</li>
 * <li><code>MENSAJE_EXCEPCION_ARCHIVO_NO_EXISTENTE</code> Para informar al
 * usuario que el archivo seleccionado no existe.</li>
 * </ul>
 * 
 * @version 23/03/2022
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 */
public class ExcepcionAbrirTorneo extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Mensaje 1.1.1 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_ARCHIVO_NO_EXISTENTE = "El sistema no encontró el archivo.\n"
			+ "Compruebe el nombre de archivo e intente de nuevo.";

	/**
	 * Mensaje 1.1.2 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_ARCHIVO_INVALIDO = "El archivo seleccionado no pertenece al sistema, intente seleccionando otro archivo.";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionAbrirTorneo</code>.
	 */
	public ExcepcionAbrirTorneo(String mensaje) {
		super(mensaje);
	}

}
