package sigestor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import sigestor.dominio.Ciclo;
import sigestor.dominio.Encuentro;
import sigestor.dominio.Participante;
import sigestor.dominio.Personalizacion;
import sigestor.dominio.Torneo;
import sigestor.excepcion.ExcepcionUtilerias;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Sirve para mostrar la interfaz donde se permitirá ver los resultados de cada
 * pareo.
 * <p>
 * Las características de la clase <code>DialogoMostrarResultados</code> son:
 * <ul>
 * <li><code>comboSeleccionarCiclo</code> Para mostrar todos los ciclos que
 * contiene el torneo.</li>
 * <li><code>campoTorneo</code> Para mostrar el nombre del torneo.</li>
 * <li><code>campoOrganizador</code> Para mostrar el nombre del organizador del
 * torneo.</li>
 * <li><code>campoFechaInicio</code> Para mostrar la fecha de inicio del
 * torneo.</li>
 * <li><code>campoFechaFin</code> Para mostrar la fecha de fin del torneo.</li>
 * <li><code>campoMarcadorInicial</code> Para mostrar el marcador del
 * participante inicial.</li>
 * <li><code>etiquetaResultado</code> para mostrar el resultado obtenido del
 * pareo.</li>
 * <li><code>campoMarcadorFinal</code> Para mostrar el marcador del participante
 * no inicial.</li>
 * <li><code>botonRegresar</code> Para cerrar la ventana Reporte de resultados y
 * volver a la ventana Capturar resultados.</li>
 * <li><code>botonExportarResultados</code> para exportar los resultados
 * obtenidos en el ciclo.</li>
 * <li><code>etiquetaNumeroInicial</code> Para mostrar el número que representa
 * al participante inicial.</li>
 * <li><code>etiquetaParticipanteInicial</code> Para asignar el marcador que
 * obtendrá el participante inicial.</li>
 * <li><code>etiquetaNumeroFinal</code> Para mostrar el número que representa al
 * participante no inicial.</li>
 * <li><code>etiquetaParticipanteFinal</code> Para asignar el marcador que
 * obtendrá el participante no inicial.</li>
 * <li><code>torneo</code> Para obtener toda la información contenida del
 * torneo.</li>
 * <li><code>participantes</code> Para obtener a todos los participantes del
 * torneo.</li>
 * <li><code>personalizacion</code> Para obtener todos los datos de la
 * personalización del torneo.</li>
 * <li><code>ventanaPrincipal</code> Para obtener datos almacenados en
 * <code>VentanaPrincipal</code>.</li>
 * <li><code>numeroPartidas</code> Para obtener el número de pareos que tendrá
 * un ciclo.</li>
 * <li><code>validacionMarcadores</code> Para validar si los marcadores están
 * activados o no.</li>
 * <li><code>indiceMostrarResultados</code></li>
 * <li><code>serialVersionUID</code> Para el número de versión de la clase.</li>
 * </ul>
 * 
 * @version 22/05/2022
 * 
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 *
 */
public class DialogoMostrarResultados extends JDialog {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	// Superior-----------------------------------------------------
	/**
	 * Lista desplegable para seleccionar el ciclo.
	 */
	private JComboBox<Ciclo> comboSeleccionarCiclo;

	/**
	 * Campo para el nombre del torneo.
	 */
	private JTextField campoTorneo;

	/**
	 * Campo para el nombre del organizador.
	 */
	private JTextField campoOrganizador;

	/**
	 * Campo para la fecha de inicio.
	 */
	private JTextField campoFechaInicio;

	/**
	 * Campo para la fecha de fin.
	 */
	private JTextField campoFechaFin;
	// Central-----------------------------------------------------
	/**
	 * Arreglo de campos para los marcadores de participantes iniciales.
	 */
	private JTextField[] campoMarcadorInicial;

	/**
	 * Arreglo de campos para los marcadores de participantes no iniciales
	 */
	private JTextField[] campoMarcadorFinal;
	// Inferior-----------------------------------------------------
	/**
	 * Botón <code>Exportar resultados</code>.
	 * 
	 * @see #accionBotonExportarResultados()
	 */
	private JButton botonExportarResultados;

