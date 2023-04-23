package sigestor.utilerias;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;

import com.csvreader.CsvWriter;

import sigestor.dominio.Participante;
import sigestor.dominio.Personalizacion;
import sigestor.dominio.Torneo;
import sigestor.excepcion.ExcepcionUtilerias;

/**
 * Sirve para guardar en un archivo CSV el reporte de resultados finales.
 * 
 * 
 * @version 06/04/2022
 * 
 * @author Jonathan Eduardo Ibarra Martinez
 * @author Hernan Sesai Lopez Aragon
 * @author Francisco Samuel Reyes Cortes
 * @author Alicia Adriana Clemente Hernandez
 */

public class UtileriasReporteResultadosFinales {

	/**
	 * Escribe en un archivo CSV los datos generales del torneo, así como también
	 * los resultados finales del torneo.
	 * 
	 * @param rutaArchivo Ruta donde se guardará el archivo CSV.
	 * @param torneo      Contiene los datos generales del torneo y la lista de
	 *                    participantes.
	 * 
	 * @throws ExcepcionUtilerias Si ocurre un error al escrbir en el archivo CSV.
	 */
	public static void escribirArchivoCsvReporteResultadosFinales(String rutaArchivo, Torneo torneo)
			throws ExcepcionUtilerias {
		try {
			DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
			Writer writer = null;
			writer = new FileWriter(rutaArchivo, false);
			CsvWriter writerCsv = new CsvWriter(writer, ',');
			writerCsv.write(torneo.getTipoTorneo());
			writerCsv.endRecord();
			writerCsv.write("Torneo:");
			writerCsv.write(torneo.getNombreTorneo());
			writerCsv.endRecord();
			writerCsv.write("Organizador: ");
			writerCsv.write(torneo.getNombreOrganizador());
			writerCsv.endRecord();
			writerCsv.write("Fecha de inicio: ");
			writerCsv.write(f.format(torneo.getFechaFinalTorneo()));
			writerCsv.endRecord();
			writerCsv.write("Fecha de fin");
			writerCsv.write(f.format(torneo.getFechaFinalTorneo()));
			writerCsv.endRecord();
			writerCsv.write(torneo.getDescripcionTorneo());
			writerCsv.endRecord();
			writerCsv.endRecord();
			writerCsv.write("Tabla de resultados finales");
			writerCsv.endRecord();
			writerCsv.endRecord();
			writerCsv.write("Lugar");
			writerCsv.write("Núm.");
			writerCsv.write("Nombre");
			if (torneo.getDatosPersonalizacion().isExistenciaMarcador()) {
				writerCsv.write(String
						.valueOf(torneo.getDatosPersonalizacion().getNombreMarcador(Personalizacion.MAYUSCULA_SINGULAR)
								+ " a favor"));
				writerCsv.write(String
						.valueOf(torneo.getDatosPersonalizacion().getNombreMarcador(Personalizacion.MAYUSCULA_SINGULAR)
								+ " en contra"));
			}
			writerCsv.write("Puntaje final");
			writerCsv.endRecord();
			for (Participante p : torneo.getListaParticipantes()) {
				writerCsv.write(String.valueOf(p.getLugarParticipante()));
				writerCsv.write(String.valueOf(p.getNumeroParticipante()));
				writerCsv.write(p.getNombreParticipante());
				if (torneo.getDatosPersonalizacion().isExistenciaMarcador()) {
					writerCsv.write(String.valueOf(p.getMarcadorFavor()));
					writerCsv.write(String.valueOf(p.getMarcadorContra()));
				}
				writerCsv.write(String.valueOf(p.getPuntajeAcumuladoParticipante()));
				writerCsv.endRecord();
			}
			writerCsv.close();
		} catch (IOException e) {
			throw new ExcepcionUtilerias(ExcepcionUtilerias.MENSAJE_EXCEPCION_GENERAR_ARCHIVO_CSV);
		}
	}

}
