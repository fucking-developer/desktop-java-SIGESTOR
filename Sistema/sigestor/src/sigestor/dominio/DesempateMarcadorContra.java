package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para implementar el criterio de desempate marcador en contra.
 * <p>
 * 
 * 
 * @version 04/06/2022
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 *
 */
public class DesempateMarcadorContra extends Desempate {

	/**
	 * Desempata dos participantes, verificando qué participante ha recibido menor
	 * valoración en su marcador en contra; dicho participante será el ganador.
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
		if (p1.getMarcadorContra() < p2.getMarcadorContra()) {
			return p1;
		} else if (p1.getMarcadorContra() > p2.getMarcadorContra()) {
			return p2;
		} else {
			return null;
		}
	}
}
