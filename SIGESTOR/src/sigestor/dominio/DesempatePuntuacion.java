package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para implementar el criterio de desempate Puntuación.
 * <p>
 * 
 * @version 08/04/2023
 * 
 * @author Eder Euclides Dionisio Diaz
 */

public class DesempatePuntuacion extends Desempate {

	/**
	 * Desempata participantes sumando, para cada participante, las puntuaciones de
	 * sus encuentros. Los puntajes que se le suman al participante a desempatar son
	 * los siguientes:
	 * <ul>
	 * <li>3 punto por cada encuentro ganado.</li>
	 * <li>1 puntos por cada encuentro empatado.</li>
	 * <li>0 puntos por cada encuentro perdido.</li>
	 * </ul>
	 * 
	 * @param p1
	 *            Recibe el objeto <code>Participante</code> con los datos del
	 *            participante a desempatar.
	 * @param p2
	 *            Recibe el objeto <code>Participante</code> con los datos del
	 *            participante a desempatar.
	 * @param listaParticipantes
	 *            Recibe la lista de participantes del torneo.
	 * @param encuentrosTotales
	 *            Recibe la lista de encuentros totales jugados en el torneo.
	 * @return Regresa el objeto <code>Participante</code> con puntaje de desempate
	 *         más alto. <code>null</code> en caso de no poderlos desempatar.
	 */
	public Participante desempatar(Participante p1, Participante p2, ArrayList<Participante> listaParticipantes,
			ArrayList<Encuentro> encuentrosTotales, Torneo torneo) {
		int puntajeP1 = obtenerGanados(p1.getNumeroParticipante(), encuentrosTotales);
		int puntajeP2 = obtenerGanados(p2.getNumeroParticipante(), encuentrosTotales);

		if (puntajeP1 > puntajeP2) {
			return p1;
		} else if (puntajeP1 < puntajeP2) {
			return p2;
		} else {
			return null;
		}
	}

	/**
	 * Obtiene el puntaje de desempate Puntuación de un participante.
	 * 
	 * @param numeroParticipante
	 *            Recibe el número del participante a evaluar.
	 * @param encuentrosTotales
	 *            Recibe la lista de encuentros del participante a evaluar.
	 * @return Regresa el puntaje de desempate Puntuación obtenido.
	 */
	private int obtenerGanados(int numeroParticipante, ArrayList<Encuentro> encuentrosParticipante) {
		int puntos = 0;
		for (Encuentro encuentro : encuentrosParticipante) {
			if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_INICIAL
					&& encuentro.getIdParticipanteInicial() == numeroParticipante) {
				puntos += 3;
			} else if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_FINAL
					&& encuentro.getIdParticipanteFinal() == numeroParticipante) {
				puntos += 3;
			} else if (encuentro.getResultadoEncuentro() == Encuentro.EMPATE
					&& encuentro.getIdParticipanteFinal() == numeroParticipante) {
				puntos += 2;
			}
		}
		return puntos;
	}

}
