package sigestor.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import sigestor.bd.BaseDatosCiclo;
import sigestor.bd.BaseDatosEncuentro;
import sigestor.bd.BaseDatosParticipante;
import sigestor.bd.BaseDatosTorneo;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosCiclo;
import sigestor.excepcion.ExcepcionBaseDatosEncuentro;
import sigestor.excepcion.ExcepcionBaseDatosParticipante;
import sigestor.excepcion.ExcepcionBaseDatosTorneo;
import sigestor.excepcion.ExcepcionCapturarResultados;

/**
 * Sirve para realizar las operaciones del torneo, como: crear los ciclos según
 * lo establecido en el sistema suizo, los encuentros según el sistema suizo,
 * desempatar jugadores y realizar reportes. <code>AlgoritmoTorneo</code>.
 * <p>
 * 
 * @version 19/03/2023
 * 
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 * @see AlgoritmoTorneo
 */
public class TorneoSuizo extends AlgoritmoTorneo {
	/**
	 * Inicializa las variables con un valor por defecto y asigna a la variable
	 * <code>torneo</code> el torneo recibido.
	 * 
	 * @param torneo
	 *            Contiene los datos generales del torneo.
	 */
	public TorneoSuizo(Torneo torneo) {
		super(torneo);
	}

	/**
	 * Obtiene la cantidad máxima de ciclos del torneo según el algoritmo general
	 * del sistema suizo.
	 * 
	 * @param numeroParticipantes
	 *            Cantidad de participantes inscritos en el torneo.
	 * @return El número máximo de ciclos que tendrá el torneo.
	 */
	@Override
	public int calcularNumeroCiclos(int numeroParticipantes) {
		return (int) (Math.log(numeroParticipantes) / Math.log(2));
	}

	/**
	 * Genera inicialmente los encuentros del primer ciclo y después los encuentros
	 * del siguiente ciclo una vez terminado el ciclo anterior.
	 */
	@Override
	public void realizarEncuentros() throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo {
		try {
			if (torneo.getCicloActual() < torneo.getAlgoritmoTorneo().getNumeroCiclos()) {
				torneo.setCicloActual(torneo.getCicloActual() + 1);
				BaseDatosCiclo bdc = new BaseDatosCiclo(torneo.getNombreArchivo());

				Ciclo ciclo = new Ciclo(torneo, torneo.getCicloActual());
				bdc.insertarCiclo(ciclo);
				if (torneo.getCicloActual() > 1) {
					encararParticipantesCiclosPosteriores(ciclo);
				} else {
					encararParticipantesPrimerCiclo(ciclo);
				}
				torneo.getAlgoritmoTorneo().getCiclos().add(ciclo);
				try {
					BaseDatosTorneo bdt = new BaseDatosTorneo(torneo.getNombreArchivo());
					bdt.actualizarCicloActual(torneo);
				} catch (ExcepcionBaseDatos | ExcepcionBaseDatosTorneo e) {

				}
			}
		} catch (ExcepcionCapturarResultados | ExcepcionBaseDatosParticipante e1) {

		}

	}

