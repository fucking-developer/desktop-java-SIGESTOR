package sigestor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.util.Date;

import javax.swing.BoxLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

/**
 * Sirve para mostrar la interfaz donde permitirá capturar,mostrar y modificar
 * los datos generales del torneo
 * <p>
 * las caracteristicas de la clase<code>PanelGeneral</code> son:
 * <ul>
 * <li><code>comboTipoTorneo</code>Para mostrar los dos tipos de torneo</li>
 * <li><code>campoOrganizador</code>Para asignar un nombre al organizador del
 * torneo</li>
 * <li><code>campoNombreTorneo</code>Para asignar un nombre al torneo</li>
 * <li><code>campoDescripcion</code>Para asignar una descripcion al torneo</li>
 * <li><code>fechaTorneoFin</code>Para asignar una fecha final al torneo</li>
 * <li><code>fechaTorneoInicio</code>para asignar una fecha inicial al
 * torneo</li>
 * <li><code>principal</code>Para obtener el torneo actual</li>
 * </ul>
 * 
 * @version 24/04/2022
 * 
 * @author Beatriz Andrea Jimenez Rios
 * @author Uriel Romeo Cruz Cortes
 * @author Ricky Didier Peralta Reyes
 * @author Jennifer Cortés Pérez
 *
 */
public class PanelGeneral extends JPanel {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * lista desplegable para seleccionar el tipo de torneo.
	 */
	private JComboBox<String> comboTipoTorneo;
	/**
	 * Area de texto para el nombre del organizador del torneo.
	 */
	private JTextField campoOrganizador;
	/**
	 * Area de texto para el nombre del torneo.
	 */
	private JTextField campoNombreTorneo;
	/**
	 * Area de texto para la descripcion del torneo.
	 */
	private JTextArea campoDescripcion;
	/**
	 * Calendario para seleccionar la fecha final del torneo.
	 */
	private JDateChooser fechaTorneoFin;
	/**
	 * Calendario para seleccionar la fecha inicial del torneo.
	 */
	private JDateChooser fechaTorneoInicio;
	/**
	 * Objeto de tipo ventana principal para guardar el torneo actual.
	 */
	private VentanaPrincipal principal;

