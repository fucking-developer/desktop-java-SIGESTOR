package sigestor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import sigestor.dominio.Participante;
import sigestor.excepcion.ExcepcionUtilerias;
import sigestor.utilerias.UtileriasListaParticipantes;

/**
 * Sirve para manejar la interfaz de administrar participantes.
 * <p>
 * Las características de la clase <code>PanelAdministrarParticipantes</code>
 * son:
 * <ul>
 * <li><code>listaParticipantes</code> para manejar la lista de
 * participantes.</li>
 * <li><code>botonNuevo</code> para añadir un nuevo participante.</li>
 * <li><code>botonModificar</code> para modificar un participante.</li>
 * <li><code>botonEliminar</code> para eliminar un participante.</li>
 * <li><code>botonImportar</code> para importar los participantes.</li>
 * <li><code>botonDescargarPlantilla</code> para descargar la plantilla de los
 * participantes.</li>
 * <li><code>checkPuntaje</code> para ordenar los participantes por
 * puntaje.</li>
 * <li><code>opcionAlfabetico</code> para ordenar los participantes por orden
 * alfabético.</li>
 * <li><code>opcionAleatorio</code> para ordenar los participantes por orden
 * aleatorio.</li>
 * <li><code>model</code> para ayudar a manejar la lista de participantes.</li>
 * <li><code>listaDeParticipantes</code> para guardar la lista de participantes
 * de tipo <code>Participante</code>.</li>
 * <li><code>ventanaPrincipal</code> para hacer referencia a la clase
 * <code>VentanaPrincipal</code>.</li>
 * </ul>
 * 
 * @version 04/04/2023
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * @author Erik Vasquez Policarpo
 * @author German Luis cruz Martínez
 * 
 */
public class PanelAdministrarParticipantes extends JPanel {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Lista de participante de tipo cadena.
	 */
	private JList<String> listaParticipantes;
	/**
	 * Botón para agregar un nuevo participante.
	 */
	private JButton botonNuevo;
	/**
	 * Botón para modificar un participante.
	 */
	private JButton botonModificar;
	/**
	 * Botón para importar una lista de participantes.
	 */
	private JButton botonImportar;
	/**
	 * Botón para descargar la plantilla.
	 */
	private JButton botonDescargarPlantilla;
	/**
	 * Botón para eliminar un participante.
	 */
	private JButton botonEliminar;
	/**
	 * Cassilla de verificación para ordenar por puntaje.
	 */
	private JCheckBox checkPuntaje;
	/**
	 * Botón de opción para ordenar por orden alfabético.
	 */
	private JRadioButton opcionAlfabetico;
	/**
	 * Botón de opción para ordenar por orden aleatorio.
	 */
	private JRadioButton opcionAleatorio;
	/**
	 * Modelo auxiliar para la lista de participantes.
	 */
	private DefaultListModel<String> model;
	/**
	 * Objeto de tipo <code>Participante</code> para la lista de participantes.
	 */
	private ArrayList<Participante> listaDeParticipantes = new ArrayList<Participante>();
	/**
	 * Referencia a la clase <code>VentanaPrincipal</code>
	 */
	private VentanaPrincipal ventanaPrincipal;
	

	/**
	 * Constructor que consiste en mostrar en pantalla el
	 * <code>PanelAdministrarParticipantes</code> con sus respectivos componentes.
	 * 
	 * @param principal
	 *            Referencia a la clase <code>VentanaPrincipal</code>.
	 * 
	 */
	public PanelAdministrarParticipantes(VentanaPrincipal principal) {
		ventanaPrincipal = principal;

		JLabel etiquetaListaDeParticipantes = new JLabel();
		etiquetaListaDeParticipantes.setText("Lista de participantes");
		this.setLayout(new BorderLayout());

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(1, 2, 5, 5));
		JPanel panelAux = new JPanel();
		JPanel panelAux2 = new JPanel();
		JPanel panelAux3 = new JPanel();

		panelAux.add(panelAux2);
		panelAux.add(panelAux3);
		this.add(panelAux, BorderLayout.NORTH);
		// _____________________1_______________________Lista
		JPanel panelListaDeParticipantes = new JPanel();
		panelListaDeParticipantes.setLayout(new BoxLayout(panelListaDeParticipantes, BoxLayout.Y_AXIS));

