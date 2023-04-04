package sigestor.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import sigestor.bd.BaseDatosEncuentro;
import sigestor.dominio.*;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosCiclo;
import sigestor.excepcion.ExcepcionBaseDatosEncuentro;
import sigestor.excepcion.ExcepcionUtilerias;

/**
 * Sirve para mostrar la interfaz del dialogo de un ciclo.
 * <p>
 * Las características de la clase <code>DialogoCiclo</code> son:
 * <ul>
 * <li><code>campoNombreTorneo</code>Se especificara el nombre que tendra el
 * torneo almacenado con aterioridad.</li>
 * <li><code>campoNombreOrganizador</code>Se especificara el nombre que tendra
 * el organizador del torneo almacenado con anterioridad.</li>
 * <li><code>campoFechaInicio</code>Se especificara la fecha en que se inciara
 * el torneo almacenado con anterioridad.</li>
 * <li><code>campoFechaFinalizacion</code>Se especificara la fecha en que el
 * torneo termina almacenada con anterioridad.</li>
 * <li><code>comboSeleccionarCiclo</code>Se selecciona el ciclo de la lista
 * almacenada.</li>
 * <li><code>tablaListaParticipantes</code>Lista donde estaran los datos de los
 * participantes previamente almacenados.</li>
 * <li><code>botonExportar</code>Se exportara a un archivo CSV los datos del
 * ciclo.</li>
 * <li><code>botonSalir</code>Se saldra del dialogo.</li>
 * <li><code>botonHacer</code>Genera los ciclos del torneo.</li>
 * <li><code>panelEncabezadoTablas</code>Contendra los encabezados de los datos
 * personalizados del torneo.</li>
 * <li><code>ventanaPrincipal</code>Se hara llamado a la ventana principal del
 * torneo.</li>
 * <li><code>modelo</code>Modelo por defecto creado para el uso del
 * dialogo.</li>
 * <li><code>torneo</code>Variable de tipo torneo.</li>
 * <li><code>listaParticipantes</code>lista de tipo participante que tendra los
 * datos de los jugadores previamente almacenados.</li>
 * <li><code>listaCiclo</code>Lista de tipo ciclo que tendra los datos del ciclo
 * seleccionado.</li>
 * <li><code>etiquetaNumeroIncial</code>Para mostrar el número que representa al
 * participante inicial.</li>
 * <li><code>etiquetaParticipanteInicial</code>Para asignar el marcador que
 * obtendrá el participante inicial.</li>
 * <li><code>etiquetaNumeroFinal</code>Para mostrar el número que representa al
 * participante no inicial.</li>
 * <li><code>etiquetaParticipanteFinal</code>Para asignar el marcador que
 * obtendrá el participante no inicial.</li>
 * <li><code>fechaEncuentro</code> Para capturar la fecha de los
 * encuentros.</li>
 * <li><code>numeroPartidas</code>Para asignar el numero de las partidas
 * realizadas.</li>
 * <li><code>cicloSeleccionado</code>Para seleccionar el ciclo almacenado del
 * torneo.</li>
 * <li><code>ventanaPrincipal</code> Para obtener datos almacenados en
 * <code>VentanaPrincipal</code>.</li>
 * <li><code>serialVersionUID</code> Para el número de versión de la clase.</li>
 * </ul>
 * 
 * @version 28/03/2023
 * 
 * @author Jonathan Eduardo Ibarra Martinez
 * @author Hernan Sesai Lopez Aragon
 * @author Francisco Samuel Reyes Cortes
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 * 
 */
public class DialogoCiclo extends JDialog {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Campo para el nombre del torneo.
	 */
	private JTextField campoNombreTorneo;

	/**
	 * Campo para el nombre del organizador.
	 */
	private JTextField campoNombreOrganizador;

	/**
	 * Campo para la fecha de inicio.
	 */
	private JTextField campoFechaInicio;

	/**
	 * Campo para la fecha de fin.
	 */
	private JTextField campoFechaFinalizacion;

	/**
	 * Lista desplegable para seleccionar el ciclo.
	 */
	private JComboBox<Ciclo> comboSeleccionarCiclo;

	/**
	 * Tabla para mostrar los participantes.
	 */
	private JTable tablaListaParticipantes;

	/**
	 * Botón <code>ExportarRonda</code>.
	 * 
	 * @see #accionBotonExportarRonda()
	 */
	private JButton botonExportar;

