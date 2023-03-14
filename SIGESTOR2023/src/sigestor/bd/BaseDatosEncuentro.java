package sigestor.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sigestor.dominio.Ciclo;
import sigestor.dominio.Encuentro;
import sigestor.dominio.Personalizacion;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosEncuentro;
import sigestor.excepcion.ExcepcionCapturarResultados;

/**
 * Sirve para manejar la base de datos de <code>Encuentro</code>.
 * 
 * @version 24/04/2022
 * 
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Francisco Samuel Reyes Cortes
 * @author Hernán Sesaí Lopéz Aragón
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 * @author Beatriz Andrea Jiménez Ríos
 */
public class BaseDatosEncuentro extends BaseDatos {

	/**
	 * Permite llamar el constructor de la clase padre.
	 * 
	 * @param nombreArchivo Nombre personalizado del archivo de la base de datos.
	 */
	public BaseDatosEncuentro(String nombreArchivo) {
		super(nombreArchivo);
	}

	/**
	 * Inserta el número de encuentro, el id del participante inicial, el id del
	 * participante final y el número de ciclo en la tabla <code>encuentro</code> de
	 * la base de datos.
	 * 
	 * @param encuentro Recibe el objeto <code>Encuentro</code> que contiene los
	 *                  datos de un encuentro.
	 * @param ciclo     Recibe el objeto <code>Ciclo</code> que contiene los datos
	 *                  de un ciclo.
	 * @throws ExcepcionBaseDatos          Lanza la excepción sí ocurre un error en
	 *                                     <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosEncuentro Lanza la excepción sí ocurre un error en
	 *                                     <code>BaseDatosEncuentro</code>.
	 */
	public void insertarEncuentro(Encuentro encuentro, Ciclo ciclo)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro {
		realizarConexion();
		int valor = realizarAccion(
				"INSERT INTO encuentro (numeroEncuentro, idParticipanteInicial, idParticipanteFinal, numeroCiclo, resultado) VALUES ("
						+ encuentro.getNumeroEncuentro() + "," + encuentro.getIdParticipanteInicial() + ","
						+ encuentro.getIdParticipanteFinal() + "," + ciclo.getNumeroCiclo() + ", "
						+ encuentro.getResultadoEncuentro() + ")");
		cerrarConexion();
		if (valor != 1) {
			throw new ExcepcionBaseDatosEncuentro(ExcepcionBaseDatosEncuentro.MENSAJE_EXCEPCION_INSERTA_ENCUENTRO
					+ ciclo.getTorneo().getDatosPersonalizacion().getNombreEncuentro(Personalizacion.MINUSCULA_SINGULAR)
					+ ExcepcionBaseDatosEncuentro.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Actualiza el marcador del participante inicial, participante final y el
	 * resultado en la tabla <code>encuentro</code>.
	 * 
	 * @param encuentro Recibe el objeto <code>Encuentro</code> que contiene los
	 *                  datos de un encuentro.
	 * @param ciclo     Recibe el objeto <code>Ciclo</code> que contiene los datos
	 *                  de un ciclo.
	 * @throws ExcepcionBaseDatos          Lanza la excepción sí ocurre un error en
	 *                                     <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosEncuentro Lanza la excepción sí ocurre un error en
	 *                                     <code>BaseDatosEncuentro</code>.
	 */
	public void actualizarEncuentro(Encuentro encuentro, Ciclo ciclo)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro {
		realizarConexion();

		int resultado = realizarAccion(
				"UPDATE encuentro SET marcadorParticipanteInicial = " + encuentro.getMarcadorParticipanteInicial()
						+ ", marcadorParticipanteFinal = " + encuentro.getMarcadorParticipanteFinal() + ", resultado = "
						+ encuentro.getResultadoEncuentro() + " WHERE numeroEncuentro = "
						+ encuentro.getNumeroEncuentro() + " and numeroCiclo = " + ciclo.getNumeroCiclo());

		cerrarConexion();
		if (resultado != 1) {
			throw new ExcepcionBaseDatosEncuentro(ExcepcionBaseDatosEncuentro.MENSAJE_EXCEPCION_ACTUALIZA_ENCUENTRO
					+ ciclo.getTorneo().getDatosPersonalizacion().getNombreEncuentro(Personalizacion.MINUSCULA_SINGULAR)
					+ ExcepcionBaseDatosEncuentro.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Elimina un encuentro almacenado en la tabla <code>encuentro</code>.
	 * 
	 * @param encuentro Recibe el objeto <code>Encuentro</code> que contiene los
	 *                  datos de un encuentro.
	 * @param ciclo     Recibe el objeto <code>Ciclo</code> que contiene los datos
	 *                  de un ciclo.
	 * @throws ExcepcionBaseDatos          Lanza la excepción sí ocurre un error en
	 *                                     <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosEncuentro Lanza la excepción sí ocurre un error al
	 *                                     eliminar un encuentro en la tabla
	 *                                     <code>encuentro</code>.
	 */
	public void eliminarEncuentro(Encuentro encuentro, Ciclo ciclo)
			throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro {
		realizarConexion();
		int resultado = realizarAccion("DELETE FROM encuentro WHERE numeroEncuentro = " + encuentro.getNumeroEncuentro()
				+ " and numeroCiclo = " + ciclo.getNumeroCiclo());
		cerrarConexion();
		if (resultado != 1) {
			throw new ExcepcionBaseDatosEncuentro(ExcepcionBaseDatosEncuentro.MENSAJE_EXCEPCION_ELIMINA_ENCUENTRO
					+ ciclo.getTorneo().getDatosPersonalizacion().getNombreEncuentro(Personalizacion.MINUSCULA_SINGULAR)
					+ ExcepcionBaseDatosEncuentro.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Obtiene los encuentros de un ciclo.
	 * 
	 * @param ciclo Recibe el objeto <code>Ciclo</code> que contiene los datos de un
	 *              ciclo.
	 * @return Regresa la lista de encuentros de un cilo.
	 * @throws ExcepcionBaseDatos          Lanza la excepción sí ocurre un error en
	 *                                     <code>BaseDatos</code>.
	 * @throws ExcepcionCapturarResultados Lanza la excepción si ocurre un error al
	 *                                     crear el objeto <code>Encuentro</code>.
	 */
	public ArrayList<Encuentro> obtenerEncuentros(Ciclo ciclo) throws ExcepcionBaseDatos, ExcepcionCapturarResultados {
		ArrayList<Encuentro> encuentros = new ArrayList<Encuentro>();
		realizarConexion();
		try {
			ResultSet busqueda = realizarConsulta(
					"SELECT *FROM encuentro WHERE numeroCiclo = " + ciclo.getNumeroCiclo());
			while (busqueda.next()) {
				encuentros.add(new Encuentro(busqueda.getInt(1), busqueda.getInt(2), busqueda.getInt(3),
						String.valueOf(busqueda.getInt(4)), String.valueOf(busqueda.getInt(5)), busqueda.getInt(6)));
			}
		} catch (SQLException e) {

		} finally {
			cerrarConexion();
		}
		return encuentros;
	}

	/**
	 * Cuenta el número de resultos sin capturar.
	 * 
	 * @return <tt>true</tt> sí verifica que no exista resultados sin capturar,
	 *         <tt>false</tt> en caso de que existan resultados sin capturar.
	 */
	public boolean verificarResultados() {
		ResultSet busqueda = null;
		boolean confirmacion = false;
		try {
			realizarConexion();
			busqueda = realizarConsulta("SELECT count(*) as col FROM encuentro WHERE resultado = 99");

			if (busqueda.getInt(1) == 0) {
				confirmacion = true;
			} else {
				confirmacion = false;
			}

			cerrarConexion();
		} catch (ExcepcionBaseDatos | SQLException e) {

		}
		return confirmacion;
	}

	/**
	 * Para eliminar los encuentros del torneo.
	 * 
	 * @throws ExcepcionBaseDatos          Lanza la excepción sí ocurre un error en
	 *                                     <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosEncuentro Lanza la excepción sí ocurre un error al
	 *                                     eliminar los registros de la tabla
	 *                                     <code>encuentro</code>.
	 */
	public void eliminarEncuentros() throws ExcepcionBaseDatos, ExcepcionBaseDatosEncuentro {
		realizarConexion();
		int resultado = realizarAccion("DELETE FROM encuentro WHERE numeroEncuentro > 0");
		cerrarConexion();
		if (resultado < 0) {
			throw new ExcepcionBaseDatosEncuentro(ExcepcionBaseDatosEncuentro.MENSAJE_EXCEPCION_ELIMINA_ENCUENTROS
					+ ExcepcionBaseDatosEncuentro.MENSAJE_EXCEPCION_SOLUCION);

		}
	}
}
