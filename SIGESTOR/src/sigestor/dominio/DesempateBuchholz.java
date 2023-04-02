package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para implementar el criterio de desempate Buchholz.
 * <p>
 * 
 * @version 06/06/2022
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 */
public class DesempateBuchholz extends Desempate {

	/**
	 * Desempata participantes sumando, para cada participante, las puntuaciones de
	 * sus rivales con los que ha tenido un encuentro. Los puntajes que se le suman
	 * al participante a desempatar son los siguientes:
	 * <ul>
	 * <li>1 punto por cada encuentro ganado de su rival.</li>
	 * <li>0 puntos por cada encuentro perdido de su rival.</li>
	 * <li>0.5 puntos por cada encuentro empatado de su rival.</li>
	 * <li>0.5 puntos por cada encuentro ganado de su rival, pero por cuestiones de
	 * descanso o por no haberse presentado su oponente.</li>
	 * </ul>
	 * 
	 * @param p1                 Recibe el objeto <code>Participante</code> con los
	 *                           datos del participante a desempatar.
	 * @param p2                 Recibe el objeto <code>Participante</code> con los
	 *                           datos del participante a desempatar.
	 * @param listaParticipantes Recibe la lista de participantes del torneo.
	 * @param encuentrosTotales  Recibe la lista de encuentros totales jugados en el
	 *                           torneo.
	 * @return Regresa el objeto <code>Participante</code> con puntaje de desempate
	 *         más alto. <code>null</code> en caso de no poderlos desempatar.
	 */
	@Override
	public Participante desempatar(Participante p1, Participante p2, ArrayList<Participante> listaParticipantes,
			ArrayList<Encuentro> encuentrosTotales, Torneo torneo) {
		float puntajeBuchholzP1 = obtenerBuchholzParticipante(p1.getNumeroParticipante(), encuentrosTotales);
		float puntajeBuchholzP2 = obtenerBuchholzParticipante(p2.getNumeroParticipante(), encuentrosTotales);

		if (puntajeBuchholzP1 > puntajeBuchholzP2) {
			return p1;
		} else if (puntajeBuchholzP1 < puntajeBuchholzP2) {
			return p2;
		} else {
			return null;
		}
	}

	/**
	 * Obtiene el puntaje de desempate Buchholz de un participante.
	 * 
	 * @param numeroParticipante Recibe el número del participante a evaluar.
	 * @param encuentrosTotales  Recibe la lista de encuentros del participante a
	 *                           evaluar.
	 * @return Regresa el puntaje de desempate Buchholz obtenido.
	 */
	private float obtenerBuchholzParticipante(int numeroParticipante, ArrayList<Encuentro> encuentrosTotales) {
		float puntajeBuchholz = 0f;

		for (Encuentro encuentroParticipante : obtenerEncuentrosParticipante(encuentrosTotales, numeroParticipante)) {
			if (encuentroParticipante.getIdParticipanteInicial() == numeroParticipante) {
				puntajeBuchholz += obtenerAcumuladoParticipante(encuentroParticipante.getIdParticipanteFinal(),
						obtenerEncuentrosParticipante(encuentrosTotales,
								encuentroParticipante.getIdParticipanteFinal()));
			}
		}

		return puntajeBuchholz;
	}

	/**
	 * Obtiene el puntaje acumulado de un participante.
	 * 
	 * @param numeroParticipante     Recibe el número del participante a evaluar.
	 * @param encuentrosParticipante Recibe la lista de encuentros en los que haya
	 *                               jugado el participante evaluado.
	 * @return Regresa el puntaje que acumuló el participante evaluado a lo largo de
	 *         sus encuentros jugados.
	 */
	private float obtenerAcumuladoParticipante(int numeroParticipante, ArrayList<Encuentro> encuentrosParticipante) {
		float puntosAcumuladosParticipante = 0f;

		for (Encuentro encuentro : encuentrosParticipante) {
			if (encuentro.getResultadoEncuentro() == Encuentro.DESCANSO
					|| encuentro.getResultadoEncuentro() == Encuentro.EMPATE) {
				puntosAcumuladosParticipante += 0.5;
			} else {
				if (numeroParticipante == encuentro.getIdParticipanteInicial()) {
					if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_INICIAL) {
						puntosAcumuladosParticipante += 1;
					}
				} else {
					if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_FINAL) {
						puntosAcumuladosParticipante += 1;
					}
				}
			}
		}
		return puntosAcumuladosParticipante;
	}

}