package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para implementar el criterio de desempate sistema Koya.
 * 
 * 
 * @version 04/06/2022
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 */
public class DesempateSistemaKoya extends Desempate {

	/**
	 * Desempata dos participantes, sumando para cada participante, los puntos
	 * logrados contra sus rivales que hayan alcanzado la mitad o más de los puntos
	 * totales posibles del torneo. La sumatoria será el puntaje de desempate del
	 * participante. Gana el participante con mayor puntaje.
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
	 * @return Regresa el objeto <code>Participante</code> con un menor marcador en
	 *         contra. <code>null</code> en caso de no poderlos desempatar.
	 */
	@Override
	public Participante desempatar(Participante p1, Participante p2, ArrayList<Participante> listaParticipantes,
			ArrayList<Encuentro> encuentrosTotales, Torneo torneo) {
		float puntajeKoyaP1 = obtenerKoyaParticipante(p1.getNumeroParticipante(),
				obtenerEncuentrosParticipante(encuentrosTotales, p1.getNumeroParticipante()), listaParticipantes,
				torneo);
		float puntajeKoyaP2 = obtenerKoyaParticipante(p2.getNumeroParticipante(),
				obtenerEncuentrosParticipante(encuentrosTotales, p2.getNumeroParticipante()), listaParticipantes,
				torneo);
		if (puntajeKoyaP1 > puntajeKoyaP2) {
			return p1;
		} else if (puntajeKoyaP2 > puntajeKoyaP1) {
			return p2;
		} else {
			return null;
		}
	}

	/**
	 * Obtiene el puntaje de desempate Koya de un participante.
	 * 
	 * @param numeroParticipante
	 *            Recibe la número del participante a evaluar.
	 * @param encuentrosParticipante
	 *            Recibe la lista de encuentros del participante a evaluar.
	 * @param listaParticipantes
	 *            Recibe la lista de participantes del torneo.
	 * @return Regresa el puntaje de desempate Koya obtenido.
	 */
	private float obtenerKoyaParticipante(int numeroParticipante, ArrayList<Encuentro> encuentrosParticipante,
			ArrayList<Participante> listaParticipantes, Torneo torneo) {
		float puntajeKoya = 0f;
		float puntosTotalesPosibles = torneo.getDatosPersonalizacion().getPuntajeGanar()
				* torneo.getAlgoritmoTorneo().getCiclos().size();

		for (Encuentro enc : encuentrosParticipante) {
			if (enc.getResultadoEncuentro() == Encuentro.GANADOR_INICIAL) {
				if (numeroParticipante == enc.getIdParticipanteInicial()) {
					if (obtenerParticipante(listaParticipantes, enc.getIdParticipanteFinal())
							.getPuntajeAcumuladoParticipante() >= (puntosTotalesPosibles / 2)) {
						puntajeKoya += 1;// getTorneo().getDatosPersonalizacion().getPuntajeGanar();
					}
				} else {
					if (obtenerParticipante(listaParticipantes, enc.getIdParticipanteInicial())
							.getPuntajeAcumuladoParticipante() >= (puntosTotalesPosibles / 2)) {
						puntajeKoya += 0;// getTorneo().getDatosPersonalizacion().getPuntajePerder();
					}
				}
			} else if (enc.getResultadoEncuentro() == Encuentro.GANADOR_FINAL) {
				if (numeroParticipante == enc.getIdParticipanteFinal()) {
					if (obtenerParticipante(listaParticipantes, enc.getIdParticipanteInicial())
							.getPuntajeAcumuladoParticipante() >= (puntosTotalesPosibles / 2)) {
						puntajeKoya += 1;// getTorneo().getDatosPersonalizacion().getPuntajeGanar();
					}
				} else {
					if (obtenerParticipante(listaParticipantes, enc.getIdParticipanteFinal())
							.getPuntajeAcumuladoParticipante() >= (puntosTotalesPosibles / 2)) {
						puntajeKoya += 0;// getTorneo().getDatosPersonalizacion().getPuntajePerder();
					}
				}
			} else if (enc.getResultadoEncuentro() == Encuentro.EMPATE) {
				if (numeroParticipante == enc.getIdParticipanteInicial()) {
					if (obtenerParticipante(listaParticipantes, enc.getIdParticipanteFinal())
							.getPuntajeAcumuladoParticipante() >= (puntosTotalesPosibles / 2)) {
						puntajeKoya += 0.5;// getTorneo().getDatosPersonalizacion().getPuntajeEmpatar();
					}
				} else {
					if (obtenerParticipante(listaParticipantes, enc.getIdParticipanteInicial())
							.getPuntajeAcumuladoParticipante() >= (puntosTotalesPosibles / 2)) {
						puntajeKoya += 0.5;// getTorneo().getDatosPersonalizacion().getPuntajeEmpatar();
					}
				}
			}
		}
		return puntajeKoya;
	}
}