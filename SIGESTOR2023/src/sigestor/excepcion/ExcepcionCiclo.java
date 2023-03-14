package sigestor.excepcion;

/**
 * Sirve para manejar las excepciones de <code>Ciclo</code>.
 * <p>
 * Las características de la clase <code>ExcepcionCiclo</code> son:
 * <ul>
 * <li><code>MENSAJE_EXCEPCION_CICLO_INCOMPLETO</code> para mostrar la causa del
 * error generado por intentar crear nuevos pareos sin haber concluido el ciclo
 * actual.</li>
 * </ul>
 * 
 * @version 20/06/2022
 * 
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Hernán Sesaí Lopéz Aragón
 * @author Francisco Samuel Reyes Cortes
 */
public class ExcepcionCiclo extends Exception {
	
	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Mensaje 4.1 del documento de mensajes del sistema parte 1.
	 * 
	 */
	private static final String MENSAJE_EXCEPCION_CICLO_INCOMPLETO_PARTE_1 = "El sistema no ha podido realizar ";

	/**
	 * Mensaje 4.1 del documento de mensajes del sistema parte 2.
	 * 
	 */
	private static final String MENSAJE_EXCEPCION_CICLO_INCOMPLETO_PARTE_2 = " porque no ha finalizado ";

	/**
	 * Mensaje 4.1 del documento de mensajes del sistema parte 3.
	 * 
	 */
	private static final String MENSAJE_EXCEPCION_CICLO_INCOMPLETO_PARTE_3 = ".\nPor favor capture todos los resultados.";

	/**
	 * Constructor donde se concatenan las partes del mensaje del sistema.
	 * 
	 * @param palabraCicloPersonalizada palabra personalizada que cambia dependiendo
	 *                                  del nombre del ciclo.
	 * @param palabraPareoPersonalizada palabra personalizada que cambia dependiendo
	 *                                  del nombre del pareo.
	 */
	public ExcepcionCiclo(String palabraCicloPersonalizada, String palabraPareoPersonalizada) {
		super(MENSAJE_EXCEPCION_CICLO_INCOMPLETO_PARTE_1 + palabraCicloPersonalizada
				+ MENSAJE_EXCEPCION_CICLO_INCOMPLETO_PARTE_2 + palabraPareoPersonalizada
				+ MENSAJE_EXCEPCION_CICLO_INCOMPLETO_PARTE_3);
	}
}