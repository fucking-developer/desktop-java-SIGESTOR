package sigestor.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import sigestor.bd.BaseDatosCiclo;
import sigestor.bd.BaseDatosEncuentro;
import sigestor.bd.BaseDatosParticipante;
import sigestor.bd.BaseDatosTorneo;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosCiclo;
import sigestor.excepcion.ExcepcionBaseDatosEncuentro;
import sigestor.excepcion.ExcepcionBaseDatosParticipante;
import sigestor.excepcion.ExcepcionBaseDatosTorneo;
import sigestor.excepcion.ExcepcionCapturarResultados;

/**
 * <code>AlgoritmoTorneo</code> Sirve para realizar las operaciones del torneo,
 * como: crear los ciclos seg�n lo establecido en el sistema Eliminaci�n
 * directa, los encuentros seg�n el sistema Eliminaci�n directa, desempatar
 * jugadores y realizar reportes.
 * 
 * <p>
 * Las caracteristicas de la clase son:
 * <ul>
 * <li><code>esSimple</code> determina si el tipo de torneo es simple o
 * doble.</li>
 * </ul>
 * 
 * @version 16/03/2023
 * 
 * @author German Luis Cruz Martinez.
 * @author Eder Euclides Dionisio Diaz.
 * @see AlgoritmoTorneo
 */
public class TorneoEliminacionDirecta extends AlgoritmoTorneo {

	/**
	 * Sirve para determinar que tipo de torneo de Eliminaci�n Directa se va a
	 * efectuar true = Eliminaci�n simple o directa false = eliminaci�n doble
	 */
	private boolean esSimple;

	/**
	 * Inicializa las variables con un valor por defecto y asigna a la variable
	 * <code>torneo</code> el torneo recibido.
	 * 
	 * @param torneo Contiene los datos generales del torneo.
	 */
	public TorneoEliminacionDirecta(Torneo torneo) {
		super(torneo);
	}

	/*
	 * obtiene el tipo de Eliminaci�n Directa
	 * 
	 * @param true = Eliminaci�n Directa simple false = Eliminaci�n Directa doble
	 */

	private void setTipoEliminacion(boolean tipoEliminacion) {
		this.esSimple = tipoEliminacion;
	}

	/**
	 * Obtiene la cantidad m�xima de ciclos del torneo seg�n el algoritmo general
	 * del sistema Eliminaci�n Directa.
	 * 
	 * @param numeroParticipantes Cantidad de participantes inscritos en el torneo.
	 * @return El n�mero m�ximo de ciclos que tendr� el torneo.
	 */
	@Override
	public int calcularNumeroCiclos(int numeroParticipantes) {
		if (esSimple) {
			return (int) (Math.ceil(Math.log(numeroParticipantes) / Math.log(2)));
		} else {
			return ((int) (Math.ceil(Math.log(numeroParticipantes) / Math.log(2)))) * 2;
		}
	}

	/**
	 * Genera inicialmente los encuentros del primer ciclo y despu�s los encuentros
	 * del siguiente ciclo una vez terminado el ciclo anterior.
	 */
	@Override
	public void realizarEncuentros() throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo {
		Date fechaEncuentro;

	}

	/**
	 * Aplica el/los criterio(s) de desempate(s) establecidos en
	 * <code>CriteriosDesempate</code>.
	 */
	@Override
	public void desempatarParticipantes() {

	}

	/**
	 * Realiza los encuentros del primer ciclo.
	 * 
	 * @param ciclo Ciclo a realizar.
	 * @throws ExcepcionCapturarResultados    Si ocurre un error con el objeto
	 *                                        <code>Encuentros</code>.
	 * @throws ExcepcionBaseDatos             Si ocurre un problema con la base de
	 *                                        datos.
	 * @throws ExcepcionBaseDatosEncuentro    Si ocurre un problema al insertar en
	 *                                        la tabla <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosParticipante Si ocurre un problema al actualizar un
	 *                                        participante en la tabla
	 *                                        <code>participantes</code>.
	 */
	public void encararParticipantes(Ciclo ciclo) throws ExcepcionCapturarResultados, ExcepcionBaseDatos,
			ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosParticipante {

	}

	/**
	 * Verifica si se han capturado todos los encuentros del ciclo.
	 * 
	 * @return <tt>true</tt> Si se han capturado todos los encuentros de un ciclo,
	 *         <tt>false</tt> de lo contrario.
	 */
	public boolean verificarEncuentros() {
		return true;
	}

	/**
	 * Inicia un torneo Eliminaci�n Directa e inserta el numero de ciclos en la
	 * tabla <code>suizo</code>.
	 * 
	 * @param torneoEliminacionDirecta Recibe el objeto
	 *                                 <code>TorneoEliminacionDirecta</code>. para
	 *                                 iniciar el torneo.
	 * @throws ExcepcionBaseDatos          Si ocurre un problema con la base de
	 *                                     datos.
	 * @throws ExcepcionBaseDatosEncuentro Si ocurre un problema al insertar en la
	 *                                     tabla <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosCiclo     Si ocurre un error al insertar en la
	 *                                     tabla <code>ciclos</code>.
	 */
	public void iniciarTorneo(TorneoEliminacionDirecta torneoEliminacionDirecta)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo {

	}

}