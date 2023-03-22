package sigestor.utilerias;

import java.util.ArrayList;


import sigestor.dominio.Participante;
import sigestor.excepcion.ExcepcionUtilerias;

public class UtileriasListaParticipantes {

	private static boolean validarListaParticipantes(String rutaArchivo) {
		//TODO verificar que sí sea un archivo con la estructura de la lista
		return false;
	}
	
	public static ArrayList<Participante> leerListaParticipantes(String rutaArchivo) throws ExcepcionUtilerias {
		if (validarListaParticipantes(rutaArchivo)) {
			//TODO lanzar excepción
		}
		//TODO crear un array de participantes (sin número) con los datos del archivo csv
		return null;
	}
	
	public static void escribirPlantilla(String rutaDestino) throws ExcepcionUtilerias {
		//TODO crear plantilla en la rutaDestino (dir)
	}
}
