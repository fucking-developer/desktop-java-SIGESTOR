package sigestor.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import sigestor.dominio.CriteriosDesempate;

/**
 * Sirve para mostrar la interfaz donde permitirá capturar,mostrar y modificar
 * los criterios de desempates
 * <p>
 * las caracteristicas de la clase<code>PanelCriteriosDesempate</code> son:
 * <ul>
 * <li><code>listaNoSeleccionados</code> lista de criterios de desempate</li>
 * <li><code>listaSeleccionados</code> lista de criterios de desempate
 * seleccionados</li>
 * <li><code>botonFlechaDerecha</code> para mover el criterio a la lista de
 * criterios seleccionados</li>
 * <li><code>botonFlechaIzquierda</code> para mover el criterio a la lista de
 * criterios no seleccionados</li>
 * <li><code>botonFlechaArriba</code> para asginar mayor prioridad al criterio
 * de desempate</li>
 * <li><code>botonFlechaAbajo</code> para asignar menor prioridad al criterio de
 * desempate</li>
 * <li><code>modelListaSeleccionados</code> para asignar criterios y tener mayor
 * control de la lista</li>
 * <li><code>modelListaNoSeleccionados</code> para asiganr criterios y tener
 * mayor control de la lista</li>
 * <li><code>principal</code>Para obtener el torneo actual</li>
 * </ul>
 * 
 * @version 17/03/2023
 * 
 * @author Beatriz Andrea Jimenez Rios
 * @author Uriel Romeo Cruz Cortes
 * @author Ricky Didier Peralta Reyes
 * @author Jennifer Cortés Pérez
 * @author Erik Vasquez Policarpo
 *
 */
public class PanelCriteriosDesempate extends JPanel {

	/**
	 * Lista de criterios de desempate que el usuario podrá seleccionar
	 */
	private JList<String> listaNoSeleccionados;

	/**
	 * Lista de criterios que el usuario ha seleccionado
	 */
	private JList<String> listaSeleccionados;

	/**
	 * Botón que permite mover el criterio a la lista de criterios seleccionados
	 */
	private JButton botonFlechaDerecha;

	/**
	 * Botón que permite mover el criterio seleccioado a la lista de criterios
	 */
	private JButton botonFlechaIzquierda;

	/**
	 * Botón que permite subir la prioridad del criterio seleccionado
	 */
	private JButton botonFlechaArriba;

	/**
	 * Botón que permite bajar la prioridad del criterio seleccionado
	 */
	private JButton botonFlechaAbajo;

	/**
	 * Modelo que permite tener mayor control de la lista de criterios seleccionados
	 */
	private DefaultListModel<String> modelListaSeleccionados;

	/**
	 * Modelo que permite tener mayor control de la lista de criterios no
	 * seleccionados
	 */
	private DefaultListModel<String> modelListaNoSeleccionados;

