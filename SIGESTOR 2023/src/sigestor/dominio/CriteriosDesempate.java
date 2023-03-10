package sigestor.dominio;

import java.util.ArrayList;

/**
 * Sirve para guardar y validar la información ingresada en el
 * <code>PanelCriteriosDesempate</code>. Las características de la clase
 * <code>CriteriosDesempate</code> son:
 * <ul>
 * <li><code>listaCriteriosSeleccionados</code> Lista de los criterios de
 * desempate que fueron seleccionados.</li>
 * </ul>
 * 
 * @version 24/04/2022
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 */

public class CriteriosDesempate {
	/**
	 * Lista de los criterios de desempate que fueron seleccionados.
	 */
	private ArrayList<String> listaCriteriosSeleccionados;
	/**
	 * Lista de los criterios de desempate a seleccionar.
	 */
	public static final ArrayList<String> criterios = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("Encuentro directo");
			add("Sistema Koya");
			add("Buchholz");
			add("Sonnerborn-Berger");
			add("Encuentros ganados");
			add("Diferencia de marcadores");
			add("Marcador a favor");
			add("Marcador en contra");
			add("Puntuación");
			add("Marcador de participante final");
		}
	};

	/**
	 * Permite crear los criterios de desempate.
	 */
	public CriteriosDesempate() {
		setListaCriterios(null);
	}

	/**
	 * Devuelve la lista de los criterios de desempate que fueron seleccionados.
	 * 
	 * @return Regresa la lista de los criterios de desempate que fueron
	 *         seleccionados.
	 */
	public ArrayList<String> getListaCriteriosSeleccionados() {
		return listaCriteriosSeleccionados;
	}

	/**
	 * Asigna la lista de los criterios de desempate seleccionados.
	 * 
	 * @param listaCriterios Lista de los criterios de desempate seleccionados.
	 */
	public void setListaCriterios(ArrayList<String> listaCriterios) {
		this.listaCriteriosSeleccionados = listaCriterios;
	}

}
