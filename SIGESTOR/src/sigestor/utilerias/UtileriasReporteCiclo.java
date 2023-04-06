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
 * Sirve para guardar en un archivo CSV el reporte de los pareos de un ciclo.
 *
 * 
 * @version 06/04/2023
 * 
 * @author Jonathan Eduardo Ibarra Martinez
 * @author Hernan Sesai Lopez Aragon
 * @author Francisco Samuel Reyes Cortes
 * @author Alicia Adriana Clemente Hernandez
 */

public class UtileriasReporteCiclo {

	/**
	 * Escribe en un archivo CSV los datos generales del torneo, así como también
	 * los pareos utilizando los datos de la clase <code>Torneo</code> y la clase
	 * <code>Ciclo</code>.
	 * 
	 * @param rutaArchivo Ruta donde se guardará el archivo CSV.
	 * @param torneo      Contiene los datos generales del torneo y la lista de
	 *                    participantes.
	 * @param ciclo       Contiene los pareos entre los participantes.
	 * @throws ExcepcionUtilerias Si ocurre un error al escribir en el archivo CSV.
	 */
	public static void escribirArchivoCsvReporteCiclo(String rutaArchivo, Torneo torneo, Ciclo ciclo)
			throws ExcepcionUtilerias {
		DateFormat fecha = DateFormat.getDateInstance(DateFormat.FULL);

		try {

			Writer writer = null;
			writer = new FileWriter(rutaArchivo, false);
			CsvWriter writerCsv = new CsvWriter(writer, ',');
			writerCsv.write(torneo.getTipoTorneo());
			writerCsv.endRecord();
			writerCsv.write("Torneo: ");
			writerCsv.write(torneo.getNombreTorneo());
			writerCsv.endRecord();
			writerCsv.write("Organizador: ");
			writerCsv.write(torneo.getNombreOrganizador());
			writerCsv.endRecord();
			writerCsv.write("Fecha de inicio: ");
			writerCsv.write(fecha.format(torneo.getFechaInicioTorneo()));
			writerCsv.endRecord();
			writerCsv.write("Fecha de fin: ");
			writerCsv.write(fecha.format(torneo.getFechaFinalTorneo()));
			writerCsv.endRecord();
			writerCsv.write("Núm.");
			writerCsv.write("Nombre");
			if (torneo.getDatosPersonalizacion().isExistenciaMarcador()) {
				writerCsv.write(torneo.getDatosPersonalizacion().getNombreMarcador(Personalizacion.MAYUSCULA_SINGULAR)
						+ " a favor");
				writerCsv.write(torneo.getDatosPersonalizacion().getNombreMarcador(Personalizacion.MAYUSCULA_SINGULAR)
						+ " en contra");
			}
			writerCsv.write("Puntaje");
			writerCsv.endRecord();
			for (Participante p : torneo.getListaParticipantes()) {
				if (p.getNombreParticipante()
						.compareTo(torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro()) != 0) {
					writerCsv.write(String.valueOf(p.getNumeroParticipante()));
					writerCsv.write(String.valueOf(p.getNombreParticipante()));
					if (torneo.getDatosPersonalizacion().isExistenciaMarcador()) {
						writerCsv.write(String.valueOf(p.getMarcadorFavor()));
						writerCsv.write(String.valueOf(p.getMarcadorContra()));
					}
					writerCsv.write(String.valueOf(p.getPuntajeAcumuladoParticipante()));
					writerCsv.endRecord();
				}
			}
			writerCsv.endRecord();
			writerCsv.endRecord();
			writerCsv.endRecord();
			writerCsv.write(torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MAYUSCULA_SINGULAR) + " "
					+ ciclo.getNumeroCiclo());
			writerCsv.endRecord();
			writerCsv.write("Núm.");
			writerCsv.write(torneo.getDatosPersonalizacion().getNombreParticipanteInicial());
			writerCsv.write("VS");
			writerCsv.write("Núm.");
			writerCsv.write(torneo.getDatosPersonalizacion().getNombreParticipanteFinal());
			writerCsv.write("Fecha del encuentro");
			writerCsv.endRecord();
			for (Encuentro e : ciclo.getEncuentroParticipantes()) {
				if (obtenerNombreParticipante(torneo, e.getIdParticipanteInicial()).compareToIgnoreCase(
						torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro()) == 0) {
					writerCsv.write(String.valueOf(e.getIdParticipanteFinal()));
					writerCsv.write(obtenerNombreParticipante(torneo, e.getIdParticipanteFinal()) + " - "
							+ torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro());
					writerCsv.write(fecha.format(e.getFechaEncuentro()));
					writerCsv.endRecord();
				} else if (obtenerNombreParticipante(torneo, e.getIdParticipanteFinal()).compareToIgnoreCase(
						torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro()) == 0) { 
					writerCsv.write(String.valueOf(e.getIdParticipanteInicial()));
					writerCsv.write(obtenerNombreParticipante(torneo, e.getIdParticipanteInicial()) + " - "
							+ torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro());
					writerCsv.write(fecha.format(e.getFechaEncuentro()));
					writerCsv.endRecord();
				} else {
					writerCsv.write(String.valueOf(e.getIdParticipanteInicial()));
					writerCsv.write(obtenerNombreParticipante(torneo, e.getIdParticipanteInicial()));
					writerCsv.write("vs");
					writerCsv.write(String.valueOf(e.getIdParticipanteFinal()));
					writerCsv.write(obtenerNombreParticipante(torneo, e.getIdParticipanteFinal()));
					writerCsv.write(fecha.format(e.getFechaEncuentro()));
					writerCsv.endRecord();
				}
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