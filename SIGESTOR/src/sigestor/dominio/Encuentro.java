package sigestor.dominio;

import sigestor.excepcion.ExcepcionCapturarResultados;
import java.util.Date;

/**
 * Sirve para guardar un encuentro de un ciclo con todos sus datos.
 * <p>
 * Las características de la clase <code>Encuentro</code> son:
 * <ul>
 * <li><code>numeroEncuentro</code> para identificar un encuentro en
 * específico.</li>
 * <li><code>idParticipanteInicial</code> para identificar al primer
 * participante de un encuentro.</li>
 * <li><code>idParticipanteFinal</code> para identificar al segundo participante
 * de un encuentro.</li>
 * <li><code>marcadorParticipanteInicial</code> para el marcador a favor del
 * primer participante.</li>
 * <li><code>marcadorParticipanteFinal</code> para el marcador a favor del
 * segundo participante.</li>
 * <li><code>resutladoEncuentro</code> para el resultado del encuentro con sus
 * dos participantes.</li>
 * <li><code>fechaDelEncuentro</code> para la fecha en que se realiza el
 * encuentro.</li>
 * </ul>
 * 
 * @version 17/03/2023
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 * @author Jonathan Eduardo Ibarra Martinez
 * @author Hernan Sesai Lopez Aragon
 * @author Francisco Samuel Reyes Cortes
 * @author Eder Euclides Dionisio Diaz
 */
public class Encuentro {

	/**
	 * Identificador del objeto <code>Encuentro</code>.
	 */
	private int numeroEncuentro;

	/**
	 * Identicador del participante inicial.
	 */
	private int idParticipanteInicial;

	/**
	 * Identificador del participante final.
	 */
	private int idParticipanteFinal;

	/**
	 * Marcador del participante inicial.
	 */
	private int marcadorParticipanteInicial;

	/**
	 * Marcador del participante final.
	 */
	private int marcadorParticipanteFinal;

	/**
	 * Resultado del encuentro que puede tener el valor de las siguientes
	 * constantes:
	 * <ul>
	 * <li>constante <code>GANADOR_INICIAL</code> si es ganador el participante
	 * inicial.</li>
	 * <li>constante <code>EMPATE</code> en caso de empatar ambos
	 * participantes.</li>
	 * <li>constante <code>GANADOR_FINAL</code> si es ganador el participante
	 * final.</li>
	 * <li>constante <code>SIN_JUGAR</code> si el encuentro no se ha jugado.</li>
	 * <li>constante <code>DESCANSO</code> si el jugador tiene descanso.</li>
	 * </ul>
	 */
	private int resultadoEncuentro;

	/**
	 * Identificador de la fecha del encuentro <code>Encuentro</code>.
	 * 
	 */
	private Date fechaDelEncuentro;

	/**
	 * Resultado del objeto <code>Encuentro</code> en caso de ser ganador el
	 * participante inicial.
	 */
	public static final int GANADOR_INICIAL = 1;

	/**
	 * Resultado del objeto <code>Encuentro</code> en caso de empatar ambos
	 * participantes.
	 */
	public static final int EMPATE = 0;

	/**
	 * Resultado del objeto <code>Encuentro</code> en caso de ser ganador el
	 * participante final.
	 */
	public static final int GANADOR_FINAL = -1;

	/**
	 * Resultado del objeto <code>Encuentro</code> en caso de no haberse jugado el
	 * encuentro
	 */
	public static final int SIN_JUGAR = 99;

	/**
	 * Resultado del objeto <code>Encuentro</code> en caso de que el participante no
	 * tenga un contrincante
	 */
	public static final int DESCANSO = 3;

	/**
	 * Inicializa las variables de un encuentro con los valores recibidos y asigna
	 * el resto con valores por defecto. Sirve para crear un objeto de tipo
	 * <code>Encuentro</code> en caso de no tener aún los marcadores y el resultado.
	 * 
	 * @param numeroEncuentro
	 *            Recibe el identificador para el encuentro.
	 * @param idParticipanteInicial
	 *            Recibe el identificador del participante inicial.
	 * @param idParticipanteFinal
	 *            Recibe el identificador del participante final.
	 * @param fechaDelEncuentro
	 *            Recibe la fecha del encuentro.
	 * @throws ExcepcionCapturarResultados
	 *             Si ocurre un error al inicializar las variables con los valores
	 *             recibidos.
	 */
	public Encuentro(int numeroEncuentro, int idParticipanteInicial, int idParticipanteFinal, Date fechaDelEncuentro)
			throws ExcepcionCapturarResultados {
		this(numeroEncuentro, idParticipanteInicial, idParticipanteFinal, SIN_JUGAR, fechaDelEncuentro);
	}

