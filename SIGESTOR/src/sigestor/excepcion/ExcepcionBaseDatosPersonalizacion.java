package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>BaseDatosPersonalizacion</code>.
 * <p>
 * Las caracter�sticas de la clase
 * <code>ExcepcionBaseDatosPersonalizacion</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_INSERTA_PERSONALIZACION</code> Para mostrar la
 * causa del error al no poder guardar los datos de personalizaci�n en la base
 * de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_CONSULTA_PERSONALIZACION</code> Para mostrar la
 * causa del error al no poder consultar los datos de personalizaci�n en la base
 * de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_ACTUALIZA_PERSONALIZACION</code> Para mostrar la
 * causa del error al no poder actualizar los datos de personalizaci�n en la
 * base de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_OBTENER_PERSONALIZACION</code> Para mostrar la
 * causa del error al no poder obtener los datos de personalizaci�n en la base
 * de datos.</li>
 * <li><code>MENSAJE_EXCEPCION_SOLUCION</code> Para mostrar la soluci�n del
 * error presentado.</li>
 * </ul>
 * 
 * @version 02/06/2023
 * 
 * @author Beatriz Andrea Jim�nez R�os
 * @author Victor Triste P�rez
 * @author Erik Vasquez Policarpo
 */
public class ExcepcionBaseDatosPersonalizacion extends Exception {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Primera parte del mensaje que indica que no ha sido posible guardar los datos
	 * de personalizaci�n en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_INSERTA_PERSONALIZACION = "No se pudieron guardar los datos de personalizaci�n";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible consultar los
	 * datos de personalizaci�n en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_CONSULTAR_PERSONALIZACION = "No se pudieron consultar los datos de personalizaci�n";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible actualizar los
	 * datos de personalizaci�n en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_ACTUALIZA_PERSONALIZACION = "No se pudieron modificar los datos de personalizaci�n";
	/**
	 * Primera parte del mensaje que indica que no ha sido posible obtener los
	 * datos de personalizaci�n en la base de datos.
	 */
	public static final String MENSAJE_EXCEPCION_OBTENER_PERSONALIZACION = "No se pudieron obtener los datos de personalizaci�n";
	/**
	 * Segunda parte del mensaje que indica la soluci�n del error presentado.
	 */
	public static final String MENSAJE_EXCEPCION_SOLUCION = ".\nIntente de nuevo, si el error persiste contacte con soporte t�cnico.";

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
