package sigestor.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
import sigestor.dominio.*;
import sigestor.excepcion.*;

/**
 * Sirve para mostrar la interfaz con las opciones que tendrá SIGESTOR.
 * <p>
 * Las características de la clase <code>VentanaPrincipal</code> son:
 * <ul>
 * <li><code>comboTipoTorneo</code>Para mostrar los dos tipos de torneo</li>
 * <li><code>menuArchivo</code> Para abrir, crear, cerrar o salir del
 * torneo.</li>
 * <li><code>menuOperaciones</code> Para administrar, iniciar o cancelar el
 * torneo.</li>
 * <li><code>menuTorneoSuizo</code> Para consultar, capturar y obtener los
 * resultados finales de tipo de torneo suizo.</li>
 * <li><code>menuTorneoRoundRobin</code> Para consultar, capturar y obtener los
 * resultados finales de tipo de torneo round robin.</li>
 * <li><code>menuTorneoEleminicacionDirecta</code> Para consultar, capturar y
 * obtener los resultados finales de tipo de torneo eliminacion directa.</li>
 * <li><code>menuAyuda</code> Para consultar el manual y obtener información
 * sobre el proyecto.</li>
 * <li><code>submenuAbrirTorneo</code> Para abrir un torneo con extensión
 * .torn.</li>
 * <li><code>submenuCrearTorneo</code> Para crear un nuevo torneo.</li>
 * <li><code>submenuCerrarTorneo</code> Para cerrar el torneo que se esté
 * administrando.</li>
 * <li><code>submenuSalir</code> Para salir de la aplicación.</li>
 * <li><code>submenuAdministrarTorneo</code> Para llevar a cabo toda la
 * configuración del torneo.</li>
 * <li><code>submenuIniciarTorneo</code> Para dar comienzo al torneo
 * configurado.</li>
 * <li><code>submenuCancelarTorneo</code> Para cancelar todo lo realizado en el
 * torneo.</li>
 * <li><code>submenuConsultarCiclosSuizo</code> Para mostrar información sobre
 * los pareos y la lista de los participantes del torneo suizo.</li>
 * <li><code>submenuResultadosSuizo</code> Para capturar los resultados y
 * mostrarlos con el torneo suizo.</li>
 * <li><code>submenuResultadosFinalesSuizo</code> Para mostrar los resultados
 * finales del torneo suizo.</li>
 * <li><code>submenuConsultarCiclosRoundRobin</code> Para mostrar información
 * sobre los pareos y la lista de los participantes del torneo round robin.</li>
 * <li><code>submenuResultadosRoundRobin</code> Para capturar los resultados y
 * mostrarlos con el torneo round robin.</li>
 * <li><code>submenuResultadosFinalesRoundRobin</code> Para mostrar los
 * resultados finales del torneo round robin.</li>
 * 
 * <li><code>submenuConsultarCiclosEliminacionDirecta</code> Para mostrar
 * información sobre los pareos y la lista de los participantes del torneo
 * eliminacion directa.</li>
 * <li><code>submenuResultadosEliminacionDirecta</code> Para capturar los
 * resultados y mostrarlos con el torneo eliminacion directa.</li>
 * <li><code>submenuResultadosFinalesEliminacionDirecta</code> Para mostrar los
 * resultados finales del torneo eliminacion directa.</li>
 * 
 * <li><code>submenuManual</code> Para mostrar un manual sobre SIGESTOR.</li>
 * <li><code>submenuAcercaDe</code> Para mostrar información de los creadores de
 * SIGESTOR.</li>
 * <li><code>submenuBarra</code> Para mostrar el menú de la ventana principal de
 * forma horizontal.</li>
 * <li><code>submenuManual</code> Para mostrar un manual sobre SIGESTOR.</li>
 * <li><code>torneoActual</code> Para almacenar toda la información contenida
 * del torneo elegido.</li>
 * <li><code>serialVersionUID</code> Para el número de versión de la clase.</li>
 * </ul>
 * 
 * @version 04/04/2023
 * 
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * @author Alicia Adriana Clemente Hernandez
 * @author Hernán Sesaí López Aragón
 * @author Erik Vasquez Policarpo
 * @author German Luis Cruz Martínez
 */
public class VentanaPrincipal extends JFrame implements ActionListener {
	/**
	 * lista desplegable para seleccionar el tipo de torneo simple o doble.
	 */
	private JComboBox<String> comboTipoTorneo;

	/**
	 * Menú <code>Archivo</code>
	 */
	private JMenu menuArchivo;

	/**
	 * Menú <code>Operaciones</code>
	 */
	private JMenu menuOperaciones;

	/**
	 * Menú <code>Torneo Suizo</code>
	 */
	private JMenu menuTorneoSuizo;

	/**
	 * Menú <code>Torneo Round Robin</code>
	 */
	private JMenu menuTorneoRoundRobin;

	/**
	 * Menú <code>Torneo Round Robin</code>
	 */
	private JMenu menuTorneoEliminacionDirecta;

	/**
	 * Menú <code>Ayuda</code>
	 */
	private JMenu menuAyuda;

	/**
	 * Opcion <code>Abrir Torneo</code> del menú <code>Archivo</code>.
	 */
	private JMenuItem submenuAbrirTorneo;

	/**
	 * Opcion <code>Crear Torneo</code> del menú <code>Archivo</code>.
	 */
	private JMenuItem submenuCrearTorneo;

	/**
	 * Opcion <code>Cerrar Torneo</code> del menú <code>Archivo</code>.
	 */
	private JMenuItem submenuCerrarTorneo;

	/**
	 * Opcion <code>Salir</code> del menú <code>Archivo</code>.
	 */
	private JMenuItem submenuSalir;

	/**
	 * Opcion <code>Admistrar Torneo</code> del menú <code>Operaciones</code>.
	 */
	private JMenuItem submenuAdministrarTorneo;

