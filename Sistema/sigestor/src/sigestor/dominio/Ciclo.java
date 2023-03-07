package sigestor.dominio;

import java.util.ArrayList;

import sigestor.bd.BaseDatosCiclo;
import sigestor.bd.BaseDatosEncuentro;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosCiclo;
import sigestor.excepcion.ExcepcionBaseDatosEncuentro;

/**
 * Sirve para establecer los pareos que tendrá cada ciclo del torneo.
 * <p>
 * Las características de la clase <code>Ciclo</code> son:
 * <ul>
 * <li><code>numeroCiclo</code> Guarda el identificador para el ciclo.</li>
 * <li><code>encuentroParticipantes</code> Guarda los encuentros de un
 * ciclo.</li>
 * <li><code>torneo</code> Almacena los datos generales del torneo.</li>
 * </ul>
 * 
 * @version 24/05/2022
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Hernán Sesaí Lopéz Aragón
 * @author Francisco Samuel Reyes Cortes
 */
public class Ciclo {

	/**
	 * Sirve como identificador del ciclo.
	 */
	private int numeroCiclo;
	/**
	 * Sirve par guardar los encuentros entre los participantes que habrá en
	 * cada ciclo.
	 */
	private ArrayList<Encuentro> encuentroParticipantes;
	/**
	 * Sirve para acceder a la lista de participantes y al nombre personalizado
	 * para ciclo que se encuentra en la clase <code>Torneo</code>.
	 */
	private Torneo torneo;

	/**
	 * Inicializa las variables con los valores recibidos.
	 * 
	 * @param torneo
	 *            Contiene la lista de participantes y al nombre personalizado
	 *            para ciclo.
	 * @param numeroCiclo
	 *            Contiene el identificador para el ciclo.
	 */
	public Ciclo(Torneo torneo, int numeroCiclo) {
		this.setTorneo(torneo);
		this.setNumeroCiclo(numeroCiclo);
	}

	/**
	 * Devuelve el identificador del ciclo.
	 * 
	 * @return Identificador para el ciclo.
	 */
	public int getNumeroCiclo() {
		return numeroCiclo;
	}

	/**
	 * Inicializa la variable miembro <code>numeroCiclo</code>.
	 * 
	 * @param numeroCiclo
	 *            Identificador para el ciclo.
	 */
	public void setNumeroCiclo(int numeroCiclo) {
		this.numeroCiclo = numeroCiclo;
	}

	/**
	 * Devuelve los encuentros que tendrá un ciclo.
	 * 
	 * @return Colección con los encuentros de un ciclo.
	 */
	public ArrayList<Encuentro> getEncuentroParticipantes() {
		return encuentroParticipantes;
	}

	/**
	 * Asigna los encuentros de un ciclo a la colección
	 * <code>encuentroParticipantes</code>.
	 * 
	 * @param encuentroParticipantes
	 *            Encuentros de un ciclo determinado.
	 */
	public void setEncuentroParticipantes(ArrayList<Encuentro> encuentroParticipantes) {
		this.encuentroParticipantes = encuentroParticipantes;
	}

	/**
	 * Regresa el torneo con todos sus atributos y sus valores asignados.
	 * 
	 * @return Variable de tipo <code>Torneo</code> con los datos del torneo.
	 */
	public Torneo getTorneo() {
		return torneo;
	}

	/**
	 * Inicializa la variable <code>torneo</code> con el objeto recibido.
	 * 
	 * @param torneo
	 *            Variable con los datos del torneo.
	 */
	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	/**
	 * Guarda los encuentros correspondientes de cada ciclo.
	 * 
	 * @param ciclo
	 *            Recibe el objeto <code>Ciclo</code>.
	 * @param nombreArchivo
	 *            Recibe el nombre del archivo.
	 * @throws ExcepcionBaseDatos
	 *             Si ocurre un problema con la base de datos.
	 * @throws ExcepcionBaseDatosEncuentro
	 *             Si ocurre un problema al insertar en la tabla
	 *             <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosCiclo
	 *             Si ocurre un error al insertar en la tabla
	 *             <code>ciclos</code>.
	 */
	public void guardarCiclo(Ciclo ciclo, String nombreArchivo)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo {
		BaseDatosEncuentro bd = new BaseDatosEncuentro(nombreArchivo);
		BaseDatosCiclo bdc = new BaseDatosCiclo(nombreArchivo);

		bdc.insertarCiclo(ciclo);
		for (int i = 0; i < (getTorneo().getListaParticipantes().size() / 2); i++) {
			bd.insertarEncuentro(this.getEncuentroParticipantes().get(i), ciclo);
		}
	}

	/**
	 * Muestra el nombre personalizado de ciclo seguido del número de ciclo.
	 * 
	 * @return Nombre personalizado de ciclo y el número de ciclo.
	 */
	@Override
	public String toString() {
		return torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MAYUSCULA_SINGULAR) + " "
				+ this.getNumeroCiclo();
	}
}
