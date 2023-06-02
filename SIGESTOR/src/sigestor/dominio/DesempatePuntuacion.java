package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para implementar el criterio de desempate Puntuación.
 * <p>
 * 
 * @version 11/04/2023
 * 
 * @author Eder Euclides Dionisio Diaz
 */

public class DesempatePuntuacion extends Desempate {

	/**
	 * Desempata participantes obteniendo su puntuación inicial, de esta manera, el
	 * participante que tenga menor puntaje es el que se elimina
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
	 * @param torneo Recibe los datos del torneo.
	 * @return Regresa el objeto <code>Participante</code> con puntaje de desempate
	 *         más bajo. <code>null</code> en caso de no poderlos desempatar.
	 */
	public Participante desempatar(Participante p1, Participante p2, ArrayList<Participante> listaParticipantes,
			ArrayList<Encuentro> encuentrosTotales, Torneo torneo) {
		float puntajeP1 = p1.getPuntajeParticipante();
		float puntajeP2 = p2.getPuntajeParticipante();

		if (puntajeP1 > puntajeP2) {
			return p2;
		} else if (puntajeP1 < puntajeP2) {
			return p1;
		} else {
			return null;
		}
	}
}
