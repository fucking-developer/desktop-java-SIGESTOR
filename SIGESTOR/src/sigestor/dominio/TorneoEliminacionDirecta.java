package sigestor.dominio;

import java.util.ArrayList;
import java.util.Collections;
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
 * <code>AlgoritmoTorneo</code> Sirve para realizar las operaciones del torneo,
 * como: crear los ciclos seg�n lo establecido en el sistema Eliminaci�n
 * directa, los encuentros seg�n el sistema Eliminaci�n directa, desempatar
 * jugadores y realizar reportes.
 * 
 * <p>
 * Las caracteristicas de la clase son:
 * <ul>
 * <li><code>esSimple</code> determina si el tipo de torneo es simple o
 * doble.</li>
 * </ul>
 * 
 * @version 11/04/2023
 * 
 * @author German Luis Cruz Martinez.
 * @author Eder Euclides Dionisio Diaz.
 * @author Erik Vasquez Policarpo
 * @see AlgoritmoTorneo
 */
public class TorneoEliminacionDirecta extends AlgoritmoTorneo {

	/**
	 * Sirve para determinar que tipo de torneo de Eliminaci�n Directa se va a
	 * efectuar true = Eliminaci�n simple o directa false = eliminaci�n doble
	 */
	public boolean esSimple;

	/**
	 * Inicializa las variables con un valor por defecto y asigna a la variable
	 * <code>torneo</code> el torneo recibido.
	 * 
	 * @param torneo
	 *            Contiene los datos generales del torneo.
	 */
	public TorneoEliminacionDirecta(Torneo torneo) {
		super(torneo);
	}

	/**
	 * obtiene el tipo de Eliminaci�n Directa
	 * 
	 * @param true
	 *            = EliminaciónDirecta simple false = Eliminaci�nDirecta doble
	 */

	public void setTipoEliminacion(boolean tipoEliminacion) {
		this.esSimple = tipoEliminacion;
	}

	public boolean getTipoEliminacion() {
		return esSimple;
	}

	/**
	 * Obtiene la cantidad m�xima de ciclos del torneo seg�n el algoritmo general
	 * del sistema Eliminaci�n Directa.
	 * 
	 * @param numeroParticipantes
	 *            Cantidad de participantes inscritos en el torneo.
	 * @return El n�mero m�ximo de ciclos que tendr� el torneo.
	 */
	@Override
	public int calcularNumeroCiclos(int numeroParticipantes) {
		if (esSimple) {
			return (int) (Math.ceil(Math.log(numeroParticipantes) / Math.log(2)));
		} else {
			return ((int) (Math.ceil(Math.log(numeroParticipantes) / Math.log(2)))) * 2;
		}
	}

	/**
	 * Genera inicialmente los encuentros del primer ciclo y despu�s los encuentros
	 * del siguiente ciclo una vez terminado el ciclo anterior. //FIXME agregar
	 * documentaci�n
	 * 
	 * @throws ExcepcionBaseDatosTorneo
	 * @throws ExcepcionCapturarResultados
	 */
	@Override
	public void realizarEncuentros() throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo,
			ExcepcionBaseDatosTorneo, ExcepcionCapturarResultados {
		if (torneo.getCicloActual() < torneo.getAlgoritmoTorneo().getNumeroCiclos()) {
			torneo.setCicloActual(torneo.getCicloActual() + 1);
			BaseDatosCiclo bdc = new BaseDatosCiclo(torneo.getNombreArchivo());

			Ciclo ciclo = new Ciclo(torneo, torneo.getCicloActual());
			bdc.insertarCiclo(ciclo);
			try {
				if (torneo.getCicloActual() > 1) {
					encararParticipantesCiclosPosteriores(ciclo);
				} else {
					encararParticipantesPrimerCiclo(ciclo);
				}
			} catch (ExcepcionBaseDatosParticipante e) {
				e.printStackTrace();
			}

			torneo.getAlgoritmoTorneo().getCiclos().add(ciclo);
			BaseDatosTorneo bdt = new BaseDatosTorneo(torneo.getNombreArchivo());
			bdt.actualizarCicloActual(torneo);
		}

	}

	/**
	 * Aplica el/los criterio(s) de desempate(s) establecidos en
	 * <code>CriteriosDesempate</code>.
	 */