	/**
	 * Aplica el/los criterio(s) de desempate(s) establecidos en
	 * <code>CriteriosDesempate</code>.
	 */
	@Override
	public void desempatarParticipantes() {
		Desempate desempate;
		Participante participanteGanador;
		ArrayList<String> criterios = torneo.getCriteriosDesempate().getListaCriteriosSeleccionados();

		ArrayList<Participante> participantes = torneo.getListaParticipantes();
		for (Participante p1 : participantes) {
			for (Participante p2 : participantes) {
				if (p1.getPuntajeAcumuladoParticipante() == p2.getPuntajeAcumuladoParticipante()) {
					cicloromper: {
						for (String criterio : criterios) {
							switch (criterio) {
							case "Encuentro directo":
								desempate = new DesempateEncuentroDirecto();
								participanteGanador = desempate.desempatar(p1, p2, participantes,
										obtenerEncuentrosTotales(), torneo);
								if (participanteGanador != null) {
									participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
											p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
									break cicloromper;
								}
								break;
							case "Sistema Koya":
								desempate = new DesempateSistemaKoya();
								participanteGanador = desempate.desempatar(p1, p2, participantes,
										obtenerEncuentrosTotales(), torneo);
								if (participanteGanador != null) {
									participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
											p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
									break cicloromper;
								}
								break;
							case "Buchholz":
								desempate = new DesempateBuchholz();
								participanteGanador = desempate.desempatar(p1, p2, participantes,
										obtenerEncuentrosTotales(), torneo);
								if (participanteGanador != null) {
									participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
											p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
									break cicloromper;
								}
								break;
							case "Sonnerborn-Berger":
								desempate = new DesempateSonnebornBerger();
								participanteGanador = desempate.desempatar(p1, p2, participantes,
										obtenerEncuentrosTotales(), torneo);
								if (participanteGanador != null) {
									participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
											p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
									break cicloromper;
								}
								break;
							case "Encuentros ganados":
								desempate = new DesempateEncuentrosGanados();
								participanteGanador = desempate.desempatar(p1, p2, participantes,
										obtenerEncuentrosTotales(), torneo);
								if (participanteGanador != null) {
									participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
											p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
									break cicloromper;
								}
								break;
							case "Diferencia de marcadores":
								desempate = new DesempateDiferenciaMarcadores();
								participanteGanador = desempate.desempatar(p1, p2, participantes,
										obtenerEncuentrosTotales(), torneo);
								if (participanteGanador != null) {
									participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
											p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
									break cicloromper;
								}
								break;
							case "Marcador a favor":
								desempate = new DesempateMarcadorFavor();
								participanteGanador = desempate.desempatar(p1, p2, participantes,
										obtenerEncuentrosTotales(), torneo);
								if (participanteGanador != null) {
									participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
											p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
									break cicloromper;
								}
								break;
							case "Marcador en contra":
								desempate = new DesempateMarcadorContra();
								participanteGanador = desempate.desempatar(p1, p2, participantes,
										obtenerEncuentrosTotales(), torneo);
								if (participanteGanador != null) {
									participantes = intercambiarPosiciones(p1.getNumeroParticipante(),
											p2.getNumeroParticipante(), participanteGanador.getNumeroParticipante());
									break cicloromper;
								}
								break;
							default: // no se ha seleccionado ningún criterio
							}

						}
					}
				}
			}
		}
		torneo.setListaParticipantes(participantes);
	}

	/**
	 * Obtiene todos los encuentros que ya hayan sido jugados en el torneo.
	 * 
	 * @return lista de encuentros jugados.
	 */
	private ArrayList<Encuentro> obtenerEncuentrosTotales() {
		ArrayList<Encuentro> encuentrosTotales = new ArrayList<Encuentro>();

		ArrayList<Ciclo> ciclos = torneo.getAlgoritmoTorneo().getCiclos();
		for (Ciclo ciclo : ciclos) {
			ArrayList<Encuentro> encuentros = ciclo.getEncuentroParticipantes();
			for (Encuentro encuentro : encuentros) {
				encuentrosTotales.add(encuentro);
			}
		}
		return encuentrosTotales;
	}

	/**
	 * Obtiene los encuentros de un participante.
	 * 
	 * @param numeroParticipante
	 *            número del participante a evaluar.
	 * @return La lista de encuentros del participante evaluado.
	 */
	private ArrayList<Encuentro> obtenerEncuentrosParticipante(int numeroParticipante) {
		ArrayList<Ciclo> ciclos = torneo.getAlgoritmoTorneo().getCiclos();
		ArrayList<Encuentro> encuentrosParticipante = new ArrayList<Encuentro>();

		for (Ciclo c : ciclos) {
			ArrayList<Encuentro> encuentros = c.getEncuentroParticipantes();
			for (Encuentro e : encuentros) {
				if (e.getIdParticipanteInicial() == numeroParticipante
						|| e.getIdParticipanteFinal() == numeroParticipante) {
					encuentrosParticipante.add(e);
				}
			}
		}
		return encuentrosParticipante;
	}

