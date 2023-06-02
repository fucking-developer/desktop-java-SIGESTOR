package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para la declaración del método que aplica el criterio de desempate
 * determinado.
 * 
 * @version 04/06/2022
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Hernán Sesaí Lopéz Aragón
 * @author Francisco Samuel Reyes Cortes
 *
 */
public abstract class Desempate {

	/**
	 * Desempata a dos participantes igualados en puntos, mediante criterios de
	 * desempate.
	 * 
	 * @param p1                 Recibe el objeto <code>Participante</code> a
	 *                           desempatar.
	 * @param p2                 Recibe el objeto <code>Participante</code> a
	 *                           desempatar.
	 * @param listaParticipantes Recibe la lista de participantes del torneo.
	 * @param encuentrosTotales  Recibe la lista de encuentros totales jugados en el
	 *                           torneo.
	 * @param torneo Recibe los datos del torneo. 
	 * @return Regresa el objeto <code>Participante</code> que es el participante
	 *         con puntaje de desempate más alto. <code>null</code> en caso de no
	 *         poderlos desempatar.
	 */
	public abstract Participante desempatar(Participante p1, Participante p2,
			ArrayList<Participante> listaParticipantes, ArrayList<Encuentro> encuentrosTotales, Torneo torneo);

	/**
	 * Obtiene los encuentros de un participante.
	 * 
	 * @param encuentrosTotales  Recibe la lista de encuentros totales jugados en el
	 *                           torneo.
	 * @param numeroParticipante Recibe el número del participante a evaluar.
	 * @return Regresa la lista de encuentros del participante evaluado.
	 */
	protected ArrayList<Encuentro> obtenerEncuentrosParticipante(ArrayList<Encuentro> encuentrosTotales,
			int numeroParticipante) {
		ArrayList<Encuentro> encuentrosParticipante = new ArrayList<Encuentro>();

		for (Encuentro encuentro : encuentrosTotales) {
			if (encuentro.getIdParticipanteInicial() == numeroParticipante
					|| encuentro.getIdParticipanteFinal() == numeroParticipante) {
				encuentrosParticipante.add(encuentro);
			}
		}
		return encuentrosParticipante;
	}

	/**
	 * Obtiene un participante de acuerdo a su número de participante.
	 * 
	 * @param listaParticipantes Recibe la lista de los participantes del torneo.
	 * @param numeroParticipante Recibe el número del participante a obtener.
	 * @return Regresa el objeto <code>Participante</code> con los datos del
	 *         participante evaluado. <code>null</code> en caso de no encontrarlo.
	 */
	protected Participante obtenerParticipante(ArrayList<Participante> listaParticipantes, int numeroParticipante) {
		for (Participante participante : listaParticipantes) {
			if (participante.getNumeroParticipante() == numeroParticipante) {
				return participante;
			}
		}
		return null;
	}
}