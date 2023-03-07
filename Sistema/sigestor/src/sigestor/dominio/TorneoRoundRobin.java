package sigestor.dominio;

import java.util.ArrayList;
import java.util.Collections;

import sigestor.bd.BaseDatosParticipante;
import sigestor.bd.BaseDatosTorneo;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosCiclo;
import sigestor.excepcion.ExcepcionBaseDatosEncuentro;
import sigestor.excepcion.ExcepcionBaseDatosParticipante;
import sigestor.excepcion.ExcepcionBaseDatosTorneo;
import sigestor.excepcion.ExcepcionCapturarResultados;

/**
 * Sirve para implementar los métodos declarados en la clase
 * <code>AlgoritmoTorneo</code>.
 * <p>
 * 
 * @version 08/06/2022
 * 
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Hernán Sesaí Lopéz Aragón
 * @author Francisco Samuel Reyes Cortes
 * 
 * @see AlgoritmoTorneo
 */
public class TorneoRoundRobin extends AlgoritmoTorneo {

	/**
	 * Sirve para saber las veces que se enfrentarían los participantes.
	 */
	private int numeroVueltas;

	/**
	 * @author Francisco Samuel Reyes Cortes
	 * 
	 *         Constructor en el que se inicializa la variable
	 *         <code>numeroVueltas</code>.
	 * @param torneo Objeto de la clase <code>Torneo</code>.
	 */
	public TorneoRoundRobin(Torneo torneo) {
		super(torneo);
		this.setNumeroVueltas(1);
	}

	/**
	 * Método que sirver para devolver el valor de la variable
	 * <code>numeroVueltas</code>.
	 * 
	 * @return Número de veces que se enfrentarían los participantes.
	 */
	public int getNumeroVueltas() {
		return numeroVueltas;
	}

	/**
	 * Asigna valor a la variable <code>numeroVueltas</code>.
	 * 
	 * @param numeroVueltas Dato que recive de la personalización del torneo.
	 */
	public void setNumeroVueltas(int numeroVueltas) {
		this.numeroVueltas = numeroVueltas;
	}

	/**
	 * Obtiene la cantidad máxima de ciclos del torneo dependiendo del número de
	 * participantes registrados.
	 * 
	 * @param numeroParticipantes Cantidad de participantes inscritos en el torneo.
	 * @return El número máximo de ciclos que tendría el torneo.
	 */
	@Override
	public int calcularNumeroCiclos(int numeroParticipantes) {
		if ((numeroParticipantes % 2) == 0) {
			return numeroParticipantes - 1;
		} else {
			return numeroParticipantes;
		}
	}

	/**
	 * 
	 * Crea instancia de la clase <code>BaseDatosTorneo</code> y llama a su método
	 * <code>insertarTorneoRoundRobin</code> para insertar datos para un torneo de
	 * tipo Round Robin.
	 * <p>
	 * 
	 * @throws ExcepcionBaseDatos          Si ocurre un problema con la base de
	 *                                     datos.
	 * @throws ExcepcionBaseDatosTorneo    Si ocurre un problema al insertar en la
	 *                                     tabla <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosEncuentro Si ocurre un problema al insertar en la
	 *                                     tabla <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosCiclo     Si ocurre un error al insertar en la
	 *                                     tabla <code>ciclos</code>.
	 * @throws ExcepcionCapturarResultados
	 */
	public void iniciarTorneo() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo, ExcepcionBaseDatosEncuentro,
			ExcepcionBaseDatosCiclo, ExcepcionCapturarResultados {
		BaseDatosTorneo bdt = new BaseDatosTorneo(torneo.getNombreArchivo());
		bdt.insertarTorneoRoundRobin(this);
		this.realizarEncuentros();
		// Participante.setPuntajeAcumulado(true);
	}

	/**
	 * Asigana los encuentros que tendrá cada ciclo del torneo entre los
	 * participantes registrados.
	 * 
	 * @throws ExcepcionBaseDatosEncuentro Si ocurre un problema al insertar en la
	 *                                     tabla <code>encuentros</code>.
	 * @throws ExcepcionBaseDatos          Si ocurre un problema con la base de
	 *                                     datos.
	 * @throws ExcepcionBaseDatosCiclo     Si ocurre un error al insertar en la
	 *                                     tabla <code>ciclos</code>.s
	 * @throws ExcepcionCapturarResultados
	 * @throws ExcepcionBaseDatosTorneo
	 */
	@Override
	public void realizarEncuentros() throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo,
			ExcepcionCapturarResultados, ExcepcionBaseDatosTorneo {
		parearParticipantes();
	}

