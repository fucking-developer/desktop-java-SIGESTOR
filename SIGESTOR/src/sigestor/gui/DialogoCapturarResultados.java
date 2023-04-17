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
import java.text.DateFormat;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;

import sigestor.bd.BaseDatosCiclo;
import sigestor.bd.BaseDatosEncuentro;
import sigestor.bd.BaseDatosParticipante;
import sigestor.bd.BaseDatosTorneo;

import sigestor.dominio.Ciclo;
import sigestor.dominio.Encuentro;
import sigestor.dominio.Participante;
import sigestor.dominio.Personalizacion;
import sigestor.dominio.Torneo;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosCiclo;
import sigestor.excepcion.ExcepcionBaseDatosEncuentro;
import sigestor.excepcion.ExcepcionBaseDatosParticipante;
import sigestor.excepcion.ExcepcionBaseDatosTorneo;
import sigestor.excepcion.ExcepcionCapturarResultados;

/**
 * Sirve para mostrar la interfaz donde permitirá capturar los resultados de
 * cada pareo.
 * <p>
 * Las características de la clase <code>DialogoCapturarResultados</code> son:
 * <ul>
 * <li><code>comboSeleccionarCiclo</code> Para almacenar todos los ciclos que se
 * creen.</li>
 * <li><code>campoTorneo</code> Para asignar el nombre del torneo.</li>
 * <li><code>campoOrganizador</code> Para asignar el nombre del organizador del
 * torneo.</li>
 * <li><code>campoFechaInicio</code> Para asignar la fecha de inicio del
 * torneo.</li>
 * <li><code>campoFechaFin</code> Para asignar la fecha de fin del torneo</li>
 * <li><code>campoMarcadorInicial</code> Para asignar el marcador del
 * participante inicial.</li>
 * <li><code>opcionGanadorInicial</code> Para indicar si el ganador es el
 * participante inicial.</li>
 * <li><code>opcionGanadorFinal</code> Para indicar si el ganador es el
 * participante no inicial.</li>
 * <li><code>opcionEmpate</code> Para indicar que el pareo ha sido un
 * empate.</li>
 * <li><code>grupoResultado</code> Para agrupar las opciones de ganador y
 * empate.</li>
 * <li><code>campoMarcadorFinal</code> Para asignar el marcador del participante
 * no inicial.</li>
 * <li><code>botonGuardar</code> Para guardar los resultados capturados.</li>
 * <li><code>botonMostrarReporte</code> Para mostrar los resultados del
 * ciclo.</li>
 * <li><code>botonModificarResultados</code> Para modificar los resultados del
 * ciclo.</li>
 * <li><code>botonCancelarCiclo</code> Para eliminar el ciclo y toda la
 * información capturada del mismo.</li>
 * <li><code>botonSalir</code> Para salir de la ventana Capturar
 * resultados.</li>
 * <li><code>etiquetaNumeroInicial</code> Para mostrar el número que representa
 * al participante inicial.</li>
 * <li><code>etiquetaParticipanteInicial</code> Para asignar el marcador que
 * obtendrá el participante inicial.</li>
 * <li><code>etiquetaNumeroFinal</code> Para mostrar el número que representa al
 * participante no inicial.</li>
 * <li><code>etiquetaParticipanteFinal</code> Para asignar el marcador que
 * obtendrá el participante no inicial.</li>
 * <li><code>etiquetaTituloTabla</code> Para asignar el titulo que llevara la
 * tabla.</li>
 * <li><code>etiquetaCicloActual</code> Para mostrar el ciclo actual.</li>
 * <li><code>etiquetaFechaEncuentros</code> Para asignar la fecha a los
 * encuentros</li>
 * <li><code>torneo</code> Para almacenar toda la información contenida del
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
 * <li><code>estadoModificar</code> Para verificar si se hará una modificación a
 * los resultados de un ciclo.</li>
 * <li><code>serialVersionUID</code> Para el número de versión de la clase.</li>
 * <li><code>formatoFechasEncuentro</code> Para establecer el formato de las
 * flechas de los encuentros.</li>
 * </ul>
 * 
 * @version 28/03/2023
 * 
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 * @author Eder Euclides Dionisio Diaz
 * @author Hernan Sesai Lopez Aragon
 * @author Erik Vasquez Policarpo
 */

public class DialogoCapturarResultados extends JDialog {
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
	 * Sirve para obtener la lista de ciclos del torneo.
	 */
	private ArrayList<Ciclo> listaCiclos;

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
	 * Arreglo de radios para participantes iniciales ganadores.
	 */
	private JRadioButton[] opcionGanadorInicial;

	/**
	 * Arreglo de radios para participantes no iniciales ganadores.
	 */
	private JRadioButton[] opcionGanadorFinal;

	/**
	 * Arreglo de radios para empates en pareos.
	 */
	private JRadioButton[] opcionEmpate;

	/**
	 * Arreglo de botones para agrupar los radios de cada pareo.
	 */
	private ButtonGroup[] grupoResultado;

	/**
	 * Arreglo de campos para los marcadores de participantes no iniciales
	 */
	private JTextField[] campoMarcadorFinal;

	// Inferior-----------------------------------------------------
	/**
	 * Botón <code>Guardar</code>.
	 * 
	 * @see #accionGuardar()
	 */
	private JButton botonGuardar;

	/**
	 * Botón <code>Mostrar Reporte</code>.
	 * 
	 * @see #accionMostrarReporte()
	 */
	private JButton botonMostrarReporte;

	/**
	 * Botón <code>Modificar resultados</code>.
	 * 
	 * @see #accionModificarResultados()
	 */
	private JButton botonModificarResultados;

	/**
	 * Botón <code>Cancelar ciclo</code>.
	 * 
	 * @see #accionCancelarCiclo() El botón será personalizable en ciclo, por que
	 *      dependerá del nombre asignado.
	 */
	private JButton botonCancelarCiclo;

	/**
	 * Botón <code>Salir</code>.
	 * 
	 * @see #accionSalir()
	 */
	private JButton botonSalir;

	/**
	 * Arreglo de etiquetas para los números que identificarán a los participantes
	 * iniciales.
	 */
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
	 * Etiquetas para mostrar el titulo de la tabla.
	 */
	private JLabel etiquetaTituloTabla;

	/**
	 * Etiqueta para mostrar el ciclo actual.
	 */
	private JLabel etiquetaCicloActual;

	/**
	 * Arreglo de campos para las fechas de los encuentros.
	 */
	private JTextField[] etiquetaFechaEncuentros;

	/**
	 * Sirve para acceder a los datos que tenga almacenado el torneo.
	 */
	private Torneo torneo;

