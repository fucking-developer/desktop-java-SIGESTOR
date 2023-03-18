package sigestor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import sigestor.dominio.CriteriosDesempate;
import sigestor.dominio.Participante;
import sigestor.dominio.Personalizacion;
import sigestor.dominio.Torneo;
import sigestor.excepcion.ExcepcionBaseDatos;
import sigestor.excepcion.ExcepcionBaseDatosCriteriosDesempate;
import sigestor.excepcion.ExcepcionBaseDatosParticipante;
import sigestor.excepcion.ExcepcionBaseDatosPersonalizacion;
import sigestor.excepcion.ExcepcionBaseDatosTorneo;
import sigestor.excepcion.ExcepcionCapturarResultados;

/**
 * Sirve para navegar entre los paneles: <code>PanelGeneral</code>,
 * <code>PanelCriteriosDesempate</code>, <code>PanelPersonalizacion</code> y
 * <code>PanelAdministrarParticipante</code>, y también nos permite guardar el
 * contenido de estos paneles en la base de datos.
 * <p>
 * Las características de la clase <code>DialogoAdministrarTorneo</code> son:
 * <ul>
 * <li><code>botonGeneral</code> Para mostrar el panel de los datos generales
 * del torneo.</li>
 * <li><code>botonCriteriosDesempate</code> Para mostrar el panel de los
 * criterios de dempate.</li>
 * <li><code>botonPersonalizacion</code> Para mostrar el panel de los datos de
 * personalización.</li>
 * <li><code>botonAdministrarParticipantes</code> Para mostrar el panel de
 * administrar torneo.</li>
 * <li><code>botonGuardar</code> Para guardar los datos del torneo.</li>
 * <li><code>botonCancelar</code> Para cancelar el llenado de datos del
 * torneo.</li>
 * <li><code>panelGeneral</code> Panel que muestra el formulario para los datos
 * generales del torneo.</li>
 * <li><code>panelCriteriosDesempate</code> Panel que muestra el formulario para
 * los criterios de desempate.</li>
 * <li><code>panelPersonalizacion</code> Panel que muestra el formulario para
 * los datos de personalización del torneo.</li>
 * <li><code>panelAdministrarParticipantes</code> Panel para administrar a los
 * particiantes del torneo.</li>
 * <li><code>panelContenedor</code> Panel que contendra otros poneles adentro y
 * asi poder mostrar solo uno.</li>
 * <li><code>ventanaPrincipal</code> Objeto de tipo VentanaPrincipal para manejo
 * del torneo.</li>
 * 
 * </ul>
 * 
 * @version 05/06/2022
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * 
 */
public class DialogoAdministarTorneo extends JDialog implements WindowListener {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Botón para mostrar el panel <code>panelGeneral.</code>
	 */
	private JButton botonGeneral;
	/**
	 * Botón para mostrar el panel <code>panelCriteriosDesempate.</code>
	 */
	private JButton botonCriteriosDesempate;
	/**
	 * Botón para mostrar el panel <code>panelPersonalizacion.</code>
	 */
	private JButton botonPersonalizacion;
	/**
	 * Botón para mostrar el panel <code>panelAdministrarParticipantes</code>
	 */
	private JButton botonAdministrarParticipantes;
	/**
	 * Botón para importar una lista de participantes.
	 */
	private JButton botonImportar;
	/**
	 * Botón para guardar los datos del torneo ingresados en los paneles.
	 */
	private JButton botonGuardar;
	/**
	 * Botón para cancelar el llenado de datos en los paneles.
	 */
	private JButton botonCancelar;
	/**
	 * Panel que muestra un formulario de los datos generales del torneo.
	 */
	private PanelGeneral panelGeneral;
	/**
	 * Panel que nos permite seleccionar criterios de desempate para el torneo.
	 */
	private PanelCriteriosDesempate panelCriteriosDesempate;
	/**
	 * Panel que muestra un formulario de los datos de personalización del torneo.
	 */
	private PanelPersonalizacion panelPersonalizacion;
	/**
	 * Panel que administra a los participantes del torneo.
	 */
	private PanelAdministrarParticipantes panelAdministrarParticipantes;
	/**
	 * Panel que contiene a los paneles:
	 * <p>panelGeneral.
	 * <p>panelCriteriosDesempate.
	 * <p>panelPersonalizacion.
	 * <p>panelAdministrarParticipantes.
	 * <p>panelContenedor.
	 */
	private JPanel panelContenedor = new JPanel();
	/**
	 * Objeto de tipo <code>VentanaPrincipal</code> que contiene el torneo actual.
	 */
	private VentanaPrincipal ventanaPrincipal;

