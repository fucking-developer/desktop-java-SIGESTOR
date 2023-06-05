package sigestor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Sirve para manejar la interfaz de personalizacion.
 * <p>
 * Las características de la clase <code>PanelPersonalizacion</code> son:
 * <ul>
 * <li><code>campoNombreParticipante</code> para ingresar el nombre
 * personalizado del participante.</li>
 * <li><code>campoNombreParticipanteQueInicia</code> para añadir el nombre
 * personalizado del participante que inicia.</li>
 * <li><code>campoNombreParticipanteQueNoInicia</code> para añadir el nombre
 * personalizado del participante que no inicia.</li>
 * <li><code>campoNombreCiclo</code> para ingresar el nombre personalizado del
 * ciclo.</li>
 * <li><code>campoNombreEncuentro</code> para ingresar el nombre personalizado
 * de los encuentros.</li>
 * <li><code>campoNombreParticipantesSinEncuentros</code> para ingresar el
 * nombre personalizado de los participantes sin encuentros.</li>
 * <li><code>spinnerPuntajeGanar</code> para ingresar el puntaje para
 * ganar.</li>
 * <li><code>spinnerPuntajePerder</code> para ingresar el puntaje para
 * perder.</li>
 * <li><code>spinnerPuntajeEmpatar</code> para ingresar el puntaje para
 * empatar.</li>
 * <li><code>opcionMarcadorSi</code> para validar si habra marcadores.</li>
 * <li><code>opcionMarcadorNo</code> para validar si no habra marcadores.</li>
 * <li><code>ventanaPrincipal</code> para hacer referencia a la clase
 * <code>VentanaPrincipal</code>.</li>
 * 
 * </ul>
 * @version 02/06/2023
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 *
 */
public class PanelPersonalizacion extends JPanel implements ActionListener, ItemListener {

	/**
	 * Sirve para definir un id que sera usado por la virtual machine cuando
	 * serializa y deserealiza el applet.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Campo para ingresar el nombre personalizado de los participantes.
	 */
	private JTextField campoNombreParticipante;
	/**
	 * Campo para ingresar el nombre personalizado de los participantes que inician
	 * el encuentro.
	 */
	private JTextField campoNombreParticipanteQueInicia;
	/**
	 * Campo para ingresar el nombre personalizado de los participantes que no
	 * inician el encuentro.
	 */
	private JTextField campoNombreParticipanteQueNoInicia;
	/**
	 * Campo para ingresar el nombre personalizado del ciclo.
	 */
	private JTextField campoNombreCiclo;
	/**
	 * Campo para ingresar el nombre personalizado de los encuentros.
	 */
	private JTextField campoNombreEncuentro;
	/**
	 * Campo para ingresar el nombre personalizado de los participantes sin
	 * encuentros.
	 */
	private JTextField campoNombreParticipantesSinEncuentros;
	/**
	 * Campo para ingresar el nombre personalizado de los marcadores.
	 */
	private JTextField campoNombreMarcadores;
	/**
	 * Campo para ingresar el puntaje para ganar.
	 */
	private JSpinner spinnerPuntajeGanar;
	/**
	 * Campo para ingresar el puntaje para perder.
	 */
	private JSpinner spinnerPuntajePerder;
	/**
	 * Campo para ingresar el puntaje para empatar.
	 */
	private JSpinner spinnerPuntajeEmpatar;
	/**
	 * Botón de opción para validar si se desea usar marcadores.
	 */
	private JRadioButton opcionMarcadorSi;
	/**
	 * Botón de opción para validar si no se desea usar marcadores.
	 */
	private JRadioButton opcionMarcadorNo;
	/**
	 * Referencia a la clase <code>VentanaPrincipal</code>
	 */
	private VentanaPrincipal ventanaPrincipal;

