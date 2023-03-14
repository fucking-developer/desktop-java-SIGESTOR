package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>Torneo</code>.
 * <p>
 * Las características de la clase <code>ExcepcionTorneo</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_NOMBRE_VACIO</code> Para informar que el nombre
 * del torneo se encuentra vacío.</li>
 * <li><code>MENSAJE_EXCEPCION_ORGANIZADOR_VACIO</code> Para informar que el
 * campo organizador se encuentra vacío.</li>
 * <li><code>MENSAJE_EXCEPCION_FECHAS_INCOHERENTES</code> Para informar que la
 * fecha inicial del torneo no concuerda con la fecha final del torneo.</li>
 * </ul>
 * 
 * @version 23/03/2022
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 */
public class ExcepcionTorneo extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Mensaje 2.2.1 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_NOMBRE_VACIO = "El campo 'Nombre del torneo' no puede estar vacío.\n"
			+ "Ingrese un nombre para el torneo.";

	/**
	 * Mensaje 2.2.2 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_ORGANIZADOR_VACIO = "El campo 'Organizador del torneo' no puede estar vacío.\n"
			+ "Ingrese el nombre de la persona o grupo que organiza el torneo.";

	/**
	 * Mensaje 2.2.3 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_FECHAS_INCOHERENTES = "La fecha inicial del torneo no concuerda con"
			+ " la fecha final del torneo. \nVerfique que la fecha inicial del torneo sea antes de la fecha final del torneo.";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionTorneo</code>.
	 */
	public ExcepcionTorneo(String mensaje) {
		super(mensaje);

	}

}
