package sigestor.dominio;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import sigestor.bd.BaseDatosEncuentro;
import sigestor.bd.BaseDatosParticipante;
import sigestor.bd.BaseDatosTorneo;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosCiclo;
import sigestor.excepcion.ExcepcionBaseDatosEncuentro;
import sigestor.excepcion.ExcepcionBaseDatosParticipante;
import sigestor.excepcion.ExcepcionBaseDatosTorneo;
import sigestor.excepcion.ExcepcionCapturarResultados;
import sigestor.excepcion.ExcepcionUtilerias;
import sigestor.utilerias.UtileriasReporteCiclo;
import sigestor.utilerias.UtileriasReporteResultados;
import sigestor.utilerias.UtileriasReporteResultadosFinales;

/**
 * Sirve para realizar las operaciones del torneo, como: crear los ciclos, los
 * encuentros, desempatar jugadores y realizar reportes.
 * <p>
 * Las caracter�sticas de la clase <code>AlgoritmoTorneo</code> son:
 * <ul>
 * <li><code>torneo</code> almacena los datos generales del torneo.</li>
 * <li><code>numeroCiclos</code> guarda la cantidad m�xima de ciclos que tendr�
 * el torneo.</li>
 * <li><code>ciclos</code> contendr� los ciclos que se generen en el
 * torneo.</li>
 * <li><code>desempates</code> almacena en una lista los desempates que se
 * eligan en la personalizaci�n del torneo.</li>
 * </ul>
 * 
 * @version 10/04/2023
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz L�pez
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste P�rez
 * @author Jonathan Eduardo Ibarra Martinez
 * @author Hernan Sesai Lopez Aragon
 * @author Francisco Samuel Reyes Cortes
 */
public abstract class AlgoritmoTorneo {

	/**
	 * Contiene los datos generales del torneo, al igual que los crieterios de
	 * desempate, la lista de jugadores y el tipo de torneo a implementar.
	 */
	protected Torneo torneo;

	/**
	 * Contiene una lista de los ciclos generados en el torneo.
	 */
	private ArrayList<Ciclo> ciclos;

	/**
	 * Colecci�n con las clases de desempate del torneo.
	 */
	private ArrayList<Desempate> desempates;

	/**
	 * Valor que se calcula dependiendo de la cantidad de participantes del torneo.
	 */
	private int numeroCiclos;

	/**
	 * Inicializa las variables con un valor por defecto y asigna a la variable
	 * <code>torneo</code> el torneo recibido.
	 * 
	 * @param torneo Recibe un objeto <code>Torneo</code> que contiene los datos del
	 *               torneo.
	 */
	public AlgoritmoTorneo(Torneo torneo) {
		setTorneo(torneo);
		setCiclos(null);
		setDesempates(null);
		setNumeroCiclos(0);
	}

	/**
	 * Obtiene la cantidad m�xima de ciclos del torneo dependiendo del n�mero de
	 * participantes registrados.
	 * 
	 * @param numeroParticipantes Recibe la cantidad de participantes inscritos en
	 *                            el torneo.
	 * @return Regresa el n�mero m�ximo de ciclos que tendr� el torneo.
	 */
	public abstract int calcularNumeroCiclos(int numeroParticipantes);

	/**
	 * Asigana los encuentros que tendr� cada ciclo del torneo entre los
	 * participantes registrados.
	 * 
	 * @throws ExcepcionBaseDatosEncuentro Si ocurre un problema al insertar en la
	 *                                     tabla <code>encuentros</code>.
	 * @throws ExcepcionBaseDatos          Si ocurre un problema con la base de
	 *                                     datos.
	 * @throws ExcepcionBaseDatosCiclo     Si ocurre un error al insertar en la
	 *                                     tabla <code>ciclos</code>.s
	 * @throws ExcepcionCapturarResultados Si ocurre un error al capturar los
	 *                                     resultados
	 * @throws ExcepcionBaseDatosTorneo    Si ocurre un error en la base de datos
	 *                                     del torneo
	 */
	public abstract void realizarEncuentros() throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro,
			ExcepcionBaseDatosCiclo, ExcepcionCapturarResultados, ExcepcionBaseDatosTorneo;

	/**
	 * Genera un archivo CSV con los resultados parciales o finales de un ciclo del
	 * torneo.
	 * 
	 * @param archivo           Recibe el archivo que ser� escrito.
	 * @param comboSeleccionado Recibe el n�mero del combo seleccionado.
	 * @throws ExcepcionUtilerias Lanza la excepci�n s� ocurre un error al escrbir
	 *                            en el archivo CSV.
	 */
	public void generarReporteParciales(File archivo, int comboSeleccionado) throws ExcepcionUtilerias {
		UtileriasReporteResultados.escribirArchivoCsvReporteResultados(archivo.getPath(),
				this.ciclos.get(comboSeleccionado), torneo);
	}

	/**
	 * Aplica el/los criterio(s) de desempate(s) establecidos en
	 * <code>CriteriosDesempate</code>.
	 */
	public abstract void desempatarParticipantes();