	/**
	 * Constructor que consiste en mostrar en pantalla el
	 * <code>PanelPersonalización</code> con sus respectivos componentes.
	 * 
	 * @param principal Referencia a la clase <code>VentanaPrincipal</code>.
	 * 
	 */
	public PanelPersonalizacion(VentanaPrincipal principal) {
		this.ventanaPrincipal = principal;
		JLabel etiquetaNombreParticipante = new JLabel("1.- Nombre de los participantes:");
		JLabel etiquetaNombreParticipanteQueInicia = new JLabel(
				"2.- Nombre de los participantes que inician el encuentro:");
		JLabel etiquetaNombreParticipanteQueNoInicia = new JLabel(
				"3.- Nombre de los participantes que no inician el encuentro:");
		JLabel etiquetaNombreCiclo = new JLabel("4.- Nombre de los ciclos:");
		JLabel etiquetaNombreEncuentro = new JLabel("5.- Nombre de los encuentros:");
		JLabel etiquetaNombreParticipantesSinEncuentros = new JLabel("6.- Nombre para participantes sin encuentros:");
		JLabel etiquetaPuntajeGanar = new JLabel("7.- Puntaje para ganador:");
		JLabel etiquetaPuntajePerder = new JLabel("8.- Puntaje para perdedor:");
		JLabel etiquetaPuntajeEmpatar = new JLabel("9.- Puntaje al empatar:");
		JLabel etiquetaMarcadores = new JLabel("10.- Marcadores:");
		JLabel etiquetaNombreMarcadores = new JLabel("11.- Nombre de los marcadores:");

		this.setLayout(new BorderLayout());
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new GridLayout(11, 1, 1, 1));

