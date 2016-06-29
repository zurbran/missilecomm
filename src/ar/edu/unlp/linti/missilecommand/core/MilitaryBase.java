package ar.edu.unlp.linti.missilecommand.core;

import ar.edu.unlp.linti.missilecommand.ui.Drawable;
import ar.edu.unlp.linti.missilecommand.ui.GameObjectRenderer;

/**
 * 
 * Base Militar zardoniana. Tiene una cantidad de misiles, puede dispararlos a
 * una posicion arbitraria (por encima de una linea) y es destruible.
 */
public class MilitaryBase extends GameObject implements Zardonian, Destroyable, Drawable
{
	/*
	 * Solamente se puede disparar misiles por encima de esta linea imaginaria
	 */
	private static int MIN_HEIGHT = 385;
	int missileCount;

	/**
	 * @param position La posicion en la que se encuentra la base militar
	 */
	public MilitaryBase(Position position)
	{
		super(position);
		this.shape = new RectangleShape(20, 20);
		this.missileCount = 10;
	}

	/**
	 * Dispara un misil siempre y cuando todavia haya misiles en la base militar.
	 * @param dest La posicion a la que apunta el misil. Este explota cuando llega a esta posicion
	 */
	public void fireMissile(Position dest)
	{
		if (this.missileCount > 0)
		{
			if (dest.getY() > MIN_HEIGHT)
			{
				dest.setY(MIN_HEIGHT);
			}

			MAB mab = new MAB(this.position, dest);
			Game.getInstance().agregarGameObject(mab);
			this.missileCount--;

			// Cuando se terminan los MAB, desaparece la base militar
			if (this.missileCount == 0)
			{
				this.toRemove = true;
			}
		}
		else
		{
			System.out.println("Error. No hay mas misiles");
		}
	}

	@Override
	void update()
	{

	}

	/**
	 * @return the missileCount
	 */
	public int getMissileCount()
	{
		return missileCount;
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