	/**
	 * Constructor que permite visualizar en panel en el dialogo
	 * <code>DialogoAdministrarTorneo.</code>
	 * 
	 * @param principal recibe un objeto de tipo ventanaPrincipal el cual contiene el objeto
	 *               de tipo torneo.
	 */
	public PanelGeneral(VentanaPrincipal principal) {
		JLabel etiquetaTipoTorneo = new JLabel(" Tipo de torneo        ");
		JLabel etiquetaNombreTorneo = new JLabel(" Nombre del torneo:        ");
		JLabel etiquetaFechaFinalTorneo = new JLabel(" Fecha final del torneo:   ");
		JLabel etiquetaNombreOrganizadorTorneo = new JLabel(" Organizador del torneo:");
		JLabel etiquetaFechaInicioTorneo = new JLabel(" Fecha inicio del torneo: ");
		JLabel etiquetaDescripcionTorneo = new JLabel(" Descripción:                        ");
		etiquetaTipoTorneo.setDisplayedMnemonic('T');

		this.principal = principal;

		this.setLayout(new BorderLayout());

		JPanel panelAux = new JPanel();

		String[] arregloTipoTorneo = { "Suizo", "Round Robin" , "Eliminación directa"};
		panelAux.add(etiquetaTipoTorneo);
		comboTipoTorneo = new JComboBox<String>(arregloTipoTorneo);
		comboTipoTorneo.setPreferredSize(new Dimension(150, 30));
		comboTipoTorneo.setToolTipText("Seleccione el tipo de torneo");
		panelAux.add(comboTipoTorneo);

		this.add(panelAux, BorderLayout.NORTH);
		// ______parte 1 de la pantalla________
		panelAux = new JPanel();
		panelAux.setLayout(new BoxLayout(panelAux, BoxLayout.Y_AXIS));
		JPanel panelAux2 = new JPanel();
		panelAux2.setLayout(new GridLayout(2, 2));
		JPanel panelAux3 = new JPanel();
		panelAux3.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel panelAux4 = new JPanel();

		panelAux4.add(etiquetaNombreTorneo);
		panelAux3.add(panelAux4);

		panelAux4 = new JPanel();
		campoNombreTorneo = new JTextField();
		campoNombreTorneo.setPreferredSize(new Dimension(130, 30));
		campoNombreTorneo.setToolTipText("Ingrese un nombre para el torneo");
		panelAux4.add(campoNombreTorneo);
		panelAux3.add(panelAux4);

		panelAux2.add(panelAux3);

		panelAux3 = new JPanel();
		panelAux3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux4 = new JPanel();

		panelAux4.add(etiquetaFechaInicioTorneo);
		panelAux3.add(panelAux4);

		panelAux4 = new JPanel();
		fechaTorneoInicio = new JDateChooser(new Date());
		fechaTorneoInicio.setPreferredSize(new Dimension(130, 30));
		panelAux4.add(fechaTorneoInicio);
		panelAux3.add(panelAux4);

		panelAux2.add(panelAux3);

		panelAux3 = new JPanel();
		panelAux3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux4 = new JPanel();

		panelAux4.add(etiquetaNombreOrganizadorTorneo);
		panelAux3.add(panelAux4);

		panelAux4 = new JPanel();
		campoOrganizador = new JTextField();
		campoOrganizador.setPreferredSize(new Dimension(130, 30));
		campoOrganizador.setToolTipText("Ingrese el nombre del organizador del torneo");
		panelAux4.add(campoOrganizador);
		panelAux3.add(panelAux4);

		panelAux2.add(panelAux3);

		panelAux3 = new JPanel();
		panelAux3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux4 = new JPanel();

		panelAux4.add(etiquetaFechaFinalTorneo);
		panelAux3.add(panelAux4);

		panelAux4 = new JPanel();
		fechaTorneoFin = new JDateChooser(new Date());
		fechaTorneoFin.setPreferredSize(new Dimension(130, 30));
		panelAux4.add(fechaTorneoFin);
		panelAux3.add(panelAux4);

		panelAux2.add(panelAux3);

		panelAux.add(panelAux2);
		// _______parte de abajo____________
		panelAux2 = new JPanel();
		panelAux2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux3 = new JPanel();

		panelAux3.add(etiquetaDescripcionTorneo);

		campoDescripcion = new JTextArea(10, 12);
		JScrollPane paraDes = new JScrollPane(campoDescripcion);
		campoDescripcion.setLineWrap(true);
		campoDescripcion.setToolTipText("Escriba una breve descripción del torneo, este campo es opcional");
		campoDescripcion.setWrapStyleWord(true);
		panelAux3.add(paraDes);

		panelAux2.add(panelAux3);
		panelAux.add(panelAux2);

		etiquetaTipoTorneo.setDisplayedMnemonic('T');
		etiquetaTipoTorneo.setLabelFor(comboTipoTorneo);
		etiquetaNombreTorneo.setDisplayedMnemonic('N');
		etiquetaNombreTorneo.setLabelFor(campoNombreTorneo);
		etiquetaFechaFinalTorneo.setDisplayedMnemonic('F');
		etiquetaFechaFinalTorneo.setLabelFor(fechaTorneoFin);
		etiquetaNombreOrganizadorTorneo.setDisplayedMnemonic('O');
		etiquetaNombreOrganizadorTorneo.setLabelFor(campoOrganizador);
		etiquetaFechaInicioTorneo.setDisplayedMnemonic('I');
		etiquetaFechaInicioTorneo.setLabelFor(fechaTorneoInicio);
		etiquetaDescripcionTorneo.setDisplayedMnemonic('S');
		etiquetaDescripcionTorneo.setLabelFor(campoDescripcion);

		inicializarPanelGeneral();

		this.add(panelAux, BorderLayout.CENTER);
		this.setVisible(false);
	}

	/**
	 * Inicializa los datos del objeto Torneo en los componentes del
	 * <code>panelGeneral.</code>
	 */
	public void inicializarPanelGeneral() {
		if (principal.getTorneoActual() != null) {
			this.comboTipoTorneo.setSelectedItem(principal.getTorneoActual().getTipoTorneo());
			this.campoNombreTorneo.setText(principal.getTorneoActual().getNombreTorneo());
			this.campoOrganizador.setText(principal.getTorneoActual().getNombreOrganizador());
			this.campoDescripcion.setText(principal.getTorneoActual().getDescripcionTorneo());
			this.fechaTorneoInicio.setDate(principal.getTorneoActual().getFechaInicioTorneo());
			this.fechaTorneoFin.setDate(principal.getTorneoActual().getFechaFinalTorneo());
		}
	}

	/**
	 * Guarda en el objeto principal de tipo Ventana principal los datos generales
	 * del torneo.
	 */
	public void guardarGeneral() {
		principal.getTorneoActual().setTipoTorneo((String) comboTipoTorneo.getSelectedItem());
		principal.getTorneoActual().setNombreTorneo(campoNombreTorneo.getText());
		principal.getTorneoActual().setNombreOrganizador(campoOrganizador.getText());
		principal.getTorneoActual().setDescripcionTorneo(campoDescripcion.getText());
		principal.getTorneoActual().setFechaFinalTorneo(fechaTorneoFin.getDate());
		principal.getTorneoActual().setFechaInicioTorneo(fechaTorneoInicio.getDate());
	}

	/**
	 * Permite mostrar el panel en el dialogo <code>DialogoAdministrarTorneo.</code>
	 */
	public void mostrarPanel() {
		this.setVisible(true);
	}

	/**
	 * Permite ocultar el panel en el dialogo <code>DialogoAdministrarTorneo.</code>
	 */
	public void ocultarPanel() {
		this.setVisible(false);
	}
}
