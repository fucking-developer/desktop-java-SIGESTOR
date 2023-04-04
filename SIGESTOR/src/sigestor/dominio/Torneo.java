package sigestor.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import sigestor.bd.BaseDatosCiclo;
import sigestor.bd.BaseDatosCriteriosDesempate;
import sigestor.bd.BaseDatosEncuentro;
import sigestor.bd.BaseDatosParticipante;
import sigestor.bd.BaseDatosPersonalizacion;
import sigestor.bd.BaseDatosTorneo;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosCiclo;
import sigestor.excepcion.ExcepcionBaseDatosCriteriosDesempate;
import sigestor.excepcion.ExcepcionBaseDatosEncuentro;
import sigestor.excepcion.ExcepcionBaseDatosParticipante;
import sigestor.excepcion.ExcepcionBaseDatosPersonalizacion;
import sigestor.excepcion.ExcepcionBaseDatosTorneo;
import sigestor.excepcion.ExcepcionCapturarResultados;
import sigestor.excepcion.ExcepcionCriteriosDesempate;
import sigestor.excepcion.ExcepcionParticipante;
import sigestor.excepcion.ExcepcionTorneo;

/**
 * Sirve para guardar y validar la información ingresada en el
 * <code>PanelGeneral</code>.
 * <p>
 * Las características de la clase <code>Torneo</code> son:
 * <ul>
 * <li><code>datosPersonalizacion</code> Para almacenar los datos de
 * personalización.</li>
 * <li><code>listaParticipantes</code> Para almacenar la lista de los
 * participantes del torneo.</li>
 * <li><code>criteriosDesempate</code> Para almacenar los criterios de desempate
 * seleccionados.</li>
 * <li><code>baseDatos</code> Para almacenar la base de datos del torneo.</li>
 * <li><code>nombreTorneo</code> Para almacenar el nombre del torneo.</li>
 * <li><code>tipoTorneo</code> Para almacenar el tipo de torneo que se
 * iniciará.</li>
 * <li><code>nombreOrganizador</code>Para almacenar el nombre del organizador
 * del torneo.</li>
 * <li><code>fechaInicioTorneo</code> Para almacenar la fecha en que se iniciará
 * el torneo.</li>
 * <li><code>fechaFinalTorneo</code> Para almacenar la fecha en que finalizará
 * el torneo.</li>
 * <li><code>cicloActual</code> Para almacenar el ciclo en que se encuentra el
 * torneo.</li>
 * <li><code>algoritmoTorneo</code> Para almacenar el algoritmo con que podrá
 * avanzar el torneo.</li>
 * </ul>
 * 
 * @version 04/04/2023
 * 
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * @author Lopez Aragon Hernan Sesai
 * @author German Luis Cruz Martínez
 * 
 */
public class Torneo {

	/**
	 * Datos personalizados para el torneo.
	 */
	private Personalizacion datosPersonalizacion;
	/**
	 * Lista de los participantes del torneo.
	 */
	private ArrayList<Participante> listaParticipantes;
	/**
	 * Criterios de desempate que se utilizarán en el torneo.
	 */
	private CriteriosDesempate criteriosDesempate;
	/**
	 * Base de datos del torneo.
	 */
	private String nombreArchivo;
	/**
	 * Nombre del torneo.
	 */
	private String nombreTorneo;
	/**
	 * Tipo de torneo que se llevará a cabo.
	 */
	private String tipoTorneo;
	/**
	 * Nombre del organizador del torneo.
	 */
	private String nombreOrganizador;
	/**
	 * Descripción del torneo.
	 */
	private String descripcionTorneo;
	/**
	 * Fecha inicial del torneo
	 */
	private Date fechaInicioTorneo;
	/**
	 * Fecha en que finalizará el torneo.
	 */
	private Date fechaFinalTorneo;
	/**
	 * Ciclo en que se encuentra el torneo, si su valor es <tt> 0 </tt> el torneo no
	 * ha sido iniciado.
	 */
	private int cicloActual;

