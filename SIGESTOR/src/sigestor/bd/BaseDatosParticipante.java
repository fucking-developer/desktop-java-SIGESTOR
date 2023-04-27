package sigestor.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sigestor.dominio.*;
import sigestor.excepcion.*;

/**
 * Sirve para insertar, actualizar, eliminar, actualizar lugar y resultado del
 * participante de la base de datos.
 * 
 * @version 12/06/2022
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * @author Luis Fernando de la Cruz Lopez
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Victor Triste Pérez
 */
public class BaseDatosParticipante extends BaseDatos {

	/**
	 * Permite llamar el constructor de la clase padre.
	 * 
	 * @param nombreArchivo Nombre personalizado del archivo de la base de datos.
	 */

	public BaseDatosParticipante(String nombreArchivo) {
		super(nombreArchivo);
	}

	/**
	 * Inserta los datos del participante a la base de datos.
	 * 
	 * @param arrayList Contiene los datos del participante.
	 * @throws ExcepcionBaseDatos             Lanza la excepción sí no se pudo
	 *                                        realizar la conexión.
	 * @throws ExcepcionBaseDatosParticipante Lanza la excepción si no se pudieron
	 *                                        insertar los datos a la tabla
	 *                                        <code>participante</code> de la base
	 *                                        de datos.
	 * 
	 */
	public void insertarParticipante(ArrayList<Participante> arrayList)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosParticipante {

		for (Participante p : arrayList) {
			realizarConexion();
			int filasAfectadas = realizarAccion("INSERT INTO participante VALUES (" + p.getNumeroParticipante() + ",'"
					+ p.getNombreParticipante() + "'," + p.getPuntajeParticipante() + ","
					+ p.getPuntajeAcumuladoParticipante() + "," + p.getMarcadorFavor() + "," + p.getMarcadorContra()
					+ "," + p.getLugarParticipante() + ")");
			cerrarConexion();
			if (filasAfectadas != 1) {
				throw new ExcepcionBaseDatosParticipante(
						ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_INSERTA_PARTICIPANTE
								+ ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_SOLUCION);
			}
		}
	}