	/**
	 * Verifica si el partcipante evaluado jugó como inicial en su último encuentro.
	 * 
	 * @param numeroParticipante
	 *            número del participante a evaluar.
	 * @return <code>true</code> en caso de que haya jugado como incial,
	 *         <code>false</code> en caso de haber jugado como final.
	 */
	private boolean fueInicial(int numeroParticipante) {
		ArrayList<Encuentro> encuentrosParticipante = obtenerEncuentrosParticipante(numeroParticipante);
		Encuentro ultimoEncuentroParticipante = encuentrosParticipante.get(encuentrosParticipante.size() - 1);

		if (ultimoEncuentroParticipante.getIdParticipanteInicial() == numeroParticipante) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Intercambia las posiciones de 2 jugadores empatados si el ganador está una
	 * posición abajo del jugador con quien empató, de lo contrario no realiza
	 * ningún movimiento.
	 * 
	 * @param numeroP1
	 *            Primer participante empatado.
	 * @param numP2
	 *            Segundo participante empatado.
	 * @param numPGanador
	 *            El participante que obtuvo más puntaje con el criterio de
	 *            desempate aplicado.
	 * @return Lista de participantes ordenada.
	 */
	private ArrayList<Participante> intercambiarPosiciones(int numeroP1, int numP2, int numPGanador) {

		ArrayList<Participante> participantes = torneo.getListaParticipantes();

		boolean encontrado = false;
		int index = 0;

		for (Participante p : participantes) {
			if (numeroP1 == p.getNumeroParticipante() || numP2 == p.getNumeroParticipante()) {
				if (encontrado && p.getNumeroParticipante() == numPGanador) {
					Collections.swap(participantes, index, index - 1);
				}
				encontrado = true;
			}
			index++;
		}
		return participantes;
	}

	/**
	 * Realiza los encuentros del primer ciclo.
	 * 
	 * @param ciclo
	 *            Ciclo a realizar.
	 * @throws ExcepcionCapturarResultados
	 *             Si ocurre un error con el objeto <code>Encuentros</code>.
	 * @throws ExcepcionBaseDatos
	 *             Si ocurre un problema con la base de datos.
	 * @throws ExcepcionBaseDatosEncuentro
	 *             Si ocurre un problema al insertar en la tabla
	 *             <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosParticipante
	 *             Si ocurre un problema al actualizar un participante en la tabla
	 *             <code>participantes</code>.
	 */
	public void encararParticipantesPrimerCiclo(Ciclo ciclo) throws ExcepcionCapturarResultados, ExcepcionBaseDatos,
			ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosParticipante {

		BaseDatosEncuentro bde = new BaseDatosEncuentro(torneo.getNombreArchivo());
		BaseDatosParticipante bdp = new BaseDatosParticipante(torneo.getNombreArchivo());

		int posicionDescanso = 0;
		for (Participante p : torneo.getListaParticipantes()) {
			if (p.getNombreParticipante()
					.compareToIgnoreCase(torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro()) == 0) {
				Collections.swap(torneo.getListaParticipantes(), posicionDescanso,
						torneo.getListaParticipantes().size() - 1);
				break;
			}
			posicionDescanso++;

		}

		ArrayList<Participante> participantes = torneo.getListaParticipantes();

		int mitad = participantes.size() / 2;

		ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
		for (int i = 1; i <= mitad; i++) {

			encuentros.add(new Encuentro(i, participantes.get(i - 1).getNumeroParticipante(),
					participantes.get(i - 1 + mitad).getNumeroParticipante(), this.getTorneo().getFechaInicioTorneo()));
			bde.insertarEncuentro(encuentros.get(i - 1), ciclo);
			bdp.actualizarResultadoParticipante(participantes.get(i - 1), ciclo);
			bdp.actualizarResultadoParticipante(participantes.get(i + mitad - 1), ciclo);

		}
		ciclo.setEncuentroParticipantes(encuentros);
	}

	/**
	 * Realiza los encuentros de un ciclo posterior al primer ciclo.
	 * 
	 * @param ciclo
	 *            Recibe el objeto <code>Ciclo</code> para guardar los encuentros a
	 *            realizar.
	 * @throws ExcepcionCapturarResultados
	 *             Si ocurre un error con el objeto <code>Encuentros</code>.
	 * @throws ExcepcionBaseDatos
	 *             Si ocurre un problema con la base de datos.
	 * @throws ExcepcionBaseDatosEncuentro
	 *             Si ocurre un problema al insertar en la tabla
	 *             <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosParticipante
	 *             Si ocurre un problema al actualizar un participante en la tabla
	 *             <code>participantes</code>.
	 */
	public void encararParticipantesCiclosPosteriores(Ciclo ciclo) throws ExcepcionCapturarResultados,
			ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosParticipante {

		BaseDatosEncuentro bde = new BaseDatosEncuentro(torneo.getNombreArchivo());
		BaseDatosParticipante bdp = new BaseDatosParticipante(torneo.getNombreArchivo());

		int contador = 1;
		ArrayList<Participante> participantes = torneo.getListaParticipantes();
		ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();

		
		for (int i = 0; i < participantes.size(); i++) {
			System.out.println(participantes.get(i).getNombreParticipante() + " - "
					+ participantes.get(i).getPuntajeAcumuladoParticipante());
		}

		for (int i = 0; i < participantes.size() - 1; i += 2) {
			
			encuentros.add(new Encuentro(contador, 
					participantes.get(i).getNumeroParticipante(),
					participantes.get(i + 1).getNumeroParticipante(),
					this.getTorneo().getFechaInicioTorneo()));
			
			bde.insertarEncuentro(encuentros.get(contador - 1), ciclo);
			bdp.actualizarResultadoParticipante(participantes.get(i), ciclo);
			bdp.actualizarResultadoParticipante(participantes.get(i + 1), ciclo);
			contador++;
		}
		ciclo.setEncuentroParticipantes(encuentros);
	}

	/**
	 * Verifica si se han capturado todos los encuentros del ciclo.
	 * 
	 * @return <tt>true</tt> Si se han capturado todos los encuentros de un ciclo,
	 *         <tt>false</tt> de lo contrario.
	 */
	public boolean verificarEncuentros() {
		ArrayList<Encuentro> encuentros = torneo.getAlgoritmoTorneo().getCiclos().get(torneo.getCicloActual() - 1)
				.getEncuentroParticipantes();
		for (Encuentro e : encuentros) {
			if (e.getResultadoEncuentro() == Encuentro.SIN_JUGAR) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Inicia un torneo suizo e inserta el numero de ciclos en la tabla
	 * <code>suizo</code>.
	 * 
	 * @param torneoSuizo
	 *            Recibe el objeto <code>TorneoSuizo</code>. para iniciar el torneo.
	 * @throws ExcepcionBaseDatos
	 *             Si ocurre un problema con la base de datos.
	 * @throws ExcepcionBaseDatosEncuentro
	 *             Si ocurre un problema al insertar en la tabla
	 *             <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosCiclo
	 *             Si ocurre un error al insertar en la tabla <code>ciclos</code>.
	 */
	public void iniciarTorneo(TorneoSuizo torneoSuizo)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo {
		try {
			BaseDatosTorneo bdt = new BaseDatosTorneo(torneo.getNombreArchivo());
			bdt.insertarTorneoSuizo(torneoSuizo);
		} catch (ExcepcionBaseDatosTorneo e) {
		}
		torneo.getAlgoritmoTorneo().setNumeroCiclos(torneoSuizo.getNumeroCiclos());
		torneoSuizo.realizarEncuentros();
	}
}