	private void parearParticipantes() throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo,
			ExcepcionCapturarResultados, ExcepcionBaseDatosTorneo {
		int numeroParticipantes = this.getTorneo().getListaParticipantes().size();
		int numeroPareos = numeroParticipantes / 2;
		int contEncuentro = numeroPareos - 1;
		int local = 0;
		int visitante = 0;
		int numCiclos = 1;
		int idParticipante1 = 1;
		int idParticipante2 = numeroParticipantes - 1;
		int identificadorRonda = 1;

		getTorneo().setCicloActual(getTorneo().getCicloActual() + 1);

		BaseDatosTorneo bdt = new BaseDatosTorneo(torneo.getNombreArchivo());
		bdt.actualizarCicloActual(getTorneo());
		for (int i = 0; i < this.getNumeroVueltas(); i++) {
			idParticipante1 = 1;
			idParticipante2 = numeroParticipantes - 1;
			for (int j = 1; j <= this.calcularNumeroCiclos(torneo.getListaParticipantes().size()); j++) {
				if (numCiclos <= this.getNumeroCiclos()) {
					ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
					Ciclo ciclo = new Ciclo(getTorneo(), numCiclos);
					for (int k = 1; k <= numeroPareos; k++) {

						local = idParticipante1;

						if (idParticipante1 == this.calcularNumeroCiclos(numeroParticipantes)) {
							idParticipante1 = 1;
						} else {
							idParticipante1++;
						}

						if (contEncuentro == numeroPareos - 1) {
							visitante = numeroParticipantes;
							contEncuentro = 0;
						} else {
							if (idParticipante2 == 0) {
								idParticipante2 = numeroParticipantes - 1;
							}
							visitante = idParticipante2;
							idParticipante2--;
							contEncuentro++;
						}

						int aux1 = local;
						int aux2 = visitante;
						if (j % 2 == 0) {
							local = aux2;
							visitante = aux1;
						}
						if (this.getTorneo().getListaParticipantes().get(local - 1).getNombreParticipante()
								.compareToIgnoreCase(this.getTorneo().getDatosPersonalizacion()
										.getNombreParticipanteSinEncuentro()) != 0) {
							local = aux2;
							visitante = aux1;
						}

						if (i % 2 != 0) {
							encuentros.add(new Encuentro(identificadorRonda,
									this.getTorneo().getListaParticipantes().get(local - 1).getNumeroParticipante(),
									this.getTorneo().getListaParticipantes().get(visitante - 1)
											.getNumeroParticipante()));

						} else {
							encuentros.add(new Encuentro(identificadorRonda,
									this.getTorneo().getListaParticipantes().get(visitante - 1).getNumeroParticipante(),
									this.getTorneo().getListaParticipantes().get(local - 1).getNumeroParticipante()));
						}
						identificadorRonda++;
					}
					ciclo.setEncuentroParticipantes(encuentros);
					ciclo.guardarCiclo(ciclo, torneo.getNombreArchivo());

					getTorneo().getAlgoritmoTorneo().getCiclos().add(ciclo);
					numCiclos++;
				}
			}
		}
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
	 * Intercambia las posiciones de 2 jugadores empatados si el ganador está una
	 * posición abajo del jugador con quien empate, de lo contrario no realiza
	 * ningún movimiento.
	 * 
	 * @param numeroP1    Primer participante empatado.
	 * @param numP2       Segundo participante empatado.
	 * @param numPGanador El participante que obtuvo más puntaje con el criterio de
	 *                    desempate aplicado.
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
					System.out.println(true);
				}
				encontrado = true;
			}
			index++;
		}
		return participantes;
	}

	/**
	 * Aplica el/los criterio(s) de desempate(s) establecidos en
	 * <code>CriteriosDesempate</code>.
	 */
	@Override
	public void desempatarParticipantes() {

		if (!Participante.isPuntaje()) {
			Participante.setPuntaje(true);
		}
		if (!Participante.isPuntajeAcumulado()) {
			Participante.setPuntajeAcumulado(true);
		}
		Collections.sort(torneo.getListaParticipantes());

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

		try {
			posicionarParticipante(participantes);
		} catch (ExcepcionBaseDatos | ExcepcionBaseDatosParticipante e) {
		}
	}

	/**
	 * Le asigna un lugar a un participante después de la aplicación de los
	 * desempates.
	 * 
	 * @param participantes Contiene los datos generales del participante.
	 * @throws ExcepcionBaseDatos             Lanza la excepción si ocurre un error
	 *                                        al actualizar el lugar del
	 *                                        participante en la tabla
	 *                                        <code>participante</code> de la base
	 *                                        de datos.
	 * @throws ExcepcionBaseDatosParticipante Lanza la excepción si ocurre un error
	 *                                        al actualizar el lugar del
	 *                                        participante en la tabla
	 *                                        <code>participante</code> de la base
	 *                                        de datos.
	 */
	private void posicionarParticipante(ArrayList<Participante> participantes)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosParticipante {
		BaseDatosParticipante bdp = new BaseDatosParticipante(this.getTorneo().getNombreArchivo());
		int i = 1;
		for (Participante p : participantes) {
			p.setLugarParticipante(i);
			bdp.actualizarLugarParticipante(p, torneo);
			i++;
		}
	}
}
