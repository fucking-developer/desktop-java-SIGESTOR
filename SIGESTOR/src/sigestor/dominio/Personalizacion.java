package sigestor.dominio;

import sigestor.excepcion.ExcepcionPersonalizacion;

/**
 * Sirve para guardar y validar la información ingresada en el
 * <code>PanelPersonalizacion</code>.
 * <p>
 * Las características de la clase <code>Personalizacion</code> son:
 * <ul>
 * <li><code>nombreCiclo</code> para el nombre de los ciclos.</li>
 * <li><code>nombreParticipante</code> para el nombre de los participantes.</li>
 * <li><code>nombreParticipanteInicial</code> para distinguir al participante
 * que inicia el encuentro.</li>
 * <li><code>nombreParticipanteFinal</code> para distinguir al participante que
 * no inicia el encuentro.</li>
 * <li><code>nombreEncuentro</code> para el nombre de los encuentros.</li>
 * <li><code>nombreParticipanteSinEncuentro</code> para los nombres de los
 * participantes sin encuentros.</li>
 * <li><code>existenciaMarcador</code> para validar si se habilitó el uso de
 * marcadores.</li>
 * <li><code>nombreMarcador</code> para distinguir el nombre de los
 * marcadores.</li>
 * <li><code>puntajeGanar</code> para el puntaje para el ganador.</li>
 * <li><code>puntajeEmpatar</code> para el puntaje para empatar.</li>
 * <li><code>puntajePerder</code> para el puntaje para perdedor.</li>
 * </ul>
 * 
 * @version 04/04/2022
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 */
public class Personalizacion {
	/**
	 * Para formatear una cadena en singular mayúscula inicial, con el resto de
	 * los caracteres en minúscula.
	 */
	public static final int MAYUSCULA_SINGULAR = 1;
	/**
	 * Para formatear una cadena en plural mayúscula inicial, con el resto de
	 * los caracteres en minúscula.
	 */
	public static final int MAYUSCULA_PLURAL = 2;
	/**
	 * Para formatear una cadena en minúscula singular.
	 */
	public static final int MINUSCULA_SINGULAR = 3;
	/**
	 * Para formatear una cadena en minúscula plural.
	 * 
	 */
	public static final int MINUSCULA_PLURAL = 4;

	/**
	 * Para el nombre de los ciclos en formato singular.
	 */
	private String nombreCiclo;
	/**
	 * Para el nombre de los participantes en formato singular.
	 */
	private String nombreParticipante;
	/**
	 * Para los participantes que inician el encuentro.
	 */
	private String nombreParticipanteInicial;
	/**
	 * Para los participantes que no inician el encuentro.
	 */
	private String nombreParticipanteFinal;
	/**
	 * Para el nombre de los encuentros en formato singular.
	 */
	private String nombreEncuentro;
	/**
	 * Para nombre de los participantes sin encuentros.
	 */
	private String nombreParticipanteSinEncuentro;
	/**
	 * Para validar si se habilitó el uso de marcadores.
	 */
	private boolean existenciaMarcador;
	/**
	 * Para el nombre de los marcadores en formato singular.
	 */
	private String nombreMarcador;
	/**
	 * Para el puntaje para el ganador.
	 */
	private float puntajeGanar;
	/**
	 * Para el puntaje para empatar.
	 */
	private float puntajeEmpatar;
	/**
	 * Para el puntaje para perdedor.
	 */
	private float puntajePerder;

	/**
	 * Permite crear el objeto <code>Personalizacion</code>.
	 * 
	 */
	public Personalizacion() {
		setExistenciaMarcador(false);
		setNombreCiclo("");
		setNombreEncuentro("");
		setNombreMarcador("");
		setNombreParticipante("");
		setNombreParticipanteInicial("");
		setNombreParticipanteFinal("");
		setNombreParticipanteSinEncuentro("");
		setPuntajeEmpatar(0.0f);
		setPuntajeGanar(0.0f);
		setPuntajePerder(0.0f);
	}

