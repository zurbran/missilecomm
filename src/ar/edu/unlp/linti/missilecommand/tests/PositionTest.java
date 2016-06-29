package ar.edu.unlp.linti.missilecommand.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlp.linti.missilecommand.core.Position;

/**
 * 
 * MainPanel que permite comprobar el buen funcionamiento del a clase Position
 *
 */
public class PositionTest
{
	@Test
	public void test()
	{
		Position p1 = new Position(2, 4);
		assertTrue(p1.getX() == 2);
		assertTrue(p1.getY() == 4);

		Position p2 = new Position(4, 2);
		assertTrue(p1.getEuclideanDistance(p1) == 0);
		assertTrue(p1.getEuclideanDistance(p2) == Math.sqrt(Math.pow(p1.getX()
				- p2.getX(), 2)
				+ Math.pow(p1.getY() - p2.getY(), 2)));
	}
}
