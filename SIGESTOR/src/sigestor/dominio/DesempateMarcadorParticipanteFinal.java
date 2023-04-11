package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para implementar el criterio de desempate Puntuación.
 * <p>
 * 
 * @version 11/04/2023
 * @author Eder Euclides Dionisio Diaz
 */
public class DesempateMarcadorParticipanteFinal extends Desempate {
	
	/**
	 * Desempata dos participantes, verificando qué participante ha recibido mayor
	 * valoración en su marcador final; dicho participante será el ganador.
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
	 * @return Regresa el objeto <code>Participante</code> con un mayor marcador a
	 *         favor. <code>null</code> en caso de no poderlos desempatar.
	 */
	
	@Override
	public Participante desempatar(Participante p1, Participante p2, ArrayList<Participante> listaParticipantes,
			ArrayList<Encuentro> encuentrosTotales, Torneo torneo) {
		int puntajeFinalP1 = 0;
		int puntajeFinalP2 = 0;
		for (Encuentro encuentro : encuentrosTotales) {
			if (encuentro.getIdParticipanteFinal() == p1.getNumeroParticipante()
					&& encuentro.getResultadoEncuentro() == Encuentro.GANADOR_FINAL) {
				puntajeFinalP1 += encuentro.getMarcadorParticipanteFinal();
			} else if (encuentro.getIdParticipanteFinal() == p2.getNumeroParticipante()
					&& encuentro.getResultadoEncuentro() == Encuentro.GANADOR_FINAL) {
				puntajeFinalP2 += encuentro.getMarcadorParticipanteFinal();
			}
		}
		if (puntajeFinalP1 > puntajeFinalP2) {
			return p1;
		} else if (puntajeFinalP1 < puntajeFinalP2) {
			return p2;
		}
		return null;

	}
}