	/**
	 * Constructor visualizar el <code>DialogoAdministrarTorneo.</code> el cual
	 * sirve para administrar lod datos del torneo.
	 * 
	 * @param principal recibe un objeto de tipo ventanaPrincipal el cual contiene
	 *                  el objeto de tipo torneo.
	 */
	public DialogoAdministarTorneo(VentanaPrincipal principal) {
		super(principal, "Administrar torneo");

		ventanaPrincipal = principal;

		panelGeneral = new PanelGeneral(principal);
		panelCriteriosDesempate = new PanelCriteriosDesempate(principal);
		panelPersonalizacion = new PanelPersonalizacion(principal);
		panelAdministrarParticipantes = new PanelAdministrarParticipantes(principal);

		this.setModal(true);

		this.setLayout(new BorderLayout());
		JPanel panelAux = new JPanel();

		JPanel panelAux2 = new JPanel();

		Action accionBotonGeneral = new AbstractAction("General") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPanelGeneral();
				panelGeneral.mostrarPanel();
				panelCriteriosDesempate.ocultarPanel();
				panelPersonalizacion.ocultarPanel();
				panelAdministrarParticipantes.ocultarPanel();
			}
		};

		accionBotonGeneral.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		accionBotonGeneral.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		accionBotonGeneral.putValue(Action.SHORT_DESCRIPTION, "Permite modificar los datos generales del torneo");
		botonGeneral = new JButton(accionBotonGeneral);
		botonGeneral.getActionMap().put("botonGeneral", accionBotonGeneral);
		botonGeneral.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionBotonGeneral.getValue(Action.ACCELERATOR_KEY), "botonGeneral");
		botonGeneral.setBackground(new Color(204, 204, 204));
		botonGeneral.setPreferredSize(new Dimension(180, 30));
		panelAux2.add(botonGeneral);
		panelAux.add(panelAux2);

		panelAux2 = new JPanel();
		Action accionBotonCriteriosDesempate = new AbstractAction("Criterios de desempate") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPanelCriteriosDesempate();
				panelGeneral.ocultarPanel();
				panelCriteriosDesempate.mostrarPanel();
				panelPersonalizacion.ocultarPanel();
				panelAdministrarParticipantes.ocultarPanel();
			}
		};
		accionBotonCriteriosDesempate.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_D);
		accionBotonCriteriosDesempate.putValue(Action.DISPLAYED_MNEMONIC_INDEX_KEY, 13);
		accionBotonCriteriosDesempate.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
		accionBotonCriteriosDesempate.putValue(Action.SHORT_DESCRIPTION,
				"Permite seleccionar los criterios de desempate del torneo");
		botonCriteriosDesempate = new JButton(accionBotonCriteriosDesempate);
		botonCriteriosDesempate.getActionMap().put("botonCriteriosDesempate", accionBotonCriteriosDesempate);
		botonCriteriosDesempate.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				(KeyStroke) accionBotonCriteriosDesempate.getValue(Action.ACCELERATOR_KEY), "botonCriteriosDesempate");
		botonCriteriosDesempate.setPreferredSize(new Dimension(180, 30));
		botonCriteriosDesempate.setBackground(new Color(204, 204, 204));
		panelAux2.add(botonCriteriosDesempate);
		panelAux.add(panelAux2);

		panelAux2 = new JPanel();

		Action accionBotonPersonalizacion = new AbstractAction("Personalización") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPanelPersonalizacion();
				panelGeneral.ocultarPanel();
				panelCriteriosDesempate.ocultarPanel();
				panelPersonalizacion.mostrarPanel();
				panelAdministrarParticipantes.ocultarPanel();
			}
		};
		accionBotonPersonalizacion.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
		accionBotonPersonalizacion.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		accionBotonPersonalizacion.putValue(Action.SHORT_DESCRIPTION,
				"Permite modificar la personalización del torneo");
		botonPersonalizacion = new JButton(accionBotonPersonalizacion);
		botonCriteriosDesempate.getActionMap().put("botonPersonalizacion", accionBotonPersonalizacion);
		botonCriteriosDesempate.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionBotonPersonalizacion.getValue(Action.ACCELERATOR_KEY), "botonPersonalizacion");
		botonPersonalizacion.setPreferredSize(new Dimension(180, 30));// 130
		botonPersonalizacion.setBackground(new Color(204, 204, 204));
		panelAux2.add(botonPersonalizacion);
		panelAux.add(panelAux2);

		panelAux2 = new JPanel();
		Action accionBotonAdministrarParticipante = new AbstractAction("Administrar participantes") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPanelAdministrarParticipantes();
				panelGeneral.ocultarPanel();
				panelCriteriosDesempate.ocultarPanel();
				panelPersonalizacion.ocultarPanel();
				panelAdministrarParticipantes.mostrarPanel();
				
			}


		};
		
		accionBotonAdministrarParticipante.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
		accionBotonAdministrarParticipante.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		accionBotonAdministrarParticipante.putValue(Action.SHORT_DESCRIPTION,
				"Permite modificar los datos de los participantes del torneo");
		botonAdministrarParticipantes = new JButton(accionBotonAdministrarParticipante);
		botonAdministrarParticipantes.getActionMap().put("botonAdministrarParticipantes",
				accionBotonAdministrarParticipante);
		botonAdministrarParticipantes.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				(KeyStroke) accionBotonAdministrarParticipante.getValue(Action.ACCELERATOR_KEY),
				"botonAdministrarParticipantes");
		botonAdministrarParticipantes.setPreferredSize(new Dimension(180, 30));
		botonAdministrarParticipantes.setBackground(new Color(204, 204, 204));
		panelAux2.add(botonAdministrarParticipantes);
		panelAux.add(panelAux2);

		this.add(panelAux, BorderLayout.NORTH);
		panelContenedor.add(panelGeneral);
		panelContenedor.add(panelCriteriosDesempate);
		panelContenedor.add(panelPersonalizacion);
		panelContenedor.add(panelAdministrarParticipantes);

		this.add(panelContenedor, BorderLayout.CENTER);

		panelAux = new JPanel();		
		ImageIcon imagenF = new ImageIcon(getClass().getResource("/imagenes/guardar.png"));
		Image icon = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);
		

		Action accionGuardar = new AbstractAction("Guardar", new ImageIcon(icon)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				guardarAvanceTorneo();

			}
		};
		accionGuardar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_G);
		accionGuardar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		accionGuardar.putValue(Action.SHORT_DESCRIPTION,
				"Permite guardar parcialmente los datos ingresados del torneo");
		botonGuardar = new JButton(accionGuardar);
		botonGuardar.getActionMap().put("guardar", accionGuardar);
		botonGuardar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionGuardar.getValue(Action.ACCELERATOR_KEY), "guardar");
		botonGuardar.setPreferredSize(new Dimension(120, 30));
		panelAux.add(botonGuardar);

		imagenF = new ImageIcon(getClass().getResource("/imagenes/cancelar.png"));
		icon = imagenF.getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING);

		Action accionCancelar = new AbstractAction("Cancelar", new ImageIcon(icon)) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarAvanceTorneo();
			}

		};

		accionCancelar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
		accionCancelar.putValue(Action.SHORT_DESCRIPTION, "Permite cancelar la configuración del torneo");
		botonCancelar = new JButton(accionCancelar);
		botonCancelar.getActionMap().put("cancelar", accionGuardar);
		botonCancelar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionCancelar.getValue(Action.ACCELERATOR_KEY), "cancelar");
		botonCancelar.setPreferredSize(new Dimension(130, 30));
		panelAux.add(botonCancelar);
		botonCancelar.setMnemonic(KeyEvent.VK_C);

		JLabel etiquetaAyuda = new JLabel("");
		panelAux.add(etiquetaAyuda);
		Action accionAyuda = new AbstractAction("", null) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaPrincipal.accionCargarManual();
			}
		};
		accionAyuda.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		etiquetaAyuda.getActionMap().put("ayuda", accionAyuda);
		etiquetaAyuda.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionAyuda.getValue(Action.ACCELERATOR_KEY), "ayuda");

		this.add(panelAux, BorderLayout.SOUTH);
		this.addWindowListener(this);
		this.setSize(new Dimension(805, 550));
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Metodo que permite mostrar el panel <code>panelGeneral</code> en el panel
	 * <code>panelContenedor</code> y acultar a los demás paneles.
	 */
	private void mostrarPanelGeneral() {
		botonGeneral.setBackground(new Color(153, 153, 153));
		botonCriteriosDesempate.setBackground(new Color(204, 204, 204));
		botonPersonalizacion.setBackground(new Color(204, 204, 204));
		botonAdministrarParticipantes.setBackground(new Color(204, 204, 204));
	}

	/**
	 * Metodo que permite mostrar el panel <code>panelCriteriosDesempate</code> en
	 * el panel <code>panelContenedor</code> y acultar a los demás paneles.
	 */
	private void mostrarPanelCriteriosDesempate() {
		botonGeneral.setBackground(new Color(204, 204, 204));
		botonCriteriosDesempate.setBackground(new Color(153, 153, 153));
		botonPersonalizacion.setBackground(new Color(204, 204, 204));
		botonAdministrarParticipantes.setBackground(new Color(204, 204, 204));
	}

	/**
	 * Metodo que permite mostrar el panel <code>panelPersonalizacion</code> en el
	 * panel <code>panelContenedor</code> y acultar a los demás paneles.
	 */

	private void mostrarPanelPersonalizacion() {
		botonGeneral.setBackground(new Color(204, 204, 204));
		botonCriteriosDesempate.setBackground(new Color(204, 204, 204));
		botonPersonalizacion.setBackground(new Color(153, 153, 153));
		botonAdministrarParticipantes.setBackground(new Color(204, 204, 204));
	}

	/**
	 * Metodo que permite mostrar el panel
	 * <code>panelAdministrarParticipantes</code> en el panel
	 * <code>panelContenedor</code> y acultar a los demás paneles.
	 */
	private void mostrarPanelAdministrarParticipantes() {
		botonGeneral.setBackground(new Color(204, 204, 204));
		botonCriteriosDesempate.setBackground(new Color(204, 204, 204));
		botonPersonalizacion.setBackground(new Color(204, 204, 204));
		botonAdministrarParticipantes.setBackground(new Color(153, 153, 153));
	}

	/**
	 * Metodo que permite guardar en la base de datos los datos ingresados en los
	 * paneles.
	 */
	private void guardarAvanceTorneo() {
		if (ventanaPrincipal.getTorneoActual() == null) {
			String nombreArchivo = guardarArchivo();
			if (nombreArchivo != null) {
				Torneo torneo = new Torneo();
				torneo.setDatosPersonalizacion(new Personalizacion());
				torneo.setCriteriosDesempate(new CriteriosDesempate());
				//ArrayList<Participante> p = new ArrayList<Participante>();
				torneo.setListaParticipantes(new ArrayList<Participante>());
				ventanaPrincipal.setTorneoActual(torneo);
				panelGeneral.guardarGeneral();
				panelPersonalizacion.guardarPersonalizacion();
				panelCriteriosDesempate.guardarCriteriosSeleccionados();
				panelAdministrarParticipantes.guardarParticipantes();
				try {
					ventanaPrincipal.getTorneoActual().setNombreArchivo(nombreArchivo);
					ventanaPrincipal.getTorneoActual().guardarTorneo();
					File file = new File(ventanaPrincipal.getTorneoActual().getNombreArchivo());
					JOptionPane.showMessageDialog(null,
							"Sus datos del torneo " + file.getName().replaceAll(".torn", "")
									+ " han sido guardados exitosamente",
							"Guardar Torneo", JOptionPane.INFORMATION_MESSAGE);
					
					ventanaPrincipal.getTorneoActual().recuperarTorneo();
					dispose();
				} catch (ExcepcionBaseDatos e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionBaseDatosTorneo e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionBaseDatosCriteriosDesempate e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionBaseDatosPersonalizacion e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionBaseDatosParticipante e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar torneo", JOptionPane.ERROR_MESSAGE);
				} catch (ExcepcionCapturarResultados e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar torneo", JOptionPane.ERROR_MESSAGE);
				}

			}
		} else if (ventanaPrincipal.getTorneoActual() != null) {
			try {
				panelGeneral.guardarGeneral();
				panelPersonalizacion.guardarPersonalizacion();
				panelCriteriosDesempate.guardarCriteriosSeleccionados();
				panelAdministrarParticipantes.guardarParticipantes();
				ventanaPrincipal.getTorneoActual().actualizarTorneo();
				File file = new File(ventanaPrincipal.getTorneoActual().getNombreArchivo());
				JOptionPane.showMessageDialog(null,
						"Sus datos del torneo " + file.getName().replaceAll(".torn", "")
								+ " han sido guardados exitosamente",
						"Guardar Torneo", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} catch (ExcepcionBaseDatos e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar torneo", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionBaseDatosTorneo e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar torneo", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionBaseDatosCriteriosDesempate e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar torneo", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionBaseDatosPersonalizacion e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar torneo", JOptionPane.ERROR_MESSAGE);
			} catch (ExcepcionBaseDatosParticipante e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Guardar torneo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Metodo que permite cancelar el llenado de datos para el torneo.
	 */
	private void cancelarAvanceTorneo() {
		String[] valores = { "Sí", "No" };
		int opcion = JOptionPane.showOptionDialog(this,
				"¿Está seguro de cancelar el llenado del formulario?\nSi presiona el botón Sí el avance de sus datos se borrarán y no se podrán recuperar",
				"Cancelar administrar torneo", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, valores,
				valores[1]);
		if (opcion == 0) {
			dispose();
		}
	}

	/**
	 * Metodo que permite seleccionar un nombre de archivo cuando creamos un torneo
	 * nuevo.
	 * @return Retorna la ruta del archivo.
	 */
	private String guardarArchivo() {
		JFileChooser dialogo = new JFileChooser();
		dialogo.setDialogTitle("SIGESTOR");
		FileFilter filtro1 = new FileNameExtensionFilter("Archivo de SIGESTOR", "Torn", "TORN");
		dialogo.setFileFilter(filtro1);
		dialogo.addChoosableFileFilter(filtro1);
		dialogo.setAcceptAllFileFilterUsed(false);
		dialogo.setCurrentDirectory(new File(System.getProperty("user.dir")));
		dialogo.setSelectedFile(null);
		dialogo.setMultiSelectionEnabled(false);
		if (dialogo.showSaveDialog(ventanaPrincipal) == JFileChooser.APPROVE_OPTION) {
			File guarda = dialogo.getSelectedFile();
			File aux = new File(guarda.getName() + ".torn");
			if (!guarda.exists() && !aux.exists()) {
				StringTokenizer tokenizer = new StringTokenizer(guarda.getName(), ".");
				if (tokenizer.countTokens() > 1) {
					String nombreArchivo = tokenizer.nextToken();
					String extension = tokenizer.nextToken();
					if (extension.equals("torn") || extension.equals("Torn") || extension.equals("TORN")) {
						return nombreArchivo + ".torn";
					} else {
						JOptionPane.showMessageDialog(null, "¡La extensión del archivo no es correcta!",
								"Guardar torneo", JOptionPane.ERROR_MESSAGE);
						return null;
					}
				} else {
					return guarda.getAbsolutePath() + ".torn";
				}

			} else {
				JOptionPane.showMessageDialog(null, "¡El nombre de este archivo ya existe!", "Guardar torneo",
						JOptionPane.ERROR_MESSAGE);
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * @param e valor por defecto
	 * 
	 */
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param e valor por defecto
	 * 
	 */
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * @param e valor por defecto
	 * 
	 */
	public void windowClosing(WindowEvent e) {
		cancelarAvanceTorneo();
	}

	/**
	 * @param e valor por defecto
	 * 
	 */
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param e valor por defecto
	 * 
	 */
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param e valor por defecto
	 * 
	 */
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param e valor por defecto
	 * 
	 */
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
