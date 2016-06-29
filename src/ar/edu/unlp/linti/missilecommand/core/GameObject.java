package ar.edu.unlp.linti.missilecommand.core;

/**
 * 
 * Clase abstracta utilizada para todos los objetos del juego, como misiles,
 * explosiones, ciudades, etc.
 * 
 * Contiene una posicion, y una forma.
 * 
 */
public abstract class GameObject
{
	/**
	 * Posicion del objeto
	 */
	protected Position position;
	/**
	 * Forma del objeto
	 */
	protected Shape shape;
	/**
	 * Determina si el objeto debe ser eliminado. 
	 */
	protected boolean toRemove;

	/**
	 * Permite a cada entidad realizar el procesamiento que sea necesario
	 */
	abstract void update();

	public GameObject(Position position)
	{
		this.position = new Position(position);
	}

	public GameObject()
	{

	}

	/**
	 * @return the position
	 */
	public Position getPosition()
	{
		return position;
	}

	/**
	 * @return the shape
	 */
	public Shape getShape()
	{
		return shape;
	}

	/**
	 * @return the toRemove
	 */
	public boolean isToRemove()
	{
		return toRemove;
	}
	
	public void dispose()
	{
		this.position = null;
		this.shape = null;
	}

}
