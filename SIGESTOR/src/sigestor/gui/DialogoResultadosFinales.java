package sigestor.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import sigestor.dominio.Participante;
import sigestor.dominio.Personalizacion;
import sigestor.dominio.TorneoEliminacionDirecta;
import sigestor.dominio.TorneoRoundRobin;
import sigestor.dominio.TorneoSuizo;
import sigestor.excepcion.ExcepcionUtilerias;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Sirve para mostrar la interfaz del dialogo de los resultados finales del
 * torneo.
 * <p>
 * Las características de la clase <code>DialogoResultadoCiclo</code> son:
 * <ul>
 * <li><code>tablaListaPosiciones</code>Contrenda una tabla con las posiciones
 * asignadas de los participantes.</li>
 * <li><code>campoNombreTorneo</code>Se especificara el nombre que tendra el
 * torneo almacenado con aterioridad.</li>
 * <li><code>campoFechaInicio</code>Se especificara la fecha en que se inciara
 * el torneo almacenado con anterioridad.</li>
 * <li><code>campoFechaFin</code>Se especificara la fecha en que el torneo
 * termina almacenada con anterioridad.</li>
 * <li><code>campoNombreOrganizador</code>Se especificara el nombre que tendra
 * el organizador del torneo almacenado con anterioridad.</li>
 * <li><code>campoNombreGanador</code> Se especificara el nombre que tendra el
 * ganador del torneo.</li>
 * <li><code>botonExportar</code>Se exportara a un archivo CSV donde contendra
 * los resultados de los participantes.</li>
 * <li><code>botonSalir</code>Tendra la funcionalidad de cerrar el dialogo.</li>
 * <li><code>ventanaPrincipal</code>Se hara llamado a la ventana principal del
 * torneo.</li>
 * <li><code>serialVersionUID</code> Para el número de versión de la clase.</li>
 * </ul>
 * 
 * @version 10/04/2023
 * 
 * @author Jonathan Eduardo Ibarra Martinez
 * @author Hernan Sesai Lopez Aragon
 * @author Francisco Samuel Reyes Cortes
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 */
public class DialogoResultadosFinales extends JDialog {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Permite crear una tabla con 20 filas y 6 comunas.
	 */
	private JTable tablaListaPosiciones = new JTable(20, 6);

	/**
	 * Campo para el nombre del torneo.
	 */
	private JTextField campoNombreTorneo;

	/**
	 * Campo para la fecha de inicio.
	 */
	private JTextField campoFechaInicio;

	/**
	 * Campo para la fecha de fin.
	 */
	private JTextField campoFechaFin;

	/**
	 * Campo para el nombre del organizador.
	 */
	private JTextField campoNombreOrganizador;

	/**
	 * Campo para el nombre del ganador.
	 */
	private JTextField campoNombreGanador;

	/**
	 * Botón <code>Exportar resultados</code>.
	 * 
	 * @see #accionExportarResultados()
	 */
	private JButton botonExportar;

	/**
	 * Botón <code>Salir</code>.
	 * 
	 * @see #accionSalir()
	 */
	private JButton botonSalir;
	/**
	 * Referencia a la clase <code>VentanaPrincipal</code>
	 */
	private VentanaPrincipal ventanaPrincipal;

	/**
	 * 
	 */
	private DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * 
	 */
	private ArrayList<Participante> listaParticipantes;

