package sigestor.utilerias;

import java.io.FileWriter;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;

import com.csvreader.CsvWriter;

import sigestor.dominio.Ciclo;
import sigestor.dominio.Encuentro;
import sigestor.dominio.Participante;
import sigestor.dominio.Personalizacion;
import sigestor.dominio.Torneo;
import sigestor.excepcion.ExcepcionUtilerias;

/**
 * Sirve para generar el reporte en un archivo CSV de los resultados parciales.
 *
 * @version 10/04/2023
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 * @author Hernán Sesaí López Aragón
 */
public class UtileriasReporteResultados {

	/**
	 * Genera un reporte en un archivo CSV de los resultados finales o parciales de
	 * un ciclo.
	 * 
	 * @param rutaArchivo    Indica el archivo donde se escribirá el reporte.
	 * @param ciclo          El objeto con la información de un ciclo.
	 * @param torneo El objeto con todos los datos del torneo actual.
	 * @throws ExcepcionUtilerias  Si ocurre un error al escribir en el archivo CSV.
	 */

	public static void escribirArchivoCsvReporteResultados(String rutaArchivo, Ciclo ciclo, Torneo torneo)
			throws ExcepcionUtilerias {
		DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
		try {
			Writer writer;
			writer = new FileWriter(rutaArchivo, false);
			CsvWriter writerCsv = new CsvWriter(writer, ',');
			writerCsv.write(torneo.getTipoTorneo());
			writerCsv.endRecord();
			writerCsv.write("Torneo: " + torneo.getNombreTorneo());
			writerCsv.endRecord();
			writerCsv.write("Organizador: " + torneo.getNombreOrganizador());
			writerCsv.endRecord();
			writerCsv.write("Fecha de inicio: " + f.format(torneo.getFechaInicioTorneo()));
			writerCsv.endRecord();
			writerCsv.write("Fecha de fin: " + f.format(torneo.getFechaFinalTorneo()));
			writerCsv.endRecord();
			writerCsv.write(torneo.getDescripcionTorneo());
			writerCsv.endRecord();
			writerCsv.write("Tabla de resultados "
					+ torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MAYUSCULA_SINGULAR) + ": "
					+ ciclo.getNumeroCiclo());
			writerCsv.endRecord();
			writerCsv.endRecord();
			writerCsv.write("Núm.");
			writerCsv.write(torneo.getDatosPersonalizacion().getNombreParticipanteInicial());
			if (torneo.getDatosPersonalizacion().isExistenciaMarcador()) {
				writerCsv.write(
						torneo.getDatosPersonalizacion().getNombreMarcador(Personalizacion.MAYUSCULA_SINGULAR));
			}
			writerCsv.write("Resultado");
			writerCsv.write("Núm.");
			writerCsv.write(torneo.getDatosPersonalizacion().getNombreParticipanteFinal());
			if (torneo.getDatosPersonalizacion().isExistenciaMarcador()) {
				writerCsv.write(
						torneo.getDatosPersonalizacion().getNombreMarcador(Personalizacion.MAYUSCULA_SINGULAR));
			}
			writerCsv.write("Fecha del encuentro");
			writerCsv.endRecord();

			for (Encuentro e : ciclo.getEncuentroParticipantes()) {
				if (obtenerNombreParticipante(torneo, e.getIdParticipanteInicial()).compareToIgnoreCase(
						torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro()) == 0) {
					writerCsv.write(String.valueOf(e.getIdParticipanteFinal()));
					writerCsv.write(obtenerNombreParticipante(torneo, e.getIdParticipanteFinal()) + " - "
							+ torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro());
				} else if (obtenerNombreParticipante(torneo, e.getIdParticipanteFinal()).compareToIgnoreCase(
						torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro()) == 0) { 
					writerCsv.write(String.valueOf(e.getIdParticipanteInicial()));
					writerCsv.write(obtenerNombreParticipante(torneo, e.getIdParticipanteInicial()) + " - "
							+ torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro());
				} else {
					writerCsv.write(String.valueOf(e.getIdParticipanteInicial()));
					writerCsv.write(obtenerNombreParticipante(torneo, e.getIdParticipanteInicial()));
					if (torneo.getDatosPersonalizacion().isExistenciaMarcador()) {
						writerCsv.write(String.valueOf(e.getMarcadorParticipanteInicial()));
					}
					writerCsv.write(e.toString());
					writerCsv.write(String.valueOf(e.getIdParticipanteFinal()));
					writerCsv.write(obtenerNombreParticipante(torneo, e.getIdParticipanteFinal()));
					if (torneo.getDatosPersonalizacion().isExistenciaMarcador()) {
						writerCsv.write(String.valueOf(e.getMarcadorParticipanteFinal()));
					}
				}
				writerCsv.write(f.format(e.getFechaEncuentro()));
				writerCsv.endRecord();
				
			}
			writerCsv.close();
		} catch (IOException e) {
			throw new ExcepcionUtilerias(ExcepcionUtilerias.MENSAJE_EXCEPCION_GENERAR_ARCHIVO_CSV);
		}
	}

	/**
	 * Obtiene el nombre del participante a partir de su número recorriendo la lista
	 * de participantes.
	 * 
	 * @param torneo         El objeto con todos los datos del torneo actual.
	 * @param idParticipante Identificador único del participante a buscar en la
	 *                       lista de participantes.
	 * @return El nombre del participante.
	 */
	private static String obtenerNombreParticipante(Torneo torneo, int idParticipante) {
		String nombreParticipante = "";
		for (Participante p : torneo.getListaParticipantes()) {
			if (p.getNumeroParticipante() == idParticipante) {
				return p.getNombreParticipante();
			}
		}
		return nombreParticipante;

	}

}