	/**
	 * Botón <code>Guardar</code>.
	 * 
	 * @see #accionBtnGuardar()
	 */
	private JButton botonGuardar;

	/**
	 * Botón <code>Salir</code>.
	 * 
	 * @see #accionBotonSalir()
	 */
	private JButton botonSalir;

	/**
	 * Botón <code>Hacer</code>.
	 * 
	 * @see #accionBotonHacer()
	 */
	private JButton botonHacer;

	/**
	 * Panel donde mostrará los encabezados de la tabla.
	 */
	private JPanel panelEncabezadoTablas = new JPanel();

	/**
	 * 
	 */
	private DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Sirve para acceder a los datos que tenga almacenado el torneo.
	 */
	private Torneo torneo;

	/**
	 * Sirve para obtener la lista de participantes del torneo.
	 */
	private ArrayList<Participante> listaParticipantes;

	/**
	 * Sirve para obtener la lista de ciclos del torneo.
	 */
	private ArrayList<Ciclo> listaCiclos;

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
	 * 
	 */
	private JLabel[] encararVS;

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
	 * Etiquetas para mostrar el ciclo actual.
	 */
	private JLabel etiquetaCicloActual;

	/**
	 * Etiquetas para mostrar el ciclo actual.
	 */
	private JLabel etiquetaCicloActual2;

	/**
	 * Arreglo para capturar las fechas de los encuentros.
	 * 
	 */
	private JDateChooser[] fechaEncuentro;

	/**
	 * Almacenar la cantidad de número de pareos de un ciclo.
	 */
	private int numeroPartidas;

	/**
	 * Almacenar el ciclo seleccionado en el diálogo.
	 */
	private int cicloSeleccionado = 0;

	/**
	 * Referencia a la clase <code>VentanaPrincipal</code>
	 */
	private VentanaPrincipal ventanaPrincipal;

	/**
	 * Devuelve el numero de paritdas.
	 * 
	 * @return numeroPartidas.
	 */
	public int getNumeroPartidas() {
		return numeroPartidas;
	}