	@Override
	public void desempatarParticipantes() {
/*		BaseDatosEncuentro bde = new BaseDatosEncuentro(torneo.getNombreArchivo());
		BaseDatosCiclo bdc = new BaseDatosCiclo(torneo.getNombreArchivo());
		ArrayList<Participante> participantes = torneo.getListaParticipantes();
		ArrayList<String> criterios = torneo.getCriteriosDesempate().getListaCriteriosSeleccionados();
		ArrayList<Encuentro> encuentrosParticipante = new ArrayList<Encuentro>();

		try {
			encuentrosParticipante = bde.obtenerEncuentros(bdc.obtenerCiclos(torneo).get(torneo.getCicloActual() - 1));
		} catch (ExcepcionBaseDatos | ExcepcionCapturarResultados | ExcepcionBaseDatosEncuentro e) {
			e.printStackTrace();
		}

		for (Encuentro encuentro : encuentrosParticipante) {
			cicloromper: {
				for (String criterio : criterios) {
					switch (criterio) {
					case "Puntuaci�n":
						Desempate desempate = new DesempatePuntuacion();
						Participante participanteGanador = desempate.desempatar(
								participantes.get(encuentro.getIdParticipanteInicial()),
								participantes.get(encuentro.getIdParticipanteFinal()), participantes,
								obtenerEncuentrosTotales(), torneo);
						if (participanteGanador != null) {
							participantes.get(participanteGanador.getNumeroParticipante()).setLugarParticipante(100);
						}
						break;
					default: // no se ha seleccionado ning�n criterio
					}

				}
			}
		}*/
	}

