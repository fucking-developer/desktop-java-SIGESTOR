package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para implementar el criterio de desempate Encuentros ganados.
 * <p>
 * 
 * @version 04/06/2022
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 * @author Hernán Sesaí López Aragón
 */
public class DesempateEncuentrosGanados extends Desempate {

	/**
	 * Desempata dos participantes, verificando qué participante tiene un mayor
	 * número de encuentros ganados.
	 * 
	 * @param p1                 Recibe el objeto <code>Participante</code> a
	 *                           desempatar.
	 * @param p2                 Recibe el objeto <code>Participante</code> a
	 *                           desempatar.
	 * @param listaParticipantes Recibe la lista de participantes del torneo.
	 * @param encuentrosTotales  Recibe la lista de encuentros totales jugados en el
	 *                           torneo.
	 * @return Regresa el objeto <code>Participante</code> con mayor número de
	 *         encuentros ganados. <code>null</code> en caso de no poderlos
	 *         desempatar.
	 */
	@Override
	public Participante desempatar(Participante p1, Participante p2, ArrayList<Participante> listaParticipantes,
			ArrayList<Encuentro> encuentrosTotales, Torneo torneo) {
		int encuentrosGanadosP1 = obtenerGanados(p1.getNumeroParticipante(),
				obtenerEncuentrosParticipante(encuentrosTotales, p1.getNumeroParticipante()));
		int encuentrosGanadosP2 = obtenerGanados(p2.getNumeroParticipante(),
				obtenerEncuentrosParticipante(encuentrosTotales, p2.getNumeroParticipante()));

		if (encuentrosGanadosP1 > encuentrosGanadosP2) {
			return p1;
		} else if (encuentrosGanadosP1 < encuentrosGanadosP2) {
			return p2;
		} else {
			return null;
		}
	}

	/**
	 * Obtiene el número total de partidas ganadas de un participante en sus
	 * encuentros jugados.
	 * 
	 * @param numeroParticipante     Recibe el número del participante a evaluar.
	 * @param encuentrosParticipante Recibe la lista de encuentros donde el
	 *                               participante a evaluar haya jugado.
	 * @return Regresa el número de encuentros ganados del participante evaluado.
	 */
	private int obtenerGanados(int numeroParticipante, ArrayList<Encuentro> encuentrosParticipante) {
		int partidasGanadas = 0;
		for (Encuentro encuentro : encuentrosParticipante) {
			if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_INICIAL
					&& encuentro.getIdParticipanteInicial() == numeroParticipante) {
				partidasGanadas++;
			} else if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_FINAL
					&& encuentro.getIdParticipanteFinal() == numeroParticipante) {
				partidasGanadas++;
			}
		}
		return partidasGanadas;
	}
}
