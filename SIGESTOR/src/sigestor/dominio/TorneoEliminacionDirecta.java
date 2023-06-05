package sigestor.dominio;

import java.util.ArrayList;
import java.util.Collections;
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
 * @version 05/06/2023
 * 
 * @author German Luis Cruz Martinez.
 * @author Eder Euclides Dionisio Diaz.
 * @author Erik Vasquez Policarpo
 * @author Jonathan Eduardo Ibarra Martínez
 * 
 * @see AlgoritmoTorneo
 */
public class TorneoEliminacionDirecta extends AlgoritmoTorneo {

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
		if (this.getTipoEliminacion()) {
			return (int) (Math.ceil(Math.log(numeroParticipantes) / Math.log(2)));
		} else {
			return ((int) (Math.ceil(Math.log(numeroParticipantes) / Math.log(2)))) * 2;
		}
	}

	/**
	 * Genera inicialmente los encuentros del primer ciclo y después los encuentros
	 * del siguiente ciclo una vez terminado el ciclo anterior. //FIXME agregar
	 * documentación
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción si ocurre un error al realizar las conexiones
	 *             de base de datos.
	 * @throws ExcepcionBaseDatosEncuentro
	 *             Lanza la excepción si ocurre un error al obtener u agregar los
	 *             encuentros.
	 * @throws ExcepcionBaseDatosCiclo
	 *             Lanza la excepción si ocurre un error al obtener u agregar los
	 *             ciclo.
	 * 
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error al obtener los datos del
	 *             torneo.
	 * @throws ExcepcionCapturarResultados
	 *             Lanza la excepción si ocurre un error al capturar los resultados.
	 */
	public void realizarEncuentros() throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo,
			ExcepcionBaseDatosTorneo, ExcepcionCapturarResultados {

		try {
			if (torneo.getCicloActual() < torneo.getAlgoritmoTorneo().getNumeroCiclos()) {
				torneo.setCicloActual(torneo.getCicloActual() + 1);
				BaseDatosCiclo bdc = new BaseDatosCiclo(torneo.getNombreArchivo());

				Ciclo ciclo = new Ciclo(torneo, torneo.getCicloActual());
				bdc.insertarCiclo(ciclo);
				if (torneo.getCicloActual() > 1) {
					encararParticipantesCiclosPosteriores(ciclo);
				} else {
					encararParticipantesPrimerCiclo(ciclo);
				}
				torneo.getAlgoritmoTorneo().getCiclos().add(ciclo);
				BaseDatosTorneo bdt = new BaseDatosTorneo(torneo.getNombreArchivo());
				bdt.actualizarCicloActual(torneo);
			}
		} catch (ExcepcionBaseDatosParticipante e) {
			e.printStackTrace();
		}

	}

	/**
	 * Intercambia las posiciones de los 2 jugadores empatados, si el ganador está
	 * una posición abajo del jugador con quien empate, de lo contrario no realiza
	 * ningún movimiento.
	 * 
	 * @param numeroP1
	 *            Primer participante empatado.
	 * @param numP2
	 *            Segundo participante empatado.
	 * @param numPGanador
	 *            Participante que obtuvo más puntaje con el criterio de desempate
	 *            aplicado.
	 * @return Lista de participantes ordenada.
	 */
	/*
	 * private ArrayList<Participante> intercambiarPosiciones(int numeroP1, int
	 * numP2, int numPGanador) { ArrayList<Participante> participantes =
	 * torneo.getListaParticipantes();
	 * 
	 * boolean encontrado = false; int index = 0;
	 * 
	 * for (Participante p : participantes) { if (numeroP1 ==
	 * p.getNumeroParticipante() || numP2 == p.getNumeroParticipante()) { if
	 * (encontrado && p.getNumeroParticipante() == numPGanador) {
	 * Collections.swap(participantes, index, index - 1); } encontrado = true; }
	 * index++; } return participantes; }
	 */

	/**
	 * Aplica el/los criterio(s) de desempate(s) establecidos en
	 * <code>CriteriosDesempate</code>.
	 */

	@Override
	public void desempatarParticipantes() {
		/*
		 * Desempate desempate; Participante participanteGanador; ArrayList<String>
		 * criterios = torneo.getCriteriosDesempate().getListaCriteriosSeleccionados();
		 * 
		 * ArrayList<Participante> participantes = torneo.getListaParticipantes(); for
		 * (Participante p1 : participantes) { for (Participante p2 : participantes) {
		 * if (p1.getPuntajeAcumuladoParticipante() ==
		 * p2.getPuntajeAcumuladoParticipante()) { cicloromper: { for (String criterio :
		 * criterios) { switch (criterio) { case "Encuentro directo": desempate = new
		 * DesempateEncuentroDirecto(); participanteGanador = desempate.desempatar(p1,
		 * p2, participantes, obtenerEncuentrosTotales(), torneo); if
		 * (participanteGanador != null) { participantes =
		 * intercambiarPosiciones(p1.getNumeroParticipante(),
		 * p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
		 * break cicloromper; } break; case "Sistema Koya": desempate = new
		 * DesempateSistemaKoya(); participanteGanador = desempate.desempatar(p1, p2,
		 * participantes, obtenerEncuentrosTotales(), torneo); if (participanteGanador
		 * != null) { participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
		 * p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
		 * break cicloromper; } break; case "Buchholz": desempate = new
		 * DesempateBuchholz(); participanteGanador = desempate.desempatar(p1, p2,
		 * participantes, obtenerEncuentrosTotales(), torneo); if (participanteGanador
		 * != null) { participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
		 * p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
		 * break cicloromper; } break; case "Sonnerborn-Berger": desempate = new
		 * DesempateSonnebornBerger(); participanteGanador = desempate.desempatar(p1,
		 * p2, participantes, obtenerEncuentrosTotales(), torneo); if
		 * (participanteGanador != null) { participantes =
		 * intercambiarPosiciones(p1.getNumeroParticipante(),
		 * p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
		 * break cicloromper; } break; case "Encuentros ganados": desempate = new
		 * DesempateEncuentrosGanados(); participanteGanador = desempate.desempatar(p1,
		 * p2, participantes, obtenerEncuentrosTotales(), torneo); if
		 * (participanteGanador != null) { participantes =
		 * intercambiarPosiciones(p1.getNumeroParticipante(),
		 * p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
		 * break cicloromper; } break; case "Diferencia de marcadores": desempate = new
		 * DesempateDiferenciaMarcadores(); participanteGanador =
		 * desempate.desempatar(p1, p2, participantes, obtenerEncuentrosTotales(),
		 * torneo); if (participanteGanador != null) { participantes =
		 * intercambiarPosiciones(p1.getNumeroParticipante(),
		 * p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
		 * break cicloromper; } break; case "Marcador a favor": desempate = new
		 * DesempateMarcadorFavor(); participanteGanador = desempate.desempatar(p1, p2,
		 * participantes, obtenerEncuentrosTotales(), torneo); if (participanteGanador
		 * != null) { participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
		 * p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
		 * break cicloromper; } break; case "Marcador en contra": desempate = new
		 * DesempateMarcadorContra(); participanteGanador = desempate.desempatar(p1, p2,
		 * participantes, obtenerEncuentrosTotales(), torneo); if (participanteGanador
		 * != null) { participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
		 * p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
		 * break cicloromper; } break; default: // no se ha seleccionado ningún criterio
		 * }
		 * 
		 * } } } } } torneo.setListaParticipantes(participantes);
		 */
	}

	/**
	 * Obtiene todos los encuentros que ya hayan sido jugados en el torneo.
	 * 
	 * @return lista de encuentros jugados.
	 */
	/*
	 * private ArrayList<Encuentro> obtenerEncuentrosTotales() {
	 * ArrayList<Encuentro> encuentrosTotales = new ArrayList<Encuentro>();
	 * 
	 * ArrayList<Ciclo> ciclos = torneo.getAlgoritmoTorneo().getCiclos(); for (Ciclo
	 * ciclo : ciclos) { ArrayList<Encuentro> encuentros =
	 * ciclo.getEncuentroParticipantes(); for (Encuentro encuentro : encuentros) {
	 * encuentrosTotales.add(encuentro); } } return encuentrosTotales; }
	 */

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
	private void encararParticipantesPrimerCiclo(Ciclo ciclo) throws ExcepcionCapturarResultados, ExcepcionBaseDatos,
			ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosParticipante {
		BaseDatosEncuentro bde = new BaseDatosEncuentro(torneo.getNombreArchivo());
		BaseDatosParticipante bdp = new BaseDatosParticipante(torneo.getNombreArchivo());

		ArrayList<Participante> participantes = torneo.getListaParticipantes();
		int totalParticipantes = participantes.size();
		int descansosPrimeraVuelta = 0;
		int descansosSegundaVuelta = 0;

		int auxUltimaPosicion = 0;
		int auxUltimaPosicionSegundaVuelta = 0;
		// día 13/05/2023 me dedicaré a revisar el algoritmos para los torneo simples
		// 06:04 p.m.-------------------------------------------------------------
		// día 31/05/2023 me quiero lanzar de un puente :c 01:27 a.m.

		if (torneo.getAlgoritmoTorneo().getTipoEliminacion()) {
			if (!esPotenciaDeDos(totalParticipantes)) {
				// System.Out.println(participantes);
				// System.Out.println("Total de participantes: " + totalParticipantes);
				// System.Out.println("Numero de ciclos: " +
				// calcularNumeroCiclos(totalParticipantes));

				descansosPrimeraVuelta = (int) (Math.pow(2, calcularNumeroCiclos(totalParticipantes)))
						- totalParticipantes;

			}
			int numeroDeEncuentrosSimple = ((participantes.size() - descansosPrimeraVuelta) / 2)
					+ descansosPrimeraVuelta;
			// System.Out.println("Numero de encuentros: " + numeroDeEncuentrosSimple);
			// System.Out.println("Numero de descansos = " + descansosPrimeraVuelta);
			ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
			for (int i = 1; i <= numeroDeEncuentrosSimple; i++) {
				if (!(descansosPrimeraVuelta > 0)) {
					// System.Out.println("i = " + i);
					encuentros.add(new Encuentro(auxUltimaPosicion + 1,
							participantes.get(i - 1).getNumeroParticipante(),
							participantes.get((participantes.size() - 1) - auxUltimaPosicion).getNumeroParticipante(),
							this.getTorneo().getFechaInicioTorneo()));

					// System.Out.println(encuentros);
					/*
					 * bdp.actualizarResultadoParticipante=
					 * realizarAccion("UPDATE participante SET marcadorFavor = " +
					 * participante.getMarcadorFavor() + ", marcadorContra = " +
					 * participante.getMarcadorContra() + ", puntajeAcumuladoParticipante = " +
					 * participante.getPuntajeAcumuladoParticipante() +
					 * " WHERE numeroParticipante = " + participante.getNumeroParticipante());
					 */
					bdp.actualizarResultadoParticipante(participantes.get(i - 1), ciclo);
					bdp.actualizarResultadoParticipante(
							participantes.get((participantes.size() - 1) - auxUltimaPosicion), ciclo);
					auxUltimaPosicion++;
					bde.insertarEncuentro(encuentros.get((auxUltimaPosicion) - 1), ciclo);

				}
				descansosPrimeraVuelta--;
			}
			ciclo.setEncuentroParticipantes(encuentros);
			// -----------------------------------------------------------------------------------------------------------------------------------------------------
		} else {
			if (!esPotenciaDeDos(totalParticipantes)) {
				descansosPrimeraVuelta = (int) (Math.pow(2, calcularNumeroCiclos(totalParticipantes)))
						- totalParticipantes;
				descansosSegundaVuelta = descansosPrimeraVuelta;

			}
			int numeroDeEncuentrosDoble = ((participantes.size() - descansosPrimeraVuelta) / 2)
					+ descansosPrimeraVuelta;

			ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
			for (int i = 1; i <= numeroDeEncuentrosDoble; i++) {
				if (!(descansosPrimeraVuelta > 0)) {

					encuentros.add(new Encuentro(auxUltimaPosicion + 1,
							participantes.get(i - 1).getNumeroParticipante(),
							participantes.get((participantes.size() - 1) - auxUltimaPosicion).getNumeroParticipante(),
							this.getTorneo().getFechaInicioTorneo()));

					bdp.actualizarResultadoParticipante(participantes.get((i - 1)), ciclo);
					bdp.actualizarResultadoParticipante(
							participantes.get((participantes.size() - 1) - auxUltimaPosicionSegundaVuelta), ciclo);
					auxUltimaPosicion++;
					auxUltimaPosicionSegundaVuelta++;
					bde.insertarEncuentro(encuentros.get((auxUltimaPosicionSegundaVuelta) - 1), ciclo);
				} else {
					descansosPrimeraVuelta--;
				}

			}
			auxUltimaPosicion = 0;
			/*
			 * for (int i = 1; i <= numeroDeEncuentrosDoble; i++) { if
			 * (!(descansosSegundaVuelta > 0)) {
			 * 
			 * encuentros.add(new Encuentro(auxUltimaPosicionSegundaVuelta + 1,
			 * participantes.get((participantes.size() - 1) -
			 * auxUltimaPosicion).getNumeroParticipante(), participantes.get(i -
			 * 1).getNumeroParticipante(), this.getTorneo().getFechaInicioTorneo()));
			 * 
			 * bdp.actualizarResultadoParticipante(participantes.get((i - 1)), ciclo);
			 * bdp.actualizarResultadoParticipante( participantes.get((participantes.size()
			 * - 1) - auxUltimaPosicion), ciclo); auxUltimaPosicion++;
			 * auxUltimaPosicionSegundaVuelta++;
			 * bde.insertarEncuentro(encuentros.get((auxUltimaPosicionSegundaVuelta) - 1),
			 * ciclo); } else { descansosSegundaVuelta--; }
			 * 
			 * }
			 */
			ciclo.setEncuentroParticipantes(encuentros);
		}

	}

	/**
	 * Realiza los encuentros de un ciclo posterior al primer ciclo.
	 * 
	 * @param ciclo
	 *            Recibe el objeto <code>Ciclo</code> para guardar los encuentros a
	 *            realizar.
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
	 * @throws ExcepcionBaseDatosCiclo
	 *             Si ocurre un problema al insertar en la tabla <code>ciclo</code>
	 * @throws ExcepcionBaseDatosTorneo
	 *             Si ocurre un problema en la base de datos torneo.
	 */
	private void encararParticipantesCiclosPosteriores(Ciclo ciclo)
			throws ExcepcionCapturarResultados, ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro,
			ExcepcionBaseDatosParticipante, ExcepcionBaseDatosCiclo, ExcepcionBaseDatosTorneo {

		BaseDatosEncuentro bde = new BaseDatosEncuentro(torneo.getNombreArchivo());
		BaseDatosParticipante bdp = new BaseDatosParticipante(torneo.getNombreArchivo());
		BaseDatosCiclo bdc = new BaseDatosCiclo(torneo.getNombreArchivo());

		ArrayList<Participante> participantes = torneo.getListaParticipantes();
		ArrayList<Participante> participantesCiclo = new ArrayList<Participante>();
		ArrayList<Encuentro> encuentrosParticipante = bde
				.obtenerEncuentros(bdc.obtenerCiclos(torneo).get(torneo.getCicloActual() - 2));

		// System.Out.println("participantes");
		Collections.sort(participantes);
		// System.Out.println(participantes);
		// System.Out.println("encuentros");
		// System.Out.println(encuentrosParticipante);
		// FIXME aqui no entra
		for (Encuentro encuentro : encuentrosParticipante) {
			// System.Out.println(encuentro);
			// System.Out.println("id participante inicial: " +
			// (encuentro.getIdParticipanteInicial() - 1));
			// System.Out.println("id participante final: " +
			// (encuentro.getIdParticipanteFinal() - 1));
			// ------------------------------------------------------------------------------------------------
			Participante partInicial = participantes.get((encuentro.getIdParticipanteInicial() - 1));
			// System.Out.println("Participante incial: " + partInicial + " puntaje
			// acumulado: "
			// + partInicial.getPuntajeAcumuladoParticipante());
			Participante partFinal = participantes.get((encuentro.getIdParticipanteFinal() - 1));
			// System.Out.println("Participante final: " + partFinal + " puntaje acumulado:
			// "
			// + partFinal.getPuntajeAcumuladoParticipante());
			// ---------------------------------------------------------------------------------------------------------
			if (torneo.getAlgoritmoTorneo().getTipoEliminacion()) {
				if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_INICIAL || partInicial
						.getPuntajeAcumuladoParticipante() > partFinal.getPuntajeAcumuladoParticipante()) {
					// FIXME eliminar
					// System.Out.println("Id de participante descalificado: " +
					// (encuentro.getIdParticipanteFinal() - 1));
					partFinal.setLugarParticipante(100);
					bdp.actualizarLugarParticipante(partFinal, torneo);
				} else if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_FINAL || partInicial
						.getPuntajeAcumuladoParticipante() < partFinal.getPuntajeAcumuladoParticipante()) {
					// FIXME eliminar
					// System.Out
					// .println("Id de participante descalificado: " +
					// (encuentro.getIdParticipanteInicial() - 1));
					partInicial.setLugarParticipante(100);
					bdp.actualizarLugarParticipante(partFinal, torneo);
				} else if (encuentro.getResultadoEncuentro() == Encuentro.EMPATE || partInicial
						.getPuntajeAcumuladoParticipante() == partFinal.getPuntajeAcumuladoParticipante()) {
					/*
					 * cicloromper: { for (String criterio : criterios) { Desempate desempate = new
					 * DesempatePuntuacion(); Participante participantePerdedor = new
					 * Participante(); switch (criterio) { case "Puntuación":
					 * //System.Out.println("Tipo de desempate: " + criterio); desempate = new
					 * DesempatePuntuacion(); participantePerdedor = desempate.desempatar(
					 * participantes.get(encuentro.getIdParticipanteInicial()),
					 * participantes.get(encuentro.getIdParticipanteFinal()), participantes,
					 * obtenerEncuentrosTotales(), torneo); if (participantePerdedor != null) {
					 * participantes.get(participantePerdedor.getNumeroParticipante())
					 * .setLugarParticipante(100); //System.Out.println(
					 * "Participante perdedor mediante el metodo de eliminacion por puntuacion: " +
					 * participantePerdedor.getNumeroParticipante()); break cicloromper; }
					 * //System.Out.
					 * println("Ningun participante eliminado por el metodo de puntuación"); break;
					 * case "Marcador de participante final":
					 * //System.Out.println("Tipo de desempate: " + criterio); if (esSimple) { //
					 * nada } else { desempate = new DesempatePuntuacion(); participantePerdedor =
					 * desempate.desempatar(
					 * participantes.get(encuentro.getIdParticipanteInicial()),
					 * participantes.get(encuentro.getIdParticipanteFinal()), participantes,
					 * obtenerEncuentrosTotales(), torneo); if (participantePerdedor != null) {
					 * participantes.get(participantePerdedor.getNumeroParticipante())
					 * .setLugarParticipante(100); //System.Out.println(
					 * "Participante perdedor mediante el metodo de eliminacion por puntuacion: " +
					 * participantePerdedor.getNumeroParticipante()); break cicloromper; }
					 * //System.Out.println(
					 * "Ningun participante eliminado por el metodo Marcador de participante final puntuación"
					 * ); } break; default: if (Math.random() < 0.5) {
					 * participantes.get(encuentro.getIdParticipanteInicial()).setLugarParticipante(
					 * 100); //System.Out.
					 * println("Id de participante descalificado mediante el metodo aleatorio: " +
					 * encuentro.getIdParticipanteInicial()); } else {
					 * participantes.get(encuentro.getIdParticipanteFinal()).setLugarParticipante(
					 * 100); //System.Out.
					 * println("Id de participante descalificado mediante el metodo aleatorio: " +
					 * encuentro.getIdParticipanteFinal()); } break cicloromper; }
					 * 
					 * } }
					 */
				}

			} else {
				if ((encuentro.getResultadoEncuentro() == Encuentro.GANADOR_INICIAL || partInicial
						.getPuntajeAcumuladoParticipante() > partFinal.getPuntajeAcumuladoParticipante())) {
					// FIXME eliminar
					// System.Out.println("Id de participante descalificado: " +
					// (encuentro.getIdParticipanteFinal() - 1));
					if (partFinal.getLugarParticipante() == 50) {
						partFinal.setLugarParticipante(100);
						bdp.actualizarLugarParticipante(partFinal, torneo);
					} else {
						partFinal.setLugarParticipante(50);
					}

				} else if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_FINAL || partInicial
						.getPuntajeAcumuladoParticipante() < partFinal.getPuntajeAcumuladoParticipante()) {
					// FIXME eliminar
					// System.Out
					// .println("Id de participante descalificado: " +
					// (encuentro.getIdParticipanteInicial() - 1));

					if (partInicial.getLugarParticipante() == 50) {
						partInicial.setLugarParticipante(100);
						bdp.actualizarLugarParticipante(partInicial, torneo);
					} else {
						partInicial.setLugarParticipante(50);
					}

				}
			}
		}
		participantes = torneo.getListaParticipantes();
		for (Participante p : participantes) {
			// System.Out.println("participante: " + p + " lugar: " +
			// p.getLugarParticipante());
			if (p.getLugarParticipante() != 100) {
				// System.Out.println("agregado");
				participantesCiclo.add(p);
			}
		}

		// FIXME borrar comentarios
		// System.Out.println("Participante totales: " + participantes);
		// System.Out.println("Participante que siguen: " + participantesCiclo);

		if (torneo.getAlgoritmoTorneo().getTipoEliminacion()) {
			// System.out.println("Simpleeeeee");
			int mitad = participantesCiclo.size() / 2;
			ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
			for (int i = 1; i <= mitad; i++) {
				encuentros.add(new Encuentro(i, participantesCiclo.get(i - 1).getNumeroParticipante(),
						participantesCiclo.get(participantesCiclo.size() - i).getNumeroParticipante(),
						this.getTorneo().getFechaInicioTorneo()));
				bde.insertarEncuentro(encuentros.get((i) - 1), ciclo);
				bdp.actualizarResultadoParticipante(participantesCiclo.get(i - 1), ciclo);
				bdp.actualizarResultadoParticipante(participantesCiclo.get(participantesCiclo.size() - i), ciclo);
				// System.Out.println("Encuentro numero: " + i);
				// System.Out.println(
				// "Participante inicial que jugará: " participantesCiclo.get(i -
				// 1).getNumeroParticipante()
				// + " nombre: " + participantesCiclo.get(i - 1).getNombreParticipante());
				// System.Out.println("Participante final que jugará: "
				// + participantesCiclo.get(participantesCiclo.size() -
				// i).getNumeroParticipante() + " nombre: "
				// + participantesCiclo.get(participantesCiclo.size() -
				// i).getNombreParticipante());

			}
			// System.Out.println("Ciclo guardado");
			ciclo.setEncuentroParticipantes(encuentros);

		} else {
			// System.out.println("Dobleeeeee aqui jony");
			int mitad = (participantesCiclo.size() / 2);
			int auxUltimaPosicionSegundaVuelta = 0;
			ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
			for (int i = 1; i <= mitad; i++) {

				if (participantesCiclo.get(i - 1).getLugarParticipante() == 50) {
					encuentros.add(new Encuentro(i,
							participantesCiclo.get(participantesCiclo.size() - i).getNumeroParticipante(),
							participantesCiclo.get(i - 1).getNumeroParticipante(),
							this.getTorneo().getFechaInicioTorneo()));
				} else {
					encuentros.add(new Encuentro(i, participantesCiclo.get(i - 1).getNumeroParticipante(),
							participantesCiclo.get(participantesCiclo.size() - i).getNumeroParticipante(),
							this.getTorneo().getFechaInicioTorneo()));
				}

				auxUltimaPosicionSegundaVuelta++;
				bde.insertarEncuentro(encuentros.get((i) - 1), ciclo);
				bdp.actualizarResultadoParticipante(participantesCiclo.get((i - 1)), ciclo);
				bdp.actualizarResultadoParticipante(participantesCiclo.get(participantesCiclo.size() - i), ciclo);
			}

			/*
			 * for (int i = 1; i <= mitad; i++) { encuentros.add(new
			 * Encuentro(auxUltimaPosicionSegundaVuelta + 1,
			 * participantesCiclo.get(participantesCiclo.size() -
			 * i).getNumeroParticipante(), participantesCiclo.get(i -
			 * 1).getNumeroParticipante(), this.getTorneo().getFechaInicioTorneo()));
			 * auxUltimaPosicionSegundaVuelta++;
			 * bde.insertarEncuentro(encuentros.get((auxUltimaPosicionSegundaVuelta - 1)),
			 * ciclo); bdp.actualizarResultadoParticipante(participantes.get((i - 1)),
			 * ciclo);
			 * bdp.actualizarResultadoParticipante(participantesCiclo.get(participantesCiclo
			 * .size() - i), ciclo); }
			 */
			ciclo.setEncuentroParticipantes(encuentros);
		}
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
	private boolean esPotenciaDeDos(double numero) {
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
		ArrayList<Encuentro> encuentros = torneo.getAlgoritmoTorneo().getCiclos().get(torneo.getCicloActual() - 1)
				.getEncuentroParticipantes();
		for (Encuentro e : encuentros) {
			if (e.getResultadoEncuentro() == Encuentro.SIN_JUGAR) {
				return false;
			}
		}
		return true;

	}

	/**
	 * Inicia un torneo Eliminación Directa e inserta el numero de ciclos en la
	 * tabla <code>suizo</code>.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Si ocurre un problema con la base de datos.
	 * @throws ExcepcionBaseDatosEncuentro
	 *             Si ocurre un problema al insertar en la tabla
	 *             <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosCiclo
	 *             Si ocurre un error al insertar en la tabla <code>ciclos</code>.
	 * @throws ExcepcionCapturarResultados
	 *             si ocurre un error al generar los encuentros.
	 * @throws ExcepcionBaseDatosTorneo
	 *             si ocurre un error con el torneo.
	 */
	public void iniciarTorneo() throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo,
			ExcepcionCapturarResultados, ExcepcionBaseDatosTorneo {

		BaseDatosTorneo bdt = new BaseDatosTorneo(torneo.getNombreArchivo());
		bdt.insertarTorneoEliminacionDirecta(this);
		this.setCiclos(new ArrayList<Ciclo>());
		torneo.setAlgoritmoTorneo(this);
		this.realizarEncuentros();
		// torneoEliminacionDirecta.realizarEncuentros();

	}

}