package sigestor.bd;

import java.sql.ResultSet;
import java.sql.SQLException;

import sigestor.dominio.*;
import sigestor.excepcion.*;

/**
 * Sirve para insertar y actualizar los datos de personalización en la base de
 * datos.
 * 
 * @version 02/06/2023
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * @author Victor Triste Pérez
 */
public class BaseDatosPersonalizacion extends BaseDatos {

	/**
	 * Permite llamar el constructor de la clase padre.
	 * 
	 * @param nombreArchivo Nombre personalizado del archivo de la base de datos.
	 */
	public BaseDatosPersonalizacion(String nombreArchivo) {
		super(nombreArchivo);
	}

	/**
	 * Inserta los datos de personalización a la base de datos.
	 * 
	 * @param personalizacion Contiene los datos de personalización.
	 * @throws ExcepcionBaseDatos                Lanza la excepción si no se pudo
	 *                                           realizar la conexión.
	 * @throws ExcepcionBaseDatosPersonalizacion Lanza la excepción si no se
	 *                                           pudieron insertar los datos a la
	 *                                           tabla <code>personalizacion</code>
	 *                                           de la base de datos.
	 * 
	 */
	public void insertarPersonalizacion(Personalizacion personalizacion)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosPersonalizacion {
		boolean alfabetico = false, aleatorio = false;
		if (Participante.isOrden()) {
			alfabetico = true;
		} else {
			aleatorio = true;
		}

		realizarConexion();
		int filasAfectadas = realizarAccion("INSERT INTO personalizacion VALUES ('" + personalizacion.getNombreCiclo(0)
				+ "', '" + personalizacion.getNombreParticipante(0) + "', '"
				+ personalizacion.getNombreParticipanteInicial() + "', '" + personalizacion.getNombreParticipanteFinal()
				+ "', '" + personalizacion.getNombreEncuentro(0) + "', '"
				+ personalizacion.getNombreParticipanteSinEncuentro() + "', " + personalizacion.isExistenciaMarcador()
				+ ", '" + personalizacion.getNombreMarcador(0) + "', " + personalizacion.getPuntajeGanar() + ", "
				+ personalizacion.getPuntajeEmpatar() + ", " + personalizacion.getPuntajePerder() + ", "
				+ Participante.isPuntaje() + ", " + alfabetico + ", " + aleatorio + ")");
		cerrarConexion();
		if (filasAfectadas != 1) {
			throw new ExcepcionBaseDatosPersonalizacion(
					ExcepcionBaseDatosPersonalizacion.MENSAJE_EXCEPCION_INSERTA_PERSONALIZACION
							+ ExcepcionBaseDatosPersonalizacion.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Actualiza los datos de personalización guardados en la base de datos.
	 * 
	 * @param personalizacion Contiene los datos de personalización.
	 * @throws ExcepcionBaseDatos                Lanza la excepción si no se pudo
	 *                                           realizar la conexión.
	 * @throws ExcepcionBaseDatosPersonalizacion Lanza la excepción si no se pudo
	 *                                           actualizar los datos de la tabla
	 *                                           <code>personalizacion</code> de la
	 *                                           base de datos.
	 * 
	 */
	public void actualizarPersonalizacion(Personalizacion personalizacion)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosPersonalizacion {
		realizarConexion();
		boolean alfabetico = false, aleatorio = false;
		if (Participante.isOrden()) {
			alfabetico = true;
		} else {
			aleatorio = true;
		}
		int filasAfectadas = realizarAccion("UPDATE personalizacion SET nombreCiclo = '"
				+ personalizacion.getNombreCiclo(0) + "', nombreParticipante = '"
				+ personalizacion.getNombreParticipante(0) + "', nombreParticipanteInicial = '"
				+ personalizacion.getNombreParticipanteInicial() + "', nombreParticipanteFinal = '"
				+ personalizacion.getNombreParticipanteFinal() + "', nombreEncuentro = '"
				+ personalizacion.getNombreEncuentro(0) + "', nombreParticipanteSinEncuentro = '"
				+ personalizacion.getNombreParticipanteSinEncuentro() + "', existenciaMarcador = "
				+ personalizacion.isExistenciaMarcador() + ", nombreMarcador = '" + personalizacion.getNombreMarcador(0)
				+ "', puntajeGanar = " + personalizacion.getPuntajeGanar() + ", puntajeEmpatar = "
				+ personalizacion.getPuntajeEmpatar() + ", puntajePerder = " + personalizacion.getPuntajePerder()
				+ ", ordenPuntaje = " + Participante.isPuntaje() + ", ordenAlfabetico = " + alfabetico
				+ ", ordenAleatorio = " + aleatorio);
		cerrarConexion();
		if (filasAfectadas != 1) {
			throw new ExcepcionBaseDatosPersonalizacion(
					ExcepcionBaseDatosPersonalizacion.MENSAJE_EXCEPCION_ACTUALIZA_PERSONALIZACION
							+ ExcepcionBaseDatosPersonalizacion.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Obtiene de la base de datos los datos de personalización del torneo.
	 * 
	 * @return regresa un objeto de tipo <code>Personalizacion.</code>
	 * @throws ExcepcionBaseDatos                Lanza la excepción si no se pudo
	 *                                           realizar la conexión.
	 * @throws ExcepcionBaseDatosPersonalizacion Lanza la excepción si ocurre un
	 *                                           error al obtener los datos de
	 *                                           personalizacion de la tabla
	 *                                           <code>personalizacion</code> de la
	 *                                           base de datos.
	 */
	public Personalizacion obtenerPersonalizacion() throws ExcepcionBaseDatos, ExcepcionBaseDatosPersonalizacion {
		try {
			realizarConexion();
			Personalizacion personalizacion = new Personalizacion();
			ResultSet resultado = realizarConsulta(
					"SELECT nombreCiclo, nombreParticipante ,nombreParticipanteInicial , nombreParticipanteFinal , nombreEncuentro , nombreParticipanteSinEncuentro , existenciaMarcador , nombreMarcador, puntajeGanar , puntajeEmpatar, puntajePerder, ordenPuntaje, ordenAlfabetico, ordenAleatorio FROM personalizacion");
			resultado.next();
			personalizacion.setNombreCiclo(resultado.getString(1));
			personalizacion.setNombreParticipante(resultado.getString(2));
			personalizacion.setNombreParticipanteInicial(resultado.getString(3));
			personalizacion.setNombreParticipanteFinal(resultado.getString(4));
			personalizacion.setNombreEncuentro(resultado.getString(5));
			personalizacion.setNombreParticipanteSinEncuentro(resultado.getString(6));
			personalizacion.setExistenciaMarcador(resultado.getBoolean(7));
			personalizacion.setNombreMarcador(resultado.getString(8));
			personalizacion.setPuntajeGanar(resultado.getFloat(9));
			personalizacion.setPuntajeEmpatar(resultado.getFloat(10));
			personalizacion.setPuntajePerder(resultado.getFloat(11));
			Participante.setPuntaje(resultado.getBoolean(12));

			if (resultado.getBoolean(13)) {
				Participante.setOrden(true);
			}
			if (resultado.getBoolean(14)) {
				Participante.setOrden(false);
			}

			resultado.close();
			cerrarConexion();
			return personalizacion;
		} catch (SQLException e) {
			throw new ExcepcionBaseDatosPersonalizacion(
					ExcepcionBaseDatosPersonalizacion.MENSAJE_EXCEPCION_OBTENER_PERSONALIZACION
							+ ExcepcionBaseDatosPersonalizacion.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

}
