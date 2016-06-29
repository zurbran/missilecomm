package ar.edu.unlp.linti.missilecommand.core;

/**
 * 
 * Representa la forma de un circulo, internamente con un radio. No incluye la
 * posicion del circulo, solamente su forma
 * 
 */
public class CircleShape implements Shape
{
	private int radius;

	/**
	 * @param radius
	 */
	public CircleShape(int radius)
	{
		this.radius = radius;
	}

	/**
	 * @return the radius
	 */
	public int getRadius()
	{
		return radius;
	}

	/**
	 * @param radius
	 *            the radius to set
	 */
	public void setRadius(int radius)
	{
		this.radius = radius;
	}
}
