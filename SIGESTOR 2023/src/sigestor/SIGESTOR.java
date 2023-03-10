package sigestor;

import sigestor.gui.VentanaPrincipal;

/**
 * Esta clase sirve pera ejecutar el sistema
 * 
 * @author Ricky Didier Peralta Reyes
 * @author Uriel Romeo Cruz Cortes
 * @author Jennifer Cort�s P�rez
 * @author Beatriz Andrea Jim�nez R�os
 * @author Alicia Adriana Clemente Hernandez
 * @author Luis Fernando de la Cruz L�pez
 * @author Luis Antonio Ruiz Sierra
 * @author Victor Triste P�rez
 * @author Jonathan Eduardo Ibarra Mart�nez
 * @author Hern�n Sesa� Lop�z Arag�n
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
