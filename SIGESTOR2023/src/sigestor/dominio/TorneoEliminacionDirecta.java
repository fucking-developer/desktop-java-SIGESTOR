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
 * como: crear los ciclos según lo establecido en el sistema Eliminación
 * directa, los encuentros según el sistema Eliminación directa, desempatar
 * jugadores y realizar reportes.
 * 
 * <p>
 * Las caracteristicas de la clase son:
 * <ul>
 * <li><code>esSimple</code> determina si el tipo de torneo es simple o
 * doble.</li>
 * </ul>
 * 
 * @version 27/03/2023
 * 
 * @author German Luis Cruz Martinez.
 * @author Eder Euclides Dionisio Diaz.
 * @see AlgoritmoTorneo
 */
public class TorneoEliminacionDirecta extends AlgoritmoTorneo {

	/**
	 * Sirve para determinar que tipo de torneo de Eliminación Directa se va a
	 * efectuar true = Eliminación simple o directa false = eliminación doble
	 */
	private boolean esSimple;

	/**
	 * Inicializa las variables con un valor por defecto y asigna a la variable
	 * <code>torneo</code> el torneo recibido.
	 * 
	 * @param torneo
	 *            Contiene los datos generales del torneo.
	 */
	public TorneoEliminacionDirecta(Torneo torneo) {
		super(torneo);
	}

	/*
	 * obtiene el tipo de Eliminación Directa
	 * 
	 * @param true = Eliminación Directa simple false = Eliminación Directa doble
	 */

	public void setTipoEliminacion(boolean tipoEliminacion) {
		this.esSimple = tipoEliminacion;
	}

	/**
	 * Obtiene la cantidad máxima de ciclos del torneo según el algoritmo general
	 * del sistema Eliminación Directa.
	 * 
	 * @param numeroParticipantes
	 *            Cantidad de participantes inscritos en el torneo.
	 * @return El número máximo de ciclos que tendrá el torneo.
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
	 * Genera inicialmente los encuentros del primer ciclo y después los encuentros
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
	 * @param ciclo
	 *            Ciclo a realizar.
	 * @throws ExcepcionCapturarResultados
	 *             Si ocurre un error con el objeto <code>Encuentros</code>.
	 * @throws ExcepcionBaseDatos
	 *             Si ocurre un problema con la base de datos.
	 * @throws ExcepcionBaseDatosEncuentro
	 *             Si ocurre un problema al insertar en la tabla
	 *             <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosParticipante
	 *             Si ocurre un problema al actualizar un participante en la tabla
	 *             <code>participantes</code>.
	 */
	public void encararParticipantes(Ciclo ciclo) throws ExcepcionCapturarResultados, ExcepcionBaseDatos,
	ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosParticipante {
		BaseDatosEncuentro bde = new BaseDatosEncuentro(torneo.getNombreArchivo());
		BaseDatosParticipante bdp = new BaseDatosParticipante(torneo.getNombreArchivo());
		ArrayList<Participante> participantes = torneo.getListaParticipantes();
		int totalParticipantes = participantes.size();
		int descansos = 0;
		if (!esPotenciaDeDos(totalParticipantes)) {
			descansos = (int) (Math.pow(2, calcularNumeroCiclos(totalParticipantes))) - totalParticipantes;
		}
		int mitad = participantes.size() / 2;

		ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
		for (int i = 1; i <= mitad; i++) {
			if (descansos == 0) {
				encuentros.add(new Encuentro(i, participantes.get(i - 1).getNumeroParticipante(),
						participantes.get(participantes.size() - i).getNumeroParticipante(),
						this.getTorneo().getFechaInicioTorneo()));
				bde.insertarEncuentro(encuentros.get(i - 1), ciclo);
				bdp.actualizarResultadoParticipante(participantes.get(i - 1), ciclo);
				bdp.actualizarResultadoParticipante(participantes.get(i + mitad - 1), ciclo);
			} else {
				descansos--;
			}

		}
		ciclo.setEncuentroParticipantes(encuentros);
	}

	/**
	 * Calcula por medio de recursividad si el número dado es potencia de dos
	 * 
	 * @param numero
	 *            numero a evaluar si es potencia de dos.
	 * 
	 * @return True si el número es potencia de dos, False si no es potencia de dos
	 * 
	 */
	public boolean esPotenciaDeDos(double numero) {
		if (numero == 1) {
			return true;
		} else if (numero > 1 && numero < 2) {
			return false;
		} else if (numero >= 2) {
			return esPotenciaDeDos(numero / 2);
		}
		return false;
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
	 * Inicia un torneo Eliminación Directa e inserta el numero de ciclos en la
	 * tabla <code>suizo</code>.
	 * 
	 * @param torneoEliminacionDirecta
	 *            Recibe el objeto <code>TorneoEliminacionDirecta</code>. para
	 *            iniciar el torneo.
	 * @throws ExcepcionBaseDatos
	 *             Si ocurre un problema con la base de datos.
	 * @throws ExcepcionBaseDatosEncuentro
	 *             Si ocurre un problema al insertar en la tabla
	 *             <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosCiclo
	 *             Si ocurre un error al insertar en la tabla <code>ciclos</code>.
	 */
	public void iniciarTorneo(TorneoEliminacionDirecta torneoEliminacionDirecta)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo {

	}

}