	/**
	 * Opcion <code>Iniciar Torneo</code> del menú <code>Operaciones</code>.
	 */
	private JMenuItem submenuIniciarTorneo;

	/**
	 * Opcion <code>Cancelar Torneo</code> del menú <code>Operaciones</code>.
	 */
	private JMenuItem submenuCancelarTorneo;

	/**
	 * Opcion <code>Consultar ciclos</code> del menú <code>Torneo Suizo</code>.
	 */
	private JMenuItem submenuConsultarCiclosSuizo;

	/**
	 * Opcion <code>Capturar resultados</code> del menú <code>Torneo Suizo</code>.
	 */
	private JMenuItem submenuResultadosSuizo;

	/**
	 * Opcion <code>Resultados finales</code> del menú <code>Torneo Suizo</code>.
	 */
	private JMenuItem submenuResultadosFinalesSuizo;

	/**
	 * Opcion <code>Consultar ciclos</code> del menú
	 * <code>Torneo Round Robin</code>.
	 */
	private JMenuItem submenuConsultarCiclosRoundRobin;

	/**
	 * Opcion <code>Capturar resultados</code> del menú
	 * <code>Torneo Round Robin</code>.
	 */
	private JMenuItem submenuResultadosRoundRobin;

	/**
	 * Opcion <code>Resultados finales</code> del menú
	 * <code>Torneo Round Robin</code>.
	 */
	private JMenuItem submenuResultadosFinalesRoundRobin;

	/**
	 * Opcion <code>Consultar ciclos</code> del menú
	 * <code>Torneo Eliminacion Directa</code>.
	 */
	private JMenuItem submenuConsultarCiclosEliminacionDirecta;

	/**
	 * Opcion <code>Capturar resultados</code> del menú
	 * <code>Torneo Eliminacion Directa</code>.
	 */
	private JMenuItem submenuResultadosEliminacionDirecta;

	/**
	 * Opcion <code>Resultados finales</code> del menú
	 * <code>Torneo Eliminacion Directa</code>.
	 */
	private JMenuItem submenuResultadosFinalesEliminacionDirecta;

	/**
	 * Opcion <code>Consultar manual</code> del menú <code>Ayuda</code>.
	 */
	private JMenuItem submenuManual;

	/**
	 * Opcion <code>Acerca de...</code> del menú <code>Ayuda</code>.
	 */
	private JMenuItem submenuAcercaDe;

	/**
	 * Menú horizontal.
	 */
	private JMenuBar submenuBarra;

