package ar.edu.unlp.linti.missilecommand.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlp.linti.missilecommand.core.MBI;
import ar.edu.unlp.linti.missilecommand.core.Missile;
import ar.edu.unlp.linti.missilecommand.core.Position;

/**
 * 
 * MainPanel que permite probar cierto funcionamiento de un misil
 *
 */
public class MissileTest
{

	@Test
	public void test()
	{

		Missile misil = new MBI(new Position(90, 600), new Position(30, 20), 1);
		Position old = new Position(misil.getPosition().getX(), misil.getPosition().getY());
		misil.update();
		misil.update();
		misil.update();
		misil.update();
		misil.update();
		//Contemplar errores de precision
		assertTrue(Math.abs(old.getEuclideanDistance(misil.getPosition()) - 5) < 0.001);
		//System.out.println(old.getEuclideanDistance(misil.position));
	}

}
