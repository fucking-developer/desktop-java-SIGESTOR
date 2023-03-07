package sigestor.dominio;

import java.text.Normalizer;

import sigestor.excepcion.ExcepcionParticipante;

/**
 * Sirve para guardar y validar la información ingresada en el
 * <code>PanelAdministrarParticipante</code>.
 * <p>
 * Las características de la clase <code>Participante</code> son:
 * <ul>
 * <li><code>numeroParticipante</code> Permite asignar un identificador al
 * participante.</li>
 * <li><code>nombreParticipante</code> Permite asignar un nombre al
 * participante.</li>
 * <li><code>puntajeParticipante</code> Permite asignar un puntaje al
 * participante.</li>
 * <li><code>puntajeAcumuladoParticipante</code> Permite llevar la suma de
 * puntajes al participante.</li>
 * <li><code>marcadorContra</code> Permite llevar la suma del marcador en contra
 * del participante.</li>
 * <li><code>marcadorFavor</code> Permite llevar la suma del marcador a favor
 * del participante.</li>
 * <li><code>puntaje</code> Permite asignar un dato booleano para saber si el
 * ordenamiento es por puntaje.</li>
 * <li><code>orden</code> Permite asignar un dato booleano para saber si el tipo
 * de ordenación es aleatorio o alfabético.</li>
 * <li><code>lugarParticipante</code> Permite asignar un valor numérico al
 * participante después de haber finalizado un torneo.</li>
 * </ul>
 * 
 * @version 07/04/2022
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * @author Francisco Samuel Reyes Cortes
 * @author Luis Antonio Ruiz Sierra
 * 
 * 
 */
public class Participante implements Comparable<Participante> {
	/**
	 * Número de participante.
	 */
	private int numeroParticipante;
	/**
	 * Nombre del participante.
	 */
	private String nombreParticipante;
	/**
	 * Puntaje del participante.
	 */
	private float puntajeParticipante;
	/**
	 * Puntaje que llevará acumulado el participante.
	 */
	private float puntajeAcumuladoParticipante;
	/**
	 * Suma del marcador en contra del participante.
	 */
	private int marcadorContra;
	/**
	 * Suma del marcador a favor del participante.
	 */
	private int marcadorFavor;
	/**
	 * Para ordenar a los participantes por puntaje.
	 */
	private static boolean puntaje;

	private static boolean puntajeAcumulado = false;

	public static boolean isPuntajeAcumulado() {
		return puntajeAcumulado;
	}

	public static void setPuntajeAcumulado(boolean puntajeAcumulado) {
		Participante.puntajeAcumulado = puntajeAcumulado;
	}

	/**
	 * Para ordenar a los participantes alfabética o aleatoriamente.
	 */
	private static boolean orden;

	/**
	 * Permite asignar un valor numérico al participante después de haber finalizado
	 * un torneo, si su valor es <tt> 0 </tt> significa que el torneo no ha
	 * finalizado.
	 */
	private int lugarParticipante;

	/**
	 * Permite crear un nuevo participante.
	 * 
	 */
	public Participante() {
		setNumeroParticipante(0);
		setNombreParticipante(null);
		setPuntajeParticipante(0.0f);
		setPuntajeAcumuladoParticipante(0.0f);
		setMarcadorContra(0);
		setMarcadorFavor(0);
		setLugarParticipante(0);
	}