	/**
	 * Intercambia las posiciones de 2 jugadores empatados si el ganador est� una
	 * posici�n abajo del jugador con quien empate, de lo contrario no realiza
	 * ning�n movimiento.
	 * 
	 * @param numeroP1
	 *            Primer participante empatado.
	 * @param numP2
	 *            Segundo participante empatado.
	 * @param numPGanador
	 *            El participante que obtuvo m�s puntaje con el criterio de
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
	private void encararParticipantesPrimerCiclo(Ciclo ciclo) throws ExcepcionCapturarResultados, ExcepcionBaseDatos,
			ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosParticipante {
		BaseDatosEncuentro bde = new BaseDatosEncuentro(torneo.getNombreArchivo());
		BaseDatosParticipante bdp = new BaseDatosParticipante(torneo.getNombreArchivo());

		ArrayList<Participante> participantes = torneo.getListaParticipantes();
		int totalParticipantes = participantes.size();
		int descansosPrimeraVuelta = 0;
		int descansosSegundaVuelta = 0;

		int auxUltimaPosicion = 0;
		int auxUltimaPosicionSegundaVuelta = 0;

		if (esSimple) {
			if (!esPotenciaDeDos(totalParticipantes)) {
				descansosPrimeraVuelta = (int) (Math.pow(2, calcularNumeroCiclos(totalParticipantes)))
						- totalParticipantes;

			}
			int numeroDeEncuentrosSimple = ((participantes.size() - descansosPrimeraVuelta) / 2)
					+ descansosPrimeraVuelta;
			ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
			for (int i = 1; i <= numeroDeEncuentrosSimple; i++) {
				if (!(descansosPrimeraVuelta > 0)) {

					encuentros.add(new Encuentro(auxUltimaPosicion + 1,
							participantes.get(i - 1).getNumeroParticipante(),
							participantes.get((participantes.size() - 1) - auxUltimaPosicion).getNumeroParticipante(),
							this.getTorneo().getFechaInicioTorneo()));
					auxUltimaPosicion++;
					bde.insertarEncuentro(encuentros.get((auxUltimaPosicion) - 1), ciclo);
					bdp.actualizarResultadoParticipante(participantes.get(i - 1), ciclo);
					bdp.actualizarResultadoParticipante(
							participantes.get((participantes.size() - 1) - auxUltimaPosicion), ciclo);
				}
				descansosPrimeraVuelta--;
			}
			ciclo.setEncuentroParticipantes(encuentros);

		} else {
			if (!esPotenciaDeDos(totalParticipantes)) {
				descansosPrimeraVuelta = (int) (Math.pow(2, calcularNumeroCiclos(totalParticipantes)))
						- totalParticipantes;
				descansosSegundaVuelta = descansosPrimeraVuelta;

			}
			int numeroDeEncuentrosDoble = ((participantes.size() - descansosPrimeraVuelta) / 2)
					+ descansosPrimeraVuelta;

			ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
			for (int i = 1; i <= numeroDeEncuentrosDoble; i++) {
				if (!(descansosPrimeraVuelta > 0)) {

					encuentros.add(new Encuentro(auxUltimaPosicion + 1,
							participantes.get(i - 1).getNumeroParticipante(),
							participantes.get((participantes.size() - 1) - auxUltimaPosicion).getNumeroParticipante(),
							this.getTorneo().getFechaInicioTorneo()));
					auxUltimaPosicion++;
					auxUltimaPosicionSegundaVuelta++;
					bde.insertarEncuentro(encuentros.get((auxUltimaPosicionSegundaVuelta) - 1), ciclo);
					bdp.actualizarResultadoParticipante(participantes.get((i - 1)), ciclo);
					bdp.actualizarResultadoParticipante(
							participantes.get((participantes.size() - 1) - auxUltimaPosicionSegundaVuelta), ciclo);
				} else {
					descansosPrimeraVuelta--;
				}

			}
			auxUltimaPosicion = 0;
			for (int i = 1; i <= numeroDeEncuentrosDoble; i++) {
				if (!(descansosSegundaVuelta > 0)) {

					encuentros.add(new Encuentro(auxUltimaPosicionSegundaVuelta + 1,
							participantes.get((participantes.size() - 1) - auxUltimaPosicion).getNumeroParticipante(),
							participantes.get(i - 1).getNumeroParticipante(), this.getTorneo().getFechaInicioTorneo()));
					auxUltimaPosicion++;
					auxUltimaPosicionSegundaVuelta++;
					bde.insertarEncuentro(encuentros.get((auxUltimaPosicionSegundaVuelta) - 1), ciclo);
					bdp.actualizarResultadoParticipante(participantes.get((i - 1)), ciclo);
					bdp.actualizarResultadoParticipante(
							participantes.get((participantes.size() - 1) - auxUltimaPosicion), ciclo);
				} else {
					descansosSegundaVuelta--;
				}

			}
			ciclo.setEncuentroParticipantes(encuentros);

		}

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
		BaseDatosCiclo bdc = new BaseDatosCiclo(torneo.getNombreArchivo());
		ArrayList<Participante> participantes = torneo.getListaParticipantes();
		ArrayList<Participante> participantesCiclo = new ArrayList<Participante>();
		ArrayList<Encuentro> encuentrosParticipante = bde
				.obtenerEncuentros(bdc.obtenerCiclos(torneo).get(torneo.getCicloActual() - 1));
		ArrayList<String> criterios = torneo.getCriteriosDesempate().getListaCriteriosSeleccionados();
		int auxUltimaPosicionSegundaVuelta = 0;

		for (Encuentro encuentro : encuentrosParticipante) {
			if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_INICIAL) {
				participantes.get(encuentro.getIdParticipanteFinal()).setLugarParticipante(100);
			} else if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_FINAL) {
				participantes.get(encuentro.getIdParticipanteInicial()).setLugarParticipante(100);
			} else if (encuentro.getResultadoEncuentro() == Encuentro.EMPATE) {
				cicloromper: {
					for (String criterio : criterios) {
						switch (criterio) {
						case "Puntuaci�n":
							Desempate desempate = new DesempatePuntuacion();
							Participante participanteGanador = desempate.desempatar(
									participantes.get(encuentro.getIdParticipanteInicial()),
									participantes.get(encuentro.getIdParticipanteFinal()), participantes,
									obtenerEncuentrosTotales(), torneo);
							if (participanteGanador != null) {
								participantes.get(participanteGanador.getNumeroParticipante())
										.setLugarParticipante(100);
								break cicloromper;
							}
							break;
						case "Marcador de participante final":
							break;
						default:
							if (Math.random() < 0.5) {
								participantes.get(encuentro.getIdParticipanteInicial()).setLugarParticipante(100);
							} else {
								participantes.get(encuentro.getIdParticipanteFinal()).setLugarParticipante(100);
							}
							break cicloromper;
						}

					}
				}

			}
		}
		for (Participante p : participantes) {
			if (!(p.getLugarParticipante() == 100)) {
				participantesCiclo.add(p);
			}
		}

		if (esSimple) {
			int mitad = participantesCiclo.size() / 2;
			ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
			for (int i = 1; i <= mitad; i++) {
				encuentros.add(new Encuentro(i, participantesCiclo.get(i - 1).getNumeroParticipante(),
						participantesCiclo.get((i - 1) + mitad).getNumeroParticipante(),
						this.getTorneo().getFechaInicioTorneo()));
				bde.insertarEncuentro(encuentros.get((i) - 1), ciclo);
				bdp.actualizarResultadoParticipante(participantesCiclo.get(i - 1), ciclo);
				bdp.actualizarResultadoParticipante(participantesCiclo.get((i - 1) + mitad), ciclo);

			}
			ciclo.setEncuentroParticipantes(encuentros);

		} else {
			int mitad = (participantes.size() / 2);
			ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
			for (int i = 1; i <= mitad; i++) {
				encuentros.add(new Encuentro(auxUltimaPosicionSegundaVuelta + 1,
						participantes.get(i - 1).getNumeroParticipante(),
						participantes.get((i - 1) + mitad).getNumeroParticipante(),
						this.getTorneo().getFechaInicioTorneo()));

				auxUltimaPosicionSegundaVuelta++;
				bde.insertarEncuentro(encuentros.get((auxUltimaPosicionSegundaVuelta) - 1), ciclo);
				bdp.actualizarResultadoParticipante(participantes.get((i - 1)), ciclo);
				bdp.actualizarResultadoParticipante(participantes.get((i - 1) + mitad), ciclo);
			}

			for (int i = 1; i <= mitad; i++) {
				encuentros.add(new Encuentro(auxUltimaPosicionSegundaVuelta + 1,
						participantes.get((i - 1) + mitad).getNumeroParticipante(),
						participantes.get(i - 1).getNumeroParticipante(), this.getTorneo().getFechaInicioTorneo()));

				auxUltimaPosicionSegundaVuelta++;
				bde.insertarEncuentro(encuentros.get((auxUltimaPosicionSegundaVuelta) - 1), ciclo);
				bdp.actualizarResultadoParticipante(participantes.get((i - 1)), ciclo);
				bdp.actualizarResultadoParticipante(participantes.get((i - 1) + mitad), ciclo);
			}
			ciclo.setEncuentroParticipantes(encuentros);

		}
	}

	/**
	 * Calcula por medio de recursividad si el n�mero dado es potencia de dos
	 * 
	 * @param numero
	 *            numero a evaluar si es potencia de dos.
	 * 
	 * @return True si el n�mero es potencia de dos, False si no es potencia de dos
	 * 
	 */
	private boolean esPotenciaDeDos(double numero) {
		if (numero == 1) {
			return true;
		} else if (numero > 1 && numero < 2) {
			return false;
		} else if (numero >= 2) {
			return esPotenciaDeDos(numero / 2);
		}
		return false;
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
	 * Inicia un torneo Eliminaci�n Directa e inserta el numero de ciclos en la
	 * tabla <code>suizo</code>.
	 * 
	 * @param torneoEliminacionDirecta
	 *            Recibe el objeto <code>TorneoEliminacionDirecta</code>. para
	 *            iniciar el torneo.
	 * @throws ExcepcionBaseDatos
	 *             Si ocurre un problema con la base de datos.
	 * @throws ExcepcionBaseDatosEncuentro
	 *             Si ocurre un problema al insertar en la tabla
	 *             <code>encuentros</code>.
	 * @throws ExcepcionBaseDatosCiclo
	 *             Si ocurre un error al insertar en la tabla <code>ciclos</code>.
	 * @throws ExcepcionCapturarResultados
	 *             si ocurre un error al generar los encuentros.
	 * @throws ExcepcionBaseDatosTorneo
	 *             si ocurre un error con el torneo.
	 */
	public void iniciarTorneo() throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo,
			ExcepcionCapturarResultados, ExcepcionBaseDatosTorneo {

		BaseDatosTorneo bdt = new BaseDatosTorneo(torneo.getNombreArchivo());
		bdt.insertarTorneoEliminacionDirecta(this);
		this.setCiclos(new ArrayList<Ciclo>());
		torneo.setAlgoritmoTorneo(this);
		this.realizarEncuentros();
		// torneoEliminacionDirecta.realizarEncuentros();

	}

}