	/**
	 * Devuelve el ciclo seleccionado.
	 * 
	 * @return cicloSelecconado.
	 */
	public int getCicloSeleccionado() {
		return cicloSeleccionado;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>cicloSeleccionado</code>.
	 * 
	 * @param cicloSeleccionado Recibe un numero entero del cicloo
	 *                          <code>cicloSeleeccionado</code>.
	 */
	public void setCicloSeleccionado(int cicloSeleccionado) {
		this.cicloSeleccionado = cicloSeleccionado;
	}

	/**
	 * Constructor en el que se inicializa el diálogo.
	 * 
	 * @param principal Recibe un objeto de tipo ventanaPrincipal el cual contiene
	 *                  el objeto de tipo torneo.
	 */
	public DialogoCiclo(VentanaPrincipal principal) {
		super(principal,
				principal.getTorneoActual().getDatosPersonalizacion().getNombreCiclo(Personalizacion.MAYUSCULA_PLURAL));
		this.setModal(true);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				accionSalir();
			}
		});
		this.ventanaPrincipal = principal;
		torneo = principal.getTorneoActual();

		// FIXME
		if (!torneo.getTipoTorneo().equals("Eliminación directa")) {
			numeroPartidas = this.torneo.getAlgoritmoTorneo().getCiclos().get(this.torneo.getCicloActual() - 1)
					.getEncuentroParticipantes().size();
			float sumar = 0;
			for (Participante l : this.torneo.getListaParticipantes())
				sumar += l.getPuntajeAcumuladoParticipante();
			if (sumar > 0) {
				if (!Participante.isPuntajeAcumulado())
					Participante.setPuntajeAcumulado(true);
				Collections.sort(this.torneo.getListaParticipantes());
			}
			listaParticipantes = torneo.getListaParticipantes();
			listaCiclos = torneo.getAlgoritmoTorneo().getCiclos();
		}

		etiquetaNumeroInicial = new JLabel[numeroPartidas];
		etiquetaParticipanteInicial = new JLabel[numeroPartidas];
		encararVS = new JLabel[numeroPartidas];
		etiquetaNumeroFinal = new JLabel[numeroPartidas];
		etiquetaParticipanteFinal = new JLabel[numeroPartidas];
		fechaEncuentro = new JDateChooser[numeroPartidas];

		JPanel panelNorte = new JPanel(null);
		panelNorte.setBounds(0, 0, this.getWidth(), this.getHeight() - 80);
		JPanel panelSuperiorCentral = new JPanel();

		ImageIcon imagenF;
		Image imagenE;

		JLabel etiquetaTipoTorneo = new JLabel("Sistema " + this.torneo.getTipoTorneo());
		Font fuente = etiquetaTipoTorneo.getFont();
		etiquetaTipoTorneo.setFont(new Font(fuente.getFontName(), fuente.getStyle(), 30));
		etiquetaTipoTorneo.setBounds(50, 15, 500, 50);

		JPanel panelCentralCentral = new JPanel();
		etiquetaCicloActual = new JLabel();
		etiquetaCicloActual.setText("Torneo pendiente");

		if (torneo.getTipoTorneo().equals("Eliminación directa")) {

			if (this.torneo.getAlgoritmoTorneo().verificarResultadosCompletos()) { // FIXME
				etiquetaCicloActual.setText("Torneo finalizado");
			} else {
				etiquetaCicloActual
						.setText(torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MAYUSCULA_SINGULAR)
								+ " actual: " + torneo.getCicloActual());
			}

		}

		etiquetaCicloActual.setFont(new Font(fuente.getFontName(), fuente.getStyle(), 20));
		etiquetaCicloActual.setBounds(50, 60, 500, 50);
		panelNorte.add(etiquetaTipoTorneo);
		panelNorte.add(etiquetaCicloActual);

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

		JLabel etiquetaTorneo = new JLabel("Torneo: ");
		etiquetaTorneo.setBounds(465, 20, 50, 25);
		panelNorte.add(etiquetaTorneo);
		campoNombreTorneo = new JTextField(torneo.getNombreTorneo());
		campoNombreTorneo.setEditable(false);
		campoNombreTorneo.setBounds(545, 20, 230, 25);
		panelNorte.add(campoNombreTorneo);

		JLabel etiquetaOrganizador = new JLabel("Organizador: ");
		etiquetaOrganizador.setBounds(465, 60, 100, 25);
		panelNorte.add(etiquetaOrganizador);
		campoNombreOrganizador = new JTextField(torneo.getNombreOrganizador());
		campoNombreOrganizador.setEditable(false);
		campoNombreOrganizador.setBounds(545, 60, 230, 25);
		panelNorte.add(campoNombreOrganizador);

		JLabel etiquetaFechaInicio = new JLabel("Fecha de inicio: ");
		etiquetaFechaInicio.setBounds(810, 20, 100, 25);
		panelNorte.add(etiquetaFechaInicio);

		DateFormat formato = DateFormat.getDateInstance(DateFormat.FULL);

		campoFechaInicio = new JTextField(formato.format(torneo.getFechaInicioTorneo()));
		campoFechaInicio.setEditable(false);
		campoFechaInicio.setBounds(915, 20, 220, 25);
		panelNorte.add(campoFechaInicio);

		JLabel etiquetaFechaFin = new JLabel("Fecha de fin: ");
		etiquetaFechaFin.setBounds(810, 60, 100, 25);
		panelNorte.add(etiquetaFechaFin);
		campoFechaFinalizacion = new JTextField(formato.format(torneo.getFechaFinalTorneo()));
		campoFechaFinalizacion.setEditable(false);
		campoFechaFinalizacion.setBounds(915, 60, 220, 25);
		panelNorte.add(campoFechaFinalizacion);

		tablaListaParticipantes = new JTable();

		JLabel etiquetaListaParticipantes = new JLabel(
				"Lista de " + torneo.getDatosPersonalizacion().getNombreParticipante(Personalizacion.MINUSCULA_PLURAL));
		etiquetaListaParticipantes.setBounds(50, 110, 200, 25);
		panelNorte.add(etiquetaListaParticipantes);
		JScrollPane scrollTablaParticipantes = new JScrollPane(tablaListaParticipantes);
		scrollTablaParticipantes.setBorder(BorderFactory.createEmptyBorder());
		scrollTablaParticipantes.setBounds(50, 135, 568, 150);
		panelNorte.add(scrollTablaParticipantes);

		JLabel etiquetaPereoParticipantes = new JLabel(
				"Parear " + torneo.getDatosPersonalizacion().getNombreParticipante(Personalizacion.MINUSCULA_PLURAL));
		etiquetaPereoParticipantes.setBounds(930, 145, 200, 25);
		panelNorte.add(etiquetaPereoParticipantes);

		imagenF = new ImageIcon(getClass().getResource("/imagenes/hacer.png"));
		imagenE = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionHacer = new AbstractAction("Hacer", new ImageIcon(imagenE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionHacer();
			}
		};
		accionHacer.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_H);
		accionHacer.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
		accionHacer.putValue(Action.SHORT_DESCRIPTION, "Realiza el siguiente pareo");
		botonHacer = new JButton(accionHacer);
		botonHacer.getActionMap().put("hacer", accionHacer);
		botonHacer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionHacer.getValue(Action.ACCELERATOR_KEY), "hacer");
		botonHacer.setBounds(930, 175, 100, 30);
		if (getCicloSeleccionado() == torneo.getCicloActual() - 1 && torneo.getTipoTorneo().contains("Round Robin")) {
			botonHacer.setEnabled(false);
		} else {
			botonHacer.setEnabled(true);
		}
		panelNorte.add(botonHacer);

		JLabel etiquetaElegirCiclo = new JLabel(
				"Elegir " + torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MAYUSCULA_SINGULAR));
		etiquetaElegirCiclo.setBounds(930, 215, 300, 25);
		panelNorte.add(etiquetaElegirCiclo);
		comboSeleccionarCiclo = new JComboBox<Ciclo>();

		comboSeleccionarCiclo.setToolTipText(
				"Seleccione un " + torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR)
						+ " para generar el reporte de pareos en pantalla");
		comboSeleccionarCiclo.setEnabled(true);
		comboSeleccionarCiclo.setBounds(930, 250, 120, 25);
		panelNorte.add(comboSeleccionarCiclo);
		etiquetaElegirCiclo.setDisplayedMnemonic(KeyEvent.VK_I);
		etiquetaElegirCiclo.setLabelFor(comboSeleccionarCiclo);
		etiquetaCicloActual2 = new JLabel();

		Action accionSeleccionarCiclo = new AbstractAction("Seleccionarciclo", null) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionSeleccionarCiclo();

			}
		};
		comboSeleccionarCiclo.setAction(accionSeleccionarCiclo);
		comboSeleccionarCiclo.getActionMap().put("seleccionarciclo", accionSeleccionarCiclo);
		comboSeleccionarCiclo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionSeleccionarCiclo.getValue(Action.ACCELERATOR_KEY), "seleccionarciclo");
		accionSeleccionarCiclo.putValue(Action.SHORT_DESCRIPTION,
				"Seleccione un " + torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR)
						+ " para generar el reporte de pareos en pantalla");

		etiquetaCicloActual2.setBounds(550, 290, 200, 25);
		panelNorte.add(etiquetaCicloActual2);
		crearEncabezadoTablaEncuentros();
		panelCentralCentral.add(crearTablaEncuentros());

		JScrollPane scrollTablaPareos = new JScrollPane(panelCentralCentral);
		scrollTablaPareos.setBounds(50, 345, 1080, 140);
		panelNorte.add(scrollTablaPareos);
		panelSuperiorCentral.add(panelEncabezadoTablas);
		panelSuperiorCentral.setBounds(40, 300, 1080, 50);
		panelNorte.add(panelSuperiorCentral);

		imagenF = new ImageIcon(getClass().getResource("/imagenes/exportarRonda.png"));
		imagenE = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionExportarRonda = new AbstractAction(
				"Exportar " + torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MAYUSCULA_SINGULAR),
				new ImageIcon(imagenE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionExportarRonda();
			}
		};
		accionExportarRonda.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		accionExportarRonda.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		accionExportarRonda.putValue(Action.SHORT_DESCRIPTION, "Genera un archivo CSV con los pareos del "
				+ torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR));

		JPanel panelSur = new JPanel();
		panelSur.setLayout(new FlowLayout());

		botonExportar = new JButton(accionExportarRonda);
		botonExportar.getActionMap().put("exportarRonda", accionExportarRonda);
		botonExportar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionExportarRonda.getValue(Action.ACCELERATOR_KEY), "exportarRonda");
		panelSur.add(botonExportar);

		imagenF = new ImageIcon(getClass().getResource("/imagenes/guardar.png"));
		imagenE = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionGuardar = new AbstractAction("Guardar fechas", new ImageIcon(imagenE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionGuardarFechas();
			}
		};
		accionGuardar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_G);
		accionGuardar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		accionGuardar.putValue(Action.SHORT_DESCRIPTION, "Guarda las fechas de los encuentros");
		botonGuardar = new JButton(accionGuardar);
		botonGuardar.getActionMap().put("guardar", accionGuardar);
		botonGuardar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionGuardar.getValue(Action.ACCELERATOR_KEY), "guardar");
		botonGuardar.setPreferredSize(new Dimension(170, 30));
		panelSur.add(botonGuardar);

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

		panelSur.add(botonSalir);

		this.add(panelSur, BorderLayout.SOUTH);
		this.add(panelNorte);

		// FIXME
		if (!torneo.getTipoTorneo().equals("Eliminación directa")) {
			activarDesactivarBotonHacer();
		}
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/icono.png")));
		this.setSize(1200, 600);
		this.setResizable(false);

		// FIXME

		inicializarDialogo();

		this.setLocationRelativeTo(principal);
		this.setVisible(true);
	}

	/**
	 * Inicializa la ventana del dialogo, llena la tabla de participantes y
	 * actualiza el combo para elegir un ciclo.
	 */
	private void inicializarDialogo() {

		llenarTablaParticipantes();
		// FIXME
		if (!torneo.getTipoTorneo().equals("Eliminación directa")) {
			this.actualizarCombo();
		}
	}

	/**
	 * Actualiza el combo de la lista desplegable eliminado y rellenándolo
	 * nuevamente.
	 */
	private void actualizarCombo() {
		comboSeleccionarCiclo.removeAllItems();
		for (Ciclo ciclos : listaCiclos) {
			comboSeleccionarCiclo.addItem(ciclos);
		}
		this.comboSeleccionarCiclo.setSelectedIndex(torneo.getCicloActual() - 1);
	}

	/**
	 * El contenido de la tabla se rellena con los datos de los participantes del
	 * torneo.
	 */
	private void llenarTablaParticipantes() {
		tablaListaParticipantes.setEnabled(false);
		modelo.addColumn("Núm.");
		modelo.addColumn("Nombre");
		if (torneo.getDatosPersonalizacion().isExistenciaMarcador()) {
			modelo.addColumn(torneo.getDatosPersonalizacion().getNombreMarcador(Personalizacion.MAYUSCULA_SINGULAR)
					+ " a favor");
			modelo.addColumn(torneo.getDatosPersonalizacion().getNombreMarcador(Personalizacion.MAYUSCULA_SINGULAR)
					+ " en contra");
		}
		modelo.addColumn("Puntaje");
		// FIXME
		if (!torneo.getTipoTorneo().equals("Eliminación directa")) {
			for (int i = 0; i < torneo.getListaParticipantes().size(); i++) {
				if (!this.listaParticipantes.get(i).getNombreParticipante().equals(this.torneo.getAlgoritmoTorneo()
						.getTorneo().getDatosPersonalizacion().getNombreParticipanteSinEncuentro())) {

					if (torneo.getDatosPersonalizacion().isExistenciaMarcador()) {
						Object[] fila = { this.listaParticipantes.get(i).getNumeroParticipante(),
								this.listaParticipantes.get(i).toString(),
								this.listaParticipantes.get(i).getMarcadorFavor(),
								this.listaParticipantes.get(i).getMarcadorContra(),
								this.listaParticipantes.get(i).getPuntajeAcumuladoParticipante() };
						modelo.addRow(fila);
					} else {
						Object[] fila = { this.listaParticipantes.get(i).getNumeroParticipante(),
								this.listaParticipantes.get(i).toString(),
								this.listaParticipantes.get(i).getPuntajeAcumuladoParticipante() };
						modelo.addRow(fila);
					}
				}
			}
		}
		tablaListaParticipantes.setModel(modelo);
	}

	/**
	 * Obtiene los participantes de la lista de participantes.
	 * 
	 * @param numeroParticipante Cantidad de participantes del torneo.
	 * @return Devuelve el nombre del participante solicitado.
	 */
	private Participante obtenerParticipante(int numeroParticipante) {
		for (Participante p : this.torneo.getListaParticipantes()) {
			if (p.getNumeroParticipante() == numeroParticipante) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Inhabilita columnas cuando existe un participante en descanso
	 * 
	 * @param i posición de arreglo.
	 */
	private void desactivarColumnasTabla(int i) {
		etiquetaNumeroFinal[i].setText("");
		etiquetaNumeroFinal[i].setVisible(false);
		etiquetaParticipanteFinal[i].setText("");
		etiquetaParticipanteFinal[i].setVisible(false);
		encararVS[i].setVisible(false);
	}

	/**
	 * Permite ponerle nombres a las columnas de la tabla inferior de la ventana.
	 */
	private void crearEncabezadoTablaEncuentros() {
		JLabel etiquetasTitulosTabla;
		JPanel panelAuxiliar = new JPanel();

		Border bordeEncabezado = BorderFactory.createLineBorder(Color.BLACK);
		panelEncabezadoTablas.add(panelAuxiliar);
		panelEncabezadoTablas.setLayout(new FlowLayout());

		etiquetasTitulosTabla = new JLabel("Núm.");
		panelAuxiliar = new JPanel();
		panelAuxiliar.setPreferredSize(new Dimension(50, 30));
		panelAuxiliar.add(etiquetasTitulosTabla);
		panelAuxiliar.setBorder(BorderFactory.createCompoundBorder(bordeEncabezado, bordeEncabezado));
		panelEncabezadoTablas.add(panelAuxiliar);

		etiquetasTitulosTabla = new JLabel(torneo.getDatosPersonalizacion().getNombreParticipanteInicial());
		panelAuxiliar = new JPanel();
		panelAuxiliar.setPreferredSize(new Dimension(320, 30));
		panelAuxiliar.add(etiquetasTitulosTabla);
		panelAuxiliar.setBorder(BorderFactory.createCompoundBorder(bordeEncabezado, bordeEncabezado));
		panelEncabezadoTablas.add(panelAuxiliar);

		etiquetasTitulosTabla = new JLabel("VS");
		panelAuxiliar = new JPanel();
		panelAuxiliar.setPreferredSize(new Dimension(70, 30));
		panelAuxiliar.add(etiquetasTitulosTabla);
		panelAuxiliar.setBorder(BorderFactory.createCompoundBorder(bordeEncabezado, bordeEncabezado));
		panelEncabezadoTablas.add(panelAuxiliar);

		etiquetasTitulosTabla = new JLabel("Núm.");
		panelAuxiliar = new JPanel();
		panelAuxiliar.setPreferredSize(new Dimension(50, 30));
		panelAuxiliar.add(etiquetasTitulosTabla);
		panelAuxiliar.setBorder(BorderFactory.createCompoundBorder(bordeEncabezado, bordeEncabezado));
		panelEncabezadoTablas.add(panelAuxiliar);

		etiquetasTitulosTabla = new JLabel(torneo.getDatosPersonalizacion().getNombreParticipanteFinal());
		panelAuxiliar = new JPanel();
		panelAuxiliar.setPreferredSize(new Dimension(320, 30));
		panelAuxiliar.add(etiquetasTitulosTabla);
		panelAuxiliar.setBorder(BorderFactory.createCompoundBorder(bordeEncabezado, bordeEncabezado));
		panelEncabezadoTablas.add(panelAuxiliar);

		etiquetasTitulosTabla = new JLabel("Fecha del encuentro");
		panelAuxiliar = new JPanel();
		panelAuxiliar.setPreferredSize(new Dimension(230, 30));
		panelAuxiliar.add(etiquetasTitulosTabla);
		panelAuxiliar.setBorder(BorderFactory.createCompoundBorder(bordeEncabezado, bordeEncabezado));
		panelEncabezadoTablas.add(panelAuxiliar);

	}

	/**
	 * Rellena la tabla con los encuentros del ciclo.
	 * 
	 * @return Regresa el panel con la información del ciclo.
	 */
	private JPanel crearTablaEncuentros() {
		JPanel panelAux;
		JPanel panelFilasTabla[] = new JPanel[numeroPartidas];
		JPanel panelTablaEncuentro = new JPanel();
		panelTablaEncuentro.setLayout(new GridLayout(numeroPartidas, 1, 1, 2));
		panelTablaEncuentro.setBackground(new Color(100, 105, 105));

		for (int i = 0; i < numeroPartidas; i++) {

			panelFilasTabla[i] = new JPanel();

			etiquetaNumeroInicial[i] = new JLabel();
			panelAux = new JPanel();
			panelAux.add(etiquetaNumeroInicial[i]);
			panelAux.setPreferredSize(new Dimension(50, 30));
			panelFilasTabla[i].add(panelAux);

			etiquetaParticipanteInicial[i] = new JLabel();
			panelAux = new JPanel();
			panelAux.add(etiquetaParticipanteInicial[i]);
			panelAux.setPreferredSize(new Dimension(310, 30));
			panelFilasTabla[i].add(panelAux);

			encararVS[i] = new JLabel("vs");
			panelAux = new JPanel();
			panelAux.add(encararVS[i]);
			panelAux.setPreferredSize(new Dimension(70, 30));
			panelFilasTabla[i].add(panelAux);

			etiquetaNumeroFinal[i] = new JLabel();
			panelAux = new JPanel();
			panelAux.add(etiquetaNumeroFinal[i]);
			panelAux.setPreferredSize(new Dimension(50, 30));
			panelFilasTabla[i].add(panelAux);

			etiquetaParticipanteFinal[i] = new JLabel();
			panelAux = new JPanel();
			panelAux.add(etiquetaParticipanteFinal[i]);
			panelAux.setPreferredSize(new Dimension(300, 30));
			panelFilasTabla[i].add(panelAux);

			fechaEncuentro[i] = new JDateChooser(torneo.getFechaInicioTorneo());
			fechaEncuentro[i].setPreferredSize(new Dimension(120, 25));
			panelAux = new JPanel();
			panelAux.add(fechaEncuentro[i]);
			panelAux.setPreferredSize(new Dimension(232, 30));
			panelFilasTabla[i].add(panelAux);

			panelTablaEncuentro.add(panelFilasTabla[i]);
		}
		return panelTablaEncuentro;
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
	 * Encargado de guardar las fechas de los encuentros del ciclo seleccionado.
	 */
	private void accionGuardarFechas() {
		Ciclo ciclo = listaCiclos.get(this.getCicloSeleccionado() - 1);
		ArrayList<Encuentro> encuentro = ciclo.getEncuentroParticipantes();
		BaseDatosEncuentro bde = new BaseDatosEncuentro(torneo.getNombreArchivo());
		for (int i = 0; i < encuentro.size(); i++) {
			encuentro.get(i).setFechaEncuentro(fechaEncuentro[i].getDate());
			try {
				bde.actualizarFechaEncuentro(encuentro.get(i), ciclo);
			} catch (ExcepcionBaseDatos | ExcepcionBaseDatosEncuentro e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar fechas", JOptionPane.ERROR_MESSAGE);
			}
		}
		JOptionPane.showMessageDialog(null, "Las fechas de los encuentros se han guardado con éxito.", "Guardar fechas",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Permite mediante un combo seleccionar el ciclo del torneo .
	 * 
	 */
	private void accionSeleccionarCiclo() {
		// FIXME
		if (!torneo.getTipoTorneo().equals("Eliminación directa")) {
			setCicloSeleccionado(comboSeleccionarCiclo.getSelectedIndex() + 1);
			if (getCicloSeleccionado() == torneo.getCicloActual()) {
				etiquetaCicloActual2
						.setText(torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MAYUSCULA_SINGULAR)
								+ " " + torneo.getCicloActual() + " - Actual");
			} else {
				etiquetaCicloActual2
						.setText(torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MAYUSCULA_SINGULAR)
								+ " " + getCicloSeleccionado());
			}
			if (comboSeleccionarCiclo.getSelectedIndex() >= 0 && torneo.getCicloActual() > 0) {
				Ciclo ciclo = listaCiclos.get(this.getCicloSeleccionado() - 1);
				ArrayList<Encuentro> listaEncuentros = ciclo.getEncuentroParticipantes();
				for (int i = 0; i < listaEncuentros.size(); i++) {
					for (Participante p : torneo.getListaParticipantes()) {
						if (p.getNumeroParticipante() == this
								.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteFinal())
								.getNumeroParticipante()
								&& this.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteFinal())
										.getNombreParticipante().compareToIgnoreCase(this.torneo
												.getDatosPersonalizacion().getNombreParticipanteSinEncuentro()) == 0) {
							etiquetaNumeroInicial[i]
									.setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteInicial()));
							etiquetaParticipanteInicial[i].setText(this
									.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteInicial())
									.getNombreParticipante() + " - "
									+ this.torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro());
							this.desactivarColumnasTabla(i);

						} else if (p.getNumeroParticipante() == this
								.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteInicial())
								.getNumeroParticipante()
								&& this.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteInicial())
										.getNombreParticipante().compareToIgnoreCase(this.torneo
												.getDatosPersonalizacion().getNombreParticipanteSinEncuentro()) == 0) {
							etiquetaNumeroInicial[i]
									.setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteFinal()));
							etiquetaParticipanteInicial[i].setText(this
									.obtenerParticipante(listaEncuentros.get(i).getIdParticipanteFinal())
									.getNombreParticipante() + " - "
									+ this.torneo.getDatosPersonalizacion().getNombreParticipanteSinEncuentro());
							this.desactivarColumnasTabla(i);
						} else {
							etiquetaNumeroInicial[i]
									.setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteInicial()));
							etiquetaNumeroFinal[i]
									.setText(String.valueOf(listaEncuentros.get(i).getIdParticipanteFinal()));
							if (p.getNumeroParticipante() == listaEncuentros.get(i).getIdParticipanteInicial()) {
								etiquetaParticipanteInicial[i].setText(p.getNombreParticipante());
							}
							if (p.getNumeroParticipante() == listaEncuentros.get(i).getIdParticipanteFinal()) {
								etiquetaParticipanteFinal[i].setText(p.getNombreParticipante());
							}
						}

					}
					fechaEncuentro[i].setDate(listaEncuentros.get(i).getFechaEncuentro());
				}

			}
		}
	}

	/**
	 * Permite crear un nuevo ciclo mientras se haya terminado de capturar los
	 * resultados del ciclo anterior. Solo estará habilitado hasta que llegue a la
	 * misma cantidad de ciclos establecido para el torneo.
	 */
	private void accionHacer() {
		// FIXME
		if (!torneo.getTipoTorneo().equals("Eliminación directa")) {
			
			
			if (this.torneo.getTipoTorneo().contains("Suizo")) {
				TorneoSuizo ts = new TorneoSuizo(torneo);
				if (ts.verificarEncuentros()) {
					
					ts.desempatarParticipantes();
					
					try {
						ts.realizarEncuentros();
					} catch (ExcepcionBaseDatos e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Ciclos", JOptionPane.ERROR_MESSAGE);
					} catch (ExcepcionBaseDatosEncuentro e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Ciclos", JOptionPane.ERROR_MESSAGE);
					} catch (ExcepcionBaseDatosCiclo e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Ciclos", JOptionPane.ERROR_MESSAGE);
					}
					JOptionPane.showMessageDialog(null,
							"El(la) " + torneo.getDatosPersonalizacion().getNombreCiclo(
									Personalizacion.MAYUSCULA_SINGULAR) + " se ha realizado exitosamente.",
							"Parear", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "El sistema no ha podido realizar "
							+ torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR)
							+ " \n porque no ha finalizado "
							+ torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MINUSCULA_SINGULAR) + "."
							+ " \n Por favor capture todos los resultados.",
							"Encarar" + torneo.getDatosPersonalizacion()
									.getNombreParticipante(Personalizacion.MINUSCULA_PLURAL).substring(8),
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				if (this.torneo.getCicloActual() < this.torneo.getAlgoritmoTorneo().getNumeroCiclos()) {
					this.torneo.setCicloActual(torneo.getCicloActual() + 1);
					this.torneo.getAlgoritmoTorneo().actualizarCiclo(this.torneo.getNombreArchivo());
					this.setCicloSeleccionado(this.torneo.getCicloActual() + 1);
					JOptionPane.showMessageDialog(null,
							"El(la) " + torneo.getDatosPersonalizacion().getNombreCiclo(
									Personalizacion.MAYUSCULA_SINGULAR) + " se ha realizado exitosamente.",
							"Parear", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			activarDesactivarBotonHacer();
			etiquetaCicloActual
					.setText(torneo.getDatosPersonalizacion().getNombreCiclo(Personalizacion.MAYUSCULA_SINGULAR)
							+ " actual: " + torneo.getCicloActual());

			if (getCicloSeleccionado() == torneo.getCicloActual() - 1
					&& torneo.getTipoTorneo().contains("Round Robin")) {
				// actualizarCombo();
			} else {
				actualizarCombo();
			}
			
			
		}
	}

	/**
	 * Permite cerrar la ventana.
	 */
	private void accionSalir() {
		dispose();
	}

	/**
	 * Permite exportar en un archivo CSV los pareos que contiene un ciclo.
	 */
	private void accionExportarRonda() {
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
				this.torneo.getAlgoritmoTorneo().generarReporteCiclo(archivo, comboSeleccionarCiclo.getSelectedIndex());
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
	 * Desactiva el botón <code>Hacer</code> cuando se encuentre en el último ciclo.
	 */
	private void activarDesactivarBotonHacer() {
		if (torneo.getCicloActual() == torneo.getAlgoritmoTorneo().getNumeroCiclos()) {
			botonHacer.setEnabled(false);
		}
	}

}