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

public class TorneoEliminacionDirecta extends AlgoritmoTorneo {

	/*
	 * Sirve para determinar que tipo de torneo de eliminación directa se va a
	 * efectuar
	 * true = Eliminación simple o directa
	 * false = eliminación doble
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
	 * obtiene el tipo de eliminación 
	 * @param true = Eliminación simple o directa
	 * 		  false = eliminación doble
	 */
	
	private void setTipoEliminacion(boolean tipoEliminacion) {
		this.esSimple = tipoEliminacion;
	}

	/**
	 * Obtiene la cantidad máxima de ciclos del torneo según el algoritmo
	 * general del sistema suizo.
	 * 
	 * @param numeroParticipantes
	 *            Cantidad de participantes inscritos en el torneo.
	 * @return El número máximo de ciclos que tendrá el torneo.
	 */
	@Override
	public int calcularNumeroCiclos(int numeroParticipantes) {
		return numeroParticipantes;
	
	}

	/**
	 * Genera inicialmente los encuentros del primer ciclo y después los
	 * encuentros del siguiente ciclo una vez terminado el ciclo anterior.
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
	 *             Si ocurre un problema al actualizar un participante en la
	 *             tabla <code>participantes</code>.
	 */
	public void encararParticipantes(Ciclo ciclo) throws ExcepcionCapturarResultados, ExcepcionBaseDatos,
			ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosParticipante {
	
	}

	/**
	 * Verifica si se han capturado todos los encuentros del ciclo.
	 * 
	 * @return <tt>true</tt> Si se han capturado todos los encuentros de un
	 *         ciclo, <tt>false</tt> de lo contrario.
	 */
	public boolean verificarEncuentros() {
		return true;
	}

	/**
	 * Inicia un torneo suizo e inserta el numero de ciclos en la tabla
	 * <code>suizo</code>.
	 * 
	 * @param torneoSuizo
	 *            Recibe el objeto <code>TorneoSuizo</code>. para iniciar el torneo.
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