	/**
	 * Sirve para acceder a los datos que tenga almacenado el torneo actual.
	 */
	private Torneo torneoActual;
	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que permite visualizar el contenido de la ventana principal.
	 */
	// @SuppressWarnings("deprecation")
	public VentanaPrincipal() {

		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/icono.png")));

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				accionSalir();
			}
		});

		menuArchivo = new JMenu("Archivo");
		menuArchivo.setIcon(new ImageIcon(getClass().getResource("/imagenes/archivo.png")));
		menuArchivo.setMnemonic(KeyEvent.VK_A);
		menuArchivo.setToolTipText(
				"Abre un torneo, crea un nuevo torneo, cierra el torneo actual y sale de la aplicación");
		submenuAbrirTorneo = new JMenuItem("Abrir torneo");
		submenuAbrirTorneo.setIcon(new ImageIcon(getClass().getResource("/imagenes/abrir.png")));
		submenuAbrirTorneo.setMnemonic(KeyEvent.VK_A);
		submenuAbrirTorneo.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK + InputEvent.CTRL_DOWN_MASK));
		submenuAbrirTorneo.addActionListener(this);
		submenuAbrirTorneo.setToolTipText("Carga y abre un torneo con extensión .torn");
		submenuCrearTorneo = new JMenuItem("Crear torneo");
		submenuCrearTorneo.addActionListener(this);
		submenuCrearTorneo.setMnemonic(KeyEvent.VK_C);
		submenuCrearTorneo.setIcon(new ImageIcon(getClass().getResource("/imagenes/crearTorneoMenu.png")));
		submenuCrearTorneo.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK + InputEvent.CTRL_DOWN_MASK));
		submenuCrearTorneo.setToolTipText("Crea un nuevo torneo");
		submenuCerrarTorneo = new JMenuItem("Cerrar torneo");
		submenuCerrarTorneo.setIcon(new ImageIcon(getClass().getResource("/imagenes/cerrarTorneoMenu.png")));
		submenuCerrarTorneo.addActionListener(this);
		submenuCerrarTorneo.setToolTipText("Cierra el torneo actual, no se cierra la configuración");
		submenuCerrarTorneo.setEnabled(false);
		submenuSalir = new JMenuItem("Salir");
		submenuSalir.setIcon(new ImageIcon(getClass().getResource("/imagenes/salir.png")));
		submenuSalir.addActionListener(this);
		submenuSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		submenuSalir.setToolTipText("Cierra la aplicación SIGESTOR");
		menuArchivo.add(submenuAbrirTorneo);
		menuArchivo.addSeparator();
		menuArchivo.add(submenuCrearTorneo);
		menuArchivo.addSeparator();
		menuArchivo.add(submenuCerrarTorneo);
		menuArchivo.addSeparator();
		menuArchivo.add(submenuSalir);
		/*-------------------------------------------------------------*/
		menuOperaciones = new JMenu("Operaciones");
		menuOperaciones.setIcon(new ImageIcon(getClass().getResource("/imagenes/operaciones.png")));
		menuOperaciones.setMnemonic(KeyEvent.VK_O);
		menuOperaciones
				.setToolTipText("Configura el torneo a crear, inicia el torneo creado y cancela el torneo actual");
		menuOperaciones.setEnabled(false);

		submenuAdministrarTorneo = new JMenuItem("Administrar torneo");
		submenuAdministrarTorneo.setMnemonic(KeyEvent.VK_D);
		submenuAdministrarTorneo.setIcon(new ImageIcon(getClass().getResource("/imagenes/administrarTorneo.png")));
		submenuAdministrarTorneo.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK + InputEvent.CTRL_DOWN_MASK));
		submenuAdministrarTorneo.addActionListener(this);
		submenuAdministrarTorneo.setToolTipText("Configura la información del torneo actual");
		submenuIniciarTorneo = new JMenuItem("Iniciar torneo");
		submenuIniciarTorneo.setMnemonic(KeyEvent.VK_I);
		submenuIniciarTorneo.setIcon(new ImageIcon(getClass().getResource("/imagenes/iniciarTorneo.png")));
		submenuIniciarTorneo.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK + InputEvent.CTRL_DOWN_MASK));
		submenuIniciarTorneo.addActionListener(this);
		submenuIniciarTorneo.setToolTipText("Comienza el torneo actual, se cierra la configuración");
		submenuCancelarTorneo = new JMenuItem("Cancelar torneo");
		submenuCancelarTorneo.setIcon(new ImageIcon(getClass().getResource("/imagenes/cancelarTorneo.png")));
		submenuCancelarTorneo.addActionListener(this);
		submenuCancelarTorneo.setToolTipText("Borra el avance del torneo actual, se abre la configuración");
		submenuCancelarTorneo.setEnabled(false);
		menuOperaciones.add(submenuAdministrarTorneo);
		menuOperaciones.addSeparator();
		menuOperaciones.add(submenuIniciarTorneo);
		menuOperaciones.addSeparator();
		menuOperaciones.add(submenuCancelarTorneo);
		/*-------------------------------------------------------------*/
		menuTorneoSuizo = new JMenu("Torneo Suizo");
		menuTorneoSuizo.setIcon(new ImageIcon(getClass().getResource("/imagenes/suizo.png")));
		menuTorneoSuizo.setEnabled(false);
		menuTorneoSuizo.setMnemonic(KeyEvent.VK_S);
		menuTorneoSuizo.setToolTipText("Tipo de sistema en el que se basará el torneo");
		submenuConsultarCiclosSuizo = new JMenuItem("Consultar ciclos");
		submenuConsultarCiclosSuizo.setIcon(new ImageIcon(getClass().getResource("/imagenes/emparejamiento.png")));
		submenuConsultarCiclosSuizo.addActionListener(this);
		submenuConsultarCiclosSuizo
				.setToolTipText("Muestra la lista de los participantes, los pareos creados y permite hacer los pareos");
		submenuResultadosSuizo = new JMenuItem("Capturar resultados");
		submenuResultadosSuizo.setIcon(new ImageIcon(getClass().getResource("/imagenes/capturarResultados.png")));
		submenuResultadosSuizo.addActionListener(this);
		submenuResultadosSuizo.setToolTipText(
				"Permite capturar y modificar los resultados parciales del ciclo actual y anteriores. También muestra los resultados que ya hayan sido capturados");
		submenuResultadosFinalesSuizo = new JMenuItem("Resultados finales");
		submenuResultadosFinalesSuizo.setIcon(new ImageIcon(getClass().getResource("/imagenes/resultadosFinales.png")));
		submenuResultadosFinalesSuizo.addActionListener(this);
		submenuResultadosFinalesSuizo
				.setToolTipText("Muestra la tabla final de posiciones y muestra al ganador del torneo");
		menuTorneoSuizo.add(submenuConsultarCiclosSuizo);
		menuTorneoSuizo.addSeparator();
		menuTorneoSuizo.add(submenuResultadosSuizo);
		menuTorneoSuizo.addSeparator();
		menuTorneoSuizo.add(submenuResultadosFinalesSuizo);
		/*-------------------------------------------------------------*/
		menuTorneoRoundRobin = new JMenu("Torneo Round Robin");
		menuTorneoRoundRobin.setIcon(new ImageIcon(getClass().getResource("/imagenes/roundRobin.png")));
		menuTorneoRoundRobin.setEnabled(false);
		menuTorneoRoundRobin.setMnemonic(KeyEvent.VK_R);
		menuTorneoRoundRobin.setDisplayedMnemonicIndex(7);
		menuTorneoRoundRobin.setToolTipText("Muestra la lista de los participantes y los pareos creados ");
		submenuConsultarCiclosRoundRobin = new JMenuItem("Consultar ciclos");
		submenuConsultarCiclosRoundRobin.setIcon(new ImageIcon(getClass().getResource("/imagenes/emparejamiento.png")));
		submenuConsultarCiclosRoundRobin.addActionListener(this);
		submenuConsultarCiclosRoundRobin
				.setToolTipText("Muestra la lista de los participantes y los pareos de cada ciclo");
		submenuResultadosRoundRobin = new JMenuItem("Capturar resultados");
		submenuResultadosRoundRobin.setIcon(new ImageIcon(getClass().getResource("/imagenes/capturarResultados.png")));
		submenuResultadosRoundRobin.addActionListener(this);
		submenuResultadosRoundRobin.setToolTipText(
				"Permite capturar y modificar los resultados parciales del ciclo actual y anteriores. También muestra los resultados que ya hayan sido capturados");
		submenuResultadosFinalesRoundRobin = new JMenuItem("Resultados finales");
		submenuResultadosFinalesRoundRobin
				.setIcon(new ImageIcon(getClass().getResource("/imagenes/resultadosFinales.png")));
		submenuResultadosFinalesRoundRobin
				.setToolTipText("Muestra la tabla final de posiciones y muestra al ganador del torneo");
		submenuResultadosFinalesRoundRobin.addActionListener(this);
		menuTorneoRoundRobin.add(submenuConsultarCiclosRoundRobin);
		menuTorneoRoundRobin.addSeparator();
		menuTorneoRoundRobin.add(submenuResultadosRoundRobin);
		menuTorneoRoundRobin.addSeparator();
		menuTorneoRoundRobin.add(submenuResultadosFinalesRoundRobin);
		/*-------------------------------------------------------------*/
		menuTorneoEliminacionDirecta = new JMenu("Torneo Eliminacion Directa");
		menuTorneoEliminacionDirecta.setIcon(new ImageIcon(getClass().getResource("/imagenes/eliminaciondirecta.png")));
		menuTorneoEliminacionDirecta.setEnabled(false);
		menuTorneoEliminacionDirecta.setMnemonic(KeyEvent.VK_D);
		menuTorneoEliminacionDirecta.setToolTipText("Tipo de sistema en el que se basará el torneo");
		submenuConsultarCiclosEliminacionDirecta = new JMenuItem("Consultar ciclos");
		submenuConsultarCiclosEliminacionDirecta
				.setIcon(new ImageIcon(getClass().getResource("/imagenes/emparejamiento.png")));
		submenuConsultarCiclosEliminacionDirecta.addActionListener(this);
		submenuConsultarCiclosEliminacionDirecta
				.setToolTipText("Muestra la lista de los participantes, los pareos creados y permite hacer los pareos");
		submenuResultadosEliminacionDirecta = new JMenuItem("Capturar resultados");
		submenuResultadosEliminacionDirecta
				.setIcon(new ImageIcon(getClass().getResource("/imagenes/capturarResultados.png")));
		submenuResultadosEliminacionDirecta.addActionListener(this);
		submenuResultadosEliminacionDirecta.setToolTipText(
				"Permite capturar y modificar los resultados parciales del ciclo actual. También muestra los resultados que ya hayan sido capturados");
		submenuResultadosFinalesEliminacionDirecta = new JMenuItem("Resultados finales");
		submenuResultadosFinalesEliminacionDirecta
				.setIcon(new ImageIcon(getClass().getResource("/imagenes/resultadosFinales.png")));
		submenuResultadosFinalesEliminacionDirecta.addActionListener(this);
		submenuResultadosFinalesEliminacionDirecta
				.setToolTipText("Muestra la tabla final de posiciones y muestra al ganador del torneo");
		menuTorneoEliminacionDirecta.add(submenuConsultarCiclosEliminacionDirecta);
		menuTorneoEliminacionDirecta.addSeparator();
		menuTorneoEliminacionDirecta.add(submenuResultadosEliminacionDirecta);
		menuTorneoEliminacionDirecta.addSeparator();
		menuTorneoEliminacionDirecta.add(submenuResultadosFinalesEliminacionDirecta);
		this.cambiarTitulo();
		/*-------------------------------------------------------------*/
		menuAyuda = new JMenu("Ayuda");
		menuAyuda.setIcon(new ImageIcon(getClass().getResource("/imagenes/ayuda.png")));
		menuAyuda.setMnemonic(KeyEvent.VK_Y);
		menuAyuda.setToolTipText("Ofrece un manual sobre el uso de la aplicación e información de los colaboradores");
		submenuManual = new JMenuItem("Consultar manual");
		submenuManual.setMnemonic(KeyEvent.VK_N);
		submenuManual.setIcon(new ImageIcon(getClass().getResource("/imagenes/consultarManual.png")));
		submenuManual.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		submenuManual.addActionListener(this);
		submenuManual.setToolTipText("Abre el manual de introducción de SIGESTOR");
		submenuAcercaDe = new JMenuItem("Acerca de...");
		submenuAcercaDe.setIcon(new ImageIcon(getClass().getResource("/imagenes/acercaDe.png")));
		submenuAcercaDe.setMnemonic(KeyEvent.VK_C);
		submenuAcercaDe.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK + InputEvent.CTRL_DOWN_MASK));
		submenuAcercaDe.addActionListener(this);
		submenuAcercaDe.setToolTipText("Muestra los créditos de SIGESTOR");
		menuAyuda.add(submenuManual);
		menuAyuda.addSeparator();
		menuAyuda.add(submenuAcercaDe);
		/*-------------------------------------------------------------*/
		submenuBarra = new JMenuBar();
		submenuBarra.add(menuArchivo);
		submenuBarra.add(menuOperaciones);
		submenuBarra.add(menuTorneoSuizo);
		submenuBarra.add(menuTorneoRoundRobin);
		submenuBarra.add(menuTorneoEliminacionDirecta);
		submenuBarra.add(menuAyuda);
		this.setJMenuBar(submenuBarra);
		this.getContentPane().setLayout(new FlowLayout());
		JLabel fondo = new JLabel();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));
		Image imagenEscalada = imagenFondo.getImage().getScaledInstance(-1,
				Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_DEFAULT);
		fondo.setIcon(new ImageIcon(imagenEscalada));
		this.getContentPane().add(fondo);

		this.setTitle("SIGESTOR");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setLocationRelativeTo(null);

		this.setResizable(false);

		this.setVisible(true);
	}

	/**
	 * Constructor que será llamado al abrir un torneo.
	 * 
	 * @param string
	 *            recibe una ruta desde el sistema operativo.
	 */
	public VentanaPrincipal(String string) {
		this();
		accionAbrirTorneo(string);
	}

	/**
	 * Encargado del manejo de eventos producido al hacer clic sobre un componente
	 * de la ventana principal.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(submenuAbrirTorneo)) {
			accionAbrirTorneo(null);
		} else if (e.getSource().equals(submenuCrearTorneo)) {
			accionCrearTorneo();
		} else if (e.getSource().equals(submenuCerrarTorneo)) {
			accionCerrarTorneo();
		} else if (e.getSource().equals(submenuSalir)) {
			accionSalir();
		} else if (e.getSource().equals(submenuAdministrarTorneo)) {
			accionAdministrarTorneo();
		} else if (e.getSource().equals(submenuIniciarTorneo)) {
			accionIniciarTorneo();
		} else if (e.getSource().equals(submenuCancelarTorneo)) {
			accionCancelarTorneo();
		} else if (e.getSource().equals(submenuConsultarCiclosSuizo)) {
			accionAbrirCiclo();
		} else if (e.getSource().equals(submenuResultadosSuizo)) {
			accionAbrirCapturarResultados();
		} else if (e.getSource().equals(submenuResultadosFinalesSuizo)) {
			accionAbrirResultadosFinales();
		} else if (e.getSource().equals(submenuConsultarCiclosEliminacionDirecta)) {
			accionAbrirCiclo();
		} else if (e.getSource().equals(submenuResultadosEliminacionDirecta)) {
			accionAbrirCapturarResultados();
		} else if (e.getSource().equals(submenuResultadosFinalesEliminacionDirecta)) {
			accionAbrirResultadosFinales();
		} else if (e.getSource().equals(submenuConsultarCiclosRoundRobin)) {
			accionAbrirCiclo();
		} else if (e.getSource().equals(submenuResultadosRoundRobin)) {
			accionAbrirCapturarResultados();
		} else if (e.getSource().equals(submenuResultadosFinalesRoundRobin)) {
			accionAbrirResultadosFinales();
		} else if (e.getSource().equals(submenuManual)) {
			accionCargarManual();
		} else if (e.getSource().equals(submenuAcercaDe)) {
			accionAbrirAcercaDe();
		}
	}

	/**
	 * Encargado de abrir <code>DialogoAdministrarTorneo</code>.
	 */
	private void accionAdministrarTorneo() {
		new DialogoAdministarTorneo(this);
	}

	/**
	 * Encargado de abrir <code>DialogoCapturarResultados</code>.
	 */
	private void accionAbrirCapturarResultados() {
		new DialogoCapturarResultados(this);
	}

	/**
	 * Encargado de abrir <code>DialogoCiclo</code>.
	 */
	private void accionAbrirCiclo() {
		new DialogoCiclo(this);
	}

	/**
	 * Encargado de salir del sistema.
	 */
	private void accionSalir() {
		System.exit(0);
	}

	/**
	 * Habilita la administración del torneo y deshabilita los menús de los torneos
	 * Suizo, Eliminacion Directa y Round Robin, además de deshabilitar la opción de
	 * <code>Cancelar torneo</code> el torneo.
	 */
	private void accionCancelarTorneo() {
		String[] valores = { "Sí", "No" };
		int opcion = JOptionPane.showOptionDialog(this, "¿Está seguro de cancelar el torneo?", "Cancelar Torneo",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, valores, valores[1]);
		if (opcion == 0) {
			String[] valores2 = { "Aceptar", "Cancelar" };
			int opcion2 = JOptionPane.showOptionDialog(this, "¿Está realmente seguro de que desea cancelar el torneo?",
					"Cancelar Torneo", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, valores2,
					valores2[1]);
			if (opcion2 == 0) {
				try {
					Participante.setPuntajeAcumulado(false);

					getTorneoActual().cancelarTorneo();
				} catch (ExcepcionBaseDatos e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Cancelar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionBaseDatosParticipante e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Cancelar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionBaseDatosTorneo e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Cancelar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionBaseDatosCiclo e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Cancelar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionBaseDatosEncuentro e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Cancelar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionBaseDatosCriteriosDesempate e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Cancelar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionCapturarResultados e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Cancelar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionBaseDatosPersonalizacion e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Cancelar torneo", JOptionPane.ERROR_MESSAGE);
				}

				this.submenuResultadosEliminacionDirecta.setText("Capturar resultados");
				this.submenuResultadosSuizo.setText("Capturar resultados");
				this.submenuResultadosRoundRobin.setText("Capturar resultados");

				submenuAdministrarTorneo.setEnabled(true);
				submenuIniciarTorneo.setEnabled(true);
				submenuCancelarTorneo.setEnabled(false);
				menuTorneoRoundRobin.setEnabled(false);
				menuTorneoSuizo.setEnabled(false);
				menuTorneoEliminacionDirecta.setEnabled(false);
			}

		}

	}

	/**
	 * Deshabilita la opción <code>Operaciones</code> del menú, así como ambos tipos
	 * de torneos del menú y retira el nombre asignado en la aplicación de acuerdo
	 * al nombre que se tenía en el torneo que estaba abierto.
	 */
	private void accionCerrarTorneo() {
		setTorneoActual(null);
		submenuCerrarTorneo.setEnabled(false);
		menuOperaciones.setEnabled(false);
		menuTorneoRoundRobin.setEnabled(false);
		menuTorneoSuizo.setEnabled(false);
		menuTorneoEliminacionDirecta.setEnabled(false);
		submenuCrearTorneo.setEnabled(true);
		submenuAbrirTorneo.setEnabled(true);
		this.setTitle("SIGESTOR");
	}

	/**
	 * Permite crear un nuevo torneo almancenando la administración del torneo en el
	 * archivo seleccionado.
	 */
	private void accionCrearTorneo() {
		new DialogoAdministarTorneo(this);
		if (getTorneoActual() != null) {
			File file = new File(getTorneoActual().getNombreArchivo());
			this.setTitle(file.getName().replaceAll(".torn", "") + " - SIGESTOR");
			submenuCerrarTorneo.setEnabled(true);
			menuOperaciones.setEnabled(true);
			submenuAdministrarTorneo.setEnabled(true);
			submenuIniciarTorneo.setEnabled(true);
			submenuCancelarTorneo.setEnabled(false);
		}
	}

	/**
	 * Muestra en una ventana al director del proyecto y sus colaboradores con la
	 * opción <code>Acerca de...</code>.
	 */
	private void accionAbrirAcercaDe() {
		JOptionPane.showMessageDialog(this, "Sistema gestor de torneos v.1.0\n\n" + "\nDirector del proyecto"
				+ "\nM.C. Manuel Alejandro Valdés Marrero\n" + "\n\nColaboradores: "
				+ "\nClemente Hernández Alicia Adriana" + "\nCortés Pérez Jennifer" + "\nCruz Cortes Uriel Romeo"
				+ "\nCruz Martinez Luis German" + "\nDe la Cruz López Luis Fernando" + "\nDionisio Diaz Eder Euclides"
				+ "\nIbarra Martínez Jonathan Eduardo" + "\nJiménez Ríos Beatriz Andrea" + "\nLópez Aragón Hernán Sesaí"
				+ "\nMatias Acevedo Luis Fernando" + "\nPeralta Reyes Ricky Didier" + "\nReyes Cortes Francisco Samuel"
				+ "\nRuiz Sierra Luis Antonio" + "\nTriste Pérez Victor" + "\nVasquez Policarpo Erik"
				+ "\n\n\nDerechos reservados UMAR " + '\u00A9' + " 2023", "Acerca de... SIGESTOR",
				JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/imagenes/logoSigestor.jpg")));
	}

	/**
	 * Encargado de mostrar un archivo PDF al usuario con información relevante de
	 * como utilizar el sistema, en caso de que no exista o no se pueda abrir el
	 * archivo se mostrará un mensaje de error.
	 */
	public void accionCargarManual() {
		File archivo = new File("MANUAL SIGESTOR.pdf");
		if (archivo.exists()) {
			try {
				Desktop.getDesktop().open(archivo);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,
						"El sistema no pudo abrir el manual, favor de contactar con soporte técnico",
						"Error al abrir el manual", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this,
					"El sistema no pudo encontrar el manual, favor de contactar con soporte técnico",
					"Error al encontrar el manual", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Regresa el torneo actual con todos sus atributos y sus valores asignados.
	 * 
	 * @return Variable de tipo <code>Torneo</code> con los datos del torneo actual.
	 */
	public Torneo getTorneoActual() {
		return torneoActual;
	}

	/**
	 * Inicializa la variable <code>torneoActual</code> con el objeto recibido.
	 * 
	 * @param torneoActual
	 *            Variable con los datos del torneo actual.
	 */
	public void setTorneoActual(Torneo torneoActual) {
		this.torneoActual = torneoActual;
	}

	/**
	 * Permite abrir el archivo del torneo con extensión .torn y verifica si puede
	 * continuar con la administración del torneo, verifica si el torneo ya ha
	 * comenzado para permitir continuar capurando los resultados o visualiza los
	 * resultados finales del torneo seleccionado.
	 * 
	 * @param ruta
	 *            La ruta que tiene el archivo.
	 */
	private void accionAbrirTorneo(String ruta) {
		String nombreArchivo;
		if (ruta == null) {
			nombreArchivo = abrirArchivo();
		} else {
			nombreArchivo = ruta;
		}
		if (nombreArchivo != null) {
			try {

				Torneo torneo = new Torneo();
				torneo.setNombreArchivo(nombreArchivo);
				torneo.recuperarTorneo();
				setTorneoActual(torneo);

				File file = new File(getTorneoActual().getNombreArchivo());
				this.setTitle(file.getName().replaceAll(".torn", "") + " - SIGESTOR");

				JOptionPane.showMessageDialog(null, "El archivo ha sido abierto exitosamente.", "Torneo Abierto",
						JOptionPane.INFORMATION_MESSAGE);

				submenuAbrirTorneo.setEnabled(false);
				menuOperaciones.setEnabled(true);
				submenuCerrarTorneo.setEnabled(true);
				submenuCrearTorneo.setEnabled(false);

				if (getTorneoActual().getCicloActual() > 0) {
					submenuAdministrarTorneo.setEnabled(false);
					submenuIniciarTorneo.setEnabled(false);
					submenuCancelarTorneo.setEnabled(true);

					if (getTorneoActual().getTipoTorneo().compareTo("Suizo") == 0) {
						menuTorneoSuizo.setEnabled(true);
						this.cambiarTitulo();
						submenuConsultarCiclosSuizo
								.setText("Consultar " + getTorneoActual().getDatosPersonalizacion().getNombreCiclo(1));
					} else if (getTorneoActual().getTipoTorneo().compareTo("Round Robin") == 0) {
						menuTorneoRoundRobin.setEnabled(true);
						this.cambiarTitulo();
						submenuConsultarCiclosRoundRobin
								.setText("Consultar " + getTorneoActual().getDatosPersonalizacion().getNombreCiclo(1));
					} else {
						menuTorneoEliminacionDirecta.setEnabled(true);
						this.cambiarTitulo();
						submenuConsultarCiclosEliminacionDirecta
								.setText("Consultar " + getTorneoActual().getDatosPersonalizacion().getNombreCiclo(1));
					}
				} else {
					submenuAdministrarTorneo.setEnabled(true);
					submenuIniciarTorneo.setEnabled(true);
					submenuCancelarTorneo.setEnabled(false);
				}
			} catch (ExcepcionBaseDatos e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Abrir torneo", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionBaseDatosTorneo e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Abrir torneo", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionBaseDatosCriteriosDesempate e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Abrir torneo", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionBaseDatosParticipante e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Abrir torneo", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionCapturarResultados e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Abrir torneo", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionBaseDatosPersonalizacion e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Abrir torneo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Muestra un diálogo donde se seleccionará el archivo con extensión .torn y
	 * verificará que tenga los datos correctos.
	 * 
	 * @return La ruta del archivo seleccionado.
	 */
	private String abrirArchivo() {
		JFileChooser dialogo = new JFileChooser();
		dialogo.setDialogTitle("Abrir archivo");
		FileFilter filtro1 = new FileNameExtensionFilter("Archivo SIGESTOR", "torn", "TORN");
		dialogo.setFileFilter(filtro1);
		dialogo.addChoosableFileFilter(filtro1);
		dialogo.setAcceptAllFileFilterUsed(false);
		dialogo.setCurrentDirectory(new File(System.getProperty("user.dir")));
		dialogo.setSelectedFile(null);
		dialogo.setMultiSelectionEnabled(false);
		int valor = dialogo.showOpenDialog(this);
		if (valor == JFileChooser.APPROVE_OPTION) {
			File archivo = dialogo.getSelectedFile();
			if (archivo.exists()) {
				return archivo.getAbsolutePath();
			} else {
				JOptionPane.showMessageDialog(null, ExcepcionAbrirTorneo.MENSAJE_EXCEPCION_ARCHIVO_NO_EXISTENTE,
						"Abrir torneo", JOptionPane.ERROR_MESSAGE);
			}
		}
		return null;
	}

	/**
	 * Validará de acuerdo a la personalización del torneo el tipo de torneo que
	 * deberá dar comienzo, así mismo, solicitará la cantidad de ciclos y el número
	 * de vueltas únicamente para el torneo Round Robin y para el torneo de
	 * Eliminación directa solo pedirá si el tipo del torneo será simple o doble.
	 * Deshabilitará el continuar con la adminstración del torneo una vez iniciado.
	 */
	private void accionIniciarTorneo() {
		try {
			getTorneoActual().validarTorneo();
			getTorneoActual().getDatosPersonalizacion().validarPersonalizacion();

			if (getTorneoActual().getTipoTorneo().equals("Round Robin")) {
				TorneoRoundRobin roundRobin = new TorneoRoundRobin(this.torneoActual);
				JPanel contenidoVueltas = new JPanel();
				contenidoVueltas.setLayout(new BoxLayout(contenidoVueltas, BoxLayout.Y_AXIS));
				JPanel panelAuxVueltas = new JPanel();

				JLabel textoVueltas = new JLabel("Seleccione el número de vueltas para el torneo:");
				contenidoVueltas.add(textoVueltas);

				SpinnerNumberModel sModel1 = new SpinnerNumberModel(1, 1, null, 1);
				JSpinner spinner1 = new JSpinner(sModel1);
				spinner1.setPreferredSize(new Dimension(80, 20));
				panelAuxVueltas.add(spinner1);
				contenidoVueltas.add(panelAuxVueltas);

				int seleccionVuelta = JOptionPane.showOptionDialog(null, contenidoVueltas, "Iniciar torneo",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (seleccionVuelta == 0) {

					JPanel contenidoCiclos = new JPanel();
					contenidoCiclos.setLayout(new BoxLayout(contenidoCiclos, BoxLayout.Y_AXIS));
					JPanel panelAuxCiclos = new JPanel();

					JLabel textoCiclos = new JLabel("Seleccione el número de ciclos para el torneo:");
					contenidoCiclos.add(textoCiclos);

					SpinnerNumberModel sModel2 = new SpinnerNumberModel(
							roundRobin.calcularNumeroCiclos(getTorneoActual().getListaParticipantes().size())
									* (int) spinner1.getValue(),
							((((int) spinner1.getValue()) - 1) * (getTorneoActual().getListaParticipantes().size() - 1))
									+ 1,
							roundRobin.calcularNumeroCiclos(getTorneoActual().getListaParticipantes().size())
									* (int) spinner1.getValue(),
							1);

					JSpinner spinner2 = new JSpinner(sModel2);
					spinner2.setPreferredSize(new Dimension(80, 20));
					panelAuxCiclos.add(spinner2);
					contenidoCiclos.add(panelAuxCiclos);

					int seleccionCiclo = JOptionPane.showOptionDialog(null, contenidoCiclos, "Iniciar torneo",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

					if (seleccionCiclo == 0) {
						roundRobin.setNumeroCiclos((int) spinner2.getValue());
						this.torneoActual.getAlgoritmoTorneo().setNumeroCiclos((int) spinner2.getValue());
						roundRobin.setNumeroVueltas((int) spinner1.getValue());

						try {
							try {
								getTorneoActual().iniciarTorneo(roundRobin, null, null);
							} catch (ExcepcionCapturarResultados e) {
								JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo Round Robin",
										JOptionPane.ERROR_MESSAGE);
							} catch (ExcepcionBaseDatosParticipante e) {
								JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo Round Robin",
										JOptionPane.ERROR_MESSAGE);
							}
							JOptionPane.showMessageDialog(null,
									"El torneo " + getTorneoActual().getNombreTorneo()
											+ " se ha iniciado correctamente.",
									"Iniciar torneo Round Robin", JOptionPane.INFORMATION_MESSAGE);

							menuTorneoRoundRobin.setEnabled(true);
							submenuAdministrarTorneo.setEnabled(false);
							submenuIniciarTorneo.setEnabled(false);
							submenuCancelarTorneo.setEnabled(true);
							submenuConsultarCiclosRoundRobin.setText(
									"Consultar " + getTorneoActual().getDatosPersonalizacion().getNombreCiclo(1));

						} catch (ExcepcionBaseDatosTorneo e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				}
			} else if (getTorneoActual().getTipoTorneo().equals("Suizo")) {
				TorneoSuizo suizo = new TorneoSuizo(this.torneoActual);
				JPanel contenido = new JPanel();
				contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
				JPanel panelAux = new JPanel();
				JLabel texto = new JLabel("Seleccione el número de ciclos para el torneo:");
				contenido.add(texto);
				SpinnerNumberModel sModel = new SpinnerNumberModel(
						suizo.calcularNumeroCiclos(getTorneoActual().getListaParticipantes().size()), 1,
						suizo.calcularNumeroCiclos(getTorneoActual().getListaParticipantes().size()), 1);
				JSpinner spinner = new JSpinner(sModel);
				spinner.setPreferredSize(new Dimension(80, 20));
				panelAux.add(spinner);
				contenido.add(panelAux);
				int seleccionCiclo = JOptionPane.showOptionDialog(null, contenido, "Iniciar torneo",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (seleccionCiclo == 0) {
					suizo.setNumeroCiclos((int) spinner.getValue());
					try {
						try {
							getTorneoActual().iniciarTorneo(null, suizo, null);
						} catch (ExcepcionCapturarResultados e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo Suizo",
									JOptionPane.ERROR_MESSAGE);

						} catch (ExcepcionBaseDatosParticipante e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo Suizo",
									JOptionPane.ERROR_MESSAGE);
						}
						JOptionPane.showMessageDialog(null,
								"El torneo " + getTorneoActual().getNombreTorneo() + " se ha iniciado correctamente.",
								"Iniciar torneo Suizo", JOptionPane.INFORMATION_MESSAGE);

						menuTorneoSuizo.setEnabled(true);
						submenuAdministrarTorneo.setEnabled(false);
						submenuIniciarTorneo.setEnabled(false);
						submenuCancelarTorneo.setEnabled(true);

						submenuConsultarCiclosSuizo
								.setText("Consultar " + getTorneoActual().getDatosPersonalizacion().getNombreCiclo(1));

					} catch (ExcepcionBaseDatosTorneo e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			} else if (getTorneoActual().getTipoTorneo().equals("Eliminación directa")) {
				TorneoEliminacionDirecta eliminacionDirecta = new TorneoEliminacionDirecta(this.torneoActual);
				JPanel contenidoMensaje = new JPanel();
				contenidoMensaje.setLayout(new BoxLayout(contenidoMensaje, BoxLayout.Y_AXIS));
				JLabel texto = new JLabel("Seleccione el subtipo de torneo Eliminación directa:");
				contenidoMensaje.add(texto);
				String[] arregloTipoTorneo = { "Simple", "Doble" };
				contenidoMensaje.add(texto);
				comboTipoTorneo = new JComboBox<String>(arregloTipoTorneo);
				comboTipoTorneo.setPreferredSize(new Dimension(100, 30));
				contenidoMensaje.add(comboTipoTorneo);
				int resultado = JOptionPane.showOptionDialog(null, contenidoMensaje, "Iniciar torneo",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (resultado == JOptionPane.OK_OPTION) {
					if (comboTipoTorneo.getSelectedItem().equals("Simple")) {
						eliminacionDirecta.setTipoEliminacion(true);
					} else {
						eliminacionDirecta.setTipoEliminacion(false);
					}
					eliminacionDirecta.setNumeroCiclos(
							eliminacionDirecta.calcularNumeroCiclos(getTorneoActual().getListaParticipantes().size()));
					try {
						try {
							getTorneoActual().iniciarTorneo(null, null, eliminacionDirecta);
						} catch (ExcepcionCapturarResultados e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo Eliminación directa",
									JOptionPane.ERROR_MESSAGE);

						} catch (ExcepcionBaseDatosParticipante e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo Eliminación directa",
									JOptionPane.ERROR_MESSAGE);
						}
						JOptionPane.showMessageDialog(null,
								"Número de ciclos es: " + eliminacionDirecta.getNumeroCiclos(),
								"Iniciar torneo Eliminación directa", JOptionPane.INFORMATION_MESSAGE);

						JOptionPane.showMessageDialog(null,
								"El torneo " + getTorneoActual().getNombreTorneo() + " se ha iniciado correctamente.",
								"Iniciar torneo Eliminación directa", JOptionPane.INFORMATION_MESSAGE);

						menuTorneoEliminacionDirecta.setEnabled(true);
						submenuAdministrarTorneo.setEnabled(false);
						submenuIniciarTorneo.setEnabled(false);
						submenuCancelarTorneo.setEnabled(true);
						submenuConsultarCiclosEliminacionDirecta
								.setText("Consultar " + getTorneoActual().getDatosPersonalizacion().getNombreCiclo(1));

					} catch (ExcepcionBaseDatosTorneo e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			}

		} catch (ExcepcionTorneo e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionPersonalizacion e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionBaseDatos e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionBaseDatosEncuentro e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo", JOptionPane.ERROR_MESSAGE);
		} catch (ExcepcionBaseDatosCiclo e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Iniciar torneo", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Permite abrir la ventana <code>Resultados finales</code> una vez terminado de
	 * capturar todos los resultados de los ciclos del torneo.
	 */
	private void accionAbrirResultadosFinales() {
		if (this.getTorneoActual().getAlgoritmoTorneo().verificarResultadosCompletos() && this.getTorneoActual()
				.getCicloActual() == this.getTorneoActual().getAlgoritmoTorneo().getNumeroCiclos()) {
			new DialogoResultadosFinales(this);
			cambiarTitulo();
		} else {
			JOptionPane.showMessageDialog(null,
					ExcepcionResultadosFinales.MENSAJE_EXCEPCION_RESULTADOS_FINALES_INCOMPLETOS, "Resultados finales",
					JOptionPane.ERROR_MESSAGE);
		}
		new DialogoResultadosFinales(this);
	}

	/**
	 * Cambia el titulo del menú <code>Capturar resultados</code> una vez terminado
	 * el torneo.
	 */
	private void cambiarTitulo() {
		if (this.getTorneoActual() != null && this.getTorneoActual().getAlgoritmoTorneo().verificarResultadosCompletos()
				&& this.getTorneoActual().getCicloActual() == this.getTorneoActual().getAlgoritmoTorneo()
						.getNumeroCiclos()
				&& this.getTorneoActual().getListaParticipantes().get(1).getLugarParticipante() > 0) {

			this.submenuResultadosRoundRobin.setText("Reporte de resultados");
			this.submenuResultadosSuizo.setText("Reporte de resultados");
			this.submenuResultadosEliminacionDirecta.setText("Reporte de resultados");
		} else {
			this.submenuResultadosRoundRobin.setText("Capturar resultados");
			this.submenuResultadosSuizo.setText("Capturar resultados");
			this.submenuResultadosEliminacionDirecta.setText("Capturar resultados");
		}

	}
}