		GridBagConstraints gridPosicion = new GridBagConstraints();

		gridPosicion.gridx = 0;
		gridPosicion.gridy = 0;
		Font fuente1 = etiquetaListaDeParticipantes.getFont();
		etiquetaListaDeParticipantes.setFont(new Font(fuente1.getFontName(), fuente1.getStyle(), 20));
		panelListaDeParticipantes.add(etiquetaListaDeParticipantes);

		model = new DefaultListModel<String>();
		listaParticipantes = new JList<String>();
		listaParticipantes.setPreferredSize(new Dimension(50, 450));
		listaParticipantes.setVisibleRowCount(10);
		listaParticipantes.setModel(model);
		JScrollPane scrollListaParticipantes = new JScrollPane(listaParticipantes);
		scrollListaParticipantes.setPreferredSize(new Dimension(350, 350));
		panelListaDeParticipantes.add(scrollListaParticipantes);
		panelPrincipal.add(panelListaDeParticipantes);

		// Botones
		JPanel panelBotonesConOpciones = new JPanel();
		panelBotonesConOpciones = new JPanel();
		panelBotonesConOpciones.setLayout(new BoxLayout(panelBotonesConOpciones, BoxLayout.Y_AXIS));

		panelAux3 = new JPanel();

		ImageIcon imagenBoton = new ImageIcon(getClass().getResource("/imagenes/nuevo.png"));
		Image icono = imagenBoton.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);

		Action accionBotonNuevo = new AbstractAction("Nuevo", new ImageIcon(icono)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionAgregarNuevoParticipante();
			}
		};
		accionBotonNuevo.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		accionBotonNuevo.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		accionBotonNuevo.putValue(Action.SHORT_DESCRIPTION, "Permite agregar a la lista un nuevo participante");
		botonNuevo = new JButton(accionBotonNuevo);
		botonNuevo.getActionMap().put("botonNuevo", accionBotonNuevo);
		botonNuevo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionBotonNuevo.getValue(Action.ACCELERATOR_KEY), "botonNuevo");
		botonNuevo.setPreferredSize(new Dimension(130, 30));
		panelAux3.add(botonNuevo);
		panelAux.add(panelAux3);

		panelAux3 = new JPanel();
		imagenBoton = new ImageIcon(getClass().getResource("/imagenes/modificar.png"));
		icono = imagenBoton.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);

		Action accionBotonModificar = new AbstractAction("Modificar", new ImageIcon(icono)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionModificarParticipante();
			}
		};
		accionBotonModificar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
		accionBotonModificar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		accionBotonModificar.putValue(Action.SHORT_DESCRIPTION, "Permite modificar de la lista a un participante");
		botonModificar = new JButton(accionBotonModificar);
		botonModificar.getActionMap().put("botonModificar", accionBotonModificar);
		botonModificar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionBotonModificar.getValue(Action.ACCELERATOR_KEY), "botonModificar");
		botonModificar.setPreferredSize(new Dimension(130, 30));
		panelAux3.add(botonModificar);
		panelAux.add(panelAux3);

		panelAux3 = new JPanel();
		imagenBoton = new ImageIcon(getClass().getResource("/imagenes/eliminar.png"));
		icono = imagenBoton.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);

		Action accionBotonEliminar = new AbstractAction("Eliminar", new ImageIcon(icono)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionEliminarParticipante();
			}
		};
		accionBotonEliminar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
		accionBotonEliminar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		accionBotonEliminar.putValue(Action.SHORT_DESCRIPTION, "Permite agregar un nuevo participante a la lista");
		botonEliminar = new JButton(accionBotonEliminar);
		botonEliminar.getActionMap().put("botonEliminar", accionBotonEliminar);
		botonEliminar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionBotonEliminar.getValue(Action.ACCELERATOR_KEY), "botonEliminar");
		botonEliminar.setPreferredSize(new Dimension(130, 30));
		panelAux3.add(botonEliminar);
		panelAux.add(panelAux3);

		panelAux3 = new JPanel();

		imagenBoton = new ImageIcon(getClass().getResource("/imagenes/importar.png"));
		icono = imagenBoton.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionBotonImportar = new AbstractAction("Importar", new ImageIcon(icono)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionImportarParticipantes();
			}
		};
		botonImportar = new JButton(accionBotonImportar);
		botonImportar.setPreferredSize(new Dimension(130, 30));
		botonImportar.setMnemonic(KeyEvent.VK_P);
		botonImportar.setToolTipText("Permite importar una lista de participantes");

		panelAux3.add(botonImportar);

		panelAux.add(panelAux3);

		panelAux3 = new JPanel();

		imagenBoton = new ImageIcon(getClass().getResource("/imagenes/descargarPlantilla.png"));
		icono = imagenBoton.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionBotonDescargarPlantilla = new AbstractAction("Descargar plantilla", new ImageIcon(icono)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionDescargarPlantilla();
			}
		};
		botonDescargarPlantilla = new JButton(accionBotonDescargarPlantilla);
		botonDescargarPlantilla.setPreferredSize(new Dimension(170, 30));
		botonDescargarPlantilla.setToolTipText("Permite descargar la plantilla de  participantes");
		botonDescargarPlantilla.setMnemonic(KeyEvent.VK_S);
		panelAux3.add(botonDescargarPlantilla);
		panelAux.add(panelAux3);

		// Criterios de desempates.....................
		panelAux3 = new JPanel();
		JLabel etiquetaCriteriosDesempate = new JLabel("Criterios de ordenación:");
		panelAux.setLayout(new BoxLayout(panelAux, BoxLayout.Y_AXIS));
		panelAux3.add(etiquetaCriteriosDesempate);
		panelAux.add(panelAux3);

		panelAux3 = new JPanel();
		checkPuntaje = new JCheckBox("Por puntaje", Participante.isPuntaje());
		checkPuntaje.setToolTipText("Ordena a los participantes por puntaje");

		panelAux3.add(checkPuntaje);
		panelAux.add(panelAux3);
		boolean aleatorio = false, alfabetico = false;

		if (Participante.isOrden()) {
			alfabetico = true;
		} else {
			aleatorio = true;
		}

		panelAux3 = new JPanel();
		opcionAlfabetico = new JRadioButton("Alfabético", alfabetico);
		opcionAlfabetico.setToolTipText("Ordena a los participantes alfabéticamente");
		panelAux3.add(opcionAlfabetico);
		panelAux2.add(panelAux3);

		panelAux3 = new JPanel();
		opcionAleatorio = new JRadioButton("Aleatorio", aleatorio);
		opcionAleatorio.setToolTipText("Ordena a los participantes aleatoriamente");
		panelAux3.add(opcionAleatorio);
		panelAux2.add(panelAux3);

		ButtonGroup grupoOrdenacion = new ButtonGroup();
		grupoOrdenacion.add(opcionAlfabetico);
		grupoOrdenacion.add(opcionAleatorio);

		panelAux.add(panelAux2);

		panelBotonesConOpciones.add(panelAux);

		panelPrincipal.add(panelBotonesConOpciones);

		inicializarParticipantes();

		this.add(panelPrincipal, BorderLayout.CENTER);
		this.setVisible(false);

		botonNuevo.setMnemonic(KeyEvent.VK_N);
		botonModificar.setMnemonic(KeyEvent.VK_M);
		botonEliminar.setMnemonic(KeyEvent.VK_L);

		listaParticipantes.setToolTipText("Lista de los participantes del torneo");

		botonModificar.setToolTipText("Permite modificar los datos de un participante de la lista");
		botonEliminar.setToolTipText("Permite eliminar a un participante de la lista");
		
	}

	/**
	 * Consiste en agregar un nuevo participante, primero se ingresa el nombre y por
	 * último se ingresa el puntaje.
	 * <p>
	 * Los datos ingresados se guardan en <code>listaDeParticipantes</code>.
	 */
	private void accionAgregarNuevoParticipante() {
		JPanel contenido = new JPanel();
		JPanel contenidoParticipante = new JPanel();
		JPanel panelAux = new JPanel();

		contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
		contenidoParticipante.setLayout(new BoxLayout(contenidoParticipante, BoxLayout.Y_AXIS));

		JLabel texto = new JLabel("Ingrese el nombre del participante:");
		panelAux.add(texto);
		contenidoParticipante.add(panelAux);

		JTextField campoNombreParticipante = new JTextField();
		campoNombreParticipante.setPreferredSize(new Dimension(150, 20));
		panelAux = new JPanel();
		panelAux.add(campoNombreParticipante);
		contenidoParticipante.add(panelAux);

		int np = JOptionPane.showOptionDialog(null, contenidoParticipante, "Nuevo participante",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

		panelAux = new JPanel();
		texto = new JLabel("Seleccione el puntaje para el participante " + campoNombreParticipante.getText());
		panelAux.add(texto);
		contenido.add(panelAux);

		SpinnerNumberModel sModel = new SpinnerNumberModel(0.0f, 0.0f, null, 0.1f);
		JSpinner spinner = new JSpinner(sModel);
		spinner.setPreferredSize(new Dimension(90, 20));
		panelAux = new JPanel();
		panelAux.add(spinner);
		contenido.add(panelAux);

		if (np == 0) {
			if (!campoNombreParticipante.getText().trim().isEmpty()) {
				int seleccion = JOptionPane.showOptionDialog(null, contenido, "Nuevo participante",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				Participante participante;

				if (seleccion == 0) {
					Float puntajeParticipante = (float) spinner.getValue();
					participante = new Participante((model.size() + 1), campoNombreParticipante.getText(),
							puntajeParticipante);
					model.addElement(participante.toString());
					listaDeParticipantes.add(participante);
				} else {
					participante = new Participante((model.size() + 1), campoNombreParticipante.getText(), 0.0f);
					model.addElement(participante.toString());
					listaDeParticipantes.add(participante);
				}

				JOptionPane.showMessageDialog(null,
						"El participante " + participante.getNombreParticipante() + " ha sido agregado exitosamente", "Nuevo participante", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null,
						"El campo 'Nombre del participante' no puede estar vacío.\n"
								+ "Ingrese un nombre para el participante.\n" + "Ejemplo: Pedro Cortes",
						"Nombre participante", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	/**
	 * Consiste en modificar un participante seleccionado de
	 * <code>listaDeParticipantes</code>, primero se modifica el nombre y por último
	 * se modifica el puntaje.
	 */
	private void accionModificarParticipante() {
		if (listaParticipantes.getSelectedValue() != null) {
			JPanel contenido = new JPanel();
			JPanel contenidoParticipante = new JPanel();
			JPanel panelAux = new JPanel();
			contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
			contenidoParticipante.setLayout(new BoxLayout(contenidoParticipante, BoxLayout.Y_AXIS));

			JLabel texto = new JLabel("Ingrese el nuevo nombre para el participante "
						+ listaDeParticipantes.get(listaParticipantes.getSelectedIndex()).getNombreParticipante() + " :");	
		
			panelAux.add(texto);
			contenidoParticipante.add(panelAux);

			JTextField campoNombreParticipante = new JTextField();
			campoNombreParticipante.setPreferredSize(new Dimension(150, 20));
			panelAux = new JPanel();
			panelAux.add(campoNombreParticipante);
			contenidoParticipante.add(panelAux);

			int np = JOptionPane.showOptionDialog(null, contenidoParticipante, "Modificar participante",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			texto = new JLabel("Seleccione el nuevo puntaje para el participante " + campoNombreParticipante.getText()
					+ " con el puntaje "
					+ listaDeParticipantes.get(listaParticipantes.getSelectedIndex()).getPuntajeParticipante());

			panelAux = new JPanel();
			panelAux.add(texto);
			contenido.add(panelAux);

			SpinnerNumberModel sModel = new SpinnerNumberModel(0.0f, 0.0f, null, 0.1f);
			JSpinner spinner = new JSpinner(sModel);
			spinner.setPreferredSize(new Dimension(90, 20));
			panelAux = new JPanel();
			panelAux.add(spinner);
			contenido.add(panelAux);
			if (np == 0) {
				if (!campoNombreParticipante.getText().trim().isEmpty()) {
					int seleccion = JOptionPane.showOptionDialog(null, contenido, "Modificar participante",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					Participante participante;

					if (seleccion == 0) {
						Float puntajeParticipante = (float) spinner.getValue();
						participante = new Participante(
								this.listaDeParticipantes.get(listaParticipantes.getSelectedIndex()).getNumeroParticipante(),
								campoNombreParticipante.getText(), puntajeParticipante);
					} else {
						participante = new Participante(
								this.listaDeParticipantes.get(listaParticipantes.getSelectedIndex()).getNumeroParticipante(),
								campoNombreParticipante.getText(), listaDeParticipantes.get(listaParticipantes.getSelectedIndex()).getPuntajeParticipante());
					}
					
					model.setElementAt(participante.toString(), listaParticipantes.getSelectedIndex());	
					listaDeParticipantes.set(listaParticipantes.getSelectedIndex(), participante);
				
					JOptionPane.showMessageDialog(null, "El participante "
							+ listaDeParticipantes.get(listaParticipantes.getSelectedIndex()).getNombreParticipante()
							+ " ha sido actualizado exitosamente","Nombre participante", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		}
	}

	/**
	 * Consiste en eliminar un participante de <code>listaDeParticipantes</code> y
	 * de <code>model</code>, antes de ello consulta al usuario sí está seguro de la
	 * acción.
	 */
	private void accionEliminarParticipante() {
		if (listaParticipantes.getSelectedValue() != null) {
			int seleccion = JOptionPane.showOptionDialog(null,
					"¿Está seguro de que desea eliminar al participante "
							+ listaDeParticipantes.get(listaParticipantes.getSelectedIndex()).getNombreParticipante() + "?",
					"Eliminar participantes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
					null);
			if (seleccion == 0) {
				listaDeParticipantes.remove(listaParticipantes.getSelectedIndex());
				model.remove(listaParticipantes.getSelectedIndex());
			}
		}
	}

	/**
	 * Consiste en inicializar los participantes de <code>VentanaPrincipal</code>.
	 */
	private void inicializarParticipantes() {
		if (ventanaPrincipal.getTorneoActual() != null) {
			for (int i = 0; i < ventanaPrincipal.getTorneoActual().getListaParticipantes().size(); i++) {
				
				if (ventanaPrincipal.getTorneoActual().getListaParticipantes().get(i).getNombreParticipante()
						.compareToIgnoreCase(ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
								.getNombreParticipanteSinEncuentro()) != 0) {
					model.addElement(ventanaPrincipal.getTorneoActual().getListaParticipantes().get(i).toString());
					//listaDeParticipantes = ventanaPrincipal.getTorneoActual().getListaParticipantes();
					listaDeParticipantes.add(ventanaPrincipal.getTorneoActual().getListaParticipantes().get(i));
					
				}
			}
		}

	}

	/**
	 * Guarda los participantes en <code>setListaParticipantes</code> de
	 * <code>VentanaPrincipal</code>.
	 */
	public void guardarParticipantes() {
		boolean ordenacion = false;
		if (!this.opcionAleatorio.isSelected()) {
			ordenacion = true;
		}
		Participante.setPuntaje(this.checkPuntaje.isSelected());
		Participante.setOrden(ordenacion);
		
		for (int i = 0; i < listaDeParticipantes.size() - 1; i++) {
			if (listaDeParticipantes.get(i).getNombreParticipante().compareToIgnoreCase(this.ventanaPrincipal
					.getTorneoActual().getDatosPersonalizacion().getNombreParticipanteSinEncuentro()) == 0) {
				//model.remove(i);
				listaDeParticipantes.remove(listaDeParticipantes.get(i));
			}
		}

		if (!ventanaPrincipal.getTorneoActual().getTipoTorneo().equals("Eliminación directa")) {
			if (listaDeParticipantes.size() % 2 != 0 && listaDeParticipantes.size() > 2) {
				Participante p = new Participante(listaDeParticipantes.size() + 1, this.ventanaPrincipal
						.getTorneoActual().getDatosPersonalizacion().getNombreParticipanteSinEncuentro(), 0.0f);
				p.setPuntajeAcumuladoParticipante(-1.0f);
				listaDeParticipantes.add(p);
			}
		}
		
		for(int i=0; i<listaDeParticipantes.size(); i++) {
			listaDeParticipantes.get(i).setNumeroParticipante(i+1);
		}
		
		Collections.sort(listaDeParticipantes);
		ventanaPrincipal.getTorneoActual().setListaParticipantes(listaDeParticipantes);
	}

	/**
	 * Consiste en mostrar el panel.
	 */
	public void mostrarPanel() {
		this.setVisible(true);
	}

	/**
	 * Consiste en ocultar el panel.
	 */
	public void ocultarPanel() {
		this.setVisible(false);
	}

	/**
	 * Consiste en abrir un archivo CSV que contiene una lista de participantes y el
	 * puntaje de dichos participantes.
	 */
	private void accionImportarParticipantes() {
		JFileChooser dialogo = new JFileChooser();
		dialogo.setDialogTitle("Importar lista de participantes");
		FileFilter filtro1 = new FileNameExtensionFilter("Archivo CSV", "csv", "CSV");
		dialogo.setFileFilter(filtro1);
		dialogo.setAcceptAllFileFilterUsed(false);
		dialogo.setCurrentDirectory(new File(System.getProperty("user.dir")));
		dialogo.setSelectedFile(null);
		dialogo.setMultiSelectionEnabled(false);
		int valor = dialogo.showOpenDialog(this);
		if (valor == JFileChooser.APPROVE_OPTION) {
			File archivo = dialogo.getSelectedFile();
			if (!archivo.equals(null) && !archivo.getName().toUpperCase().endsWith(".CSV")) {
				archivo = new File(archivo.getAbsolutePath() + ".CSV");
			}
			if (archivo.exists() && archivo.getName().toUpperCase().endsWith(".CSV")) {
				try {
					ArrayList<Participante> participantes = UtileriasListaParticipantes.leerListaParticipantes(archivo.getAbsolutePath());
					if(participantes.size() > 0) {
						for (Participante p : participantes) {
							Participante participante = new Participante((model.size() + 1), p.getNombreParticipante(),
									p.getPuntajeParticipante());
							model.addElement(participante.toString());
							listaDeParticipantes.add(participante);	
							listaParticipantes.setPreferredSize(new Dimension(50, listaDeParticipantes.size()*18));
						}
						JOptionPane.showMessageDialog(null,
								"Se han importado "+ participantes.size() +" participantes exitosamente",
								"Importar participantes", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ExcepcionUtilerias e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error al abrir el archivo",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "El archivo no existe o no es de tipo CSV",
						"Error al abrir el archivo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Permite descargar una plantilla con extensión CSV con un formato ya
	 * establecido.
	 */
	private void accionDescargarPlantilla() {
		JFileChooser dialogo = new JFileChooser();
		dialogo.setDialogTitle("Descargar plantilla de lista de participantes");
		FileFilter filtro1 = new FileNameExtensionFilter("Archivo CSV", "csv", "CSV");
		dialogo.setFileFilter(filtro1);
		dialogo.setAcceptAllFileFilterUsed(false);
		dialogo.setCurrentDirectory(new File(System.getProperty("user.dir")));
		File nombrePorDefecto = new File("Plantilla.CSV");
		dialogo.setSelectedFile(nombrePorDefecto);
		dialogo.setMultiSelectionEnabled(false);
		if (dialogo.showSaveDialog(ventanaPrincipal) == JFileChooser.APPROVE_OPTION) {
			File plantilla = dialogo.getSelectedFile();
			if (!plantilla.equals(null) && !plantilla.getName().toUpperCase().endsWith(".CSV")) {
				plantilla = new File(plantilla.getAbsolutePath() + ".CSV");
			}
			if (plantilla.exists()) {
				String[] valores = { "Sí", "No" };
				int opcionRemplazar = JOptionPane.showOptionDialog(null,
						plantilla.getName() + " ya existe.\n ¿Desea reemplazarlo?", "Confirmar Descargar plantilla",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, valores, valores[1]);
				if (opcionRemplazar != 0) {
					return;
				}
			}
			try {
				UtileriasListaParticipantes.escribirPlantilla(plantilla.getAbsolutePath());
				JOptionPane.showMessageDialog(null, "La plantilla se ha descargado correctamente",
						"Descargar plantilla", JOptionPane.INFORMATION_MESSAGE);
			} catch (ExcepcionUtilerias e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Descargar plantilla", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}