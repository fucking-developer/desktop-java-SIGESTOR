package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>ResultadosFinales</code>.
 * <p>
 * Las características de la clase <code>ExcepcionResultadosFinales</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_RESULTADOS_FINALES_INCOMPLETOS</code> para
 * mostrar la causa del error generado por no haber terminado de capturar todos
 * los resultados de los ciclos del torneo.</li>
 * </ul>
 * 
 * @version 02/06/2023
 * 
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Hernán Sesaí Lopéz Aragón
 * @author Francisco Samuel Reyes Cortes
 */
public class ExcepcionResultadosFinales extends Exception {
	
	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Mensaje 6.1 referente al documento de mensajes del sistema.
	 */
	public static final String MENSAJE_EXCEPCION_RESULTADOS_FINALES_INCOMPLETOS = "No se pueden mostrar los resultados finales"
			+ "\nya que no se ha terminado de capturar ";

	/**
	 * Constructor donde se concatena el mensaje del sistema.
	 * 
	 * @param palabraCicloPersonalizada palabra que cambia dependiendo del nombre
	 *                                  del ciclo.
	 */
	public ExcepcionResultadosFinales(String palabraCicloPersonalizada) {
		super(MENSAJE_EXCEPCION_RESULTADOS_FINALES_INCOMPLETOS + palabraCicloPersonalizada + ".");
	}
}
