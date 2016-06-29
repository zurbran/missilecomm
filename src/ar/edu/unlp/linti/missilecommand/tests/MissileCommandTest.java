package ar.edu.unlp.linti.missilecommand.tests;

import ar.edu.unlp.linti.missilecommand.core.Game;

/**
 * 
 * Esta clase prueba el juego, corriendo el nivel 2, luego el nivel 7, luego el
 * nivel 10, reiniciando el juego cada vez (para evitar que pierda las ciudades
 * la computadora y no se pueda terminar el test)
 */
public class MissileCommandTest
{
	public static void main(String[] args)
	{
		/*
		 * Estoy iniciando el juego 3 veces separadas, arrancando cada una en el
		 * nivel mencionado. Hago estoy de iniciar el juego de cero cada vez, ya
		 * que sino ya en el primer nivel la computadora pierde. De esta forma,
		 * pueden empezar los tres niveles teniendo todas las ciudades. En el
		 * juego lo esperable seria que se inicie del nivel 1, con todas las
		 * ciudades, y Game se encargue de continuar a los siguientes niveles
		 * siempre y cuando no pierda, y la cantidad de ciudades se mantiene
		 * entre niveles (a menos que con puntos gane uno).
		 */

		/*
		 * Evite imprimir para el test final la posicion de cada object cuando
		 * se desplaza porque sino el output se hace completamente ilegible, con
		 * casi 150000 impresiones de actualizacion de posicion. 
		 */
		
		Game game = Game.getInstance();
		game.start(1);
		//game.start(7);
		//game.start(10);
	}
}