	/**
	 * Algoritmo que se utilizará para el avance del torneo.
	 */
	private AlgoritmoTorneo algoritmoTorneo;

	/**
	 * Permite crear un nuevo torneo.
	 * 
	 */
	public Torneo() {
		setDatosPersonalizacion(null);
		setListaParticipantes(null);
		setCriteriosDesempate(null);
		setNombreArchivo("");
		setNombreTorneo("");
		setTipoTorneo("suizo");
		setNombreOrganizador("");
		setDescripcionTorneo("");
		setFechaInicioTorneo(null);
		setFechaFinalTorneo(null);
		setCicloActual(0);
		setAlgoritmoTorneo(null);
	}

	/**
	 * Método que sirver para devolver el valor de la variable
	 * <code>nombreArchivo</code>
	 * 
	 * @return NombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * Método que sirve para recibir el valor de la variable
	 * <code>nombreArchivo</code>
	 * 
	 * @param nombreArchivo
	 *            El nombre del archivo que contendrá el torneo.
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * Devuelve los datos de personalización del torneo.
	 * 
	 * @return Regresa el objeto de tipo <code>Personalizacion</code>.
	 * @see Personalizacion
	 */
	public Personalizacion getDatosPersonalizacion() {
		return datosPersonalizacion;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>datosPersonalizacion</code>.
	 * 
	 * @param datosPersonalizacion
	 *            Recibe la variable de tipo <code>Personalizacion</code>.
	 */
	public void setDatosPersonalizacion(Personalizacion datosPersonalizacion) {
		this.datosPersonalizacion = datosPersonalizacion;
	}

	/**
	 * Devuelve la lista de los participantes del torneo.
	 * 
	 * @return Regresa la lista de participantes del torneo.
	 * @see Participante
	 */
	public ArrayList<Participante> getListaParticipantes() {
		return listaParticipantes;
	}

	/**
	 * Asigna el parámetro recibido a la lista <code>listaParticipantes</code>.
	 * 
	 * @param listaParticipantes
	 *            Recibe la lista de los participantes del torneo.
	 */
	public void setListaParticipantes(ArrayList<Participante> listaParticipantes) {
		this.listaParticipantes = listaParticipantes;
	}

	/**
	 * Devuelve los criterios de desempate seleccionados.
	 * 
	 * @return Regresa los criterios de desempate seleccionados.
	 * @see CriteriosDesempate
	 */
	public CriteriosDesempate getCriteriosDesempate() {
		return criteriosDesempate;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>criteriosDesempate</code>.
	 * 
	 * @param criteriosDesempate
	 *            Recibe la variable de tipo <code>CriteriosDesempate</code>.
	 */
	public void setCriteriosDesempate(CriteriosDesempate criteriosDesempate) {
		this.criteriosDesempate = criteriosDesempate;
	}

	/**
	 * Devuelve el nombre del torneo.
	 * 
	 * @return Regresa el nombre del torneo.
	 * 
	 */
	public String getNombreTorneo() {
		return nombreTorneo;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>nombreTorneo</code> y
	 * elimina espacios si existen al inicio y al final de la cadena.
	 * 
	 * @param nombreTorneo
	 *            Recibe el nombre del torneo.
	 */
	public void setNombreTorneo(String nombreTorneo) {
		this.nombreTorneo = nombreTorneo.trim();
	}

	/**
	 * Devuelve el tipo de torneo.
	 * 
	 * @return Regresa el tipo de torneo, <tt>"Suizo"</tt> en caso de el torneo sea
	 *         Suizo, <tt>"Round Robin"</tt> en caso de ser Round Robin,
	 *         <tt>"Eliminación directa"</tt> en caso de que el torneo sea
	 *         Eliminación directa.
	 */

	public String getTipoTorneo() {
		return tipoTorneo;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>tipoTorneo</code> y elimina
	 * espacios si existen al inicio y al final de la cadena.
	 * 
	 * @param tipoTorneo
	 *            Recibe el tipo de torneo, <tt>"Suizo"</tt> en caso de que el
	 *            torneo sea Suizo, <tt>"Round Robin"</tt> en caso de ser Round
	 *            Robin,<tt>"Eliminación directa"</tt> en caso de que el torneo sea
	 *            Eliminación directa.
	 */

	public void setTipoTorneo(String tipoTorneo) {
		this.tipoTorneo = tipoTorneo.trim();
	}

	/**
	 * Devuelve el nombre del organizador.
	 * 
	 * @return Regresa el nombre del organizador.
	 */

	public String getNombreOrganizador() {
		return nombreOrganizador;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>nombreOrganizador</code> y
	 * elimina espacios si existen al inicio y al final de la cadena.
	 * 
	 * @param nombreOrganizador
	 *            Recibe el nombre del organizador.
	 */

	public void setNombreOrganizador(String nombreOrganizador) {
		this.nombreOrganizador = nombreOrganizador.trim();
	}

	/**
	 * Devuelve la fecha de inicio del torneo.
	 * 
	 * @return Regresa la fecha de inicio del torneo.
	 */

	public Date getFechaInicioTorneo() {
		return fechaInicioTorneo;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>fechaInicioTorneo</code>.
	 * 
	 * @param fechaInicioTorneo
	 *            Recibe la fecha en que iniciará el torneo.
	 */

	public void setFechaInicioTorneo(Date fechaInicioTorneo) {
		this.fechaInicioTorneo = fechaInicioTorneo;
	}

	/**
	 * Devuelve la fecha en que finalizará el torneo.
	 * 
	 * @return Regresa la fecha en que finalizará el torneo.
	 */

	public Date getFechaFinalTorneo() {
		return fechaFinalTorneo;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>fechaFinalTorneo</code>.
	 * 
	 * @param fechaFinalTorneo
	 *            Recibe la fecha en que finalizará el torneo.
	 */
	public void setFechaFinalTorneo(Date fechaFinalTorneo) {
		this.fechaFinalTorneo = fechaFinalTorneo;
	}

	/**
	 * Devuelve el ciclo actual del torneo.
	 * 
	 * @return Regresa el ciclo actual del torneo,<tt> 0 </tt> en caso de el torneo
	 *         no ha sido iniciado.
	 */

	public int getCicloActual() {
		return cicloActual;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>cicloActual</code>.
	 * 
	 * @param cicloActual
	 *            Recibe el ciclo actual del torneo.
	 */

	public void setCicloActual(int cicloActual) {
		this.cicloActual = cicloActual;
	}

	/**
	 * Devuelve una descripción del torneo.
	 * 
	 * @return Regresa la descripción del torneo.
	 */
	public String getDescripcionTorneo() {
		return descripcionTorneo;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>descripcionTorneo</code> y
	 * elimina espacios si existen al inicio o al final de la cadena.
	 * 
	 * @param descripcionTorneo
	 *            Recibe la descripción del torneo.
	 */

	public void setDescripcionTorneo(String descripcionTorneo) {
		this.descripcionTorneo = descripcionTorneo.trim();
	}

	/**
	 * Devuelve la variable <code>algoritmoTorneo</code>.
	 * 
	 * @return Regresa la variable de tipo <code>AlgoritmoTorneo</code>.
	 * @see AlgoritmoTorneo
	 */
	public AlgoritmoTorneo getAlgoritmoTorneo() {
		return algoritmoTorneo;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>algoritmoTorneo</code>.
	 * 
	 * @param algoritmoTorneo
	 *            Recibe el algoritmo del torneo.
	 */
	public void setAlgoritmoTorneo(AlgoritmoTorneo algoritmoTorneo) {
		this.algoritmoTorneo = algoritmoTorneo;
	}

	/**
	 * Sirve para ordenar a los participantes antes de iniciar el torneo.
	 * 
	 */
	private void ordenarParticipantes() {
		Collections.sort(listaParticipantes);
	}

	/**
	 * Inicia el torneo dependiendo el tipo de torneo que es.
	 * 
	 * @param torneoRoundRobin
	 *            Recibe el torneo <code>TorneoRoundRobin</code> si es el caso, en
	 *            caso contrario recibe <tt>null</tt>.
	 * @param torneoSuizo
	 *            Recibe el torneo <code>TorneoSuizo</code> si es el caso, en caso
	 *            contrario recibe <tt>null</tt>.
	 * @param torneoEliminacionDirecta
	 *            Recibe el torneo <code>TorneoEliminacionDirecta</code> si es el
	 *            caso, en caso contrario recibe <tt>null</tt>.
	 * @throws ExcepcionBaseDatosCiclo
	 *             Lanza la excepción si ocurre un error en
	 *             <code>BaseDatosCiclo</code>.
	 * @throws ExcepcionBaseDatosEncuentro
	 *             Lanza la excepción si ocurre un error en
	 *             <code>BaseDatosEncuentro</code>.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción si ocurre un error en <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error en
	 *             <code>BaseDatosTorneo</code>.
	 * @throws ExcepcionBaseDatosParticipante
	 *             Lanza la excepción si ocurre un error en
	 *             <code>BaseDatosParticipante</code>.
	 * @throws ExcepcionCapturarResultados
	 *             Lanza la excepción si ocurre un error al crear el objeto
	 *             <code>Encuentro</code>.
	 * 
	 */
	public void iniciarTorneo(TorneoRoundRobin torneoRoundRobin, TorneoSuizo torneoSuizo,
			TorneoEliminacionDirecta torneoEliminacionDirecta)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCiclo, ExcepcionBaseDatosTorneo,
			ExcepcionBaseDatosParticipante, ExcepcionCapturarResultados {
		
		ordenarParticipantes();
		if (this.getTipoTorneo().equals("Suizo")) {
			torneoSuizo.iniciarTorneo(torneoSuizo);
		} else if (this.getTipoTorneo().equals("Eliminación directa")) {
			torneoEliminacionDirecta.iniciarTorneo(torneoEliminacionDirecta);
		} else if (this.getTipoTorneo().equals("Round Robin")) {
			torneoRoundRobin.iniciarTorneo();
		}

	}

	/**
	 * Permite validar los datos ingresados en los paneles:
	 * <code>PanelGeneral</code>, <code>PanelCriteriosDesempate</code> y
	 * <code>PanelAdministrarParticipantes</code>.
	 * 
	 * @throws ExcepcionTorneo
	 *             Lanza la excepción si la variable <code>nombreTorneo</code> se
	 *             encuentra vacía.
	 * 
	 *             Lanza la excepción si la variable <code>nombreOrganizador</code>
	 *             se encuentra vacía.
	 * 
	 *             Lanza la excepción si la variable <code>fechaFinalTorneo</code>
	 *             es menor que la variable <code>fechaInicialTorneo</code>.
	 * 
	 *             Lanza la excepción si la variable <code>listaParticipantes</code>
	 *             contiene menos de dos participantes.
	 * 
	 *             Lanza la excepción si la variable <code>criteriosDesempate</code>
	 *             contiene menos de un criterio de desempate seleccionado.
	 * 
	 *             Lanza la excepción si la variable
	 *             <code>datosPersonalizacion</code> no tiene seleccionada la
	 *             existencia de marcadores y se seleccionaron los criterios de
	 *             desempate: "Diferencia de marcadores", "Marcador a favor",
	 *             "Marcador en contra" y "Marcador de participante final".
	 * 
	 *             Lanza la excepción si la variable <code>tipoTorneo</code> es
	 *             "Round Robin" y se seleccionó el criterio de desempate
	 *             "Bucholtz".
	 * 
	 *             Lanza la excepción si la variable <code>tipoTorneo</code> es
	 *             "Eliminación directa" y si fueron seleccionados otros criterios
	 *             de desempate a excepción de "Puntuación" y "Marcador de
	 *             participante final".
	 */
	public void validarTorneo() throws ExcepcionTorneo {
		if (nombreTorneo.isEmpty()) {
			throw new ExcepcionTorneo(ExcepcionTorneo.MENSAJE_EXCEPCION_NOMBRE_VACIO);
		}
		if (nombreOrganizador.isEmpty()) {
			throw new ExcepcionTorneo(ExcepcionTorneo.MENSAJE_EXCEPCION_ORGANIZADOR_VACIO);
		}
		if (fechaFinalTorneo.before(fechaInicioTorneo)) {
			throw new ExcepcionTorneo(ExcepcionTorneo.MENSAJE_EXCEPCION_FECHAS_INCOHERENTES);
		}
		if (listaParticipantes.size() < 2) {
			throw new ExcepcionTorneo(ExcepcionParticipante.MENSAJE_EXCEPCION_LISTA_PARTICIPANTE_INCOMPLETOS);
		}
		if (criteriosDesempate.getListaCriteriosSeleccionados().size() < 1) {
			throw new ExcepcionTorneo(ExcepcionCriteriosDesempate.MENSAJE_EXCEPCION_LISTA_VACIA);
		}
		if (!datosPersonalizacion.isExistenciaMarcador()) {
			for (String criterio : criteriosDesempate.getListaCriteriosSeleccionados()) {
				if (criterio.equals("Diferencia de marcadores") || criterio.equals("Marcador a favor")
						|| criterio.equals("Marcador en contra") || criterio.equals("Marcador de participante final")) {
					throw new ExcepcionTorneo(ExcepcionCriteriosDesempate.MENSAJE_EXCEPCION_CRITERIOS_INVALIDOS);
				}
			}
		}
		if (tipoTorneo.equals("Round Robin")) {
			for (String criterio : criteriosDesempate.getListaCriteriosSeleccionados()) {
				if (criterio.equals("Bucholtz")) {
					throw new ExcepcionTorneo(ExcepcionCriteriosDesempate.MENSAJE_EXCEPCION_CRITERIOS_TIPO_TORNEO);
				}
			}
		}
		if (tipoTorneo.equals("Eliminación directa")) {
			for (String criterio : criteriosDesempate.getListaCriteriosSeleccionados()) {
				if (!criterio.equals("Puntuación") && !criterio.equals("Marcador de participante final")) {
					throw new ExcepcionTorneo(
							ExcepcionCriteriosDesempate.MENSAJE_EXCEPCION_CRITERIOS_INVALIDOS_ELIMINACION_DIRECTA);
				}
			}
		}
	}

	/**
	 * Permite recuperar los datos de la base de datos para ingresarlos al objeto
	 * <code>Torneo</code>.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción si ocurre un error en <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error en
	 *             <code>BaseDatosTorneo</code>.
	 * @throws ExcepcionBaseDatosCriteriosDesempate
	 *             Lanza la excepción si ocurre un error en
	 *             <code>BaseDatosCriteriosDesempate</code>.
	 * @throws ExcepcionBaseDatosParticipante
	 *             Lanza la excepción si ocurre un error en
	 *             <code>BaseDatosParticipante</code>.
	 * @throws ExcepcionCapturarResultados
	 *             Lanza la excepción si ocurre un error al capturar los resultados.
	 * @throws ExcepcionBaseDatosPersonalizacion
	 *             Lanza la excepción si ocurre un error en
	 *             <code>BaseDatosPersonalizacion</code>.
	 */
	public void recuperarTorneo()
            throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo, ExcepcionBaseDatosCriteriosDesempate,
            ExcepcionBaseDatosParticipante, ExcepcionCapturarResultados, ExcepcionBaseDatosPersonalizacion {
        BaseDatosTorneo baseDatosTorneo = new BaseDatosTorneo(this.nombreArchivo);
        BaseDatosCriteriosDesempate baseDatosCriterios = new BaseDatosCriteriosDesempate(this.nombreArchivo);
        BaseDatosPersonalizacion baseDatosPersonalizacion = new BaseDatosPersonalizacion(this.nombreArchivo);
        BaseDatosParticipante baseDatosParticipantes = new BaseDatosParticipante(this.nombreArchivo);
       
        Torneo torneoConsultado = baseDatosTorneo.obtenerDatosGenerales();
        this.setNombreTorneo(torneoConsultado.getNombreTorneo());
        this.setTipoTorneo(torneoConsultado.getTipoTorneo());
        this.setNombreOrganizador(torneoConsultado.getNombreOrganizador());
        this.setDescripcionTorneo(torneoConsultado.getDescripcionTorneo());
        this.setFechaInicioTorneo(torneoConsultado.getFechaInicioTorneo());
        this.setFechaFinalTorneo(torneoConsultado.getFechaFinalTorneo());
        this.setCicloActual(torneoConsultado.getCicloActual());
        this.setCriteriosDesempate(baseDatosCriterios.obtenerCriteriosDesempate());
        this.setDatosPersonalizacion(baseDatosPersonalizacion.obtenerPersonalización());
        this.setListaParticipantes(baseDatosParticipantes.obtenerParticipante());

        AlgoritmoTorneo algoritmo = null;
        if (this.getCicloActual() > 0) {
            // Torneo iniciado            
            if (this.getTipoTorneo().equals("Suizo")) {
                algoritmo = baseDatosTorneo.obtenerTorneoSuizo();
            } else if (this.getTipoTorneo().equals("Round Robin")) {
                algoritmo = baseDatosTorneo.obtenerTorneoRoundRobin();
            } else if (this.getTipoTorneo().equals("Eliminación directa")) {
                algoritmo = baseDatosTorneo.obtenerTorneoEliminacionDirecta();
            }
            algoritmo.setTorneo(this);
        } else {
            // Torneo no iniciado
            if (this.getTipoTorneo().equals("Suizo")) {
                algoritmo = new TorneoSuizo(this);
            } else if (this.getTipoTorneo().equals("Round Robin")) {
                algoritmo = new TorneoRoundRobin(this);
            } else if (this.getTipoTorneo().equals("Eliminación directa")) {
                algoritmo = new TorneoEliminacionDirecta(this);
            }
        }
        this.setAlgoritmoTorneo(algoritmo);
        BaseDatosCiclo baseDatosCiclos = new BaseDatosCiclo(nombreArchivo);
        this.getAlgoritmoTorneo().setCiclos(baseDatosCiclos.obtenerCiclos(this));            
    }

	/**
	 * Permite guardar los datos de la base de datos para ingresarlos al objeto
	 * <code>Torneo</code>.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí ocurre un error en <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosTorneo</code>.
	 * @throws ExcepcionBaseDatosCriteriosDesempate
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosCriteriosDesempate</code>.
	 * @throws ExcepcionBaseDatosPersonalizacion
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosPersonalizacion</code>.
	 * @throws ExcepcionBaseDatosParticipante
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosParticipante</code>.
	 */
	public void guardarTorneo() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo,
			ExcepcionBaseDatosCriteriosDesempate, ExcepcionBaseDatosPersonalizacion, ExcepcionBaseDatosParticipante {
		BaseDatosTorneo baseDatosTorneo = new BaseDatosTorneo(this.nombreArchivo);
		BaseDatosCriteriosDesempate baseDatosCriterios = new BaseDatosCriteriosDesempate(this.nombreArchivo);
		BaseDatosPersonalizacion baseDatosPersonalizacion = new BaseDatosPersonalizacion(this.nombreArchivo);
		BaseDatosParticipante baseDatosParticipante = new BaseDatosParticipante(this.nombreArchivo);
		baseDatosTorneo.insertarDatosGenerales(this);
		baseDatosCriterios.insertarCriteriosDesempate(this.criteriosDesempate);
		baseDatosPersonalizacion.insertarPersonalizacion(this.datosPersonalizacion);
		baseDatosParticipante.insertarParticipante(this.listaParticipantes);
	}

	/**
	 * Permite actualizar los datos de la base de datos para ingresarlos al objeto
	 * <code>Torneo</code>.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí ocurre un error en <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosTorneo</code>.
	 * @throws ExcepcionBaseDatosCriteriosDesempate
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosCriteriosDesempate</code>.
	 * @throws ExcepcionBaseDatosPersonalizacion
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosPersonalizacion</code>.
	 * @throws ExcepcionBaseDatosParticipante
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosParticipante</code>.
	 */
	public void actualizarTorneo() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo,
			ExcepcionBaseDatosCriteriosDesempate, ExcepcionBaseDatosPersonalizacion, ExcepcionBaseDatosParticipante {
		BaseDatosTorneo baseDatosTorneo = new BaseDatosTorneo(this.nombreArchivo);
		BaseDatosCriteriosDesempate baseDatosCriterios = new BaseDatosCriteriosDesempate(this.nombreArchivo);
		BaseDatosPersonalizacion baseDatosPersonalizacion = new BaseDatosPersonalizacion(this.nombreArchivo);
		BaseDatosParticipante baseDatosParticipante = new BaseDatosParticipante(this.nombreArchivo);
		baseDatosTorneo.actualizarDatosGenerales(this);
		baseDatosCriterios.eliminarCriteriosDesempate();
		baseDatosCriterios.insertarCriteriosDesempate(this.getCriteriosDesempate());
		baseDatosPersonalizacion.actualizarPersonalizacion(this.getDatosPersonalizacion());
		baseDatosParticipante.eliminarParticipante();
		baseDatosParticipante.insertarParticipante(this.getListaParticipantes());
	}

	/**
	 * Permite actualizar los datos de la base de datos para ingresarlos al objeto
	 * <code>Torneo</code>.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí ocurre un error en <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosParticipante
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosParticipante</code>.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosTorneo</code>.
	 * @throws ExcepcionBaseDatosCiclo
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosCiclo</code>.
	 * @throws ExcepcionBaseDatosEncuentro
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosEncuentro</code>.
	 * @throws ExcepcionBaseDatosCriteriosDesempate
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosCriteriosDesempate</code>.
	 * @throws ExcepcionCapturarResultados
	 *             Lanza la excepción sí ocurre un error al capturar los resultados.
	 * @throws ExcepcionBaseDatosPersonalizacion
	 *             Lanza la excepción sí ocurre un error en
	 *             <code>BaseDatosPersonalizacion</code>.
	 */
	public void cancelarTorneo() throws ExcepcionBaseDatos, ExcepcionBaseDatosParticipante, ExcepcionBaseDatosTorneo,
			ExcepcionBaseDatosCiclo, ExcepcionBaseDatosEncuentro, ExcepcionBaseDatosCriteriosDesempate,
			ExcepcionCapturarResultados, ExcepcionBaseDatosPersonalizacion {
		setCicloActual(0);
		BaseDatosParticipante participantes = new BaseDatosParticipante(this.nombreArchivo);
		participantes.cancelarAvanceParticipante(this.listaParticipantes);
		BaseDatosCiclo bdc = new BaseDatosCiclo(this.nombreArchivo);
		BaseDatosEncuentro bde = new BaseDatosEncuentro(this.nombreArchivo);
		BaseDatosTorneo bdt = new BaseDatosTorneo(this.nombreArchivo);

		bdc.eliminarCiclos();
		bde.eliminarEncuentros();
		bdt.actualizarCicloActual(this);
		bdt.eliminarTorneoRoundRobin();
		bdt.eliminarTorneoSuizo();

		recuperarTorneo();

	}
}
