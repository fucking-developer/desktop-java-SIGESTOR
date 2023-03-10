package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones del paquete <code>utilerias</code>.
 * <p>
 * La característica de la clase <code>ExcepcionUtilerias</code> es:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_GENERAR_ARCHIVO_CSV</code> para mostrar la causa
 * del error al no generar el archivo CSV.</li>
 * </ul>
 * 
 * @version 20/06/2022
 * 
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
	public static final String MENSAJE_EXCEPCION_GENERAR_ARCHIVO_CSV = "El sistema no pudo generar el archivo CSV \n debido"
			+ "a un error de escritura de datos.";

	/**
	 * Permite mostrar un mensaje de acuerdo a la constante declarada en esta clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionUtilerias</code>.
	 */
	public ExcepcionUtilerias(String mensaje) {
		super(mensaje);
	}
}