	/**
	 * Devuelve el lugar que se le asignó al participante.
	 * 
	 * @return El lugar del participante, <tt> 0 </tt> en caso de que el torneo no
	 *         haya finalizado.
	 * 
	 */
	public int getLugarParticipante() {
		return lugarParticipante;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>lugarParticipante</code>.
	 * 
	 * @param lugarParticipante Recibe el lugar del participante que se le asigna al
	 *                          finalizar el torneo.
	 */
	public void setLugarParticipante(int lugarParticipante) {
		this.lugarParticipante = lugarParticipante;
	}

	/**
	 * 
	 * Permite crear un nuevo participante.
	 * 
	 * @param numeroParticipante  Recibe el número de participante.
	 * @param nombreParticipante  Recibe el nombre de participante.
	 * @param puntajeParticipante Recibe un puntaje para el participante.
	 */
	public Participante(int numeroParticipante, String nombreParticipante, float puntajeParticipante) {
		setNumeroParticipante(numeroParticipante);
		setNombreParticipante(nombreParticipante);
		setPuntajeParticipante(puntajeParticipante);
		setPuntajeAcumuladoParticipante(0.0f);
		setMarcadorContra(0);
		setMarcadorFavor(0);
	}

	/**
	 * Devuelve el número de participante.
	 * 
	 * @return Regresa el número del participante.
	 */
	public int getNumeroParticipante() {
		return numeroParticipante;
	}

	/**
	 * Asigna el parámetro recibido a la variable numeroParticipante.
	 * 
	 * @param numeroParticipante Recibe el número del participante.
	 */
	public void setNumeroParticipante(int numeroParticipante) {
		this.numeroParticipante = numeroParticipante;
	}

	/**
	 * Devuelve el nombre del participante.
	 * 
	 * @return Regresa el nombre del participante.
	 */
	public String getNombreParticipante() {
		return nombreParticipante;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>nombreParticipante</code> y
	 * elimina espacios si existen al inicio y al final de la cadena.
	 * 
	 * @param nombreParticipante Recibe el nombre del participante.
	 */
	public void setNombreParticipante(String nombreParticipante) {
		if (getNombreParticipante() != null) {
			this.nombreParticipante = nombreParticipante.trim();
		} else {
			this.nombreParticipante = nombreParticipante;
		}
	}

	/**
	 * Devuelve el puntaje del participante.
	 * 
	 * @return Regresa el puntaje del participante.
	 */
	public float getPuntajeParticipante() {
		return puntajeParticipante;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>puntajeParticipante</code>.
	 * 
	 * @param puntajeParticipante Recibe el puntaje del participante.
	 */
	public void setPuntajeParticipante(float puntajeParticipante) {
		this.puntajeParticipante = puntajeParticipante;
	}

	/**
	 * Devuelve el puntaje acumulado del participante.
	 * 
	 * @return Regresa el puntaje acumulado del participante.
	 */
	public float getPuntajeAcumuladoParticipante() {
		return puntajeAcumuladoParticipante;
	}

	/**
	 * Asigna el parámetro recibido a la variable
	 * <code>puntajeAcumuladoParticipante</code>.
	 * 
	 * @param puntajeAcumuladoParticipante Recibe la suma del puntaje acumulado del
	 *                                     participante.
	 */
	public void setPuntajeAcumuladoParticipante(float puntajeAcumuladoParticipante) {
		this.puntajeAcumuladoParticipante = puntajeAcumuladoParticipante;
	}

	/**
	 * Devuelve la suma del marcador en contra del participante.
	 * 
	 * @return Regresa suma del marcador en contra del participante.
	 */
	public int getMarcadorContra() {
		return marcadorContra;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>marcadorContra</code>.
	 * 
	 * @param marcadorContra Recibe la suma del marcador en contra del participante.
	 */
	public void setMarcadorContra(int marcadorContra) {
		this.marcadorContra = marcadorContra;
	}

	/**
	 * Devuelve la suma del marcador a favor del participante.
	 * 
	 * @return Regresa suma del marcador a favor del participante.
	 */
	public int getMarcadorFavor() {
		return marcadorFavor;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>marcadorFavor</code>.
	 * 
	 * @param marcadorFavor Recibe la suma del marcador a favor del participante.
	 */
	public void setMarcadorFavor(int marcadorFavor) {
		this.marcadorFavor = marcadorFavor;
	}

	/**
	 * Devuelve el tipo de ordenación por puntaje.
	 * 
	 * @return <tt>true</tt> si la ordenación de los participantes será por puntaje,
	 *         <tt>false</tt> en caso contrario.
	 * 
	 */
	public static boolean isPuntaje() {
		return puntaje;
	}

	/**
	 * Asigna el parámetro recibido a la variable puntaje.
	 * 
	 * @param puntaje Recibe <tt>true</tt> en caso de que la ordenación de los
	 *                participantes es por puntaje, <tt> false </tt> en caso
	 *                contrario.
	 */

	public static void setPuntaje(boolean puntaje) {
		Participante.puntaje = puntaje;
	}

	/**
	 * Devuelve el tipo de ordenación aleatorio o alfabético.
	 * 
	 * @return <tt>true</tt> en caso de que la ordenación sea alfabética,
	 *         <tt>false</tt> en caso de ser aleatoria.
	 */
	public static boolean isOrden() {
		return orden;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>orden</code>.
	 * 
	 * @param orden Recibe el tipo de ordenación de los participantes, <tt>true</tt>
	 *              en caso de que la ordenación sea alfabética, <tt>false</tt> en
	 *              caso de ser aleatoria.
	 */

	public static void setOrden(boolean orden) {
		Participante.orden = orden;
	}

	/**
	 * Permite representar a la clase con un identificador único.
	 * 
	 * @return Regresa solo el nombre del participante si no tiene un puntaje
	 *         asignado.
	 *         <p>
	 *         Regresa el nombre del participante y el puntaje del participante en
	 *         caso de tener un puntaje asignado.
	 */

	@Override
	public String toString() {
		if (this.puntajeParticipante == 0.0f) {
			return getNombreParticipante();
		} else {
			return getNombreParticipante() + " (" + puntajeParticipante + ")";
		}
	}

	/**
	 * Permite validar los datos ingresados en el <code>PanelParticipantes</code>
	 * 
	 * @throws ExcepcionParticipante Lanza la excepción si el nombre del
	 *                               participante está vacío.
	 */
	public void validarParticipante() throws ExcepcionParticipante {
		if (nombreParticipante.isEmpty()) {
			throw new ExcepcionParticipante(ExcepcionParticipante.MENSAJE_EXCEPCION_NOMBRE_VACIO);
		}
	}

	/**
	 * Permite acumular el puntaje para ser asignado a la variable
	 * <code>puntajeAcumuladoParticipante</code>.
	 * 
	 * @param valorAcumular Recibe un valor de tipo flotante que se sumará en
	 *                      <code>puntajeAcumuladoParticipante</code>.
	 */
	public void acumularPuntajeAcumuladoParticipante(float valorAcumular) {
		setPuntajeAcumuladoParticipante(getPuntajeAcumuladoParticipante() + valorAcumular);
	}

	/**
	 * Permite acumular el marcador en contra para ser asignado a la variable
	 * <code>marcadorContra</code>.
	 * 
	 * @param valorAcumular Recibe un valor de tipo entero que se sumará en
	 *                      <code>marcadorContra</code>.
	 */
	public void acumularMarcadorContra(int valorAcumular) {
		setMarcadorContra(getMarcadorContra() + valorAcumular);
	}

	/**
	 * Permite acumular el marcador a favor para ser asignado a la variable
	 * <code>marcadorFavor</code>.
	 * 
	 * @param valorAcumular Recibe un valor de tipo entero que se sumará en
	 *                      <code>marcadorFavor</code>.
	 */
	public void acumularMarcadorFavor(int valorAcumular) {
		setMarcadorFavor(getMarcadorFavor() + valorAcumular);

	}

	/**
	 * Compara y ordena al participante con el objeto recibido de acuerdo a los
	 * criterios de ordenación siguientes:
	 * <p>
	 * Si la variable <code>lugarParticipante</code> es diferente de <tt>0</tt>
	 * ordena a los participantes de acuerdo a su lugar asignado.
	 * <p>
	 * Si la variable <code>puntaje</code> es <tt>true</tt> compara el puntaje de
	 * los participantes y los ordena de esta manera.
	 * <p>
	 * Si la variable <code>orden</code> es <tt>true</tt> compara el nombre de los
	 * participantes y los ordena alfabéticamente.
	 * <p>
	 * Si la variable <code>orden</code> es <tt>false</tt> ordena a los
	 * participantes de forma aleatoria.
	 * 
	 * @param participante Recibe una variable de tipo Participante.
	 * @return Regresa cero cuando el participante es igual al objeto recibido.
	 *         <p>
	 *         Regresa un número menor a cero cuando el participante es menor al
	 *         objeto recibido.
	 *         <p>
	 *         Regresa un número mayor a cero cuando el participante es mayor al
	 *         objeto recibido.
	 * 
	 */
	public int compareTo(Participante participante) {
		if (participante.getLugarParticipante() != 0) {
			return (getLugarParticipante() - participante.getLugarParticipante());
		} else if (isPuntajeAcumulado()) {
			if (participante.getPuntajeAcumuladoParticipante() < this.getPuntajeAcumuladoParticipante()) {
				return -1;
			} else if (participante.getPuntajeAcumuladoParticipante() > getPuntajeAcumuladoParticipante()) {
				return 1;
			} else {
				return this.ordenPuntaje(participante);
			}
		} else if (isPuntaje()) {
			return this.ordenPuntaje(participante);
		} else if (isOrden()) {
			return this.ordenarAlfabetico(participante);
		} else {
			return (int) ((Math.random() * -10) + (Math.random() * 10));
		}
	}

	private int ordenPuntaje(Participante participante) {
		if (participante.getPuntajeParticipante() < getPuntajeParticipante()) {
			return -1;
		} else if (participante.getPuntajeParticipante() > getPuntajeParticipante()) {
			return 1;
		} else {
			if (isOrden()) {
				return this.ordenarAlfabetico(participante);
			} else {
				return (int) ((Math.random() * -10) + (Math.random() * 10));
			}
		}
	}

	private int ordenarAlfabetico(Participante participante) {
		String original1 = this.getNombreParticipante();
		String cadenaNormalize1 = Normalizer.normalize(original1, Normalizer.Form.NFD);
		String cadenaSinAcentos1 = cadenaNormalize1.replaceAll("[^\\p{ASCII}]", "");

		String original = participante.getNombreParticipante();
		String cadenaNormalize = Normalizer.normalize(original, Normalizer.Form.NFD);
		String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");

		return cadenaSinAcentos1.compareToIgnoreCase(cadenaSinAcentos);
	}
}