	/**
	 * Inicializa las variables de un encuentro con los valores recibidos y asigna
	 * los marcadores con valores por defecto. Sirve para crear un objeto de tipo
	 * <code>Encuentro</code> sin usar marcadores.
	 * 
	 * @param numeroEncuentro
	 *            Recibe el identificador para el encuentro.
	 * @param idParticipanteInicial
	 *            Recibe el identidicador del participante inicial.
	 * @param idParticipanteFinal
	 *            Recibe el identificador del participante final.
	 * @param resultadoEncuentro
	 *            Recibe el resultado del encuentro.
	 * @param fechaDelEncuentro
	 *            Recibe la fecha del encuentro.
	 * @throws ExcepcionCapturarResultados
	 *             Si ocurre un error al inicializar las variables con los valores
	 *             recibidos.
	 */
	public Encuentro(int numeroEncuentro, int idParticipanteInicial, int idParticipanteFinal, int resultadoEncuentro,
			Date fechaDelEncuentro) throws ExcepcionCapturarResultados {
		this(numeroEncuentro, idParticipanteInicial, idParticipanteFinal, "0", "0", resultadoEncuentro,
				fechaDelEncuentro);
	}

	/**
	 * Inicializa las variables de un encuentro con los valores recibidos. Sirve
	 * para crear un objeto de tipo <code>Encuentro</code> haciendo uso de
	 * marcadores.
	 * 
	 * @param numeroEncuentro
	 *            Recibe el identificador para el encuentro.
	 * @param idParticipanteInicial
	 *            Recibe el identificador del participante inicial.
	 * @param idParticipanteFinal
	 *            Recibe el identificador del participante final.
	 * @param marcadorParticipanteInicial
	 *            Recibe el marcador a favor del participante inicial.
	 * @param marcadorParticipanteFinal
	 *            Recibe el marcador a favor del participante final.
	 * @param resultadoEncuentro
	 *            Recibe el resultado del encuentro.
	 * @param fechaDelEncuentro
	 *            Recibe la fecha del encuentro.
	 * @throws ExcepcionCapturarResultados
	 *             Si ocurre un error al inicializar las variables con los valores
	 *             recibidos.
	 */
	public Encuentro(int numeroEncuentro, int idParticipanteInicial, int idParticipanteFinal,
			String marcadorParticipanteInicial, String marcadorParticipanteFinal, int resultadoEncuentro,
			Date fechaEncuentro) throws ExcepcionCapturarResultados {
		setNumeroEncuentro(numeroEncuentro);
		setIdParticipanteInicial(idParticipanteInicial);
		setIdParticipanteFinal(idParticipanteFinal);
		setMarcadorParticipanteInicial(marcadorParticipanteInicial);
		setMarcadorParticipanteFinal(marcadorParticipanteFinal);
		setResultadoEncuentro(resultadoEncuentro);
		setFechaEncuentro(fechaEncuentro);
	}

	/**
	 * Obtiene el identificador del objeto <code>Encuentro</code>.
	 * 
	 * @return Regresa el identificador del encuentro.
	 */

	public int getNumeroEncuentro() {
		return numeroEncuentro;
	}

	/**
	 * Inicializa el identificador del objeto <code>Encuentro</code> con el valor
	 * recibido.
	 * 
	 * @param numeroEncuentro
	 *            Recibe el identificador del encuentro.
	 */
	public void setNumeroEncuentro(int numeroEncuentro) {
		this.numeroEncuentro = numeroEncuentro;
	}

	/**
	 * Obtiene el identificador del participante inicial del objeto
	 * <code>Encuentro</code>.
	 * 
	 * @return Regresa el valor de la variable <code>numeroParticipante</code> del
	 *         objeto <code>Participante</code>.
	 */
	public int getIdParticipanteInicial() {
		return idParticipanteInicial;
	}

