package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones del paquete <code>utilerias</code>.
 * <p>
 * La característica de la clase <code>ExcepcionUtilerias</code> es:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_GENERAR_ARCHIVO_CSV</code> para mostrar la causa
 * del error al no generar el archivo CSV.</li>
 * <li><code>MENSAJE_EXCEPCION_FORMATO_INCORRECTO</code> para indicar que el archivo CSV no cumple el formato.</li>
 * <li><code>MENSAJE_EXCEPCION_LEER_ARCHIVO_CSV</code> para indicar que ha ocurrido un error al leer el archivo CSV.</li>
 * <li><code>MENSAJE_EXCEPCION_ESCRIBIR_PLANTILLA_CSV</code> para indicar que ha ocurrido un error al escribir la plantilla CSV.</li>
 * </ul>
 * 
 * @version 23/03/2023
 * 
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 *
 */
public class ExcepcionUtilerias extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Mensaje 6.2 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_GENERAR_ARCHIVO_CSV = "El sistema no pudo generar el archivo CSV "
			+ "\n debido a un error de escritura de datos.";
	
	/**
	 * Indica que el formato del archivo CSV no es correcto.
	 */
	public static final String MENSAJE_EXCEPCION_FORMATO_INCORRECTO = "El archivo CSV no cumple con el formato requerido."
			+ "\nFavor de usar otro archivo CSV con el formato correcto.";

	/**
	 * Indica que ocurrio un error al intentar leer el archivo CSV.
	 */
	public static final String MENSAJE_EXCEPCION_LEER_ARCHIVO_CSV = "Ocurrio un error al leer el archivo CSV."
			+ "\nFavor de intentar con otro archivo CSV.";
	
	/**
	 * Indica que ocurrio un error al escribir la plantilla CSV.
	 */
	public static final String MENSAJE_EXCEPCION_ESCRIBIR_PLANTILLA_CSV = "Ocurrio un error al escrbir la plantilla CSV."
			+ "\nFavor de contactar con soporte técnico.";
	
	
	/**
	 * Permite mostrar un mensaje de acuerdo a la constante declarada en esta clase.
	 * 
	 * @param mensaje
	 *            Recibe la constante declarada en la clase
	 *            <code>ExcepcionUtilerias</code>.
	 */
	public ExcepcionUtilerias(String mensaje) {
		super(mensaje);
	}
}
