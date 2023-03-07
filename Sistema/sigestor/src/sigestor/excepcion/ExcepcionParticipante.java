package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>Participante</code>.
 * <p>
 * Las características de la clase <code>ExcepcionParticipante</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_NOMBRE_VACIO</code> Para informar que el nombre
 * del participante se encuentra vacío.</li>
 * </ul>
 * 
 * @version 23/03/2022
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 */
public class ExcepcionParticipante extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Mensaje 2.1 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_NOMBRE_VACIO = "El campo 'Nombre del participante' no puede estar vacío.\n"
			+ "Ingrese un nombre para el participante.\n" + "Ejemplo: \n1.- Cortés Pedro \n" + "2.- Venenosos";

	/**
	 * Mensaje 2.4.9 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_LISTA_PARTICIPANTE_INCOMPLETOS = "Verifique que existan al menos dos participantes para iniciar el torneo.";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionParticipante</code>.
	 */
	public ExcepcionParticipante(String mensaje) {
		super(mensaje);

	}

}