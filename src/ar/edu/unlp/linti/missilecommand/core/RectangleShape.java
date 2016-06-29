package ar.edu.unlp.linti.missilecommand.core;

/**
 * Forma de rectangulo centrado en la posicion de GameObject, ancho width y alto
 * height. No contiene la posicion, solamente su forma/tamano
 * 
 */
public class RectangleShape implements Shape
{
	private int height;
	private int width;

	/**
	 * 
	 * @param height
	 *            Alto total del rectangulo
	 * @param width
	 *            Ancho total del rectangulo
	 */
	public RectangleShape(int width, int height)
	{
		this.height = height;
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}
}