	/**
	 * Botón <code>Salir</code>.
	 * 
	 * @see #accionBtnSalir()
	 */
	private JButton botonSalir;

	/**
	 * Arreglo de etiquetas para los números que identificarán a los participantes
	 * iniciales.
	 */
	
	private JDateChooser etiquetaFechaEncuentros;
	
	private JLabel[] etiquetaNumeroInicial;

	/**
	 * Arreglo de etiquetas para los nombres de los participantes iniciales.
	 */
	private JLabel[] etiquetaParticipanteInicial;

	/**
	 * Arreglo de etiquetas para los números que identificarán a los participantes
	 * no iniciales.
	 */
	private JLabel[] etiquetaNumeroFinal;

	/**
	 * Arreglo de etiquetas para los nombres de los participantes no iniciales.
	 */
	private JLabel[] etiquetaParticipanteFinal;

	/**
	 * Arreglo de etiquetas para los resultados de los pareos.
	 */
	private JLabel[] etiquetaResultado;

	/**
	 * Referencia a la clase <code>VentanaPrincipal</code>
	 */
	private VentanaPrincipal ventanaPrincipal;

	/**
	 * Sirve para almacenar la cantidad de numero de pareos de un ciclo.
	 */
	private int numeroPartidas;

	/**
	 * Sirve para acceder a los datos que tenga almacenado el torneo.
	 */
	private Torneo torneo;

	/**
	 * Sirve para obtener la lista de participantes del torneo.
	 */
	private ArrayList<Participante> participantes;

	/**
	 * Sirve para obtener los datos personalizados del torneo.
	 */
	private Personalizacion personalizacion;

	/**
	 * Variable temporal que indicará si se activaron los marcadores o no.
	 */
	private boolean validacionMarcadores;

	/**
	 * Permite mostrar los resultados con base al ciclo seleccionado.
	 */
	private int indiceMostrarResultados;

