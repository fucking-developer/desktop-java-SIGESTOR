package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>Personalizacion</code>.
 * <p>
 * Las características de la clase <code>ExcepcionPersonalizacion</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_NOMBRE_PARTICIPANTES_VACIO</code> Para informar
 * que el campo nombres de los participantes se encuentra vacío.</li>
 * <li><code>MENSAJE_EXCEPCION_PARTICIPANTES_INICIA_ENCUENTRO_VACIO</code> Para
 * informar que el campo nombres de los participantes que inician el encuentro
 * se encuentra vacío.</li>
 * <li><code>MENSAJE_EXCEPCION_PARTICIPANTES_NO_INICIA_ENCUENTRO_VACIO</code>
 * Para informar que el campo nombres de los participantes que no inician el
 * encuentro se encuentra vacío.</li>
 * <li><code>MENSAJE_EXCEPCION_NOMBRE_CICLOS_VACIO</code> Para informar que el
 * campo nombre de los ciclos se encuentra vacío.</li>
 * <li><code>MENSAJE_EXCEPCION_NOMBRE_ENCUENTROS_VACIO</code> Para informar que
 * el campo nombres de los encuentros se encuentra vacío.</li>
 * <li><code>MENSAJE_EXCEPCION_PARTICIPANTES_SIN_ENCUENTRO_VACIO</code> Para
 * informar que el campo nombres de los participantes sin encuentros se
 * encuentra vacío.</li>
 * <li><code>MENSAJE_EXCEPCION_PUNTAJES_INCOHERENTES</code> Para informar que el
 * campo puntaje para ganador debe ser mayor que el puntaje al empatar. \nDel
 * mismo modo, el puntaje por empatar debe ser mayor que el puntaje para
 * perdedor."</li>
 * <li><code>MENSAJE_EXCEPCION_MARCADORES_VACIO</code> Para informar que el
 * campo nombres de los marcadores se encuentra vacío.</li>
 * </ul>
 * 
 * @version 23/03/2022
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 */
public class ExcepcionPersonalizacion extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Mensaje 2.4.1 del documento de mensajes del sistema
	 */
	public static final String MENSAJE_EXCEPCION_NOMBRE_PARTICIPANTES_VACIO = "El campo 'Nombre de los participantes' no puede estar vacío.\n"
			+ "Ingrese un nombre en singular para los participantes.\n" + "Ejemplo: \n1.- Jugador \n" + "2.- Equipo";

	/**
	 * Mensaje 2.4.2 del documento de mensajes del sistema
	 */
	public static final String MENSAJE_EXCEPCION_PARTICIPANTES_INICIA_ENCUENTRO_VACIO = "El campo 'Nombre de los participantes que inician el encuentro' no puede estar vacío.\n"
			+ "Ingrese el nombre que se utiliza para distinguir al participante que inicia el encuentro o es asignado primero al encuentro.\n"
			+ "Ejemplo: \n1.- Blancas \n" + "2.- Local";

	/**
	 * Mensaje 2.4.3 del documento de mensajes del sistema
	 */
	public static final String MENSAJE_EXCEPCION_PARTICIPANTES_NO_INICIA_ENCUENTRO_VACIO = "El campo 'Nombre de los participantes que no inician el encuentro' no puede estar vacío.\n"
			+ "Ingrese el nombre que se utiliza para distinguir al participante que no inicia el encuentro o es asignado segundo al encuentro.\n"
			+ "Ejemplo: \n1.- Negras \n" + "2.- Visitante";

	/**
	 * Mensaje 2.4.4 del documento de mensajes del sistema
	 */
	public static final String MENSAJE_EXCEPCION_NOMBRE_CICLOS_VACIO = "El campo 'Nombre de los ciclos' no puede estar vacío.\n"
			+ "Ingrese un nombre en singular para los ciclos.\n" + "Ejemplo: \n1.- Jornada \n" + "2.- Ronda \n"
			+ "3.- Semana";

	/**
	 * Mensaje 2.4.5 del documento de mensajes del sistema
	 */
	public static final String MENSAJE_EXCEPCION_NOMBRE_ENCUENTROS_VACIO = "El campo 'Nombre de los encuentros' no puede estar vacío.\n"
			+ "Ingrese un nombre en singular para los encuentros.\n" + "Ejemplo: \n1.-Partida \n" + "2.- Partido";

	/**
	 * Mensaje 2.4.6 del documento de mensajes del sistema
	 */
	public static final String MENSAJE_EXCEPCION_PARTICIPANTES_SIN_ENCUENTRO_VACIO = "El campo 'Nombre para participantes sin encuentros' no puede estar vacío.\n"
			+ "Ingrese el nombre para distinguir que un participante no tendrá encuentro en el ciclo.\n"
			+ "Ejemplo: \n1.- Descanso" + "\n2.- Bye";

	/**
	 * Mensaje 2.4.7 del documento de mensajes del sistema
	 */
	public static final String MENSAJE_EXCEPCION_PUNTAJES_INCOHERENTES = "El puntaje para ganador debe ser mayor que el puntaje al empatar."
			+ " \nDel mismo modo, el puntaje por empatar debe ser mayor que el puntaje para perdedor."
			+ " Verifique los 3 campos.";

	/**
	 * Mensaje 2.4.8 del documento de mensajes del sistema
	 */
	public static final String MENSAJE_EXCEPCION_MARCADORES_VACIO = "El campo 'Nombre de los marcadores' no puede estar vacío.\n"
			+ "Ingrese un nombre en singular para los marcadores\n" + "Ejemplo: \n1.- Marcador" + "\n2.- Gol";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionPersonlizacion</code>.
	 */
	public ExcepcionPersonalizacion(String mensaje) {
		super(mensaje);

	}
}
