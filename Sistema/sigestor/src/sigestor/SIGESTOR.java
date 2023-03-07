package sigestor;

import sigestor.gui.VentanaPrincipal;

/**
 * Esta clase sirve pera ejecutar el sistema
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cortés Pérez
 * @author Beatriz Andrea Jiménez Ríos
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz López
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste Pérez
 * @author Jonathan Eduardo Ibarra Martínez
 * @author Hernán Sesaí Lopéz Aragón
 * @author Francisco Samuel Reyes Cortes
 * 
 *
 */
public class SIGESTOR {
	/**
	 * @param args Acepta valores cuando ejecuta lineas de comandos.
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			new VentanaPrincipal(args[0]);
		} else {
			new VentanaPrincipal();
		}
	}
}
