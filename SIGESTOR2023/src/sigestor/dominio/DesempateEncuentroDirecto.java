package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para implementar el criterio de desempate encuentro directo.
 * <p>
 * 
 * @version 4/06/2022
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 */

public class DesempateEncuentroDirecto extends Desempate {

	/**
	 * Desempata dos participantes, verificando si los jugadores ya se han
	 * enfrentado anteriormente; si lo han hecho, se toma como ganador a quien haya
	 * ganado más veces dicho encuentro.
	 * 
	 * @param p1                 Recibe el objeto <code>Participante</code> a
	 *                           desempatar.
	 * @param p2                 Recibe el objeto <code>Participante</code> a
	 *                           desempatar.
	 * @param listaParticipantes Recibe la lista de participantes del torneo.
	 * @param encuentrosTotales  Recibe la lista de encuentros totales jugados en el
	 *                           torneo.
	 * @return Regresa el objeto <code>Participante</code> con un mayor número de
	 *         encuentros directos ganados. <code>null</code> en caso de no poderlos
	 *         desempatar.
	 */
	@Override
	public Participante desempatar(Participante p1, Participante p2, ArrayList<Participante> listaParticipantes,
			ArrayList<Encuentro> encuentrosTotales, Torneo torneo) {
		int ganadosEncuentroDirectoP1 = obtenerGanadosEncuentroDirecto(p1.getNumeroParticipante(),
				obtenerEncuentrosParticipante(encuentrosTotales, p2.getNumeroParticipante()));
		int ganadosEncuentroDirectoP2 = obtenerGanadosEncuentroDirecto(p2.getNumeroParticipante(),
				obtenerEncuentrosParticipante(encuentrosTotales, p1.getNumeroParticipante()));

		if (ganadosEncuentroDirectoP1 > ganadosEncuentroDirectoP2) {
			return p1;
		} else if (ganadosEncuentroDirectoP1 < ganadosEncuentroDirectoP2) {
			return p2;
		} else {
			return null;
		}
	}

	/**
	 * Obtiene el número de encuentros ganados de un participante, donde los
	 * jugadores a desempatar se hayan enfrentado.
	 * 
	 * @param numeroParticipante1     Recibe el número del participante a evaluar.
	 * @param encuentrosParticipante2 Recibe la lista de encuentros del participante
	 *                                rival del participante evaluado.
	 * @return Regresa el número de encuentros directos ganados del participante
	 *         evaluado.
	 */
	private int obtenerGanadosEncuentroDirecto(int numeroParticipante1, ArrayList<Encuentro> encuentrosParticipante2) {
		int ganadosEncuentroDirecto = 0;
// 1 gano inicial
		// -1 gano final
		//0 enpate
		for (Encuentro encuentro : obtenerEncuentrosDirectos(numeroParticipante1, encuentrosParticipante2)) {
			if (encuentro.getResultadoEncuentro() == 1 && encuentro.getIdParticipanteInicial() == numeroParticipante1) {
				ganadosEncuentroDirecto++;
			}
			if (encuentro.getResultadoEncuentro() == -1 && encuentro.getIdParticipanteFinal() == numeroParticipante1) {
				ganadosEncuentroDirecto++;
			}
		}
		return ganadosEncuentroDirecto;
	}

	/**
	 * Obtiene los encuentros en donde los participantes a desempatar se hayan
	 * enfrentado.
	 * 
	 * @param numeroParticipante1     Recibe el número del participante a evaluar.
	 * @param encuentrosParticipante2 Recibe la lista de encuentros del participante
	 *                                rival del participante evaluado.
	 * @return Regresa la lista de encuentros directos de dos participantes.
	 */
	private ArrayList<Encuentro> obtenerEncuentrosDirectos(int numeroParticipante1,
			ArrayList<Encuentro> encuentrosParticipante2) {
		ArrayList<Encuentro> encuentrosDirectos = new ArrayList<Encuentro>();

		for (Encuentro e : encuentrosParticipante2) {
			if (e.getIdParticipanteInicial() == numeroParticipante1
					|| e.getIdParticipanteFinal() == numeroParticipante1) {
				encuentrosDirectos.add(e);
			}
		}
		return encuentrosDirectos;
	}
}