package sigestor.dominio;

import java.util.ArrayList;

public class DesempateMarcadorParticipanteFinal extends Desempate  {
	/**
	 * Desempata dos participantes, verificando qu� participante ha recibido mayor
	 * valoraci�n en su marcador a favor; dicho participante ser� el ganador.
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
			return null;
		
	}
}