	/**
	 * Elimina el participante guardado en la base de datos.
	 * 
	 * @throws ExcepcionBaseDatos             Lanza la excepción sí no se pudo
	 *                                        realizar la conexión.
	 * @throws ExcepcionBaseDatosParticipante Lanza la excepción si ocurre un error
	 *                                        al eliminar los datos en la tabla
	 *                                        <code>participante</code> de la base
	 *                                        de datos.
	 * 
	 */
	public void eliminarParticipante() throws ExcepcionBaseDatos, ExcepcionBaseDatosParticipante {
		realizarConexion();
		int filasAfectadas = realizarAccion("DELETE FROM participante WHERE numeroParticipante>0");
		cerrarConexion();
		if (filasAfectadas < 0) {
			throw new ExcepcionBaseDatosParticipante(
					ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_ELIMINA_PARTICIPANTE
							+ ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * 
	 * Actualiza el lugar del participante guardado en la base de datos.
	 * 
	 * @param participante Contiene los datos generales del participante.
	 * @param torneo       Contiene los datos generales del torneo.
	 * @throws ExcepcionBaseDatos             Lanza la excepción sí no se pudo
	 *                                        realizar la conexión.
	 * @throws ExcepcionBaseDatosParticipante Lanza la excepción si ocurre un error
	 *                                        al actualizar el lugar del
	 *                                        participante en la tabla
	 *                                        <code>participante</code> de la base
	 *                                        de datos.
	 */
	public void actualizarLugarParticipante(Participante participante, Torneo torneo)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosParticipante {
		realizarConexion();
		int filasAfectadas = realizarAccion(
				"UPDATE participante SET lugarParticipante = " + participante.getLugarParticipante()
						+ " WHERE numeroParticipante = " + participante.getNumeroParticipante());
		cerrarConexion();
		if (filasAfectadas != 1) {
			throw new ExcepcionBaseDatosParticipante(
					ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_ACTUALIZA_LUGAR_PARTICIPANTE
							+ torneo.getDatosPersonalizacion().getNombreParticipante(Personalizacion.MINUSCULA_SINGULAR)
							+ ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * 
	 * Actualiza el resultado del participante guardado en la base de datos.
	 * 
	 * @param participante Contiene los datos generales del participante.
	 * @param ciclo        Contiene el ciclo actual.
	 * @throws ExcepcionBaseDatos             Lanza la excepción sí no se pudo
	 *                                        realizar la conexión.
	 * @throws ExcepcionBaseDatosParticipante Lanza la excepción si ocurre un error
	 *                                        al actualizar el lugar del
	 *                                        participante en la tabla
	 *                                        <code>participante</code> de la base
	 *                                        de datos.
	 */
	public void actualizarResultadoParticipante(Participante participante, Ciclo ciclo)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosParticipante {

		realizarConexion();
		int resultado1 = realizarAccion("UPDATE participante SET marcadorFavor = " + participante.getMarcadorFavor()
				+ ", marcadorContra = " + participante.getMarcadorContra() + ", puntajeAcumuladoParticipante = "
				+ participante.getPuntajeAcumuladoParticipante() + " WHERE numeroParticipante = "
				+ participante.getNumeroParticipante());

		cerrarConexion();
		if (resultado1 != 1) {
			throw new ExcepcionBaseDatosParticipante(
					ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_ACTUALIZA_RESULTADO_PARTICIPANTE
							+ ciclo.getTorneo().getDatosPersonalizacion()
									.getNombreParticipante(Personalizacion.MINUSCULA_SINGULAR)
							+ ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Obtiene la lista del nombre de los participantes del torneo.
	 * 
	 * @return Regresa una lista con los nombres de los participantes.
	 * @throws ExcepcionBaseDatos             Lanza la excepción sí no se pudo
	 *                                        realizar la conexión.
	 * @throws ExcepcionBaseDatosParticipante Lanza la excepción si ocurre un error
	 *                                        al obtener al participante en la tabla
	 *                                        <code>participante</code> de la base
	 *                                        de datos.
	 * 
	 * 
	 */
	public ArrayList<Participante> obtenerParticipante() throws ExcepcionBaseDatos, ExcepcionBaseDatosParticipante {
		try {
			realizarConexion();
			ArrayList<Participante> listaParticipante = new ArrayList<Participante>();
			ResultSet retorno = realizarConsulta("SELECT * FROM participante");
			while (retorno.next()) {
				Participante participante = new Participante();
				participante.setNumeroParticipante(retorno.getInt(1));
				participante.setNombreParticipante(retorno.getString(2));
				participante.setPuntajeParticipante(retorno.getFloat(3));
				participante.setPuntajeAcumuladoParticipante(retorno.getFloat(4));
				participante.setMarcadorFavor(retorno.getInt(5));
				participante.setMarcadorContra(retorno.getInt(6));
				participante.setLugarParticipante(retorno.getInt(7));

				listaParticipante.add(participante);
			}
			cerrarConexion();
			return listaParticipante;
		} catch (SQLException e) {
			throw new ExcepcionBaseDatosParticipante(
					ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_OBTIENE_PARTICIPANTE
							+ ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_OBTIENE_PARTICIPANTE);
		}
	}

	
	public ArrayList<Participante> obtenerParticipanteLugar() throws ExcepcionBaseDatos, ExcepcionBaseDatosParticipante {
		try {
			realizarConexion();
			ArrayList<Participante> listaParticipante = new ArrayList<Participante>();
			ResultSet retorno = realizarConsulta("SELECT * FROM participante WHERE lugarParticipante = 1");
			while (retorno.next()) {
				Participante participante = new Participante();
				participante.setNumeroParticipante(retorno.getInt(1));
				participante.setNombreParticipante(retorno.getString(2));
				participante.setPuntajeParticipante(retorno.getFloat(3));
				participante.setPuntajeAcumuladoParticipante(retorno.getFloat(4));
				participante.setMarcadorFavor(retorno.getInt(5));
				participante.setMarcadorContra(retorno.getInt(6));
				participante.setLugarParticipante(retorno.getInt(7));

				listaParticipante.add(participante);
			}
			cerrarConexion();
			return listaParticipante;
		} catch (SQLException e) {
			throw new ExcepcionBaseDatosParticipante(
					ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_OBTIENE_PARTICIPANTE
							+ ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_OBTIENE_PARTICIPANTE);
		}
	}
	
	/**
	 * Cancela el avance del participante de la base de datos.
	 * 
	 * @param arrayList Recibe un arreglo que contiene los datos del participante.
	 * @throws ExcepcionBaseDatos             Lanza la excepción sí no se pudo
	 *                                        realizar la conexión.
	 * @throws ExcepcionBaseDatosParticipante Lanza la excepción si ocurre un error
	 *                                        al cancelar el avance del participante
	 *                                        en la tabla <code>participante</code>
	 *                                        de la base de datos.
	 */
	public void cancelarAvanceParticipante(ArrayList<Participante> arrayList)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosParticipante {
		for (Participante p : arrayList) {
			realizarConexion();

			int filasAfectadas = realizarAccion("UPDATE participante SET marcadorFavor = " + 0 + ", marcadorContra = "
					+ 0 + ", puntajeAcumuladoParticipante = " + 0 + ", lugarParticipante = " + 0
					+ " WHERE numeroParticipante = " + p.getNumeroParticipante());

			cerrarConexion();
			if (filasAfectadas != 1) {
				throw new ExcepcionBaseDatosParticipante(
						ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_INSERTA_PARTICIPANTE
								+ ExcepcionBaseDatosParticipante.MENSAJE_EXCEPCION_SOLUCION);
			}
		}
	}
}