	/**
	 * Sirve para obtener la lista de participantes del torneo.
	 */
	private ArrayList<Participante> participantes;

	/**
	 * Para obtener los datos personalizados del torneo.
	 */
	private Personalizacion personalizacion;

	/**
	 * Referencia a la clase <code>VentanaPrincipal</code>
	 */
	private VentanaPrincipal ventanaPrincipal;

	/**
	 * Almacenar la cantidad de numero de pareos de un ciclo.
	 */
	private int numeroPartidas;

	/**
	 * Variable temporal que indicará si se activaron los marcadores o no.
	 */
	private boolean validacionMarcadores;

	/**
	 * Variable que llevará el control si se modifican los resultados del ciclo.
	 */
	private boolean estadoModificar = false;

	/**
	 * Variable para establecer el formato de las fechas de los encuentros.
	 */
	private DateFormat formatoFechasEncuentro = DateFormat.getDateInstance(DateFormat.LONG);

	/**
	 * Constructor en el que se inicializa el diálogo. .
	 * 
	 * @param principal
	 *            Recibe un objeto de tipo ventanaPrincipal el cual contiene el
	 *            objeto de tipo torneo.
	 */
	public DialogoCapturarResultados(VentanaPrincipal principal) {
		super(principal, "Capturar resultados");
		this.setModal(true);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				accionSalir();
			}
		});

		this.ventanaPrincipal = principal;
		torneo = principal.getTorneoActual();
		participantes = torneo.getListaParticipantes();
		personalizacion = torneo.getDatosPersonalizacion();
		validacionMarcadores = ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().isExistenciaMarcador();
		listaCiclos = torneo.getAlgoritmoTorneo().getCiclos();
		numeroPartidas = this.ventanaPrincipal.getTorneoActual().getAlgoritmoTorneo().getCiclos()
				.get(this.ventanaPrincipal.getTorneoActual().getCicloActual() - 1).getEncuentroParticipantes().size();

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
		JPanel panelResultados = new JPanel();

		JPanel panelFilasTabla[] = new JPanel[numeroPartidas + auxImpar];

		JPanel inferiorPanel = new JPanel();
		inferiorPanel.setLayout(new GridLayout(1, 5));

		JPanel auxPanel;

		Border borde = BorderFactory.createLineBorder(Color.BLACK);

		/*
		 * Panel superior-------------------------------------------------------------
		 * ---------------------------------------------------------------------
		 * -----------------
		 */
		panelAuxSup.setLayout(new FlowLayout(-1));

		JLabel etiquetaTipoTorneo = new JLabel("Sistema " + this.ventanaPrincipal.getTorneoActual().getTipoTorneo());
		etiquetaTipoTorneo.setFont(
				new Font(etiquetaTipoTorneo.getFont().getFontName(), etiquetaTipoTorneo.getFont().getStyle(), 30));
		auxPanel = new JPanel();
		auxPanel.add(etiquetaTipoTorneo);
		auxPanel.setPreferredSize(new Dimension(450, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelAuxSup.add(auxPanel);

		JLabel etiquetaTorneo = new JLabel("Torneo:");
		etiquetaTorneo.setPreferredSize(new Dimension(90, 25));
		auxPanel = new JPanel();
		auxPanel.add(etiquetaTorneo);
		auxPanel.setPreferredSize(new Dimension(82, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		campoTorneo = new JTextField();
		campoTorneo.setPreferredSize(new Dimension(230, 25));
		campoTorneo.setEditable(false);
		auxPanel = new JPanel();
		auxPanel.add(campoTorneo);
		auxPanel.setPreferredSize(new Dimension(300, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		JLabel etiquetaFechaInicio = new JLabel("Fecha de inicio:");
		etiquetaFechaInicio.setPreferredSize(new Dimension(90, 25));
		auxPanel = new JPanel();
		auxPanel.add(etiquetaFechaInicio);
		auxPanel.setPreferredSize(new Dimension(95, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		campoFechaInicio = new JTextField();
		campoFechaInicio.setPreferredSize(new Dimension(180, 25));
		campoFechaInicio.setEditable(false);
		auxPanel = new JPanel();
		auxPanel.add(campoFechaInicio);
		auxPanel.setPreferredSize(new Dimension(202, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		superiorPanel.add(panelAuxSup);
		panelAuxSup = new JPanel();
		panelAuxSup.setLayout(new FlowLayout(-1));

		auxPanel = new JPanel();
		auxPanel.setPreferredSize(new Dimension(70, 50));
		panelAuxSup.add(auxPanel);
		etiquetaCicloActual = new JLabel(
				this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreCiclo(1) + " actual: "
						+ this.ventanaPrincipal.getTorneoActual().getCicloActual());
		etiquetaCicloActual.setPreferredSize(new Dimension(150, 25));
		auxPanel = new JPanel();
		auxPanel.add(etiquetaCicloActual);
		auxPanel.setPreferredSize(new Dimension(377, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);
		Action accionAyuda = new AbstractAction("Ayuda", null) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionAyuda();
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
		auxPanel.setPreferredSize(new Dimension(80, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		campoOrganizador = new JTextField();
		campoOrganizador.setPreferredSize(new Dimension(230, 25));
		campoOrganizador.setEditable(false);
		auxPanel = new JPanel();
		auxPanel.add(campoOrganizador);
		auxPanel.setPreferredSize(new Dimension(300, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		JLabel etiquetaFechaFin = new JLabel("Fecha de fin:");
		etiquetaFechaFin.setPreferredSize(new Dimension(90, 25));
		auxPanel = new JPanel();
		auxPanel.add(etiquetaFechaFin);
		auxPanel.setPreferredSize(new Dimension(95, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		campoFechaFin = new JTextField(); // formato.format(fecha)
		campoFechaFin.setPreferredSize(new Dimension(180, 25));
		campoFechaFin.setEditable(false);
		auxPanel = new JPanel();
		auxPanel.add(campoFechaFin);
		auxPanel.setPreferredSize(new Dimension(202, 50));
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAuxSup.add(auxPanel);

		superiorPanel.add(panelAuxSup);
		/*
		 * Panel central--------------------------------------------------------------
		 * ---------------------------------------------------------------------
		 * ----------------
		 */
		JPanel tituloTabla = new JPanel();
		tituloTabla.setLayout(new GridLayout(2, 1));

		JLabel etiquetaElegirCiclo = new JLabel("Elegir"
				+ torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR).substring(6)
				+ ": ");

		etiquetaTituloTabla = new JLabel();
		auxPanel = new JPanel();
		auxPanel.add(etiquetaElegirCiclo);
		comboSeleccionarCiclo = new JComboBox<>();
		comboSeleccionarCiclo.setPreferredSize(new Dimension(120, 25));

		etiquetaElegirCiclo.setDisplayedMnemonic(KeyEvent.VK_I);
		etiquetaElegirCiclo.setLabelFor(comboSeleccionarCiclo);
		auxPanel.add(comboSeleccionarCiclo);
		Action accionSeleccionarCiclo = new AbstractAction("Seleccionarciclo", null) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionSeleccionarCiclo();

			}
		};
		accionSeleccionarCiclo.putValue(Action.SHORT_DESCRIPTION,
				"Seleccione " + this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreCiclo(3)
						+ " para capturar o modificar los resultados");
		comboSeleccionarCiclo.setAction(accionSeleccionarCiclo);
		comboSeleccionarCiclo.getActionMap().put("seleccionarciclo", accionSeleccionarCiclo);
		comboSeleccionarCiclo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionSeleccionarCiclo.getValue(Action.ACCELERATOR_KEY), "seleccionarciclo");
		auxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		tituloTabla.add(auxPanel);

		auxPanel = new JPanel();
		auxPanel.setLayout(new FlowLayout(1));

		etiquetaTituloTabla.setFont(
				new Font(etiquetaTipoTorneo.getFont().getFontName(), etiquetaTipoTorneo.getFont().getStyle(), 20));
		auxPanel.add(etiquetaTituloTabla);
		tituloTabla.add(auxPanel);
		panelSuperiorCentral.add(tituloTabla);
		/*------------------------------------Num----------------------------------------------------------*/
		JLabel etiquetaTitulosTabla = new JLabel("Núm.");
		auxPanel = new JPanel();
		auxPanel.setPreferredSize(new Dimension(50, altoTitulos));
		auxPanel.add(etiquetaTitulosTabla);
		auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
		panelEncabezados.add(auxPanel);
		/*----------------------------------BLANCAS----------------------------------------------------------*/

		etiquetaTitulosTabla = new JLabel(
				this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipanteInicial());
		auxPanel = new JPanel();
		auxPanel.setPreferredSize(new Dimension(260, altoTitulos));
		auxPanel.add(etiquetaTitulosTabla);
		auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
		panelEncabezados.add(auxPanel);
		/*----------------------------------Marcadores----------------------------------------------------------*/
		if (validacionMarcadores) {
			etiquetaTitulosTabla = new JLabel(
					this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreMarcador(1));
			auxPanel = new JPanel();
			auxPanel.setPreferredSize(new Dimension(70, altoTitulos));
			auxPanel.add(etiquetaTitulosTabla);
			auxPanel.setBorder(BorderFactory.createCompoundBorder(borde, borde));
			panelEncabezados.add(auxPanel);
		}
		/*----------------------------------Resultados----------------------------------------------------------*/
		panelResultados.setLayout(new GridLayout(2, 1));
		etiquetaTitulosTabla = new JLabel("Resultado");
		auxPanel = new JPanel();
		auxPanel.setLayout(new FlowLayout(1));
		auxPanel.add(etiquetaTitulosTabla);
		panelResultados.add(auxPanel);

		auxPanel = new JPanel();
		auxPanel.setLayout(new FlowLayout(1));
		etiquetaTitulosTabla = new JLabel("Ganador");
		auxPanel.add(etiquetaTitulosTabla);
		etiquetaTitulosTabla = new JLabel("Empate");
		auxPanel.add(etiquetaTitulosTabla);
		etiquetaTitulosTabla = new JLabel("Ganador");
		auxPanel.add(etiquetaTitulosTabla);

		panelResultados.add(auxPanel);
		panelResultados.setPreferredSize(new Dimension(200, altoTitulos));
		panelResultados.setBorder(BorderFactory.createCompoundBorder(borde, borde));
		panelEncabezados.add(panelResultados);

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
		if (validacionMarcadores) {
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
		opcionGanadorInicial = new JRadioButton[numeroPartidas + auxImpar];
		opcionGanadorFinal = new JRadioButton[numeroPartidas + auxImpar];
		opcionEmpate = new JRadioButton[numeroPartidas + auxImpar];
		grupoResultado = new ButtonGroup[numeroPartidas + auxImpar];
		etiquetaNumeroFinal = new JLabel[numeroPartidas + auxImpar];
		etiquetaParticipanteFinal = new JLabel[numeroPartidas + auxImpar];
		campoMarcadorFinal = new JTextField[numeroPartidas + auxImpar];
		etiquetaFechaEncuentros = new JTextField[numeroPartidas + auxImpar];

		for (int i = 0; i < numeroPartidas + auxImpar; i++) {
			panelFilasTabla[i] = new JPanel();
			panelFilasTabla[i].setLayout(new FlowLayout(1));

			etiquetaNumeroInicial[i] = new JLabel();
			auxPanel = new JPanel();
			auxPanel.add(etiquetaNumeroInicial[i]);
			auxPanel.setPreferredSize(new Dimension(50, 30));
			panelFilasTabla[i].add(auxPanel);

			etiquetaParticipanteInicial[i] = new JLabel();
			auxPanel = new JPanel();
			auxPanel.add(etiquetaParticipanteInicial[i]);
			auxPanel.setPreferredSize(new Dimension(250, 30));
			panelFilasTabla[i].add(auxPanel);

			if (validacionMarcadores) {
				campoMarcadorInicial[i] = new JTextField();
				campoMarcadorInicial[i].setToolTipText("Escriba el "
						+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreMarcador(2)
						+ " que le corresponde al "
						+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipante(2)
						+ " " + this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
								.getNombreParticipanteInicial());
				campoMarcadorInicial[i].setPreferredSize(new Dimension(60, 30));
				auxPanel = new JPanel();
				auxPanel.add(campoMarcadorInicial[i]);
				auxPanel.setPreferredSize(new Dimension(70, 35));
				panelFilasTabla[i].add(auxPanel);

			}

			opcionGanadorInicial[i] = new JRadioButton("", false);
			opcionGanadorInicial[i].setToolTipText("Seleccione si "
					+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipante(3) + " "
					+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipanteInicial()
					+ " ha sido el Ganador de "
					+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreEncuentro(3));
			opcionEmpate[i] = new JRadioButton("", false);
			opcionEmpate[i].setToolTipText("Seleccione si en "
					+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreEncuentro(3)
					+ " existe un Empate");
			opcionGanadorFinal[i] = new JRadioButton("", false);
			opcionGanadorFinal[i].setToolTipText("Seleccione si "
					+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipante(3) + " "
					+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipanteFinal()
					+ " ha sido el Ganador de "
					+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreEncuentro(3));
			grupoResultado[i] = new ButtonGroup();
			grupoResultado[i].add(opcionGanadorInicial[i]);
			grupoResultado[i].add(opcionEmpate[i]);
			grupoResultado[i].add(opcionGanadorFinal[i]);

			auxPanel = new JPanel();
			auxPanel.add(opcionGanadorInicial[i]);
			auxPanel.add(opcionEmpate[i]);
			auxPanel.add(opcionGanadorFinal[i]);

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

			if (validacionMarcadores) {
				campoMarcadorFinal[i] = new JTextField();
				campoMarcadorFinal[i].setPreferredSize(new Dimension(60, 30));
				campoMarcadorFinal[i].setToolTipText("Escriba el "
						+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreMarcador(2)
						+ " que le corresponde al "
						+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipante(2)
						+ " " + this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
								.getNombreParticipanteFinal());
				auxPanel = new JPanel();
				auxPanel.add(campoMarcadorFinal[i]);
				auxPanel.setPreferredSize(new Dimension(70, 35));
				panelFilasTabla[i].add(auxPanel);

			}
			etiquetaFechaEncuentros[i] = new JTextField(formatoFechasEncuentro.format(new Date()));
			etiquetaFechaEncuentros[i].setPreferredSize(new Dimension(170, 25));
			etiquetaFechaEncuentros[i].setEditable(false);

			auxPanel = new JPanel();
			auxPanel.add(etiquetaFechaEncuentros[i]);
			auxPanel.setPreferredSize(new Dimension(200, 30));
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
			opcionEmpate[numeroPartidas].setVisible(false);
			opcionGanadorFinal[numeroPartidas].setVisible(false);
			opcionGanadorInicial[numeroPartidas].setVisible(false);
			campoMarcadorFinal[numeroPartidas].setVisible(false);
		}

		JScrollPane scrollTabla;
		scrollTabla = new JScrollPane(panelCentralCentral);

		centralPanel.add(panelSuperiorCentral, BorderLayout.NORTH);
		centralPanel.add(scrollTabla, BorderLayout.CENTER);
		/*
		 * Panel inferior-------------------------------------------------------------
		 * ---------------------------------------------------------------------
		 * -----------------
		 */
		ImageIcon imagenF;
		Image imagenE;

		imagenF = new ImageIcon(getClass().getResource("/imagenes/guardar.png"));
		imagenE = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionGuardar = new AbstractAction("Guardar", new ImageIcon(imagenE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionGuardar();
			}
		};
		accionGuardar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_G);
		accionGuardar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		if (validacionMarcadores) {
			accionGuardar.putValue(Action.SHORT_DESCRIPTION,
					"Guarda " + this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreMarcador(4)
							+ " y el resultado de cada "
							+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreEncuentro(2)
							+ ". " + "Deshabilita la edición de los resultados capturados");
		} else {
			accionGuardar.putValue(Action.SHORT_DESCRIPTION,
					"Guarda el resultado de cada "
							+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreEncuentro(2)
							+ ". " + "Deshabilita la edición de los resultados capturados");
		}
		botonGuardar = new JButton(accionGuardar);
		botonGuardar.getActionMap().put("guardar", accionGuardar);
		botonGuardar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionGuardar.getValue(Action.ACCELERATOR_KEY), "guardar");
		botonGuardar.setPreferredSize(new Dimension(120, 30));
		auxPanel = new JPanel();
		auxPanel.add(botonGuardar);
		inferiorPanel.add(auxPanel);

		imagenF = new ImageIcon(getClass().getResource("/imagenes/mostrarReporte.png"));
		imagenE = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionMostrarReporte = new AbstractAction("Mostrar resultados", new ImageIcon(imagenE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionMostrarReporte();
			}
		};
		accionMostrarReporte.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
		accionMostrarReporte.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		accionMostrarReporte.putValue(Action.SHORT_DESCRIPTION, "Muestra en pantalla el avance de "
				+ this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreEncuentro(4));
		botonMostrarReporte = new JButton(accionMostrarReporte);
		botonMostrarReporte.getActionMap().put("mostrarReporte", accionMostrarReporte);
		botonMostrarReporte.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionMostrarReporte.getValue(Action.ACCELERATOR_KEY), "mostrarReporte");
		botonMostrarReporte.setPreferredSize(new Dimension(200, 30));
		auxPanel = new JPanel();
		auxPanel.add(botonMostrarReporte);
		inferiorPanel.add(auxPanel);

		imagenF = new ImageIcon(getClass().getResource("/imagenes/modificarResultados.png"));
		imagenE = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionModificarResultados = new AbstractAction("Modificar resultados", new ImageIcon(imagenE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionModificarResultados();
			}
		};
		accionModificarResultados.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O);
		accionModificarResultados.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		accionModificarResultados.putValue(Action.SHORT_DESCRIPTION,
				"Permite modificar los resultados que hayan sido capturados anteriormente");
		botonModificarResultados = new JButton(accionModificarResultados);
		botonModificarResultados.getActionMap().put("modificarResultados", accionModificarResultados);
		botonModificarResultados.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionModificarResultados.getValue(Action.ACCELERATOR_KEY), "modificarResultados");
		botonModificarResultados.setPreferredSize(new Dimension(200, 30));
		auxPanel = new JPanel();
		auxPanel.add(botonModificarResultados);
		inferiorPanel.add(auxPanel);

		imagenF = new ImageIcon(getClass().getResource("/imagenes/cancelar.png"));
		imagenE = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionCancelarCiclo = new AbstractAction("Cancelar "
				+ torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR).substring(6),
				new ImageIcon(imagenE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionCancelarCiclo();
			}
		};
		accionCancelarCiclo.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
		accionCancelarCiclo.putValue(Action.SHORT_DESCRIPTION,
				"Elimina los resultados de " + " actual al igual que " + " y regresa al " + " anterior");
		botonCancelarCiclo = new JButton(accionCancelarCiclo);
		botonCancelarCiclo.getActionMap().put("cancelarCiclo", accionCancelarCiclo);
		botonCancelarCiclo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionCancelarCiclo.getValue(Action.ACCELERATOR_KEY), "cancelarCiclo");
		botonCancelarCiclo.setPreferredSize(new Dimension(200, 30));
		auxPanel = new JPanel();
		botonCancelarCiclo.setEnabled(true);
		auxPanel.add(botonCancelarCiclo);

		inferiorPanel.add(auxPanel);

		imagenF = new ImageIcon(getClass().getResource("/imagenes/botonSalir.png"));
		imagenE = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionSalir = new AbstractAction("Salir", new ImageIcon(imagenE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionSalir();
			}
		};
		accionSalir.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
		accionSalir.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		accionSalir.putValue(Action.SHORT_DESCRIPTION, "Cierra la ventana y regresa al menú principal");
		botonSalir = new JButton(accionSalir);
		botonSalir.getActionMap().put("salir", accionSalir);
		botonSalir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionSalir.getValue(Action.ACCELERATOR_KEY), "salir");
		botonSalir.setPreferredSize(new Dimension(120, 30));
		auxPanel = new JPanel();
		auxPanel.add(botonSalir);
		inferiorPanel.add(auxPanel);
		if (!validacionMarcadores) {
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
		deshabilitarTabla();
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

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
		this.setLocationRelativeTo(ventanaPrincipal);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Inicializa el combo con los ciclos que contiene el torneo.
	 */
	private void inicializar() {
		mostrarDatosGenerales(torneo);
		for (Ciclo c : listaCiclos) {
			this.comboSeleccionarCiclo.addItem(c);
		}
		this.comboSeleccionarCiclo.setSelectedIndex(torneo.getCicloActual() - 1);
	}

	/**
	 * Permite habilitar los botones de <code>Modificar resultados</code> y
	 * <code>Cancelar ciclo</code> únicamente si se encuentra en el ciclo actual
	 */
	private void habilitarBotones() {
		if (this.torneo.getTipoTorneo().contains("Suizo")) {
			if (comboSeleccionarCiclo.getSelectedIndex() != ventanaPrincipal.getTorneoActual().getCicloActual() - 1) {
				botonModificarResultados.setEnabled(false);
				botonCancelarCiclo.setEnabled(false);
			} else {
				botonModificarResultados.setEnabled(true);
				botonCancelarCiclo.setEnabled(true);
			}
			if (torneo.getCicloActual() == 1) {
				botonCancelarCiclo.setEnabled(false);
			}
		} else {
			if (comboSeleccionarCiclo.getSelectedIndex() == torneo.getCicloActual() - 1) {
				botonCancelarCiclo.setEnabled(true);
			} else {
				botonCancelarCiclo.setEnabled(false);
			}
		}
	}

	/**
	 * Obtiene toda la información almacena en un ciclo.
	 * 
	 * @param listaEncuentros
	 *            Recibe la lista de los encuentros de un ciclo.
	 */
	private void obtenerResultadosCiclo(ArrayList<Encuentro> listaEncuentros) {
		for (int i = 0; i < listaEncuentros.size(); i++) {
			for (Participante p : torneo.getListaParticipantes()) {
				if (p.getNumeroParticipante() == this
						.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteFinal()).getNumeroParticipante()
						&& this.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteFinal())
								.getNombreParticipante().compareToIgnoreCase(this.torneo.getDatosPersonalizacion()
										.getNombreParticipanteSinEncuentro()) == 0) {
					etiquetaNumeroInicial[i].setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteInicial()));
					etiquetaParticipanteInicial[i]
							.setText(this.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteInicial())
									.getNombreParticipante() + " - "
									+ this.torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro());
					this.desactivarColumnasTabla(i);
				} else if (p.getNumeroParticipante() == this
						.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteInicial()).getNumeroParticipante()
						&& this.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteInicial())
								.getNombreParticipante().compareToIgnoreCase(this.torneo.getDatosPersonalizacion()
										.getNombreParticipanteSinEncuentro()) == 0) {
					etiquetaNumeroInicial[i].setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteFinal()));
					etiquetaParticipanteInicial[i]
							.setText(this.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteFinal())
									.getNombreParticipante() + " - "
									+ this.torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro());
					this.desactivarColumnasTabla(i);
				} else {
					etiquetaNumeroInicial[i].setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteInicial()));
					if (p.getNumeroParticipante() == listaEncuentros.get(i).getIdParticipanteInicial()) {
						etiquetaParticipanteInicial[i].setText(p.getNombreParticipante());
					}
					if (p.getNumeroParticipante() == listaEncuentros.get(i).getIdParticipanteFinal()) {
						etiquetaParticipanteFinal[i].setText(p.getNombreParticipante());
					}

					etiquetaNumeroFinal[i].setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteFinal()));
					if (validacionMarcadores) {
						campoMarcadorInicial[i]
								.setText(String.valueOf(listaEncuentros.get(i).getMarcadorParticipanteInicial()));
						campoMarcadorFinal[i]
								.setText(String.valueOf(listaEncuentros.get(i).getMarcadorParticipanteFinal()));
					}

					if (listaEncuentros.get(i).getResultadoEncuentro() == Encuentro.GANADOR_INICIAL) {
						this.opcionGanadorInicial[i].setSelected(true);
					} else if (listaEncuentros.get(i).getResultadoEncuentro() == Encuentro.GANADOR_FINAL) {
						this.opcionGanadorFinal[i].setSelected(true);
					} else if (listaEncuentros.get(i).getResultadoEncuentro() == Encuentro.EMPATE) {
						this.opcionEmpate[i].setSelected(true);
					} else if (listaEncuentros.get(i).getResultadoEncuentro() == Encuentro.SIN_JUGAR) {
						grupoResultado[i].clearSelection();
					} else if (listaEncuentros.get(i).getResultadoEncuentro() == Encuentro.DESCANSO) {
						this.opcionGanadorInicial[i].setSelected(true);
					}
				}
			}
			etiquetaFechaEncuentros[i]
					.setText(formatoFechasEncuentro.format(listaEncuentros.get(i).getFechaEncuentro()));
		}
	}

	/**
	 * Desactiva las columnas de la tabla.
	 * 
	 * @param i
	 *            Posicion de la fila de la tabla.
	 */
	private void desactivarColumnasTabla(int i) {
		etiquetaNumeroFinal[i].setText("");
		etiquetaNumeroFinal[i].setVisible(false);
		etiquetaParticipanteFinal[i].setText("");
		etiquetaParticipanteFinal[i].setVisible(false);
		if (validacionMarcadores) {
			campoMarcadorInicial[i].setText("");
			campoMarcadorInicial[i].setVisible(false);
			campoMarcadorFinal[i].setText("");
			campoMarcadorFinal[i].setVisible(false);
		}
		grupoResultado[i].clearSelection();
		opcionGanadorInicial[i].setVisible(false);
		opcionGanadorFinal[i].setVisible(false);
		opcionEmpate[i].setVisible(false);
	}

	/**
	 * Obtiene al participante registrado en el torneo.
	 * 
	 * @param numeroParticipante
	 *            Identificador unico del participante.
	 * @return Retorna al participante.
	 */
	private Participante obtenerParticipante(int numeroParticipante) {

		for (Participante p : participantes) {
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
	 * @param torneo
	 *            Recibe el objeto <code>Torneo</code> con la información del
	 *            torneo.
	 */
	private void mostrarDatosGenerales(Torneo torneo) {
		DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
		this.campoTorneo.setText(torneo.getNombreTorneo());
		this.campoOrganizador.setText(torneo.getNombreOrganizador());
		this.campoFechaInicio.setText(f.format(torneo.getFechaInicioTorneo()));
		this.campoFechaFin.setText(f.format(torneo.getFechaFinalTorneo()));
	}

	/**
	 * Permite mantener desabilitados los campos de marcadores y resultado de la
	 * ventana <code>Capturar resultados</code> al presionar el botón
	 * <code>Guardar</code>.
	 */
	private void deshabilitarTabla() {
		for (int i = 0; i < numeroPartidas; i++) {
			if (validacionMarcadores) {
				campoMarcadorInicial[i].setEditable(false);
				campoMarcadorFinal[i].setEditable(false);
			}
			opcionGanadorInicial[i].setEnabled(false);
			opcionGanadorFinal[i].setEnabled(false);
			opcionEmpate[i].setEnabled(false);
		}
	}

	/**
	 * Habilita los campos de marcadores y radios de la ventana
	 * <code>Capturar resultados</code> al presionar el botón
	 * <code>Modificar resultados</code>.
	 */
	private void habilitarTabla() {
		for (int i = 0; i < numeroPartidas; i++) {
			if (this.validacionMarcadores) {
				campoMarcadorInicial[i].setEditable(true);
				campoMarcadorFinal[i].setEditable(true);
			}
			opcionGanadorInicial[i].setEnabled(true);
			opcionGanadorFinal[i].setEnabled(true);
			opcionEmpate[i].setEnabled(true);
		}
	}

	/**
	 * Muestra la ventana <code>Reporte de resultados</code>.
	 */
	private void accionMostrarReporte() {
		new DialogoMostrarResultados(ventanaPrincipal, comboSeleccionarCiclo.getSelectedIndex());
	}

	/**
	 * Permite mostrar mensajes al usuario al presionar el botón
	 * <code>Modificar resultados</code>.
	 */
	private void accionModificarResultados() {
		Object[] opciones = { "Sí", "No" };
		int respuesta = JOptionPane.showOptionDialog(null, "¿Está seguro(a) de que desea modificar los resultados?",
				"Modificar resultados", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,
				opciones[1]);
		if (respuesta == JOptionPane.YES_OPTION) {
			estadoModificar = true;
			habilitarTabla();
		}
	}

	/**
	 * Permite mostrar un archivo PDF al usuario con información relevante de como
	 * utilizar el sistema.
	 * 
	 */
	private void accionAyuda() {
		ventanaPrincipal.accionCargarManual();
	}

	/**
	 * Obtiene toda la información de un ciclo al ser seleccionado en el combo.
	 */
	private void accionSeleccionarCiclo() {
		ArrayList<Ciclo> listaCiclos = torneo.getAlgoritmoTorneo().getCiclos();
		Ciclo ciclo = listaCiclos.get(comboSeleccionarCiclo.getSelectedIndex());
		ArrayList<Encuentro> listaEncuentros = ciclo.getEncuentroParticipantes();
		obtenerResultadosCiclo(listaEncuentros);
		deshabilitarTabla();
		etiquetaTituloTabla.setText("Tabla de resultados de "
				+ ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreCiclo(3) + " "
				+ (comboSeleccionarCiclo.getSelectedIndex() + 1));
		etiquetaCicloActual
				.setText(torneo.getDatosPersonalizacion().getNombreCiclo(1) + " actual: " + torneo.getCicloActual());

		habilitarBotones();
	}

	/**
	 * Permite mostrar mensajes al usuario al presionar el botón
	 * <code>Cancelar ciclo</code> advirtiendo lo que sucederá al decidir cancelar
	 * el ciclo del torneo.
	 */
	private void accionCancelarCiclo() {
		Object[] opciones = { "Sí", "No" };
		int respuesta1 = JOptionPane.showOptionDialog(null,
				"¿Está seguro de cancelar "
						+ torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR) + "?",
				"Cancelar" + torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR)
						.substring(6),
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
		if (respuesta1 == JOptionPane.YES_OPTION) {
			int respuesta2 = JOptionPane.showOptionDialog(null,
					"Si se cancela se perderán todos los resultados \n incluyendo "
							+ torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR) + "."
							+ "\n ¿Está realmente seguro de cancelar "
							+ torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR) + "?",
					"Cancelar" + torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR)
							.substring(6),
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);
			if (respuesta2 == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null,
						"" + "El(la)"
								+ torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR)
										.substring(6)
								+ " se ha cancelado.",
						"Cancelar" + torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR)
								.substring(6),
						JOptionPane.INFORMATION_MESSAGE);
				eliminarCiclo();
			}
		}

		etiquetaTituloTabla.setText("Tabla de resultados de "
				+ ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreCiclo(3) + " "
				+ (comboSeleccionarCiclo.getSelectedIndex() + 1));
		etiquetaCicloActual
				.setText(torneo.getDatosPersonalizacion().getNombreCiclo(1) + " actual: " + torneo.getCicloActual());
		habilitarBotones();
	}

	/**
	 * Permite salir de la ventana con el botón <code>Salir</code>.
	 */
	private void accionSalir() {
		if (estadoModificar) {
			String[] valores = { "Sí", "Guardar y salir", "Cancelar" };
			int opcion = JOptionPane.showOptionDialog(this,
					"Los resultados no han sido guardados.\nToda la información ingresada se perderá."
							+ "\n ¿Está seguro que desea salir?",
					"Capturar resultados", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, valores,
					valores[1]);
			if (opcion == 0) {
				dispose();
			} else if (opcion == 1) {
				accionGuardar();
				dispose();
			}
		} else {
			dispose();
		}
	}

	/**
	 * Guarda todos los resultados de los encuentros capturados de un ciclo y
	 * actualiza los resultados de cada participante.
	 */
	private void accionGuardar() {
		estadoModificar = false;
		deshabilitarTabla();

		actualizarResultadoEncuentros();
		actualizarParticipantes();
		JOptionPane.showMessageDialog(null, "Los resultados se han guardado exitosamente.", "Capturar resultados",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Elimina el ciclo y los encuentros que están en él, tambien actualiza el
	 * marcador y el puntaje acumulado de los participantes.
	 */
	private void eliminarCiclo() {
		BaseDatosEncuentro bde = new BaseDatosEncuentro(ventanaPrincipal.getTorneoActual().getNombreArchivo());
		BaseDatosCiclo bdc = new BaseDatosCiclo(ventanaPrincipal.getTorneoActual().getNombreArchivo());
		ArrayList<Ciclo> ciclos = torneo.getAlgoritmoTorneo().getCiclos();
		ArrayList<Encuentro> encuentros = ciclos.get(torneo.getCicloActual() - 1).getEncuentroParticipantes();
		for (Encuentro e : encuentros) {
			sumarRestarMarcadorParticipante(e, (-1) * e.getMarcadorParticipanteInicial(),
					(-1) * e.getMarcadorParticipanteFinal());
			sumarRestarPuntajeAcumuladoParticipante(e, (-1) * personalizacion.getPuntajeGanar(),
					(-1) * personalizacion.getPuntajePerder(), (-1) * personalizacion.getPuntajeEmpatar());
			if (this.torneo.getTipoTorneo().contains("Suizo")) {
				try {
					bde.eliminarEncuentro(e, ciclos.get(torneo.getCicloActual() - 1));
				} catch (ExcepcionBaseDatos e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Eliminar ciclo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionBaseDatosEncuentro e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Eliminar encuentros",
							JOptionPane.ERROR_MESSAGE);

				}
			} else {
				try {
					e.setMarcadorParticipanteFinal("0");
					e.setMarcadorParticipanteInicial("0");
					e.setResultadoEncuentro(99);
					bde.actualizarEncuentro(e, ciclos.get(torneo.getCicloActual() - 1));
				} catch (ExcepcionBaseDatos | ExcepcionBaseDatosEncuentro | ExcepcionCapturarResultados e1) {

				}
				comboSeleccionarCiclo.setSelectedIndex(torneo.getCicloActual() - 1); // FIXME

			}
		}
		actualizarParticipantes();
		if (this.torneo.getTipoTorneo().contains("Suizo")) {
			try {
				bdc.eliminarCiclo(ciclos.get(torneo.getCicloActual() - 1));
			} catch (ExcepcionBaseDatos e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Eliminar ciclo", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionBaseDatosCiclo e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Eliminar ciclo", JOptionPane.ERROR_MESSAGE);
			}
			ciclos.remove(torneo.getCicloActual() - 1);
			this.comboSeleccionarCiclo.removeItemAt(torneo.getCicloActual() - 1);
			torneo.setCicloActual(torneo.getCicloActual() - 1);
			torneo.getAlgoritmoTorneo().setCiclos(ciclos);
		} else {
			if (torneo.getCicloActual() != 1) {
				this.comboSeleccionarCiclo.removeItemAt(torneo.getCicloActual() - 1);
				torneo.setCicloActual(torneo.getCicloActual() - 1);
				torneo.getAlgoritmoTorneo().setCiclos(ciclos);
			}
		}
		try {
			BaseDatosTorneo bdt = new BaseDatosTorneo(ventanaPrincipal.getTorneoActual().getNombreArchivo());
			bdt.actualizarCicloActual(torneo);
		} catch (ExcepcionBaseDatos e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Eliminar ciclo", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionBaseDatosTorneo e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Eliminar ciclo", JOptionPane.ERROR_MESSAGE);
		}

		BaseDatosParticipante bdp = new BaseDatosParticipante(this.torneo.getNombreArchivo());

		for (Participante p : participantes) {
			if (p.getLugarParticipante() > 0) {
				p.setLugarParticipante(0);
				try {
					bdp.actualizarLugarParticipante(p, torneo);
				} catch (ExcepcionBaseDatos | ExcepcionBaseDatosParticipante e1) {

				}
			}
		}

	}

	/**
	 * Actualiza los resultados de los participantes en
	 * <code>BaseDatosParticipante</code>.
	 */
	private void actualizarParticipantes() {

		BaseDatosParticipante bdp = new BaseDatosParticipante(ventanaPrincipal.getTorneoActual().getNombreArchivo());
		Ciclo ciclo = torneo.getAlgoritmoTorneo().getCiclos().get(torneo.getCicloActual() - 1);
		Collections.sort(this.participantes);
		for (Participante p : participantes) {
			try {
				if (p.getNombreParticipante()
						.compareToIgnoreCase(personalizacion.getNombreParticipanteSinEncuentro()) == 0) {
					break;
				}
				bdp.actualizarResultadoParticipante(p, ciclo);
			} catch (ExcepcionBaseDatos e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Capturar resultados", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionBaseDatosParticipante e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Capturar resultados", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Actualiza los resultados de los encuentros en la base de datos.
	 * 
	 */
	private void actualizarResultadoEncuentros() {
		BaseDatosEncuentro bde = new BaseDatosEncuentro(ventanaPrincipal.getTorneoActual().getNombreArchivo());

		Ciclo ciclo = null;
		if (this.torneo.getTipoTorneo().contains("Suizo")) {
			ciclo = comboSeleccionarCiclo.getItemAt(torneo.getCicloActual() - 1);
		} else if (this.torneo.getTipoTorneo().contains("Eliminación directa")) {
			ciclo = comboSeleccionarCiclo.getItemAt(torneo.getCicloActual() - 1);
		} else {
			ciclo = comboSeleccionarCiclo.getItemAt(this.comboSeleccionarCiclo.getSelectedIndex());
		}
		ArrayList<Encuentro> encuentros = ciclo.getEncuentroParticipantes();
		for (int i = 0; i < encuentros.size(); i++) {
			if (obtenerParticipante(encuentros.get(i).getIdParticipanteFinal()).getNombreParticipante()
					.compareToIgnoreCase(personalizacion.getNombreParticipanteSinEncuentro()) == 0) {
				if (encuentros.get(i).getResultadoEncuentro() == Encuentro.SIN_JUGAR) {// FIXME
					encuentros.get(i).setResultadoEncuentro(Encuentro.DESCANSO);// FIXME
					sumarRestarPuntajeAcumuladoParticipante(encuentros.get(i), personalizacion.getPuntajeGanar(),
							personalizacion.getPuntajePerder(), personalizacion.getPuntajeEmpatar());// FIXME
				}

			} else if (obtenerParticipante(encuentros.get(i).getIdParticipanteInicial()).getNombreParticipante()
					.compareToIgnoreCase(personalizacion.getNombreParticipanteSinEncuentro()) == 0) {
				if (encuentros.get(i).getResultadoEncuentro() == Encuentro.SIN_JUGAR) {// FIXME
					encuentros.get(i).setResultadoEncuentro(Encuentro.DESCANSO);// FIXME
					sumarRestarPuntajeAcumuladoParticipante(encuentros.get(i), personalizacion.getPuntajeGanar(),
							personalizacion.getPuntajePerder(), personalizacion.getPuntajeEmpatar());// FIXME
				}
			} else {
				sumarRestarPuntajeAcumuladoParticipante(encuentros.get(i), (-1) * personalizacion.getPuntajeGanar(),
						(-1) * personalizacion.getPuntajePerder(), (-1) * personalizacion.getPuntajeEmpatar());
				if (validacionMarcadores) {
					sumarRestarMarcadorParticipante(encuentros.get(i),
							(-1) * (encuentros.get(i).getMarcadorParticipanteInicial()),
							(-1) * (encuentros.get(i).getMarcadorParticipanteFinal()));
					try {
						encuentros.get(i).setMarcadorParticipanteInicial(campoMarcadorInicial[i].getText());
						encuentros.get(i).setMarcadorParticipanteFinal(campoMarcadorFinal[i].getText());
					} catch (ExcepcionCapturarResultados e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Capturar resultados",
								JOptionPane.ERROR_MESSAGE);
					}
					if (encuentros.get(i).getMarcadorParticipanteInicial() > encuentros.get(i)
							.getMarcadorParticipanteFinal()) {
						opcionGanadorInicial[i].setSelected(true);
						encuentros.get(i).setResultadoEncuentro(Encuentro.GANADOR_INICIAL);
					} else if (encuentros.get(i).getMarcadorParticipanteInicial() < encuentros.get(i)
							.getMarcadorParticipanteFinal()) {
						opcionGanadorFinal[i].setSelected(true);
						encuentros.get(i).setResultadoEncuentro(Encuentro.GANADOR_FINAL);
					} else if (opcionEmpate[i].isSelected()) {
						encuentros.get(i).setResultadoEncuentro(Encuentro.EMPATE);
					} else if (encuentros.get(i).getMarcadorParticipanteInicial() == encuentros.get(i)
							.getMarcadorParticipanteFinal() && !opcionEmpate[i].isSelected()) {
						encuentros.get(i).setResultadoEncuentro(Encuentro.SIN_JUGAR);
					} else if (encuentros.get(i).getResultadoEncuentro() == Encuentro.DESCANSO) {
						encuentros.get(i).setResultadoEncuentro(Encuentro.DESCANSO);
					}
					sumarRestarMarcadorParticipante(encuentros.get(i),
							encuentros.get(i).getMarcadorParticipanteInicial(),
							encuentros.get(i).getMarcadorParticipanteFinal());
				} else {
					if (opcionGanadorInicial[i].isSelected()) {
						encuentros.get(i).setResultadoEncuentro(Encuentro.GANADOR_INICIAL);
					} else if (opcionGanadorFinal[i].isSelected()) {
						encuentros.get(i).setResultadoEncuentro(Encuentro.GANADOR_FINAL);
					} else if (opcionEmpate[i].isSelected()) {
						encuentros.get(i).setResultadoEncuentro(Encuentro.EMPATE);
					} else {
						encuentros.get(i).setResultadoEncuentro(Encuentro.SIN_JUGAR);
					}
				}
				sumarRestarPuntajeAcumuladoParticipante(encuentros.get(i), personalizacion.getPuntajeGanar(),
						personalizacion.getPuntajePerder(), personalizacion.getPuntajeEmpatar());
			}
			try {
				bde.actualizarEncuentro(encuentros.get(i), ciclo);
			} catch (ExcepcionBaseDatos e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Capturar resultados", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionBaseDatosEncuentro e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Capturar resultados", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * Actualiza el marcador a favor y el marcador en contra de los participantes de
	 * un encuentro.
	 * 
	 * @param encuentro
	 *            Recible el objeto <code>Encuentro</code> que contiene los datos un
	 *            encuentro.
	 * @param marcadorParticipanteInicial
	 *            Recibe el marcador del participante inicial.
	 * @param marcadorParticipanteFinal
	 *            Recibe el marcador obtenido del participante final.
	 */
	private void sumarRestarMarcadorParticipante(Encuentro encuentro, int marcadorParticipanteInicial,
			int marcadorParticipanteFinal) {
		if (encuentro.getResultadoEncuentro() != Encuentro.DESCANSO) {
			for (Participante p : participantes) {
				if (encuentro.getIdParticipanteInicial() == p.getNumeroParticipante()) {
					p.acumularMarcadorFavor(marcadorParticipanteInicial);
					p.acumularMarcadorContra(marcadorParticipanteFinal);
				}
				if (encuentro.getIdParticipanteFinal() == p.getNumeroParticipante()) {
					p.acumularMarcadorFavor(marcadorParticipanteFinal);
					p.acumularMarcadorContra(marcadorParticipanteInicial);
				}
			}
		}
	}

	/**
	 * Actualiza el puntaje acumulado del participante .
	 * 
	 * @param encuentro
	 *            Recible el objeto <code>Encuentro</code> que contiene los datos
	 *            del encuentro.
	 * @param puntajeGanar
	 *            Recibe el valor del puntaje ganador.
	 * @param puntajePerder
	 *            Recibe el valor del puntaje perdedor.
	 * @param puntajeEmpatar
	 *            Recibe el valor del puntaje empate.
	 */
	private void sumarRestarPuntajeAcumuladoParticipante(Encuentro encuentro, float puntajeGanar, float puntajePerder,
			float puntajeEmpatar) {
		for (Participante p : participantes) {
			if (encuentro.getIdParticipanteInicial() == p.getNumeroParticipante()) {
				if (encuentro.getResultadoEncuentro() == Encuentro.DESCANSO) {
					p.acumularPuntajeAcumuladoParticipante(puntajeGanar);
					break;
				} else if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_INICIAL) {
					p.acumularPuntajeAcumuladoParticipante(puntajeGanar);
				} else if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_FINAL) {
					p.acumularPuntajeAcumuladoParticipante(puntajePerder);
				} else if (encuentro.getResultadoEncuentro() == Encuentro.EMPATE) {
					p.acumularPuntajeAcumuladoParticipante(puntajeEmpatar);
				} else {
					p.acumularPuntajeAcumuladoParticipante(puntajePerder);
				}
			}
			if (encuentro.getIdParticipanteFinal() == p.getNumeroParticipante()) {
				if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_INICIAL) {
					p.acumularPuntajeAcumuladoParticipante(puntajePerder);
				} else if (encuentro.getResultadoEncuentro() == Encuentro.GANADOR_FINAL) {
					p.acumularPuntajeAcumuladoParticipante(puntajeGanar);
				} else if (encuentro.getResultadoEncuentro() == Encuentro.EMPATE) {
					p.acumularPuntajeAcumuladoParticipante(puntajeEmpatar);
				} else {
					p.acumularPuntajeAcumuladoParticipante(puntajePerder);
				}
			}
		}
	}
}