	/**
	 * Constructor en el que se inicializa el dialogo <code>principal</code>
	 * 
	 * @param principal Recibe un objeto de tipo ventanaPrincipal el cual contiene
	 *                  el objeto de tipo torneo.
	 */
	public DialogoResultadosFinales(VentanaPrincipal principal) {
		super(principal, "Resultados finales");

		ventanaPrincipal = principal;

		JPanel panelBase = new JPanel();
		JPanel panelAuxiliar = new JPanel();

		ImageIcon imagenF;
		Image imagenE;

		panelBase.setLayout(new GridLayout(1, 1));
		panelAuxiliar.setLayout(null);

		if (principal.getTorneoActual().getTipoTorneo().contains("Suizo")) {
			TorneoSuizo ts = new TorneoSuizo(principal.getTorneoActual());
			ts.desempatarParticipantes();

		} else if ((principal.getTorneoActual().getTipoTorneo().contains("Eliminación directa"))) {
			TorneoEliminacionDirecta te = new TorneoEliminacionDirecta(principal.getTorneoActual());
			te.desempatarParticipantes();

		} else {
			TorneoRoundRobin tr = new TorneoRoundRobin(principal.getTorneoActual());
			tr.desempatarParticipantes();
		}

		principal.getTorneoActual().getAlgoritmoTorneo().asignarLugarParticipante();
		listaParticipantes = ventanaPrincipal.getTorneoActual().getListaParticipantes();

		JLabel etiquetaTitulo = new JLabel("Reporte de resultados finales del torneo");
		Font fuente = etiquetaTitulo.getFont();
		etiquetaTitulo.setFont(new Font(fuente.getFontName(), fuente.getStyle(), 20));
		etiquetaTitulo.setBounds(400, 0, 400, 30);
		panelAuxiliar.add(etiquetaTitulo);
		panelBase.add(panelAuxiliar);

		Action accionAyuda = new AbstractAction("Reporte de resultados finales del torneo", null) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionAyuda();
			}
		};
		accionAyuda.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		etiquetaTitulo.getActionMap().put("ayuda", accionAyuda);
		etiquetaTitulo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put((KeyStroke) accionAyuda.getValue(Action.ACCELERATOR_KEY), "ayuda");

		JLabel etiquetaTorneo = new JLabel("Torneo:");
		etiquetaTorneo.setBounds(320, 40, 50, 20);
		panelAuxiliar.add(etiquetaTorneo);
		panelBase.add(panelAuxiliar);

		campoNombreTorneo = new JTextField(this.ventanaPrincipal.getTorneoActual().getNombreTorneo());
		campoNombreTorneo.setBounds(400, 40, 150, 20);
		campoNombreTorneo.setEditable(false);
		;
		panelAuxiliar.add(campoNombreTorneo);
		panelBase.add(panelAuxiliar);

		JLabel etiquetaFechaInicio = new JLabel("Fecha de inicio:");
		etiquetaFechaInicio.setBounds(620, 40, 150, 20);
		panelAuxiliar.add(etiquetaFechaInicio);
		panelBase.add(panelAuxiliar);

		DateFormat formato = DateFormat.getDateInstance(DateFormat.FULL);

		campoFechaInicio = new JTextField(
				formato.format(this.ventanaPrincipal.getTorneoActual().getFechaInicioTorneo()));
		campoFechaInicio.setBounds(740, 40, 200, 20);
		campoFechaInicio.setEditable(false);
		panelAuxiliar.add(campoFechaInicio);
		panelBase.add(panelAuxiliar);

		JLabel etiquetaOrganizador = new JLabel("Organizador:");
		etiquetaOrganizador.setBounds(320, 80, 100, 20);
		panelAuxiliar.add(etiquetaOrganizador);
		panelBase.add(panelAuxiliar);

		campoNombreOrganizador = new JTextField(this.ventanaPrincipal.getTorneoActual().getNombreOrganizador());
		campoNombreOrganizador.setBounds(400, 80, 150, 20);
		campoNombreOrganizador.setEditable(false);
		panelAuxiliar.add(campoNombreOrganizador);
		panelBase.add(panelAuxiliar);

		JLabel etiquetaFechaFin = new JLabel("Fecha de fin:");
		etiquetaFechaFin.setBounds(620, 80, 100, 20);
		panelAuxiliar.add(etiquetaFechaFin);
		panelBase.add(panelAuxiliar);

		campoFechaFin = new JTextField(formato.format(this.ventanaPrincipal.getTorneoActual().getFechaFinalTorneo()));
		campoFechaFin.setBounds(740, 80, 200, 20);
		campoFechaFin.setEditable(false);
		panelAuxiliar.add(campoFechaFin);
		panelBase.add(panelAuxiliar);

		JLabel etiquetaGanador = new JLabel("Ganador: ");
		etiquetaGanador.setBounds(480, 120, 100, 20);
		panelAuxiliar.add(etiquetaGanador);
		panelBase.add(panelAuxiliar);

		if (!ventanaPrincipal.getTorneoActual().getTipoTorneo().equals("Eliminacion Directa")) {
			campoNombreGanador = new JTextField(this.listaParticipantes.get(0).getNombreParticipante());
		} else {
			campoNombreGanador = new JTextField();
		}

		campoNombreGanador.setBounds(560, 120, 150, 20);
		campoNombreGanador.setEditable(false);
		panelAuxiliar.add(campoNombreGanador);
		panelBase.add(panelAuxiliar);

		JLabel etiquetaTituloTabla = new JLabel("Tabla de resultados finales:");
		panelAuxiliar.add(etiquetaTituloTabla);
		panelBase.add(panelAuxiliar);

		encabezadoTabla();

		tablaListaPosiciones.setEnabled(false);
		JScrollPane scrollTablaPosiciones = new JScrollPane(tablaListaPosiciones);
		scrollTablaPosiciones.setBorder(BorderFactory.createEmptyBorder());

		if (principal.getTorneoActual().getDatosPersonalizacion().isExistenciaMarcador()) {
			scrollTablaPosiciones.setBounds(172, 180, 855, 300);
			etiquetaTituloTabla.setBounds(172, 140, 200, 30);
		} else {
			scrollTablaPosiciones.setBounds(291, 180, 618, 300);
			etiquetaTituloTabla.setBounds(291, 140, 200, 30);
		}

		panelAuxiliar.add(scrollTablaPosiciones);
		panelBase.add(panelAuxiliar);

		botonExportar = new JButton("Exportar resultados");
		botonExportar.setBounds(425, 500, 175, 30);
		panelAuxiliar.add(botonExportar);
		panelBase.add(panelAuxiliar);
		imagenF = new ImageIcon(getClass().getResource("/imagenes/exportarRonda.png"));
		imagenE = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		Action accionExportarResultados = new AbstractAction("Exportar resultados", new ImageIcon(imagenE)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				accionExportarResultados();
			}
		};
		accionExportarResultados.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		accionExportarResultados.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		accionExportarResultados.putValue(Action.SHORT_DESCRIPTION,
				"Genera un archivo CSV con los resultados finales del torneo");
		botonExportar.setAction(accionExportarResultados);
		botonExportar.getActionMap().put("exportarResultados", accionExportarResultados);
		botonExportar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put((KeyStroke) accionExportarResultados.getValue(Action.ACCELERATOR_KEY), "exportarResultados");

		botonSalir = new JButton("Salir");
		botonSalir.setBounds(680, 500, 100, 30);
		panelAuxiliar.add(botonSalir);
		panelBase.add(panelAuxiliar);
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
		accionSalir.putValue(Action.SHORT_DESCRIPTION, "Cierra la ventana y regresa a la ventana principal");
		botonSalir.setAction(accionSalir);
		botonSalir.getActionMap().put("salir", accionSalir);
		botonSalir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put((KeyStroke) accionSalir.getValue(Action.ACCELERATOR_KEY), "salir");

		this.add(panelBase);

		this.setSize(new Dimension(1200, 600));
		this.setLocationRelativeTo(principal);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/icono.png")));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Permite ponerle nombres a las columnas de la tabla de la ventana.
	 */
	private void encabezadoTabla() {
		tablaListaPosiciones.setEnabled(false);
		modelo.addColumn("Lugar");
		modelo.addColumn("Núm.");
		modelo.addColumn("Nombre de " + this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
				.getNombreParticipante(Personalizacion.MINUSCULA_SINGULAR));
		if (this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().isExistenciaMarcador()) {
			modelo.addColumn(this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
					.getNombreMarcador(Personalizacion.MAYUSCULA_SINGULAR) + " a favor");
			modelo.addColumn(this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
					.getNombreMarcador(Personalizacion.MAYUSCULA_SINGULAR) + " en contra");
		}
		modelo.addColumn("Puntaje");
		// FIXME
		if (!ventanaPrincipal.getTorneoActual().getTipoTorneo().equals("Eliminación directa")) {
			for (int i = 0; i < this.ventanaPrincipal.getTorneoActual().getListaParticipantes().size(); i++) {
				if (!this.listaParticipantes.get(i).getNombreParticipante().equals(this.ventanaPrincipal
						.getTorneoActual().getDatosPersonalizacion().getNombreParticipanteSinEncuentro())) {
					if (this.ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().isExistenciaMarcador()) {
						Object[] fila = { this.listaParticipantes.get(i).getLugarParticipante(),
								this.listaParticipantes.get(i).getNumeroParticipante(),
								this.listaParticipantes.get(i).toString(),
								this.listaParticipantes.get(i).getMarcadorFavor(),
								this.listaParticipantes.get(i).getMarcadorContra(),
								this.listaParticipantes.get(i).getPuntajeAcumuladoParticipante() };
						modelo.addRow(fila);
					} else {
						Object[] fila = { this.listaParticipantes.get(i).getLugarParticipante(),
								this.listaParticipantes.get(i).getNumeroParticipante(),
								this.listaParticipantes.get(i).getNombreParticipante(),
								this.listaParticipantes.get(i).getPuntajeAcumuladoParticipante() };
						modelo.addRow(fila);
					}
				}
			}
		}
		tablaListaPosiciones.setModel(modelo);
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
	 * Permite exportar en un archivo CSV los resultados finales que contiene el
	 * torneo finalizado.
	 */
	private void accionExportarResultados() {
		Object[] opciones = { "Sí", "No" };
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
			archivo = dialogo.getSelectedFile();
			if (!archivo.equals(null) && !archivo.getName().toUpperCase().endsWith(".CSV")) {
				archivo = new File(archivo.getAbsolutePath() + ".CSV");
			}
			if (archivo.exists()) {
				int respuesta1 = JOptionPane.showOptionDialog(null,
						archivo.getName() + " ya existe.\n ¿Desea reemplazarlo?", "Confirmar Descargar plantilla",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);
				if (respuesta1 != 0) {
					return;
				}
			}
			try {
				this.ventanaPrincipal.getTorneoActual().getAlgoritmoTorneo().generarReporteFinal(archivo);
				JOptionPane
				.showMessageDialog(null, "El archivo se ha guardado exitosamente.",
						"Generar reporte de " + ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
						.getNombreCiclo(Personalizacion.MAYUSCULA_SINGULAR),
						JOptionPane.INFORMATION_MESSAGE);
			} catch (ExcepcionUtilerias e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Permite cerrar la ventana.
	 */
	private void accionSalir() {
		dispose();
	}
}
