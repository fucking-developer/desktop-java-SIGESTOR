package sigestor.utilerias;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import sigestor.dominio.Participante;
import sigestor.excepcion.ExcepcionUtilerias;

/**
 * Encargada de validar el formato y leer la lista de participantes de un
 * archivo CSV, así como generar una plantilla en un archivo CSV.
 * 
 * @version 27/03/2023
 * 
 * @author Jonathan Eduardo Ibarra Martínez.
 * @author Eder Euclides Dionisio Diaz.
 * @author Hernan Sesai Lopez Aragon.
 *
 */
public class UtileriasListaParticipantes {

	/**
	 * Encargado de validar el formato de un archivo CSV.
	 * 
	 * @param rutaArchivo Contiene la ruta del archivo CSV.
	 * @return <tt>true</tt> Si el formato del archivo es correcto, es decir,
	 *         contiene dos columnas y los encabezados son 'Nombre' y 'Puntaje',
	 *         <tt>false</tt> en caso contrario.
	 * @throws ExcepcionUtilerias Si ocurre un error al leer el archivo CSV.
	 */
	private static boolean validarListaParticipantes(String rutaArchivo) throws ExcepcionUtilerias {
		boolean formatoCorrecto = false;
		try {
			CsvReader csv = new CsvReader(new FileReader(rutaArchivo));
			csv.readHeaders();
			if (csv.getHeaderCount() == 2 && csv.getHeader(0).equals("Nombre") && csv.getHeader(1).equals("Puntaje")) {
				formatoCorrecto = true;
			} else {
				formatoCorrecto = false;
			}
		} catch (IOException e) {
			throw new ExcepcionUtilerias(ExcepcionUtilerias.MENSAJE_EXCEPCION_LEER_ARCHIVO_CSV);
		}
		return formatoCorrecto;
	}

	/**
	 * Encargado de leer las filas de un archivo CSV el cual solo contiene nombres y
	 * puntajes de participantes, dichos datos se guardan en un ArrayList de
	 * participantes.
	 * 
	 * @param rutaArchivo Contiene la ruta del archivo CSV.
	 * 
	 * @return ArrayList con los datos de los participantes del archivo CSV.
	 * 
	 * @throws ExcepcionUtilerias Si ocurre un error al leer el archivo CSV o si el
	 *                            archivo CSV tiene un formato incorrecto.
	 */

	public static ArrayList<Participante> leerListaParticipantes(String rutaArchivo) throws ExcepcionUtilerias {
		if (!validarListaParticipantes(rutaArchivo)) {
			throw new ExcepcionUtilerias(ExcepcionUtilerias.MENSAJE_EXCEPCION_FORMATO_INCORRECTO);
		}
		ArrayList<Participante> listaParticipantes = new ArrayList<Participante>();
		Participante participante;
		Reader reader;
		try {
			reader = new FileReader(rutaArchivo);
			CsvReader readerCsv = new CsvReader(reader);
			readerCsv.readHeaders();
			while (readerCsv.readRecord()) {
				participante = new Participante();

				if (readerCsv.get(0).trim().isEmpty()) {
					continue;
				}
				try {
					participante.setNombreParticipante(readerCsv.get(0));
					participante.setPuntajeParticipante(Float.valueOf(readerCsv.get(1)));

				} catch (NumberFormatException e) {
					if (readerCsv.get(1).trim().isEmpty()) {
						participante.setPuntajeParticipante(0.0f);
						listaParticipantes.add(participante);
					}
					continue;

				}
				listaParticipantes.add(participante);

			}
			readerCsv.close();
			return listaParticipantes;
		} catch (IOException e) {
			throw new ExcepcionUtilerias(ExcepcionUtilerias.MENSAJE_EXCEPCION_LEER_ARCHIVO_CSV);
		}
	}

	/**
	 * Genera una plantilla con extensión CSV para el ingreso de datos para una
	 * lista de participantes.
	 * 
	 * @param rutaDestino Indica la ruta del archivo donde se guardará la plantilla.
	 * @throws ExcepcionUtilerias Si ocurre un error al escribir en el archivo CSV.
	 */
	public static void escribirPlantilla(String rutaDestino) throws ExcepcionUtilerias {
		try {
			Writer writer;
			writer = new FileWriter(rutaDestino, false);
			CsvWriter writerCsv = new CsvWriter(writer, ',');
			writerCsv.write("Nombre");
			writerCsv.write("Puntaje");
			writerCsv.endRecord();
			writerCsv.close();
		} catch (IOException e) {
			throw new ExcepcionUtilerias(ExcepcionUtilerias.MENSAJE_EXCEPCION_ESCRIBIR_PLANTILLA_CSV);
		}
	}
}
