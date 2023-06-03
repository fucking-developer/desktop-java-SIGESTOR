package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>CapturarResultados</code>.
 * <p>
 * Las caracter�sticas de la clase <code>ExcepcionCapturarResultados</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_MARCADOR_INCORRECTO</code> Para mostrar la causa
 * del error al ingresar valores no num�ricos en el campo del marcador.</li>
 * <li><code>MENSAJE_EXCEPCION_NUMERO_NEGATIVO</code> Para mostrar la causa del
 * error al ingresar valores num�ricos negativos en el campo del marcador.</li>
 * <li><code>MENSAJE_EXCEPCION_MARCADOR_VACIO</code> Para mostrar la causa del
 * error al no ingresar valores num�ricos o no num�ricos en el campo del marcador.</li>
 * </ul>
 * 
 * @version 02/06/2023
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz L�pez
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste P�rez
 * @author Erik Vasquez Policarpo
 */
public class ExcepcionCapturarResultados extends Exception {
	
	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Mensaje 6.3 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_MARCADOR_VACIO = "El sistema necesita un valor num�rico para guardar. \n"
			+ "Por favor verifique que el campo no se encuentre vac�o.";
	
	/**
	 * Mensaje 4.5.2 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_MARCADOR_INCORRECTO = "El sistema necesita un valor num�rico para guardar. \n"
			+ "Por favor verifique que solo incluya valores num�ricos.";

	/**
	 * Mensaje 4.5.3 del documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_NUMERO_NEGATIVO = "El sistema necesita un valor num�rico positivo para guardar. \n"
			+ "Por favor verifique que solo incluya valores num�ricos positivos.";

	/**
	 * Permite mostrar un mensaje de acuerdo a las constantes declaradas en esta
	 * clase.
	 * 
	 * @param mensaje Recibe la constante declarada en la clase
	 *                <code>ExcepcionCapturarResultados</code>.
	 */
	public ExcepcionCapturarResultados(String mensaje) {
		super(mensaje);
	}
}