	/**
	 * Inicializa el identificador del participante inicial del objeto
	 * <code>Encuentro</code> con el valor recibido.
	 * 
	 * @param idParticipanteInicial
	 *            Recibe el valor de la variable <code>numeroParticipante</code> del
	 *            objeto <code>Participante</code>.
	 */
	public void setIdParticipanteInicial(int idParticipanteInicial) {
		this.idParticipanteInicial = idParticipanteInicial;
	}

	/**
	 * Obtiene el identificador del participante final del objeto
	 * <code>Encuentro</code>.
	 * 
	 * @return Regresa el valor de la variable <code>numeroParticipante</code> del
	 *         objeto <code>Participante</code>.
	 */
	public int getIdParticipanteFinal() {
		return idParticipanteFinal;
	}

	/**
	 * Inicializa el identificador del participante final del objeto
	 * <code>Encuentro</code> con el valor recibido.
	 * 
	 * @param idParticipanteFinal
	 *            Recibe el valor de la variable <code>numeroParticipante</code> del
	 *            objeto <code>Participante</code>.
	 */
	public void setIdParticipanteFinal(int idParticipanteFinal) {
		this.idParticipanteFinal = idParticipanteFinal;
	}

	/**
	 * Obtiene el marcador a favor del participante inicial del objeto
	 * <code>Encuentro</code>.
	 * 
	 * @return Regresa el marcador a favor del participante inicial.
	 */
	public int getMarcadorParticipanteInicial() {
		return marcadorParticipanteInicial;
	}

	/**
	 * Inicializa el marcador del participante inicial del objeto
	 * <code>Encuentro</code>. Recibe el marcador en forma de cadena y verifica si
	 * se trata de un valor válido para insertarlo en el marcador correspondiente.
	 * 
	 * @param marcadorParticipanteInicial
	 *            Recibe el marcador del participante inicial en forma de cadena.
	 * @throws ExcepcionCapturarResultados
	 *             En caso de que el parámetro recibido contenga valores no válidos
	 *             para el marcador. Los casos no válidos son:
	 *             <ul>
	 *             <li>Si es un número negativo.</li>
	 *             <li>Si contiene caracteres no numéricos</li>
	 *             <li>Si son solo espacios en blanco o se encuentra vacío.</li>
	 *             </ul>
	 */
	public void setMarcadorParticipanteInicial(String marcadorParticipanteInicial) throws ExcepcionCapturarResultados {
		marcadorParticipanteInicial = marcadorParticipanteInicial.replaceAll(" ", "");
		if (!marcadorParticipanteInicial.isEmpty()) {
			try {
				int marcador = Integer.parseInt(marcadorParticipanteInicial);
				if (marcador >= 0) {
					this.marcadorParticipanteInicial = marcador;
				} else {
					throw new ExcepcionCapturarResultados(
							ExcepcionCapturarResultados.MENSAJE_EXCEPCION_NUMERO_NEGATIVO);
				}
			} catch (NumberFormatException e) {
				throw new ExcepcionCapturarResultados(
						ExcepcionCapturarResultados.MENSAJE_EXCEPCION_MARCADOR_INCORRECTO);
			}
		} else {
			throw new ExcepcionCapturarResultados(ExcepcionCapturarResultados.MENSAJE_EXCEPCION_MARCADOR_VACIO);
		}
	}

	/**
	 * Obtiene el marcador a favor del participante final del objeto
	 * <code>Encuentro</code>.
	 * 
	 * @return Regresa el marcador a favor del participante final.
	 */
	public int getMarcadorParticipanteFinal() {
		return marcadorParticipanteFinal;
	}

