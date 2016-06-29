package ar.edu.unlp.linti.missilecommand.core;

import ar.edu.unlp.linti.missilecommand.ui.Drawable;
import ar.edu.unlp.linti.missilecommand.ui.GameObjectRenderer;

/**
 * 
 * Ciudad Zardoniana. No puede hacer nada mas que ser destruida.
 *
 */
public class City extends GameObject implements Zardonian, Destroyable, Drawable
{


	/**
	 * @param position
	 */
	public City(Position position)
	{
		super(position);
		this.shape = new RectangleShape(30, 42);
	}

	@Override
	void update()
	{

	}

	@Override
	public void destroyMe()
	{
		this.toRemove = true;
	}

	@Override
	public void draw(GameObjectRenderer visitor)
	{
		visitor.draw(this);
	}

}