	/**
	 * Constructor en el que se inicializa el diálogo.
	 * 
	 * @param principal               Recibe un objeto de tipo ventanaPrincipal el
	 *                                cual contiene el objeto de tipo torneo.
	 * @param indiceMostrarResultados El ciclo a mostrar los resultados.
	 */
	public DialogoMostrarResultados(VentanaPrincipal principal, int indiceMostrarResultados) {
		super(principal, "Reporte de resultados");
		this.setModal(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				accionBtnSalir();
			}
		});

		ventanaPrincipal = principal;

		this.indiceMostrarResultados = indiceMostrarResultados;
		numeroPartidas = this.ventanaPrincipal.getTorneoActual().getAlgoritmoTorneo().getCiclos()
				.get(this.ventanaPrincipal.getTorneoActual().getCicloActual() - 1).getEncuentroParticipantes().size();
		torneo = principal.getTorneoActual();
		participantes = torneo.getListaParticipantes();
		personalizacion = torneo.getDatosPersonalizacion();
		this.validacionMarcadores = ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().isExistenciaMarcador();

		int auxImpar = 0;
		if (participantes.size() % 2 != 0) {
			auxImpar = 1;
		}
		int altoTitulos = 45;

		this.setLayout(new BorderLayout());

		JPanel superiorPanel = new JPanel();
		superiorPanel.setLayout(new GridLayout(2, 1));
		JPanel panelAuxSup = new JPanel();

		JPanel centralPanel = new JPanel();
		centralPanel.setLayout(new BorderLayout());
		JPanel panelSuperiorCentral = new JPanel();
		panelSuperiorCentral.setLayout(new GridLayout(2, 1));
		JPanel panelEncabezados = new JPanel();
		panelEncabezados.setLayout(new FlowLayout(1));
		JPanel panelCentralCentral = new JPanel();
		panelCentralCentral.setLayout(new GridLayout(numeroPartidas + auxImpar, 1, 1, 2));
		panelCentralCentral.setBackground(new Color(100, 105, 105));
		panelCentralCentral.setSize(200, numeroPartidas * 300);

		JPanel panelFilasTabla[] = new JPanel[numeroPartidas + auxImpar];

		JPanel inferiorPanel = new JPanel();
		inferiorPanel.setLayout(new FlowLayout(1, 150, 1));

		JPanel auxPanel;

		Border borde = BorderFactory.createLineBorder(Color.BLACK);

		/*
		 * Panel
		 * superior---------------------------------------------------------------------
		 * -----------------------------------------------------------------------------
		 * -
		 */
		panelAuxSup.setLayout(new FlowLayout(-1));
		JLabel etiquetaTipoTorneo = new JLabel("Sistema " + this.ventanaPrincipal.getTorneoActual().getTipoTorneo());
		etiquetaTipoTorneo.setFont(
				new Font(etiquetaTipoTorneo.getFont().getFontName(), etiquetaTipoTorneo.getFont().getStyle(), 30));
		auxPanel = new JPanel();
		auxPanel.add(etiquetaTipoTorneo);
		auxPanel.setPreferredSize(new Dimension(350, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAuxSup.add(auxPanel);

		JLabel etiquetaTorneo = new JLabel("Torneo:");
		etiquetaTorneo.setPreferredSize(new Dimension(90, 25));
		auxPanel = new JPanel();
		auxPanel.add(etiquetaTorneo);
		auxPanel.setPreferredSize(new Dimension(100, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		campoTorneo = new JTextField();
		campoTorneo.setPreferredSize(new Dimension(230, 25));
		campoTorneo.setEditable(false);
		auxPanel = new JPanel();
		auxPanel.add(campoTorneo);
		auxPanel.setPreferredSize(new Dimension(350, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		JLabel etiquetaFechaInicio = new JLabel("Fecha de inicio:");
		etiquetaFechaInicio.setPreferredSize(new Dimension(90, 25));
		auxPanel = new JPanel();
		auxPanel.add(etiquetaFechaInicio);
		auxPanel.setPreferredSize(new Dimension(100, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		campoFechaInicio = new JTextField();
		campoFechaInicio.setPreferredSize(new Dimension(220, 25));
		campoFechaInicio.setEditable(false);
		auxPanel = new JPanel();
		auxPanel.add(campoFechaInicio);
		auxPanel.setPreferredSize(new Dimension(250, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		superiorPanel.add(panelAuxSup);
		panelAuxSup = new JPanel();
		panelAuxSup.setLayout(new FlowLayout(-1));

		auxPanel = new JPanel();
		auxPanel.setPreferredSize(new Dimension(70, 50));
		panelAuxSup.add(auxPanel);
		JLabel etiquetaCicloActual = new JLabel();
		if (this.torneo.getAlgoritmoTorneo().verificarResultadosCompletos()) {
			etiquetaCicloActual.setText("Torneo finalizado");
		} else {
			etiquetaCicloActual = new JLabel(
					this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreCiclo(1) + " actual: "
							+ (this.indiceMostrarResultados + 1));
		}
		etiquetaCicloActual.setPreferredSize(new Dimension(150, 25));
		auxPanel = new JPanel();
		auxPanel.add(etiquetaCicloActual);
		auxPanel.setPreferredSize(new Dimension(277, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);
		Action accionAyuda = new AbstractAction("Ayuda") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaPrincipal.cargarManual();
			}
		};
		accionAyuda.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		etiquetaCicloActual.getActionMap().put("ayuda", accionAyuda);
		etiquetaCicloActual.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionAyuda.getValue(Action.ACCELERATOR_KEY), "ayuda");

		JLabel etiquetaOrganizador = new JLabel("Organizador:");
		etiquetaOrganizador.setPreferredSize(new Dimension(90, 25));
		auxPanel = new JPanel();
		auxPanel.add(etiquetaOrganizador);
		auxPanel.setPreferredSize(new Dimension(100, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		campoOrganizador = new JTextField("");
		campoOrganizador.setPreferredSize(new Dimension(230, 25));
		campoOrganizador.setEditable(false);
		auxPanel = new JPanel();
		auxPanel.add(campoOrganizador);
		auxPanel.setPreferredSize(new Dimension(350, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		JLabel etiquetaFechaFin = new JLabel("Fecha de fin:");
		etiquetaFechaFin.setPreferredSize(new Dimension(90, 25));
		auxPanel = new JPanel();
		auxPanel.add(etiquetaFechaFin);
		auxPanel.setPreferredSize(new Dimension(100, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		campoFechaFin = new JTextField();
		campoFechaFin.setPreferredSize(new Dimension(220, 25));
		campoFechaFin.setEditable(false);
		auxPanel = new JPanel();
		auxPanel.add(campoFechaFin);
		auxPanel.setPreferredSize(new Dimension(250, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		superiorPanel.add(panelAuxSup);
		/*
		 * Panel
		 * central----------------------------------------------------------------------
		 * -----------------------------------------------------------------------------
		 */
		JPanel tituloTabla = new JPanel();
		tituloTabla.setLayout(new GridLayout(2, 1));

		JLabel etiquetaElegirCiclo;
		etiquetaElegirCiclo = new JLabel(
				"Elegir " + this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreCiclo(1) + ": ");
		// etiquetaElegirCiclo.setDisplayedMnemonic(KeyEvent.VK_C);
		JLabel etiquetaTituloTabla = new JLabel();
		auxPanel = new JPanel();
		auxPanel.add(etiquetaElegirCiclo);

		comboSeleccionarCiclo = new JComboBox<Ciclo>();
		comboSeleccionarCiclo.setPreferredSize(new Dimension(120, 25));

		auxPanel.add(comboSeleccionarCiclo);
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		tituloTabla.add(auxPanel);
		Action accionSeleccionarCiclo = new AbstractAction("Seleccionarciclo", null) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionComboSeleccionarCiclo();
				etiquetaTituloTabla.setText("Tabla de resultados de "
						+ ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreCiclo(3) + " "
						+ (comboSeleccionarCiclo.getSelectedIndex() + 1));
			}
		};
		accionSeleccionarCiclo.putValue(Action.SHORT_DESCRIPTION,
				"Seleccione " + this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreCiclo(3)
						+ " para ver los resultados");
		comboSeleccionarCiclo.setAction(accionSeleccionarCiclo);
		comboSeleccionarCiclo.getActionMap().put("seleccionarciclo", accionSeleccionarCiclo);
		comboSeleccionarCiclo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionSeleccionarCiclo.getValue(Action.ACCELERATOR_KEY), "seleccionarciclo");

		etiquetaElegirCiclo.setDisplayedMnemonic(KeyEvent.VK_I);
		etiquetaElegirCiclo.setLabelFor(comboSeleccionarCiclo);

		auxPanel = new JPanel();
		auxPanel.setLayout(new FlowLayout(1));

		etiquetaTituloTabla.setFont(
				new Font(etiquetaTipoTorneo.getFont().getFontName(), etiquetaTipoTorneo.getFont().getStyle(), 20));
		auxPanel.add(etiquetaTituloTabla);
		tituloTabla.add(auxPanel);
		panelSuperiorCentral.add(tituloTabla);

		JLabel etiquetaTitulosTabla = new JLabel("Núm.");
		auxPanel = new JPanel();
		auxPanel.setPreferredSize(new Dimension(50, altoTitulos));
		auxPanel.add(etiquetaTitulosTabla);
		auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
		panelEncabezados.add(auxPanel);

		etiquetaTitulosTabla = new JLabel(
				this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipanteInicial());
		auxPanel = new JPanel();
		auxPanel.setPreferredSize(new Dimension(260, altoTitulos));
		auxPanel.add(etiquetaTitulosTabla);
		auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
		panelEncabezados.add(auxPanel);

		if (this.validacionMarcadores) {
			etiquetaTitulosTabla = new JLabel(
					this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreMarcador(1));
			auxPanel = new JPanel();
			auxPanel.setPreferredSize(new Dimension(70, altoTitulos));
			auxPanel.add(etiquetaTitulosTabla);
			auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
			panelEncabezados.add(auxPanel);
		}

		etiquetaTitulosTabla = new JLabel("Resultado");
		auxPanel = new JPanel();
		auxPanel.setPreferredSize(new Dimension(200, altoTitulos));
		auxPanel.add(etiquetaTitulosTabla);
		auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
		panelEncabezados.add(auxPanel);

		etiquetaTitulosTabla = new JLabel("Núm.");
		auxPanel = new JPanel();
		auxPanel.setPreferredSize(new Dimension(50, altoTitulos));
		auxPanel.add(etiquetaTitulosTabla);
		auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
		panelEncabezados.add(auxPanel);

		etiquetaTitulosTabla = new JLabel(
				this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipanteFinal());
		auxPanel = new JPanel();
		auxPanel.setPreferredSize(new Dimension(230, altoTitulos));
		auxPanel.add(etiquetaTitulosTabla);
		auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
		panelEncabezados.add(auxPanel);
		
		if (this.validacionMarcadores) {
			etiquetaTitulosTabla = new JLabel(
					this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreMarcador(1));
			auxPanel = new JPanel();
			auxPanel.setPreferredSize(new Dimension(70, altoTitulos));
			auxPanel.add(etiquetaTitulosTabla);
			auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
			panelEncabezados.add(auxPanel);

		}		
		
		etiquetaTitulosTabla = new JLabel("Fecha del encuentro");
		auxPanel = new JPanel();
		auxPanel.setPreferredSize(new Dimension(200, altoTitulos));
		auxPanel.add(etiquetaTitulosTabla);
		auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
		panelEncabezados.add(auxPanel);



		auxPanel = new JPanel();
		panelEncabezados.add(auxPanel);

		panelSuperiorCentral.add(panelEncabezados);

		etiquetaNumeroInicial = new JLabel[numeroPartidas + auxImpar];
		etiquetaParticipanteInicial = new JLabel[numeroPartidas + auxImpar];
		campoMarcadorInicial = new JTextField[numeroPartidas + auxImpar];
		etiquetaNumeroFinal = new JLabel[numeroPartidas + auxImpar];
		etiquetaParticipanteFinal = new JLabel[numeroPartidas + auxImpar];
		campoMarcadorFinal = new JTextField[numeroPartidas + auxImpar];
		etiquetaResultado = new JLabel[numeroPartidas + auxImpar];

		for (int i = 0; i < numeroPartidas + auxImpar; i++) {
			panelFilasTabla[i] = new JPanel();
			panelFilasTabla[i].setLayout(new FlowLayout(1));

			etiquetaNumeroInicial[i] = new JLabel(String.valueOf(i + 1));
			auxPanel = new JPanel();
			auxPanel.add(etiquetaNumeroInicial[i]);
			auxPanel.setPreferredSize(new Dimension(50, 30));

			panelFilasTabla[i].add(auxPanel);

			etiquetaParticipanteInicial[i] = new JLabel();
			auxPanel = new JPanel();
			auxPanel.add(etiquetaParticipanteInicial[i]);
			auxPanel.setPreferredSize(new Dimension(250, 30));

			panelFilasTabla[i].add(auxPanel);

			if (this.validacionMarcadores) {
				campoMarcadorInicial[i] = new JTextField();
				campoMarcadorInicial[i].setEditable(false);
				campoMarcadorInicial[i].setPreferredSize(new Dimension(60, 30));
				auxPanel = new JPanel();
				auxPanel.add(campoMarcadorInicial[i]);
				auxPanel.setPreferredSize(new Dimension(70, 35));

				panelFilasTabla[i].add(auxPanel);
			}

			etiquetaResultado[i] = new JLabel();
			auxPanel = new JPanel();
			auxPanel.add(etiquetaResultado[i]);
			auxPanel.setPreferredSize(new Dimension(200, 30));

			panelFilasTabla[i].add(auxPanel);

			etiquetaNumeroFinal[i] = new JLabel();
			auxPanel = new JPanel();
			auxPanel.add(etiquetaNumeroFinal[i]);
			auxPanel.setPreferredSize(new Dimension(50, 30));

			panelFilasTabla[i].add(auxPanel);

			etiquetaParticipanteFinal[i] = new JLabel();
			auxPanel = new JPanel();
			auxPanel.add(etiquetaParticipanteFinal[i]);
			auxPanel.setPreferredSize(new Dimension(230, 30));
			panelFilasTabla[i].add(auxPanel);
			if (this.validacionMarcadores) {
				campoMarcadorFinal[i] = new JTextField();
				campoMarcadorFinal[i].setEditable(false);
				campoMarcadorFinal[i].setPreferredSize(new Dimension(60, 30));
				auxPanel = new JPanel();
				auxPanel.add(campoMarcadorFinal[i]);
				auxPanel.setPreferredSize(new Dimension(70, 35));

				
				panelFilasTabla[i].add(auxPanel);
				
			}		
			
			etiquetaFechaEncuentros = new JDateChooser(new Date());
			etiquetaFechaEncuentros.setPreferredSize(new Dimension(150, 25));
			auxPanel = new JPanel();
			auxPanel.add(etiquetaFechaEncuentros);
			auxPanel.setPreferredSize(new Dimension(200, 30));
			panelFilasTabla[i].add(auxPanel);

			// auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
			panelFilasTabla[i].add(auxPanel);



			panelCentralCentral.add(panelFilasTabla[i]);

		}

		if (participantes.size() % 2 != 0) {
			etiquetaNumeroInicial[numeroPartidas]
					.setText(participantes.get(participantes.size() - 1).getNumeroParticipante() + "");
			etiquetaParticipanteInicial[numeroPartidas]
					.setText(participantes.get(participantes.size() - 1).getNombreParticipante() + " - "
							+ personalizacion.getNombreParticipanteSinEncuentro());
			campoMarcadorInicial[numeroPartidas].setVisible(false);

			campoMarcadorFinal[numeroPartidas].setVisible(false);
		}

		JScrollPane scrollTabla = new JScrollPane(panelCentralCentral);

		centralPanel.add(panelSuperiorCentral, BorderLayout.NORTH);
		centralPanel.add(scrollTabla, BorderLayout.CENTER);

		ImageIcon imagenF;
		Image imagenE;

		imagenF = new ImageIcon(getClass().getResource("/imagenes/exportarRonda.png"));
		imagenE = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionExportarResultados = new AbstractAction("Exportar resultados", new ImageIcon(imagenE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionBotonExportarResultados();
			}
		};
		accionExportarResultados.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		accionExportarResultados.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		accionExportarResultados.putValue(Action.SHORT_DESCRIPTION,
				"Obtiene los resultados y los almacena en un archivo CSV");
		botonExportarResultados = new JButton(accionExportarResultados);
		botonExportarResultados.getActionMap().put("exportarResultados", accionExportarResultados);
		botonExportarResultados.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionExportarResultados.getValue(Action.ACCELERATOR_KEY), "exportarResultados");
		botonExportarResultados.setPreferredSize(new Dimension(190, 30));
		auxPanel = new JPanel();
		auxPanel.add(botonExportarResultados);
		inferiorPanel.add(auxPanel);

		imagenF = new ImageIcon(getClass().getResource("/imagenes/regresar.png"));
		imagenE = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionSalir = new AbstractAction("Regresar", new ImageIcon(imagenE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionBtnSalir();
			}
		};
		accionSalir.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
		accionSalir.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		accionSalir.putValue(Action.SHORT_DESCRIPTION, "Regresa a la ventana Capturar resultados");
		botonSalir = new JButton(accionSalir);
		botonSalir.getActionMap().put("regresar", accionSalir);
		botonSalir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionSalir.getValue(Action.ACCELERATOR_KEY), "regresar");
		botonSalir.setPreferredSize(new Dimension(120, 30));
		auxPanel = new JPanel();
		auxPanel.add(botonSalir);
		inferiorPanel.add(auxPanel);

		if (!this.validacionMarcadores) {
			JPanel panelAuxiliar = new JPanel();
			panelAuxiliar.setPreferredSize(new Dimension(77, 0));
			this.add(panelAuxiliar, BorderLayout.EAST);
			panelAuxiliar = new JPanel();
			panelAuxiliar.setPreferredSize(new Dimension(77, 0));
			this.add(panelAuxiliar, BorderLayout.WEST);
		}

		this.add(superiorPanel, BorderLayout.NORTH);
		this.add(centralPanel, BorderLayout.CENTER);
		this.add(inferiorPanel, BorderLayout.SOUTH);
		inicializar();
		inicializarVentana();
	}

	/**
	 * Establece el tamaño, la posición y el no redimensionamiento al ejecutarse la
	 * ventana.
	 */
	private void inicializarVentana() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/icono.png")));
		this.setSize(new Dimension(1200, 600));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Permite obtener los datos para mostrar en un ciclo.
	 */
	private void accionComboSeleccionarCiclo() {
		Torneo torneo = this.ventanaPrincipal.getTorneoActual();
		ArrayList<Ciclo> listaCiclos = torneo.getAlgoritmoTorneo().getCiclos();
		ArrayList<Participante> listaParticipantes = torneo.getListaParticipantes();
		Ciclo ciclo = listaCiclos.get(comboSeleccionarCiclo.getSelectedIndex());
		ArrayList<Encuentro> listaEncuentros = ciclo.getEncuentroParticipantes();
		obtenerResultadosCiclo(listaEncuentros, listaParticipantes);
	}

	/**
	 * Inicializa el combo que contiene los ciclos del torneo para mostrar el
	 * contenido.
	 */
	private void inicializar() {
		Torneo torneo = this.ventanaPrincipal.getTorneoActual();
		ArrayList<Ciclo> listaCiclos = torneo.getAlgoritmoTorneo().getCiclos();
		ArrayList<Participante> listaParticipantes = torneo.getListaParticipantes();
		mostrarDatosGenerales(torneo);

		for (Ciclo c : listaCiclos) {
			this.comboSeleccionarCiclo.addItem(c);
		}
		this.comboSeleccionarCiclo.setSelectedIndex(this.indiceMostrarResultados);
		Ciclo ciclo = comboSeleccionarCiclo.getItemAt(this.indiceMostrarResultados);
		ArrayList<Encuentro> listaEncuentros = ciclo.getEncuentroParticipantes();
		obtenerResultadosCiclo(listaEncuentros, listaParticipantes);
	}

	/**
	 * Obtiene de la base de datos la información correspondiente de cada
	 * participante.
	 * 
	 * @param listaEncuentros    La lista de encuentros del ciclo seleccionado.
	 * @param listaParticipantes La lista de participantes del torneo.
	 */
	private void obtenerResultadosCiclo(ArrayList<Encuentro> listaEncuentros,
			ArrayList<Participante> listaParticipantes) {
		for (int i = 0; i < listaEncuentros.size(); i++) {
			for (Participante p : listaParticipantes) {
				if (p.getNumeroParticipante() == this
						.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteFinal()).getNumeroParticipante()
						&& this.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteFinal())
								.getNombreParticipante().compareToIgnoreCase(this.torneo.getDatosPersonalizacion()
										.getNombreParticipanteSinEncuentro()) == 0) {
					desactivarColumnasTabla(i);
					etiquetaNumeroInicial[i].setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteInicial()));
					etiquetaParticipanteInicial[i]
							.setText(this.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteInicial())
									.getNombreParticipante() + " - "
									+ this.torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro());
				} else if (p.getNumeroParticipante() == this
						.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteInicial()).getNumeroParticipante()
						&& this.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteInicial())
								.getNombreParticipante().compareToIgnoreCase(this.torneo.getDatosPersonalizacion()
										.getNombreParticipanteSinEncuentro()) == 0) {
					desactivarColumnasTabla(i);
					etiquetaNumeroInicial[i].setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteFinal()));
					etiquetaParticipanteInicial[i]
							.setText(this.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteFinal())
									.getNombreParticipante() + " - "
									+ this.torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro());
				} else {
					this.etiquetaNumeroInicial[i]
							.setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteInicial()));
					this.etiquetaNumeroFinal[i]
							.setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteFinal()));
					if (p.getNumeroParticipante() == listaEncuentros.get(i).getIdParticipanteInicial()) {
						this.etiquetaParticipanteInicial[i].setText(p.getNombreParticipante());
					}
					if (p.getNumeroParticipante() == listaEncuentros.get(i).getIdParticipanteFinal()) {
						this.etiquetaParticipanteFinal[i].setText(p.getNombreParticipante());
					}
				}

			}
			if (this.validacionMarcadores) {
				this.campoMarcadorInicial[i]
						.setText(String.valueOf(listaEncuentros.get(i).getMarcadorParticipanteInicial()));
				this.campoMarcadorFinal[i]
						.setText(String.valueOf(listaEncuentros.get(i).getMarcadorParticipanteFinal()));
			}
			this.etiquetaResultado[i].setText(String.valueOf(listaEncuentros.get(i).toString()));
		}
	}

	private void desactivarColumnasTabla(int i) {
		this.etiquetaParticipanteFinal[i].setVisible(false);
		this.etiquetaNumeroFinal[i].setVisible(false);
		this.etiquetaResultado[i].setVisible(false);
		if (this.validacionMarcadores) {
			this.campoMarcadorInicial[i].setVisible(false);
			this.campoMarcadorFinal[i].setVisible(false);
		}
	}

	/**
	 * Obtiene al participante registrado en el torneo.
	 * 
	 * @param numeroParticipante Identificador unico del participante.
	 * @return Retorna al participante.
	 */
	private Participante obtenerParticipante(int numeroParticipante) {
		for (Participante p : participantes ) {
			if (p.getNumeroParticipante() == numeroParticipante) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Permite obtener los datos generales del torneo y mostrarlos en la parte
	 * superior de la pantalla en su respectivo componente.
	 * 
	 * @param torneo Información contenida del torneo.
	 */
	private void mostrarDatosGenerales(Torneo torneo) {
		DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);

		this.campoTorneo.setText(torneo.getNombreTorneo());
		this.campoOrganizador.setText(torneo.getNombreOrganizador());
		this.campoFechaInicio.setText(f.format(torneo.getFechaInicioTorneo()));
		this.campoFechaFin.setText(f.format(torneo.getFechaFinalTorneo()));
	}

	/**
	 * Permite exportar en un archivo CSV los resultados que contiene un ciclo.
	 */
	private void accionBotonExportarResultados() {
		JFileChooser dialogo = new JFileChooser();
		File archivo = null;
		dialogo.setDialogTitle("Guardar como");
		FileFilter filtro1 = new FileNameExtensionFilter("Archivo CSV", "csv", "CSV");
		dialogo.setFileFilter(filtro1);

		dialogo.setAcceptAllFileFilterUsed(false);
		dialogo.setCurrentDirectory(new File(System.getProperty("user.dir")));
		dialogo.setSelectedFile(null);
		dialogo.setMultiSelectionEnabled(false);
		int valor = dialogo.showSaveDialog(null);
		if (valor == JFileChooser.APPROVE_OPTION) {
			try {
				archivo = dialogo.getSelectedFile();
				this.torneo.getAlgoritmoTorneo().generarReporteParciales(archivo,
						comboSeleccionarCiclo.getSelectedIndex());
				JOptionPane.showMessageDialog(
						null, "El archivo se ha guardado exitosamente.", "Generar reporte de " + this.torneo
								.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MAYUSCULA_SINGULAR),
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ExcepcionUtilerias e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Permite cerrar la ventana.
	 */
	private void accionBtnSalir() {
		dispose();
	}
}