	/**
	 * Inicializa el marcador del participante final del objeto
	 * <code>Encuentro</code>. Recibe el marcador en forma de cadena y verifica si
	 * se trata de un valor válido para insertarlo en el marcador correspondiente.
	 * 
	 * @param marcadorParticipanteFinal
	 *            Recibe el marcador del participante final en forma de cadena.
	 * @throws ExcepcionCapturarResultados
	 *             En caso de que el parámetro recibido contenga valores no válidos
	 *             para el marcador. Los casos no válidos son:
	 *             <ul>
	 *             <li>Si es un número negativo.</li>
	 *             <li>Si contiene caracteres no numéricos</li>
	 *             <li>Si son solo espacios en blanco o se encuentra vacío.</li>
	 *             </ul>
	 */
	public void setMarcadorParticipanteFinal(String marcadorParticipanteFinal) throws ExcepcionCapturarResultados {
		marcadorParticipanteFinal = marcadorParticipanteFinal.replaceAll(" ", "");
		if (!marcadorParticipanteFinal.isEmpty()) {
			try {
				int marcador = Integer.parseInt(marcadorParticipanteFinal);
				if (marcador >= 0) {
					this.marcadorParticipanteFinal = marcador;
				} else {
					throw new ExcepcionCapturarResultados(
							ExcepcionCapturarResultados.MENSAJE_EXCEPCION_NUMERO_NEGATIVO);
				}
			} catch (NumberFormatException e) {
				throw new ExcepcionCapturarResultados(
						ExcepcionCapturarResultados.MENSAJE_EXCEPCION_MARCADOR_INCORRECTO);
			}
		} else {
			throw new ExcepcionCapturarResultados(ExcepcionCapturarResultados.MENSAJE_EXCEPCION_MARCADOR_VACIO);
		}
	}

	/**
	 * Obtiene el resultado del objeto <code>Encuentro</code>.
	 * 
	 * @return Regresa el resultado del encuentro. Existen tres valores posibles:
	 *         <ul>
	 *         <li>constante <code>GANADOR_INICIAL</code> si es ganador el
	 *         participante inicial.</li>
	 *         <li>constante <code>EMPATE</code> en caso de empatar ambos
	 *         participantes.</li>
	 *         <li>constante <code>GANADOR_FINAL</code> si es ganador el
	 *         participante final.</li>
	 *         </ul>
	 */
	public int getResultadoEncuentro() {
		return resultadoEncuentro;
	}

	/**
	 * Inicializa el resultado del objeto <code>Encuentro</code> con el valor
	 * recibido.
	 * 
	 * @param resultadoEncuentro
	 *            Recibe ell resultado del encuentro. Puede contener los valores
	 *            siguientes:
	 *            <ul>
	 *            <li>constante <code>GANADOR_INICIAL</code> si es ganador el
	 *            participante inicial.</li>
	 *            <li>constante <code>EMPATE</code> en caso de empatar ambos
	 *            participantes.</li>
	 *            <li>constante <code>GANADOR_FINAL</code> si es ganador el
	 *            participante final.</li>
	 *            <li>constante <code>SIN_JUGAR</code> si el encuentro no se ha
	 *            jugado.</li>
	 *            <li>constante <code>DESCANSO</code> si el jugador tiene
	 *            descanso.</li>
	 *            </ul>
	 */
	public void setResultadoEncuentro(int resultadoEncuentro) {
		this.resultadoEncuentro = resultadoEncuentro;
	}

	/**
	 * Obtiene la fecha del encuentro <code>Encuentro</code>.
	 * 
	 * @return Regresa el valor de la fecha del encuentro.
	 */
	public Date getFechaEncuentro() {
		return fechaDelEncuentro;
	}

	/**
	 * Inicializa el identificador del objeto <code>Encuentro</code> con el valor
	 * recibido.
	 * 
	 * @param fechaEncuentro
	 *            Recibe la fecha del encuentro.
	 */
	public void setFechaEncuentro(Date fechaEncuentro) {
		this.fechaDelEncuentro = fechaEncuentro;
	}

	/**
	 * Muestra el resultado de la partida como una cadena de texto.
	 * 
	 * @return Regresa el resultado del encuentro.
	 */
	@Override
	public String toString() {
		if (this.resultadoEncuentro == GANADOR_INICIAL) {
			return "[ganador - perdedor]";
		} else if (this.resultadoEncuentro == GANADOR_FINAL) {
			return "[perdedor - ganador]";
		} else if (this.resultadoEncuentro == EMPATE) {
			return "[empate]";
		} else if (this.resultadoEncuentro == SIN_JUGAR) {
			return "[sin jugar]";
		} else {
			return "[descanso]";
		}
	}
}