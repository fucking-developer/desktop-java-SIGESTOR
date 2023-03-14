package sigestor.bd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sigestor.dominio.Ciclo;
import sigestor.dominio.Encuentro;
import sigestor.dominio.Personalizacion;
import sigestor.dominio.Torneo;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosCiclo;
import sigestor.excepcion.ExcepcionCapturarResultados;

/**
 * Sirve para manejar la base de datos de <code>Ciclo</code>.
 * 
 * @version 24/05/2022
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
public class BaseDatosCiclo extends BaseDatos {

	/**
	 * Permite llamar el constructor de la clase padre.
	 * 
	 * @param nombreArchivo Nombre personalizado del archivo de la base de datos.
	 */
	public BaseDatosCiclo(String nombreArchivo) {
		super(nombreArchivo);
	}

	/**
	 * Inserta el número del ciclos en la tabla <code>ciclo</code> de la base de
	 * datos.
	 * 
	 * @param ciclo Recibe el objeto <code>Ciclo</code> que contiene los datos de un
	 *              ciclo.
	 * @throws ExcepcionBaseDatos      Lanza la excepción sí ocurre un error en
	 *                                 <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosCiclo Lanza la excepción sí ocurre un error al
	 *                                 insertar un dato la tabla <code>ciclo</code>.
	 */
	public void insertarCiclo(Ciclo ciclo) throws ExcepcionBaseDatos, ExcepcionBaseDatosCiclo {
		realizarConexion();
		int valor = realizarAccion("INSERT INTO ciclo (numeroCiclo) VALUES (" + ciclo.getNumeroCiclo() + ")");
		cerrarConexion();
		if (valor != 1) {
			throw new ExcepcionBaseDatosCiclo(ExcepcionBaseDatosCiclo.MENSAJE_EXCEPCION_INSERTA_CICLO
					+ ciclo.getTorneo().getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR)
					+ ExcepcionBaseDatosCiclo.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Elimina el número del ciclo en la tabla <code>ciclo</code> de la base de
	 * datos.
	 * 
	 * @param ciclo Recibe el objeto <code>Ciclo</code> que contiene los datos de un
	 *              ciclo.
	 * @throws ExcepcionBaseDatos Lanza la excepción sí ocurre un error en
	 *                           <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosCiclo Lanza la excepción sí ocurre un error
	 */
	public void eliminarCiclo(Ciclo ciclo) throws ExcepcionBaseDatos, ExcepcionBaseDatosCiclo {
		realizarConexion();
		int resultado = realizarAccion("DELETE FROM ciclo WHERE numeroCiclo = " + ciclo.getNumeroCiclo());
		cerrarConexion();
		if (resultado != 1) {
			throw new ExcepcionBaseDatosCiclo(ExcepcionBaseDatosCiclo.MENSAJE_EXCEPCION_ELIMINA_CICLO
					+ ciclo.getTorneo().getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR)
					+ ExcepcionBaseDatosCiclo.MENSAJE_EXCEPCION_SOLUCION);
		}
	}

	/**
	 * Obtiene todos los ciclos con sus respectivos encuentros.
	 * 
	 * @param torneo Recibe el objeto <code>Torneo</code> que contiene los datos del
	 *               torneo.
	 * @return Regresa la lista de ciclos.
	 * @throws ExcepcionBaseDatos          Lanza la excepción sí ocurre un error en
	 *                                     <code>BaseDatos</code>.
	 * @throws ExcepcionCapturarResultados Lanza la excepción si ocurre un error al
	 *                                     crear el objeto <code>Encuentro</code>.
	 */
	public ArrayList<Ciclo> obtenerCiclos(Torneo torneo) throws ExcepcionBaseDatos, ExcepcionCapturarResultados {
		ArrayList<Ciclo> ciclos = new ArrayList<>();
		BaseDatosEncuentro bde = new BaseDatosEncuentro(getNombreArchivo());
		try {
			realizarConexion();
			ResultSet resultado = realizarConsulta("SELECT * FROM ciclo");
			while (resultado.next()) {
				Ciclo ciclo = new Ciclo(torneo, resultado.getInt("numeroCiclo"));
				ArrayList<Encuentro> encuentros = bde.obtenerEncuentros(ciclo);
				ciclo.setEncuentroParticipantes(encuentros);
				ciclos.add(ciclo);
			}
		} catch (ExcepcionBaseDatos | SQLException e) {

		} finally {
			cerrarConexion();
		}
		return ciclos;
	}

	/**
	 * Para eliminar los ciclos del torneo que se encuentras en la base de datos.
	 * 
	 * @throws ExcepcionBaseDatos      Lanza la excepción sí ocurre un error en
	 *                                 <code>BaseDatos</code>.
	 * @throws ExcepcionBaseDatosCiclo Lanza la excepción sí ocurre un erroral
	 *                                 eliminar un registro de la tabla
	 *                                 <code>ciclo</code>.
	 */
	public void eliminarCiclos() throws ExcepcionBaseDatos, ExcepcionBaseDatosCiclo {
		realizarConexion();
		int resultado = realizarAccion("DELETE FROM ciclo WHERE numeroCiclo > 0");
		cerrarConexion();
		if (resultado < 0) {
			throw new ExcepcionBaseDatosCiclo(ExcepcionBaseDatosCiclo.MENSAJE_EXCEPCION_ELIMINA_CICLOS
					+ ExcepcionBaseDatosCiclo.MENSAJE_EXCEPCION_SOLUCION);

		}
	}
}