	/**
	 * Genera un archivo CSV con los pareos de un ciclo de los participantes del
	 * torneo.
	 * 
	 * @param archivo           Recibe el archivo que ser� escrito.
	 * @param comboSeleccionado Recibe el n�mero del combo seleccionado.
	 * 
	 * @throws ExcepcionUtilerias Lanza la excepci�n s� ocurre un error al escrbir
	 *                            en el archivo CSV.
	 */
	public void generarReporteCiclo(File archivo, int comboSeleccionado) throws ExcepcionUtilerias {

		UtileriasReporteCiclo.escribirArchivoCsvReporteCiclo(archivo.getPath(), torneo,
				this.ciclos.get(comboSeleccionado));

	}

	/**
	 * Genera un archivo CSV con los resultados finales de los participantes del
	 * torneo.
	 * 
	 * @param archivo Recibe el archivo que ser� escrito.
	 * @throws ExcepcionUtilerias Lanza la excepci�n s� ocurre un error al escrbir
	 *                            en el archivo CSV.
	 */
	public void generarReporteFinal(File archivo) throws ExcepcionUtilerias {
		UtileriasReporteResultadosFinales.escribirArchivoCsvReporteResultadosFinales(archivo.getPath(), torneo);
	}

	/**
	 * Regresa el torneo con todos sus atributos y sus valores asignados.
	 * 
	 * @return Regresa un objeto de tipo <code>Torneo</code> con los datos del
	 *         torneo.
	 */
	public Torneo getTorneo() {
		return torneo;
	}

	/**
	 * Inicializa la variable <code>torneo</code> con el torneo recibido.
	 * 
	 * @param torneo Recibe un objeto <code>Torneo</code> con los datos del torneo.
	 */
	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	/**
	 * Obtiene una colecci�n con los ciclos del torneo.
	 * 
	 * @return Regresa una lista con los ciclos del torneo.
	 */
	public ArrayList<Ciclo> getCiclos() {
		return ciclos;
	}

	/**
	 * Inicializa la colecci�n de ciclos con la lista de ciclos recibida.
	 * 
	 * @param ciclos Lista de ciclos a establecer en la variable
	 *               <code>ciclos</code>.
	 */
	public void setCiclos(ArrayList<Ciclo> ciclos) {
		this.ciclos = ciclos;
	}

	/**
	 * Regresa una colecci�n de desempates que fueron establecidos en la clase
	 * <code>CriteriosDesempate</code>.
	 * 
	 * @return Regresa una lista con los desempates que se utilizar�n en el torneo.
	 */
	public ArrayList<Desempate> getDesempates() {
		return desempates;
	}

	/**
	 * Establece los desempates a utilizar en el torneo y los almacena en una
	 * colecci�n.
	 * 
	 * @param desempates Recibe una lista con los desempates establecidos al iniciar
	 *                   el torneo.
	 */
	public void setDesempates(ArrayList<Desempate> desempates) {
		this.desempates = desempates;
	}

	/**
	 * Regresa el n�mero de ciclos que haya sido calculado.
	 * 
	 * @return Regresa el n�mero de ciclos que tendr� el torneo.
	 */
	public int getNumeroCiclos() {
		return numeroCiclos;
	}

	/**
	 * Establece el n�mero de ciclos que tendr� el torneo.
	 * 
	 * @param numeroCiclos Recibe el n�mero de ciclos del torneo.
	 */
	public void setNumeroCiclos(int numeroCiclos) {
		this.numeroCiclos = numeroCiclos;
	}

	/**
	 * Actualiza el ciclo actual en la <code>BaseDatosTorneo</code>.
	 * 
	 * @param nombreArchivo Recibe el nombre de la base de datos del torneo.
	 */
	public void actualizarCiclo(String nombreArchivo) {
		try {
			BaseDatosTorneo bdt = new BaseDatosTorneo(nombreArchivo);
			bdt.actualizarCicloActual(this.getTorneo());
		} catch (ExcepcionBaseDatos | ExcepcionBaseDatosTorneo e) {

		}
	}

	/**
	 * Verifica los encuentros sin capturar.
	 * 
	 * @return <tt>true</tt> s� verifica que no exista resultados sin capturar,
	 *         <tt>false</tt> en caso de que existan resultados sin capturar.
	 */
	public boolean verificarResultadosCompletos() {
		BaseDatosEncuentro bdt = new BaseDatosEncuentro(this.getTorneo().getNombreArchivo());
		return bdt.verificarResultados();
	}

	/**
	 * Asigna un lugar al participante al finalizar el torneo y lo guarda en la
	 * <code>BaseDatosParticipante</code>.
	 */
	public void asignarLugarParticipante() {
		Participante.setPuntaje(true);
		Collections.sort(torneo.getListaParticipantes());
		int index = 1;
		for (Participante p : torneo.getListaParticipantes()) {
			p.setLugarParticipante(index);
			try {
				BaseDatosParticipante bdp = new BaseDatosParticipante(torneo.getNombreArchivo());
				bdp.actualizarLugarParticipante(p, torneo);
			} catch (ExcepcionBaseDatos | ExcepcionBaseDatosParticipante e) {

			}
			index++;
		}
	}
}
