package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para implementar el criterio de desempate Sonneborn Berger.
 * <p>
 * 
 * @version 04/06/2022
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 */
public class DesempateSonnebornBerger extends Desempate {

	/**
	 * Desempata dos participantes, sumando para cada participante, los puntos
	 * totales de sus rivales a quienes venció, más la mitad de los puntos de sus
	 * rivales con quienes entabló. La sumatoria será el puntaje de desempate del
	 * participante. Gana el participante con mayor puntaje.
	 * 
	 * @param p1                 Recibe el objeto <code>Participante</code> con los
	 *                           datos del participante a desempatar.
	 * @param p2                 Recibe el objeto <code>Participante</code> con los
	 *                           datos del participante a desempatar.
	 * @param listaParticipantes Recibe la lista de participantes del torneo.
	 * @param encuentrosTotales  Recibe la lista de encuentros totales jugados en el
	 *                           torneo.
	 * @return Regresa el objeto <code>Participante</code> con un menor marcador en
	 *         contra. <code>null</code> en caso de no poderlos desempatar.
	 */
	@Override
	public Participante desempatar(Participante p1, Participante p2, ArrayList<Participante> listaParticipantes,
			ArrayList<Encuentro> encuentrosTotales, Torneo torneo) {
		float puntajeSonneP1 = obtenerSonnebornBergerParticipante(p1.getNumeroParticipante(),
				obtenerEncuentrosParticipante(encuentrosTotales, p1.getNumeroParticipante()), listaParticipantes);
		float puntajeSonneP2 = obtenerSonnebornBergerParticipante(p2.getNumeroParticipante(),
				obtenerEncuentrosParticipante(encuentrosTotales, p2.getNumeroParticipante()), listaParticipantes);
		
		if (puntajeSonneP1 > puntajeSonneP2) {
			return p1;
		} else if (puntajeSonneP2 > puntajeSonneP1) {
			return p2;
		} else {
			return null;
		}
	}

	/**
	 * Obtiene el puntaje de desempate SonnebornBerger de un participante.
	 * 
	 * @param numeroParticipante     Recibe el número del participante a evaluar.
	 * @param encuentrosParticipante Recibe la lista de encuentros del participante
	 *                               a evaluar.
	 * @param listaParticipantes     Recibe la lista de participantes del torneo.
	 * @return Regresa el puntaje de desempate SonnebornBerger obtenido.
	 */
	private float obtenerSonnebornBergerParticipante(int numeroParticipante,
			ArrayList<Encuentro> encuentrosParticipante, ArrayList<Participante> listaParticipantes) {
		float puntajeSonne = 0f;

		for (Encuentro encuentro : encuentrosParticipante) {
			if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_INICIAL) {
				if (numeroParticipante == encuentro.getIdParticipanteInicial()) {
					puntajeSonne += obtenerParticipante(listaParticipantes, encuentro.getIdParticipanteFinal())
							.getPuntajeAcumuladoParticipante();
				}
			} else if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_FINAL) {
				if (numeroParticipante == encuentro.getIdParticipanteFinal()) {
					puntajeSonne += obtenerParticipante(listaParticipantes, encuentro.getIdParticipanteInicial())
							.getPuntajeAcumuladoParticipante();
				}
			} else if (encuentro.getResultadoEncuentro() == Encuentro.EMPATE) {
				if (numeroParticipante == encuentro.getIdParticipanteInicial()) {
					puntajeSonne += obtenerParticipante(listaParticipantes, encuentro.getIdParticipanteFinal())
							.getPuntajeAcumuladoParticipante();
				} else {
					puntajeSonne += obtenerParticipante(listaParticipantes, encuentro.getIdParticipanteInicial())
							.getPuntajeAcumuladoParticipante();
				}
			}
		}
		return puntajeSonne;
	}
}