	/**
	 * Devuelve el nombre de los ciclos en el formato recibido.
	 * 
	 * @param tipo
	 *            Recibe el formato que se desea aplicar al nombre de los
	 *            ciclos.
	 * @return Regresa el nombre de los ciclos formateado acorde al parámetro
	 *         recibido.
	 */
	public String getNombreCiclo(int tipo) {
		String retorno = nombreCiclo;
		if (tipo == MAYUSCULA_SINGULAR) {
			retorno = retorno.toLowerCase();
			char[] arreglo = retorno.toCharArray();
			arreglo[0] = Character.toUpperCase(arreglo[0]);
			retorno = new String(arreglo);
			return retorno;
		} else if (tipo == MAYUSCULA_PLURAL) {
			retorno = retorno.toLowerCase();
			char[] arreglo = retorno.toCharArray();
			arreglo[0] = Character.toUpperCase(arreglo[0]);
			retorno = new String(arreglo);
			return retorno + "s";
		} else if (tipo == MINUSCULA_SINGULAR) {
			return "el(la) " + retorno.toLowerCase();
		} else if (tipo == MINUSCULA_PLURAL) {
			
			return "los(las) " + retorno.toLowerCase() + "s";
		}
		return retorno;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>nombreCiclo</code> y
	 * elimina espacios si existen al inicio y al final de la cadena.
	 * 
	 * @param nombreCiclo
	 *            Recibe el nombre de los ciclos en formato singular.
	 */
	public void setNombreCiclo(String nombreCiclo) {
		this.nombreCiclo = nombreCiclo.trim();
	}

	/**
	 * Devuelve el nombre de los participantes en el formato recibido.
	 * 
	 * @param tipo
	 *            Recibe el formato que se desea aplicar al nombre de los
	 *            participantes.
	 * @return Regresa el nombre de los participantes formateado acorde al
	 *         parámetro recibido.
	 */
	public String getNombreParticipante(int tipo) {
		String retorno = nombreParticipante;
		if (tipo == MAYUSCULA_SINGULAR) {
			retorno = retorno.toLowerCase();
			char[] arreglo = retorno.toCharArray();
			arreglo[0] = Character.toUpperCase(arreglo[0]);
			retorno = new String(arreglo);
			return retorno;
		} else if (tipo == MINUSCULA_PLURAL) {
			if (retorno.lastIndexOf("e") < 0) 
				return "los(las) " + retorno.toLowerCase() + "es";
			else 
				return "los(las) " + retorno.toLowerCase() + "s";
		} else if (tipo == MINUSCULA_SINGULAR) {
			return "el(la) " + retorno.toLowerCase();
		}
		return retorno;
	}

	/**
	 * Asigna el parámetro recibido a la variable
	 * <code>nombreParticipante</code> y elimina espacios si existen al inicio y
	 * al final de la cadena.
	 * 
	 * @param nombreParticipante
	 *            Recibe el nombre de los participantes en formato singular.
	 */
	public void setNombreParticipante(String nombreParticipante) {
		this.nombreParticipante = nombreParticipante.trim();
	}

	/**
	 * Devuelve el nombre de los participantes que inician el encuentro en
	 * formato de mayúscula inicial y el resto minúsculas.
	 * 
	 * @return Regresa el nombre para los participantes que inician el
	 *         encuentro.
	 */
	public String getNombreParticipanteInicial() {
		if (nombreParticipanteInicial == null) {
			String retorno = nombreParticipanteInicial.toLowerCase();
			char[] arreglo = retorno.toCharArray();
			arreglo[0] = Character.toUpperCase(arreglo[0]);
			retorno = new String(arreglo);
			return retorno;
		} else {
			return nombreParticipanteInicial;
		}
	}

	/**
	 * Asigna el parámetro recibido a la variable
	 * <code>nombreParticipanteInicial</code> y elimina espacios si existen al
	 * inicio y al final de la cadena.
	 * 
	 * @param nombreParticipanteInicial
	 *            Recibe el nombre para los participantes que inician el
	 *            encuentro.
	 */
	public void setNombreParticipanteInicial(String nombreParticipanteInicial) {
		this.nombreParticipanteInicial = nombreParticipanteInicial.trim();
	}

	/**
	 * Devuelve el nombre de los participantes que no inician el encuentro en
	 * formato de mayúscula inicial y el resto minúsculas.
	 * 
	 * @return Regresa el nombre para los participantes que no inician el
	 *         encuentro.
	 */
	public String getNombreParticipanteFinal() {
		if (nombreParticipanteFinal == null) {
			String retorno = nombreParticipanteFinal.toLowerCase();
			char[] arreglo = retorno.toCharArray();
			arreglo[0] = Character.toUpperCase(arreglo[0]);
			retorno = new String(arreglo);
			return retorno;
		} else {
			return nombreParticipanteFinal;
		}

	}

	/**
	 * Asigna el parámetro recibido a la variable
	 * <code>nombreParticipanteFinal</code> y elimina espacios si existen al
	 * inicio y al final de la cadena.
	 * 
	 * @param nombreParticipanteFinal
	 *            Recibe el nombre para los participantes que no inican el
	 *            encuentro.
	 */
	public void setNombreParticipanteFinal(String nombreParticipanteFinal) {
		this.nombreParticipanteFinal = nombreParticipanteFinal.trim();
	}

	/**
	 * Devuelve el nombre de los encuentros en el formato recibido.
	 * 
	 * @param tipo
	 *            Recibe el formato que se desea aplicar al nombre de los
	 *            encuentros.
	 * @return Regresa el nombre de los encuentros formateado acorde al
	 *         parametro recibido.
	 */
	public String getNombreEncuentro(int tipo) {
		String retorno = nombreEncuentro;
		if (tipo == MAYUSCULA_SINGULAR) {
			retorno = retorno.toLowerCase();
			char[] arreglo = retorno.toCharArray();
			arreglo[0] = Character.toUpperCase(arreglo[0]);
			retorno = new String(arreglo);
			return retorno;
		} else if (tipo == MINUSCULA_PLURAL) {
			return "los(las) " + retorno.toLowerCase() + "s";
		} else if (tipo == MINUSCULA_SINGULAR) {
			return "el(la) " + retorno.toLowerCase();
		}

		return retorno;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>nombreEncuentro</code> y
	 * elimina espacios si existen al inicio y al final de la cadena.
	 * 
	 * @param nombreEncuentro
	 *            Recibe el nombre para para los encuentros en formato singular.
	 */
	public void setNombreEncuentro(String nombreEncuentro) {
		this.nombreEncuentro = nombreEncuentro.trim();
	}

	/**
	 * Devuelve el nombre de los participantes sin encuentros en formato de
	 * mayúscula inicial y el resto minúsculas.
	 * 
	 * @return Regresa el nombre para los participantes sin encuentros.
	 */
	public String getNombreParticipanteSinEncuentro() {
		if (nombreParticipanteSinEncuentro == null) {
			String retorno = nombreParticipanteSinEncuentro.toLowerCase();
			char[] arreglo = retorno.toCharArray();
			arreglo[0] = Character.toUpperCase(arreglo[0]);
			retorno = new String(arreglo);
			return retorno;
		} else {
			return nombreParticipanteSinEncuentro;
		}
	}

	/**
	 * Asigna el parámetro recibido a la variable
	 * <code>ParticipanteSinEncuentro</code> y elimina espacios si existen al
	 * inicio y al final de la cadena.
	 * 
	 * @param nombreParticipanteSinEncuentro
	 *            Recibe el nombre para los participantes sin encuentros.
	 */
	public void setNombreParticipanteSinEncuentro(String nombreParticipanteSinEncuentro) {
		this.nombreParticipanteSinEncuentro = nombreParticipanteSinEncuentro.trim();
	}

	/**
	 * Devuelve la existencia de marcadores.
	 * 
	 * @return <tt> true </tt> si existen marcadores, <tt> false </tt> en caso
	 *         contrario.
	 */
	public boolean isExistenciaMarcador() {
		return existenciaMarcador;
	}

	/**
	 * Asigna el parámetro recibido a la variable
	 * <code>existenciaMarcador</code>.
	 * 
	 * @param existenciaMarcador
	 *            Recibe el valor booleano de la existencia de marcadores,
	 *            <tt>true</tt> si existen marcadores en el torneo,
	 *            <tt>false</tt> en caso contrario.
	 */
	public void setExistenciaMarcador(boolean existenciaMarcador) {
		this.existenciaMarcador = existenciaMarcador;
	}

	/**
	 * Devuelve el nombre de los marcadores en el formato recibido.
	 * 
	 * @param tipo
	 *            Recibe el formato que se desea aplicar al nombre de los
	 *            marcadores.
	 * @return Regresa el nombre de los marcadores formateado acorde al
	 *         parámetro recibido.
	 */
	public String getNombreMarcador(int tipo) {
		String retorno = nombreMarcador;
		if (tipo == MAYUSCULA_SINGULAR) {
			retorno = retorno.toLowerCase();
			char[] arreglo = retorno.toCharArray();
			arreglo[0] = Character.toUpperCase(arreglo[0]);
			retorno = new String(arreglo);
			return retorno;
		} else if (tipo == MINUSCULA_PLURAL) {
			return "los(las) " + retorno.toLowerCase() + "s";
		}
		return retorno;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>nombreMarcador</code> y
	 * elimina espacios si existen al inicio y al final de la cadena.
	 * 
	 * @param nombreMarcador
	 *            Recibe el nombre para los marcadores en formato singular.
	 */
	public void setNombreMarcador(String nombreMarcador) {
		this.nombreMarcador = nombreMarcador.trim();
	}

	/**
	 * Devuelve el puntaje para ganadores.
	 * 
	 * @return Regresa el puntaje para ganadores.
	 */
	public float getPuntajeGanar() {
		return puntajeGanar;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>puntajeGanar</code>.
	 * 
	 * @param puntajeGanar
	 *            Recibe el puntaje para ganadores.
	 */

	public void setPuntajeGanar(float puntajeGanar) {
		this.puntajeGanar = puntajeGanar;
	}

	/**
	 * Devuelve el puntaje para empatar.
	 * 
	 * @return Regresa el puntaje para empatar.
	 */
	public float getPuntajeEmpatar() {
		return puntajeEmpatar;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>puntajeEmpatar</code>.
	 * 
	 * @param puntajeEmpatar
	 *            Recibe el puntaje para empatar.
	 */
	public void setPuntajeEmpatar(float puntajeEmpatar) {
		this.puntajeEmpatar = puntajeEmpatar;
	}

	/**
	 * Devuelve el puntaje para perdedores.
	 * 
	 * @return Regresa el puntaje para perdedores.
	 */
	public float getPuntajePerder() {
		return puntajePerder;
	}

	/**
	 * Asigna el parámetro recibido a la variable <code>puntajePerder</code>.
	 * 
	 * @param puntajePerder
	 *            Recibe el puntaje para perdedores.
	 */

	public void setPuntajePerder(float puntajePerder) {
		this.puntajePerder = puntajePerder;
	}

	/**
	 * Permite validar los datos ingresados en el
	 * <code>PanelPersonalizacion</code>.
	 * 
	 * @throws ExcepcionPersonalizacion
	 *             Lanza cuando las variables miembros estén vacías o los
	 *             puntajes no sean coherentes.
	 */
	public void validarPersonalizacion() throws ExcepcionPersonalizacion {

		if (nombreCiclo.isEmpty()) {
			throw new ExcepcionPersonalizacion(ExcepcionPersonalizacion.MENSAJE_EXCEPCION_NOMBRE_CICLOS_VACIO);
		}

		if (nombreParticipante.isEmpty()) {
			throw new ExcepcionPersonalizacion(ExcepcionPersonalizacion.MENSAJE_EXCEPCION_NOMBRE_PARTICIPANTES_VACIO);
		}

		if (nombreParticipanteInicial.isEmpty()) {
			throw new ExcepcionPersonalizacion(
					ExcepcionPersonalizacion.MENSAJE_EXCEPCION_PARTICIPANTES_INICIA_ENCUENTRO_VACIO);
		}

		if (nombreParticipanteFinal.isEmpty()) {
			throw new ExcepcionPersonalizacion(
					ExcepcionPersonalizacion.MENSAJE_EXCEPCION_PARTICIPANTES_NO_INICIA_ENCUENTRO_VACIO);
		}

		if (nombreEncuentro.isEmpty()) {
			throw new ExcepcionPersonalizacion(ExcepcionPersonalizacion.MENSAJE_EXCEPCION_NOMBRE_ENCUENTROS_VACIO);
		}

		if (nombreParticipanteSinEncuentro.isEmpty()) {
			throw new ExcepcionPersonalizacion(
					ExcepcionPersonalizacion.MENSAJE_EXCEPCION_PARTICIPANTES_SIN_ENCUENTRO_VACIO);
		}

		if (isExistenciaMarcador()) {
			if (nombreMarcador.isEmpty()) {
				throw new ExcepcionPersonalizacion(ExcepcionPersonalizacion.MENSAJE_EXCEPCION_MARCADORES_VACIO);
			}
		}

		if (puntajeGanar <= puntajeEmpatar || puntajeEmpatar <= puntajePerder) {
			throw new ExcepcionPersonalizacion(ExcepcionPersonalizacion.MENSAJE_EXCEPCION_PUNTAJES_INCOHERENTES);
		}

	}
}
