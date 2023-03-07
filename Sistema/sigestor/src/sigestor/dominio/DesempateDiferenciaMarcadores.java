package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para implementar el criterio de desempate diferencia de marcadores.
 * <p>
 * 
 * @version 04/06/2022
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 *
 */
public class DesempateDiferenciaMarcadores extends Desempate {

	/**
	 * Desempata dos participantes, restando el marcador a favor menos el marcador
	 * en contra de cada participante. El participante ganador será el que obtenga
	 * la mayor diferencia de marcadores.
	 * 
	 * @param p1                 Recibe el objeto <code>Participante</code> a
	 *                           desempatar.
	 * @param p2                 Recibe el objeto <code>Participante</code> a
	 *                           desempatar.
	 * @param listaParticipantes Recibe la lista de participantes del torneo.
	 * @param encuentrosTotales  Recibe la lista de encuentros totales jugados en el
	 *                           torneo.
	 * @return Regresa el objeto <code>Participante</code> con una mayor diferencia
	 *         de marcadores. <code>null</code> en caso de no poderlos desempatar.
	 */
	@Override
	public Participante desempatar(Participante p1, Participante p2, ArrayList<Participante> listaParticipantes,
			ArrayList<Encuentro> encuentrosTotales, Torneo torneo) {
		int diferenciaP1 = p1.getMarcadorFavor() - p1.getMarcadorContra();
		int diferenciaP2 = p2.getMarcadorFavor() - p2.getMarcadorContra();
		if (diferenciaP1 > diferenciaP2) {
			return p1;
		} else if (diferenciaP1 < diferenciaP2) {
			return p2;
		} else {
			return null;
		}
	}

}