		// ________________0___________________
		JPanel panelAux2 = new JPanel();
		JPanel panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(1, 2));
		panelAux2.setLayout(new BorderLayout());
		JPanel panelAux4 = new JPanel();
		panelAux4.add(etiquetaNombreParticipante);
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		campoNombreParticipante = new JTextField();
		campoNombreParticipante.setPreferredSize(new Dimension(130, 30));
		panelAux4 = new JPanel();
		panelAux4.add(campoNombreParticipante);
		panelAux2 = new JPanel();
		panelAux2.setLayout(new BorderLayout());
		panelAux2.add(panelAux4, BorderLayout.CENTER);
		panelAux3.add(panelAux2);

		panelAux.add(panelAux3);

		// ________________1___________________
		panelAux2 = new JPanel();
		panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(1, 2));
		panelAux2.setLayout(new BorderLayout());
		panelAux4 = new JPanel();
		panelAux4.add(etiquetaNombreParticipanteQueInicia);
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		campoNombreParticipanteQueInicia = new JTextField();
		campoNombreParticipanteQueInicia.setPreferredSize(new Dimension(130, 30));
		panelAux4 = new JPanel();
		panelAux4.add(campoNombreParticipanteQueInicia);
		panelAux2 = new JPanel();
		panelAux2.setLayout(new BorderLayout());
		panelAux2.add(panelAux4, BorderLayout.CENTER);
		panelAux3.add(panelAux2);

		panelAux.add(panelAux3);
		// _______________2_____________________
		panelAux2 = new JPanel();
		panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(1, 2));
		panelAux2.setLayout(new BorderLayout());
		panelAux4 = new JPanel();
		panelAux4.add(etiquetaNombreParticipanteQueNoInicia);
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		campoNombreParticipanteQueNoInicia = new JTextField();
		campoNombreParticipanteQueNoInicia.setPreferredSize(new Dimension(130, 30));
		panelAux4 = new JPanel();
		panelAux4.add(campoNombreParticipanteQueNoInicia);
		panelAux2 = new JPanel();
		panelAux2.setLayout(new BorderLayout());
		panelAux2.add(panelAux4, BorderLayout.CENTER);
		panelAux3.add(panelAux2);

		panelAux.add(panelAux3);
		// _____________3________________________
		panelAux2 = new JPanel();
		panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(1, 2));

		panelAux2.setLayout(new BorderLayout());
		panelAux4 = new JPanel();
		panelAux4.add(etiquetaNombreCiclo);
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		campoNombreCiclo = new JTextField();
		campoNombreCiclo.setPreferredSize(new Dimension(130, 30));
		panelAux4 = new JPanel();
		panelAux4.add(campoNombreCiclo);
		panelAux2 = new JPanel();
		panelAux2.setLayout(new BorderLayout());
		panelAux2.add(panelAux4, BorderLayout.CENTER);
		panelAux3.add(panelAux2);

		panelAux.add(panelAux3);
		// ____________4__________________________
		panelAux2 = new JPanel();
		panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(1, 2));

		panelAux2.setLayout(new BorderLayout());
		panelAux4 = new JPanel();
		panelAux4.add(etiquetaNombreEncuentro);
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		campoNombreEncuentro = new JTextField();
		campoNombreEncuentro.setPreferredSize(new Dimension(130, 30));
		panelAux4 = new JPanel();
		panelAux4.add(campoNombreEncuentro);
		panelAux2 = new JPanel();
		panelAux2.setLayout(new BorderLayout());
		panelAux2.add(panelAux4, BorderLayout.CENTER);
		panelAux3.add(panelAux2);

		panelAux.add(panelAux3);
		// ___________5___________________________
		panelAux2 = new JPanel();
		panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(1, 2));

		panelAux2.setLayout(new BorderLayout());
		panelAux4 = new JPanel();
		panelAux4.add(etiquetaNombreParticipantesSinEncuentros);
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		campoNombreParticipantesSinEncuentros = new JTextField();
		campoNombreParticipantesSinEncuentros.setPreferredSize(new Dimension(130, 30));
		panelAux4 = new JPanel();
		panelAux4.add(campoNombreParticipantesSinEncuentros);
		panelAux2 = new JPanel();
		panelAux2.setLayout(new BorderLayout());
		panelAux2.add(panelAux4, BorderLayout.CENTER);
		panelAux3.add(panelAux2);

		panelAux.add(panelAux3);
		// ___________6___________________________
		panelAux2 = new JPanel();
		panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(1, 2));

		panelAux2.setLayout(new BorderLayout());
		panelAux4 = new JPanel();
		panelAux4.add(etiquetaPuntajeGanar);
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		spinnerPuntajeGanar = new JSpinner(new SpinnerNumberModel(0.0f, 0.0f, 100f, 0.1f));
		spinnerPuntajeGanar.setPreferredSize(new Dimension(130, 30));
		panelAux4 = new JPanel();
		panelAux4.add(spinnerPuntajeGanar);
		panelAux2 = new JPanel();
		panelAux2.setLayout(new BorderLayout());
		panelAux2.add(panelAux4, BorderLayout.CENTER);
		panelAux3.add(panelAux2);

		panelAux.add(panelAux3);
		// ___________7___________________________
		panelAux2 = new JPanel();
		panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(1, 2));

		panelAux2.setLayout(new BorderLayout());
		panelAux4 = new JPanel();
		panelAux4.add(etiquetaPuntajePerder);
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		spinnerPuntajePerder = new JSpinner(new SpinnerNumberModel(0.0f, 0.0f, 100f, 0.1f));
		spinnerPuntajePerder.setPreferredSize(new Dimension(130, 30));
		panelAux4 = new JPanel();
		panelAux4.add(spinnerPuntajePerder);
		panelAux2 = new JPanel();
		panelAux2.setLayout(new BorderLayout());
		panelAux2.add(panelAux4, BorderLayout.CENTER);
		panelAux3.add(panelAux2);

		panelAux.add(panelAux3);
		// ___________8___________________________
		panelAux2 = new JPanel();
		panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(1, 2));

		panelAux2.setLayout(new BorderLayout());
		panelAux4 = new JPanel();
		panelAux4.add(etiquetaPuntajeEmpatar);
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		spinnerPuntajeEmpatar = new JSpinner(new SpinnerNumberModel(0.0f, 0.0f, 100f, 0.1f));
		spinnerPuntajeEmpatar.setPreferredSize(new Dimension(130, 30));
		panelAux4 = new JPanel();
		panelAux4.add(spinnerPuntajeEmpatar);
		panelAux2 = new JPanel();
		panelAux2.setLayout(new BorderLayout());
		panelAux2.add(panelAux4, BorderLayout.CENTER);
		panelAux3.add(panelAux2);

		panelAux.add(panelAux3);
		// ______________9___________________________
		panelAux2 = new JPanel();
		panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(1, 2));

		panelAux2.setLayout(new BorderLayout());
		panelAux4 = new JPanel();
		panelAux4.setLayout(new BoxLayout(panelAux4, BoxLayout.PAGE_AXIS));
		JPanel aux5 = new JPanel();
		aux5.add(etiquetaMarcadores);
		panelAux4.add(aux5);

		aux5 = new JPanel();
		opcionMarcadorSi = new JRadioButton("Sí", false);
		aux5.add(opcionMarcadorSi);
		opcionMarcadorSi.addItemListener(this);

		opcionMarcadorNo = new JRadioButton("No", true);
		aux5.add(opcionMarcadorNo);
		opcionMarcadorNo.addItemListener(this);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(opcionMarcadorSi);
		grupo.add(opcionMarcadorNo);

		panelAux4.add(aux5);
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		panelAux4 = new JPanel();
		panelAux2 = new JPanel();
		panelAux2.setLayout(new BorderLayout());
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		panelAux.add(panelAux3);
		// ____________10_______________________________
		panelAux2 = new JPanel();
		panelAux3 = new JPanel();
		panelAux3.setLayout(new GridLayout(1, 2));

		panelAux2.setLayout(new BorderLayout());
		panelAux4 = new JPanel();
		panelAux4.setLayout(new FlowLayout());
		aux5 = new JPanel();
		aux5.add(etiquetaNombreMarcadores);
		panelAux4.add(aux5);

		aux5 = new JPanel();
		campoNombreMarcadores = new JTextField();
		campoNombreMarcadores.setEnabled(false);
		campoNombreMarcadores.setPreferredSize(new Dimension(130, 30));
		aux5.add(campoNombreMarcadores);
		panelAux4.add(aux5);
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		panelAux4 = new JPanel();
		panelAux2 = new JPanel();
		panelAux2.setLayout(new BorderLayout());
		panelAux2.add(panelAux4, BorderLayout.WEST);
		panelAux3.add(panelAux2);

		panelAux.add(panelAux3);

		campoNombreParticipante
				.setToolTipText("Escriba el nombre generalizado para los participantes. Ejemplo: - Jugador, - Equipo");
		campoNombreParticipanteQueInicia.setToolTipText(
				"Escriba el nombre generalizado de los participantes que inician el encuentro. Ejemplo: - Blancas, - Local");
		campoNombreParticipanteQueNoInicia.setToolTipText(
				"Escriba el nombre generalizado de los participantes que no inician el torneo. Ejemplo: - Negras, - Visitante");
		campoNombreCiclo
				.setToolTipText("Escriba el nombre generalizado para el ciclo. Ejemplo: - Ronda, - Jornada, - Semana");
		campoNombreEncuentro
				.setToolTipText("Escriba el nombre generalizado para los encuentros. Ejemplos: - Partida, - Partido");
		campoNombreParticipantesSinEncuentros.setToolTipText(
				"Escriba el nombre generalizado para los participantes sin encuentros. Ejemplo: - Descanso");
		spinnerPuntajeGanar.setToolTipText("Asigne el puntaje para el ganador");
		spinnerPuntajePerder.setToolTipText("Asigne el puntaje para el perdedor");
		spinnerPuntajeEmpatar.setToolTipText("Asigne el puntaje para empatar");
		opcionMarcadorSi.setToolTipText("Permite asignar marcadores al torneo");
		opcionMarcadorNo.setToolTipText("No permite asignar marcadores al torneo");
		campoNombreMarcadores.setToolTipText("Escriba el nombre en singular de los marcadores del torneo");

		JScrollPane scrollPanelAux = new JScrollPane(panelAux, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanelAux.setPreferredSize(new Dimension(700, 400));

		etiquetaNombreParticipante.setDisplayedMnemonic('1');
		etiquetaNombreParticipante.setLabelFor(campoNombreParticipante);
		etiquetaNombreParticipanteQueInicia.setDisplayedMnemonic('2');
		etiquetaNombreParticipanteQueInicia.setLabelFor(campoNombreParticipanteQueInicia);
		etiquetaNombreParticipanteQueNoInicia.setDisplayedMnemonic('3');
		etiquetaNombreParticipanteQueNoInicia.setLabelFor(campoNombreParticipanteQueNoInicia);
		etiquetaNombreCiclo.setDisplayedMnemonic('4');
		etiquetaNombreCiclo.setLabelFor(campoNombreCiclo);
		etiquetaNombreEncuentro.setDisplayedMnemonic('5');
		etiquetaNombreEncuentro.setLabelFor(campoNombreEncuentro);
		etiquetaNombreParticipantesSinEncuentros.setDisplayedMnemonic('6');
		etiquetaNombreParticipantesSinEncuentros.setLabelFor(campoNombreParticipantesSinEncuentros);
		etiquetaPuntajeGanar.setDisplayedMnemonic('7');
		etiquetaPuntajeGanar.setLabelFor(spinnerPuntajeGanar);
		etiquetaPuntajePerder.setDisplayedMnemonic('8');
		etiquetaPuntajePerder.setLabelFor(spinnerPuntajePerder);
		etiquetaPuntajeEmpatar.setDisplayedMnemonic('9');
		etiquetaPuntajeEmpatar.setLabelFor(spinnerPuntajeEmpatar);
		etiquetaMarcadores.setDisplayedMnemonic('m');
		etiquetaMarcadores.setLabelFor(opcionMarcadorSi);
		etiquetaNombreMarcadores.setDisplayedMnemonic('l');
		etiquetaNombreMarcadores.setLabelFor(campoNombreMarcadores);

		inicializarPanelPersonalizacion();

		this.add(scrollPanelAux, BorderLayout.CENTER);

		this.setVisible(false);
	}

	/**
	 * Encargado del manejo de eventos producido al hacer clic sobre un componente
	 * de la ventana principal.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Consiste en validar si los marcadores fueron seleccionados, de tal forma
	 * manda a llamar los métodos correspondientes.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(opcionMarcadorSi)) {
			accionMarcadoresSi();
		} else if (e.getSource().equals(opcionMarcadorNo)) {
			accionMarcadoresNo();
		}
	}

	/**
	 * Consiste en incializar cada uno de los campos de
	 * <code>PanelPersonalizacion</code>.
	 */
	public void inicializarPanelPersonalizacion() {
		if (ventanaPrincipal.getTorneoActual() != null) {
			campoNombreParticipante
					.setText(ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipante(0));
			campoNombreParticipanteQueInicia.setText(
					ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipanteInicial());
			campoNombreParticipanteQueNoInicia
					.setText(ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipanteFinal());
			campoNombreCiclo.setText(ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreCiclo(0));
			campoNombreEncuentro
					.setText(ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreEncuentro(0));
			campoNombreParticipantesSinEncuentros.setText(
					ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreParticipanteSinEncuentro());
			spinnerPuntajeGanar
					.setValue(ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getPuntajeGanar());
			spinnerPuntajeEmpatar
					.setValue(ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getPuntajeEmpatar());
			spinnerPuntajePerder
					.setValue(ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getPuntajePerder());
			opcionMarcadorSi
					.setSelected(ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().isExistenciaMarcador());
			if (opcionMarcadorSi.isSelected()) {
				campoNombreMarcadores
						.setText(ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().getNombreMarcador(0));
			}

		}
	}

	/**
	 * Guarda los datos ingresados para poder ser utilizados durante el torneo.
	 */
	public void guardarPersonalizacion() {
		ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
				.setNombreParticipante(campoNombreParticipante.getText());
		ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
				.setNombreParticipanteInicial(campoNombreParticipanteQueInicia.getText());
		ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
				.setNombreParticipanteFinal(campoNombreParticipanteQueNoInicia.getText());
		ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().setNombreCiclo(campoNombreCiclo.getText());
		ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().setNombreEncuentro(campoNombreEncuentro.getText());
		ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
				.setNombreParticipanteSinEncuentro(campoNombreParticipantesSinEncuentros.getText());
		double valor = Double.parseDouble(spinnerPuntajeGanar.getValue().toString());
		ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().setPuntajeGanar((float) valor);
		valor = Double.parseDouble(spinnerPuntajeEmpatar.getValue().toString());
		ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().setPuntajeEmpatar((float) valor);
		valor = Double.parseDouble(spinnerPuntajePerder.getValue().toString());
		ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().setPuntajePerder((float) valor);
		ventanaPrincipal.getTorneoActual().getDatosPersonalizacion()
				.setExistenciaMarcador(opcionMarcadorSi.isSelected());
		ventanaPrincipal.getTorneoActual().getDatosPersonalizacion().setNombreMarcador(campoNombreMarcadores.getText());
	}

	/**
	 * Desabilita el campo <code>campoNombreMarcadores</code>.
	 */
	private void accionMarcadoresNo() {
		campoNombreMarcadores.setEnabled(false);
	}

	/**
	 * Habilita el campo <code>campoNombreMarcadores</code>.
	 */
	private void accionMarcadoresSi() {
		campoNombreMarcadores.setEnabled(true);
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
}