	/**
	 * Objeto de tipo <code>VentanaPrincipal</code> que contiene el torneo actual
	 */
	private VentanaPrincipal ventanaPrincipal;

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que permite visualizar en panel en el dialogo
	 * <code>DialogoAdministrarTorneo.</code>
	 * 
	 * @param principal Recibe un objeto de tipo ventanaPrincipal el cual contiene el objeto
	 *               de tipo torneo.
	 */
	public PanelCriteriosDesempate(VentanaPrincipal principal) {

		ventanaPrincipal = principal;

		JScrollPane scrollCriteriosDeDesempate;
		JScrollPane scrollCriteriosSeleccionados;

		JPanel panelEtiquetaCriteriosDeDesempate = new JPanel();
		JPanel panelBotonesDerechaIzquierda = new JPanel();
		JPanel panelEtiquetaCriteriosSeleccionados = new JPanel();
		JPanel panelBotonesArribaAbajo = new JPanel();

		panelBotonesDerechaIzquierda.setLayout(new GridLayout(2, 1, 80, 80));

		panelBotonesArribaAbajo.setLayout(new GridLayout(2, 1, 80, 80));

		panelEtiquetaCriteriosDeDesempate.setLayout(new GridBagLayout());

		panelEtiquetaCriteriosSeleccionados.setLayout(new GridBagLayout());

		GridBagConstraints gridPosicion = new GridBagConstraints();

		gridPosicion.gridx = 0;
		gridPosicion.gridy = 0;

		JLabel etiquetaCriteriosDeDesempate = new JLabel("Criterios de desempate");
		Font fuenteEtiqueta = etiquetaCriteriosDeDesempate.getFont();
		etiquetaCriteriosDeDesempate.setFont(new Font(fuenteEtiqueta.getFontName(), fuenteEtiqueta.getStyle(), 20));
		panelEtiquetaCriteriosDeDesempate.add(etiquetaCriteriosDeDesempate, gridPosicion);

		gridPosicion.gridx = 0;
		gridPosicion.gridy = 1;

		modelListaNoSeleccionados = new DefaultListModel<String>();

		listaNoSeleccionados = new JList<String>();
		listaNoSeleccionados.setToolTipText("Lista de criterios de desempate que no se utilizarán en el torneo");
		listaNoSeleccionados.setPreferredSize(new Dimension(50, 450));
		listaNoSeleccionados.setVisibleRowCount(10);
		listaNoSeleccionados.setModel(modelListaNoSeleccionados);

		scrollCriteriosDeDesempate = new JScrollPane(listaNoSeleccionados);
		scrollCriteriosDeDesempate.setPreferredSize(new Dimension(250, 300));
		panelEtiquetaCriteriosDeDesempate.add(scrollCriteriosDeDesempate, gridPosicion);

		Icon icon = new ImageIcon(getClass().getResource("/imagenes/derecha.png"));

		Action accionBotonDesplazarDerecha = new AbstractAction("", icon) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionPasarACriteriosSeleccionados();
			}
		};
		accionBotonDesplazarDerecha.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_1);
		accionBotonDesplazarDerecha.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
		accionBotonDesplazarDerecha.putValue(Action.SHORT_DESCRIPTION, "Permite seleccionar criterios de desempate");
		botonFlechaDerecha = new JButton(accionBotonDesplazarDerecha);
		botonFlechaDerecha.getActionMap().put("botonFlechaDerecha", accionBotonDesplazarDerecha);
		botonFlechaDerecha.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionBotonDesplazarDerecha.getValue(Action.ACCELERATOR_KEY), "botonFlechaDerecha");
		botonFlechaDerecha.setPreferredSize(new Dimension(30, 30));
		panelBotonesDerechaIzquierda.add(botonFlechaDerecha, gridPosicion);

		icon = new ImageIcon(getClass().getResource("/imagenes/izquierda.png"));

		Action accionBotonDesplazarIzquierda = new AbstractAction("", icon) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionPasarACriteriosNoSeleccionados();
			}
		};
		accionBotonDesplazarIzquierda.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_2);
		accionBotonDesplazarIzquierda.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
		accionBotonDesplazarIzquierda.putValue(Action.SHORT_DESCRIPTION,
				"Permite regresar criterios de desempate seleccionados");
		botonFlechaIzquierda = new JButton(accionBotonDesplazarIzquierda);
		botonFlechaIzquierda.getActionMap().put("botonFlechaIzquierda", accionBotonDesplazarIzquierda);
		botonFlechaIzquierda.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				(KeyStroke) accionBotonDesplazarIzquierda.getValue(Action.ACCELERATOR_KEY), "botonFlechaIzquierda");
		botonFlechaIzquierda.setPreferredSize(new Dimension(30, 30));
		panelBotonesDerechaIzquierda.add(botonFlechaIzquierda, gridPosicion);

		gridPosicion.gridx = 2;
		gridPosicion.gridy = 0;

		JLabel etiquetaCriteriosSeleccionados = new JLabel("Criterios seleccionados ");
		Font fuente2 = etiquetaCriteriosSeleccionados.getFont();
		etiquetaCriteriosSeleccionados.setFont(new Font(fuente2.getFontName(), fuente2.getStyle(), 20));
		panelEtiquetaCriteriosSeleccionados.add(etiquetaCriteriosSeleccionados, gridPosicion);

		gridPosicion.gridx = 2;
		gridPosicion.gridy = 1;

		modelListaSeleccionados = new DefaultListModel<String>();
		listaSeleccionados = new JList<String>();

		listaSeleccionados.setToolTipText("Lista de criterios de desempate que sí se utilizarán en el torneo");
		listaSeleccionados.setPreferredSize(new Dimension(50, 450));
		listaSeleccionados.setVisibleRowCount(10);
		listaSeleccionados.setModel(modelListaSeleccionados);

		scrollCriteriosSeleccionados = new JScrollPane(listaSeleccionados);
		scrollCriteriosSeleccionados.setPreferredSize(new Dimension(250, 300));
		panelEtiquetaCriteriosSeleccionados.add(scrollCriteriosSeleccionados, gridPosicion);

		icon = new ImageIcon(getClass().getResource("/imagenes/arriba.png"));
		Action accionBotonDesplazarArriba = new AbstractAction("", icon) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionSubirPrioridad();
			}
		};
		accionBotonDesplazarArriba.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_3);
		accionBotonDesplazarArriba.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
		accionBotonDesplazarArriba.putValue(Action.SHORT_DESCRIPTION,
				"Permite aumentar la prioridad al criterio de desempate seleccionado");
		botonFlechaArriba = new JButton(accionBotonDesplazarArriba);
		botonFlechaArriba.getActionMap().put("botonFlechaArriba", accionBotonDesplazarArriba);
		botonFlechaArriba.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionBotonDesplazarArriba.getValue(Action.ACCELERATOR_KEY), "botonFlechaArriba");
		botonFlechaArriba.setPreferredSize(new Dimension(30, 30));
		panelBotonesArribaAbajo.add(botonFlechaArriba);

		icon = new ImageIcon(getClass().getResource("/imagenes/abajo.png"));
		Action accionBotonDesplazarAbajo = new AbstractAction("", icon) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionBajarPrioridad();
			}
		};
		accionBotonDesplazarAbajo.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_4);
		accionBotonDesplazarAbajo.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK));
		accionBotonDesplazarAbajo.putValue(Action.SHORT_DESCRIPTION,
				"Permite bajar la prioridad al criterio de desempate seleccionado");
		botonFlechaAbajo = new JButton(accionBotonDesplazarAbajo);
		botonFlechaAbajo.getActionMap().put("botonFlechaAbajo", accionBotonDesplazarAbajo);
		botonFlechaAbajo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionBotonDesplazarAbajo.getValue(Action.ACCELERATOR_KEY), "botonFlechaAbajo");
		botonFlechaAbajo.setPreferredSize(new Dimension(30, 30));
		panelBotonesArribaAbajo.add(botonFlechaAbajo);

		inicializarCriteriosNoSeleccionados();

		this.add(panelEtiquetaCriteriosDeDesempate);
		this.add(panelBotonesDerechaIzquierda);
		this.add(panelEtiquetaCriteriosSeleccionados);
		this.add(panelBotonesArribaAbajo);

		this.setVisible(false);
	}

	/**
	 * Permite mostrar el panel desde <code>DialogoAdministrarTorneo</code>
	 */
	public void mostrarPanel() {
		this.setVisible(true);
	}

	/**
	 * Permite ocultar el panel desde <code>DialogoAdministrarTorneo</code>
	 */
	public void ocultarPanel() {
		this.setVisible(false);
	}

	/**
	 * Permite pasar a los criterios seleccionados usando
	 * <code>botonFlechaDerecha</code>
	 */
	private void accionPasarACriteriosSeleccionados() {
		if (!(listaNoSeleccionados.getSelectedIndex() == -1)) {
			modelListaSeleccionados.addElement(listaNoSeleccionados.getSelectedValue());
			modelListaNoSeleccionados.remove(listaNoSeleccionados.getSelectedIndex());
		}
	}

	/**
	 * Permite pasar a los criterios no seleccionados usando
	 * <code>botonFlechaIzquierda</code>
	 */
	private void accionPasarACriteriosNoSeleccionados() {
		if (!(listaSeleccionados.getSelectedIndex() == -1)) {
			modelListaNoSeleccionados.addElement(listaSeleccionados.getSelectedValue());
			modelListaSeleccionados.remove(listaSeleccionados.getSelectedIndex());
		}
	}

	/**
	 * Permite subir la prioridad de los criterios seleccionados usando
	 * <code>botonFlechaArriba<code>
	 */
	private void accionSubirPrioridad() {
		if ((listaSeleccionados.getSelectedValue() != null)) {
			if (listaSeleccionados.getSelectedIndex() != 0) {
				int indice = listaSeleccionados.getSelectedIndex() - 1;
				modelListaSeleccionados.add(listaSeleccionados.getSelectedIndex() - 1,
						listaSeleccionados.getSelectedValue());
				modelListaSeleccionados.remove(listaSeleccionados.getSelectedIndex());
				listaSeleccionados.setSelectedIndex(indice);
			}
		}
	}

	/**
	 * Permite bajar la prioridad de los criterios seleccionados usando
	 * <code>botonFlechaAbajo<code>
	 */
	private void accionBajarPrioridad() {
		if ((listaSeleccionados.getSelectedValue() != null)) {
			if (listaSeleccionados.getSelectedIndex() != modelListaSeleccionados.size() - 1) {
				int indice = listaSeleccionados.getSelectedIndex() + 1;
				modelListaSeleccionados.add(listaSeleccionados.getSelectedIndex() + 2,
						listaSeleccionados.getSelectedValue());
				modelListaSeleccionados.remove(listaSeleccionados.getSelectedIndex());
				listaSeleccionados.setSelectedIndex(indice);
			}
		}
	}

	/**
	 * Permite inicializar los criterios de desempates
	 */
	private void inicializarCriteriosNoSeleccionados() {
		if (ventanaPrincipal.getTorneoActual() != null) {
			for (String criteriosS : ventanaPrincipal.getTorneoActual().getCriteriosDesempate()
					.getListaCriteriosSeleccionados()) {
				modelListaSeleccionados.addElement(criteriosS);

			}
			int n = ventanaPrincipal.getTorneoActual().getCriteriosDesempate().getListaCriteriosSeleccionados().size();
			for (String constanteCriterios : CriteriosDesempate.criterios) {
				int i = 0;
				for (String criteriosS : ventanaPrincipal.getTorneoActual().getCriteriosDesempate()
						.getListaCriteriosSeleccionados()) {
					if (!constanteCriterios.equals(criteriosS)) {

						i++;
					}
				}
				if (n == i) {
					modelListaNoSeleccionados.addElement(constanteCriterios);
				}
			}

		} else {
			for (String criterio : CriteriosDesempate.criterios) {
				modelListaNoSeleccionados.addElement(criterio);
			}
		}
	}

	/**
	 * Permite guardar los criterios seleccionados en el torneo actual.
	 */
	public void guardarCriteriosSeleccionados() {
		ArrayList<String> criteriosSeleccionados = new ArrayList<String>();
		for (int i = 0; i < modelListaSeleccionados.getSize(); i++) {
			criteriosSeleccionados.add(modelListaSeleccionados.get(i));
		}
		ventanaPrincipal.getTorneoActual().getCriteriosDesempate().setListaCriterios(criteriosSeleccionados);
	}

}
