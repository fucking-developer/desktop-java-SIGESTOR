package sigestor.bd;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sigestor.dominio.Personalizacion;
import sigestor.dominio.Torneo;
import sigestor.dominio.TorneoEliminacionDirecta;
import sigestor.dominio.TorneoRoundRobin;
import sigestor.dominio.TorneoSuizo;
import sigestor.excepcion.*;

/**
 * Sirve para manejar la base datos general del torneo.
 * 
 * @version 21/03/2023
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Francisco Samuel Reyes Cortes
 * @author Hernán Sesaí Lopéz Aragón
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 */
public class BaseDatosTorneo extends BaseDatos {

	/**
	 * Permite validar sí existe el archivo de la base de datos y además que existan
	 * todas las tablas correspondientes, en caso contrario crea la base de datos.
	 * 
	 * @param nombreArchivo
	 *            Nombre del archivo de la base de datos.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción sí ocurre un error al validar la existencia
	 *             del archivo y al validar las tablas o sí ocurre un error al crear
	 *             la base de datos.
	 *
	 */
	public BaseDatosTorneo(String nombreArchivo) throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		super(nombreArchivo);
		File archivo = new File(getNombreArchivo());
		if (!archivo.exists() || !validarTablas()) {
			crearBaseDatos();
		}
	}

	/**
	 * Permite crear la base datos junto con todas las tablas correspondientes al
	 * documento diccionario de datos.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             lanza la excepcion si no se puede crear la base de datos.
	 */
	private void crearBaseDatos() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		try {
			realizarConexion();
			realizarAccion(
					"CREATE TABLE datosGenerales (nombreTorneo TEXT PRIMARY KEY, nombreOrganizador TEXT, fechaInicioTorneo TEXT, fechaFinalTorneo TEXT, tipoTorneo TEXT DEFAULT 'suizo', cicloActual INTEGER DEFAULT 0, descripcion TEXT)");

			realizarAccion("CREATE TABLE suizo(numeroCiclos INTEGER DEFAULT 1)");
			realizarAccion("CREATE TABLE roundRobin(numeroCiclos INTEGER DEFAULT 1, numeroVueltas INTEGER DEFAULT 1)");
			realizarAccion(
					"CREATE TABLE criteriosDesempates(numeroCriterio INTEGER NOT NULL PRIMARY KEY, nombreCriterio TEXT NOT NULL)");
			realizarAccion(
					"CREATE TABLE personalizacion(nombreCiclo TEXT, nombreParticipante TEXT,nombreParticipanteInicial TEXT, nombreParticipanteFinal TEXT, "
							+ "nombreEncuentro TEXT, nombreParticipanteSinEncuentro TEXT, existenciaMarcador BOOLEAN DEFAULT false, nombreMarcador TEXT, puntajeGanar"
							+ " REAL DEFAULT 0.0, puntajeEmpatar REAL DEFAULT 0.0, puntajePerder REAL DEFAULT 0.0, ordenPuntaje BOOLEAN DEFAULT false, ordenAlfabetico BOOLEAN DEFAULT false, ordenAleatorio BOOLEAN DEFAULT false)");
			realizarAccion("CREATE TABLE ciclo(numeroCiclo INTEGER DEFAULT 0 PRIMARY KEY)");
			realizarAccion(
					"CREATE TABLE encuentro(numeroEncuentro INTEGER DEFAULT 0, idParticipanteInicial INTEGER DEFAULT 0, idParticipanteFinal INTEGER DEFAULT 0, marcadorParticipanteInicial INTEGER DEFAULT 0, marcadorParticipanteFinal INTEGER DEFAULT 0, resultado INTEGER DEFAULT 0, numeroCiclo INTEGER DEFAULT 0, fechaDelEncuentro TEXT)");
			realizarAccion(
					"CREATE TABLE participante(numeroParticipante INTEGER DEFAULT 0, nombreParticipante  TEXT, puntajeParticipante REAL DEFAULT 0.0, puntajeAcumuladoParticipante REAL DEFAULT 0.0, marcadorFavor INTEGER DEFAULT 0, marcadorContra INTEGER DEFAULT 0, lugarParticipante INTEGER DEFAULT 0)");
			realizarAccion("CREATE TABLE eliminacionDirecta(numeroCiclos INTEGER DEFAULT 1)");
			cerrarConexion();
		} catch (ExcepcionBaseDatos e) {

			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_CREA_BASE_DATOS_PARTE_1
					+ getNombreArchivo() + ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_CREA_BASE_DATOS_PARTE_2
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Verifica la existencia de todas las tablas en la base de datos.
	 * 
	 * @return <tt>true</tt> sí verifica la existencia de todas las tablas en la
	 *         base de datos, <tt>false</tt> en caso de que la base de datos esté
	 *         vacía.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción sí ocurre un error al obtener las tablas o sí
	 *             el archivo no es del sistema.
	 */
	private boolean validarTablas() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		int contador = 0;
		boolean band = false;
		ArrayList<String> tablas = obtenerTablas();
		for (String e : tablas) {
			if (e.equals("datosGenerales") || e.equals("suizo") || e.equals("roundRobin")
					|| e.equals("criteriosDesempates") || e.equals("personalizacion") || e.equals("ciclo")
					|| e.equals("encuentro") || e.equals("participante") || e.equals("eliminacionDirecta")) {
				contador++;
			}
		}
		if (tablas.size() == 9 && contador == 9) {
			band = true;
		} else if (tablas.size() == 0) {
			band = false;
		} else if (tablas.size() != 9 || contador != 9) {
			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_VALIDA_TABLAS
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}
		return band;
	}

	/**
	 * Obtiene los datos generales del torneo
	 * 
	 * @return regresa un objeto de tipo torneo con los datos generales
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error al obtener los datos
	 *             generales de la tabla <code>datosGenerales</code> de la base de
	 *             datos.
	 */
	public Torneo obtenerDatosGenerales() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		try {
			realizarConexion();
			Torneo torneo = new Torneo();
			ResultSet resultado = realizarConsulta(
					"SELECT nombreTorneo, nombreOrganizador, fechaInicioTorneo, fechaFinalTorneo, tipoTorneo, cicloActual, descripcion FROM datosGenerales");
			resultado.next();
			torneo.setNombreTorneo(resultado.getString(1));
			torneo.setNombreOrganizador(resultado.getString(2));
			SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

			Date fechaInicio = formato.parse(resultado.getString(3));
			torneo.setFechaInicioTorneo(fechaInicio);
			Date fechaFinal = formato.parse(resultado.getString(4));
			torneo.setFechaFinalTorneo(fechaFinal);
			torneo.setTipoTorneo(resultado.getString(5));
			torneo.setCicloActual(resultado.getInt(6));
			torneo.setDescripcionTorneo(resultado.getString(7));
			resultado.close();
			cerrarConexion();
			return torneo;
		} catch (SQLException | ParseException e) {
			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_CONSULTA_TORNEO
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Inserta los datos generales a la base de datos.
	 * 
	 * @param torneo
	 *            Contiene los datos generales del torneo.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * 
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error al insertar en la tabla
	 *             <code>datosGenerales</code> en la base de datos.
	 */
	public void insertarDatosGenerales(Torneo torneo) throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

		Date dateFechaInicioTorneo = torneo.getFechaInicioTorneo();
		String cadenaFechaInicial = formato.format(dateFechaInicioTorneo);

		Date dateFechaFinalTorneo = torneo.getFechaFinalTorneo();
		String cadenaFechaFinal = formato.format(dateFechaFinalTorneo);
		realizarConexion();
		int filasAfectadas = realizarAccion("INSERT INTO datosGenerales VALUES ('" + torneo.getNombreTorneo() + "', '"
				+ torneo.getNombreOrganizador() + "', '" + cadenaFechaInicial + "', '" + cadenaFechaFinal + "', '"
				+ torneo.getTipoTorneo() + "', " + torneo.getCicloActual() + ", '" + torneo.getDescripcionTorneo()
				+ "')");
		cerrarConexion();
		if (filasAfectadas != 1) {
			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_INSERTA_DATOS_GENERALES
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}

	}

	/**
	 * Actualiza los datos generales guardados en la base de datos.
	 * 
	 * @param torneo
	 *            Contiene los datos generales del torneo.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción sí ocurre un error al actualizar la tabla
	 *             <code>datosGenerales</code> en la base de datos.
	 */
	public void actualizarDatosGenerales(Torneo torneo) throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

		Date dateFechaInicioTorneo = torneo.getFechaInicioTorneo();
		String cadenaFechaInicial = formato.format(dateFechaInicioTorneo);

		Date dateFechaFinalTorneo = torneo.getFechaFinalTorneo();
		String cadenaFechaFinal = formato.format(dateFechaFinalTorneo);

		realizarConexion();
		int filasAfectadas = realizarAccion("UPDATE datosGenerales SET nombreTorneo = '" + torneo.getNombreTorneo()
		+ "', nombreOrganizador = '" + torneo.getNombreOrganizador() + "', fechaInicioTorneo = '"
		+ cadenaFechaInicial + "', fechaFinalTorneo = '" + cadenaFechaFinal + "', cicloActual = "
		+ torneo.getCicloActual() + ", tipoTorneo = '" + torneo.getTipoTorneo() + "', descripcion = '"
		+ torneo.getDescripcionTorneo() + "'");
		cerrarConexion();
		if (filasAfectadas != 1) {
			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_ACTUALIZA_DATOS_GENERALES
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}

	}

	/**
	 * Actualiza el ciclo actual guardado en la base de datos.
	 * 
	 * @param torneo
	 *            Contiene los datos generales del torneo.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción sí ocurre un error al actualizar el ciclo
	 *             actual en tabla <code>datosGenerales</code> de la base de datos.
	 */
	public void actualizarCicloActual(Torneo torneo) throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		realizarConexion();
		int filasAfectadas = realizarAccion("UPDATE datosGenerales SET" + " cicloActual = " + torneo.getCicloActual());
		cerrarConexion();
		if (filasAfectadas != 1) {
			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_ACTUALIZA_CICLO_ACTUAL_PARTE_1
					+ torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR)
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_ACTUALIZA_CICLO_ACTUAL_PARTE_2
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}

	}

	/**
	 * Obtiene los ciclos del torneo suizo de la base de datos
	 * 
	 * @return Regresa el número de ciclos del torneo
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error al obtener el número de
	 *             ciclos en la tabla <code>suizo</code> de la base de datos.
	 */
	public int obtenerTorneoSuizo() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		try {
			realizarConexion();
			ResultSet resultado = realizarConsulta("SELECT *FROM suizo");
			return resultado.getInt("numeroCiclos");
		} catch (SQLException e) {
			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_CONSULTA_CICLO_SUIZO
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		} finally {
			cerrarConexion();
		}
	}

	/**
	 * Obtiene los ciclos del torneo round robin de la base de datos
	 * 
	 * @return Regresa el número de ciclos del torneo
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error al obtener el número de
	 *             ciclos en la tabla <code>roundRobin</code> de la base de datos.
	 */
	public int obtenerTorneoRoundRobin() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		try {
			realizarConexion();
			ResultSet resultado = realizarConsulta("SELECT *FROM roundRobin");
			return resultado.getInt("numeroCiclos");
		} catch (SQLException e) {
			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_CONSULTA_CICLO_ROUND_ROBIN
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		} finally {
			cerrarConexion();
		}
	}

	/**
	 * Obtiene los ciclos del torneo eliminacionDirecta de la base de datos
	 * 
	 * @return Regresa el número de ciclos del torneo
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error al obtener el número de
	 *             ciclos en la tabla <code>eliminacionDirecta</code> de la base de
	 *             datos.
	 */
	public int obtenerTorneoEliminacionDirecta() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		try {
			realizarConexion();
			ResultSet resultado = realizarConsulta("SELECT *FROM eliminacionDirecta");
			return resultado.getInt("numeroCiclos");
		} catch (SQLException e) {
			throw new ExcepcionBaseDatosTorneo(
					ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_CONSULTA_CICLO_ELIMINACION_DIRECTA
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		} finally {
			cerrarConexion();
		}
	}

	/**
	 * Inserta el número de ciclos en la tabla <code>suizo</code> de la base de
	 * datos.
	 * 
	 * @param torneoSuizo
	 *            Contiene los datos generales del torneo suizo.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error al insertar el número de
	 *             ciclos en la tabla <code>suizo</code> de la base de datos.
	 */
	public void insertarTorneoSuizo(TorneoSuizo torneoSuizo) throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {

		realizarConexion();
		int resultado = realizarAccion("INSERT INTO suizo VALUES(" + torneoSuizo.getNumeroCiclos() + ")");
		cerrarConexion();
		if (resultado != 1) {
			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_INSERTA_TORNEO_SUIZO
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Inserta el número de ciclos y el número de vueltas en la tabla
	 * <code>roundRobin</code> de la base de datos.
	 * 
	 * @param robin
	 *            Contiene el número de ciclos y el número de vueltas del torneo.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción sí ocurre un error al insertar los datos en la
	 *             tabla <code>roundRobin</code> de la base de datos.
	 */
	public void insertarTorneoRoundRobin(TorneoRoundRobin robin) throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		realizarConexion();
		int valor = realizarAccion("INSERT INTO roundRobin (numeroCiclos, numeroVueltas) VALUES("
				+ robin.getNumeroCiclos() + "," + robin.getNumeroVueltas() + ")");
		cerrarConexion();
		if (valor != 1) {
			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_INSERTA_TORNEO_ROUND_ROBIN
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Inserta el número de ciclos en la tabla <code>eliminacionDirecta</code> de la
	 * base de datos.
	 * 
	 * @param eliminacionDirecta
	 *            Contiene el número de ciclos del torneo.
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción sí ocurre un error al insertar los datos en la
	 *             tabla <code>eliminacionDirecta</code> de la base de datos.
	 */
	public void insertarTorneoEliminacionDirecta(TorneoEliminacionDirecta eliminacionDirecta)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		realizarConexion();
		int valor = realizarAccion(
				"INSERT INTO eliminacionDirecta VALUES(" + eliminacionDirecta.getNumeroCiclos() + ")");
		cerrarConexion();
		if (valor != 1) {
			throw new ExcepcionBaseDatosTorneo(
					ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_INSERTA_TORNEO_ELIMINACION_DIRECTA
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Elimina el número de ciclos en la tabla <code>suizo</code> de la base de
	 * datos.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error al eliminar el número de
	 *             ciclos en la tabla <code>suizo</code> de la base de datos.
	 */
	public void eliminarTorneoSuizo() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		realizarConexion();
		int resultado = realizarAccion("DELETE FROM suizo WHERE numeroCiclos > 0");
		cerrarConexion();
		if (resultado < 0) {
			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_ELIMINA_TORNEO_SUIZO
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Elimina los datos de la tabla <code>roundRobin</code> de la base de datos.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error al eliminar los datos de la
	 *             tabla <code>roundRobin</code> de la base de datos.
	 */
	public void eliminarTorneoRoundRobin() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		realizarConexion();
		int valor = realizarAccion("DELETE FROM roundRobin WHERE numeroCiclos > 0");
		cerrarConexion();
		if (valor < 0) {
			throw new ExcepcionBaseDatosTorneo(ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_ELIMINA_TORNEO_ROUND_ROBIN
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Elimina los datos de la tabla <code>eliminacionDirecta</code> de la base de
	 * datos.
	 * 
	 * @throws ExcepcionBaseDatos
	 *             Lanza la excepción sí no se pudo realizar la conexión.
	 * @throws ExcepcionBaseDatosTorneo
	 *             Lanza la excepción si ocurre un error al eliminar los datos de la
	 *             tabla <code>eliminacionDirecta</code> de la base de datos.
	 */
	public void eliminarTorneoEliminacionDirecta() throws ExcepcionBaseDatos, ExcepcionBaseDatosTorneo {
		realizarConexion();
		int resultado = realizarAccion("DELETE FROM eliminacionDirecta WHERE numeroCiclos > 0");
		cerrarConexion();
		if (resultado < 0) {
			throw new ExcepcionBaseDatosTorneo(
					ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_ELIMINA_TORNEO_ELIMINACION_DIRECTA
					+ ExcepcionBaseDatosTorneo.MENSAJE_EXCEPCION_SOLUCION);
		}
	}
}
